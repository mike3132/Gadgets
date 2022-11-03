package me.mike3132.gadgets.EventManager;

import me.mike3132.gadgets.BalloonsManager.Balloons;
import me.mike3132.gadgets.ChatManager.Messages;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BalloonEvents implements Listener {

    @EventHandler
    public void onPlayerLeash(PlayerInteractEntityEvent pie) {
        Player player = pie.getPlayer();
        Cow cow = (Cow) pie.getRightClicked();
        if (Balloons.balloonPlayers.containsKey(player)) {
            if (Balloons.balloonPlayers.containsValue(cow)) {
                pie.setCancelled(true);
                Messages.sendMessage(player, "No-Lead-Event");
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent pqe) {
        Player player = pqe.getPlayer();
        if (Balloons.balloonPlayers.containsKey(player)) {
            Balloons.removeBalloon(player);
        }
    }
}
