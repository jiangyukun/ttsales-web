/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename WxAppUserServiceImpl.java
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

import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.WxAppUser;
import cn.ttsales.work.persistence.sys.WxAppUserDao;
import cn.ttsales.work.service.sys.WxAppUserService;


/**
 * WxAppUser Service Impl
 * @author dandyzheng
 *
 */
@Service("wxAppUserService")
public class WxAppUserServiceImpl implements WxAppUserService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private WxAppUserDao wxAppUserDao;

	@Transactional
	public WxAppUser saveWxAppUser(WxAppUser wxAppUser) {
		return wxAppUserDao.saveWxAppUser(wxAppUser);
	}

	@Transactional
	public List<WxAppUser> saveWxAppUsers(List<WxAppUser> wxAppUsers) {
		return null;
	}

	@Transactional
	public WxAppUser editWxAppUser(WxAppUser wxAppUser) {
		return wxAppUserDao.editWxAppUser(wxAppUser);
	}

	@Transactional
	public List<WxAppUser> editWxAppUsers(List<WxAppUser> wxAppUsers) {
		return wxAppUserDao.editWxAppUsers(wxAppUsers);
	}

	@Transactional
	public void removeWxAppUser(WxAppUser WxAppUser) {
		wxAppUserDao.removeWxAppUser(WxAppUser);
	}

	@Transactional
	public void removeWxAppUsers(List<WxAppUser> WxAppUsers) {
		wxAppUserDao.removeWxAppUsers(WxAppUsers);		
	}

	@Transactional
	public void removeWxAppUser(String wxAppUserId) {
		wxAppUserDao.removeWxAppUser(wxAppUserId);		
	}

	public WxAppUser getWxAppUser(String wxAppUserId) {
		return wxAppUserDao.getWxAppUser(wxAppUserId);
	}

	public List<WxAppUser> getAllWxAppUsers() {
		return wxAppUserDao.getAllWxAppUsers();
	}

	public WxAppUser queryWxAppUserByAppIdAndWCOpenId(String appId,
			String openId) {
		return wxAppUserDao.queryWxAppUserByAppIdAndWCOpenId(appId,openId);
	}

	public WxAppUser queryWxAppUserByAppIdAndUserId(String appId, String userId) {
		return wxAppUserDao.queryWxAppUserByAppIdAndUserId(appId,userId);
	}

	public WxAppUser getWxAppUserByWcOpenId(String openId) {
		if(StringUtil.isEmpty(openId)){
			return null;
		}
		return wxAppUserDao.getWxAppUserByWcOpenId(openId);
	}
	@Transactional
	public WxAppUser editWxAppUserByAppIdAndUserId(String appId,String userId,
			String wcOpenId) {
		WxAppUser wxAppUser =  wxAppUserDao.queryWxAppUserByAppIdAndUserId(appId,userId);
		if(wxAppUser==null){
			wxAppUser = new WxAppUser();
			wxAppUser.setAppid(appId);
			wxAppUser.setUserId(userId);
			wxAppUser.setWcOpenId(wcOpenId);
			wxAppUser = wxAppUserDao.saveWxAppUser(wxAppUser);	
		}
		return wxAppUser;
	}
	
	
}
