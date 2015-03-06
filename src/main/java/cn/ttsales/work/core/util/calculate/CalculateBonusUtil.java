/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename BusReserveStoreDTO.java
 * @package cn.ttsales.work.dto
 * @author dandyzheng
 * @date 2014-9-17
 */
package cn.ttsales.work.core.util.calculate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ttsales.work.domain.BusUserBonus;
 
/**
 * 计算红包工具类
 * @author zhaoxiaobin
 *
 */
public class CalculateBonusUtil{
	public static final Map<String,String> SQL = new HashMap<String,String>();
	
	static{
		/**红包对应查询结果sql**/
		SQL.put("1", "insert into sss");
		SQL.put("2", "select * from sss");
	}
	
	/**
	 * 查询结果再次处理
	 * @param bonusId
	 * @param busUserBonuses
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-25
	 * @see
	 */
	public static List<BusUserBonus> calculateBusUserBonusesByBonusId(
			String bonusId, List<BusUserBonus> busUserBonuses) {
		if("1".equals(bonusId)){
			return calculate1(busUserBonuses);
		}else if("2".equals(bonusId)){
			return calculate2(busUserBonuses);
		}
		return new ArrayList<BusUserBonus>();
	}
	
	private static List<BusUserBonus> calculate1(
			List<BusUserBonus> busUserBonuses) {
		return busUserBonuses;
	}

	private static List<BusUserBonus> calculate2(
			List<BusUserBonus> busUserBonuses) {
 		return busUserBonuses;
	}

	 
	
}
