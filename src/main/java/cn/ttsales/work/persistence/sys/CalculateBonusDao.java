/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename SysRegionDao.java
 * @package cn.ttsales.work.persistence.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys;


public interface CalculateBonusDao {
	/**
	 * 根据红包id计算用户红包
	 * 
	 * @param bonusId
	 * @author zhaoxiaobin
	 * @return 
	 * @date 2014-11-25
	 * @see
	 */
	public void calculateBonusByBonusId(String bonusId);

}
