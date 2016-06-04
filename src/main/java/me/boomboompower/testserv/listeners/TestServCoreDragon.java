package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;

import static me.boomboompower.testserv.utils.Utils.*;
import static me.boomboompower.testserv.utils.Register.*;

public class TestServCoreDragon implements Listener {

    private TestServCore testServCore;

    public TestServCoreDragon(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onDragonPhaseChange(EnderDragonChangePhaseEvent event) {
        Entity e = event.getEntity();

        e.setCustomName(translate("&5&lThe almighty EnderDragon"));
        e.setCustomNameVisible(true);
    }
}
