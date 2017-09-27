package com.mcndsj.lobby_Control.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.mcndsj.lobby_Gui.Items.AbstractItem;
import com.mcndsj.lobby_Gui.Utils.ItemFactory;

public class GameSelectItem extends AbstractItem{
	
	private static ItemStack item = ItemFactory.create(Material.COMPASS, (byte)0, ChatColor.GOLD + "菜单-Menu", "右键打开菜单." ,"Right Click to open menu");
	
	public GameSelectItem() {
		super("选择服务器",45 ,Material.COMPASS, item.getItemMeta(),0);
		// TODO Auto-generated constructor stub
	}

	
}
