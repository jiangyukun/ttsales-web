/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename WxAppUserDaoImpl.java
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
import cn.ttsales.work.core.util.ListUtil;
import cn.ttsales.work.domain.WxAppUser;
import cn.ttsales.work.persistence.sys.WxAppUserDao;


/**
 * WxAppUser Dao Impl
 * @author dandyzheng
 *
 */
@Repository("wxAppUserDao")
public class WxAppUserDaoImpl extends AbstractFacade implements WxAppUserDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public WxAppUser saveWxAppUser(WxAppUser wxAppUser) {
		return persist(wxAppUser);
	}

	public List<WxAppUser> saveWxAppUsers(List<WxAppUser> wxAppUsers) {
		return persist(wxAppUsers);
	}

	public WxAppUser editWxAppUser(WxAppUser wxAppUser) {
		return merge(wxAppUser);
	}

	public List<WxAppUser> editWxAppUsers(List<WxAppUser> wxAppUsers) {
		return merge(wxAppUsers);
	}

	public void removeWxAppUser(WxAppUser wxAppUser) {
		remove(getWxAppUser(wxAppUser.getAppUserId()));
	}

	public void removeWxAppUsers(List<WxAppUser> wxAppUsers) {
		remove(wxAppUsers);
	}

	public void removeWxAppUser(String wxAppUserId) {
		remove(wxAppUserId);
	}

	public WxAppUser getWxAppUser(String wxAppUserId) {
		return find(WxAppUser.class, wxAppUserId);
	}

	public List<WxAppUser> getAllWxAppUsers() {
		return this.find("from WxAppUser s");
	}

	public WxAppUser queryWxAppUserByAppIdAndWCOpenId(String appId,
			String openId) {
		List<WxAppUser> wxAppUsers  = this.find("from WxAppUser s where s.appid = ? and s.wcOpenId = ?",new String[]{appId,openId});
		if(ListUtil.isEmpty(wxAppUsers)){
			return null;
		}
		return 	wxAppUsers.get(0);
	}

	public WxAppUser queryWxAppUserByAppIdAndUserId(String appId, String userId) {
		List<WxAppUser> wxAppUsers  = this.find("from WxAppUser s where s.appid = ? and s.userId = ?",new String[]{appId,userId});
		if(ListUtil.isEmpty(wxAppUsers)){
			return null;
		}
		return 	wxAppUsers.get(0);
	}

	public WxAppUser getWxAppUserByWcOpenId(String openId) {
		List<WxAppUser> wxAppUsers  = this.find("from WxAppUser s where  s.wcOpenId = ?",openId);
		if(ListUtil.isEmpty(wxAppUsers)){
			return null;
		}
		return 	wxAppUsers.get(0);
	}

}
