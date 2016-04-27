package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class TestServCoreExplosions implements Listener {

    private TestServCore testServCore;

    public TestServCoreExplosions(TestServCore testServCore) {
        this.testServCore = testServCore;

        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().clear();
    }
}
