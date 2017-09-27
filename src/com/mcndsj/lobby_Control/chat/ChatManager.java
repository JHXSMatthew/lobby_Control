package com.mcndsj.lobby_Control.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mcndsj.lobby_Control.config.Messages;

public class ChatManager {

	private List<String> dic;
	private HashMap<String,String> helpMap;
	
	public ChatManager(){
		dic = new ArrayList<String>();
		helpMap = new HashMap<String,String>();
	}


	
	public String Filter(String s){
		String returnValue = s;
		Matcher m = null;
		for(String str : dic){
			Pattern p = Pattern.compile(str,Pattern.CASE_INSENSITIVE);
			StringBuffer sb = new StringBuffer();
			m = p.matcher(s);
			while(m.find()){
				m.appendReplacement(sb, "*");
			}
			returnValue = sb.toString();
		}
		return returnValue;
	}
	
	public void register(){
		helpMap.put("皮肤", Messages.NotifyPrefix + "服务器仅支持正版皮肤哦~");
		
		this.dic.add(".*(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}).*");
		this.dic.add("靠");
		this.dic.add("在.*那.*里");
		this.dic.add("有.*病");
		this.dic.add("灵.*车.*漂.*移");
		this.dic.add("傻.*逼");
		this.dic.add("傻.*比");
		this.dic.add("傻.*笔");
		this.dic.add("傻.*b.*i");
		this.dic.add("s.*h.*a.*逼");
		this.dic.add("煞.*逼");
		this.dic.add("傻.*B");
		this.dic.add("S.*B");
		this.dic.add("s.*b");
		this.dic.add("S.*b");
		this.dic.add("s.*B");
		this.dic.add("逗.*B");
		this.dic.add("吃.*屎");
		this.dic.add("垃.*圾");
		this.dic.add("草");
		this.dic.add("煞.*笔");
		this.dic.add("操");
		this.dic.add("你.*妈");
		this.dic.add("日");
		this.dic.add("滚.*蛋");
		this.dic.add("儿.*子");
		this.dic.add("爸.*爸");
		this.dic.add("孙.*子");
		this.dic.add("犯.*贱");
		this.dic.add("j.*b");
		this.dic.add("J.*B");
		this.dic.add("J.*b");
		this.dic.add("j.*B");
		this.dic.add("共.*产.*党");
		this.dic.add("[fF].*[uU].*[cC].+[kK]");
		this.dic.add("[fF].*[kK]");
		this.dic.add("护.*床");
		this.dic.add("猪");
		this.dic.add("呵.*呵");
		this.dic.add("战.*桥");
		this.dic.add("像.*素.*时.*光");
		this.dic.add("荆.*棘.*之.*路");
		this.dic.add("[Bb].*[Uu].*[Gg]");
		this.dic.add("开.*挂");
		this.dic.add("挂");
		this.dic.add("作.*弊");
		this.dic.add("狗.*娘.*养.*的");
		this.dic.add("狗");
		this.dic.add("战.*桥");
		this.dic.add("痿");
		this.dic.add("贱");
		this.dic.add("狗.*东.*西");
		this.dic.add("墓.*碑");
		this.dic.add("户.*口.*本");
		this.dic.add("坟.*头");
		this.dic.add("骨.*灰");
		this.dic.add("卡");
		this.dic.add("垃.*圾.*服");
		this.dic.add("反.*作.*弊");
		this.dic.add("屌");
		this.dic.add("脑.*门.*子");
		this.dic.add("屌");
		this.dic.add("地.*球.*都.*市");
		this.dic.add("[Ii].*[Pp]");
		this.dic.add("东.*方.*大陆.*");
		this.dic.add("勇.*者");
		this.dic.add("尼.*玛");
	}
}
