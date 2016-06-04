package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 26/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.GameMode;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import java.util.ArrayList;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreBlockHandler implements Listener {

    private TestServCore testServCore;

    private ArrayList<String> listBreakOne;
    private ArrayList<String> listBreakTwo;

    private ArrayList<String> listPlaceOne;
    private ArrayList<String> listPlaceTwo;

    public TestServCoreBlockHandler(TestServCore testServCore) {
        this.testServCore = testServCore;

        this.listBreakOne = new ArrayList<String>();
        this.listPlaceOne = new ArrayList<String>();
        this.listBreakTwo = new ArrayList<String>();
        this.listPlaceTwo = new ArrayList<String>();

        registerEvents(this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEntityChangeBlock(EntityChangeBlockEvent e) {
        if (e.getEntity() instanceof Enderman) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.CREATIVE) return;
        if (!permissionCheck(p, "testServ.Break")) {
            e.setCancelled(true);
            if (listBreakOne.contains(p.getName())) {
                sendToPlayer(p, denyMessage("break blocks", "Final warning"));
                listBreakOne.remove(p.getName());
                listBreakTwo.add(p.getName());
            } else if (listBreakTwo.contains(p.getName())) {
                p.kickPlayer(translate(kickMessage()));
                listBreakTwo.remove(p.getName());
            } else {
                sendToPlayer(p, denyMessage("break blocks", "First warning"));
                listBreakOne.add(p.getName());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.CREATIVE) return;
        if (!permissionCheck(p, "testServ.Break")) {
            e.setCancelled(true);
            if (listPlaceOne.contains(p.getName())) {
                sendToPlayer(p, denyMessage("place blocks", "Final warning"));
                listPlaceOne.remove(p.getName());
                listPlaceTwo.add(p.getName());
            } else if (listPlaceTwo.contains(p.getName())) {
                p.kickPlayer(kickMessage());
                listPlaceTwo.remove(p.getName());
            } else {
                sendToPlayer(p, denyMessage("place blocks", "First warning"));
                listPlaceOne.add(p.getName());
            }
        }
    }
}
