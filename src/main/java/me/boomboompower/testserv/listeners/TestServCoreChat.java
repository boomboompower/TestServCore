package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 26/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreChat implements Listener {

    private TestServCore testServCore;

    public TestServCoreChat(TestServCore testServCore) {
        this.testServCore = testServCore;

        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    @EventHandler
    private void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);

        ArrayList array = new ArrayList();
        String message = e.getMessage();
        Player p = e.getPlayer();

        for (Entity entity : p.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (player.getName().equals(p.getName())) return;
                sendToPlayer(player, "&b" + p.getName() + " &9>&r " + message);
                array.add(player.getName());
            }
        }

        if (array.isEmpty()) {
            sendToPlayer(p, "&8&lWorld&7 You spoke but nobody heard you...");
        } else if (!array.isEmpty()) {
            if (array.size() > 1) {
                sendToPlayer(p, "&2&lWorld&a a player heard your call...");
            } else {
                sendToPlayer(p, "&2&lWorld&a some players heard your call...");
            }
            String players = array.toString().replace("[", "").replace("]", "");

            sendToPlayer(p, "&2&lWorld&a they were: " + players);
            array.clear();
        } else {
            sendToConsole("&cAn error occured for &4" + p.getName() + "&c\'s array! Please look into this!");
            sendToPlayer(p, "&4&lError:&c An error occured while sending nearby players");
            sendToPlayer(p, "&4&lError:&c the message, &4boomboompower&c has been alerted!");
        }
    }
}
