package lostForceGames.commands;

import java.util.logging.Level;

import lostForceGames.LostForceGames;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEnable implements CommandExecutor {

	public LostForceGames main;

	public CommandEnable(LostForceGames main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			main.getLogger().log(Level.WARNING, "You are not a Player!");
			return true;
		}

		final Player player = (Player) sender;

		if (args.length == 0) {
			player.sendMessage(main.prefix + "Wrong Usage!");
			return true;
		}

		if (args.length == 1) {

			if (args[0].equalsIgnoreCase("stop")) {

				if (main.enable == false) {
					player.sendMessage(main.prefix + "The Game in already stopped!");
					return true;
				} else {
					main.enable = false;
					player.sendMessage(main.prefix + "You stopped the Game!");
					return true;
				}
			}

			if (args[0].equalsIgnoreCase("start")) {

				if (main.enable == true) {
					player.sendMessage(main.prefix + "The Game in already enabled!");
					return true;
				} else {
					main.enable = true;
					player.sendMessage(main.prefix + "You enabled the Game!");
					return true;
				}
			}
		}
		return false;
	}

}
