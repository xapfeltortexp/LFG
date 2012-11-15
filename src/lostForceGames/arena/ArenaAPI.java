package lostForceGames.arena;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import lostForceGames.LostForceGames;

public class ArenaAPI {

	public LostForceGames main;

	public ArenaAPI(LostForceGames main) {
		this.main = main;
	}

	public boolean isInArea(Player player, Location loc1, Location loc2) {
		double[] ecke = new double[2];

		ecke[0] = loc1.getX();
		ecke[1] = loc2.getX();
		Arrays.sort(ecke);
		if (player.getLocation().getX() > ecke[1] || player.getLocation().getX() < ecke[0])
			return false;

		ecke[0] = loc1.getZ();
		ecke[1] = loc2.getZ();
		Arrays.sort(ecke);
		if (player.getLocation().getZ() > ecke[1] || player.getLocation().getZ() < ecke[0])
			return false;

		ecke[0] = loc1.getY();
		ecke[1] = loc2.getY();
		Arrays.sort(ecke);
		if (player.getLocation().getY() > ecke[1] || player.getLocation().getY() < ecke[0])
			return false;

		return true;
	}

	public boolean isGameEmpty(ArrayList<String> arrayList) {

		if (arrayList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public String getAcctualArena(ArrayList<String> arrayList) {

		String game = arrayList.get(0);

		return game;
	}

	public int getMapSize() {

		int size = main.list.size();

		return size;
	}

}
