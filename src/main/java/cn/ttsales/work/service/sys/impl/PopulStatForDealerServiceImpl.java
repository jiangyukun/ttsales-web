/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename SysRegionServiceImpl.java
 * @package cn.ttsales.work.service.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ttsales.work.dto.SysSalerCountDTO;
import cn.ttsales.work.persistence.sys.TransmitTempDao;
import cn.ttsales.work.service.sys.PopulStatForDealerService;

/**
 * SysRegion Service Impl
 * 
 * @author dandyzheng
 * 
 */
@Service("populStatForDealerService")
public class PopulStatForDealerServiceImpl implements PopulStatForDealerService,
		Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private TransmitTempDao transmitTempDao;
	
	
	public List<SysSalerCountDTO> getSysSalerCountDTOsByConditions(
			String deptId, String startDate, String endDate) {
 		 return transmitTempDao.getSysSalerCountDTOsByConditions(deptId,startDate,endDate);
 	}
	 
}
