package me.boomboompower.testserv.customentities;

/*
* Made for TestServCore
* by boomboompower 07/05/2016
*
* THIS CODE IS OUTDATED
*/

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.UUID;

public class CustomPlayer {

    private Player player;

    @Deprecated
    private ItemStack itemInHand;

    @Deprecated
    private boolean isOnGround;

    private ItemStack itemInMainHand;
    private ItemStack itemInOffHand;
    private ItemStack helmet;
    private ItemStack chestPlate;
    private ItemStack leggings;
    private ItemStack boots;

    private boolean hasPlayedBefore;
    private boolean canPickupItems;
    private boolean isBlocking;
    private boolean isGlowing;
    private boolean isFlying;
    private boolean isOnline;

    private Double maxHealth;
    private Double health;

    private String customName;
    private String name;

    private GameMode gameMode;

    private PlayerInventory inventory;

    private InventoryView inventoryView;

    private UUID uuid;

    @Deprecated
    public CustomPlayer() {
        this.player = Bukkit.getOfflinePlayer("boomboompower").getPlayer();
        this.name = "";
        this.uuid = player.getUniqueId();
        this.customName = "";
        this.hasPlayedBefore = false;
        this.canPickupItems = false;
        this.isBlocking = false;
        this.isFlying = false;
        this.isGlowing = false;
        this.isOnline = false;
        this.inventory = player.getInventory();
        this.inventoryView = player.getOpenInventory();
        this.maxHealth = 20.0D;
        this.health = 20.0D;
        this.gameMode = GameMode.SURVIVAL;

        this.itemInMainHand = new ItemStack(Material.AIR);
        this.itemInOffHand = new ItemStack(Material.AIR);
        this.helmet = new ItemStack(Material.AIR);
        this.chestPlate = new ItemStack(Material.AIR);
        this.leggings = new ItemStack(Material.AIR);
        this.boots = new ItemStack(Material.AIR);

        this.itemInHand = new ItemStack(Material.AIR);
        this.isOnGround = true;
    }

    public CustomPlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.customName = ""; // Done to stop a NPE being thrown.
        this.hasPlayedBefore = player.hasPlayedBefore();
        this.canPickupItems = player.getCanPickupItems();
        this.isBlocking = player.isBlocking();
        this.isFlying = player.isFlying();
        this.isGlowing = player.isGlowing();
        this.isOnline = player.isOnline();
        this.inventory = player.getInventory();
        this.inventoryView = player.getOpenInventory();
        this.maxHealth = player.getMaxHealth();
        this.health = player.getHealth();
        this.gameMode = player.getGameMode();

        this.itemInMainHand = inventory.getItemInMainHand();
        this.itemInOffHand = inventory.getItemInOffHand();
        this.helmet = inventory.getHelmet();
        this.chestPlate = inventory.getChestplate();
        this.leggings = inventory.getLeggings();
        this.boots = inventory.getBoots();

        this.itemInHand = player.getItemInHand();
        this.isOnGround = player.isOnGround();
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    private void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public InventoryView getOpenInventory() {
        return inventoryView;
    }

    public void setOpenInventory(InventoryView inventoryView) {
        this.inventoryView = inventoryView;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    public void setInventory(PlayerInventory inventory, int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }

    public boolean canPickupItems() {
        return canPickupItems;
    }

    public void setCanPickupItems(boolean canPickupItems) {
        this.canPickupItems = canPickupItems;
    }

    @Deprecated
    public ItemStack getItemInHand() {
        return itemInHand;
    }

    @Deprecated
    public void setItemInHand(ItemStack itemInHand) {
        this.itemInHand = itemInHand;
    }

    @Deprecated
    public boolean isOnGround() {
        return isOnGround;
    }

    @Deprecated
    public void setIsOnGround(boolean isOnGround) {
        this.isOnGround = isOnGround;
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public ItemStack getItemInMainHand() {
        return this.itemInMainHand;
    }

    public void setItemInMainHand(ItemStack itemInMainHand) {
        this.itemInMainHand = itemInMainHand;
    }

    public ItemStack getItemInOffHand() {
        return this.itemInOffHand;
    }

    public void setItemInOffHand(ItemStack itemInOffHand) {
        this.itemInOffHand = itemInOffHand;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setFlying(boolean flying) {
        this.isFlying = flying;
    }

    public boolean isGlowing() {
        return isGlowing;
    }

    public void setGlowing(boolean glowing) {
        this.isGlowing = glowing;
    }

    public Collection<PotionEffect> getPotionEffects() {
        return player.getActivePotionEffects();
    }

    public void addEffects(int effectStrength, PotionEffectType... effects) {
       for (PotionEffectType potionEffectType : effects) {
           player.addPotionEffect(potionEffectType.createEffect(Integer.MAX_VALUE, effectStrength));
       }
    }

    public void addEffects(int effectStrength, PotionEffect potionEffect) {
        player.addPotionEffect(potionEffect);
    }

    public void removeAllEffects() {
        player.getActivePotionEffects().clear();
    }

    public void removeEffects(PotionEffectType... potionEffect) {
        for (PotionEffectType potionEffectType : potionEffect) {
            if (player.hasPotionEffect(potionEffectType)) {
                player.removePotionEffect(potionEffectType);
            }
        }
    }

    public boolean hasPlayedBefore() {
        return this.hasPlayedBefore;
    }

    public void setHasPlayedBefore(boolean hasPlayedBefore) {
        this.hasPlayedBefore = hasPlayedBefore;
    }

    public void updatePlayer(Player player) {
        player.setAllowFlight(isFlying());
        player.setDisplayName(getCustomName());
        player.setFlying(isFlying());
        player.setGlowing(isGlowing());
        player.setGameMode(getGameMode());

        player.getInventory().setItemInMainHand(getItemInMainHand());
        player.getInventory().setItemInOffHand(getItemInOffHand());

        player.updateInventory();
        player.setCanPickupItems(canPickupItems());

        player.getActivePotionEffects().clear();
        for (PotionEffect effects : getPotionEffects()) {
            player.addPotionEffect(effects);
        }
    }

    @Deprecated
    public void INVALID_updatePlayer() {
        player.setItemInHand(itemInHand);
    }
}
