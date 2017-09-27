package com.mcndsj.lobby_Control.rz;

import org.bukkit.ChatColor;

/**
 * Created by Matthew on 11/07/2016.
 */
public enum RzType {
    bz(ChatColor.AQUA + "版主",11,"版主认证成功！",null),
    mm(ChatColor.RED + "妹子",5,"妹子认证成功,欢迎常来YourCraft小游戏哦！","mm"),
    zb(ChatColor.AQUA + "游戏主播",6,"您的游戏主播身份认证成功,感谢您入驻YourCraft小游戏,祝您在这里玩的愉快！","vip6"),
    skz(ChatColor.AQUA + "实况主",10,"您的实况主身份认证成功,感谢您入驻YourCraft小游戏,祝您在这里玩的愉快！","vip7");


    private String name;
    private int prefix_id;
    private String welcome;
    private String permissionGroup ;


    RzType(String name, int prefix_id , String welcome,String permissionGroup){
        this.name = name;
        this.prefix_id = prefix_id;
        this.welcome = welcome;
        this.permissionGroup = permissionGroup;
    }

    public String getName(){
        return this.name;
    }

    public int getPrefix_id(){
        return prefix_id;
    }

    public String getWelcome(){
        return welcome;
    }

    public String getPermissionGroup(){
        return permissionGroup;
    }
}
