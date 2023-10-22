package com.osroyale.content.shootingstar;

import com.osroyale.content.skill.impl.mining.Mining;
import com.osroyale.content.skill.impl.mining.PickaxeData;
import com.osroyale.game.action.Action;
import com.osroyale.game.action.policy.WalkablePolicy;
import com.osroyale.game.world.entity.mob.player.Player;
import com.osroyale.game.world.entity.skill.Skill;
import com.osroyale.game.world.items.Item;
import com.osroyale.game.world.object.GameObject;

public class ShootingStarAction extends Action<Player> {

    private PickaxeData pickaxe;
    private GameObject object;

    public ShootingStarAction(Player player, PickaxeData pickaxe, GameObject object) {
        super(player, 3, false);
        this.pickaxe = pickaxe;
        this.object = object;
    }

    @Override
    public WalkablePolicy getWalkablePolicy() {
        return WalkablePolicy.NON_WALKABLE;
    }

    @Override
    public String getName() {
        return "shooting-star-action";
    }

    @Override
    protected boolean canSchedule() {
        return !getMob().skills.get(Skill.MINING).isDoingSkill();
    }

    @Override
    protected void onSchedule() {
        getMob().animate(pickaxe.animation);
    }

    @Override
    protected void onCancel(boolean logout) {
        getMob().resetFace();
        getMob().skills.get(Skill.MINING).setDoingSkill(false);
    }

    @Override
    protected void execute() {
        if (!getMob().skills.get(Skill.MINING).isDoingSkill()) {
            cancel();
            return;
        }

        if (!mine()) {
            cancel();
        }
    }

    private boolean mine() {
        if (!getMob().inventory.hasCapacityFor(new Item(25527))) {
            getMob().dialogueFactory.sendStatement("You can't carry anymore star dust.").execute();
            return false;
        }

        getMob().animate(pickaxe.animation);

        if (Mining.success(getMob(), ShootingStar.shootingStarData.getMiningLevel(), pickaxe)) {
            if (object == null || !object.active()) {
                return false;
            }

            if (ShootingStar.shootingStarData.availableDust <= 0) {
                getMob().resetAnimation();
                return false;
            }

            ShootingStar.shootingStarData.decreaseDust(getMob());
        }
        return true;
    }
}
