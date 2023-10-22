package com.osroyale.game.engine;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.osroyale.Config;
import com.osroyale.content.writer.InterfaceWriter;
import com.osroyale.content.writer.impl.InformationWriter;
import com.osroyale.game.engine.sync.ClientSynchronizer;
import com.osroyale.game.engine.sync.ParallelClientSynchronizer;
import com.osroyale.game.engine.sync.SequentialClientSynchronizer;
import com.osroyale.game.engine.sync.task.NpcPreUpdateTask;
import com.osroyale.game.engine.sync.task.PlayerPreUpdateTask;
import com.osroyale.game.world.World;
import com.osroyale.game.world.entity.MobList;
import com.osroyale.game.world.entity.mob.npc.Npc;
import com.osroyale.game.world.entity.mob.player.Player;
import com.osroyale.util.Stopwatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public final class GameEngine extends AbstractScheduledService {

    public static final int TICK_MILLIS = 600;

    public static int millisToTicks(final int millis) {
        return millis / TICK_MILLIS;
    }

    public static int clientTicksToMillis(final int clientTicks) {
        return clientTicks * 20;
    }

    public static int clientTicksToServerTicks(final int clientTicks) {
        return millisToTicks(clientTicksToMillis(clientTicks));
    }

    private static final Logger logger = LogManager.getLogger();

    private final ClientSynchronizer synchronizer;

    @Override
    protected String serviceName() {
        return "GameThread";
    }

    public GameEngine() {
        synchronizer = (Config.PARALLEL_GAME_ENGINE ? new ParallelClientSynchronizer() : new SequentialClientSynchronizer());
    }

    @Override
    protected void runOneIteration() {
        try {
            final Stopwatch stopwatch = Stopwatch.start();
            final Stopwatch stopwatch2 = Stopwatch.start();

            World world = World.get();
            MobList<Player> players = World.getPlayers();
            MobList<Npc> npcs = World.getNpcs();

            world.dequeLogins();

            long elapsed = stopwatch.elapsedTime(TimeUnit.MILLISECONDS);
            if (elapsed > 10 && Config.SERVER_DEBUG) {
                System.out.printf("world.dequeLogins(): %d ms%n", elapsed);
            }

            stopwatch.reset();
            world.dequeLogouts();
            elapsed = stopwatch.elapsedTime(TimeUnit.MILLISECONDS);
            if (elapsed > 10 && Config.SERVER_DEBUG) {
                System.out.printf("world.dequeLogouts(): %d ms%n", elapsed);
            }

            stopwatch.reset();
            npcs.forEach(npc -> new NpcPreUpdateTask(npc).run());
            elapsed = stopwatch.elapsedTime(TimeUnit.MILLISECONDS);
            if (elapsed > 10 && Config.SERVER_DEBUG) {
                System.out.printf("NpcPreUpateTask: %d ms%n", elapsed);
            }

            stopwatch.reset();
            players.forEach(player -> new PlayerPreUpdateTask(player).run());
            elapsed = stopwatch.elapsedTime(TimeUnit.MILLISECONDS);
            if (elapsed > 10 && Config.SERVER_DEBUG) {
                System.out.printf("PlayerPreUpdateTask: %d ms%n", elapsed);
            }

            stopwatch.reset();
            world.process();
            elapsed = stopwatch.elapsedTime(TimeUnit.MILLISECONDS);
            if (elapsed > 10 && Config.SERVER_DEBUG) {
                System.out.printf("world.process(): %d ms%n", elapsed);
            }

            stopwatch.reset();
            npcs.forEach(Npc::sequence);
            elapsed = stopwatch.elapsedTime(TimeUnit.MILLISECONDS);
            if (elapsed > 10 && Config.SERVER_DEBUG) {
                System.out.printf("npc.sequence(): %d ms%n", elapsed);
            }

            stopwatch.reset();
            players.forEach(player -> {
                try {
                    player.sequence();
                } catch (Exception ex) {
                    logger.error(String.format("error player.sequence(): %s", player), ex);
                }
            });
            elapsed = stopwatch.elapsedTime(TimeUnit.MILLISECONDS);
            if (elapsed > 10 && Config.SERVER_DEBUG) {
                System.out.printf("player.sequence(): %d ms%n", elapsed);
            }

            stopwatch.reset();
            try {
                synchronizer.synchronize(players, npcs);
            } catch (Exception ex) {
                logger.fatal("Error in the main game sequencer.", ex);
            }
            elapsed = stopwatch.elapsedTime(TimeUnit.MILLISECONDS);
            if (elapsed > 10 && Config.SERVER_DEBUG) {
                System.out.printf("synchronizer.synchronize(): %d ms%n", elapsed);
            }
            players.forEach(player -> {
                    InterfaceWriter.write(new InformationWriter(player));
            });

            stopwatch.reset();
            if (stopwatch2.elapsedTime(TimeUnit.MILLISECONDS) > 60 && Config.SERVER_DEBUG) {
                System.out.printf("CYCLE END: %d ms%n", stopwatch2.elapsedTime(TimeUnit.MILLISECONDS));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, TICK_MILLIS, TimeUnit.MILLISECONDS);
    }

}
