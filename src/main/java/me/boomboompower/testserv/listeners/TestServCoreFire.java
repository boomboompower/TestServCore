package me.boomboompower.testserv.listeners;

/*
* Made for TestServCore
* by boomboompower 06/05/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreFire implements Listener {

    private TestServCore testServCore;

    public TestServCoreFire(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void onBlockIgnite(BlockIgniteEvent event) {
        if (event.getIgnitingEntity() instanceof Player) {
            Player player = (Player) event.getIgnitingEntity();
            if (!permissionCheck(player, "TestServ.ignite")) {
                event.setCancelled(true);
                sendToPlayer(player, "&cYou do not have permission to ignite this block!");
            }
        }
    }
}
