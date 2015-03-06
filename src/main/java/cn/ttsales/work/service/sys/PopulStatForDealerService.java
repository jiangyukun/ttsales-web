/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename SysRegionService.java
 * @package cn.ttsales.work.service.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys;

import java.util.List;

import cn.ttsales.work.dto.SysSalerCountDTO;




 
public interface PopulStatForDealerService {
	/**
	 * 根据条件获取SysSalerCountDTOs
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-9-23
	 * @see
	 */
	public List<SysSalerCountDTO> getSysSalerCountDTOsByConditions(String deptId,
			String startDate, String endDate);
	 
	 
}
