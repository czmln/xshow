package com.sg.syj.base.tool;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.springframework.web.context.ContextLoader;

public class WebServiceUtil{
   
	
	/****
	 * 服务接口的地�?�?
	 */
	private static   String serviceAddress= ReadSpringProperty.getContextProperty("serverAddress").toString();
	
	/***
	 * @description  获取spring 容器的业务bean
	 * @param beanId 业务bean在spring中的id 默认为类名首字母小写
	 * @return Object
	 */
	public static Object getSpringBean(String beanId) {
		return ContextLoader.getCurrentWebApplicationContext().getBean(beanId);
	}

	/***
	 * @description  调用外部的接�?
	 * @param nameSpace 命名空间 �? methodName 方法�? ，Map<String ,String> parms 接口�?要传入的参数 key是参数名 value是参数�??
	 * @return Object
	 * @throws Exception 
	 */
	public static String transportCall(String serverName,String methodName,Map<String ,String> parms) throws Exception{
		
		
		if(serviceAddress==null||serviceAddress.equals("")) throw new Exception("读取接口配置文件的服务器地址出错");
		//命名空间
		String nameSpace=serviceAddress+serverName;

        // EndPoint  
        String endPoint = nameSpace+"?wsdl";  
    
        // 指定WebService的命名空间和调用的方法名  
        SoapObject rpc = new SoapObject(nameSpace, methodName);  
       
        //添加参数
        for(String key:parms.keySet()){
            rpc.addProperty(key, parms.get(key));  
        }
        
        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版�?  
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);  
  
        envelope.bodyOut = rpc;  
        // 设置是否调用的是dotNet�?发的WebService  
        //envelope.dotNet = true;  
        // 等价于envelope.bodyOut = rpc;  
        envelope.setOutputSoapObject(rpc);  
        
        HttpTransportSE transport = new HttpTransportSE(endPoint);  
        try {  
            // 调用WebService  
            transport.call("", envelope);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        // 获取返回的数�?  
     
          SoapObject object = (SoapObject) envelope.bodyIn;  
        // 获取返回的结�?  
        String result = object.getProperty(0).toString(); 
		
		return result;
	}
	
	
	/****
	 * @description  根据sap系统接口返回的数据格�? 将数据放入map
	 * @param result 返回的数�?
	 * @return List<Map<String,String>>
	 * @throws Exception
	 */
	public static List<Map<String,String>> assembleSapData(String result) throws Exception{
		
		List<Map<String,String>> list=new LinkedList<Map<String,String>>();
		
		//得到每条数据的字符串
		String[] resArray = result.split(";");
		
		for(String str:resArray){	
			Map<String,String> map=new HashMap<String, String>();
			//得到�?条数据的字段数组
			 String[] oneRecordArr =str.split(",");
			 if(oneRecordArr.length<2) continue;
			 for(String acolumnStr:oneRecordArr){
				      String[] keyValue = acolumnStr.split("=");	      
				      if(keyValue.length!=2) continue;
				      map.put(keyValue[0], keyValue[1])	;       
			 }
			 if(!map.isEmpty()){
				 list.add(map);
			 }
		}
		return list;
	}
	
}
