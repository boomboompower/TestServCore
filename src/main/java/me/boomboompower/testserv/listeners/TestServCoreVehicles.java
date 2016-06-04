package me.boomboompower.testserv.listeners;

/*
* Made for TestServCore
* by boomboompower 04/05/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.*;

import java.util.Random;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreVehicles implements Listener {

    // TODO: 08-May-16
    // THIS CLASS IS NOT BEING LINKED INTO, AS
    // WHEN IT IS USED THIS ERROR IS THROWN:
    // http://goo.gl/b6EN6T

    private TestServCore testServCore;

    public TestServCoreVehicles(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler
    private void onVehicleCollision(VehicleCollisionEvent event) {
        if (event.getVehicle().getPassenger() instanceof Player) {
            Player p = (Player) event.getVehicle().getPassenger();
            Random random = new Random();
            Boolean nextBoolean = random.nextBoolean();
            Integer integer = random.nextInt(9);
            if (nextBoolean) {
                sendToPlayer(p, "&cYou fell out of your vehicle!");
                if (++integer > 9) {
                    event.getVehicle().remove();
                    sendToPlayer(p, "&cYour vehicle broke!");
                }
            }
        }
    }

    @EventHandler
    private void onVehicleMove(VehicleMoveEvent event) {
        if (event.getTo().getY() <= lowestLocation()) {
            event.getVehicle().remove();
        }
    }

    @EventHandler
    private void onVehicleEnter(VehicleEnterEvent event) {
        if (event.getEntered() instanceof Player) {
            Player player = (Player) event.getEntered();
            if (!permissionCheck(player, "TestServ.Vehicle.Enter")) {
                event.setCancelled(true);
                sendToPlayer(player, vehicleDenyMessage("enter"));
            }
        }
    }

    @EventHandler
    private void onVehicleExit(VehicleExitEvent event) {
        if (event.getExited() instanceof Player) {
            Player player = (Player) event.getExited();
            if (!permissionCheck(player, "TestServ.Vehicle.Exit")) {
                event.setCancelled(true);
                sendToPlayer(player, vehicleDenyMessage("exit"));
            }
        }
    }

    @EventHandler
    private void onVehicleUpdate(VehicleUpdateEvent event) {
        // Once again, there are no manipulative methods such
        // As getting the reason the vehicle was changed
    }

    @EventHandler
    private void onVehicleCreate(VehicleCreateEvent event) {
        if (event.getVehicle() != null) {
            event.getVehicle().playEffect(EntityEffect.WITCH_MAGIC);
        }
    }

    @EventHandler
    private void onVehicleDestroy(VehicleDestroyEvent event) {
        if (event.getAttacker() instanceof Player) {
            Player player = (Player) event.getAttacker();
            if (!permissionCheck(player, "TestServ.Vehicle.Destroy")) {
                event.setCancelled(true);
                sendToPlayer(player, vehicleDenyMessage("destroy"));

            }
        } else {
            event.setCancelled(true);
        }
    }

    private String vehicleDenyMessage(String message) {
        message = translate("&cYou do not have permission to " + message + " this vehicle!");
        return message;
    }
}
