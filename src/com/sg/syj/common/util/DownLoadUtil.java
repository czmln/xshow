/**
 * DownLoadUtil.java
 * @since 2012-9-17
 * Copyright (c) 2012, aostarit All Rights Reserved.
 */
package com.sg.syj.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 下载工具�??
 * 
 * @author zhangbaojian
 * @since 2012-9-17
 * @version 2.0
 */
public class DownLoadUtil {

	private static final Logger log = Logger.getLogger(DownLoadUtil.class);

	/**
	 * 文件下载
	 * 
	 * @param filePath 文件的绝对路�??
	 * @param response 返回数据对象
	 */
	public static void downloadFile(String filePath, HttpServletResponse response, HttpServletRequest request) {
		try {
			File f = new File(filePath);
			if (f.exists()) {
				String agent=request.getHeader("user-agent");
				String fileName = null;
				if (agent.indexOf("Firefox") != -1) {
					fileName = MimeUtility.encodeText(f.getName(), "UTF8", "B");  
				} else {
					fileName = URLEncoder.encode(f.getName(), "UTF-8");
					fileName = new String(fileName.getBytes(), "ISO-8859-1");
				}
				response.setContentType("application/txt");
				response.setHeader("Content-disposition", "attachment;filename=" + fileName);
				FileInputStream in = new FileInputStream(f);
				OutputStream out = response.getOutputStream();
				byte[] temp = new byte[1024];
				int len;
				do {
					len = in.read(temp);
					if (len < 0) {
						break;
					}
					out.write(temp, 0, len);
				} while (true);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			log.error("下载报表文件异常", e);
		}
	}
	
	/**
	 * 文件下载，处理火狐浏览器文件名乱码问�??
	 * @author goujb 
	 * @param filePath
	 * @param request
	 * @param response
	 * @date 2012-12-14
	 */
	public static void downloadFile(String filePath,HttpServletRequest request, HttpServletResponse response) {
		try {
			File f = new File(filePath);
		downloadFileHandler( request, response, f);
//			if (f.exists()) {
//				String fileName =f.getName();
//				byte[] bytes = request.getHeader("User-Agent").contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8");
//				fileName=new String(bytes, "iso-8859-1");
//				response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//				response.setContentType("application/txt");
//				FileInputStream in = new FileInputStream(f);
//				OutputStream out = response.getOutputStream();
//				byte[] temp = new byte[1024];
//				int len;
//				do {
//					len = in.read(temp);
//					if (len < 0) {
//						break;
//					}
//					out.write(temp, 0, len);
//				} while (true);
//			}
		} catch (Exception e) {
			log.error("下载报表文件异常", e);
		}
	}

	/**
	 * 文件下载
	 * @author goujb 
	 * @param request
	 * @param response
	 * @param file
	 * @param fileName
	 * @param extName
	 * @throws ServletException
	 * @throws IOException
	 * @date 2012-12-20
	 */
	private static void downloadFileHandler(HttpServletRequest request,HttpServletResponse response,File file)
			throws ServletException,IOException{
		String fileName=file.getName();
		if (fileName.length()>50) {
		fileName=fileName.substring(0, 50)+"...";
		}
		String agent=request.getHeader("user-agent");
		//String tempUrl=fileVO.getFileType()+"/"+fileVO.getPath()+"/"+fileVO.getFileName();
		if(agent!=null && agent.toLowerCase().indexOf("linux")!=-1){
		if(agent.indexOf("MSIE")!=-1){
		fileName=(new String(fileName.getBytes("GBK"), "ISO8859-1"));
		}else{
		fileName="=?UTF-8?B?"+(new String(Base64.encodeBase64(fileName.getBytes("UTF-8"))))+"?=";
		}
		response.setContentType("application/x-download");
		response.addHeader("Content-Disposition","attachment; filename="+fileName);
		//response.addHeader("X-Sendfile",tempUrl);
		}else if(agent!=null && agent.indexOf("Firefox")!=-1){
		fileName = URLEncoder.encode(fileName,"UTF-8");
		fileName = fileName.replaceAll("\\+","%20");
		fileName = new String(fileName.getBytes("GBK"),"iso8859-1");
		fileName = "utf8\'\'"+fileName;
		response.setContentType("application/x-msdownload");
		response.addHeader("Content-Disposition","attachment; filename*="+fileName);
		//response.addHeader("X-Sendfile",tempUrl);
		}else{				
		//IE6下载文件名称包括/�??.的处�??
		if(null != agent&&agent.indexOf("MSIE 6.0")!=-1){
		fileName=fileName.replaceAll("/", "_").replaceAll("\\.", "%2E");
		}
		fileName = (new String(fileName.getBytes("GBK"), "ISO8859-1"));
		response.setContentType("application/x-download");
		response.addHeader("Content-Disposition","attachment; filename="+fileName);
		}
		
		if(file!=null){
		response.setContentLength((int)file.length());
		if (file.exists() && file.isFile()) {
		InputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 1024];
		int readLength = 0;
		ServletOutputStream out = response.getOutputStream();
		while (((readLength = in.read(buffer)) != -1))
		out.write(buffer, 0, readLength);
		// IOUtils.write(FileUtils.readFileToByteArray(file),
		// res.getWriter());
		in.close();
		out.flush();
		out.close();
		}
		}
	
	}	
	
	
}
