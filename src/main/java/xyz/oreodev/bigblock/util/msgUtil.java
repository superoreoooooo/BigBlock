package xyz.oreodev.bigblock.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class msgUtil<T> {
    public final String bar = "====================================================";
    public final String prefix = "[BigBlock] ";

    public void Msg(T value) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + prefix + bar);
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + prefix + value.toString());
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + prefix + bar);
    }
}
