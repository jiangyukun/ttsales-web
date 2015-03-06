/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename RepUserCrossService.java
 * @package cn.ttsales.work.service.rep
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.rep;

import java.util.List;

import cn.ttsales.work.domain.RepOpenUser;
import cn.ttsales.work.domain.RepUserCross;


/**
 * RepUserCross Service
 * @author dandyzheng
 *
 */
public interface RepUserCrossService {
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
	 * 查询特定userId的RepUserCross
	 * @param userId
	 * @param type
	 * @return List<RepUserCross>
	 * @author zhangmizhong
	 * @date 2013-3-27
	 */
	public List<RepUserCross> queryRepUserCrossByUserId(String userId,String type);
	/**
	 * 获取认证信息后更细OpenUserId， 并写openUser信息
	 * @param repUserCross
	 * @param repOpenUser
	 * @return
	 */
	public RepUserCross editOpenUser(RepUserCross repUserCross,RepOpenUser repOpenUser);
}


