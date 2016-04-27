package me.boomboompower.testserv.commands;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

import me.boomboompower.testserv.TestServCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreHelp implements CommandExecutor {

    private TestServCore testServCore;
    private String command = "aHelp";

    public TestServCoreHelp(TestServCore testServCore) {
        this.testServCore = testServCore;

        testServCore.getCommand(command).setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase(command)) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!permissionCheck(p, "testServ.Help")) {
                    sendDenyMessage(p);
                } else {
                    if (args.length < 1) {
                        defaultHelpPage(p);
                    } else if (args[0].equalsIgnoreCase("1")) {
                        defaultHelpPage(p);
                    } else if (args[0].equalsIgnoreCase("2")) {
                        helpPageHeader(p, "2/3");
                        sendToPlayer(p, "");
                    } else if (args[0].equalsIgnoreCase("3")) {
                        helpPageHeader(p, "3/3");
                        sendToPlayer(p, "");
                    } else if (args[0].equalsIgnoreCase("interactor")) {
                        helpPageHeader(p, "Interactor");
                        helpPageCommand(p, "interactor eject", "Eject yourself from an entity.");
                        sendToPlayer(p, "");
                    } else {
                        defaultHelpPage(p);
                    }
                }
            } else {
                if (args.length < 1) {
                    consoleHelpPage(sender);
                } else if (args[0].equalsIgnoreCase("interactor")) {
                    helpPageHeader(sender, "&5Server");
                    helpPageCommand(sender, "eject", "Eject yourself from the entity your riding!");
                    helpPageFooter(sender, "Try &c/help <command> &4for more info!");
                }

            }
        }
        return true;
    }

    private void helpPageHeader(CommandSender sender, String page) {
        duelSend(sender, "&e&m------------&4&l Server Help &c(" + page + "&c) &e&m------------");
    }

    private void helpPageFooter(CommandSender sender, String message) {
        duelSend(sender, "&e&m------&4&l " + message + " &e&m------");
    }

    private void helpPageCommand(CommandSender sender, String command, String info) {
        duelSend(sender, "&e/" + command + " &7- &6" + info);
    }

    private void defaultHelpPage(Player p) {
        helpPageHeader(p, "1/3");
        helpPageCommand(p, "help", "Displays this help page!");
        helpPageCommand(p, "ban", "Ban a player from the server!");
        helpPageCommand(p, "interactor", "Base command for interactor!");
        helpPageFooter(p, "Try &c/help <command>&4&l for more info!");
    }

    private void consoleHelpPage(CommandSender sender) {
        helpPageHeader(sender, "&5Server");
        helpPageCommand(sender, "help", "Displays this help page!");
        helpPageCommand(sender, "ban", "Ban a player from the server!");
        helpPageCommand(sender, "interactor", "Base command for interactor!");
        helpPageFooter(sender, "Try &c/help <command>&4&l for more info!");
    }
}
