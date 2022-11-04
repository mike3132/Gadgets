package me.mike3132.gadgets.GuiManager;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.mike3132.gadgets.BalloonGuiItemManager.CowBalloonItem;
import me.mike3132.gadgets.BalloonGuiItemManager.GlowSquidBalloonItem;
import me.mike3132.gadgets.BalloonsManager.CowBalloon;
import me.mike3132.gadgets.BalloonsManager.GlowSquidBalloon;
import me.mike3132.gadgets.ChatManager.Messages;
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

        // Cow balloon item.
        StaticPane cowPane = new StaticPane(0, 0, 9, 3, Pane.Priority.HIGHEST);
        ItemStack cowItem = CowBalloonItem.createCowItem(player);
        cowPane.addItem(new GuiItem(cowItem, event -> {
            Player playerClicker = (Player) event.getWhoClicked();
            if (!playerClicker.hasPermission("Gadgets.Balloon.Cow") ||
                    !playerClicker.hasPermission("Gadgets.Balloon.Passive") ||
                    !playerClicker.hasPermission("Gadgets.Balloon.*")) {
                event.setCancelled(true);
                return;
            }
            if (!CowBalloon.cowBalloonPlayers.containsKey(player)) {
                CowBalloon.summonBalloon(playerClicker);
                Messages.sendMessage(playerClicker, "Balloon-Summon-Message");
            } else {
                CowBalloon.removeBalloon(playerClicker);
                Messages.sendMessage(playerClicker, "Balloon-Despawn-Message");
            }

        }), 0, 0);
        gui.addPane(cowPane);

        // Glow squid balloon item.
        StaticPane glowSquidPane = new StaticPane(0,0,9,3, Pane.Priority.HIGHEST);
        ItemStack glowSquidItem = GlowSquidBalloonItem.createGlowSquidItem(player);
        glowSquidPane.addItem(new GuiItem(glowSquidItem, event -> {
            Player playerClicker = (Player) event.getWhoClicked();
            if (!playerClicker.hasPermission("Gadgets.Balloon.GlowSquid") ||
                    !playerClicker.hasPermission("Gadgets.Balloon.Passive") ||
                    !playerClicker.hasPermission("Gadgets.Balloon.*")) {
                event.setCancelled(true);
                return;
            }
            if (!GlowSquidBalloon.glowSquidBalloonPlayers.containsKey(player)) {
                GlowSquidBalloon.summonBalloon(playerClicker);
                Messages.sendMessage(playerClicker, "Balloon-Summon-Message");
            } else {
                GlowSquidBalloon.removeBalloon(playerClicker);
                Messages.sendMessage(player, "Balloon-Despawn-Message");
            }

        }), 1, 0);
        gui.addPane(glowSquidPane);



        return gui;
    }

}
