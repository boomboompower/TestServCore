package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.boomboompower.testserv.utils.Utils.translate;

public class TestServCoreJoin implements Listener {

    private TestServCore testServCore;

    private World world = Bukkit.getWorlds().get(0);
    private Double X = -167.0D;
    private Double Y = 123.0D;
    private Double Z = 334.0D;

    public TestServCoreJoin(TestServCore testServCore) {
        this.testServCore = testServCore;

        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(translate("&7[&a+&7] &f") + p.getName());

        p.setCollidable(false);

        p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
        p.removePotionEffect(PotionEffectType.GLOWING);
        p.removePotionEffect(PotionEffectType.SPEED);
        p.removePotionEffect(PotionEffectType.HEAL);

        p.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(Integer.MAX_VALUE, 4));
        p.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 3));
        p.addPotionEffect(PotionEffectType.HEAL.createEffect(1, 100));

        sendToSpawn(p, 5);
    }

    private void sendToSpawn(final Player player, int time) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(new Location(world, X, Y, Z), PlayerTeleportEvent.TeleportCause.COMMAND);
            }
        }.runTaskLater(testServCore, time);
    }
}
