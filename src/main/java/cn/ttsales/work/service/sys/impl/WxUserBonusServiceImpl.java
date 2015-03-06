/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename WxUserBonusServiceImpl.java
 * @package cn.ttsales.work.service.rbs.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.domain.WxUserBonus;
import cn.ttsales.work.persistence.sys.WxUserBonusDao;
import cn.ttsales.work.service.sys.WxUserBonusService;


/**
 * WxUserBonus Service Impl
 * @author dandyzheng
 *
 */
@Service("wxUserBonusService")
public class WxUserBonusServiceImpl implements WxUserBonusService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	@Autowired
	private WxUserBonusDao wxUserBonusDao;
	
	@Transactional
	public WxUserBonus saveWxUserBonus(WxUserBonus wxUserBonus) {
		return wxUserBonusDao.saveWxUserBonus(wxUserBonus);
	}

	@Transactional
	public List<WxUserBonus> saveWxUserBonuss(List<WxUserBonus> wxUserBonuss) {
		return null;
	}

	@Transactional
	public WxUserBonus editWxUserBonus(WxUserBonus wxUserBonus) {
		return wxUserBonusDao.editWxUserBonus(wxUserBonus);
	}

	@Transactional
	public List<WxUserBonus> editWxUserBonuss(List<WxUserBonus> wxUserBonuss) {
		return wxUserBonusDao.editWxUserBonuss(wxUserBonuss);
	}

	@Transactional
	public void removeWxUserBonus(WxUserBonus WxUserBonus) {
		wxUserBonusDao.removeWxUserBonus(WxUserBonus);
	}

	@Transactional
	public void removeWxUserBonuss(List<WxUserBonus> WxUserBonuss) {
		wxUserBonusDao.removeWxUserBonuss(WxUserBonuss);		
	}

	@Transactional
	public void removeWxUserBonus(String wxUserBonusId) {
		wxUserBonusDao.removeWxUserBonus(wxUserBonusId);		
	}

	public WxUserBonus getWxUserBonus(String wxUserBonusId) {
		return wxUserBonusDao.getWxUserBonus(wxUserBonusId);
	}

	public List<WxUserBonus> getAllWxUserBonuss() {
		return wxUserBonusDao.getAllWxUserBonuss();
	}

	public WxUserBonus getWxUserBonusByOwnerIdAndbonusId(String ownerId,
			String bonusId) {
		return wxUserBonusDao.getWxUserBonusByOwnerIdAndbonusId(ownerId,bonusId);
	}
}
