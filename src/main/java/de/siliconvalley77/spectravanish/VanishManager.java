package de.siliconvalley77.spectravanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class VanishManager {
    private final Plugin plugin;
    private final List<Player> vanished;

    public VanishManager(Plugin plugin) {
        this.plugin = plugin;
        this.vanished = new ArrayList<>();
    }

    public List<Player> getVanished(){
        return vanished;
    }

    public boolean isVanished(Player player){
        return vanished.contains(player);
    }

    public void setVanished(Player player, boolean bool) {
        if (bool){
            vanished.add(player);
        }else {
            vanished.remove(player);
        }

        for (Player onlinePLayer : Bukkit.getOnlinePlayers()){
            if(player.equals(onlinePLayer)) continue;
            if (bool){
                onlinePLayer.hidePlayer(plugin, player);
            }else {
                onlinePLayer.showPlayer(plugin, player);
            }
        }
    }

    public void hideAll(Player player){
        vanished.forEach(player1 -> player.hidePlayer(plugin, player1));
    }

    public void showPlayers(Player player){
        vanished.forEach(player1 -> player.showPlayer(plugin, player1));
    }

    public void showPlayer(Player player, Player vanishedPlayer){
        player.showPlayer(vanishedPlayer);
    }
}
