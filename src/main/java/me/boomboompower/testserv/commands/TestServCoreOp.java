package me.boomboompower.testserv.commands;

/*
* Made for TestServCore
* by boomboompower 25/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

import static me.boomboompower.testserv.utils.Utils.*;
import static me.boomboompower.testserv.utils.Register.*;

public class TestServCoreOp implements CommandExecutor {

    private TestServCore testServCore;

    private String command = "aOp";
    private String command2 = "aOpMe";

    public TestServCoreOp(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerCommands(command, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase(command) || cmd.getName().equalsIgnoreCase(command2)) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                sendToPlayer(p, "&4&lSERVER: &cYou were de-opped!");
                p.setOp(false);
            } else {
                if (args.length < 1) {
                    sendToConsole("&cThis is just a fake command! You can still do &4/op <PLAYER>");
                } else {
                    Player player = Bukkit.getPlayer(args[0]);
                    player.setOp(true);
                    sendToPlayer(player, "&4&lSERVER: &cYou are now op!");
                }
            }
        }
        return true;
    }
}
