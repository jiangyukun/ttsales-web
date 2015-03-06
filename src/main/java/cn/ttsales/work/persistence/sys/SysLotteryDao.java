/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename SysLotteryDao.java
 * @package cn.ttsales.work.persistence.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys;

import java.util.List;

import cn.ttsales.work.domain.SysLottery;
import cn.ttsales.work.dto.LotteryRecordDTO;



/**
 * SysLottery DAO
 * @author dandyzheng
 *
 */
public interface SysLotteryDao {
	/**
	 * 保存 SysLottery
	 * @param sysLottery SysLottery
	 * @return SysLottery 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysLottery saveSysLottery(SysLottery sysLottery);
	
	/**
	 * 批量保存SysLottery
	 * @param sysLotterys SysLotterys
	 * @return List<SysLottery>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysLottery> saveSysLotterys(List<SysLottery> sysLotterys);
	
	/**
	 * 修改SysLottery
	 * @param sysLottery SysLottery
	 * @return SysLottery
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysLottery editSysLottery(SysLottery sysLottery);
	
	/**
	 * 批量修改SysLottery
	 * @param sysLotterys SysLotterys
	 * @return List<SysLottery>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysLottery> editSysLotterys(List<SysLottery> sysLotterys);
	
	/**
	 * 删除SysLottery
	 * @param SysLottery SysLottery
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysLottery(SysLottery sysLottery);
	
	/**
	 * 批量删除SysLottery
	 * @param SysLotterys SysLotterys
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysLotterys(List<SysLottery> sysLotterys);
	
	/**
	 * 根据SysLottery' id，删除SysLottery
	 * @param sysLotteryId SysLottery's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysLottery(String sysLotteryId);
	
	/**
	 * 根据SysLottery' id，获取SysLottery
	 * @param sysLotteryId SysLottery's id
	 * @return SysLottery
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysLottery getSysLottery(String sysLotteryId); 
	
	/**
	 * 获取所有SysLottery
	 * @return List<SysLottery>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysLottery> getAllSysLotterys();
	
	/**
	 * 更新抽奖表中用户id的字段
	 * @param userId
	 * @param lotteryId
	 * @return
	 */
	public int updateLotteryUserId(String userId, String lotteryId);
	
	/**
	 * 获取一个未确定用户的lottery
	 * @return
	 */
	public SysLottery getLotteryByRank(String deptId);
	
	/**
	 * 随机获取一个用户未抽取的lottery
	 * @param userId
	 * @return
	 */
	public SysLottery getUserLotteryByRank(String userId, String deptId);
	
	/**
	 * 查询用户可以抽奖的个数
	 * @param userId
	 * @return
	 */
	public int queryCanLotteryCount(String userId, String deptId);
	
	
	/**
	 * 根据用户id和部门id获取抽奖的记录，
	 * userId不为null查询部门用户的抽奖中奖记录，userId为null查询整个部门的抽奖中奖记录
	 * 
	 * @param userId
	 * @param deptId
	 * @return
	 */
	public List<LotteryRecordDTO> queryLotterRecordByUserIdAndDeptId(String userId, String deptId);
}
