package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 30/04/2016
*/

import me.boomboompower.testserv.TestServCore;
import me.boomboompower.testserv.utils.Renderer;

import org.bukkit.map.MapView;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.MapInitializeEvent;

import static me.boomboompower.testserv.utils.Register.*;

public class TestServCoreMaps implements Listener {

    private TestServCore testServCore;

    public TestServCoreMaps(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onMapInitialize(MapInitializeEvent e) {
        MapView mapView = e.getMap();
        mapView.setScale(MapView.Scale.FARTHEST);

        mapView.getRenderers().clear();

        mapView.addRenderer(new Renderer());
    }
}
