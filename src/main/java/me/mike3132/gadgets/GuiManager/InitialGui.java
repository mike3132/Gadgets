package me.mike3132.gadgets.GuiManager;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import me.mike3132.gadgets.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InitialGui {

    public static ChestGui createGui() {
        ChestGui gui = new ChestGui(6, Main.chatColor("&6Ragnarok &bGadgets"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane backgroundPane = new OutlinePane(0,0,9,6, Pane.Priority.LOWEST);
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemMeta.addEnchant(Enchantment.LUCK, 10, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        backgroundPane.addItem(new GuiItem(new ItemStack(item)));
        backgroundPane.setRepeat(true);

        gui.addPane(backgroundPane);

        OutlinePane balloonPane = new OutlinePane(4,2,9,6, Pane.Priority.HIGHEST);
        ItemStack balloonItem = new ItemStack(Material.LEAD);
        ItemMeta balloonMeta = balloonItem.getItemMeta();
        balloonMeta.setDisplayName(Main.chatColor("&aClick here to see balloons"));
        balloonMeta.addEnchant(Enchantment.LUCK, 10, true);
        balloonMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        balloonItem.setItemMeta(balloonMeta);
        balloonPane.addItem(new GuiItem(balloonItem, event -> {
            Player player = (Player) event.getWhoClicked();
            ChestGui balloonGui = BalloonGui.createGui();
            balloonGui.show(player);
        }));
        gui.addPane(balloonPane);

        return gui;
    }
}
