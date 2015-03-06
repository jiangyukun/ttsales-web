package cn.ttsales.work.core.util;

import java.util.Map;

public class MapUtil {
	public static boolean isEmpty(Map<?,?> map) {
		if(map == null || map.size() == 0){
			return true;
		}
		return false;
	}
	
}
