package me.mike3132.gadgets;

import me.mike3132.gadgets.BalloonsManager.Balloons;
import me.mike3132.gadgets.CommandManager.Gadgets;
import me.mike3132.gadgets.EventManager.BalloonEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {
    public static Main plugin;

    public static String chatColor(String chatColor) {
        return ChatColor.translateAlternateColorCodes('&', chatColor);
    }

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(chatColor("&a[&2Gadgets&a] " + "&2&lENABLED"));

        // Event Register
        Bukkit.getPluginManager().registerEvents(new BalloonEvents(), this);

        // Command loader
        registerGive();

        // Config loader
        saveDefaultConfig();
        getConfig();
        createMessagesFile();
        createBalloonFile();
    }

    // Command register
    public void registerGive() {
        new Gadgets();
    }

    // File creation
    private File messages;
    private FileConfiguration messagesConfig;

    private void createMessagesFile() {
        messages = new File(getDataFolder(), "messages.yml");
        if (!messages.exists()) {
            messages.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
        messagesConfig = new YamlConfiguration();
        try {
         messagesConfig.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    private File balloon;
    private FileConfiguration balloonConfig;
    private void createBalloonFile() {
        balloon = new File(getDataFolder(), "BalloonConfig.yml");
        if (!balloon.exists()) {
            balloon.getParentFile().mkdirs();
            saveResource("BalloonConfig.yml", false);
        }
        balloonConfig = new YamlConfiguration();
        try {
            balloonConfig.load(balloon);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(chatColor("&a[&2Gadgets&a] " + "&2&lENABLED"));
    }
}
