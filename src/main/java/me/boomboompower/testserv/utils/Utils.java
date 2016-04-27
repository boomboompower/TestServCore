package me.boomboompower.testserv.utils;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Utils {

    public static boolean permissionCheck(Player player, String permission) {
        permission = permission.toLowerCase().replace(" ", ".");

        if (player.hasPermission(permission)) {
            return true;
        } else {
            return false;
        }
    }

    public static String translate(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);

        return message;
    }

    public static void sendToPlayer(Player player, String message) {
        player.sendMessage(translate(message));
    }

    public static void duelSend(CommandSender sender, String message) {
        if (sender instanceof ConsoleCommandSender) {
            sendToConsole(message);
        } else if (sender instanceof Player) {
            sendToPlayer((Player) sender, message);
        } else {
            sendToConsole("&cAn unexpected error occured while using the &4duelSend(CommandSender, String) &cmethod!");
        }
    }

    public static String denyMessage() {
        return translate("&cI\'m sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    }

    public static String kickMessage() {
        return translate("&4&lYou were warned.");
    }

    public static void kickPlayer(Player p) {
        p.kickPlayer(kickMessage());
    }

    public static void kickPlayer(Player p, String message) {
        p.kickPlayer(translate(message));
    }

    public static String denyMessage(String reason, String warning) {
        String message = translate("&cYou do not have permission to " + reason + "! &4(" + warning + ")&c.");
        return message;
    }

    public static void sendDenyMessage(Player player) {
        player.sendMessage(denyMessage());
    }

    public static void sendToConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(translate(message));
    }
}
