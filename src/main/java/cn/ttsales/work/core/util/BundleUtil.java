/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename BundleUtil.java
 * @package cn.ttsales.work.core.util
 * @author dandyzheng
 * @date 2014-8-11
 */
package cn.ttsales.work.core.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import cn.ttsales.work.core.SASConstants;



/**
 * @author dandyzheng
 * 
 */
public class BundleUtil {
	
	public static Locale CURR_LOCALE = Locale.CHINA;
	public static final String RESOURCE_PATH ="META-INF.";
	
	/**
	 * 获取资源文件信息
	 * @param name 资源名称basename
	 * @param key 资源KEY
	 * @param f 资源占位值
	 * @return 资源最终提示信息
	 * @author dandyzheng
	 * @date 2013-7-1
	 * @see
	 */
	public static String getProperty(String name,String key,Object ...f){
		ResourceBundle r = ResourceBundle.getBundle(RESOURCE_PATH+name,CURR_LOCALE);//根据指定的国家/语言环境加载对应的资源文件
		if(f == null || f.length == 0){
			return r.getString(key);
		}else{
			return MessageFormat.format(r.getString(key), f);
		}
	}
	
	public static void main(String[] arags){
		System.out.println(BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.sms.content"));
		//System.out.println(MsgUtil.getMsg(AsipConstants.RESOURCE_BUNDLE, "asip.kk","a","b"));
		/*System.out.println(MsgUtil.getMsg(AsipConstants.RESOURCE_BUNDLE, "sdfSysParam.paramValue1","ssss"));
		System.out.println(MsgUtil.getMsg(AsipConstants.RESOURCE_BUNDLE, "sdfSysParam.paramValueMsg1","ssss","aaa"));*/
		/*
			String fileName = "D:/workspace/SDFSHOP/src/main/webapp/WEB-INF/dispatcher-servlet.xml";

	       FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(fileName);

	       String name = "";

	       name = context.getMessage("sdfshop.welcome", null, Locale.CHINA);

	       System.out.println(name);*/
		
		//System.out.println(MsgUtil.getMsg(AsipConstants.RESOURCE_BUNDLE, "ASIP.FLOWCODE_FIRST_UNCHECK","ddd"));

	}
}
