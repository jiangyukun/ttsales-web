/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename RepTransmitDao.java
 * @package cn.ttsales.work.persistence.rep
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.rep;

import java.util.List;

import cn.ttsales.work.domain.RepTransmit;
import cn.ttsales.work.dto.RepTransmitDTO;
import cn.ttsales.work.dto.RepTransmitReportDTO;



/**
 * RepTransmit DAO
 * @author dandyzheng
 *
 */
public interface RepTransmitDao {
	/**
	 * 保存 RepTransmit
	 * @param repTransmit RepTransmit
	 * @return RepTransmit 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepTransmit saveRepTransmit(RepTransmit repTransmit);
	
	/**
	 * 批量保存RepTransmit
	 * @param repTransmits RepTransmits
	 * @return List<RepTransmit>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepTransmit> saveRepTransmits(List<RepTransmit> repTransmits);
	
	/**
	 * 修改RepTransmit
	 * @param repTransmit RepTransmit
	 * @return RepTransmit
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepTransmit editRepTransmit(RepTransmit repTransmit);
	
	/**
	 * 批量修改RepTransmit
	 * @param repTransmits RepTransmits
	 * @return List<RepTransmit>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepTransmit> editRepTransmits(List<RepTransmit> repTransmits);
	
	/**
	 * 删除RepTransmit
	 * @param RepTransmit RepTransmit
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepTransmit(RepTransmit repTransmit);
	
	/**
	 * 批量删除RepTransmit
	 * @param RepTransmits RepTransmits
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepTransmits(List<RepTransmit> repTransmits);
	
	/**
	 * 根据RepTransmit' id，删除RepTransmit
	 * @param repTransmitId RepTransmit's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepTransmit(String repTransmitId);
	
	/**
	 * 根据RepTransmit' id，获取RepTransmit
	 * @param repTransmitId RepTransmit's id
	 * @return RepTransmit
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepTransmit getRepTransmit(String repTransmitId); 
	
	/**
	 * 获取所有RepTransmit
	 * @return List<RepTransmit>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepTransmit> getAllRepTransmits();
	
	/**
	 * 根据用户ID,推广信息ID,获取初始转发记录
	 * @param userCrossId 用户ID
	 * @param popularizeId 推广信息ID
	 * @return List<RepTransmit> 初始转发记录
	 * @author dandyzheng
	 * @date 2014-8-13
	 * @see
	 */
	public List<RepTransmit> queryRepTransmit(String userCrossId,String popularizeId);
	
	/**
	 * 根据父转发ID获取所有的子转发（包括自己）
	 * @param pTransmitId 父转发ID
	 * @return 所有的子转发
	 * @author dandyzheng
	 * @date 2014-8-13
	 * @see
	 */
	public List<RepTransmitDTO> queryAllChildTransmits(String pTransmitId);
	
	
	public List<RepTransmitReportDTO> queryReportTransmitData(String userCrossId,String popularizeId );
	

	/**
	 * 获取用户在转发表中按时间排序的位置
	 * @param userCrossId
	 * @param popularizeId
	 * @return
	 */
	public int queryTransmitRank(String userCrossId, String schemeId);
}
