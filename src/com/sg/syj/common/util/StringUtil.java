package com.sg.syj.common.util;



import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @Description:字符串工具类
 * @ClassName :com.cdutcm.common.util.StringUtil.java
 * @Author :zhufj
 */
public class StringUtil {
	/**
	 * @Description: 字符串转换为整形
	 * @MethodName : parseInt
	 * @Author :zhufj
	 * @param str
	 * @param def
	 * @return
	 */
	public static Integer parseInt(String str, Integer def) {
		try {
			return Integer.parseInt(str);
		} catch(NumberFormatException ex) {
			return def;
		}
	}
	/**
	 * @Description:字符串转换为Double
	 * @MethodName :parseInt
	 * @Author :zhufj
	 * @param str
	 * @param def
	 * @return
	 */
	public static Double parseDouble(String str, Double def) {
		try {
			return Double.parseDouble(str);
		} catch(NumberFormatException ex) {
			return def;
		}
	}
	/**
	 * @Description:字符串转换为Long
	 * @MethodName :parseLong
	 * @Author :zhufj
	 * @param str
	 * @param def
	 * @return
	 */
	public static Long parseLong(String str, Long def) {
		try {
			return Long.parseLong(str);
		} catch(NumberFormatException ex) {
			return def;
		}
	}
	/**
	 * @Description:判断字符串是否为Null或空
	 * @MethodName :isEmpty
	 * @Author :zhufj
	 * @param str
	 * @return 如果为空或 null 返回 true，否则返回 false
	 */
	public static Boolean isEmpty(String str) {
		if(str == null){
			return true;
		}
		str = str.trim();
		return "".equals(str);
	}
	/**
	 * 判断当前对象是否为 null
	 * @param str 需要进行判断的对象
	 * @return 如果为 null 返回 true，否则返回 false
	 */
	public static Boolean objIsEmpty(Object str) {
		return str == null;
	}
	/**
	 * 检查字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean hasText(String str) {
		return str != null && !"".equals(str);
	}
	/**
	 * 判断是否不是空集合
	 * @param list
	 * @return
	 */
	public static boolean isNotEmptyList(List<?> list) {
		return list != null && list.size() > 0;
	}
	
	/**
	 * @param str
	 * @return 返回模糊查询的双%
	 */
	public static String addSymbol(String str){
		return "%" + str + "%";
	}
	/**
	 * 判断是否是空集合
	 * @param list
	 * @return
	 */
	public static boolean isEmptyList(List<?> list) {
		return list == null || list.size() <= 0;
	}
	/**
	 * 分割字符串
	 * @param str 需要分割的字符
	 * @param delimiter 按指定符号分割
	 * @return 字符串数组
	 */
	public static String[] split(String str, String delimiter) {
		int delimiterLength;
		int stringLength = str.length();
		if(delimiter == null || (delimiterLength = delimiter.length()) == 0) {
			return new String[]{str};
		}
		int count = 0, start = 0, end;
		while((end = str.indexOf(delimiter, start)) != -1) {
			count++;
			start = end + delimiterLength;
		}
		count++;
		String[] result = new String[count];
		count = 0;
		start = 0;
		while((end = str.indexOf(delimiter, start)) != -1) {
			result[count] = (str.substring(start, end));
			count++;
			start = end + delimiterLength;
		}
		end = stringLength;
		result[count] = str.substring(start, end);
		return (result);
	}
	/** 
     * 获取字符串内的所有汉字的汉语拼音并大写每个字的首字母 
     * @param chinese 
     * @return 
     */  
	public static String spell(String chinese) {
		if(!isEmpty(chinese)) {
			HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
			// 小写
			format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			// 不标声调
			format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			// u:的声母替换为v
			format.setVCharType(HanyuPinyinVCharType.WITH_V);
			try {
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < chinese.length(); i++) {
					String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), format);
					if(array == null || array.length == 0) {
						continue;
					}
					// 不管多音字,只取第一个
					String s = array[0];
					// 大写第一个字母
					char c = s.charAt(0);
					String pinyin = String.valueOf(c);
					sb.append(pinyin);
				}
				return sb.toString();
			} catch(BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	/**
	 * 判断首字母是否是汉字
	 * @param str 需要进行判断的字符串
	 * @return true/false
	 */
	public static boolean isChineseChar(String str) {
		boolean flag = false;
		if(str != null) {
			char temp = str.charAt(0);
			flag = temp >= 0x0391 && temp <= 0xFFE5;
		}
		return flag;
	}
	/**
	 * 判断字符串中指定位置是否是汉字
	 * @param str 需要进行判断的字符串
	 * @return true/false
	 */
	public static boolean isChineseChar(String str, int index) {
		boolean flag = false;
		if(str != null) {
			if(index < str.length()) {
				char temp = str.charAt(index);
				flag = temp >= 0x0391 && temp <= 0xFFE5;
			}
		}
		return flag;
	}
	/**
	 * 判断首字母是否是英文
	 * @param str 需要进行判断的字符串
	 * @return true/false
	 */
	public static boolean isEnglishChar(String str) {
		boolean flag = false;
		if(str != null) {
			char temp = str.charAt(0);
			flag = temp >= 0x0000 &&  temp <= 0x00FF;
		}
		return flag;
	}
	/**
	 * 判断字符串中指定位置是否是英文
	 * @param str 需要进行判断的字符串
	 * @return true/false
	 */
	public static boolean isEnglishChar(String str, int index) {
		boolean flag = false;
		if(str != null) {
			if(index < str.length()) {
				char temp = str.charAt(index);
				flag = temp >= 0x0000 &&  temp <= 0x00FF;
			}
		}
		return flag;
	}
	
	/**
	 * @param str
	 * @return
	 * Description：将字符串转化为UTF8
	 */
	public static String encode2UTF8(String str){
		if(isEmpty(str)){
			return null;
		}
		try {
			return new String(str.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}
}
