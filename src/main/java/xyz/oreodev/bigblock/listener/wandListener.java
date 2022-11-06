package xyz.oreodev.bigblock.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.oreodev.bigblock.BigBlock;
import xyz.oreodev.bigblock.enums.blockType;
import xyz.oreodev.bigblock.util.blockUtil;

import java.util.ArrayList;
import java.util.List;

public class wandListener implements Listener {
    private blockUtil util;

    public wandListener() {
        this.util = new blockUtil();
    }

    public static List<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (cooldown.contains(player)) return;
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (player.getItemInHand() != null && !player.getItemInHand().getType().equals(Material.BLAZE_ROD)) return;
            if (!player.getTargetBlock(120).isEmpty()) {
                Location loc = player.getTargetBlock(120).getLocation();
                loc.add(0.5, 0, 0.5);
                BlockFace face = player.rayTraceBlocks(120).getHitBlockFace();
                Location loc2 = util.getLocByBlockFace(face, loc);
                int blockX = loc2.getBlockX() / 16 - 1;
                int blockY = loc2.getBlockY() / 16;
                int blockZ = loc2.getBlockZ() / 16 - 1;
                util.generateBlock(blockX, blockY, blockZ, blockType.STONE);
                cooldown.add(player);
                Bukkit.getScheduler().runTaskLaterAsynchronously(JavaPlugin.getPlugin(BigBlock.class), () -> {cooldown.remove(player);}, 20);
                if (player.getLocation().getBlock().getType().equals(Material.LIGHT)) player.teleport(player.getLocation().add(0, 16, 0));
            }
        }
    }
}
