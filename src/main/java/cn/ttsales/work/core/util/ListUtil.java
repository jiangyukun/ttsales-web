package cn.ttsales.work.core.util;

import java.util.List;

public class ListUtil {
	public static boolean isEmpty(List<?> list) {
		if(list == null || list.size() == 0){
			return true;
		}
		return false;
	}
	
	public static <T> T getSingle(List<T> list){
		if(null == list || list.size() == 0){
			return null;
		}
		return list.get(0);
	}
}
