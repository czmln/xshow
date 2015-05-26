package com.sg.syj.common.util;

import java.util.UUID;

public class UuidTool {
	
	public String uuid;

	public String getUuid() {
		uuid = UUID.randomUUID().toString().replace("-", "");  
		return uuid;
	}
}
