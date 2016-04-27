package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class TestServCoreMobDeath implements Listener {

    private TestServCore testServCore;

    public TestServCoreMobDeath(TestServCore testServCore) {
        this.testServCore = testServCore;

        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEntityDeath(EntityDeathEvent e) {
        Entity entity = e.getEntity();

        e.setDroppedExp(0);

        if (entity instanceof Slime) {
            Slime slime = (Slime) entity;

            slime.setSize(slime.getSize() - 1);
            if (slime.getSize() < 1) {
                slime.remove();
            }
        } else {
            // Coming soon...
        }
    }
}
