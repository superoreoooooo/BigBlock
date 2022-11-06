package xyz.oreodev.bigblock.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.oreodev.bigblock.enums.blockType;

import java.util.HashMap;

public class wandCommand implements CommandExecutor {
    HashMap<Player, blockType> wandMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage("select material");
                return false;
            }
            if (!wandMap.containsKey(player)) {
                for (blockType type : blockType.values()) {
                    if (args[1].equalsIgnoreCase(type.toString())) {
                        wandMap.put(player, type);
                        ItemStack item = new ItemStack(Material.BLAZE_ROD);
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(ChatColor.AQUA + "WAND");
                        item.setItemMeta(meta);
                        player.getInventory().setItemInMainHand(item);
                    }
                }
            } else {
                wandMap.remove(player);
                ItemStack item = new ItemStack(Material.BLAZE_ROD);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.AQUA + "WAND");
                item.setItemMeta(meta);
                player.getInventory().remove(item);
            }
        }
        return false;
    }
}
