package com.osroyale.net.packet.in;

import com.osroyale.game.world.entity.mob.player.Player;
import com.osroyale.net.packet.GamePacket;
import com.osroyale.net.packet.PacketListenerMeta;
import com.osroyale.net.packet.PacketListener;

@PacketListenerMeta({241})
public class MouseClickPacketListener implements PacketListener {

    @Override
    public void handlePacket(Player player, GamePacket packet) {

    }

}
