package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 26/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

import static me.boomboompower.testserv.utils.Register.*;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreChat implements Listener {

    private TestServCore testServCore;

    public TestServCoreChat(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);

        ArrayList<String> array = new ArrayList<String>();
        String message = e.getMessage();
        Player p = e.getPlayer();

        for (Entity entity : p.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (array.contains(player.getName())) return;
                if (player.getName().equals(p.getName())) return;
                sendToPlayer(player, "&9&l" + p.getName() + " &b>&r " + message);
                array.add(player.getName());
            }
        }

        if (array.isEmpty()) {
            sendToPlayer(p, "&9&lWorld:&7 You spoke but nobody heard you...");
        } else if (!array.isEmpty()) {
            if (array.size() > 1) {
                sendToPlayer(p, "&9&lWorld:&7 A player heard your call...");
            } else {
                sendToPlayer(p, "&9&lWorld:&7 some players heard your call...");
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < array.size(); i++) {
                builder.append(array.get(i));
                builder.append(", ");
            }
            String players = builder.toString().trim();

            if (array.size() < 1) players = array.get(0);
            sendToPlayer(p, "&9&lWorld:&7 they were: " + players);
            array.clear();
        } else {
            sendToConsole("&cAn error occured for &4" + p.getName() + "&c\'s array! Please look into this!");
            sendToConsole("&cThe message was &4" + e.getMessage() + "&c ");
            sendToPlayer(p, "&4&lError:&7 An error occured while sending nearby players");
            sendToPlayer(p, "&4&lError:&7 the message, &4boomboompower&c has been alerted!");
        }
    }
}
