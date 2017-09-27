package com.mcndsj.lobby_Control.maintenance;

import com.mcndsj.lobby_Control.utils.BungeeUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;

/**
 * Created by Matthew on 13/06/2016.
 */
public class PlayerWatcher {
    private Player player;
    private Location l;
    private int count ; // in second
    private int left = 10;

    private static int limit = 600;



    public PlayerWatcher(Player p){
        l = p.getLocation();
        player = p;
    }

    public boolean valid(){
        return player != null && player.isOnline();
    }
    /**
     * being call every second
     * @return is same location
     */
    public boolean isSameLocation(){
        boolean is = player.getLocation().equals(l);
        l = player.getLocation();
        if(is) {
            count++;
        }else {
            count = 0;
            left = 10;
        }
        return is;
    }

    public boolean checkVoidSend(){
        return count > limit;
    }

    public void voidSend(){
        left --;
        if(left == 0){
            BungeeUtils.sendTo(this.player,"void");
            left = 10;
        }else{
            player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "您将在" + left + "秒后因挂机被移动出该大厅...");
            player.playSound(player.getLocation(), Sound.NOTE_PIANO,left/10,left/10);
        }
    }



}
