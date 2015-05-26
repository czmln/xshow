package com.sg.syj.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class RespUtil {
	
	/**
	 * 向前台返回ajax响应
	 * @param response
	 * @param json
	 * @throws IOException
	 */
	public static void resultToStr(HttpServletResponse response, String json)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(json);
		} finally {
			out.flush();
			out.close();			
		}
	}
}
