package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.boomboompower.testserv.utils.Utils.translate;

public class TestServCoreQuit implements Listener {

    private TestServCore testServCore;

    public TestServCoreQuit(TestServCore testServCore) {
        this.testServCore = testServCore;

        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(translate("&7[&c-&7] &f") + e.getPlayer().getName());
    }
}
