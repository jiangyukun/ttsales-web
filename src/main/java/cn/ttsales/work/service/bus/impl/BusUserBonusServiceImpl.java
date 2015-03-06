/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename BusUserBonusServiceImpl.java
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

import cn.ttsales.work.domain.BusUserBonus;
import cn.ttsales.work.dto.MyBonusDetailDTO;
import cn.ttsales.work.persistence.bus.BusUserBonusDao;
import cn.ttsales.work.service.bus.BusUserBonusService;


/**
 * BusUserBonus Service Impl
 * @author dandyzheng
 *
 */
@Service("busUserBonusService")
public class BusUserBonusServiceImpl implements BusUserBonusService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private BusUserBonusDao busUserBonusDao;

	@Transactional
	public BusUserBonus saveBusUserBonus(BusUserBonus busUserBonus) {
		return busUserBonusDao.saveBusUserBonus(busUserBonus);
	}

	@Transactional
	public List<BusUserBonus> saveBusUserBonuss(List<BusUserBonus> busUserBonuss) {
		return null;
	}

	@Transactional
	public BusUserBonus editBusUserBonus(BusUserBonus busUserBonus) {
		return busUserBonusDao.editBusUserBonus(busUserBonus);
	}

	@Transactional
	public List<BusUserBonus> editBusUserBonuss(List<BusUserBonus> busUserBonuss) {
		return busUserBonusDao.editBusUserBonuss(busUserBonuss);
	}

	@Transactional
	public void removeBusUserBonus(BusUserBonus BusUserBonus) {
		busUserBonusDao.removeBusUserBonus(BusUserBonus);
	}

	@Transactional
	public void removeBusUserBonuss(List<BusUserBonus> BusUserBonuss) {
		busUserBonusDao.removeBusUserBonuss(BusUserBonuss);		
	}

	@Transactional
	public void removeBusUserBonus(String busUserBonusId) {
		busUserBonusDao.removeBusUserBonus(busUserBonusId);		
	}

	public BusUserBonus getBusUserBonus(String busUserBonusId) {
		return busUserBonusDao.getBusUserBonus(busUserBonusId);
	}

	public List<BusUserBonus> getAllBusUserBonuss() {
		return busUserBonusDao.getAllBusUserBonuss();
	}

	public List<BusUserBonus> getBusBonusesByUserId(String userId) {
		return busUserBonusDao.getBusBonusesByUserId(userId);
	}

	public List<MyBonusDetailDTO> getMyBonusDetailInMonths(String userId,
			String userType, String date) {
		return busUserBonusDao.getMyBonusDetailInMonths(userId, userType, date);
	}
	
	
}
