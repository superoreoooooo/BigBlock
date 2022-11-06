package xyz.oreodev.bigblock.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.bigblock.BigBlock;
import xyz.oreodev.bigblock.enums.blockType;

import java.util.ArrayList;
import java.util.List;

public class blockUtil {
    private final BigBlock plugin = JavaPlugin.getPlugin(BigBlock.class);

    public void generateBlock(int blockX, int blockY, int blockZ, blockType type) {
        int nX = blockX * 16;
        int nY = blockY * 16;
        int nZ = blockZ * 16;
        int cX = 1000000;
        int cY = 0;
        int cZ = 1000000;

        Location newBlockPos = new Location(Bukkit.getWorld("world"), nX, nY, nZ);
        Location copyPos = new Location(Bukkit.getWorld("world"), cX, cY, cZ);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 16; y++) {
                    newBlockPos.getBlock().setType(copyPos.getBlock().getType());
                    newBlockPos.set(nX + x, nY + y, nZ + z);
                    copyPos.set(cX + x, cY + y, cZ + z);
                }
            }
        }
        newBlockPos.getBlock().setType(copyPos.getBlock().getType());
    }

    List<Player> playerList = new ArrayList<>();

    public int taskId;

    public Location getLocByBlockFace(BlockFace face, Location loc) {
        switch (face) {
            case UP:
                loc.add(0,1,0);
                break;
            case DOWN:
                loc.add(0,-1,0);
                break;
            case EAST:
                loc.add(1,0,0);
                break;
            case NORTH:
                loc.add(0,0,-1);
                break;
            case WEST:
                loc.add(-1,0,0);
                break;
            case SOUTH:
                loc.add(0,0,1);
                break;
            default:
                break;
        }
        return loc;
    }

    public void runTask(Player player) {
        if (playerList.contains(player)) {
            playerList.remove(player);
            return;
        }
        else playerList.add(player);
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (playerList.size() == 0) Bukkit.getScheduler().cancelTasks(plugin);
                Bukkit.getConsoleSender().sendMessage("tick! " + playerList);
                if (!player.getItemInHand().getType().equals(Material.BLAZE_ROD)) return;
                if (!player.getTargetBlock(120).isEmpty()) {
                    Location loc = player.getTargetBlock(120).getLocation();
                    loc.add(0.5, 0, 0.5);
                    BlockFace face = player.rayTraceBlocks(120).getHitBlockFace();
                    //player.sendMessage(face.toString());
                    spawnFb(getLocByBlockFace(face, loc));
                }
            }
        }, 0, 10);
    }

    public void spawnFb(Location loc) {
        FallingBlock fb = Bukkit.getWorld("world").spawnFallingBlock(loc, Material.RED_STAINED_GLASS, (byte) 0);
        fb.setGlowing(true);
        fb.setGravity(false);
        Bukkit.getScheduler().runTaskLater(plugin, fb::remove, 10);
    }
}
