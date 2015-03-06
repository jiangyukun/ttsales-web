/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename BusBonusService.java
 * @package cn.ttsales.work.service.bus
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.bus;

import java.util.List;

import cn.ttsales.work.domain.BusBonus;


/**
 * BusBonus Service
 * @author dandyzheng
 *
 */
public interface BusBonusService {
	/**
	 * 保存 BusBonus
	 * @param busBonus BusBonus
	 * @return BusBonus 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusBonus saveBusBonus(BusBonus busBonus);
	
	/**
	 * 批量保存BusBonus
	 * @param busBonuss BusBonuss
	 * @return List<BusBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusBonus> saveBusBonuss(List<BusBonus> busBonuss);
	
	/**
	 * 修改BusBonus
	 * @param busBonus BusBonus
	 * @return BusBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusBonus editBusBonus(BusBonus busBonus);
	
	/**
	 * 批量修改BusBonus
	 * @param busBonuss BusBonuss
	 * @return List<BusBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusBonus> editBusBonuss(List<BusBonus> busBonuss);
	
	/**
	 * 删除BusBonus
	 * @param BusBonus BusBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusBonus(BusBonus busBonus);
	
	/**
	 * 批量删除BusBonus
	 * @param BusBonuss BusBonuss
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusBonuss(List<BusBonus> busBonuss);
	
	/**
	 * 根据BusBonus' id，删除BusBonus
	 * @param busBonusId BusBonus's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusBonus(String busBonusId);
	
	/**
	 * 根据BusBonus' id，获取BusBonus
	 * @param busBonusId BusBonus's id
	 * @return BusBonus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusBonus getBusBonus(String busBonusId); 
	
	/**
	 * 获取所有BusBonus
	 * @return List<BusBonus>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusBonus> getAllBusBonuss();	
}
