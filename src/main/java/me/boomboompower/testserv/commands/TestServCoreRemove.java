package me.boomboompower.testserv.commands;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

/**
* Please note, this is in BETA stages, and any
* help given would be greatly appreciated 
/*

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreRemove implements CommandExecutor {

    private TestServCore testServCore;

    private String command = "remove";

    public TestServCoreRemove(TestServCore testServCore) {
        this.testServCore = testServCore;

        testServCore.getCommand(command).setExecutor(this);
    }

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

                        } else if (entityTest("zombie", args)) {
                            if (entity instanceof Zombie) entity.remove();
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

                    } else if (entityTest("", args)) {
                        if (entity instanceof Zombie) entity.remove();

                    } else {
                        sendToConsole("&cUnknown entity.");
                    }
                }
            }
        }
        return true;
    }

    private boolean entityTest(String entity, String[] args) {
        if (args[0].equalsIgnoreCase(entity)) {
            return true;
        } else {
            return false;
        }
    }
}
