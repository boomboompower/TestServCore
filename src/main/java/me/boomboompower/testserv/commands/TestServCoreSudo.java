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

import static me.boomboompower.testserv.utils.Register.registerCommands;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreSudo implements CommandExecutor {

    private TestServCore testServCore;

    private String command = "aSudo";
    private String command2 = "aChat";

    public TestServCoreSudo(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerCommands(command, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase(command) || cmd.getName().equalsIgnoreCase(command2)) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                sendToPlayer(p, "&7But nothing happened...");
            } else {
                if (args.length < 1) {
                    sendToConsole("&cThis command can force a player to do something!");
                } else {
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        if (online.getName().equalsIgnoreCase(args[0])) {
                            Player player = Bukkit.getPlayer(args[0]);
                            if (args.length < 1) {
                                sendToConsole("&cHey bud, you need to say what you want them to do!");
                                sendToConsole("&cE.G &4/sudo <Player> <Command> &c(Command can be chat to ;D)");
                            } else {
                                sendToConsole("&aSuccessfully made &2" + player.getName() + " do &2" + getArguments(args) + "&a!");
                                player.chat(getArguments(args));
                            }
                            break;
                        } else {
                            sendToConsole("&cCould not find &4" + args[0] + "&c! Are they online?");
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }
}
