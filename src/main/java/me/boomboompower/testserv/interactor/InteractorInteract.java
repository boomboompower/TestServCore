package me.boomboompower.testserv.interactor;

/**
 * This code is part of *Interactor*
 * A separate plugin by boomboompower
 * <p>
 * http://github.com/boomboompower/Interactor/
 */

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import static org.bukkit.entity.EntityType.*;
import static me.boomboompower.testserv.utils.Utils.*;

public class InteractorInteract implements Listener {

    private TestServCore testServCore;

    public InteractorInteract(TestServCore testServCore) {
        this.testServCore = testServCore;

        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        Entity e = event.getRightClicked();
        EntityType t = e.getType();

        if (!e.isCustomNameVisible()) {
            e.setCustomName(translate("&9" + p.getName() + "&b's entity!"));
            e.setCustomNameVisible(true);
        } else if (e.getCustomName().contains(p.getName()) && e instanceof LivingEntity) {
            if (t == SHEEP) {
                sendToPlayer(p, "&9Riding your&b sheep&9!");
                p.getPassenger().eject();
                e.setPassenger(p);
            } else {
                // Coming soon...
            }
        }
    }
}
