/*
 * LongSerializer.java
 * @author 张光云
 * @since 2012-9-25
 * Copyright (c) 2012, aostarit All Rights Reserved.
 * @version 2.0
 */
package com.sg.syj.common.constant;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 转换json时将Long类型的数据转换为String类型输出
 * @author libw
 * @since 2014-9-25
 * @version 1.0
 */
public class LongSerializer extends JsonSerializer<Long> {

	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(Long arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		arg1.writeString(String.valueOf(arg0));
	}
	
	@Override
	public Class<Long> handledType() {
		// TODO Auto-generated method stub
		return Long.class;
	}

}
