/**
 * JsonUtil.java
 * com.sgcc.isc.framework.common.util
 * @author: zhangbaojian
 * @date: 2012-8-7
 * Copyright (c) 2012, aostarit All Rights Reserved.
 */
package com.sg.syj.common.util;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.type.TypeReference;

import com.sg.syj.common.constant.LongSerializer;
import com.sg.syj.common.constant.TimestampSerializer;

/**
 * @author libw
 * @date 2014-11-19
 * @version 1.0
 */
public class JsonUtil {
	
	private static JsonUtil jsonUtil;
	private ObjectMapper mapper;
	
	private JsonUtil(ObjectMapper mapper){
		this.mapper = mapper;
	}
	
	private static JsonUtil getInstance() {
		if(jsonUtil == null){
			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule("JsonUtil", 
					new Version(1, 0, 0, null));
			module.addSerializer(new LongSerializer());
			module.addSerializer(new TimestampSerializer());
			mapper.registerModule(module);
			jsonUtil = new JsonUtil(mapper);
		}
		return jsonUtil;
	}

	private ObjectMapper getMapper() {
		return mapper;
	}

	public static <T> T jsonToObject(String json, Class<T> clazz) throws Exception {
		return getInstance().getMapper().readValue(json, clazz);
	}

	public static String toJson(Object value) throws Exception {
		return getInstance().getMapper().writeValueAsString(value);
	}

	public static <T> T covertValue(Object value, Class<T> toValueType) throws Exception {
		return getInstance().getMapper().convertValue(value, toValueType);
	}
	
	public static ArrayList<?> toObjectArrayListFromJson(String json,TypeReference<?> t) throws JsonParseException, JsonMappingException, IOException{
		return (ArrayList<?>)getInstance().getMapper().readValue(json, t);
	}
	
	

	
}
