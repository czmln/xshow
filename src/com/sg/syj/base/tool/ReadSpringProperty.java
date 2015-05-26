package com.sg.syj.base.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/***
 * 
 * @author czm
 * 获取spring管理的属性文�?
 *
 */
public class ReadSpringProperty extends PropertyPlaceholderConfigurer {
	
	    private static Map<String, Object> propertiesMap;
         
	    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {  
	       
	    	super.processProperties(beanFactoryToProcess, props);
	        propertiesMap = new HashMap<String, Object>();
	        for (Object key : props.keySet()) {
	            String keyStr = key.toString();
	            String value = props.getProperty(keyStr);
	            propertiesMap.put(keyStr, value);
	        }
	    }
	    
	    //根据属�?�文件的属�?�的属�?�名返回�?
	    public static Object getContextProperty(String name) {
	        return propertiesMap.get(name);
	    }
}
