package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*/

import me.boomboompower.testserv.TestServCore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffectType;

import static me.boomboompower.testserv.utils.Utils.*;

public class TestServCoreMobSpawning implements Listener {

    private TestServCore testServCore;

    public TestServCoreMobSpawning(TestServCore testServCore) {
        this.testServCore = testServCore;

        Bukkit.getPluginManager().registerEvents(this, testServCore);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEntitySpawn(EntitySpawnEvent e) {
        Entity entity = e.getEntity();

        if (entity instanceof Creeper) {
            Creeper creeper = (Creeper) entity;
            creeper.setPowered(false);

        } else if (entity instanceof Zombie) {
            Zombie zombie = (Zombie) entity;
            zombie.setBaby(false);
            zombie.setVillager(true);
            zombie.setVillagerProfession(Villager.Profession.FARMER);
            zombie.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(Integer.MAX_VALUE, 1));

        } else if (entity instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) entity;
            Zombie zombie = skeleton.getWorld().spawn(skeleton.getLocation(), Zombie.class);
            skeleton.setSkeletonType(Skeleton.SkeletonType.WITHER);
            skeleton.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(Integer.MAX_VALUE, 2));
            skeleton.setPassenger(zombie);

        } else if (entity instanceof Slime) {
            Slime slime = (Slime) entity;
            slime.setSize(5);

        } else if (entity instanceof MagmaCube) {
            MagmaCube magmaCube = (MagmaCube) entity;
            magmaCube.setSize(15);

        } else if (entity instanceof Guardian) {
            Guardian guardian = (Guardian) entity;
            guardian.setElder(true);

        } else if (entity instanceof PigZombie) {
            PigZombie pigZombie = (PigZombie) entity;
            pigZombie.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(Integer.MAX_VALUE, 10));
            pigZombie.setAngry(true);
            pigZombie.setAnger(100);

        } else if (entity instanceof Enderman) {
            Enderman enderman = (Enderman) entity;

            enderman.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(Integer.MAX_VALUE, 5));
            enderman.setCarriedMaterial(Material.DIAMOND_BLOCK.getNewData((byte) 1));

        } else if (entity instanceof EnderDragon) {
            EnderDragon enderDragon = (EnderDragon) entity;
            enderDragon.setCustomName(translate("&5&lThe EnderDragon!"));

        } else if (entity instanceof Shulker) { // TODO add something *UNIQUE*
            Shulker shulker = (Shulker) entity;

        } else if (entity instanceof Snowman) {
            Snowman snowman = (Snowman) entity;
            snowman.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(Integer.MAX_VALUE, 2));

        } else if (entity instanceof IronGolem) {
            IronGolem ironGolem = (IronGolem) entity;
            ironGolem.setPlayerCreated(true);

        } else if (entity instanceof Spider) { // TODO add something *UNIQUE*
            Spider spider = (Spider) entity;

        } else if (entity instanceof CaveSpider) { // TODO add something *UNIQUE*
            CaveSpider caveSpider = (CaveSpider) entity;

        } else if (entity instanceof Wither) { // TODO add something *UNIQUE*
            Wither wither = (Wither) entity;

        } else {
            entity.setCustomName(translate("&aEntity not supported"));

        } if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entity;
            living.setCollidable(false);
            living.setAI(true);
            living.setMaximumAir(10);
        }
    }
}
