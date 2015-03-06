/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename WxAppBonusService.java
 * @package cn.ttsales.work.service.rbs
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys;

import java.util.List;

import cn.ttsales.work.domain.WxAppBonus;


/**
 * WxAppBonus Service
 * @author dandyzheng
 *
 */
public interface WxAppBonusService {
	/**
	 * 保存 WxAppBonus
	 * @param wxAppBonus WxAppBonus
	 * @return WxAppBonus 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxAppBonus saveWxAppBonus(WxAppBonus wxAppBonus);
	
	/**
	 * 批量保存WxAppBonus
	 * @param wxAppBonuss WxAppBonuss
	 * @return List<WxAppBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxAppBonus> saveWxAppBonuss(List<WxAppBonus> wxAppBonuss);
	
	/**
	 * 修改WxAppBonus
	 * @param wxAppBonus WxAppBonus
	 * @return WxAppBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxAppBonus editWxAppBonus(WxAppBonus wxAppBonus);
	
	/**
	 * 批量修改WxAppBonus
	 * @param wxAppBonuss WxAppBonuss
	 * @return List<WxAppBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxAppBonus> editWxAppBonuss(List<WxAppBonus> wxAppBonuss);
	
	/**
	 * 删除WxAppBonus
	 * @param BonusDTO WxAppBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxAppBonus(WxAppBonus wxAppBonus);
	
	/**
	 * 批量删除WxAppBonus
	 * @param WxAppBonuss WxAppBonuss
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxAppBonuss(List<WxAppBonus> wxAppBonuss);
	
	/**
	 * 根据WxAppBonus' id，删除WxAppBonus
	 * @param wxAppBonusId WxAppBonus's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxAppBonus(String wxAppBonusId);
	
	/**
	 * 根据WxAppBonus' id，获取WxAppBonus
	 * @param wxAppBonusId WxAppBonus's id
	 * @return WxAppBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxAppBonus getWxAppBonus(String wxAppBonusId); 
	
	/**
	 * 获取所有WxAppBonus
	 * @return List<WxAppBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxAppBonus> getAllWxAppBonuss();
}
