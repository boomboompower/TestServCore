package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;

import java.util.ArrayList;
import java.util.List;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCorePaintings implements Listener {

    private TestServCore testServCore;

    private ArrayList<String> one;
    private ArrayList<String> two;

    public TestServCorePaintings(TestServCore testServCore) {
        this.testServCore = testServCore;
        this.one = new ArrayList<String>();
        this.two = new ArrayList<String>();

        registerEvents(this);
    }

    @EventHandler
    private void hangingBreakEvent(HangingBreakEvent e) {
        e.setCancelled(true);
        List<Entity> entityList = e.getEntity().getNearbyEntities(5D, 5D, 5D);

        if (entityList instanceof Player) {
            Player player = (Player) entityList;

            sendToPlayer(player, "&c&lPaintings: &4I am invulnerable!");
        }
    }

    @EventHandler
    private void hangingPlaceEvent(HangingPlaceEvent e) {
        Player p = e.getPlayer();
        if (!permissionCheck(p, "TestServ.Paintings.Place")) {
            e.setCancelled(true);
            if (one.contains(p.getName())) {
                sendToPlayer(p, "&cYou&4&l cannot&c place paintings. &4(Final warning)&c.");
                one.remove(p.getName());
                two.add(p.getName());
            } else if (two.contains(p.getName())) {
                p.kickPlayer(kickMessage());
                two.remove(p.getName());
            } else {
                sendToPlayer(p, "&cCannot place paintings &4(First warning)&c.");
                one.add(p.getName());
            }
        }
    }
}
