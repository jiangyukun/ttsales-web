/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename ArryUtil.java
 * @package com.ratan.util
 * @author dandyzheng
 * @date 2012-6-7
 */
package cn.ttsales.work.core.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dandyzheng
 * 
 */
public class ArrayUtil {

	/**
	 * array to list
	 * @return list<object>
	 * @author dandyzheng
	 * @date 2012-6-7
	 */
 	public static <T>List<T> toList(T[] o) {
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < o.length; i++) {
			list.add(o[i]);
		}
		return list;
	}
 	
 	public static <T>Set<T> toSet(T[] o) {
 		Set<T> set = new HashSet<T>();
		for (int i = 0; i < o.length; i++) {
			set.add(o[i]);
		}
		return set;
	}
 	
 	public static <T>Set<T> toSet(List<T> o) {
 		Set<T> set = new HashSet<T>();
		for (int i = 0; i < o.size(); i++) {
			set.add(o.get(i));
		}
		return set;
	}
	
	public static <T> List<T> toList(Set<T> set) {
		if(null==set || set.size() ==0){
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (T t:set) {
			list.add(t);
		}
		return list;
	}
	
	public static boolean isEmpty(List<?> list){
		boolean isEmpty = false;
		if(null == list || list.size()==0){
			isEmpty = true;
		}
		return isEmpty;
	}
	
	public static <T> boolean isEmpty(T... array){
		if(null != array && array.length>0){
			return false;
		}
		return true;
	}
	
	public static <T> boolean isEmpty(Map<String,T> maps){
		if(null == maps || maps.size() == 0){
			return true;
		}
		return false;
	}
	
	public static <T> boolean isIntegerKeyEmpty(Map<Integer,T> maps){
		if(null == maps || maps.size() == 0){
			return true;
		}
		return false;
	}
}
