/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename RepReadCountDao.java
 * @package cn.ttsales.work.persistence.rep
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.rep;

import java.util.List;

import cn.ttsales.work.domain.RepReadCount;



/**
 * RepReadCount DAO
 * @author dandyzheng
 *
 */
public interface RepReadCountDao {
	/**
	 * 保存 RepReadCount
	 * @param repReadCount RepReadCount
	 * @return RepReadCount 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepReadCount saveRepReadCount(RepReadCount repReadCount);
	
	/**
	 * 批量保存RepReadCount
	 * @param repReadCounts RepReadCounts
	 * @return List<RepReadCount>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepReadCount> saveRepReadCounts(List<RepReadCount> repReadCounts);
	
	/**
	 * 修改RepReadCount
	 * @param repReadCount RepReadCount
	 * @return RepReadCount
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepReadCount editRepReadCount(RepReadCount repReadCount);
	
	/**
	 * 批量修改RepReadCount
	 * @param repReadCounts RepReadCounts
	 * @return List<RepReadCount>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepReadCount> editRepReadCounts(List<RepReadCount> repReadCounts);
	
	/**
	 * 删除RepReadCount
	 * @param RepReadCount RepReadCount
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepReadCount(RepReadCount repReadCount);
	
	/**
	 * 批量删除RepReadCount
	 * @param RepReadCounts RepReadCounts
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepReadCounts(List<RepReadCount> repReadCounts);
	
	/**
	 * 根据RepReadCount' id，删除RepReadCount
	 * @param repReadCountId RepReadCount's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepReadCount(String repReadCountId);
	
	/**
	 * 根据RepReadCount' id，获取RepReadCount
	 * @param repReadCountId RepReadCount's id
	 * @return RepReadCount
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepReadCount getRepReadCount(String repReadCountId); 
	
	/**
	 * 获取所有RepReadCount
	 * @return List<RepReadCount>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepReadCount> getAllRepReadCounts();
	
	/**
	 * 获取用户在文案中的有效阅读数
	 * @param userId
	 * @return
	 */
	public int querySomeOneValidReadCount(String schemeId, String... userIds);
}
