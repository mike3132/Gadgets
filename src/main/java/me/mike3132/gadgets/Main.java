package me.mike3132.gadgets;

import me.mike3132.gadgets.CommandManager.Gadgets;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main plugin;

    public static String chatColor(String chatColor) {
        return ChatColor.translateAlternateColorCodes('&', chatColor);
    }

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(chatColor("6Gadgets" + "&2&lENABLED"));

        // Event Register


        // Command loader
        registerGive();

    }

    // Command register
    public void registerGive() {
        new Gadgets();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(chatColor("&6Gadgets" + "&2&lENABLED"));
    }
}
