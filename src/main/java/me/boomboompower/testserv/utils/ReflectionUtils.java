package me.boomboompower.testserv.utils;

/*
* Made for TestServ Core
* by boomboompower 30/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;
import me.boomboompower.testserv.events.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("ALL") // Don't want warnings in this class.
public class ReflectionUtils {

    private TestServCore testServCore;

    public ReflectionUtils(TestServCore testServCore) {
        this.testServCore = testServCore;
    }

    public static void sendHeaderAndFooter(Player player, String header, String footer) {
        try {
            HeaderFooterSendEvent event = new HeaderFooterSendEvent(player, header, footer);
            Bukkit.getPluginManager().callEvent(event);

            header = Utils.translate(event.getHeader());
            footer = Utils.translate(event.getFooter());

            Object headerJson = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}");
            Object footerJson = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}");
            Object packet = getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(getNMSClass("IChatBaseComponent")).newInstance(headerJson);

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footerJson);

            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);

            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (IllegalArgumentException ex) {
        } catch (NoSuchMethodException ex) {
        } catch (SecurityException ex) {
        } catch (IllegalAccessException ex) {
        } catch (InvocationTargetException ex) {
        } catch (InstantiationException ex) {
        } catch (NoSuchFieldException ex) {
        } catch (NullPointerException ex) {}
    }

    public static void sendChatMessage(Player player, String message) {
        try {
            ChatMessageSendEvent event = new ChatMessageSendEvent(player, message);
            Bukkit.getPluginManager().callEvent(event);

            message = Utils.translate(event.getMessage());

            Object chatJSON = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}");
            Object packet = getNMSClass("PacketPlayOutChat").getConstructor(getNMSClass("IChatBaseComponent"), byte.class).newInstance(chatJSON, (byte) 0);

            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);

            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {}
    }

    public static void sendActionBar(Player player, String message) {
        try {
            ActionBarSendEvent event = new ActionBarSendEvent(player, message);
            Bukkit.getPluginManager().callEvent(event);

            message = Utils.translate(event.getMessage());

            Object actionBarJSON = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}");
            Object packet = getNMSClass("PacketPlayOutChat").getConstructor(getNMSClass("IChatBaseComponent"), byte.class).newInstance(actionBarJSON, (byte) 2);

            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);

            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (IllegalArgumentException ex) {
        } catch (NoSuchMethodException ex) {
        } catch (SecurityException ex) {
        } catch (IllegalAccessException ex) {
        } catch (InvocationTargetException ex) {
        } catch (InstantiationException ex) {
        } catch (NoSuchFieldException ex) {
        } catch (NullPointerException ex) {}
    }

    public static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + getVersion() + "." + name);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }
}
