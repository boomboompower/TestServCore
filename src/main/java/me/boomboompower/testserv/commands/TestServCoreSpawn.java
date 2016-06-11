package me.boomboompower.testserv.commands;

/*
* Made for TestServCore
* by boomboompower 25/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Sound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.scheduler.BukkitRunnable;

import static me.boomboompower.testserv.utils.Utils.*;
import static me.boomboompower.testserv.utils.Register.*;

public class TestServCoreSpawn implements CommandExecutor {

    private TestServCore testServCore;

    private String command = "spawn";

    public TestServCoreSpawn(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerCommands(command, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase(command)) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!permissionCheck(p, "testServ.spawn")) {
                    sendDenyMessage(p);
                } else {
                    sendToWorld(p, Bukkit.getWorlds().get(0));
                    sendToPlayer(p, "&9Warping to spawn...");

                    spawnSound(p, Sound.ENTITY_BLAZE_SHOOT, 10);
                }
            } else {
                sendToConsole("&cOnly a player can use this :P");
            }
        }
        return true;
    }

    private void spawnSound(final Player player, final Sound sound, int time) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), sound, 10, 2);
            }
        }.runTaskLater(testServCore, time);
    }
}
