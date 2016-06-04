package me.boomboompower.testserv.interactor;

/**
 * This code is part of *Interactor*
 * A separate plugin by boomboompower
 * <p>
 * http://github.com/boomboompower/Interactor/
 */

import me.boomboompower.testserv.TestServCore;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import static me.boomboompower.testserv.utils.Register.*;
import static me.boomboompower.testserv.utils.Utils.*;
import static org.bukkit.entity.EntityType.*;

public class InteractorInteract implements Listener {

    private TestServCore testServCore;

    public InteractorInteract(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        Entity e = event.getRightClicked();
        EntityType t = e.getType();

        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        if (!e.isCustomNameVisible()) {
            e.setCustomName(translate("&9" + p.getName() + "&b's entity!"));
            e.setCustomNameVisible(true);
        } else if (e.getCustomName().contains(p.getName()) && e instanceof LivingEntity) {
            p.eject();
            if (t == PLAYER) {
                sendToPlayer(p, "&cCannot ride players!");
            } else if (t == GUARDIAN) {
                sendToPlayer(p, "&cYou cannot ride Guardians!");
            } else if (t == ENDER_DRAGON) {
                sendToPlayer(p, "&cYou cannot ride the &5EnderDragon&c!");
            } else if (t == WITHER) {
                sendToPlayer(p, "&cYou cannot ride the &5Wither&c!");
            } else {
                sendToPlayer(p, "&aRiding the " + t.getName());
                e.setPassenger(p);
            }
        }
    }
}
