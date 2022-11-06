package xyz.oreodev.bigblock.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.oreodev.bigblock.util.msgUtil;

public class reloadCommand implements CommandExecutor {
    private msgUtil m = new msgUtil();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        m.Msg("reloading..");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload confirm");
        return false;
    }
}
