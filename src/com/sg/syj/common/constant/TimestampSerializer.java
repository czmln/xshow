package com.sg.syj.common.constant;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class TimestampSerializer extends JsonSerializer<java.sql.Timestamp> {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@Override
	public void serialize(Timestamp arg0, JsonGenerator arg1,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		arg1.writeString(String.valueOf(dateFormat.format(arg0)));
	}
	@Override
	public Class<Timestamp> handledType() {
		// TODO Auto-generated method stub
		return Timestamp.class;
	}
}
