package com.mcndsj.lobby_Control.maintenance;

import com.mcndsj.lobby_Control.LobbyControl;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Matthew on 13/06/2016.
 */
public class PlayerAFKController extends BukkitRunnable{

    private List<PlayerWatcher> watcherList;

    public PlayerAFKController(){
        watcherList = new ArrayList<>();
        runTaskTimer(LobbyControl.instance,0,20);
    }

    public void addtoWatchList(Player p){
        watcherList.add(new PlayerWatcher(p));
    }

    @Override
    public void run() {
        Iterator<PlayerWatcher> iterator = watcherList.iterator();
        while(iterator.hasNext()){
            PlayerWatcher watcher = iterator.next();
            if(!watcher.valid()){
                iterator.remove();
                continue;
            }
            //void send part
            if(watcher.isSameLocation())
                if(watcher.checkVoidSend())
                    watcher.voidSend();

        }
    }
}
