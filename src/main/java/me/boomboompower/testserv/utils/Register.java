package me.boomboompower.testserv.utils;

/*
* Made for TestServ Core
* by boomboompower 28/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class Register {

    // If your wondering why we didn't just
    // Put this in the Utils class, it's
    // Because we wanted the Utils class to
    // Be usable from anywhere without needing
    // The restrictions for only TestServCore

    private static TestServCore testServCore;

    public Register(TestServCore testServCore) {
        this.testServCore = testServCore;

        new ReflectionUtils(testServCore);
    }

    public static void registerEvents(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, testServCore);
    }

    public static void registerCommands(String command, CommandExecutor commandExecutor) {
        testServCore.getCommand(command).setExecutor(commandExecutor);
    }
}
