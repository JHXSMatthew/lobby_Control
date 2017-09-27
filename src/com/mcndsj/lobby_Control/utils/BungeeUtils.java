package com.mcndsj.lobby_Control.utils;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mcndsj.lobby_Control.LobbyControl;

public class BungeeUtils {
	public static void sendTo(Player p ,String serverName){
		 ByteArrayDataOutput out = ByteStreams.newDataOutput();
		 out.writeUTF("Connect");
		 out.writeUTF(serverName);
		 p.sendPluginMessage(LobbyControl.instance, "BungeeCord", out.toByteArray());
	}
}
