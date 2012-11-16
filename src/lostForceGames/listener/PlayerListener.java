package lostForceGames.listener;

import java.util.HashMap;

import lostForceGames.LostForceGames;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerListener implements Listener {

	public LostForceGames main;

	public PlayerListener(LostForceGames main) {
		this.main = main;
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {

		if ((event.getEntity() instanceof Player) && (event.getDamager() instanceof Player)) {

			final Player player = (Player) event.getEntity();
			final Player daplayer = (Player) event.getDamager();

			if (!main.teamblue.containsKey(player.getName()) || !main.teamred.containsKey(player.getName()) || !main.teamblue.containsKey(daplayer.getName()) || !main.teamred.containsKey(daplayer.getName())) {
				return;
			}

			if (main.teamblue.containsKey(daplayer.getName()) && main.teamblue.containsKey(player.getName())) {

				player.sendMessage(ChatColor.RED + "Damage missed! Target is in your Team!");
				event.setCancelled(true);
				return;
			}

			if (main.teamred.containsKey(daplayer.getName()) && main.teamred.containsKey(player.getName())) {
				player.sendMessage(ChatColor.RED + "Damage missed! Target is in your Team!");
				event.setCancelled(true);
				return;
			}
			
			if(isNotInMyTeam(main.teamblue, main.teamred, daplayer.getName(), player.getName()) == true) {
			
				player.sendMessage(main.prefix + "Right. Your target is NOT in your team");
				return;
			}

		}

	}
	
	public boolean isNotInMyTeam(HashMap<String, Integer> teamblue, HashMap<String, Integer> teamred, String player, String otherPlayer) {
		
		if(teamblue.containsKey(player) && !teamblue.containsKey(otherPlayer)) {
			return true;
		}
		
		return false;
	}

}
