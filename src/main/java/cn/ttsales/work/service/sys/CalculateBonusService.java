/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename SysRegionService.java
 * @package cn.ttsales.work.service.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys;

public interface CalculateBonusService {
	/**
	 * 根据红包id计算用户红包
	 * 
	 * @param bonusId
	 * @author zhaoxiaobin
	 * @date 2014-11-25
	 * @see
	 */
	public void calculateBonusByBonusId(String bonusId);

}
