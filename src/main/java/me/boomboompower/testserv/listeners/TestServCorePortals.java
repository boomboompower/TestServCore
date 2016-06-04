package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 28/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;
import org.bukkit.event.world.PortalCreateEvent;

import java.util.ArrayList;

import static me.boomboompower.testserv.utils.Utils.*;
import static me.boomboompower.testserv.utils.Register.*;

public class TestServCorePortals implements Listener {

    private TestServCore testServCore;
    private ArrayList<String> inPortal = new ArrayList<String>();

    public TestServCorePortals(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onEntityPortalEnter(EntityPortalEnterEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!inPortal.contains(player.getName())) {
                sendToPlayer(player, "&fYou are filled with the essence of the portal...");
            }
        }
    }

    @EventHandler
    private void onEntityPortalExit(EntityPortalExitEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            event.setAfter(player.getLocation().getDirection().normalize().multiply(3));
            sendToPlayer(player, "&fThe essence slowly fades away...");
            if (inPortal.contains(player.getName())) inPortal.remove(player.getName());
        }
    }

    @EventHandler
    private void onPortalCreate(PortalCreateEvent e) {
        e.setCancelled(true);
    }
}
