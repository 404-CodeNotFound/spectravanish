package de.siliconvalley77.spectravanish.Commands;

import de.siliconvalley77.spectravanish.Main;
import de.siliconvalley77.spectravanish.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectraVanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        VanishManager vanishManager = Main.getInstance().getVanishManager();
        if (strings.length == 1){

        } else if (commandSender instanceof Player) {
            Player player = ((Player) commandSender);
            if (!vanishManager.isVanished(player)){
                vanishManager.setVanished(player, true);
                commandSender.sendMessage("You successfully vanished yourself");
            }else {
                    Player playerToVanish = Bukkit.getPlayer(strings[0]);
                    if (playerToVanish == null || !playerToVanish.isOnline()){
                        commandSender.sendMessage("This Player is not online or doesn't exists!");
                        return true;
                    }
                vanishManager.setVanished(player, false);
                commandSender.sendMessage("Successesfully vanished ", strings[0]);
            }
        }
        return true;
    }
}
