/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename BusUserAdvanceServiceImpl.java
 * @package cn.ttsales.work.service.bus.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.bus.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.domain.BusUserAdvance;
import cn.ttsales.work.persistence.bus.BusUserAdvanceDao;
import cn.ttsales.work.service.bus.BusUserAdvanceService;


/**
 * BusUserAdvance Service Impl
 * @author dandyzheng
 *
 */
@Service("busUserAdvanceService")
public class BusUserAdvanceServiceImpl implements BusUserAdvanceService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private BusUserAdvanceDao busUserAdvanceDao;

	@Transactional
	public BusUserAdvance saveBusUserAdvance(BusUserAdvance busUserAdvance) {
		return busUserAdvanceDao.saveBusUserAdvance(busUserAdvance);
	}

	@Transactional
	public List<BusUserAdvance> saveBusUserAdvances(List<BusUserAdvance> busUserAdvances) {
		return null;
	}

	@Transactional
	public BusUserAdvance editBusUserAdvance(BusUserAdvance busUserAdvance) {
		return busUserAdvanceDao.editBusUserAdvance(busUserAdvance);
	}

	@Transactional
	public List<BusUserAdvance> editBusUserAdvances(List<BusUserAdvance> busUserAdvances) {
		return busUserAdvanceDao.editBusUserAdvances(busUserAdvances);
	}

	@Transactional
	public void removeBusUserAdvance(BusUserAdvance BusUserAdvance) {
		busUserAdvanceDao.removeBusUserAdvance(BusUserAdvance);
	}

	@Transactional
	public void removeBusUserAdvances(List<BusUserAdvance> BusUserAdvances) {
		busUserAdvanceDao.removeBusUserAdvances(BusUserAdvances);		
	}

	@Transactional
	public void removeBusUserAdvance(String busUserAdvanceId) {
		busUserAdvanceDao.removeBusUserAdvance(busUserAdvanceId);		
	}

	public BusUserAdvance getBusUserAdvance(String busUserAdvanceId) {
		return busUserAdvanceDao.getBusUserAdvance(busUserAdvanceId);
	}

	public List<BusUserAdvance> getAllBusUserAdvances() {
		return busUserAdvanceDao.getAllBusUserAdvances();
	}

	public List<BusUserAdvance> getBusUserAdvanceByUserId(String userId) {
		return busUserAdvanceDao.getBusUserAdvanceByUserId(userId);
	}
	
	
}
