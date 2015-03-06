/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename WxAppBonusDaoImpl.java
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
import cn.ttsales.work.domain.WxAppBonus;
import cn.ttsales.work.persistence.sys.WxAppBonusDao;


/**
 * WxAppBonus Dao Impl
 * @author dandyzheng
 *
 */
@Repository("wxAppBonusDao")
public class WxAppBonusDaoImpl extends AbstractFacade implements WxAppBonusDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public WxAppBonus saveWxAppBonus(WxAppBonus wxAppBonus) {
		return persist(wxAppBonus);
	}

	public List<WxAppBonus> saveWxAppBonuss(List<WxAppBonus> wxAppBonuss) {
		return persist(wxAppBonuss);
	}

	public WxAppBonus editWxAppBonus(WxAppBonus wxAppBonus) {
		return merge(wxAppBonus);
	}

	public List<WxAppBonus> editWxAppBonuss(List<WxAppBonus> wxAppBonuss) {
		return merge(wxAppBonuss);
	}

	public void removeWxAppBonus(WxAppBonus wxAppBonus) {
		remove(getWxAppBonus(wxAppBonus.getBonusId()));
	}

	public void removeWxAppBonuss(List<WxAppBonus> wxAppBonuss) {
		remove(wxAppBonuss);
	}

	public void removeWxAppBonus(String wxAppBonusId) {
		remove(wxAppBonusId);
	}

	public WxAppBonus getWxAppBonus(String wxAppBonusId) {
		return find(WxAppBonus.class, wxAppBonusId);
	}

	public List<WxAppBonus> getAllWxAppBonuss() {
		return this.find("from WxAppBonus s");
	}
}
