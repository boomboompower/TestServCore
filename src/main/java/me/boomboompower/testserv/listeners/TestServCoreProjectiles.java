package me.boomboompower.testserv.listeners;

/*
* Made for TestServCore
* by boomboompower 16/05/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Effect;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import static me.boomboompower.testserv.utils.Register.registerEvents;

public class TestServCoreProjectiles implements Listener {

    private TestServCore testServCore;

    public TestServCoreProjectiles(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onProjectileHit(ProjectileHitEvent event) {
        Entity e = event.getEntity();
        World w = e.getWorld();
        w.playEffect(e.getLocation(), Effect.SMOKE, 500);
    }

    @EventHandler
    private void onProjectileLaunch(ProjectileLaunchEvent event) {
        Entity e = event.getEntity();
        World w = e.getWorld();
        w.playEffect(e.getLocation(), Effect.FIREWORKS_SPARK, 500);
        e.setVelocity(e.getLocation().getDirection().normalize().multiply(1.5));
    }
}
