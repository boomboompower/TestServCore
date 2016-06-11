package me.boomboompower.testserv.interactor;

/**
 * This code is part of *Interactor*
 * A separate plugin by boomboompower
 * <p>
 * http://github.com/boomboompower/Interactor/
 *
 * THIS CODE IS OUTDATED
 */

import me.boomboompower.testserv.TestServCore;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.boomboompower.testserv.utils.Register.registerEvents;

public class InteractorDoors implements Listener {

    private TestServCore testServCore;

    public InteractorDoors(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().isSneaking()) return;
        if (event.useInteractedBlock() == Event.Result.ALLOW && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block.getType() == Material.IRON_DOOR_BLOCK) {
                if (block.getData() >= 8) {
                    block = block.getRelative(BlockFace.DOWN);
                }
                if (block.getType() == Material.IRON_DOOR_BLOCK) {
                    if (block.getData() < 4) {
                        block.setData((byte) (block.getData() + 4));
                        block.getWorld().playEffect(block.getLocation(), Effect.DOOR_TOGGLE, 0);
                    } else {
                        block.setData((byte) (block.getData() - 4));
                        block.getWorld().playEffect(block.getLocation(), Effect.DOOR_TOGGLE, 0);
                    }
                    event.setUseItemInHand(Event.Result.DENY);
                }
            }
        }
    }
}
