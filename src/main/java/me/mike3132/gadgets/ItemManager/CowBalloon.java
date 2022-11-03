package me.mike3132.gadgets.ItemManager;

import me.mike3132.gadgets.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CowBalloon {


    public static ItemStack createCowItem(Player player) {
        ItemStack item = new ItemStack(Material.COW_SPAWN_EGG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Main.chatColor("&2Cow &aBalloon"));
        ArrayList<String> lore = new ArrayList<>();
        // Set lore
        if (player.hasPermission("Example.Perm")) {
            lore.add("This will show with perms");
        } else {
            lore.add("This will show without perms");
        }

        meta.setLore(lore);
        item.setItemMeta(meta);


        return item;
    }
}
