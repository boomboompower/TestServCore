package me.boomboompower.testserv.commands;

/*
* Made for TestServCore
* by boomboompower 25/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.boomboompower.testserv.utils.Register.*;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreBan implements CommandExecutor {

    private TestServCore testServCore;

    private final String URL = "http://boomboompower.weebly.com/unban.html";
    private String command = "aBan";

    public TestServCoreBan(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerCommands(command, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase(command)) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!permissionCheck(p, "testServ.Ban")) {
                    sendDenyMessage(p);
                } else {
                    if (args.length < 1) {
                        sendToPlayer(p, "&cIncorrect arguments! Use &4/ban {PLAYER}&c instead!");
                    } else  {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (args[0].equalsIgnoreCase(all.getName())) {
                                Player pl = all.getPlayer();

                                p.setBanned(true);
                                p.kickPlayer(translate("&4Banned by &c&l" + p.getName() + "&4. Submit an appeal at &b" + URL + " &4!"));
                            } else {
                                sendToPlayer(p, "&cCould not find &4" + args[0] + "&c! Are they online?");
                            }
                        }
                    }
                }
            } else {
                if (args.length < 1) {
                    sendToConsole("&cIncorrect arguments, use &4/ban {PLAYER}");
                } else {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (args[0].equalsIgnoreCase(all.getName())) {
                            Player p = all.getPlayer();

                            p.setBanned(true);
                            p.kickPlayer(translate("&4&lBanned by console!"));
                        } else {
                            sendToConsole("&cCouldn\'t find &4" + args[0] + "&c! Are they online?");
                        }
                    }
                }
            }
        }
        return true;
    }
}
