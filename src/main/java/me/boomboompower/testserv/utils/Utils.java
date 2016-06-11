package me.boomboompower.testserv.utils;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*
* THIS CODE IS OUTDATED!
*/

import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Utils {

    private Utils() {}

    public static boolean permissionCheck(Player player, String permission) {
        return player.hasPermission(permission.toLowerCase());
    }

    public static int lowestLocation() {
        return -35;
    }

    public static int highestLocation() {
        return 300;
    }

    public static Location spawnLocation(World world) {
        return new Location(world, -167.0D, 123.0D, 334.0D);
    }

    public static String getArguments(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builder.append(args[i]);
            builder.append(" ");
        }
        return builder.toString().trim();
    }

    public static String alertFormat() {
        return translate("&4&lALERT: &c&l");
    }

    public static String denyMessage() {
        return translate("&cI\'m sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    }

    public static String translate(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);

        return message;
    }

    public static String stripColor(String message) {
        message = ChatColor.stripColor(message);

        return message;
    }

    public static String kickMessage() {
        return translate("&4&lYou were warned.");
    }

    public static String denyMessage(String reason, String warning) {
        return translate("&cYou do not have permission to " + reason + "! &4(" + warning + ")&c.");
    }

    public static void openInventory(Player player, Inventory inventory) {
        player.openInventory(inventory);
    }

    public static void closeInventory(Player player) {
        player.closeInventory();
    }

    public static void sendToWorld(Player player, World world) {
        player.teleport(spawnLocation(world), PlayerTeleportEvent.TeleportCause.SPECTATE);
    }

    public static void sendSound(Player player, Sound sound, float pitch) {
        player.playSound(player.getLocation(), sound, (float) 10, pitch);
    }

    public static void kickPlayer(Player p) {
        p.kickPlayer(kickMessage());
    }

    public static void kickPlayer(Player p, String message) {
        p.kickPlayer(translate(message));
    }

    public static void sendDenyMessage(Player player) {
        player.sendMessage(denyMessage());
    }

    public static void sendToPlayer(Player player, String message) {
        player.sendMessage(translate(message));
    }

    public static void sendToConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(translate(message));
    }

    public static void duelSend(CommandSender sender, String message) {
        if (sender instanceof ConsoleCommandSender) {
            sendToConsole(message);
        } else if (sender instanceof Player) {
            sendToPlayer((Player) sender, message);
        } else {
            sendToConsole("&cAn unexpected error occured while using the &4duelSend(CommandSender, String)&c method!");
        }
    }

    public static void sendActionBar(String message, Player... player) {
        for (Player players : player) {
            ReflectionUtils.sendActionBar(players, message);
        }
    }

    public static void sendHeaderAndFooter(String header, String footer, Player... player) {
        for (Player players : player) {
            ReflectionUtils.sendHeaderAndFooter(players, header, footer);
        }
    }

    public static void sendChatMessage(String message, Player... player) {
        for (Player players : player) {
            ReflectionUtils.sendChatMessage(players, message);
        }
    }

    public static ItemStack getItemInMainHand(Player player) {
        return player.getInventory().getItemInMainHand();
    }

    public static ItemStack getItemInOffHand(Player player) {
        return player.getInventory().getItemInOffHand();
    }
}
