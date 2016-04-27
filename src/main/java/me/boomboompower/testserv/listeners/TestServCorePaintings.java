package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;

import java.util.ArrayList;

import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCorePaintings implements Listener {

    private TestServCore testServCore;

    private ArrayList one;
    private ArrayList two;

    public TestServCorePaintings(TestServCore testServCore) {
        this.testServCore = testServCore;
        this.one = new ArrayList();
        this.two = new ArrayList();

        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    private void hangingBreakEvent(HangingBreakEvent e) {
        e.setCancelled(true);
    }

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
