package de.siliconvalley77.spectravanish.Commands;

import de.siliconvalley77.spectravanish.Main;
import de.siliconvalley77.spectravanish.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectraVanishCommand implements CommandExecutor {
    private final String VANISH_OWN_PERMISSION = "spectravanish.vanish.own";
    private final String VANISH_OTHER_PERMISSION = "spectravanish.vanish.other";
    private final String REVEAL_PERMISSION = "spectravanish.reveal";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        VanishManager vanishManager = Main.getInstance().getVanishManager();

        if (strings.length == 1 && strings[0].equals("vanish") && commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (commandSender.hasPermission(VANISH_OWN_PERMISSION)) {
                if (!vanishManager.isVanished(player)) {
                    vanishManager.setVanished(player, true);
                    commandSender.sendMessage("You have successfully vanished yourself.");
                } else {
                    vanishManager.setVanished(player, false);
                    commandSender.sendMessage("You are no longer vanished.");
                }
            } else {
                commandSender.sendMessage("You do not have permission to vanish yourself.");
            }
        } else if (strings.length == 2 && strings[0].equals("vanish")) {
            Player player = (Player) commandSender;
            Player playerToVanish = Bukkit.getPlayer(strings[1]);

            if (playerToVanish == null || !playerToVanish.isOnline()) {
                commandSender.sendMessage("This player is not online or doesn't exist!");
                return true;
            }

            if (commandSender.hasPermission(VANISH_OTHER_PERMISSION)) {
                if (!vanishManager.isVanished(playerToVanish)) {
                    vanishManager.setVanished(playerToVanish, true);
                    commandSender.sendMessage("Successfully vanished " + playerToVanish.getName());
                } else {
                    vanishManager.setVanished(playerToVanish, false);
                    commandSender.sendMessage("Successfully unvanished " + playerToVanish.getName());
                }
            } else {
                commandSender.sendMessage("You do not have permission to vanish others.");
            }
        } else if (strings.length == 2 && strings[0].equals("reveal")) {
            Player playerToReveal = Bukkit.getPlayer(strings[1]);

            if (playerToReveal == null || !playerToReveal.isOnline()) {
                commandSender.sendMessage("This player is not online or doesn't exist!");
                return true;
            }

            if (commandSender.hasPermission(REVEAL_PERMISSION)) {
                vanishManager.showPlayer((Player) commandSender, playerToReveal);
            } else {
                commandSender.sendMessage("You do not have permission to reveal other players.");
            }
        } else if (strings.length == 1 && strings[0].equals("reveal")) {
            commandSender.sendMessage("Please specify a player to reveal: /sv reveal <player>");
        }
        return true;
    }
}
