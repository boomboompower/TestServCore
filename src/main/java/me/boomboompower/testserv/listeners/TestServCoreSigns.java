package me.boomboompower.testserv.listeners;

/*
* Made for TestServCore
* by boomboompower 02/05/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreSigns implements Listener {

    private TestServCore testServCore;
    private ArrayList<String> travelling;
    private Inventory inventory = Bukkit.createInventory(null, 27, translate("&5&lWarp Menu"));

    public TestServCoreSigns(TestServCore testServCore) {
        this.testServCore = testServCore;
        this.travelling = new ArrayList<String>();

        registerEvents(this);
    }

    @EventHandler
    private void onSignUpdate(SignChangeEvent e) {
        Player p = e.getPlayer();
        Sign sign = (Sign) e.getBlock().getState();

        if (e.getLine(0).contains("Test") || e.getLine(0).contains("Realm")) {
            if (!permissionCheck(p, "testServ.Sign")) {
                sendToPlayer(p, "&cUnfortunaly, you cannot color your signs!");
                sign.setLine(0, translate("&4No Permission"));
                sign.setLine(1, translate("&f") + p.getName());
            } else {
                sendToPlayer(p, "&aServer sign created!");
                sign.setLine(0, translate("&7[&4&lRealm&7]"));
                sign.setLine(1, translate("&cPress me to"));
                sign.setLine(2, translate("&cSet your"));
                sign.setLine(3, translate("&cDimension!"));
            }
            sign.update();
        }
    }

    @EventHandler
    private void onSignInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = event.getClickedBlock();
            if (b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST) {
                Sign s = (Sign) b.getState();
                if (s.getLine(0).equalsIgnoreCase("Test") || s.getLine(0).equalsIgnoreCase("Realm")) {
                    setInventoryContents();
                    openInventory(p, inventory);
                }
            }
        }
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();

        if (event.getHand() != EquipmentSlot.HAND) return;
        if (player.getInventory().getItemInMainHand() == worldItem()) {
            setInventoryContents();
            openInventory(player, inventory);
        }
    }

    @EventHandler
    private void onInventoryInteract(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player p = (Player) event.getWhoClicked();
            if (event.getInventory().getName().equals(inventory.getName())) {
                event.setCancelled(true);
                Material t = event.getCurrentItem().getType();
                if (isTravelling(p)) {
                    sendToPlayer(p, "&cYou are already traveling!");
                    a(p);
                    return;
                }
                if (t == Material.ENDER_STONE) {
                    setTravelling(p, true);
                    p.addPotionEffect(PotionEffectType.LEVITATION.createEffect(3, 5));
                    waitForEnd(p, 2);
                    sendToPlayer(p, "&7You begin to feel lighter... A force summons you to the skies!");
                    a(p);
                    setTravelling(p, false);
                } else if (t == Material.GRASS) {
                    setTravelling(p, true);
                    sendToWorld(p, Bukkit.getWorlds().get(0));
                    sendToPlayer(p, "&2A mystical force takes you to the overworld...");
                    a(p);
                    setTravelling(p, false);
                } else if (t == Material.OBSIDIAN) {
                    setTravelling(p, true);
                    sendToWorld(p, Bukkit.getWorlds().get(1));
                    sendToPlayer(p, "&cThe devil summons you to the nether...");
                    a(p);
                    setTravelling(p, false);
                }
            }
        }
    }

    private ItemStack netherStack() {
        ItemStack netherStack = new ItemStack(Material.OBSIDIAN);
        ItemMeta netherMeta = netherStack.getItemMeta();

        netherMeta.setDisplayName(translate("&cThe Nether"));
        netherStack.setItemMeta(netherMeta);

        return netherStack;
    }

    private ItemStack overWorldStack() {
        ItemStack overWorldStack = new ItemStack(Material.GRASS);
        ItemMeta overWorldMeta = overWorldStack.getItemMeta();

        overWorldMeta.setDisplayName(translate("&aThe Overworld"));
        netherStack().setItemMeta(overWorldMeta);

        return overWorldStack;
    }

    private ItemStack endStack() {
        ItemStack endStack = new ItemStack(Material.ENDER_STONE);
        ItemMeta endMeta = endStack.getItemMeta();

        endMeta.setDisplayName(translate("&6The End"));
        endStack.setItemMeta(endMeta);

        return endStack;
    }

    private void setInventoryContents() {
        inventory.setItem(9, netherStack());
        inventory.setItem(13, overWorldStack());
        inventory.setItem(17, endStack());
    }

    public static ItemStack worldItem() {
        ItemStack worldItem = new ItemStack(Material.WATCH, 1);
        ItemMeta worldMeta = worldItem.getItemMeta();

        worldMeta.setDisplayName(translate("&aWorld Selector"));
        worldItem.setItemMeta(worldMeta);

        return worldItem;
    }

    private void a(Player a) {
        a.closeInventory();
    }

    private void waitForEnd(final Player p, int time) {
        new BukkitRunnable() {
            @Override
            public void run() {
                sendToWorld(p, Bukkit.getWorlds().get(2));
                p.removePotionEffect(PotionEffectType.LEVITATION);
            }
        }.runTaskLater(testServCore, time * 20);
    }

    private boolean isTravelling(Player player) {
        return travelling.contains(player.getName());
    }

    private void setTravelling(Player player, boolean travelling) {
        if (travelling) {
            this.travelling.add(player.getName());
        } else {
            this.travelling.remove(player.getName());
        }
    }
}
