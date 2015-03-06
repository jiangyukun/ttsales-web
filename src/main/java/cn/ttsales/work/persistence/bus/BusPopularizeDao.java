/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename BusPopularizeDao.java
 * @package cn.ttsales.work.persistence.bus
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.bus;

import java.util.List;

import cn.ttsales.work.domain.BusPopularize;



/**
 * BusPopularize DAO
 * @author dandyzheng
 *
 */
public interface BusPopularizeDao {
	/**
	 * 保存 BusPopularize
	 * @param busPopularize BusPopularize
	 * @return BusPopularize 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusPopularize saveBusPopularize(BusPopularize busPopularize);
	
	/**
	 * 批量保存BusPopularize
	 * @param busPopularizes BusPopularizes
	 * @return List<BusPopularize>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusPopularize> saveBusPopularizes(List<BusPopularize> busPopularizes);
	
	/**
	 * 修改BusPopularize
	 * @param busPopularize BusPopularize
	 * @return BusPopularize
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusPopularize editBusPopularize(BusPopularize busPopularize);
	
	/**
	 * 批量修改BusPopularize
	 * @param busPopularizes BusPopularizes
	 * @return List<BusPopularize>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusPopularize> editBusPopularizes(List<BusPopularize> busPopularizes);
	
	/**
	 * 删除BusPopularize
	 * @param BusPopularize BusPopularize
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusPopularize(BusPopularize busPopularize);
	
	/**
	 * 批量删除BusPopularize
	 * @param BusPopularizes BusPopularizes
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusPopularizes(List<BusPopularize> busPopularizes);
	
	/**
	 * 根据BusPopularize' id，删除BusPopularize
	 * @param busPopularizeId BusPopularize's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeBusPopularize(String busPopularizeId);
	
	/**
	 * 根据BusPopularize' id，获取BusPopularize
	 * @param busPopularizeId BusPopularize's id
	 * @return BusPopularize
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public BusPopularize getBusPopularize(String busPopularizeId); 
	
	/**
	 * 获取所有BusPopularize
	 * @return List<BusPopularize>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<BusPopularize> getAllBusPopularizes();
	
	/**
	 * 根据成员ID 获取该成员的推广信息
	 * @param memberId 成员ID
	 * @return 推广信息列表
	 * @author dandyzheng
	 * @date 2014-8-12
	 * @see
	 */
	public List<BusPopularize> queryBusPopularizesByMemberId(String memberId);

	/**
	 * 修改推广信息状态
	 * @param popularizeId
	 * @param state
	 * @author zhaoxiaobin
	 * @date 2014-9-15
	 * @see
	 */
	public void changeStage(String popularizeId, String state);
	
	/**
	 * 根据用户id和推广文案状态，查询推广列表
	 * @param memberId
	 * @param states 文案状态，不传state参数代表查询所有文案，带state参数代表查询相应状态的文案
	 * @return
	 */
	public List<BusPopularize> queryMemberBusPopularizedByState(String memberId, String... states);
	
	public void saveWishRecord(String userCrossId, String transmitId, String popularizeId);
}
