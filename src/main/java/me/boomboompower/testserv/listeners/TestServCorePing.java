package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import static me.boomboompower.testserv.utils.Utils.translate;

public class TestServCorePing implements Listener {

    private TestServCore testServCore;

    public TestServCorePing(TestServCore testServCore) {
        this.testServCore = testServCore;

        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    @EventHandler
    private void onServerPing(ServerListPingEvent e) {
        e.setMaxPlayers(0);
        e.setMotd(translate("&9Owner:&b boomboompower &7| &ePlugin testing!\n&f&lWelcome to TestServ!"));
    }
}
