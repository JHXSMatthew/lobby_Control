package com.mcndsj.lobby_Control;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.mcndsj.Lobby_Display.Lobby_Display;

public class ControlPlayer {
	private UUID uuid;
	private String name;
	private Player p ;
	
	private boolean isUsingPlayerHider = false;
	private long lastUsePlayerHider = -1;
	
	private long lastChat = -1;
	private List<String> chatQueue;
	
	
	
	public ControlPlayer(UUID uuid,String name){
		this.uuid = uuid;
		this.name = name;
		
	}
	
	public void ConstructCall(Player p){
		this.p = p;
		chatQueue = new ArrayList<String>();
	}
	
	public String getPrefix(){
		return Lobby_Display.getInstance().getApi().getPrefix(p);
	}
	
	public long getLastChat(){
		return lastChat;
	}
	
	
	public long getLastUsePlayerHider(){
		return lastUsePlayerHider;
	} 
	public void setHider(){
		if(isUsingPlayerHider){
			isUsingPlayerHider = false;
			for(Player p : Bukkit.getOnlinePlayers()){
				if(p != this.p){
					this.p.showPlayer(p);
				}
			}
		}else{
			isUsingPlayerHider = true;
			for(Player p : Bukkit.getOnlinePlayers()){
				if(p != this.p){
					this.p.hidePlayer(p);
				}
			}
		}
		this.lastUsePlayerHider = System.currentTimeMillis() + 1000 * 3;
	}
	
	public boolean isUsingPlayerHider(){
		return this.isUsingPlayerHider;
	}
	
	public boolean isChatAllow(String str){
		for(String s : chatQueue){
			if(str.equals(s) || 
					str.substring(0, str.length() - 2) .equals(s)){
				return false;
			}
		}
		return true;
	}
	
	public String getName(){
		return this.name;
	}
	
	public UUID getUUid(){
		return this.uuid;
	}
	
	public void setLastChat(String str, long time){
		this.lastChat = time;
		if(chatQueue.size() > 3){
			chatQueue.remove(0);
			chatQueue.add(str);
		}
	}
	
	public Player get(){
		return p;
	}
	
}
