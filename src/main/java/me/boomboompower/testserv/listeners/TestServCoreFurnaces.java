package me.boomboompower.testserv.listeners;

/*
* Made for TestServCore
* by boomboompower 06/05/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreFurnaces implements Listener {

    private TestServCore testServCore;

    public TestServCoreFurnaces(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onFurnaceBurn(FurnaceBurnEvent event) {
        switch (event.getFuel().getType()) {
            case LAVA_BUCKET:
                event.setBurning(false);
                event.getFuel().setType(Material.AIR);
                break;
            case DIAMOND_BLOCK:
                event.setBurning(true);
                event.setBurnTime(20000);
            case DIAMOND_SWORD:
                event.setBurning(true);
                event.setBurnTime(600 * 20);
            default:
                break;
        }
    }

    @EventHandler
    private void onFurnaceSmelt(FurnaceSmeltEvent event) {
        switch (event.getSource().getType()) {
            case DIAMOND_SWORD:
                event.setResult(opSword());
                event.getSource().setType(Material.AIR);
            default:
                break;
        }
    }

    private ItemStack opSword() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = sword.getItemMeta();

        meta.spigot().setUnbreakable(true);
        meta.setLore(addToLore(new ArrayList<String>(), "&bThe Most Op Sword", "&bYou'll Ever Find!"));
        meta.setDisplayName(translate("&a&k!!!&r &5Op Sword&r &a&k!!!"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);

        sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
        sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        sword.addUnsafeEnchantment(Enchantment.MENDING, 10);
        sword.setItemMeta(meta);

        return sword;
    }

    private List<String> addToLore(List<String> firstLore, String... lore) {
        for (String lor : lore) {
            firstLore.add(translate(lor));
        }
        return firstLore;
    }
}
