/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename RepUserCrossServiceImpl.java
 * @package cn.ttsales.work.service.rep.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.rep.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.domain.RepOpenUser;
import cn.ttsales.work.domain.RepUserCross;
import cn.ttsales.work.persistence.rep.RepOpenUserDao;
import cn.ttsales.work.persistence.rep.RepUserCrossDao;
import cn.ttsales.work.service.rep.RepUserCrossService;


/**
 * RepUserCross Service Impl
 * @author dandyzheng
 *
 */
@Service("repUserCrossService")
public class RepUserCrossServiceImpl implements RepUserCrossService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private RepUserCrossDao repUserCrossDao;
	
	@Autowired
	private RepOpenUserDao repOpenUserDao;

	@Transactional
	public RepUserCross saveRepUserCross(RepUserCross repUserCross) {
		return repUserCrossDao.saveRepUserCross(repUserCross);
	}

	@Transactional
	public List<RepUserCross> saveRepUserCrosss(List<RepUserCross> repUserCrosss) {
		return null;
	}

	@Transactional
	public RepUserCross editRepUserCross(RepUserCross repUserCross) {
		return repUserCrossDao.editRepUserCross(repUserCross);
	}

	@Transactional
	public List<RepUserCross> editRepUserCrosss(List<RepUserCross> repUserCrosss) {
		return repUserCrossDao.editRepUserCrosss(repUserCrosss);
	}

	@Transactional
	public void removeRepUserCross(RepUserCross RepUserCross) {
		repUserCrossDao.removeRepUserCross(RepUserCross);
	}

	@Transactional
	public void removeRepUserCrosss(List<RepUserCross> RepUserCrosss) {
		repUserCrossDao.removeRepUserCrosss(RepUserCrosss);		
	}

	@Transactional
	public void removeRepUserCross(String repUserCrossId) {
		repUserCrossDao.removeRepUserCross(repUserCrossId);		
	}

	public RepUserCross getRepUserCross(String repUserCrossId) {
		return repUserCrossDao.getRepUserCross(repUserCrossId);
	}

	public List<RepUserCross> getAllRepUserCrosss() {
		return repUserCrossDao.getAllRepUserCrosss();
	}
	
	public List<RepUserCross> queryRepUserCrossByUserId(String userId,String type) {
		return repUserCrossDao.queryRepUserCross(userId, type);
	}
	/**
	 * 获取认证信息后更细OpenUserId， 并写openUser信息
	 * @param repUserCross
	 * @param repOpenUser
	 * @return
	 */
	@Transactional
	public RepUserCross editOpenUser(RepUserCross repUserCross,RepOpenUser repOpenUser) {
		RepUserCross returnRepUserCross = repUserCrossDao.editRepUserCross(repUserCross);
		repOpenUserDao.editRepOpenUser(repOpenUser);
		return returnRepUserCross;

	}
}
