/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename WxAppServiceImpl.java
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

import cn.ttsales.work.domain.WxApp;
import cn.ttsales.work.persistence.sys.WxAppDao;
import cn.ttsales.work.service.sys.WxAppService;


/**
 * WxApp Service Impl
 * @author dandyzheng
 *
 */
@Service("wxAppService")
public class WxAppServiceImpl implements WxAppService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private WxAppDao wxAppDao;

	@Transactional
	public WxApp saveWxApp(WxApp wxApp) {
		return wxAppDao.saveWxApp(wxApp);
	}

	@Transactional
	public List<WxApp> saveWxApps(List<WxApp> wxApps) {
		return null;
	}

	@Transactional
	public WxApp editWxApp(WxApp wxApp) {
		return wxAppDao.editWxApp(wxApp);
	}

	@Transactional
	public List<WxApp> editWxApps(List<WxApp> wxApps) {
		return wxAppDao.editWxApps(wxApps);
	}

	@Transactional
	public void removeWxApp(WxApp WxApp) {
		wxAppDao.removeWxApp(WxApp);
	}

	@Transactional
	public void removeWxApps(List<WxApp> WxApps) {
		wxAppDao.removeWxApps(WxApps);		
	}

	@Transactional
	public void removeWxApp(String wxAppId) {
		wxAppDao.removeWxApp(wxAppId);		
	}

	public WxApp getWxApp(String wxAppId) {
		return wxAppDao.getWxApp(wxAppId);
	}

	public List<WxApp> getAllWxApps() {
		return wxAppDao.getAllWxApps();
	}
}
