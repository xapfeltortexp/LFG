package lostForceGames.arena;

import lostForceGames.LostForceGames;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ArenaListener implements Listener {
	
	public LostForceGames main;
	
	public ArenaListener(LostForceGames main) {
		this.main = main;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		final Player player = event.getPlayer();
		
		if(player.getInventory().getItemInHand().getType() != Material.GOLD_AXE) {
			return;
		}
		
		if(!(player.hasPermission("LostForceGames.create"))) {
			return;
		}
		
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onIntract(PlayerInteractEvent event) {
		
		final Player player = event.getPlayer();
		final Action action = event.getAction();

		if(action == Action.RIGHT_CLICK_BLOCK 
				&& (player.hasPermission("LostForceGames.create")) 
				&& player.getInventory().getItemInHand().getType() == Material.GOLD_AXE) {
			
			    if(!main.creator.containsKey(player.getName())) {
			    	player.sendMessage(main.prefix + "You forgot to define the Arena Name");
			    	return;
			    }
				
				int x = (int) event.getClickedBlock().getLocation().getX();
				int y = (int) event.getClickedBlock().getLocation().getY();
				int z = (int) event.getClickedBlock().getLocation().getZ();
				
				String key = main.creator.get(player.getName());
				
				player.sendMessage(main.prefix + "First Point set for " + ChatColor.AQUA + key);
				
				main.getConfig().addDefault("LostForceGames.Location_1." + key + ".X", x);
				main.getConfig().addDefault("LostForceGames.Location_1." + key + ".Y", y);
				main.getConfig().addDefault("LostForceGames.Location_1." + key + ".Z", z);
				main.saveConfig();
			}
	
		if(action == Action.LEFT_CLICK_BLOCK 
				&& (player.hasPermission("LostForceGames.create")) 
				&& player.getInventory().getItemInHand().getType() == Material.GOLD_AXE) {
			
		    	if(!main.creator.containsKey(player.getName())) {
		    		player.sendMessage(main.prefix + "You forgot to define the Arena Name");
		    		return;
		    	}
				
				int x = (int) event.getClickedBlock().getLocation().getX();
				int y = (int) event.getClickedBlock().getLocation().getY();
				int z = (int) event.getClickedBlock().getLocation().getZ();
				
				String key = main.creator.get(player.getName());
				
				player.sendMessage(main.prefix + "Second Point set for " + ChatColor.AQUA + key);
				
				main.getConfig().addDefault("LostForceGames.Location_2." + key + ".X", x);
				main.getConfig().addDefault("LostForceGames.Location_2." + key + ".Y", y);
				main.getConfig().addDefault("LostForceGames.Location_2." + key + ".Z", z);
				main.saveConfig();
			}
	}

}
