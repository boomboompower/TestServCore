package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 26/04/2016
*/

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import me.boomboompower.testserv.TestServCore;

import static org.bukkit.Material.*;
import static org.bukkit.GameMode.*;
import static org.bukkit.event.block.Action.*;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreSwap implements Listener {

    private TestServCore testServCore;
    private ArrayList illegal;

    public TestServCoreSwap(TestServCore testServCore) {
        this.testServCore = testServCore;
        this.illegal = new ArrayList();

        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    @EventHandler
    private void onPlayerSwapHandEvent(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == CREATIVE) return;
        if (illegal.contains(p.getName())) {
            illegal.remove(p.getName());
            sendToConsole("&4" + p.getName() + "&c attempted to swap item hands too many times!");
            p.kickPlayer(translate("&4&lYou were warned.\n&9&oYour action has been logged and sent to the Admins!"));
        } else {
            illegal.add(p.getName());
            p.sendMessage(translate("&cYou cannot swap items into your offhand. &4You have been warned."));
        }
        e.setCancelled(true);
    }

    @EventHandler
    private void onInventoryMoveItem(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (e.getWhoClicked().getGameMode() == CREATIVE) return;
        if (item.getType() == SHIELD) {
            if (item.getItemMeta().spigot().isUnbreakable()) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent e) {
        Material a = e.getPlayer().getItemInHand().getType();
        PlayerInventory inventory = e.getPlayer().getInventory();
        if (e.getPlayer().getGameMode() == CREATIVE) return;
        if (inventory.getItemInOffHand().getType() == SHIELD) return;
        if (e.getAction() == LEFT_CLICK_BLOCK  || e.getAction() == LEFT_CLICK_AIR) return;
        if (a == DIAMOND_SWORD || a == GOLD_SWORD || a == IRON_SWORD || a == STONE_SWORD || a == WOOD_SWORD) {
            ItemStack item = new ItemStack(SHIELD, 1);
            ItemMeta meta = item.getItemMeta();
            meta.spigot().setUnbreakable(true);
            item.setItemMeta(meta);
            inventory.setItemInOffHand(item);
            removeShield(inventory, 2);
        }
    }

    private void removeShield(final PlayerInventory inv, int time) {
        new BukkitRunnable() {
            @Override
            public void run() {
                inv.setItemInOffHand(new ItemStack(AIR));
            }
        }.runTaskLater(testServCore, time * 20);
    }

}
