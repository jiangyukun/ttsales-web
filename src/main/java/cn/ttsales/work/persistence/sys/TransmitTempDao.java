/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename SysRegionDao.java
 * @package cn.ttsales.work.persistence.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys;

import java.util.List;

import net.sf.json.JSONObject;
import cn.ttsales.work.dto.MyPerformanceDTO;
import cn.ttsales.work.dto.PerformanceAnalyzeDTO;
import cn.ttsales.work.dto.PopulStatDTO;
import cn.ttsales.work.dto.PopulStatSumDTO;
import cn.ttsales.work.dto.SchemePerformanceDTO;
import cn.ttsales.work.dto.SysMemberCountDTO;
import cn.ttsales.work.dto.SysSalerCountDTO;
import cn.ttsales.work.dto.TrackUserDTO;




/**
 * SysRegion DAO
 * @author dandyzheng
 *
 */
public interface TransmitTempDao {
	/**
	 * 更新转发临时表
	 * @param date
	 * @author zhaoxiaobin
	 * @date 2014-11-10
	 * @see
	 */
	public void updateTransmitTemp(String date);

	/**
	 * 根据月份和用户id获取我的奖励
	 * @param selectMonth
	 * @param userId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-10
	 * @see
	 */
	public List<MyPerformanceDTO> getMyPerformance(String selectMonth, String userId);

	/**
	 * 根据参数获取业绩分析
	 * @param popularizeId
	 * @param showType
	 * @param userId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-11
	 * @see
	 */
	public List<PerformanceAnalyzeDTO> getPerformanceAnalyze(String popularizeId,
			int showType, String userId);

	/**
	 * 获取追踪用户
	 * @param popularizeId
	 * @param userCrossId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-11
	 * @see
	 */
	public List<TrackUserDTO> getTrackUserDTOs(String popularizeId,
			String userCrossId);

	/**
	 * 获取用户业绩
	 * @param popularizeId
	 * @param userCrossId
	 * @param showType
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-11
	 * @see
	 */
	public JSONObject getUserPerformance(String popularizeId,
			String userCrossId, int showType);
	
	/**
	 * 获取推广统计
	 * @param schemeId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-11
	 * @see
	 */
	public List<PopulStatDTO> getPopulStatDTOs(String schemeId,
			String startDate, String endDate);
	
	/**
	 * 获取推广统计总和
	 * @param schemeId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-11
	 * @see
	 */
	public PopulStatSumDTO getPopulStatSumDTO(String schemeId,
			String startDate, String endDate);
	
	/**
	 * 获取表格数据
	 * @param type
	 * @param schemeId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-11
	 * @see
	 */
	public List<SysMemberCountDTO> getSysMemberCountDTOs(String type,
			String schemeId, String startDate, String endDate);
	
	/**
	 * 获得销售业绩
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-11
	 * @see
	 */
	public List<SysSalerCountDTO> getSysSalerCountDTOsByConditions(
			String deptId, String startDate, String endDate);
	 
		
	/**
	 * 根据推广id，获取用户、以及用户好友在某个文案中的业绩。
	 * @param memeberId
	 * @param schemeId
	 * @return
	 */
	public SchemePerformanceDTO queryMemberPerformanceInScheme(String popularizeId);
	
	/**
	 * 根据推广id获取用户朋友或用户的转发链路下的各项统计排行
	 * @param userId
	 * @param popularizeId
	 * @param rankRange 排行范围(friend（一级转发即朋友）、all（链路上全部的）)
	 * @return
	 */
	public JSONObject queryPerformanceRank(String popularizeId, String rankRange);
}
