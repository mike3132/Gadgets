package me.mike3132.gadgets.BalloonsManager;

import me.mike3132.gadgets.Main;
import org.bukkit.Location;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class Balloons {
    public static HashMap<Player, Cow> balloonPlayers = new HashMap<>();


    public static void summonBalloon(Player player) {
        Location location = player.getLocation();
        Location playerLocationModified = player.getLocation().add(0, 3, 0);
        Cow cow = (Cow) playerLocationModified.getWorld().spawnEntity(playerLocationModified, EntityType.COW);
        cow.setLeashHolder(player);
        cow.setInvulnerable(true);
        cow.setSilent(true);
        cow.setBaby();
        balloonPlayers.put(player, cow);

        Vector vector = location.getDirection().normalize().multiply(1).add(new Vector(0, 3, 0));
        new BukkitRunnable() {
            @Override
            public void run() {
                if (balloonPlayers.containsKey(player)) {
                    cow.teleport(player.getLocation().clone().add(vector));
                }
            }
        }.runTaskTimer(Main.plugin, 0, 1);
    }

    public static void removeBalloon(Player player) {
        if (balloonPlayers.containsKey(player)) {
            Cow cow = balloonPlayers.get(player);
            balloonPlayers.remove(player);
            cow.remove();
        }
    }
}


// Custom Armor stand code for later use

//     armorStand.setInvisible(true);
//     armorStand.setCustomNameVisible(false);
//     armorStand.setGravity(false);
//     armorStand.setCanPickupItems(false);
//     armorStand.setArms(true);
//     armorStand.setBasePlate(false);
//     armorStand.getEquipment().setHelmet(new ItemStack(itemStack));
//     armorStand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING_OR_CHANGING);
//     armorStand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING_OR_CHANGING);
//     armorStand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING_OR_CHANGING);
//     armorStand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING_OR_CHANGING);
//     armorStand.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
//     armorStand.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING_OR_CHANGING);

