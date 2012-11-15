package lostForceGames.commands;

import java.util.logging.Level;

import lostForceGames.LostForceGames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCreate implements CommandExecutor {

	public LostForceGames main;

	public CommandCreate(LostForceGames main) {
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

		if (args[0].equalsIgnoreCase("create")) {

			if (args.length == 1) {
				player.sendMessage(main.prefix + "Wrong Usage!");
				return true;
			}

			if (args.length == 2) {

				if (main.creator.containsKey(player.getName())) {
					player.sendMessage(main.prefix + "You are already in the Creator Mode!");
					return true;
				}

				main.creator.put(player.getName(), args[1]);

				player.sendMessage(main.prefix + "You create now the Arena: " + ChatColor.AQUA + args[1]);
				return true;
			}
		}
		return false;
	}
}
