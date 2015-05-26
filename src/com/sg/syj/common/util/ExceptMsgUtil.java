package com.sg.syj.common.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * TODO
 * @ClassName: ExceptMsgUtil
 * @author zhangdie
 */
public class ExceptMsgUtil {
	
	static Logger logger = Logger.getLogger(ExceptMsgUtil.class);
	
	private static Properties properties = null;
	
	static {
		if (null == properties){
			try {
				properties = PropertiesLoaderUtils.loadAllProperties("exceptionmsg.properties");
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public static Properties properties(){
		try {
				properties = PropertiesLoaderUtils.loadAllProperties("exceptionmsg.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
	
	public static String getMsg(String key){
    	String message = properties.getProperty(key);
    	return message;
    }
	
    
    public static String getMsg(String key, String param1){
    	String message = properties.getProperty(key);
    	message = Common.getMessage(message, param1);
    	return message;
    }
    
    public static String getMsg(String key, String param1, String param2){
    	String message = properties.getProperty(key);
    	message = Common.getMessage(message, param1, param2);
    	return message;
    }
    
    public static String getMsg(String key, String param1, String param2, String param3){
    	String message = properties.getProperty(key);
    	message = Common.getMessage(message, param1, param2, param3);
    	return message;
    }
    
    public static void main(String[] args){
    	String msg = ExceptMsgUtil.getMsg("msg.user.service.add.error");
    	System.out.println(msg);
    }

}
