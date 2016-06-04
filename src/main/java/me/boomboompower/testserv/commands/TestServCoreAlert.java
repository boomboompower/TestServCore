package me.boomboompower.testserv.commands;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.boomboompower.testserv.utils.Register.*;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreAlert implements CommandExecutor {

    private TestServCore testServCore;
    private String command = "alert";

    public TestServCoreAlert(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerCommands(command, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase(command)) {
            if (sender instanceof Player && !permissionCheck((Player) sender, "sca.alert")) {
                sendDenyMessage((Player) sender);
            } else {
                if (args.length < 1) {
                    duelSend(sender, "&cIncorrect usage. Use &4/alert <message>&c instead!");
                } else {
                    Bukkit.broadcastMessage(alertFormat() + translate(getArguments(args)));
                }
            }
        }
        return true;
    }

    private String getArguments(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(args[i]);
            builder.append(" ");
        }
        return builder.toString().trim();
    }
}
