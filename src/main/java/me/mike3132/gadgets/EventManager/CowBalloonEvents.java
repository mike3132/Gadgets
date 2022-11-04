package me.mike3132.gadgets.EventManager;

import me.mike3132.gadgets.BalloonsManager.CowBalloon;
import me.mike3132.gadgets.BalloonsManager.GlowSquidBalloon;
import me.mike3132.gadgets.ChatManager.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class CowBalloonEvents implements Listener {

    @EventHandler
    public void onPlayerLeash(PlayerInteractEntityEvent pie) {
        Player player = pie.getPlayer();
        if (CowBalloon.cowBalloonPlayers.containsKey(player) || GlowSquidBalloon.glowSquidBalloonPlayers.containsKey(player)) {
            pie.setCancelled(true);
            Messages.sendMessage(player, "No-Right-Click-With-Balloon");
        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent pqe) {
        Player player = pqe.getPlayer();
        if (CowBalloon.cowBalloonPlayers.containsKey(player)) {
            CowBalloon.removeBalloon(player);
        }
    }
}
