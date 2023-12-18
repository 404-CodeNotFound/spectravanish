// © 404PluginNotFound
package de.siliconvalley77.spectravanish;

import de.siliconvalley77.spectravanish.Commands.SpectraVanishCommand;
import de.siliconvalley77.spectravanish.Listeners.ConnectionListener;
import de.siliconvalley77.spectravanish.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    private VanishManager vanishManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.instance = this;
        this.vanishManager = new VanishManager(this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
        getCommand("sv").setExecutor(new SpectraVanishCommand());

        int resourceId = 114029;
        new UpdateChecker(this, resourceId).getLatestVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                getLogger().info("Plugin is up to date!");
            } else {
                getLogger().info("There is a new version available: " + version);
            }
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }

    public VanishManager getVanishManager() {
        return vanishManager;
    }
}