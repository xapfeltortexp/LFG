package lostForceGames.commands;

import java.util.logging.Level;

import lostForceGames.LostForceGames;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandJoin implements CommandExecutor {

	public LostForceGames main;

	public CommandJoin(LostForceGames main) {
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
		
		return false;
	}

}
