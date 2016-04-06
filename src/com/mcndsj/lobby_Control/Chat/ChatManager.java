package com.mcndsj.lobby_Control.Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mcndsj.lobby_Control.Config.Messages;

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
		helpMap.put("Ƥ��", Messages.NotifyPrefix + "��������֧������Ƥ��Ŷ~");
		
		this.dic.add(".*(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}).*");
		this.dic.add("��");
		this.dic.add("��.*��.*��");
		this.dic.add("��.*��");
		this.dic.add("��.*��.*Ư.*��");
		this.dic.add("ɵ.*��");
		this.dic.add("ɵ.*��");
		this.dic.add("ɵ.*��");
		this.dic.add("ɵ.*b.*i");
		this.dic.add("s.*h.*a.*��");
		this.dic.add("ɷ.*��");
		this.dic.add("ɵ.*B");
		this.dic.add("S.*B");
		this.dic.add("s.*b");
		this.dic.add("S.*b");
		this.dic.add("s.*B");
		this.dic.add("��.*B");
		this.dic.add("��.*ʺ");
		this.dic.add("��.*��");
		this.dic.add("��");
		this.dic.add("ɷ.*��");
		this.dic.add("��");
		this.dic.add("��.*��");
		this.dic.add("��");
		this.dic.add("��.*��");
		this.dic.add("��.*��");
		this.dic.add("��.*��");
		this.dic.add("��.*��");
		this.dic.add("��.*��");
		this.dic.add("j.*b");
		this.dic.add("J.*B");
		this.dic.add("J.*b");
		this.dic.add("j.*B");
		this.dic.add("��.*��.*��");
		this.dic.add("[fF].*[uU].*[cC].+[kK]");
		this.dic.add("[fF].*[kK]");
		this.dic.add("��.*��");
		this.dic.add("��");
		this.dic.add("��.*��");
		this.dic.add("ս.*��");
		this.dic.add("��.*��.*ʱ.*��");
		this.dic.add("��.*��.*֮.*·");
		this.dic.add("[Bb].*[Uu].*[Gg]");
		this.dic.add("��.*��");
		this.dic.add("��");
		this.dic.add("��.*��");
		this.dic.add("��.*��.*��.*��");
		this.dic.add("��");
		this.dic.add("ս.*��");
		this.dic.add("��");
		this.dic.add("��");
		this.dic.add("��.*��.*��");
		this.dic.add("Ĺ.*��");
		this.dic.add("��.*��.*��");
		this.dic.add("��.*ͷ");
		this.dic.add("��.*��");
		this.dic.add("��");
		this.dic.add("��.*��.*��");
		this.dic.add("��.*��.*��");
		this.dic.add("��");
		this.dic.add("��.*��.*��");
		this.dic.add("��");
		this.dic.add("��.*��.*��.*��");
		this.dic.add("[Ii].*[Pp]");
		this.dic.add("��.*��.*��½.*");
		this.dic.add("��.*��");
		this.dic.add("��.*��");
	}
}
