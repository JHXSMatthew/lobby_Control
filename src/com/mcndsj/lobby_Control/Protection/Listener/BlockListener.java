package com.mcndsj.lobby_Control.Protection.Listener;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class BlockListener implements Listener{
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBurn(BlockBurnEvent evt) {
		evt.setCancelled(true);
	}
	
	@EventHandler
	public void onSpread(BlockSpreadEvent evt) {
		evt.setCancelled(true);
	}
	
	@EventHandler
	public void onFade(BlockFadeEvent evt) {
	   evt.setCancelled(true);
	}
	
	@EventHandler
	public void onIgnite(BlockIgniteEvent evt) {
		evt.setCancelled(true);
	}
	
	@EventHandler
	public void noWeather(WeatherChangeEvent evt){
		if(evt.toWeatherState()){
			evt.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onSnowChange(EntityBlockFormEvent evt){
		if(evt.getEntity().getType().equals(EntityType.SNOWMAN)){
			evt.getEntity().remove();
			evt.setCancelled(true);
		}
	}
	
	@EventHandler (priority = EventPriority.LOWEST)
	public void onExplode(EntityExplodeEvent evt){
		if(evt.isCancelled()){
			return;
		}
		evt.setCancelled(true);
	}
	
	@EventHandler
	public void noPlace(BlockPlaceEvent evt){
		if(evt.getPlayer().getGameMode() == GameMode.CREATIVE && evt.getPlayer().hasPermission("lobby.admin")){
			return;
		}
		evt.setCancelled(true);
	}
	
	@EventHandler
	public void noBreak(BlockBreakEvent evt){
		if(evt.getPlayer().getGameMode() == GameMode.CREATIVE && evt.getPlayer().hasPermission("lobby.admin")){
			return;
		}
		evt.setCancelled(true);
	}
}
