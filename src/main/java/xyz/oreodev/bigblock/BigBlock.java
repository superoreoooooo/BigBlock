package xyz.oreodev.bigblock;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.bigblock.command.reloadCommand;
import xyz.oreodev.bigblock.util.msgUtil;

public final class BigBlock extends JavaPlugin {
    private final msgUtil m = new msgUtil();

    @Override
    public void onEnable() {
        getCommand("r").setExecutor(new reloadCommand());

        m.Msg("Hello, world!");
    }

    @Override
    public void onDisable() {
        m.Msg("Shutting Down...");
    }
}
