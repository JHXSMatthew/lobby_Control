package com.mcndsj.lobby_Control.Protection.Listener;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EntityListener implements Listener{
	
	@EventHandler
	public void onAnimalJump(EntityInteractEvent evt){
		if (evt.isCancelled()) {
		     return;
		}
	    if (evt.getEntityType() == EntityType.PLAYER) {
	      return;
	    }
	    if (evt.getBlock().getType() == Material.SOIL){
	    	evt.setCancelled(true);
	    }
	}
	
	@EventHandler
	public void onJump(PlayerInteractEvent evt){
	    if ((evt.getAction() == Action.PHYSICAL) && 
	      (evt.getClickedBlock().getType() == Material.SOIL))
	      evt.setCancelled(true);
	}
	
	@EventHandler
	public void preventSpawn(CreatureSpawnEvent evt){
		if(evt.getSpawnReason() == CreatureSpawnEvent.SpawnReason.EGG
				|| evt.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL
				|| evt.getSpawnReason() == SpawnReason.BREEDING){
			if(evt.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG){
				return;
			}
			evt.setCancelled(true);
		}
	}
	

	@EventHandler
	public void voidTeleport(EntityDamageEvent evt){
		
		evt.setCancelled(true);
		
		if(evt.getCause() == DamageCause.VOID){
			evt.getEntity().teleport(evt.getEntity().getWorld().getSpawnLocation());
		}
	}
	

}
