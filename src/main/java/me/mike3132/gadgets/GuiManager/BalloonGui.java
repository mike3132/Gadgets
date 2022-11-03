package me.mike3132.gadgets.GuiManager;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.mike3132.gadgets.BalloonsManager.Balloons;
import me.mike3132.gadgets.ItemManager.CowBalloon;
import me.mike3132.gadgets.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BalloonGui {

    public static ChestGui createGui() {
        ChestGui gui = new ChestGui(3, Main.chatColor("&cBalloons"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        // Background that will be shown if no other gui items are present.
        OutlinePane backgroundPane = new OutlinePane(0,0,9,3, Pane.Priority.LOWEST);
        ItemStack backgroundItem = new ItemStack(Material.BEACON);
        ItemMeta backgroundMeta = backgroundItem.getItemMeta();
        backgroundMeta.setDisplayName(" ");
        backgroundItem.setItemMeta(backgroundMeta);
        backgroundPane.setRepeat(true);
        backgroundPane.addItem(new GuiItem(backgroundItem));

        gui.addPane(backgroundPane);

        // Cow balloon item. This will be shown to the player and the lore will tell them if they have it unlocked.

        StaticPane cowPane = new StaticPane(0,0, 9, 3, Pane.Priority.HIGHEST);
        ItemStack cowItem = CowBalloon.createCowItem();

        cowPane.addItem(new GuiItem(cowItem, event -> {
            Player player = (Player) event.getWhoClicked();
            if (!Balloons.balloonPlayers.containsKey(player)) {
                Balloons.summonBalloon(player);
                player.closeInventory();
                player.sendMessage(Main.chatColor("&bSuccessfully summoned your &2Cow &aBalloon"));
            } else {
                Balloons.removeBalloon(player);
                player.closeInventory();
                player.sendMessage(Main.chatColor("&bSuccessfully removed your &2Cow &aBalloon"));
            }

        }), 0,0);

        gui.addPane(cowPane);

        return gui;
    }
}