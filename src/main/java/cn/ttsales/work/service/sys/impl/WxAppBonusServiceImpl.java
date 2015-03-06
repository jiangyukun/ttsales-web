/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename WxAppBonusServiceImpl.java
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

import cn.ttsales.work.domain.WxAppBonus;
import cn.ttsales.work.persistence.sys.WxAppBonusDao;
import cn.ttsales.work.service.sys.WxAppBonusService;


/**
 * WxAppBonus Service Impl
 * @author dandyzheng
 *
 */
@Service("wxAppBonusService")
public class WxAppBonusServiceImpl implements WxAppBonusService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private WxAppBonusDao wxAppBonusDao;

	@Transactional
	public WxAppBonus saveWxAppBonus(WxAppBonus wxAppBonus) {
		return wxAppBonusDao.saveWxAppBonus(wxAppBonus);
	}

	@Transactional
	public List<WxAppBonus> saveWxAppBonuss(List<WxAppBonus> wxAppBonuss) {
		return null;
	}

	@Transactional
	public WxAppBonus editWxAppBonus(WxAppBonus wxAppBonus) {
		return wxAppBonusDao.editWxAppBonus(wxAppBonus);
	}

	@Transactional
	public List<WxAppBonus> editWxAppBonuss(List<WxAppBonus> wxAppBonuss) {
		return wxAppBonusDao.editWxAppBonuss(wxAppBonuss);
	}

	@Transactional
	public void removeWxAppBonus(WxAppBonus WxAppBonus) {
		wxAppBonusDao.removeWxAppBonus(WxAppBonus);
	}

	@Transactional
	public void removeWxAppBonuss(List<WxAppBonus> WxAppBonuss) {
		wxAppBonusDao.removeWxAppBonuss(WxAppBonuss);		
	}

	@Transactional
	public void removeWxAppBonus(String wxAppBonusId) {
		wxAppBonusDao.removeWxAppBonus(wxAppBonusId);		
	}

	public WxAppBonus getWxAppBonus(String wxAppBonusId) {
		return wxAppBonusDao.getWxAppBonus(wxAppBonusId);
	}

	public List<WxAppBonus> getAllWxAppBonuss() {
		return wxAppBonusDao.getAllWxAppBonuss();
	}
}
