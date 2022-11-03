package me.mike3132.gadgets.BalloonGuiItemManager;

import me.mike3132.gadgets.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;

public class CowBalloon {


    public static ItemStack createCowItem(Player player) {
        ItemStack item = new ItemStack(Material.COW_SPAWN_EGG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Main.chatColor("&2Cow &aBalloon"));
        ArrayList<String> lore = new ArrayList<>();
        // Set lore
        if (!player.hasPermission("Gadgets.Balloon.Cow") ||
                !player.hasPermission("Gadgets.Balloon.Passive") ||
                !player.hasPermission("Gadgets.Balloon.*")) {
            File balloonConfig = new File(Main.plugin.getDataFolder(), "BalloonConfig.yml");
            YamlConfiguration balloonMessage = YamlConfiguration.loadConfiguration(balloonConfig);
            for (String realLore : balloonMessage.getStringList("No-Cow-Balloon-Use-Permission")) {
                lore.add(Main.chatColor("" + realLore));

            }
        } else {
            File balloonConfig = new File(Main.plugin.getDataFolder(), "BalloonConfig.yml");
            YamlConfiguration balloonMessage = YamlConfiguration.loadConfiguration(balloonConfig);
            for (String realLore : balloonMessage.getStringList("Has-Cow-Balloon-Use-Permission")) {
                lore.add(Main.chatColor("" + realLore));
            }
        }

        meta.setLore(lore);
        item.setItemMeta(meta);


        return item;
    }
}
