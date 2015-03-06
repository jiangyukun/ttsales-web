/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename WxUserBonusService.java
 * @package cn.ttsales.work.service.rbs
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys;

import java.util.List;

import cn.ttsales.work.domain.WxUserBonus;

/**
 * WxUserBonus Service
 * @author dandyzheng
 *
 */
public interface WxUserBonusService {
	/**
	 * 保存 WxUserBonus
	 * @param wxUserBonus WxUserBonus
	 * @return WxUserBonus 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxUserBonus saveWxUserBonus(WxUserBonus wxUserBonus);
	
	/**
	 * 批量保存WxUserBonus
	 * @param wxUserBonuss WxUserBonuss
	 * @return List<WxUserBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxUserBonus> saveWxUserBonuss(List<WxUserBonus> wxUserBonuss);
	
	/**
	 * 修改WxUserBonus
	 * @param wxUserBonus WxUserBonus
	 * @return WxUserBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxUserBonus editWxUserBonus(WxUserBonus wxUserBonus);
	
	/**
	 * 批量修改WxUserBonus
	 * @param wxUserBonuss WxUserBonuss
	 * @return List<WxUserBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxUserBonus> editWxUserBonuss(List<WxUserBonus> wxUserBonuss);
	
	/**
	 * 删除WxUserBonus
	 * @param WxUserBonus WxUserBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxUserBonus(WxUserBonus wxUserBonus);
	
	/**
	 * 批量删除WxUserBonus
	 * @param WxUserBonuss WxUserBonuss
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxUserBonuss(List<WxUserBonus> wxUserBonuss);
	
	/**
	 * 根据WxUserBonus' id，删除WxUserBonus
	 * @param wxUserBonusId WxUserBonus's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxUserBonus(String wxUserBonusId);
	
	/**
	 * 根据WxUserBonus' id，获取WxUserBonus
	 * @param wxUserBonusId WxUserBonus's id
	 * @return WxUserBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxUserBonus getWxUserBonus(String wxUserBonusId); 
	
	/**
	 * 获取所有WxUserBonus
	 * @return List<WxUserBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxUserBonus> getAllWxUserBonuss();

	/**
	 * 根据ownerId bonusId 获取微信红包
	 * @param ownerId
	 * @param bonusId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2015-1-30
	 * @see
	 */
	public WxUserBonus getWxUserBonusByOwnerIdAndbonusId(String ownerId,
			String bonusId);
}
