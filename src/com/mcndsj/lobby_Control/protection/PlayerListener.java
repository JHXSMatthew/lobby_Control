package com.mcndsj.lobby_Control.protection;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.mcndsj.lobby_Control.ControlPlayer;
import com.mcndsj.lobby_Control.LobbyControl;
import com.mcndsj.lobby_Control.config.Messages;
import com.mcndsj.lobby_Vip.LobbyVip;

public class PlayerListener implements Listener{
    private ExecutorService pool = null;
    
    public PlayerListener(){
        pool = Executors.newSingleThreadExecutor();
        new BukkitRunnable(){
        	int x = (int) Bukkit.getWorld("lobby").getSpawnLocation().getX();;
        	int z = (int) Bukkit.getWorld("lobby").getSpawnLocation().getZ();
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()){
					if(p.getLocation().getX() > x + 200 || p.getLocation().getX() < x - 200 || p.getLocation().getZ() > z + 200 || p.getLocation().getZ() < z - 200 ){
						new BukkitRunnable(){
							public void run(){
								 Location l = p.getWorld().getSpawnLocation().clone();
								 Random r = new Random();
								 l.add(r.nextInt(6) - 3, -8, r.nextInt(6) - 3);
								 p.teleport(l);
							}
						}.runTask(LobbyControl.instance);
					}
				}
			}
        	
        }.runTaskTimerAsynchronously(LobbyControl.instance, 0, 20);
    }




	@EventHandler(priority = EventPriority.MONITOR)
	public void onPreJoin(PlayerLoginEvent evt){
		if(evt.getResult() != PlayerLoginEvent.Result.ALLOWED){
            return;
        }
        pool.execute(new Runnable() {
            public void run() {
        		LobbyControl.getPlayerManager().createControlPlayer(evt.getPlayer().getUniqueId(), evt.getPlayer().getName());
            }
        });
	}

	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent evt){
		evt.setQuitMessage("");
		LobbyControl.getPlayerManager().removeControlPlayer(evt.getPlayer().getName());
	}
	
	@EventHandler
	public void handleItemPickUp(PlayerPickupItemEvent evt){
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
			if(!evt.getPlayer().hasPermission("lobby.admin")){
				evt.setCancelled(true);
			}
		}
	}
	
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onInteractEvent(PlayerInteractEvent evt){
		if(!evt.getPlayer().hasPermission("lobby.admin")){
			evt.setCancelled(true);
		}
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
		 LobbyControl.getPlayerManager().getControlPlayer(evt.getPlayer().getName()).ConstructCall(p);;
		 
		 Location l = p.getWorld().getSpawnLocation().clone();
		 Random r = new Random();
		 l.add(r.nextInt(6) - 3, 0, r.nextInt(6) - 3);
		 p.teleport(l);
		 p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
		 
		 p.getInventory().setArmorContents(null);
		 p.getInventory().clear();
		 p.setGameMode(GameMode.ADVENTURE);
		 for(int i=0;i<50;i++){
			p.sendMessage("");
		 }
		 p.sendMessage("§6=====================================================");
		 p.sendMessage("      §c§l欢迎来到Yourcraft小游戏服~");
	 	 p.sendMessage("        §b§l秉承着 §4§l公平公正§b§l 的理念做中国的小游戏服！");
		 p.sendMessage("           §b§l我们致力于将国外万人服体验带给国内玩家！");
		 p.sendMessage("§6=====================================================");
		 evt.setJoinMessage("");
		 
		 if(LobbyVip.getApi().isVip(evt.getPlayer().getName())){
			 evt.getPlayer().setAllowFlight(true);
			 evt.getPlayer().setFlying(true);
		 }
		try {
			LobbyControl.getPlayerAFKController().addtoWatchList(evt.getPlayer());
		}catch (NullPointerException e){

		}
	}
	

	//ChatLimit
	@EventHandler(priority = EventPriority.LOWEST)
	public void onChatEvent(AsyncPlayerChatEvent evt){
		Player p = evt.getPlayer();
		ControlPlayer cp = LobbyControl.getPlayerManager().getControlPlayer(p.getName());
		if(!LobbyVip.getApi().isVip(p.getName())){
			if(cp.getLastChat() > System.currentTimeMillis()){
				p.sendMessage(ChatColor.YELLOW +"距离您上次发言还不足3秒,为了 避免刷屏嫌疑,请您稍后再试!");
				p.sendMessage(ChatColor.YELLOW +"成为我们的 " + ChatColor.GREEN + "会员" + ChatColor.YELLOW + "可以免除该限制!");
				evt.setCancelled(true);
				return;
			}
			if(!cp.isChatAllow(evt.getMessage())){
				p.sendMessage(ChatColor.YELLOW +"您的近几次发言具有重复性,请勿重复刷屏!");
				p.sendMessage(ChatColor.YELLOW +"成为我们的 " + ChatColor.GREEN + "会员" + ChatColor.YELLOW + "可以免除该限制!");
				evt.setCancelled(true);
				return;
			}
		}
		
		if(evt.getMessage().equals("1")){
			p.sendMessage(Messages.NotifyPrefix + "您没有掉线...");
			evt.setCancelled(true);
			return;
		}
		
		cp.setLastChat(evt.getMessage(), System.currentTimeMillis() + 4000);
		evt.setMessage(LobbyControl.getChatManager().Filter(evt.getMessage()));
		evt.setFormat("0 " + ChatColor.translateAlternateColorCodes('&', cp.getPrefix().replace("&l", ""))  + "%s "+ ChatColor.GOLD + ">>"+ (LobbyVip.getApi().isVip(p.getName())?ChatColor.WHITE:ChatColor.GRAY) + " %s");
	}
}
