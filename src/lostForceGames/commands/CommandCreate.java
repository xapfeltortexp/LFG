package lostForceGames.commands;

import java.util.List;
import java.util.logging.Level;

import lostForceGames.LostForceGames;
import lostForceGames.arena.ArenaAPI;

import org.bukkit.ChatColor;
import org.bukkit.Location;
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

			if (!(player.hasPermission("LostForceGames.create"))) {
				player.sendMessage(main.prefix + "You dont have Permissions");
				return true;
			}

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

			if (!(player.hasPermission("LostForceGames.stop"))) {
				player.sendMessage(main.prefix + "You dont have Permissions");
				return true;
			}

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

			if (!(player.hasPermission("LostForceGames.start"))) {
				player.sendMessage(main.prefix + "You dont have Permissions");
				return true;
			}

			if (main.enable == true) {
				player.sendMessage(main.prefix + "The Game in already enabled!");
				return true;
			} else {
				main.enable = true;
				player.sendMessage(main.prefix + "You enabled the Game!");
				return true;
			}
		}

		if (args[0].equalsIgnoreCase("setspawn")) {

			if (!(player.hasPermission("LostForceGames.setspawn"))) {
				player.sendMessage(main.prefix + "You dont have Permissions");
				return true;
			}

			if (args.length == 1) {
				player.sendMessage(main.prefix + "Wrong Usage!");
				return true;
			}

			if (args.length == 2) {
				player.sendMessage(main.prefix + "Wrong Usage!");
				return true;
			}

			if (main.getConfig().getString("LostForceGames.Location_1." + args[1] + ".X") == null || main.getConfig().getString("LostForceGames.Location_2." + args[1] + ".X") == null) {
				player.sendMessage(main.prefix + "Arena not found!");
				return true;
			} else {

				double x = player.getLocation().getX();
				double y = player.getLocation().getY();
				double z = player.getLocation().getZ();
				float yaw = player.getLocation().getYaw();
				float pitch = player.getLocation().getPitch();

				if (args[2].equalsIgnoreCase("red")) {

					main.getConfig().addDefault("LostForceGames.Spawns.Red." + args[1] + ".X", x);
					main.getConfig().addDefault("LostForceGames.Spawns.Red." + args[1] + ".Y", y);
					main.getConfig().addDefault("LostForceGames.Spawns.Red." + args[1] + ".Z", z);
					main.getConfig().addDefault("LostForceGames.Spawns.Red." + args[1] + ".Yaw", yaw);
					main.getConfig().addDefault("LostForceGames.Spawns.Red." + args[1] + ".Pitch", pitch);
					main.saveConfig();

					player.sendMessage(main.prefix + "Spawn set for: " + ChatColor.RED + "red");
					return true;

				} else if (args[2].equalsIgnoreCase("blue")) {

					main.getConfig().addDefault("LostForceGames.Spawns.Blue." + args[1] + ".X", x);
					main.getConfig().addDefault("LostForceGames.Spawns.Blue." + args[1] + ".Y", y);
					main.getConfig().addDefault("LostForceGames.Spawns.Blue." + args[1] + ".Z", z);
					main.getConfig().addDefault("LostForceGames.Spawns.Blue." + args[1] + ".Yaw", yaw);
					main.getConfig().addDefault("LostForceGames.Spawns.Blue." + args[1] + ".Pitch", pitch);
					main.saveConfig();

					player.sendMessage(main.prefix + "Spawn set for: " + ChatColor.BLUE + "blue");
					return true;

				} else {
					player.sendMessage(main.prefix + "Argument not found!");
					return true;
				}
			}
		}

		if (args[0].equalsIgnoreCase("join")) {

			List<String> list = main.getConfig().getStringList("LostForceGames.Maps");
			if (list.size() == 0) {
				player.sendMessage(main.prefix + "No Maps found!");
				return true;
			}

			if (main.teamblue.containsKey(player.getName()) || (main.teamred.containsKey(player.getName()))) {
				player.sendMessage(main.prefix + "You are already in a Game!");
				return true;
			}

			if (main.teambluesize >= main.teamredsize) {

				String mapname = main.acctualMap.get(0);

				double x = main.getConfig().getDouble("LostForceGames.Spawns.Red." + mapname + ".X");
				double y = main.getConfig().getDouble("LostForceGames.Spawns.Red." + mapname + ".Y");
				double z = main.getConfig().getDouble("LostForceGames.Spawns.Red." + mapname + ".Z");
				double yaw = main.getConfig().getDouble("LostForceGames.Spawns.Red." + mapname + ".Yaw");
				double pitch = main.getConfig().getDouble("LostForceGames.Spawns.Red." + mapname + ".Yaw");

				Location loc = new Location(player.getWorld(), x, y, z, (float) yaw, (float) pitch);

				player.teleport(loc);
				player.sendMessage(main.prefix + "You joined Team " + ChatColor.RED + "red");
				main.teamred.put(player.getName(), 1);
				return true;
			} else {

				String mapname = main.acctualMap.get(0);

				double x = main.getConfig().getDouble("LostForceGames.Spawns.Blue." + mapname + ".X");
				double y = main.getConfig().getDouble("LostForceGames.Spawns.Blue." + mapname + ".Y");
				double z = main.getConfig().getDouble("LostForceGames.Spawns.Blue." + mapname + ".Z");
				double yaw = main.getConfig().getDouble("LostForceGames.Spawns.Blue." + mapname + ".Yaw");
				double pitch = main.getConfig().getDouble("LostForceGames.Spawns.Blue." + mapname + ".Yaw");

				Location loc = new Location(player.getWorld(), x, y, z, (float) yaw, (float) pitch);

				player.teleport(loc);
				player.sendMessage(main.prefix + "You joined Team " + ChatColor.BLUE + "blue");
				main.teamblue.put(player.getName(), 1);
				return true;
			}

			// BLUE TEAM

		}

		if (args[0].equalsIgnoreCase("delarena")) {

			if (!(player.hasPermission("LostForceGames.delarena"))) {
				player.sendMessage(main.prefix + "You dont have Permissions");
				return true;
			}

			if (main.getConfig().getString("LostForceGames.Location_1." + args[1] + ".X") == null) {
				player.sendMessage(main.prefix + "Arena not found!");
				return true;
			} else {

				List<String> list = main.getConfig().getStringList("LostForceGames.Maps");

				list.remove(args[0]);

				main.getConfig().set("LostForceGames.Maps", null);
				main.getConfig().set("LostForceGames.Maps", list);

				main.getConfig().set("LostForceGames.Location_1." + args[1] + ".X", null);
				main.getConfig().set("LostForceGames.Location_1." + args[1] + ".Y", null);
				main.getConfig().set("LostForceGames.Location_1." + args[1] + ".Z", null);
				main.getConfig().set("LostForceGames.Location_2." + args[1] + ".X", null);
				main.getConfig().set("LostForceGames.Location_2." + args[1] + ".Y", null);
				main.getConfig().set("LostForceGames.Location_2." + args[1] + ".Z", null);
				main.getConfig().set("LostForceGames.Spawns.Red." + args[1] + ".X", null);
				main.getConfig().set("LostForceGames.Spawns.Red." + args[1] + ".Y", null);
				main.getConfig().set("LostForceGames.Spawns.Red." + args[1] + ".Z", null);
				main.getConfig().set("LostForceGames.Spawns.Red." + args[1] + ".Yaw", null);
				main.getConfig().set("LostForceGames.Spawns.Red." + args[1] + ".Pitch", null);
				main.getConfig().set("LostForceGames.Spawns.Blue." + args[1] + ".X", null);
				main.getConfig().set("LostForceGames.Spawns.Blue." + args[1] + ".Y", null);
				main.getConfig().set("LostForceGames.Spawns.Blue." + args[1] + ".Z", null);
				main.getConfig().set("LostForceGames.Spawns.Blue." + args[1] + ".Yaw", null);
				main.getConfig().set("LostForceGames.Spawns.Blue." + args[1] + ".Pitch", null);
				main.saveConfig();

				player.sendMessage(main.prefix + "Arena " + ChatColor.AQUA + args[1] + ChatColor.GRAY + " deleted!");
				return true;

			}
		}

		if (args[0].equalsIgnoreCase("clear")) {

			if (!(player.hasPermission("LostForceGames.clear"))) {
				player.sendMessage(main.prefix + "You dont have Permissions");
				return true;
			}

			if (args.length == 1) {

				if (main.creator.containsKey(player.getName())) {
					main.creator.remove(player.getName());
					player.sendMessage(main.prefix + "You got removed from the Creator List!");
					return true;
				} else {
					player.sendMessage(main.prefix + "You dont create an arena at the moment!");
					return true;
				}

			}
		}
		return false;
	}
}
