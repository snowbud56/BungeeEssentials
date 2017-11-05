import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;

public class BungeeEssentials extends Plugin {
    private static BungeeEssentials instance;
    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("enabled!");
        getProxy().getPluginManager().registerCommand(this, new SendCommand());
        getProxy().getPluginManager().registerCommand(this, new AnnounceCommand());
        getProxy().getPluginManager().registerCommand(this, new PingCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static BungeeEssentials getInstance() {
        return instance;
    }
}
