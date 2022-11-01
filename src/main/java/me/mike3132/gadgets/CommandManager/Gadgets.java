package me.mike3132.gadgets.CommandManager;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import me.mike3132.gadgets.GuiManager.InitialGui;
import me.mike3132.gadgets.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gadgets implements CommandExecutor {

    public Gadgets() {
        Main.plugin.getCommand("Gadgets").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.chatColor("&4You cannot use commands from console"));
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 0) {
            if (!player.hasPermission("Gadgets.Main")) {
                player.sendMessage("Not enough permission");
                return false;
            }
            if (!args[0].equalsIgnoreCase("Menu")) {
                player.sendMessage("This is not a menu command");
                return false;
            }
            ChestGui gui = InitialGui.createGui();
            gui.show(player);
        } else {
            player.sendMessage("Not enough args");
        }
        return true;
    }
}
