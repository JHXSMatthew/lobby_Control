package com.mcndsj.lobby_Control;

import org.bukkit.plugin.java.JavaPlugin;

import com.mcndsj.lobby_Control.Chat.ChatManager;
import com.mcndsj.lobby_Control.Protection.Listener.BlockListener;
import com.mcndsj.lobby_Control.Protection.Listener.EntityListener;
import com.mcndsj.lobby_Control.Protection.Listener.PlayerListener;

public class LobbyControl extends JavaPlugin {

	private static ChatManager cl ;
	private static PlayerManager pm;
	
	public static LobbyControl instance;
	
	public void onEnable(){
		instance = this;
		cl = new ChatManager();
		pm = new PlayerManager();
		instance.getServer().getPluginManager().registerEvents(new BlockListener(), this);
		instance.getServer().getPluginManager().registerEvents(new EntityListener(), this);
		instance.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}
	public static ChatManager getChatManager(){
		return cl;
	}
	
	public static PlayerManager getPlayerManager(){
		return pm;
	}
}
