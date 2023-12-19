package de.siliconvalley77.spectravanish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        boolean hasSeeOthersPermission = player.hasPermission("spectravanish.seeothers");

        if (bool) {
            vanished.add(player);
            sendVanishMessage(player, true, hasSeeOthersPermission);
        } else {
            vanished.remove(player);
            sendVanishMessage(player, false, hasSeeOthersPermission);
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (player.equals(onlinePlayer)) continue;

            if (!hasSeeOthersPermission) {
                if (bool) {
                    onlinePlayer.hidePlayer(plugin, player);
                } else {
                    onlinePlayer.showPlayer(plugin, player);
                }
            }
        }
    }

    private void sendVanishMessage(Player player, boolean enteringVanish, boolean hasSeeOthersPermission) {
        String playerName = player.getName();
        String message;

        if (enteringVanish) {
            if (hasSeeOthersPermission) {
                message = playerName + " has entered vanish mode.";
            } else {
                message = ChatColor.YELLOW + playerName + " left the game.";
            }
        } else {
            if (hasSeeOthersPermission) {
                message = playerName + " has left vanish mode.";
            } else {
                message = ChatColor.YELLOW + playerName + " left the game.";
            }
        }

        Bukkit.broadcastMessage(message);
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
