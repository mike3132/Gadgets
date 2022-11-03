package me.mike3132.gadgets.GuiManager;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.mike3132.gadgets.BalloonsManager.Balloons;
import me.mike3132.gadgets.ChatManager.Messages;
import me.mike3132.gadgets.BalloonGuiItemManager.CowBalloon;
import me.mike3132.gadgets.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BalloonGui {

    public static ChestGui createGui(Player player) {
        ChestGui gui = new ChestGui(3, Main.chatColor("&cBalloons"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        // Background that will be shown if no other gui items are present.
        OutlinePane backgroundPane = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        ItemStack backgroundItem = new ItemStack(Material.BEACON);
        ItemMeta backgroundMeta = backgroundItem.getItemMeta();
        backgroundMeta.setDisplayName(" ");
        backgroundItem.setItemMeta(backgroundMeta);
        backgroundPane.setRepeat(true);
        backgroundPane.addItem(new GuiItem(backgroundItem));

        gui.addPane(backgroundPane);

        // Cow balloon item. This will be shown to the player and the lore will tell them if they have it unlocked.

        StaticPane cowPane = new StaticPane(0, 0, 9, 3, Pane.Priority.HIGHEST);
        ItemStack cowItem = CowBalloon.createCowItem(player);

        cowPane.addItem(new GuiItem(cowItem, event -> {
            Player playerClicker = (Player) event.getWhoClicked();
            if (!playerClicker.hasPermission("Gadgets.Balloon.Cow") ||
                    !playerClicker.hasPermission("Gadgets.Balloon.Passive") ||
                    !playerClicker.hasPermission("Gadgets.Balloon.*")) {
                event.setCancelled(true);
                return;
            }
            if (!Balloons.balloonPlayers.containsKey(player)) {
                Balloons.summonBalloon(playerClicker);
                playerClicker.closeInventory();
                Messages.sendMessage(playerClicker, "Balloon-Summon-Message");
            } else {
                Balloons.removeBalloon(playerClicker);
                playerClicker.closeInventory();
                Messages.sendMessage(playerClicker, "Balloon-Despawn-Message");
            }

        }), 0, 0);

        gui.addPane(cowPane);

        return gui;
    }
}
