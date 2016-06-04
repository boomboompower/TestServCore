package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityExplodeEvent;

import static me.boomboompower.testserv.utils.Register.*;

public class TestServCoreExplosions implements Listener {

    private TestServCore testServCore;

    public TestServCoreExplosions(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().clear();
    }
}
