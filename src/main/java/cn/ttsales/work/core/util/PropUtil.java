/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename PropUtil.java
 * @package com.ratan.util
 * @author dandyzheng
 * @date 2012-6-7
 */
package cn.ttsales.work.core.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Properties工具类
 * @author dandyzheng
 * 
 */
public class PropUtil {
	private static Properties props;
	private static String FINENAME = "/META-INF/msg.properties";

	static{
		setProperties();
	}

	/**
	 * 以默认文件 CLASS下 “/ex.properties” 文件名 加载 Propertie
	 * 
	 * @author dandyzheng
	 * @date 2012-6-7
	 */
	private static void setProperties() {
		InputStream fis = null;
		try {
			props = new Properties();
			fis = PropUtil.class.getResourceAsStream(FINENAME);
			props.load(fis);
			fis.close();
		} catch (Exception e) {
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 指定文件路径加载Propertie
	 * @param fileName 文件完整路径，如：d:/workspace/PrimeFaceDemo/src/resources/hibernate.properties
	 * @author dandyzheng
	 * @date 2012-6-7
	 * @see
	 */
	public static void setProperties(String fileName) {
		FileInputStream fis = null;
		try {
			FINENAME = fileName;
			props = new Properties();
			fis = new FileInputStream(fileName);
			props.load(fis);
		} catch (Exception e) {
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void setPropertiesByResourceAsStream(String fileName) {
		InputStream fis = null;
		try {
			FINENAME = fileName;
			props = new Properties();
			fis = PropUtil.class.getResourceAsStream(fileName);
			props.load(fis);
		} catch (Exception e) {
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据KEY获取属性值
	 * @param key key
	 * @return 属性值
	 * @author dandyzheng
	 * @date 2012-6-7
	 * @see
	 */
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
	
	public static String getProperty(String propResource,String key) {
		setPropertiesByResourceAsStream(propResource);
		return props.getProperty(key);
	}

	/**
	 * 获取所有属性，存储在MAP中
	 * @return 属性MAP
	 * @author dandyzheng
	 * @date 2012-6-7
	 * @see
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getPropertys() {
		Map map = new HashMap();
		Enumeration enu = props.propertyNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = props.getProperty(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 控制台打印所有属性值
	 * 
	 * @author dandyzheng
	 * @date 2012-6-7
	 * @see
	 */
	public static void printProperties() {
		props.list(System.out);
	}

	/**
	 * 写入属性
	 * @param key key
	 * @param value value
	 * @author dandyzheng
	 * @date 2012-6-7
	 * @see
	 */
	public static void writeProperties(String key, String value) {
		OutputStream fos = null;
		try {
			fos = new FileOutputStream(FINENAME);
			props.setProperty(key, value);
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, "『comments』Update key：" + key);
		} catch (IOException e) {
			e.printStackTrace();
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void writeProperties(String propResource,String key, String value) {
		OutputStream fos = null;
		try {
			fos = new FileOutputStream(propResource);
			props.setProperty(key, value);
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, "『comments』Update key：" + key);
		} catch (IOException e) {
			e.printStackTrace();
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		//PropUtil.setProperties("d:/workspace/PrimeFaceDemo/src/resources/hibernate.properties");
		//PropUtil.setProperties("E:\\workspace2\\PrimeFaceDemo\\src\\resources\\upload.properties");
		PropUtil.setPropertiesByResourceAsStream("/META-INF/log4j.properties");
		String value = PropUtil.getProperty("log4j.appender.A.File");
		System.out.println("vffffffffffffv="+value);
//		PropUtil.writeProperties("e", "eeeee");
//		PropUtil.printProperties();
	}
}
