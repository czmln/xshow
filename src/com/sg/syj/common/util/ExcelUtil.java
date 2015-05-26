/**
 * ExcelUtil.java
 * @since 2012-9-6
 * Copyright (c) 2012, aostarit All Rights Reserved.
 */
package com.sg.syj.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * Excel工具�??
 * 
 * @author zhangbaojian
 * @since 2012-9-8
 * @version 2.0
 */
public class ExcelUtil {

	private static final Logger log = Logger.getLogger(ExcelUtil.class);
	
	public static void writeExcel(String items, Object data, String excelModel, String excelFileName){
		
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put(items, data);
		
        XLSTransformer transformer = new XLSTransformer();
		
		try {
			transformer.transformXLS(excelModel, beans, excelFileName);
		} catch (ParsePropertyException e) {
			log.error("写Excel失败�??", e);
		} catch (IOException e) {
			log.error("写Excel失败�??", e);
		}
	}
	
	public static void readExcelFile(String key, List list, String templeFile, String filePath){   
		File excelFile = new File(filePath);
		File excelModel = new File(templeFile);
		try {
			InputStream inputXLS = new FileInputStream(excelFile);
			if (inputXLS != null) {
				InputStream inputXML = new BufferedInputStream(new FileInputStream(excelModel));
				XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
				Map<String, Object> beans = new HashMap<String, Object>();
				beans.put(key, list);
				mainReader.read(inputXLS, beans);
			}
		} catch (FileNotFoundException e) {
			log.error("未找到文件，读Excel失败�??", e);
		} catch (IOException e) {
			log.error("读Excel失败�??", e);
		} catch (SAXException e) {
			log.error("读Excel失败�??", e);
		}
    }  

}
