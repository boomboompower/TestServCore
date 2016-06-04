package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 28/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCorePlayerMove implements Listener {

    private TestServCore testServCore;

    public TestServCorePlayerMove(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        Double y = p.getLocation().getY();

        if (p.getGameMode() == GameMode.CREATIVE) return;
        if (y <= lowestLocation() || y >= highestLocation()) {
            sendToWorld(p, Bukkit.getWorlds().get(0));
            p.setFallDistance(0.0F);
        }
    }

    @EventHandler
    private void onBedEnter(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(true);
    }

    @EventHandler
    private void onBedLeave(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;
        Block b = event.getBed();
        b.breakNaturally();
    }

    @EventHandler
    private void onFoodChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        event.setFoodLevel(20);
    }
}
