package me.boomboompower.testserv.listeners;

/*
* Made for TestServCore
* by boomboompower 13/05/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCorePlayerItems implements Listener {

    private TestServCore testServCore;

    public TestServCorePlayerItems(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onItemDrop(PlayerDropItemEvent event) {
        Player p = event.getPlayer();
        if (gameMode(p) == GameMode.CREATIVE) return;
        if (!permissionCheck(p, "TestServ.Drop")) {
            event.setCancelled(true);
            sendToPlayer(p, "&cYou cannot drop items!");
        }
    }

    @EventHandler
    private void onItemPickup(PlayerPickupItemEvent event) {
        Player p = event.getPlayer();
        if (gameMode(p) == GameMode.CREATIVE) return;
        if (!permissionCheck(p, "TestServ.Pickup")) {
            event.setCancelled(true);
            sendToPlayer(p, "&cYou cannot pickup items!");
        }
    }

    @EventHandler
    private void onArrowPickup(PlayerPickupArrowEvent event) {
        Player p = event.getPlayer();
        if (gameMode(p) == GameMode.CREATIVE) return;
        if (!permissionCheck(p, "TestServ.Pickup")) {
            sendToPlayer(p, "&cYou cannot pickup arrows!");
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onBucketEmpty(PlayerBucketEmptyEvent event) {
        Player p = event.getPlayer();
        if (gameMode(p) == GameMode.CREATIVE) return;
        if (!permissionCheck(p, "TestServ.Bucket")) {
            sendToPlayer(p, "&cYou cannot empty buckets!");
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onBucketEvent(PlayerBucketEvent event) {
        Player p = event.getPlayer();
        if (gameMode(p) == GameMode.CREATIVE) return;
        if (permissionCheck(p, "TestServ.Bucket")) return;

        sendToPlayer(p, "&cYou cannot do this with this bucket!");
        event.setCancelled(true);
    }

    @EventHandler
    private void onBucketFill(PlayerBucketFillEvent event) {
        Player p = event.getPlayer();
        if (gameMode(p) == GameMode.CREATIVE) return;
        if (!permissionCheck(p, "TestServ.Bucket")) {
            sendToPlayer(p, "&cYou cannot fill buckets!");
            event.setCancelled(true);
        }
    }

    private GameMode gameMode(Player player) {
        return player.getGameMode();
    }
}
