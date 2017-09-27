package com.mcndsj.lobby_Control;

import com.mcndsj.lobby_Control.maintenance.ServerMaintainer;

/**
 * Created by Matthew on 13/06/2016.
 */
public class LobbyControlAPIs {

    public static void registerRestartPort(int startingPort){
        ServerMaintainer.create(startingPort);
    }
}
