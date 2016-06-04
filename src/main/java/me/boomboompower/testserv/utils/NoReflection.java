package me.boomboompower.testserv.utils;

/*
* Made for TestServCore
* by boomboompower 13/05/2016
*/

import com.mojang.authlib.GameProfile;

import me.boomboompower.testserv.TestServCore;

import net.minecraft.server.v1_9_R2.*;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.CraftServer;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class NoReflection implements Listener {

    private WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
    private TestServCore testServCore;
    private EntityPlayer npc;

    public NoReflection(TestServCore testServCore) {
        this.testServCore = testServCore;
        this.npc = new EntityPlayer(((CraftServer) Bukkit.getServer()).getServer(), nmsWorld, new GameProfile(UUID.randomUUID(), "Ghost"), new PlayerInteractManager(nmsWorld));

        registerEvents(this);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        npc.teleportTo(spawnLocation(Bukkit.getWorlds().get(0)), false);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent event) {
        double x = event.getFrom().getX();
        double y = event.getFrom().getY();
        double z = event.getFrom().getZ();

        npc.move(z, y, z);
    }

//    private org.bukkit.inventory.ItemStack dragonEgg() {
//        org.bukkit.inventory.ItemStack egg = new org.bukkit.inventory.ItemStack(Material.DRAGON_EGG);
//        org.bukkit.inventory.meta.ItemMeta meta = egg.getItemMeta();
//
//        meta.setDisplayName(translate("&9&lDragon Egg"));
//
//        egg.setItemMeta(meta);
//        return egg;
//    }
//
//    private org.bukkit.inventory.ItemStack sword() {
//        org.bukkit.inventory.ItemStack sword = new org.bukkit.inventory.ItemStack(Material.DIAMOND_SWORD);
//        org.bukkit.inventory.meta.ItemMeta meta = sword.getItemMeta();
//
//        meta.setDisplayName(translate("&9&lSword of the slayer..."));
//
//        sword.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
//        sword.setItemMeta(meta);
//
//        return sword;
//    }
}
