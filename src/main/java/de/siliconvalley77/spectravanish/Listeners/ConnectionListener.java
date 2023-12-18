package de.siliconvalley77.spectravanish.Listeners;

import de.siliconvalley77.spectravanish.Main;
import de.siliconvalley77.spectravanish.VanishManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        VanishManager vanishManager = Main.getInstance().getVanishManager();
        vanishManager.hideAll(event.getPlayer());
    }
}
