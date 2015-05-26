package com.sg.syj.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sg.syj.common.constant.Constants;

public class Common {

	public static String getMessage(String message, String param) {

		message = message.replace(Constants.CON_MSG_REPLACE1, param);
		return message;
	}

	public static String getMessage(String message, String param1, String param2) {

		message = message.replace(Constants.CON_MSG_REPLACE1, param1);
		message = message.replace(Constants.CON_MSG_REPLACE2, param2);
		return message;
	}

	public static String getMessage(String message, String param1, String param2,
			String param3) {

		message = message.replace(Constants.CON_MSG_REPLACE1, param1);
		message = message.replace(Constants.CON_MSG_REPLACE2, param2);
		message = message.replace(Constants.CON_MSG_REPLACE3, param3);
		return message;
	}
	
	public static String getSqlLikeEscapeStr(String str){
		
		str = str.replaceAll("/", "//");
		str = str.replaceAll("%", "/%");
		str = str.replaceAll("_", "/_");
		
		return str;
	}
	
	
	public static Map<String,String> getNavtionTypeMap(){
		Map<String,String> map = new HashMap<String,String>();
		map.put(Constants.MENUTYPE_ACTIVITY,"列表详情模板");
		map.put(Constants.MENUTYPE_SERVICE, "图文列表模板");
		map.put(Constants.MENUTYPE_MEDIA, "文字列表模板");
		map.put(Constants.MENUTYPE_BUYERS, "12图排列模板");
		map.put(Constants.MENUTYPE_ABOUT, "进口展模板1");
		map.put(Constants.MENUTYPE_ABOUT2, "文本详情模板");
		map.put(Constants.MENUTYPE_CPZS, "展品分类模板");
		map.put(Constants.MENUTYPE_DOMLOAD, "资料下载模板");
		map.put(Constants.MENUTYPE_CONTACT, "二维码模板 ");
		return map;
	}
	
	
	public static List<Map<String, String>> getNavtionType(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			Map<String,String> map = new HashMap<String,String>();
			map.put("type","列表详情模板");
			map.put("id",Constants.MENUTYPE_ACTIVITY);
			Map<String,String> map1 = new HashMap<String,String>();
			map1.put("id",Constants.MENUTYPE_SERVICE);
			map1.put("type", "图文列表模板");
			Map<String,String> map2 = new HashMap<String,String>();
			map2.put("id",Constants.MENUTYPE_MEDIA);
			map2.put("type", "文字列表模板");
			Map<String,String> map3 = new HashMap<String,String>();
			map3.put("id",Constants.MENUTYPE_BUYERS);
			map3.put("type", "12图排列模板");
			Map<String,String> map4 = new HashMap<String,String>();
			map4.put("id",Constants.MENUTYPE_ABOUT);
			map4.put("type", "进口展模板1");
			Map<String,String> map5 = new HashMap<String,String>();
			map5.put("id",Constants.MENUTYPE_ABOUT2);
			map5.put("type", "文本详情模板");
			Map<String,String> map6 = new HashMap<String,String>();
			map6.put("id",Constants.MENUTYPE_CPZS);
			map6.put("type", "展品分类模板");
			Map<String,String> map7 = new HashMap<String,String>();
			map7.put("id",Constants.MENUTYPE_DOMLOAD);
			map7.put("type", "资料下载模板");
			Map<String,String> map8 = new HashMap<String,String>();
			map8.put("id",Constants.MENUTYPE_CONTACT);
			map8.put("type", "二维码模板 ");
			list.add(map);
			list.add(map1);
			list.add(map2);
			list.add(map3);
			list.add(map4);
			list.add(map5);
			list.add(map6);
			list.add(map7);
			list.add(map8);
			return list;
	}
	
}
