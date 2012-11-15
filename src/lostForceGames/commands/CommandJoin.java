package lostForceGames.commands;

import java.util.logging.Level;

import lostForceGames.LostForceGames;
import lostForceGames.arena.ArenaAPI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandJoin implements CommandExecutor {

	public LostForceGames main;
	public ArenaAPI api;

	public CommandJoin(LostForceGames main) {
		this.main = main;
	}
	
	public CommandJoin(ArenaAPI api) {
		this.api = api;
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
			if (args[0].equalsIgnoreCase("join")) {
				
				if(api.getMapSize() == 0) {
					player.sendMessage(main.prefix + "No Maps found!");
					return true;
				}
				
				String name = api.getAcctualArena(main.acctualMap);
				
				int x = main.getConfig().getInt("LostForceGames.Location_1." + name + ".X");
				int y = main.getConfig().getInt("LostForceGames.Location_1." + name + ".X");
				int z = main.getConfig().getInt("LostForceGames.Location_1." + name + ".X");
				
			}
		}

		return false;
	}

}
