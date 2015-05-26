package com.sg.syj.common.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.util.HtmlUtils;

public class MessageUtil {

	private static MessageUtil instance = null;
	
	private static Properties properties = null;
	
	static {
		PropertyUtil propertyUtil = PropertyUtil.newInstance("webmessage");
		properties = propertyUtil.getProperties();
	}

	private MessageUtil(){
		
	}
	
	public static MessageUtil getInstance(){
		if(instance == null){
			instance = new MessageUtil();
			MessageUtil.getProperties();
		}
		return instance;
	}
	
    public static String getMsg(String key){
    	MessageUtil.getInstance();
    	String message = properties.getProperty(key);
    	return message;
    }
	
    
    public static String getMsg(String key, String param1){
    	MessageUtil.getInstance();
    	String message = properties.getProperty(key);
    	message = Common.getMessage(message, param1);
    	return message;
    }
    
    public static String getMsg(String key, String param1, String param2){
    	MessageUtil.getInstance();
    	String message = properties.getProperty(key);
    	message = Common.getMessage(message, param1, param2);
    	return message;
    }
    
    public static String getMsg(String key, String param1, String param2, String param3){
    	MessageUtil.getInstance();
    	String message = properties.getProperty(key);
    	message = Common.getMessage(message, param1, param2, param3);
    	return message;
    }
    
	public static String getProperties(){
		
		PropertyUtil propertyUtil = PropertyUtil.newInstance("webmessage");
		properties = propertyUtil.getProperties();
		
		return null;
	}
	
	public static Properties properties(){
		MessageUtil.getInstance(); 
		//if(properties == null)//注释之后修改了properties文件及时生效
//				properties = PropertiesLoaderUtils.loadAllProperties("webmessage.properties");
		PropertyUtil propUtil = PropertyUtil.newInstance("webmessage");
		properties = propUtil.getProperties();
		return properties;
	}
	
	public static void main(String[] args){
		String qq = HtmlUtils.htmlEscape("<div style=\"display:block\"></div>");
		System.out.println(qq);
		//MessageUtil.getProperties();
		System.out.println(MessageUtil.getMsg("msg.end", "aaa","bbb"));
		
	}
	
}
