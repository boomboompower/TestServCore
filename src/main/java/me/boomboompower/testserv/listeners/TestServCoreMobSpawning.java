package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*/

import me.boomboompower.testserv.TestServCore;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

import static me.boomboompower.testserv.utils.Register.registerEvents;
import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreMobSpawning implements Listener {

    private TestServCore testServCore;

    public TestServCoreMobSpawning(TestServCore testServCore) {
        this.testServCore = testServCore;

        registerEvents(this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEntitySpawn(EntitySpawnEvent e) {
        Entity entity = e.getEntity();

        if (entity instanceof Creeper) {
            Creeper creeper = (Creeper) entity;
            creeper.setPowered(false);

        } else if (entity instanceof Zombie) {
            Zombie zombie = (Zombie) entity;
            zombie.setVillager(true);
            if (zombie instanceof PigZombie) {
                PigZombie pigZombie = (PigZombie) entity;
                pigZombie.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(Integer.MAX_VALUE, 10));
                pigZombie.setAngry(true);
                pigZombie.setAnger(100000);
            } else {
                if (zombie.isBaby()) {
                    zombie.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 3));
                } else {
                    zombie.setVillagerProfession(Villager.Profession.FARMER);
                    zombie.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(Integer.MAX_VALUE, 1));
                }
            }

        } else if (entity instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) entity;
            Zombie zombie = skeleton.getWorld().spawn(skeleton.getLocation(), Zombie.class);
            skeleton.setSkeletonType(Skeleton.SkeletonType.WITHER);
            skeleton.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(Integer.MAX_VALUE, 2));
            skeleton.setPassenger(zombie);

        } else if (entity instanceof Slime) {
            Slime slime = (Slime) entity;
            slime.setSize(5);

        } else if (entity instanceof Guardian) {
            Guardian guardian = (Guardian) entity;
            guardian.setElder(true);

        } else if (entity instanceof Enderman) {
            Enderman enderman = (Enderman) entity;

            enderman.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(Integer.MAX_VALUE, 5));

            Integer integer = new Random().nextInt(5);

            MaterialData mat;

            switch (integer) {
                case 0:
                    mat = Material.EMERALD_BLOCK.getNewData((byte) 0);
                    break;
                case 1:
                    mat = Material.DIAMOND_BLOCK.getNewData((byte) 0);
                    break;
                case 2:
                    mat = Material.GOLD_BLOCK.getNewData((byte) 0);
                    break;
                case 3:
                    mat = Material.IRON_BLOCK.getNewData((byte) 0);
                    break;
                default:
                    mat = Material.COAL_BLOCK.getNewData((byte) 0);
                    break;
            }
            enderman.setCarriedMaterial(mat);

        } else if (entity instanceof EnderDragon) {
            EnderDragon enderDragon = (EnderDragon) entity;
            enderDragon.setCustomName(translate("&5&lThe EnderDragon!"));

        } else if (entity instanceof Shulker) {
            Shulker shulker = (Shulker) entity;
            shulker.setMaxHealth(50D);
            shulker.setHealth(50D);

        } else if (entity instanceof Snowman) {
            Snowman snowman = (Snowman) entity;
            snowman.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(Integer.MAX_VALUE, 2));

        } else if (entity instanceof IronGolem) {
            IronGolem ironGolem = (IronGolem) entity;
            ironGolem.setPlayerCreated(true);

        } else if (entity instanceof Spider) {
            Spider spider = (Spider) entity;
            if (spider instanceof CaveSpider) {
                CaveSpider caveSpider = (CaveSpider) entity;
                caveSpider.addPotionEffect(PotionEffectType.SLOW.createEffect(Integer.MAX_VALUE, 3));
            } else {
                List<Entity> nearby = spider.getNearbyEntities(4D, 4D, 4D);
                if (nearby instanceof Player) {
                    Player player = (Player) nearby;
                    spider.setTarget(player);
                }
            }

        } else if (entity instanceof Wither) {
            Wither wither = (Wither) entity;
            wither.playEffect(EntityEffect.DEATH);
            wither.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(Integer.MAX_VALUE, 3));

        } else if (entity instanceof Blaze) {
            Blaze blaze = (Blaze) entity;
            blaze.setMaxHealth(50D);
            blaze.setHealth(50D);

        } else if (entity instanceof Giant) {
            Giant giant = (Giant) entity;
            giant.addPotionEffect(PotionEffectType.SLOW.createEffect(Integer.MAX_VALUE, 5));

        } else if (entity instanceof Endermite) {
            Endermite endermite = (Endermite) entity;
            endermite.addPotionEffect(PotionEffectType.ABSORPTION.createEffect(Integer.MAX_VALUE, 2));
            endermite.setMaxHealth(30D);
            endermite.setHealth(30D);

        } else if (entity instanceof Player) {
            Player p = (Player) entity;
            p.setDisplayName(translate("&5&l") + p.getName());
            sendSound(p, Sound.UI_BUTTON_CLICK, 0);

            p.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(Integer.MAX_VALUE, 4));
            p.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 3));
            p.addPotionEffect(PotionEffectType.HEAL.createEffect(1, 100));

        } else {
            entity.setCustomName(translate("&cEntity &4not&c supported"));
        }
    }
}
