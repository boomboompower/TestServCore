package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCorePlayerDeath implements Listener {

    private TestServCore testServCore;

    public TestServCorePlayerDeath(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        LivingEntity killer = player.getKiller();

        player.spigot().respawn();
        player.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 3));

        event.setDeathMessage("");
        event.setKeepInventory(true);

        if (killer != null) {
            Player k = (Player) killer;

            sendToPlayer(player, "&cYou were killed by &4" + killer.getName() + "&c!");
            sendToPlayer(k, "&aYou killed &2" + player.getName() + "!");
            k.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(5,1));
            event.setDeathMessage(translate("&9&lDeath: &b" + player.getName() + "&9&l was killed by &b" + killer.getName()));
        }
    }
}
