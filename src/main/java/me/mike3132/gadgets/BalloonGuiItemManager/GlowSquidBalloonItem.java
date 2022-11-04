package me.mike3132.gadgets.BalloonGuiItemManager;

import me.mike3132.gadgets.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;

public class GlowSquidBalloonItem {

    public static ItemStack createGlowSquidItem(Player player) {
        ItemStack item = new ItemStack(Material.GLOW_SQUID_SPAWN_EGG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Main.chatColor("&dGow &bSquid &aBalloon"));
        ArrayList<String> lore = new ArrayList<>();
        if (!player.hasPermission("Gadgets.Balloon.GlowSquid") ||
        !player.hasPermission("Gadgets.Balloon.Passive") ||
        !player.hasPermission("Gadgets.Balloon.*")) {
            File balloonConfig = new File(Main.plugin.getDataFolder(), "BalloonConfig.yml");
            YamlConfiguration balloonMessage = YamlConfiguration.loadConfiguration(balloonConfig);
            for (String realLore : balloonMessage.getStringList("No-Glow-Squid-Permission")) {
                lore.add(Main.chatColor("" + realLore));
            }
        } else {
            File balloonConfig = new File(Main.plugin.getDataFolder(), "BalloonConfig.yml");
            YamlConfiguration balloonMessage = YamlConfiguration.loadConfiguration(balloonConfig);
            for (String realLore : balloonMessage.getStringList("Has-Glow-Squid-Permission")) {
                lore.add(Main.chatColor("" + realLore));
            }
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
