package me.mike3132.gadgets.CommandManager;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import me.mike3132.gadgets.ChatManager.Messages;
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
        if (args.length == 0) {
            player.sendMessage(Main.chatColor("Not enough command args"));
            return true;
        }
        switch (args[0].toUpperCase()) {
            case "MENU":
                if (!player.hasPermission("Gadgets.Menu")) {
                    Messages.sendMessage(player, "No-Command-Permission");
                    return true;
                }
                ChestGui gui = InitialGui.createGui();
                gui.show(player);
                break;
            case "RELOAD":
                if (!player.hasPermission("Gadgets.Reload")) {
                    Messages.sendMessage(player, "No-Command-Permission");
                    return true;
                }
                Main.plugin.reloadConfig();
                sender.sendMessage(Main.chatColor("&a[&2Gadgets&a] " + "&6Config reloaded in &2" + String.valueOf(System.currentTimeMillis() - 1 + " &6ms")));
                break;
            default:
                Messages.sendMessage(player, "Not-Enough-Command-Args");
                break;
        }
        return true;
    }
}
