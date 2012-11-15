package lostForceGames.commands;

import java.util.List;
import java.util.logging.Level;

import lostForceGames.LostForceGames;
import lostForceGames.arena.ArenaAPI;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCreate implements CommandExecutor {

	public LostForceGames main;
	public ArenaAPI api;

	public CommandCreate(LostForceGames main) {
		this.main = main;
	}
	
	public CommandCreate(ArenaAPI api) {
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

		if (args[0].equalsIgnoreCase("create")) {

			if (args.length == 1) {
				player.sendMessage(main.prefix + "Wrong Usage!");
				return true;
			}

			if (args.length == 2) {

				if (main.creator.containsKey(player.getName())) {
					main.creator.remove(player.getName());
					main.creator.put(player.getName(), args[1]);
					
					player.sendMessage(main.prefix + "You create now the Arena: " + ChatColor.AQUA + args[1]);
					return true;
				}

				main.creator.put(player.getName(), args[1]);

				player.sendMessage(main.prefix + "You create now the Arena: " + ChatColor.AQUA + args[1]);
				return true;
			}
		}
		
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
		
		if (args[0].equalsIgnoreCase("join")) {
			
			
			List<String> list = main.getConfig().getStringList("LostForceGames.Maps");
			if(api.getMapSize(list) == 0) {
				player.sendMessage(main.prefix + "No Maps found!");
				return true;
			}
			
			String name = api.getAcctualArena(main.acctualMap);
			
			int x = main.getConfig().getInt("LostForceGames.Location_1." + name + ".X");
			int y = main.getConfig().getInt("LostForceGames.Location_1." + name + ".X");
			int z = main.getConfig().getInt("LostForceGames.Location_1." + name + ".X");
			
		}
		return false;
	}
}
