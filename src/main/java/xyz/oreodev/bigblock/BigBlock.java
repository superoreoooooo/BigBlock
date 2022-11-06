package xyz.oreodev.bigblock;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.bigblock.command.completer.wandCompleter;
import xyz.oreodev.bigblock.command.reloadCommand;
import xyz.oreodev.bigblock.command.wandCommand;
import xyz.oreodev.bigblock.util.msgUtil;

public final class BigBlock extends JavaPlugin {
    private final msgUtil m = new msgUtil();

    @Override
    public void onEnable() {
        getCommand("r").setExecutor(new reloadCommand());
        getCommand("wand").setExecutor(new wandCommand());
        getCommand("wand").setTabCompleter(new wandCompleter());

        m.Msg("Hello, world!");
    }

    @Override
    public void onDisable() {
        m.Msg("Shutting Down...");
    }
}
