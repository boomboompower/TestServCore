package me.boomboompower.testserv.utils;

/*
* Made for TestServ Core
* by boomboompower 30/04/2016
*
* THIS CODE IS OUTDATED
*/

import org.bukkit.entity.Player;
import org.bukkit.map.*;

import static me.boomboompower.testserv.utils.Utils.translate;

public class Renderer extends MapRenderer {

    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        for (int x =  25; x < 50; x++) {
            for (int y = 25; y < 50; y++) {
                mapCanvas.setPixel(x, y, MapPalette.DARK_GRAY);
            }
        }

        MapCursorCollection cursors = new MapCursorCollection();
        cursors.addCursor(10, 10, (byte) 0, (byte) 6, true);
        mapCanvas.setCursors(cursors);

        mapCanvas.drawText(15, 15, MinecraftFont.Font, translate("&eHello &6" + player.getName()));
//        try {
//            mapCanvas.drawImage(0, 0, ImageIO.read(new URL("http://boomboompower.weebly.com/uploads/5/9/5/4/59542065/4519773_orig.png")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
