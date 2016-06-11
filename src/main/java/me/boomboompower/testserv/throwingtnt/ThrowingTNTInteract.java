package me.boomboompower.testserv.throwingtnt;

/**
 * This code is part of *ThrowingTNT*
 * A separate plugin by boomboompower
 * <p>
 * http://github.com/boomboompower/ThrowingTNT/
 * 
 * THIS CODE IS OUTDATED
 */

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class ThrowingTNTInteract implements Listener {

    private TestServCore testServCore;
    private ArrayList<String> cooldown;

    public ThrowingTNTInteract(TestServCore testServCore) {
        this.testServCore = testServCore;
        this.cooldown = new ArrayList<String>();

        registerEvents(this);
    }

    @EventHandler
    private void onInteract(PlayerAnimationEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();

        ItemStack itemInHand = getItemInMainHand(p);
        if (cooldown.contains(p.getName())) return;
        if (itemInHand.getType() == Material.TNT) {
            p.playSound(loc, Sound.UI_BUTTON_CLICK, 10, 2);

            TNTPrimed tnt = p.getWorld().spawn(loc, TNTPrimed.class);

            tnt.setVelocity(loc.getDirection().normalize().multiply(1.5));

            if (itemInHand.getAmount() > 0) {
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            } else {
                itemInHand.setType(Material.AIR);
            }
            tntCooldown(p, 3);
        }
    }

    private void tntCooldown(final Player player, int time) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (cooldown.contains(player.getName())) {
                    cooldown.remove(player.getName());
                }
            }
        }.runTaskLater(testServCore, time * 20);
    }
}
