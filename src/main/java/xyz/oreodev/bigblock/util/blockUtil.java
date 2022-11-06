package xyz.oreodev.bigblock.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.bigblock.BigBlock;
import xyz.oreodev.bigblock.enums.blockType;

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
    }

}
