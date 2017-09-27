package com.mcndsj.lobby_Control;

import com.mcndsj.lobby_Control.rz.Command_rz;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.mcndsj.lobby_Control.maintenance.*;


import com.mcndsj.lobby_Control.chat.ChatManager;
import com.mcndsj.lobby_Control.functionalItems.PlayerHider;
import com.mcndsj.lobby_Control.gui.GameSelectGui;
import com.mcndsj.lobby_Control.protection.BlockListener;
import com.mcndsj.lobby_Control.protection.EntityListener;
import com.mcndsj.lobby_Control.protection.PlayerListener;

public class LobbyControl extends JavaPlugin {

	private static ChatManager cl ;
	private static PlayerManager pm;
	private static PlayerAFKController pafk;
	
	public static LobbyControl instance;
	
	public void onEnable(){
		instance = this;
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		cl = new ChatManager();
		pm = new PlayerManager();
		pafk = new PlayerAFKController();
		
		//GUI stuff
		new GameSelectGui();
		
		instance.getServer().getPluginManager().registerEvents(new BlockListener(), this);
		instance.getServer().getPluginManager().registerEvents(new EntityListener(), this);
		instance.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		instance.getServer().getPluginManager().registerEvents(new PlayerHider(), this);

		this.getServer().setDefaultGameMode(GameMode.ADVENTURE);
		
		for(Player p: Bukkit.getOnlinePlayers()){
			pm.getControlPlayer(p.getName());
		}

		getCommand("rz").setExecutor(new Command_rz());
		getServer().getWorld("lobby").setGameRuleValue("doDaylightCycle", "false");
	}
	public static ChatManager getChatManager(){
		return cl;
	}
	
	public static PlayerManager getPlayerManager(){
		return pm;
	}

	public static PlayerAFKController getPlayerAFKController(){
		return pafk;
	}
}
