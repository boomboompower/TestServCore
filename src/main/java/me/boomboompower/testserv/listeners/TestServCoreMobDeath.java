package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreMobDeath implements Listener {

    private TestServCore testServCore;

    public TestServCoreMobDeath(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEntityDeath(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        e.setDroppedExp(0);

        if (entity instanceof Slime) {
            entity.remove();
        } else if (entity instanceof EnderDragon) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendActionBar("&4&lThe &5&lEnderDragon&4&l was slain by &c&l" + e.getEntity().getKiller().getName() + "&4&l!", player);
            }
        }
    }
}
