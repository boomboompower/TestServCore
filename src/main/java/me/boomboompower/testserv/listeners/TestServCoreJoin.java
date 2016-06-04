package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.boomboompower.testserv.listeners.TestServCoreSigns.worldItem;
import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreJoin implements Listener {

    private TestServCore testServCore;

    public TestServCoreJoin(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        double lastPlayed = p.getLastPlayed() / 0.001;

        p.setMaxHealth(40D);
        p.setHealth(40D);
        p.setFoodLevel(20);

        p.getInventory().clear();
        p.getInventory().setItem(4, worldItem());

        e.setJoinMessage(translate("&7[&a+&7] &f") + p.getName());

        p.setCollidable(false);

        p.getActivePotionEffects().clear();

        p.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 3));

        // Wait 5 ticks so this runs after any other plugin.
        sendToSpawn(p, 5);

        sendHeaderAndFooter("&cWelcome to &4&lTESTSERV", "&bOwner &f: &9boomboompower", p);
        sendActionBar("&4&lWelcome to the server! &c&l" + p.getName() + "!", p);
        sendChatMessage("&4&lWelcome to the server! &c&l" + p.getName() + "!", p);
        sendChatMessage("&aYou last played: &3" + lastPlayed + "!");
    }

    private void sendToSpawn(final Player player, int time) {
        new BukkitRunnable() {
            @Override
            public void run() {
                sendToWorld(player,Bukkit.getWorlds().get(0));
            }
        }.runTaskLater(testServCore, time);
    }
}
