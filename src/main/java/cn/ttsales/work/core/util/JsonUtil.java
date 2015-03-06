/**
 * Copyright (c) 2012 SDFSHOP.All rights reserved.
 * @filename JsonUtil.java
 * @package sdf.core.util
 * @author dandyzheng
 * @date 2013-7-13
 */
package cn.ttsales.work.core.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;







/**
 * @author dandyzheng
 *
 */
public class JsonUtil {
	public static JSONObject fromStr(String json){
		return JSONObject.fromObject(json);
	}
	
	public static<T> JSONObject fromObject(T object,JsonConfig jsonConfig){
		return JSONObject.fromObject(object, jsonConfig);
	}
	
	public static<T> JSONArray fromList(List<T> list,JsonConfig jsonConfig){
		return JSONArray.fromObject(list, jsonConfig);
	}
	
	
	public static void main(String[] args){
		/*List<ZkProBrand> zkProBrands = new ArrayList<ZkProBrand>();
		ZkProBrand zkProBrand1 = new ZkProBrand();
		zkProBrand1.setBrandName("a");
		
		ZkProCategory zkProCategory = new ZkProCategory();
		zkProCategory.setCategoryName("a1");
		zkProBrand1.setZkProCategory(zkProCategory);
		
		ZkProBrand zkProBrand2 = new ZkProBrand();
		zkProBrand2.setBrandName("b");
		
		ZkProBrand zkProBrand3 = new ZkProBrand();
		zkProBrand3.setBrandName("c");
		
		zkProBrands.add(zkProBrand1);
		zkProBrands.add(zkProBrand2);
		zkProBrands.add(zkProBrand3);
		zkProCategory.setZkProBrands(zkProBrands);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"zkProCategory"});
		JSONArray j = JsonUtil.fromList(zkProBrands, jsonConfig);
		System.out.println(j);*/
	}
}

