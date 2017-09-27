package com.mcndsj.lobby_Control.maintenance;

import com.mcndsj.lobby_Control.LobbyControl;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Calendar;

/**
 * Created by Matthew on 13/06/2016.
 */
public class ServerMaintainer extends BukkitRunnable {

    private static int modifier = 1;

    private static int hour = 4;

    private int minutes = 0;

    private static ServerMaintainer ins;

    public static boolean create(int startingPort){
        if(ins != null)
            return false;

        ins = new ServerMaintainer(startingPort);
        ins.runTaskTimer(LobbyControl.instance,0,20);
        System.out.println("Server will shutting down at " + hour + ":" + (Bukkit.getPort() - startingPort));
        return true;
    }

    private ServerMaintainer(int startingPort){
        int port = Bukkit.getPort();
        if(startingPort < port){
            minutes = port - startingPort;
        }
    }
    @Override
    public void run() {
        Calendar c  = Calendar.getInstance();
        if(c.get(Calendar.HOUR_OF_DAY) == hour && c.get(Calendar.MINUTE) == minutes){
            System.out.println("Server maintenance schedule : " + hour + ":"+minutes + " shutting down....");
            Bukkit.shutdown();
        }
    }


}
