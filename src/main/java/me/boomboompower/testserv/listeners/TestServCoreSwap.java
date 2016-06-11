package me.boomboompower.testserv.listeners;

/*
* Made for TestServCore
* by boomboompower 26/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import java.util.ArrayList;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreSwap implements Listener {

    private TestServCore testServCore;
    private ArrayList<String> cooldown;
    private ArrayList<String> illegal;

    public TestServCoreSwap(TestServCore testServCore) {
        this.testServCore = testServCore;
        this.illegal = new ArrayList<String>();
        this.cooldown = new ArrayList<String>();

        registerEvents(this);
    }

    @EventHandler
    private void onPlayerSwapHandEvent(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.CREATIVE) return;
        if (e.getMainHandItem().getType() == Material.AIR) return;
        e.setCancelled(true);
        if (illegal.contains(p.getName())) {
            illegal.remove(p.getName());
            p.kickPlayer(translate(kickMessage()));
        } else {
            illegal.add(p.getName());
            p.sendMessage(translate("&cYou cannot swap items into your offhand. &4You have been warned."));
        }
    }

    @EventHandler
    private void onInventoryMoveItem(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (e.getWhoClicked().getGameMode() == GameMode.CREATIVE) return;
        if (item == null) return;
        if (item.getType() == Material.SHIELD) {
            if (item.getItemMeta().spigot().isUnbreakable()) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent e) {
        Material a = e.getPlayer().getInventory().getItemInMainHand().getType();
        PlayerInventory inventory = e.getPlayer().getInventory();
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if (cooldown.contains(e.getPlayer().getName())) return;
        if (inventory.getItemInOffHand().getType() == Material.SHIELD) return;
        if (e.getAction() == Action.LEFT_CLICK_BLOCK  || e.getAction() == Action.LEFT_CLICK_AIR) return;
        if (a == Material.DIAMOND_SWORD || a == Material.GOLD_SWORD || a == Material.IRON_SWORD || a == Material.STONE_SWORD || a == Material.WOOD_SWORD) {
            inventory.setItemInOffHand(shield());
            removeShield(inventory, 3);
            shieldCooldown(e.getPlayer(), 5);
        }
    }

    private void removeShield(final PlayerInventory inv, int time) {
        new BukkitRunnable() {
            @Override
            public void run() {
                inv.setItemInOffHand(new ItemStack(Material.AIR));
            }
        }.runTaskLater(testServCore, time * 20);
    }

    private void shieldCooldown(final Player player, int time) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (cooldown.contains(player.getName())) {
                    cooldown.remove(player.getName());
                }
            }
        }.runTaskLater(testServCore, time * 20);
    }

    private ItemStack shield() {
        ItemStack item = new ItemStack(Material.SHIELD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }
}
