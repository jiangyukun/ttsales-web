/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename BusLotteryService.java
 * @package cn.ttsales.work.service.bus
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.bus;

import java.util.List;

import cn.ttsales.work.domain.BusLottery;
import cn.ttsales.work.dto.LotteryRecordDTO;
import cn.ttsales.work.dto.QyClaimLotteryDTO;
import net.sf.json.JSONObject;


/**
 * BusLottery Service
 * @author dandyzheng
 *
 */
public interface BusLotteryService {
	/**
	 * 保存 BusLottery
	 * @param busLottery BusLottery
	 * @return BusLottery 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusLottery saveBusLottery(BusLottery busLottery);
	
	/**
	 * 批量保存BusLottery
	 * @param busLotterys BusLotterys
	 * @return List<BusLottery>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusLottery> saveBusLotterys(List<BusLottery> busLotterys);
	
	/**
	 * 修改BusLottery
	 * @param busLottery BusLottery
	 * @return BusLottery
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusLottery editBusLottery(BusLottery busLottery);
	
	/**
	 * 批量修改BusLottery
	 * @param busLotterys BusLotterys
	 * @return List<BusLottery>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusLottery> editBusLotterys(List<BusLottery> busLotterys);
	
	/**
	 * 删除BusLottery
	 * @param BusLottery BusLottery
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusLottery(BusLottery busLottery);
	
	/**
	 * 批量删除BusLottery
	 * @param BusLotterys BusLotterys
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusLotterys(List<BusLottery> busLotterys);
	
	/**
	 * 根据BusLottery' id，删除BusLottery
	 * @param busLotteryId BusLottery's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusLottery(String busLotteryId);
	
	/**
	 * 根据BusLottery' id，获取BusLottery
	 * @param busLotteryId BusLottery's id
	 * @return BusLottery
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusLottery getBusLottery(String busLotteryId); 
	
	/**
	 * 获取所有BusLottery
	 * @return List<BusLottery>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusLottery> getAllBusLotterys();	
	
	/**
	 * 获取用户通过有效阅读获取的抽奖次数
	 * @param uId
	 * @return
	 */
	public int queryLotteryCountByReadGet(String deptId, String...userIds);
	
	
	/**
	 * 随机获取多个未确定用户的lottery
	 * @param userId
	 * @return
	 */
	public List<BusLottery> queryLotterysByRank(String deptId, int sum);

	/**
	 * 更新抽奖表中用户id的字段
	 * @param userId
	 * @return
	 */
	public int updateLotteryUserId(String userId,String deptId);
	
	/**
	 * 获取用户有效的阅读数
	 * @param uId
	 * @param deptId
	 * @return
	 */
	public int queryValidReadCount(String deptId, String schemeId, String userId, String openId);
	
	/**
	 * 查询用户可以抽奖的个数
	 * @param userId
	 * @return
	 */
	public int queryCanLotteryCount(String deptId, String userId, String openId);
	
	/**
	 * 根据lottery记录创建红包记录
	 * @param appId
	 * @param deptId
	 * @param userId
	 * @return JSONObject: bonus为获取的红包金额，bonusId为生成的红包id
	 */
	public JSONObject createWxUserBonusByLottery(String appId, QyClaimLotteryDTO lotDto);
	
	/**
	 * 根据用户id和部门id获取抽奖的记录，
	 * userId不为null查询部门用户的抽奖中奖记录，userId为null查询整个部门的抽奖中奖记录
	 * 
	 * @param userId
	 * @param deptId
	 * @return
	 */
	public List<LotteryRecordDTO> queryLotterRecordByUserIdAndDeptId(String deptId, String userId, String openId);
	
	/**
	 * 是否有通过转发获得的抽奖机会
	 * @param userId
	 * @return
	 */
	public boolean isHaveTransmitLottery(String userId, String schemeId);
}
