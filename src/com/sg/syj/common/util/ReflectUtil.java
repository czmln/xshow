package com.sg.syj.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 反射公用操作
 * 
 * @author 张保�??
 * @since 2012-11-27
 * @version 2.0
 */
public class ReflectUtil {

	static Logger logger = Logger.getLogger(ReflectUtil.class.getName());

	/**
	 * 空的Class数组 
	 */
	public static final Class<?>[] EMPTY_CLASS = new Class<?>[0];

	/**
	 * 空对象数�??
	 */
	public static final Object[] EMPTY_OBJECT = new Object[0];

	/**
	 * javaBean反射用到缓存 <String, ReflectCacheBean> 完整类名
	 * 类中属�?�，方法缓存
	 */
	public static final Map<String, ReflectCacheBean> reflectCache = new HashMap<String, ReflectCacheBean>();

	public static final Map<String, Class<?>> clsMap = new HashMap<String, Class<?>>();

	/**
	 * 方法缓存 <String, Map<String, Map<Object, Method>>> 类名
	 * 方法�?? 参数类型 方法
	 */
	public static final Map<String, Map<String, Map<Object, Method>>> methodCache = new HashMap<String, Map<String, Map<Object, Method>>>();

	/**
	 * 根据obj类型 反射创建�??个新的对�??
	 * 
	 * @param obj 对象
	 * @return Object 新建对象
	 */
	public static Object newInstance(Object obj) {
		if (obj == null) {
			logger.error("传入的对象不能为空；");
			return null;
		}
		// 获取obj类型
		Class<?> cls = obj.getClass();
		Object ret = null;
		try {
			// 创建�??个对�??
			ret = cls.newInstance();
		} catch (Exception e) {
			logger.error("创建实例失败�??", e);
		}
		return ret;
	}

	public static Object newInstance(Class<?> cls) {
		Object obj = null;
		try {
			obj = cls.newInstance();
		} catch (Exception e) {
			logger.error("创建实例失败�??", e);
		}
		return obj;
	}


	public static Field[] getDeclaredFields(Class<?> cls){
		String clsName = cls.getName();
		ReflectCacheBean cacheBean = reflectCache.get(clsName);
		if(cacheBean == null){
			ReflectCacheBean tempCacheBean = new ReflectCacheBean();
			reflectCache.put(clsName, tempCacheBean);
			cacheBean = tempCacheBean;
		}
		Field[] fileds = cacheBean.getFields();
		if (fileds == null || fileds.length <= 0) {
			Field[] tempFields = cls.getDeclaredFields();
			cacheBean.setFields(tempFields);
			fileds = tempFields;
		}
		return fileds;
	}
	
	/**
	 * 通过反射新建�??个新的对�??
	 * 
	 * @param clsName 完整类名 java.lang.String
	 * @return Object 新建对象
	 */
	public static Object newInstance(String clsName) {
		if (StringUtils.isEmpty(clsName)) {
			logger.error("类名不能为空�??");
			return null;
		}
		Object obj = null;
		Class<?> cls = getClass(clsName);
		if (cls == null) {
			return null;
		}
		try {
			// 创建�??个对�??
			obj = cls.newInstance();
		} catch (Exception e) {
			logger.error("创建实例失败�??", e);
		}
		return obj;
	}

	/**
	 * 返回与带有给定字符串名的类或接口相关联的 Class 对象
	 * 
	 * @param clsName 完整类名 java.lang.String
	 * @return Class 类型
	 */
	public static Class<?> getClass(String clsName) {
		if (StringUtils.isEmpty(clsName)) {
			logger.error("类名不能为空�??");
			return null;
		}
		Class<?> cls = clsMap.get(clsName);
		if (cls != null) {
			return cls;
		}
		try {
			cls = Class.forName(clsName);
			clsMap.put(clsName, cls);
			return cls;
		} catch (ClassNotFoundException e) {
			logger.error("找不到对�??" + clsName, e);
			return null;
		}
	}

	/**
	 * 设置对象知道属�?�的�??
	 * 
	 * @param obj
	 * @param fieldName 属�?�名�??
	 * @param fieldValue 属�?��??
	 */
	public static void writeProperty(Object obj, String fieldName, Object fieldValue) {
		// 获取属�?�的set方法
		Method method = getWriteMethod(obj, fieldName);
		try {
			if (method == null) {
				logger.error(obj + "的属�??" + fieldName + "没有对应的set方法�??");
				return;
			}
			// 设置属�?��??
			method.invoke(obj, new Object[] { fieldValue });
		} catch (IllegalArgumentException e) {
			logger.error("方法" + method + "的参数类型不能为" + fieldValue.getClass().getName());
			throw e;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回对象指定属�?�的�??
	 * 
	 * @param obj
	 * @param fieldName 属�?�名�??
	 * @return Object 属�?��??
	 * @throws NoSuchMethodException
	 */
	public static Object readProperty(Object obj, String fieldName){
		Object ret = null;
		Method method = getReadMethod(obj, fieldName);
		try {
			if (method == null) {
				logger.error(obj + "的属�??" + fieldName + "没有对应的get方法�??");
				return null;
			}
			ret = method.invoke(obj, EMPTY_OBJECT);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 返回对象指定属�?�的类型
	 * 
	 * @param obj
	 * @param fieldName 属�?�名
	 * @return Class 属�?�类�??
	 */
	public static Class<?> getFieldType(Object obj, String fieldName) {
		if (obj == null || StringUtils.isEmpty(fieldName)) {
			// System.out.println("属�?�名不能为空�??");
			logger.error("属�?�名不能为空�??");
			return null;
		}
		Class<?> cls = obj.getClass();
		return getFieldType(cls, fieldName);
	}

	/**
	 * 返回�?? clsName �?? fieldName 属�?�类�??
	 * 
	 * @param clsName 完整类名
	 * @param fieldName 属�?�名
	 * @return Class 属�?�类�??
	 */
	public static Class<?> getFieldType(String clsName, String fieldName) {
		if (StringUtils.isEmpty(clsName) || StringUtils.isEmpty(fieldName)) {
			logger.error("类名和属性名都不能为空；");
			return null;
		}
		try {
			Class<?> cls = Class.forName(clsName);
			return getFieldType(cls, fieldName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回�?? cls 的属�?? fieldName 类型(包括父类中的属�??)
	 * 
	 * @param cls
	 * @param fieldName 属�?�名�??
	 * @return Class 属�?�类�??
	 */
	public static Class<?> getFieldType(Class<?> cls, String fieldName) {
		if (cls == null || StringUtils.isEmpty(fieldName)) {
			logger.error("类名和属性名都不能为空；");
			return null;
		}
		try {
			Field field = cls.getDeclaredField(fieldName);
			if (field == null) {
				logger.error("对象" + cls.getName() + "中找不到属�??" + fieldName);
				return null;
			}
			return field.getType();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {

			Class<?> supCls = cls.getSuperclass();

			// 若果当前类中没有对应属�?�，在父类中查找�??
			if (supCls != Object.class) {
				return getFieldType(supCls, fieldName);
			} else {
				e.printStackTrace();
			}
		}
		logger.error("对象" + cls.getName() + "中找不到属�??" + fieldName);
		return null;
	}

	/**
	 * 返回 获取对象属�?�的方法 JavaBean使用
	 * 
	 * @param obj
	 * @param fieldName 属�?�名�??
	 * @return Method 属�?�的get方法
	 * @throws NoSuchMethodException
	 */
	public static Method getReadMethod(Object obj, String fieldName){
		if (obj == null || StringUtils.isEmpty(fieldName)) {
			return null;
		}
		Class<?> cls = obj.getClass();
		return getReadMethod(cls, fieldName);
	}

	/**
	 * 返回 获取对象属�?�的方法 JavaBean使用
	 * 
	 * @param clsName 对象类名
	 * @param fieldName 属�?�名�??
	 * @return Method 属�?�的get方法
	 * @throws NoSuchMethodException
	 */
	public static Method getReadMethod(String clsName, String fieldName){
		Class<?> cls = getClass(clsName);
		return getReadMethod(cls, fieldName);
	}

	/**
	 * 返回 获取对象属�?�的方法 JavaBean使用
	 * 
	 * @param cls
	 * @param fieldName 属�?�名�??
	 * @return Method 属�?�的get方法
	 * @throws NoSuchMethodException
	 */
	public static Method getReadMethod(Class<?> cls, String fieldName){
		if (cls == null || StringUtils.isEmpty(fieldName)) {
			return null;
		}
		Method method = null;

		String clsName = cls.getName();
		ReflectCacheBean cacheBean = reflectCache.get(clsName);
		if (cacheBean != null) {
			method = cacheBean.getReadMethodMap().get(fieldName);
			if (method != null) {
				return method;
			}
		}

		String methodName = null;
		try {
			methodName = "get" + changFirstUpper(fieldName);
			method = cls.getMethod(methodName, EMPTY_CLASS);

			// 放入缓存
			if (method != null) {
				if (cacheBean == null) {
					cacheBean = new ReflectCacheBean();
					cacheBean.setBeanClass(cls);
				}
				cacheBean.getReadMethodMap().put(fieldName, method);
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {

			try {
				// 属�?�为布尔类型
				methodName = "is" + changFirstUpper(fieldName);
				method = cls.getMethod(methodName, EMPTY_CLASS);
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				logger.error("找不到对�??" + cls + "�??" + fieldName + "属�?�的get方法�??");
			}
		}
		return method;
	}

	/**
	 * 返回 设置对象属�?�的方法 JavaBean使用
	 * 
	 * @param obj
	 * @param fieldName 属�?�名�??
	 * @return Method 属�?�的set方法
	 */
	public static Method getWriteMethod(Object obj, String fieldName) {
		if (obj == null || StringUtils.isEmpty(fieldName)) {
			return null;
		}
		Class<?> cls = obj.getClass();
		return getWriteMethod(cls, fieldName);
	}

	/**
	 * 返回 设置对象属�?�的方法 JavaBean使用
	 * 
	 * @param clsName 对象类名
	 * @param fieldName 属�?�名�??
	 * @return Method 属�?�的set方法
	 */
	public static Method getWriteMethod(String clsName, String fieldName) {
		Class<?> cls = getClass(clsName);
		return getWriteMethod(cls, fieldName);
	}

	/**
	 * 返回 设置对象属�?�的方法 JavaBean使用
	 * 
	 * @param cls
	 * @param fieldName 属�?�名�??
	 * @return Method 属�?�的set方法
	 */
	public static Method getWriteMethod(Class<?> cls, String fieldName) {
		if (cls == null || StringUtils.isEmpty(fieldName)) {
			return null;
		}

		Method method = null;
		String clsName = cls.getName();

		// 从缓存中获取操作方法
		ReflectCacheBean cacheBean = reflectCache.get(clsName);
		if (cacheBean != null) {
			method = cacheBean.getWriteMethodMap().get(fieldName);
			if (method != null) {
				return method;
			}
		}

		try {
			Class<?> clazz = getFieldType(cls, fieldName);
			if (clazz == null) {
				return null;
			}
			method = cls.getMethod("set" + changFirstUpper(fieldName), new Class[] { clazz });

			// 放入缓存
			if (method != null) {
				if (cacheBean == null) {
					cacheBean = new ReflectCacheBean();
					cacheBean.setBeanClass(cls);
				}
				cacheBean.getWriteMethodMap().put(fieldName, method);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			logger.error("找不到对�??" + cls + "�??" + fieldName + "属�?�的set方法�??");
		}
		return method;
	}

	/**
	 * 返回 对象的指定方�??
	 * 
	 * @param obj
	 * @param methodName 方法名称
	 * @param parameterTypes 参数类型属�??
	 * @return Method 方法
	 */
	public static Method getMethod(Object obj, String methodName, Class<?>[] parameterTypes) {
		if (obj == null || StringUtils.isEmpty(methodName)) {
			return null;
		}

		if (parameterTypes == null) {
			parameterTypes = EMPTY_CLASS;
		}

		String clsName = obj.getClass().getName();
		Method method = null;
		// 从缓存中获取
		Map<String, Map<Object, Method>> methodMap = methodCache.get(clsName);
		Map<Object, Method> propertyMap = null;
		if (methodMap != null) {
			propertyMap = methodMap.get(methodName);
			if (propertyMap != null) {
				method = propertyMap.get(parameterTypes);
				if (method != null) {
					return method;
				}
			}
		}

		try {
			Class<?> cls = obj.getClass();
			method = cls.getMethod(methodName, parameterTypes);

			// 将获取方法放入缓�??
			if (method != null) {
				if (methodMap == null) {
					methodMap = new HashMap<String, Map<Object, Method>>();
					methodCache.put(clsName, methodMap);
				}
				if (propertyMap == null) {
					propertyMap = new HashMap<Object, Method>();
					methodMap.put(methodName, propertyMap);
				}
				propertyMap.put(parameterTypes, method);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return method;
	}

	/**
	 * 执行方法
	 * 
	 * @param method 方法
	 * @param obj
	 * @param param 参数数组
	 * @return Object 执行结果
	 */
	public static Object invokeMethod(Method method, Object obj, Object[] param) {
		if (method == null || obj == null) {
			return null;
		}
		if (param == null) {
			param = EMPTY_OBJECT;
		}
		try {
			return method.invoke(obj, param);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// System.out.println("执行invoke是错误；方法�??" + obj +
			// method);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取对象构�?�函�??
	 * 
	 * @param obj
	 * @param paramType 参数类型
	 * @return 构�?�函�??
	 */
	public static Constructor<?> getConstructorMethod(Object obj, Class<?>[] paramType) {
		if (obj == null) {
			return null;
		}
		Class<?> cls = obj.getClass();
		try {
			Constructor<?> constructor = cls.getConstructor(paramType);
			return constructor;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		logger.error("对象" + cls.getName() + "没有声明构�?�函�??" + paramType);
		return null;
	}

	/**
	 * 用相应的构�?�方�?? 创建对象
	 * 
	 * @param obj
	 * @param paramType 构�?�方法参�??
	 * @return 新建对象
	 */
	// public static Object newInstance(Object obj, Object[]
	// paramType){
	//
	// if(obj == null){
	// return null;
	// }
	//
	// if(paramType == null || paramType.length == 0){
	// return newInstance(obj);
	// }
	// Class[] clsType = new Class[paramType.length];
	// for(int i=0; i<paramType.length; i++){
	// clsType[i] = paramType[i].getClass();
	// }
	// Constructor con = getConstructorMethod(obj, clsType);
	// if(con == null){
	// return null;
	// }
	// Object ret = null;
	// try {
	// ret = con.newInstance(paramType);
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// } catch (InstantiationException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// }
	// return ret;
	//
	// }

	/**
	 * 执行方法 javabean
	 * 
	 * @param methodName 方法名称
	 * @param obj
	 * @return Object 执行结果
	 */
	public static Object invokeMethod(String methodName, Object obj) {
		if (obj == null || StringUtils.isEmpty(methodName)) {
			return null;
		}
		Method method = getMethod(obj, methodName, EMPTY_CLASS);
		return invokeMethod(method, obj, EMPTY_OBJECT);
	}

	/**
	 * 将字符串第一个字母改为大�?? "abc"-->"Abc"
	 * 
	 * @param str
	 * @return String
	 */
	private static String changFirstUpper(String str) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		StringBuffer sb = new StringBuffer(str);
		char ch = sb.charAt(0);
		sb.setCharAt(0, Character.toUpperCase(ch));
		return sb.toString();
	}

	/**
	 * 初始化数据类bean 转换时反射用到的方法
	 */
	public static void initReflectCache(Iterator<String> iter) {
		while (iter.hasNext()) {
			String clsName = iter.next();
			try {
				Class<?> cls = Class.forName(clsName);
				ReflectCacheBean cacheBean = new ReflectCacheBean();
				cacheBean.setBeanClass(cls);
				BeanInfo beanInfo = Introspector.getBeanInfo(cls);
				PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
				if (descriptors != null) {
					for (int i = 0; i < descriptors.length; i++) {
						PropertyDescriptor des = descriptors[i];
						try {
							cacheBean.getReadMethodMap().put(des.getName(), des.getReadMethod());
							cacheBean.getWriteMethodMap().put(des.getName(), des.getWriteMethod());
						} catch (SecurityException e) {
							e.printStackTrace();
						}
					}
				}
				ReflectUtil.reflectCache.put(clsName, cacheBean);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取bean�?? set 方法缓存
	 * 
	 * @param clsName bean类名
	 */
	public static Map<String, Method> getWriteMap(String clsName) {
		return reflectCache.get(clsName).getWriteMethodMap();
	}

	/**
	 * 获取bean�?? get 方法缓存
	 * 
	 * @param clsName bean类名
	 */
	public static Map<String, Method> getReadMap(String clsName) {
		return reflectCache.get(clsName).getReadMethodMap();
	}
}

/**
 * 缓存bean�?? class field readMethod writeMethod
 */
class ReflectCacheBean {

	/**
	 * javaBean 对应的class
	 */
	private Class<?> beanClass;

	/**
	 * javaBean 中所有读属�?�的方法 <String, Method> 属�?�名 属�?�对应的get方法
	 */
	private Map<String, Method> readMethodMap = new HashMap<String, Method>();

	/**
	 * javaBean 中所有写读属性的方法 <String, Method> 属�?�名 属�?�对应的set方法
	 */
	private Map<String, Method> writeMethodMap = new HashMap<String, Method>();

	/**
	 * javaBean �??有属�?? <String, Field> 属�?�名 属�?�对�??
	 */
	private Map<String, Field> fieldMap = new HashMap<String, Field>();
	
	private Field[] fields;

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public Map<String, Method> getReadMethodMap() {
		return readMethodMap;
	}

	public void setReadMethodMap(Map<String, Method> readMethodMap) {
		this.readMethodMap = readMethodMap;
	}

	public Map<String, Method> getWriteMethodMap() {
		return writeMethodMap;
	}

	public void setWriteMethodMap(Map<String, Method> writeMethodMap) {
		this.writeMethodMap = writeMethodMap;
	}

	public Map<String, Field> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, Field> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}
}
