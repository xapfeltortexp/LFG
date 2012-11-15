package lostForceGames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import lostForceGames.arena.ArenaListener;
import lostForceGames.commands.CommandCreate;
import lostForceGames.commands.CommandEnable;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class LostForceGames extends JavaPlugin {
	
	public HashMap<String, String> creator = new HashMap<String, String>();
	public ArrayList<String> gamers = new ArrayList<String>();
	
	public String prefix = ChatColor.AQUA + "[" + ChatColor.DARK_GRAY + ChatColor.BOLD + "LF" + ChatColor.DARK_AQUA + ChatColor.BOLD + "Games" + ChatColor.AQUA + "] " + ChatColor.GRAY;
	
	ArenaListener al;
	
	public CommandCreate cmd;
	public CommandEnable cmde;
	
	public boolean enable;
	
	public void onEnable() {
		
		load_Conifg();
		
		ArenaListener al = new ArenaListener(this);
		getServer().getPluginManager().registerEvents(al, this);
		
		getLogger().log(Level.SEVERE, "[LostForceGames] Plugin successful Enabled!");
		
		cmd = new CommandCreate(this);
		cmde = new CommandEnable(this);
		getCommand("lfg").setExecutor(cmde);
		getCommand("lfg").setExecutor(cmd);
		
		enable = true;
		
	}

	public void onDisable() {

		getLogger().log(Level.SEVERE, "[LostForceGames] Plugin successful disabled!");

	}
	
	public void load_Conifg() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

}
