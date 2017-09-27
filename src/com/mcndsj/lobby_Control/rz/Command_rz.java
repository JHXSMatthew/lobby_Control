package com.mcndsj.lobby_Control.rz;

import com.mcndsj.Lobby_Display.Lobby_Display;
import com.mcndsj.lobby_Control.config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * Created by Matthew on 11/07/2016.
 */
public class Command_rz implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.hasPermission("lobby.admin"))
            return true;

        if(strings.length < 2){
            printHelp(commandSender);
            return true;
        }
        String type = strings[0];
        String name = strings[1];
        Player p = Bukkit.getPlayer(name);

        if (p == null) {
            commandSender.sendMessage(name + " 不在线!");
            return true;
        }

        RzType t = null;
        for(RzType rz : RzType.values()){
            if(rz.name().toLowerCase().equals(type)){
                t = rz;
                break;
            }
        }
        if(t == null){
            commandSender.sendMessage("这种认证不存在");
            return true;
        }

        if(t.getPermissionGroup() != null) {
            PermissionUser user = PermissionsEx.getUser(p);
            user.addGroup(t.getPermissionGroup());
        }
        Lobby_Display.getInstance().getApi().addPrefix(p,t.getPrefix_id());
        p.sendMessage(Messages.NotifyPrefix + t.getWelcome());
        p.sendMessage(Messages.NotifyPrefix + " 使用物品栏你的头颅中的称号簿可以更换自己的称号!");
        commandSender.sendMessage(Messages.NotifyPrefix + p.getDisplayName() +  ChatColor.GRAY + " 的 " + t.getName()+  ChatColor.GRAY + " 认证成功!");

        return true;
    }

    private void printHelp(CommandSender commandSender) {
        commandSender.sendMessage("=====================================");
        for(RzType type :RzType.values()){
            commandSender.sendMessage(type.getName() + " " + type.name().toLowerCase());
        }
        commandSender.sendMessage("格式/rz 英文缩写 玩家名称");
        commandSender.sendMessage("=====================================");
    }
}
