package lostForceGames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import lostForceGames.arena.ArenaAPI;
import lostForceGames.arena.ArenaListener;
import lostForceGames.commands.CommandCreate;
import lostForceGames.listener.PlayerListener;

import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class LostForceGames extends JavaPlugin {
	
	public HashMap<String, String> creator = new HashMap<String, String>();
	public ArrayList<String> acctualMap = new ArrayList<String>();
	
	public HashMap<String, Integer> teamred = new HashMap<String, Integer>();
	public HashMap<String, Integer> teamblue = new HashMap<String, Integer>();
	public HashMap<String, Inventory> Inv_Items = new HashMap<String, Inventory>();
	public HashMap<String, Inventory> Inv_Armor = new HashMap<String, Inventory>(); 
	
	public int teamredsize;
	public int teambluesize;
	
	public String prefix = ChatColor.AQUA + "[" + ChatColor.DARK_GRAY + ChatColor.BOLD + "LF" + ChatColor.DARK_AQUA + ChatColor.BOLD + "Games" + ChatColor.AQUA + "] " + ChatColor.GRAY;
	
	ArenaListener al;
	PlayerListener pl;
	
	public CommandCreate cmd;
	
	public ArenaAPI api;
	
	public boolean enable;
	
	public void onEnable() {
		
		load_Conifg();
		
		ArenaListener al = new ArenaListener(this);
		PlayerListener pl = new PlayerListener(this);
		getServer().getPluginManager().registerEvents(pl, this);
		getServer().getPluginManager().registerEvents(al, this);
		
		getLogger().log(Level.SEVERE, "[LostForceGames] Plugin successful Enabled!");
		
		cmd = new CommandCreate(this);
		
		api = new ArenaAPI(this);
		
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
