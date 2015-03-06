/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename BusBonusServiceImpl.java
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

import cn.ttsales.work.domain.BusBonus;
import cn.ttsales.work.persistence.bus.BusBonusDao;
import cn.ttsales.work.service.bus.BusBonusService;


/**
 * BusBonus Service Impl
 * @author dandyzheng
 *
 */
@Service("busBonusService")
public class BusBonusServiceImpl implements BusBonusService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private BusBonusDao busBonusDao;

	@Transactional
	public BusBonus saveBusBonus(BusBonus busBonus) {
		return busBonusDao.saveBusBonus(busBonus);
	}

	@Transactional
	public List<BusBonus> saveBusBonuss(List<BusBonus> busBonuss) {
		return null;
	}

	@Transactional
	public BusBonus editBusBonus(BusBonus busBonus) {
		return busBonusDao.editBusBonus(busBonus);
	}

	@Transactional
	public List<BusBonus> editBusBonuss(List<BusBonus> busBonuss) {
		return busBonusDao.editBusBonuss(busBonuss);
	}

	@Transactional
	public void removeBusBonus(BusBonus BusBonus) {
		busBonusDao.removeBusBonus(BusBonus);
	}

	@Transactional
	public void removeBusBonuss(List<BusBonus> BusBonuss) {
		busBonusDao.removeBusBonuss(BusBonuss);		
	}

	@Transactional
	public void removeBusBonus(String busBonusId) {
		busBonusDao.removeBusBonus(busBonusId);		
	}

	public BusBonus getBusBonus(String busBonusId) {
		return busBonusDao.getBusBonus(busBonusId);
	}

	public List<BusBonus> getAllBusBonuss() {
		return busBonusDao.getAllBusBonuss();
	}
	
	
}
