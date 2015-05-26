/**
 * RestUrlUtil.java
 * com.sgcc.isc.service.adapter.utils
 * @author: wy
 * @date: 2012-8-3
 * Copyright (c) 2012, aostarit All Rights Reserved.
 */
package com.sg.syj.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 组装RESTFUL风格的URL工具�??
 * 
 * @author 王勇
 * @date 2012-8-3
 * @version
 */
public class RestUrlUtil {
	/**
	 * 拼装RESTFUL风格的URL
	 * 
	 * @param uri
	 * @param map
	 * @return
	 */
	public static String expandURL(String uri, Map<String, Object> map) {
		StringBuffer buffer = new StringBuffer(100);
		buffer.append(uri);
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for (Iterator<Entry<String, Object>> iter = entrySet.iterator(); iter.hasNext();) {
			Entry<String, Object> entry = iter.next();
			Object value = entry.getValue();
			// 过滤掉参数�?�为空的参数
			if (value != null && !"".equals(value)) {
				buffer.append("/{" + entry.getKey() + "}");
			}
		}
		return buffer.toString();
	}
}
