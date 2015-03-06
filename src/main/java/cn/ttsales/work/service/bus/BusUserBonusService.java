/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename BusUserBonusService.java
 * @package cn.ttsales.work.service.bus
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.bus;

import java.util.List;

import cn.ttsales.work.domain.BusUserBonus;
import cn.ttsales.work.dto.MyBonusDetailDTO;


/**
 * BusUserBonus Service
 * @author dandyzheng
 *
 */
public interface BusUserBonusService {
	/**
	 * 保存 BusUserBonus
	 * @param busUserBonus BusUserBonus
	 * @return BusUserBonus 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusUserBonus saveBusUserBonus(BusUserBonus busUserBonus);
	
	/**
	 * 批量保存BusUserBonus
	 * @param busUserBonuss BusUserBonuss
	 * @return List<BusUserBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusUserBonus> saveBusUserBonuss(List<BusUserBonus> busUserBonuss);
	
	/**
	 * 修改BusUserBonus
	 * @param busUserBonus BusUserBonus
	 * @return BusUserBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusUserBonus editBusUserBonus(BusUserBonus busUserBonus);
	
	/**
	 * 批量修改BusUserBonus
	 * @param busUserBonuss BusUserBonuss
	 * @return List<BusUserBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusUserBonus> editBusUserBonuss(List<BusUserBonus> busUserBonuss);
	
	/**
	 * 删除BusUserBonus
	 * @param BusUserBonus BusUserBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusUserBonus(BusUserBonus busUserBonus);
	
	/**
	 * 批量删除BusUserBonus
	 * @param BusUserBonuss BusUserBonuss
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusUserBonuss(List<BusUserBonus> busUserBonuss);
	
	/**
	 * 根据BusUserBonus' id，删除BusUserBonus
	 * @param busUserBonusId BusUserBonus's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusUserBonus(String busUserBonusId);
	
	/**
	 * 根据BusUserBonus' id，获取BusUserBonus
	 * @param busUserBonusId BusUserBonus's id
	 * @return BusUserBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusUserBonus getBusUserBonus(String busUserBonusId); 
	
	/**
	 * 获取所有BusUserBonus
	 * @return List<BusUserBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusUserBonus> getAllBusUserBonuss();

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<BusUserBonus> getBusBonusesByUserId(String userId);
	
	/**
	 * 
	 * @param userId
	 * @param userType
	 * @param date
	 * @return
	 */
	public List<MyBonusDetailDTO> getMyBonusDetailInMonths(String userId,
			String userType, String date);
}
