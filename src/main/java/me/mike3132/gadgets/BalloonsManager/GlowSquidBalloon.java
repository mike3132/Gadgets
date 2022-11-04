package me.mike3132.gadgets.BalloonsManager;

import me.mike3132.gadgets.Main;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.GlowSquid;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class GlowSquidBalloon {

    public static HashMap<Player, GlowSquid> glowSquidBalloonPlayers = new HashMap<>();

    public static void summonBalloon(Player player) {
        Location location = player.getLocation();
        Location playerLocationModified = player.getLocation().add(0, 4, 0);
        GlowSquid glowSquid = (GlowSquid) playerLocationModified.getWorld().spawnEntity(playerLocationModified, EntityType.GLOW_SQUID);
        glowSquid.setLeashHolder(player);
        glowSquid.setInvulnerable(true);
        glowSquid.setSilent(true);

        glowSquidBalloonPlayers.put(player, glowSquid);

        Vector vector = location.getDirection().normalize().multiply(1).add(new Vector(0, 4, 0));
        new BukkitRunnable() {
            @Override
            public void run() {
                if (glowSquidBalloonPlayers.containsKey(player)) {
                    glowSquid.teleport(player.getLocation().clone().add(vector));
                }
            }
        }.runTaskTimer(Main.plugin, 0, 1);
    }

    public static void removeBalloon(Player player) {
        if (glowSquidBalloonPlayers.containsKey(player)) {
            GlowSquid glowSquid = glowSquidBalloonPlayers.get(player);
            glowSquidBalloonPlayers.remove(player);
            glowSquid.remove();
            glowSquid.getWorld().spawnParticle(Particle.CLOUD, glowSquid.getLocation().add(0,0,0), 10,0,0, 0);
        }
    }


}
