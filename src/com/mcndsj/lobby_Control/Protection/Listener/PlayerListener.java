package com.mcndsj.lobby_Control.Protection.Listener;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.mcndsj.lobby_Control.ControlPlayer;
import com.mcndsj.lobby_Control.LobbyControl;
import com.mcndsj.lobby_Control.Config.Messages;
import com.mcndsj.lobby_Vip.LobbyVip;

public class PlayerListener implements Listener{

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPreLogin(AsyncPlayerPreLoginEvent evt){
		LobbyControl.getPlayerManager().createControlPlayer(evt.getUniqueId(), evt.getName());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent evt){
		LobbyControl.getPlayerManager().removeControlPlayer(evt.getPlayer().getName());
	}
	
	@EventHandler
	public void handleItemPickUp(PlayerPickupItemEvent  evt){
		evt.getItem().remove();
		evt.setCancelled(true);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void noItemMove (InventoryClickEvent evt){
		
		if(evt.getView().getPlayer() instanceof Player){
			if(!((Player)evt.getView().getPlayer()).hasPermission("lobby.admin")){
				evt.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent evt){
		if(evt.getRightClicked().getType() == EntityType.ITEM_FRAME){
			evt.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onInteractEvent(PlayerInteractEvent evt){
		evt.setCancelled(true);
	}
	
	@EventHandler
	public void noHunger(FoodLevelChangeEvent evt){
		evt.setCancelled(true);
	}
	@EventHandler
	public void handleItemDrop(PlayerDropItemEvent evt){
		evt.setCancelled(true);
	}
	
	
	@EventHandler
	public void onLogin(PlayerLoginEvent evt){
		if(evt.getResult() != PlayerLoginEvent.Result.ALLOWED){
			if(evt.getPlayer().hasPermission("lobby.fulljoin")){
				evt.allow();
			}	
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent evt){
		 Player p = evt.getPlayer();
		 p.teleport(p.getWorld().getSpawnLocation());
		 p.getInventory().setArmorContents(null);
		 p.getInventory().clear();
		 p.setGameMode(GameMode.ADVENTURE);
		 for(int i=0;i<50;i++){
			p.sendMessage("");
		 }
		 p.sendMessage("��6=====================================================");
		 p.sendMessage("      ��c��l��ӭ����YourcraftС��Ϸ��~");
	 	 p.sendMessage("        ��b��l������ ��4��l��ƽ������b��l ���������й���С��Ϸ����");
		 p.sendMessage("           ��b��l���������ڽ��������˷��������������ң�");
		 p.sendMessage("��6=====================================================");
	}
	

	//ChatLimit
	@EventHandler(priority = EventPriority.LOWEST)
	public void onChatEvent(AsyncPlayerChatEvent evt){
		Player p = evt.getPlayer();
		ControlPlayer cp = LobbyControl.getPlayerManager().getControlPlayer(p.getName());
		if(!LobbyVip.getApi().isVip(p.getName())){
			if(cp.getLastChat() > System.currentTimeMillis()){
				p.sendMessage(ChatColor.YELLOW +"�������ϴη��Ի�����3��,Ϊ�� ����ˢ������,�����Ժ�����!");
				p.sendMessage(ChatColor.YELLOW +"��Ϊ���ǵ� " + ChatColor.GREEN + "��Ա" + ChatColor.YELLOW + "�������������!");
				evt.setCancelled(true);
				return;
			}
			if(cp.isChatAllow(evt.getMessage())){
				p.sendMessage(ChatColor.YELLOW +"���Ľ����η��Ծ����ظ���,�����ظ�ˢ��!");
				p.sendMessage(ChatColor.YELLOW +"��Ϊ���ǵ� " + ChatColor.GREEN + "��Ա" + ChatColor.YELLOW + "�������������!");
				evt.setCancelled(true);
				return;
			}
		}
		
		if(evt.getMessage().equals("1")){
			p.sendMessage(Messages.NotifyPrefix + "��û�е���...");
			evt.setCancelled(true);
			return;
		}
		
		cp.setLastChat(evt.getMessage(), System.currentTimeMillis() + 4000);
		evt.setMessage(LobbyControl.getChatManager().Filter(evt.getMessage()));
		evt.setFormat("0 " + cp.getPrefix() + "%s "+ ChatColor.GOLD + ">> %s");
	}
}
