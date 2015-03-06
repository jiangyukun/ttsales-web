/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename RepTransmitService.java
 * @package cn.ttsales.work.service.rep
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.rep;

import java.util.List;

import cn.ttsales.work.domain.RepTransmit;
import cn.ttsales.work.dto.RepPopularizeTransmitReportDTO;
import cn.ttsales.work.dto.RepTransmitDTO;


/**
 * RepTransmit Service
 * @author dandyzheng
 *
 */
public interface RepTransmitService {
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
	 * 根据企业成员ID,推广信息ID,获取所有的子转发（包括自己）
	 * @param userId 企业成员ID
	 * @param popularizeId 推广信息ID
	 * @return 所有的子转发
	 * @author dandyzheng
	 * @date 2014-8-13
	 * @see
	 */
	public List<RepTransmitDTO> queryAllChildTransmits(String userId,String popularizeId);
	
	public List<RepTransmitDTO> createEdgeNodes(String userId,String popularizeId,String depth);
	
	public List<RepTransmitDTO> createChildEdgeNodes(String repTransmitId);
	
	
	public List<RepPopularizeTransmitReportDTO> queryReportTransmitData(String userId);
	
	/**
	 * 获取用户在转发表中按时间排序的位置
	 * @param userCrossId
	 * @param popularizeId
	 * @return
	 */
	public int queryTransmitRank(String userCrossId, String schemeId);
}
