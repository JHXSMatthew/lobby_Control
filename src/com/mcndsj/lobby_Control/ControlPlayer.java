package com.mcndsj.lobby_Control;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

import org.bukkit.entity.Player;

public class ControlPlayer {
	private UUID uuid;
	private String name;
	private Player p ;
	
	public long lastChat = -1;
	private Queue<String> chatQueue;
	
	public ControlPlayer(UUID uuid,String name){
		this.uuid = uuid;
		this.name = name;
	
	}
	
	public void ConstructCall(Player p){
		this.p = p;
		chatQueue = new ArrayBlockingQueue<String>(4);
	}
	
	//@TODO: implements prefix 
	public String getPrefix(){
		return "PREFIX";
	}
	
	public long getLastChat(){
		return lastChat;
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
	
	public void setLastChat(String str, long time){
		this.lastChat = time;
		if(chatQueue.size() > 3){
			chatQueue.poll();
			chatQueue.add(str);
		}
	}
	
	public Player get(){
		return p;
	}
	
}
