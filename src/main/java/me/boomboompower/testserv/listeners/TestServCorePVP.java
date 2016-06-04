package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.NumberConversions;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCorePVP implements Listener {

    private TestServCore testServCore;

    public TestServCorePVP(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Entity damager = e.getDamager();

        if (entity instanceof Player && damager instanceof Player) {
            Player aDamage = (Player) entity;
            Player aDamager = (Player) damager;

            aDamage.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(3, 2));
            aDamager.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(3, 1));

            sendToPlayer(aDamage, "&cYou were damaged by &4" + aDamager.getName() + "&c for &4" + NumberConversions.ceil(e.getDamage()) + "&c!");
            sendToPlayer(aDamager, "&aYou damaged &3" + aDamage.getName() + "&a for &3" + NumberConversions.ceil(e.getDamage()) + "a!");
        }
    }


}
