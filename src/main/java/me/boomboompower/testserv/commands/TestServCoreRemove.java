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
import org.bukkit.entity.*;

import static me.boomboompower.testserv.utils.Register.*;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreRemove implements CommandExecutor {

    private TestServCore testServCore;

    private String command = "remove";

    public TestServCoreRemove(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerCommands(command, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase(command)) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!permissionCheck(p, "testServ.Remove")) {
                    sendDenyMessage(p);
                } else {
                    for (Entity entity : p.getWorld().getEntities()) {
                        if (args.length < 1) {
                            sendToPlayer(p, "&cIncorrect usage! Please use &4/remove (entity)");
                            sendToPlayer(p, "&e(Note: &6This excludes players&e).");
                        } else if (entityTest("slime", args)) {
                            if (entity instanceof Slime) entity.remove();

                        } else if (entityTest("skeleton", args)) {
                            if (entity instanceof Skeleton) entity.remove();

                        } else if (entityTest("creeper", args)) {
                            if (entity instanceof Creeper) entity.remove();

                        } else if (entityTest("zombie", args)) {
                            if (entity instanceof Zombie) entity.remove();

                        } else if (entityTest("enderman", args)) {
                            if (entity instanceof Enderman) entity.remove();

                        } else if (entityTest("wither", args)) {
                            if (entity instanceof Wither) entity.remove();

                        } else if (entityTest("all", args)) {
                            if (!(entity instanceof Player)) entity.remove();

                        } else {
                            sendToPlayer(p, "&cUnknown entity &4(Or Not supported yet)&c.");
                        }
                    }
                }
            } else {
                for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
                    if (args.length < 1) {
                        sendToConsole("&cTry using &4/remove (entity)&c :D");
                    } else if (entityTest("slime", args)) {
                        if (entity instanceof Slime) entity.remove();

                    } else if (entityTest("skeleton", args)) {
                        if (entity instanceof Skeleton) entity.remove();

                    } else if (entityTest("creeper", args)) {
                        if (entity instanceof Creeper) entity.remove();

                    } else if (entityTest("zombie", args)) {
                        if (entity instanceof Zombie) entity.remove();

                    } else if (entityTest("enderman", args)) {
                        if (entity instanceof Enderman) entity.remove();

                    } else if (entityTest("all", args)) {
                        if (!(entity instanceof Player)) entity.remove();

                    } else if (entityTest("player", args)) {
                        if (entity instanceof Player) ((Player) entity).setHealth(0.0D);

                    } else {
                        sendToConsole("&cUnknown entity.");
                    }
                }
            }
        }
        return true;
    }

    private boolean entityTest(String entity, String[] args) {
        return args[0].equalsIgnoreCase(entity);
    }
}
