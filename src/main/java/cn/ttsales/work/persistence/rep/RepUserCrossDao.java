/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename RepUserCrossDao.java
 * @package cn.ttsales.work.persistence.rep
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.rep;

import java.util.List;

import cn.ttsales.work.domain.RepUserCross;



/**
 * RepUserCross DAO
 * @author dandyzheng
 *
 */
public interface RepUserCrossDao {
	/**
	 * 保存 RepUserCross
	 * @param repUserCross RepUserCross
	 * @return RepUserCross 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepUserCross saveRepUserCross(RepUserCross repUserCross);
	
	/**
	 * 批量保存RepUserCross
	 * @param repUserCrosss RepUserCrosss
	 * @return List<RepUserCross>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepUserCross> saveRepUserCrosss(List<RepUserCross> repUserCrosss);
	
	/**
	 * 修改RepUserCross
	 * @param repUserCross RepUserCross
	 * @return RepUserCross
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepUserCross editRepUserCross(RepUserCross repUserCross);
	
	/**
	 * 批量修改RepUserCross
	 * @param repUserCrosss RepUserCrosss
	 * @return List<RepUserCross>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepUserCross> editRepUserCrosss(List<RepUserCross> repUserCrosss);
	
	/**
	 * 删除RepUserCross
	 * @param RepUserCross RepUserCross
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepUserCross(RepUserCross repUserCross);
	
	/**
	 * 批量删除RepUserCross
	 * @param RepUserCrosss RepUserCrosss
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepUserCrosss(List<RepUserCross> repUserCrosss);
	
	/**
	 * 根据RepUserCross' id，删除RepUserCross
	 * @param repUserCrossId RepUserCross's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepUserCross(String repUserCrossId);
	
	/**
	 * 根据RepUserCross' id，获取RepUserCross
	 * @param repUserCrossId RepUserCross's id
	 * @return RepUserCross
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepUserCross getRepUserCross(String repUserCrossId); 
	
	/**
	 * 获取所有RepUserCross
	 * @return List<RepUserCross>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepUserCross> getAllRepUserCrosss();
	
	/**
	 * 根据用户ID,类型查询用户对照ID
	 * @param userId
	 * @param type 参照 SASConstants.USER_CROSS_TYPE 
	 * @return List<RepUserCross>
	 * @author dandyzheng
	 * @date 2014-8-13
	 * @see
	 */
	public List<RepUserCross> queryRepUserCross(String userId,String type);
}
