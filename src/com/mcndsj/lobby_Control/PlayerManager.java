package com.mcndsj.lobby_Control;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;

public class PlayerManager {
	private ConcurrentHashMap<String,ControlPlayer> hash = new ConcurrentHashMap<String,ControlPlayer>();
	

	public ControlPlayer getControlPlayer(String name){
		ControlPlayer lp = hash.get(name);
	
		return lp != null? lp : createControlPlayer(Bukkit.getPlayer(name).getUniqueId(),name);
	}
	
	
	public ControlPlayer createControlPlayer(UUID uuid ,String name){
		ControlPlayer lp = new ControlPlayer(uuid,name);
		hash.put(name, lp);
		return lp;
	}
	
	public void removeControlPlayer(String name){
		hash.remove(name);
	}
	

}
