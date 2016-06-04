package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;

import me.boomboompower.testserv.TestServCore;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCorePing implements Listener {

    private TestServCore testServCore;

    public TestServCorePing(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onServerPing(ServerListPingEvent e) {
        Random random = new Random();
        Boolean bool = random.nextBoolean();

        e.setMaxPlayers(random.nextInt(100000));

        protocolTest();
        if (bool) {
            e.setMotd(translate("&9Owner:&b boomboompower &7| &ePlugin testing!\n&f&lWelcome to TestServ!"));
        } else {
            e.setMotd(translate("&9Owner:&b boomboompower &7| &ePlugin testing!\n&c&lServer still in development stages!"));
        }
    }

    private void protocolTest() {
        final List<WrappedGameProfile> names = new ArrayList<WrappedGameProfile>();
        names.add(new WrappedGameProfile("1", translate("&e&m---------------")));
        names.add(new WrappedGameProfile("2", translate("&6&lWelcome to the server!")));
        names.add(new WrappedGameProfile("3", translate("&e&m---------------")));
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(testServCore, ListenerPriority.NORMAL,
                Arrays.asList(PacketType.Status.Server.OUT_SERVER_INFO), ListenerOptions.ASYNC) {
            @Override
            public void onPacketSending(PacketEvent event) {
                event.getPacket().getServerPings().read(0).setPlayers(names);
            }
        });
    }
}
