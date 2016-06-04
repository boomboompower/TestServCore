package me.boomboompower.testserv.commands;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.boomboompower.testserv.utils.Utils.*;
import static me.boomboompower.testserv.utils.Register.*;

public class TestServCoreKick implements CommandExecutor {

    private TestServCore testServCore;

    private String command = "aKick";

    public TestServCoreKick(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerCommands(command, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase(command)) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!permissionCheck(p, "testServ.Kick")) {
                    sendDenyMessage(p);
                } else {
                    if (args.length < 1) {
                        sendToPlayer(p, "&cPlease add some arguments, e.g &4/kick <player> <reason>");
                    } else if (args.length == 1) {
                        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
                        if (player.isOnline()) {
                            player.getPlayer().kickPlayer(translate("&cKicked by &4" + p.getName()));
                        } else {
                            sendToPlayer(p, notOnline(args));
                        }
                    } else {
                        Player player = Bukkit.getPlayer(args[0]);
                        if (player.isOnline()) {
                            player.getPlayer().kickPlayer(translate("&c" + getArguments(args)));
                        } else {
                            sendToPlayer(p, notOnline(args));
                        }
                    }
                }
            } else {
                if (args.length < 1) {
                    sendToConsole("&cPlease add some arguments, e.g &4/kick <player> <reason>");
                } else if (args.length == 1) {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
                    if (player.isOnline()) {
                        player.getPlayer().kickPlayer(translate("&bKicked by &5&lCONSOLE&b!"));
                    } else {
                        sendToConsole(notOnline(args));
                    }
                } else {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (player.isOnline()) {
                        player.getPlayer().kickPlayer(translate("&c" + getArguments(args)));
                    } else {
                        sendToConsole(notOnline(args));
                    }
                }
            }
        }
        return true;
    }

    private String notOnline(String[] args) {
        return "&cThe player &4" + args[0] + "&c is not online!";
    }
}
