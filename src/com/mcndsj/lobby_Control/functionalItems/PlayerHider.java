package com.mcndsj.lobby_Control.functionalItems;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.mcndsj.lobby_Control.ControlPlayer;
import com.mcndsj.lobby_Control.LobbyControl;
import com.mcndsj.lobby_Control.config.Messages;
import com.mcndsj.lobby_Gui.Utils.ItemFactory;

public class PlayerHider implements Listener{
	

	@EventHandler
	public void handleClick(PlayerInteractEvent evt){
		if(evt.getItem() != null 
				&& evt.getItem().hasItemMeta() 
				&& evt.getItem().getItemMeta().getDisplayName().contains("隐藏玩家")){
			ControlPlayer cp = LobbyControl.getPlayerManager().getControlPlayer(evt.getPlayer().getName());
			if(cp.getLastUsePlayerHider() > System.currentTimeMillis()){
				evt.getPlayer().sendMessage(Messages.NotifyPrefix +ChatColor.GRAY + "您必须等待3秒后才可以使用.");
				return;
			}
			if(evt.getItem().getType() == Material.BLAZE_ROD){
				evt.getItem().setType(Material.BONE);
				cp.setHider();
				evt.getPlayer().sendMessage(Messages.NotifyPrefix +ChatColor.GRAY + "隐藏周围玩家.");
			}else{
				evt.getItem().setType(Material.BLAZE_ROD);
				cp.setHider();
				evt.getPlayer().sendMessage(Messages.NotifyPrefix +ChatColor.GRAY + "显示周围玩家.");
			}
			evt.getPlayer().playSound(evt.getPlayer().getLocation(), Sound.CLICK, 1, 1);
		}
	}
	
	@EventHandler
	public void handlePlayerJoin(PlayerJoinEvent evt){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(LobbyControl.getPlayerManager().getControlPlayer(p.getName()).isUsingPlayerHider()){
				p.hidePlayer(evt.getPlayer());
			}
		}
		evt.getPlayer().getInventory().setItem(8, ItemFactory.create(Material.BLAZE_ROD,(byte)0,ChatColor.GREEN + "隐藏玩家", "右键点击隐藏/显示玩家"));
	}
	
	

}
