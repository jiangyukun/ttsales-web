/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename BusUserAdvanceDao.java
 * @package cn.ttsales.work.persistence.bus
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.bus;

import java.util.List;

import cn.ttsales.work.domain.BusUserAdvance;



/**
 * BusUserAdvance DAO
 * @author dandyzheng
 *
 */
public interface BusUserAdvanceDao {
	/**
	 * 保存 BusUserAdvance
	 * @param busUserAdvance BusUserAdvance
	 * @return BusUserAdvance 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusUserAdvance saveBusUserAdvance(BusUserAdvance busUserAdvance);
	
	/**
	 * 批量保存BusUserAdvance
	 * @param busUserAdvances BusUserAdvances
	 * @return List<BusUserAdvance>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusUserAdvance> saveBusUserAdvances(List<BusUserAdvance> busUserAdvances);
	
	/**
	 * 修改BusUserAdvance
	 * @param busUserAdvance BusUserAdvance
	 * @return BusUserAdvance
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusUserAdvance editBusUserAdvance(BusUserAdvance busUserAdvance);
	
	/**
	 * 批量修改BusUserAdvance
	 * @param busUserAdvances BusUserAdvances
	 * @return List<BusUserAdvance>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusUserAdvance> editBusUserAdvances(List<BusUserAdvance> busUserAdvances);
	
	/**
	 * 删除BusUserAdvance
	 * @param BusUserAdvance BusUserAdvance
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusUserAdvance(BusUserAdvance busUserAdvance);
	
	/**
	 * 批量删除BusUserAdvance
	 * @param BusUserAdvances BusUserAdvances
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusUserAdvances(List<BusUserAdvance> busUserAdvances);
	
	/**
	 * 根据BusUserAdvance' id，删除BusUserAdvance
	 * @param busUserAdvanceId BusUserAdvance's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusUserAdvance(String busUserAdvanceId);
	
	/**
	 * 根据BusUserAdvance' id，获取BusUserAdvance
	 * @param busUserAdvanceId BusUserAdvance's id
	 * @return BusUserAdvance
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusUserAdvance getBusUserAdvance(String busUserAdvanceId); 
	
	/**
	 * 获取所有BusUserAdvance
	 * @return List<BusUserAdvance>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusUserAdvance> getAllBusUserAdvances();
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<BusUserAdvance> getBusUserAdvanceByUserId(String userId);
	
		
}
