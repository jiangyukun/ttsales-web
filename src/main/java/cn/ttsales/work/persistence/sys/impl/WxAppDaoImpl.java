/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename WxAppDaoImpl.java
 * @package cn.ttsales.work.persistence.rbs.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.domain.WxApp;
import cn.ttsales.work.persistence.sys.WxAppDao;


/**
 * WxApp Dao Impl
 * @author dandyzheng
 *
 */
@Repository("wxAppDao")
public class WxAppDaoImpl extends AbstractFacade implements WxAppDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public WxApp saveWxApp(WxApp wxApp) {
		return persist(wxApp);
	}

	public List<WxApp> saveWxApps(List<WxApp> wxApps) {
		return persist(wxApps);
	}

	public WxApp editWxApp(WxApp wxApp) {
		return merge(wxApp);
	}

	public List<WxApp> editWxApps(List<WxApp> wxApps) {
		return merge(wxApps);
	}

	public void removeWxApp(WxApp wxApp) {
		remove(getWxApp(wxApp.getAppid()));
	}

	public void removeWxApps(List<WxApp> wxApps) {
		remove(wxApps);
	}

	public void removeWxApp(String wxAppId) {
		remove(wxAppId);
	}

	public WxApp getWxApp(String wxAppId) {
		return find(WxApp.class, wxAppId);
	}

	public List<WxApp> getAllWxApps() {
		return this.find("from WxApp s");
	}

}
