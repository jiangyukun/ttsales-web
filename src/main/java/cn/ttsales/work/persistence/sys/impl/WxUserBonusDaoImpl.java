/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename WxUserBonusDaoImpl.java
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
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.domain.WxUserBonus;
import cn.ttsales.work.persistence.sys.WxUserBonusDao;


/**
 * WxUserBonus Dao Impl
 * @author dandyzheng
 *
 */
@Repository("wxUserBonusDao")
public class WxUserBonusDaoImpl extends AbstractFacade implements WxUserBonusDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public WxUserBonus saveWxUserBonus(WxUserBonus wxUserBonus) {
		return persist(wxUserBonus);
	}

	public List<WxUserBonus> saveWxUserBonuss(List<WxUserBonus> wxUserBonuss) {
		return persist(wxUserBonuss);
	}

	public WxUserBonus editWxUserBonus(WxUserBonus wxUserBonus) {
		return merge(wxUserBonus);
	}

	public List<WxUserBonus> editWxUserBonuss(List<WxUserBonus> wxUserBonuss) {
		return merge(wxUserBonuss);
	}

	public void removeWxUserBonus(WxUserBonus wxUserBonus) {
		remove(getWxUserBonus(wxUserBonus.getUserBonusId()));
	}

	public void removeWxUserBonuss(List<WxUserBonus> wxUserBonuss) {
		remove(wxUserBonuss);
	}

	public void removeWxUserBonus(String wxUserBonusId) {
		remove(wxUserBonusId);
	}

	public WxUserBonus getWxUserBonus(String wxUserBonusId) {
		return find(WxUserBonus.class, wxUserBonusId);
	}

	public List<WxUserBonus> getAllWxUserBonuss() {
		return this.find("from WxUserBonus s");
	}

	public WxUserBonus getWxUserBonusByOwnerIdAndbonusId(String ownerId,
			String bonusId) {
		List<WxUserBonus>  results  = this.find("from WxUserBonus s where s.ownerId = ? and s.wxAppBonus.bonusId = ?",ownerId,bonusId);
		if(ArrayUtil.isEmpty(results)){
			return null;
		}
		return results.get(0);
	}
}