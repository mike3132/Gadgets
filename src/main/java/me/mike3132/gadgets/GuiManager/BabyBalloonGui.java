package me.mike3132.gadgets.GuiManager;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.mike3132.gadgets.BalloonsManager.CowBalloon;
import me.mike3132.gadgets.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BabyBalloonGui {

    public static ChestGui createGui() {
        ChestGui gui = new ChestGui(3, Main.chatColor("&3Balloon Modifications"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        // Background that will be shown if no other gui items are present.
        OutlinePane backgroundPane = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        ItemStack backgroundItem = new ItemStack(Material.END_CRYSTAL);
        ItemMeta backgroundMeta = backgroundItem.getItemMeta();
        backgroundMeta.setDisplayName(" ");
        backgroundItem.setItemMeta(backgroundMeta);
        backgroundPane.setRepeat(true);
        backgroundPane.addItem(new GuiItem(backgroundItem));
        gui.addPane(backgroundPane);

        // Cow balloon pane
        StaticPane cowPane = new StaticPane(0, 0, 9, 6, Pane.Priority.HIGHEST);
        ItemStack cowItem = new ItemStack(Material.DIAMOND);
        ItemMeta cowMeta = cowItem.getItemMeta();
        cowPane.addItem(new GuiItem(cowItem, event -> {
            Player playerClicker = (Player) event.getWhoClicked();

        }), 0, 0);

        cowItem.setItemMeta(cowMeta);
        gui.addPane(cowPane);
        return gui;
    }
}
