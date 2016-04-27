package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 26/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCorePVP implements Listener {

    private TestServCore testServCore;

    public TestServCorePVP(TestServCore testServCore) {
        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    @EventHandler
    private void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Entity damager = e.getDamager();

        if (entity instanceof Player && damager instanceof Player) {
            Player aDamage = (Player) entity;
            Player aDamager = (Player) damager;

            sendToPlayer(aDamage, "&cYou were damaged by &4" + aDamager.getName() + "&c for &4" + e.getDamage() + "&c!");
            sendToPlayer(aDamager, "&aYou damaged &3" + aDamage.getName() + "&a for &3" + e.getDamage() + "7a!");
        }
    }
}
