/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename RepOpenUserServiceImpl.java
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
import cn.ttsales.work.service.rep.RepOpenUserService;


/**
 * RepOpenUser Service Impl
 * @author dandyzheng
 *
 */
@Service("repOpenUserService")
public class RepOpenUserServiceImpl implements RepOpenUserService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private RepOpenUserDao repOpenUserDao;
	@Autowired
	private RepUserCrossDao repUserCrossDao;

	@Transactional
	public RepOpenUser saveRepOpenUser(RepOpenUser repOpenUser) {
		return repOpenUserDao.saveRepOpenUser(repOpenUser);
	}

	@Transactional
	public List<RepOpenUser> saveRepOpenUsers(List<RepOpenUser> repOpenUsers) {
		return null;
	}

	@Transactional
	public RepOpenUser editRepOpenUser(RepOpenUser repOpenUser) {
		return repOpenUserDao.editRepOpenUser(repOpenUser);
	}

	@Transactional
	public List<RepOpenUser> editRepOpenUsers(List<RepOpenUser> repOpenUsers) {
		return repOpenUserDao.editRepOpenUsers(repOpenUsers);
	}

	@Transactional
	public void removeRepOpenUser(RepOpenUser RepOpenUser) {
		repOpenUserDao.removeRepOpenUser(RepOpenUser);
	}

	@Transactional
	public void removeRepOpenUsers(List<RepOpenUser> RepOpenUsers) {
		repOpenUserDao.removeRepOpenUsers(RepOpenUsers);		
	}

	@Transactional
	public void removeRepOpenUser(String repOpenUserId) {
		repOpenUserDao.removeRepOpenUser(repOpenUserId);		
	}

	public RepOpenUser getRepOpenUser(String repOpenUserId) {
		return repOpenUserDao.getRepOpenUser(repOpenUserId);
	}

	public List<RepOpenUser> getAllRepOpenUsers() {
		return repOpenUserDao.getAllRepOpenUsers();
	}

	@Transactional
	public void addRepOpenUser(RepOpenUser rUser,String userCrossId) {
		RepOpenUser user = repOpenUserDao.getRepOpenUser(rUser.getOpenId());
		RepUserCross  repUserCross = repUserCrossDao.getRepUserCross(userCrossId);
		repUserCross.setUserId(rUser.getOpenId());
		repUserCross.setType("03");
		if(user == null){
			repOpenUserDao.editRepOpenUser(rUser);
		}
		repUserCrossDao.editRepUserCross(repUserCross);
	}
	
	
}
