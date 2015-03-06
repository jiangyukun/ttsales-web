/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename BusUserBalanceService.java
 * @package cn.ttsales.work.service.bus
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.bus;

import java.util.List;
import java.util.Map;

import cn.ttsales.work.domain.BusUserBalance;


/**
 * BusUserBalance Service
 * @author dandyzheng
 *
 */
public interface BusUserBalanceService {
	/**
	 * 保存 BusUserBalance
	 * @param busUserBalance BusUserBalance
	 * @return BusUserBalance 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusUserBalance saveBusUserBalance(BusUserBalance busUserBalance);
	
	/**
	 * 批量保存BusUserBalance
	 * @param busUserBalances BusUserBalances
	 * @return List<BusUserBalance>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusUserBalance> saveBusUserBalances(List<BusUserBalance> busUserBalances);
	
	/**
	 * 修改BusUserBalance
	 * @param busUserBalance BusUserBalance
	 * @return BusUserBalance
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusUserBalance editBusUserBalance(BusUserBalance busUserBalance);
	
	/**
	 * 批量修改BusUserBalance
	 * @param busUserBalances BusUserBalances
	 * @return List<BusUserBalance>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusUserBalance> editBusUserBalances(List<BusUserBalance> busUserBalances);
	
	/**
	 * 删除BusUserBalance
	 * @param BusUserBalance BusUserBalance
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusUserBalance(BusUserBalance busUserBalance);
	
	/**
	 * 批量删除BusUserBalance
	 * @param BusUserBalances BusUserBalances
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusUserBalances(List<BusUserBalance> busUserBalances);
	
	/**
	 * 根据BusUserBalance' id，删除BusUserBalance
	 * @param busUserBalanceId BusUserBalance's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusUserBalance(String busUserBalanceId);
	
	/**
	 * 根据BusUserBalance' id，获取BusUserBalance
	 * @param busUserBalanceId BusUserBalance's id
	 * @return BusUserBalance
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusUserBalance getBusUserBalance(String busUserBalanceId); 
	
	/**
	 * 获取所有BusUserBalance
	 * @return List<BusUserBalance>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusUserBalance> getAllBusUserBalances();	
	
	public List<BusUserBalance> queryBusUserBalance(String userId,String userType);
	
	public int receiveBusUserBalance(BusUserBalance busUserBalance,float amount);
	
	public int rollbackBusUserBalance(BusUserBalance busUserBalance,float amount);
	
	public Map<String,String> handleReceiveBusUserBalance(String openId,BusUserBalance busUserBalance,float amount);
	
	
}
