/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename SysSaleResultServiceImpl.java
 * @package cn.ttsales.work.service.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys.impl;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.dto.MyPerformanceDTO;
import cn.ttsales.work.dto.PerformanceAnalyzeDTO;
import cn.ttsales.work.dto.SchemePerformanceDTO;
import cn.ttsales.work.dto.TrackUserDTO;
import cn.ttsales.work.persistence.sys.TransmitTempDao;
import cn.ttsales.work.service.sys.TransmitTempService;


/**
 * SysSaleResult Service Impl
 * @author dandyzheng
 *
 */
@Service("transmitTempService")
public class TransmitTempServiceImpl implements TransmitTempService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private TransmitTempDao transmitTempDao;
	
	@Transactional
	public void updateTransmitTemp(String date) {
		transmitTempDao.updateTransmitTemp(date);
		
	}

	public List<MyPerformanceDTO> getMyPerformance(String selectMonth,
			String userId) {
		return transmitTempDao.getMyPerformance(selectMonth,userId);
	}

	public List<PerformanceAnalyzeDTO> getPerformanceAnalyze(String popularizeId,
			int showType, String userId) {
		return transmitTempDao.getPerformanceAnalyze(popularizeId,showType,userId);
	}

	public List<TrackUserDTO> getTrackUserDTOs(String popularizeId,
			String userCrossId) {
		return transmitTempDao.getTrackUserDTOs(popularizeId,userCrossId);
	}

	public JSONObject getUserPerformance(String popularizeId,
			String userCrossId, int showType) {
		return transmitTempDao.getUserPerformance(popularizeId,userCrossId,showType);
	}

	public SchemePerformanceDTO queryMemberPerformanceInScheme(String popularizeId) {
		return transmitTempDao.queryMemberPerformanceInScheme(popularizeId);
	}

	public JSONObject queryPerformanceRank(String popularizeId, String rankRange) {
		return transmitTempDao.queryPerformanceRank(popularizeId, rankRange);
	}
}
