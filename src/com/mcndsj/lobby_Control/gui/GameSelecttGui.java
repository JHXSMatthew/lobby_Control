package com.mcndsj.lobby_Control.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.mcndsj.lobby_Control.utils.BungeeUtils;
import com.mcndsj.lobby_Gui.Guis.AbstractGui;
import com.mcndsj.lobby_Gui.Utils.ItemFactory;


public class GameSelecttGui extends AbstractGui{

	public GameSelecttGui() {
		item = new GameSelectItem();
		register();
		setTitle(item.getTitle());
	}

	/*
	 * updator protocol 
	 * end_of_lore
	 * 
	 * " "
	 * "代币获取翻倍: chatcolor.gold[x]"
	 * "全游戏获取购买者: chatcolor.aqu[x]"
	 * " "
	 * "xxx 正在游戏"
	 */
	private void register(){
		getItem().registerItem(4, ItemFactory.create(Material.BOOKSHELF, (byte)0
				, ChatColor.GREEN + "主大厅", "回到主大厅,点击NPC加入其它游戏."));
		
		getItem().registerItem(10, ItemFactory.create(Material.STONE_AXE
				, (byte)0, ChatColor.GREEN + "战墙", ChatColor.DARK_GRAY+ "团队生存" ," "
				,"你有10分钟时间与你的队友","一起准备开启大战.","战墙倒塌后大战开始"
				, "非常适合喜欢原汁原味PVP的玩家.","  ","   ", ChatColor.BOLD +  ChatColor.BOLD.toString() + "NEW" + ChatColor.RED + "职业开放" ));

		getItem().registerItem(11, ItemFactory.create(Material.GOLDEN_APPLE
				, (byte)0, ChatColor.GREEN + "极限生存-UHC", ChatColor.DARK_GRAY+ "团队生存" ," "
				,"在一个不会自然恢复的世界里","与你的两名队友一起.","在百人中生存下来." , "边境会随时间不停的收缩.","强者为王的世界里", "等你来战！"
				,"  ",ChatColor.RED +  "非常不适合新手玩家." ));
		getItem().registerItem(12, ItemFactory.create(Material.BED
				, (byte)0, ChatColor.GREEN + "起床战争", ChatColor.DARK_GRAY+ "团队竞技" ," "
				,"你可以选择加入四队之一","收集资源从商人处换取道具.","在有限时间内与队友破坏敌人的床." , "并合作消灭他们!","", "只要你的床还没有被摧毁,", "你就可以无限复活.", "战斗吧!"
				,"  "));
		getItem().registerItem(13, ItemFactory.create(Material.COAL
				, (byte)0, ChatColor.GREEN + "CSGO", ChatColor.DARK_GRAY+ "枪战游戏" ," "
				,"刺激的枪战游戏","你可以选择成为匪徒或警察.","在不同的模式中有着不同的游戏玩法." , "爆破模式下,匪徒的目的是安放炸弹.","", "警察的目的是拆除炸弹,", "或者消灭敌对阵营也可以获得胜利!", "战斗吧!"
				,"  " ,ChatColor.RED +  "进入大厅后请接收材质包",ChatColor.RED +  "进入大厅后请接收材质包."  ));
	}
	@Override
	protected void callOnClick(Player p, ItemStack item) {
		String name = item.getItemMeta().getDisplayName();
		if(name.contains("主大厅")){
			BungeeUtils.sendTo(p, "lobby1");
		}else if(name.contains("战墙")){
			BungeeUtils.sendTo(p, "walllobby1");
		}else if(name.contains("极限生存-UHC")){
			BungeeUtils.sendTo(p, "uhclobby1");
		}else if(name.contains("起床战争")){
			BungeeUtils.sendTo(p, "bwlobby1");
		}else if(name.contains("CSGO")){
			BungeeUtils.sendTo(p, "csgolobby1");
		}
		
		
		
	}

}
