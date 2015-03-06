/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename AccountSchemeViewServiceImpl.java
 * @package cn.ttsales.work.service.acc.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.acc.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.core.jpa.PageModel;
import cn.ttsales.work.core.jpa.PageParam;
import cn.ttsales.work.domain.AccountSchemeView;
import cn.ttsales.work.dto.MyRankDTO;
import cn.ttsales.work.dto.RankingListUserDTO;
import cn.ttsales.work.persistence.acc.AccountSchemeViewDao;
import cn.ttsales.work.service.acc.AccountSchemeViewService;


/**
 * AccountSchemeView Service Impl
 * @author dandyzheng
 *
 */
@Service("accountSchemeViewService")
public class AccountSchemeViewServiceImpl implements AccountSchemeViewService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private AccountSchemeViewDao accountSchemeViewDao;

	@Transactional
	public AccountSchemeView saveAccountSchemeView(AccountSchemeView accountSchemeView) {
		return accountSchemeViewDao.saveAccountSchemeView(accountSchemeView);
	}

	@Transactional
	public List<AccountSchemeView> saveAccountSchemeViews(List<AccountSchemeView> accountSchemeViews) {
		return null;
	}

	@Transactional
	public AccountSchemeView editAccountSchemeView(AccountSchemeView accountSchemeView) {
		return accountSchemeViewDao.editAccountSchemeView(accountSchemeView);
	}

	@Transactional
	public List<AccountSchemeView> editAccountSchemeViews(List<AccountSchemeView> accountSchemeViews) {
		return accountSchemeViewDao.editAccountSchemeViews(accountSchemeViews);
	}

	@Transactional
	public void removeAccountSchemeView(AccountSchemeView AccountSchemeView) {
		accountSchemeViewDao.removeAccountSchemeView(AccountSchemeView);
	}

	@Transactional
	public void removeAccountSchemeViews(List<AccountSchemeView> AccountSchemeViews) {
		accountSchemeViewDao.removeAccountSchemeViews(AccountSchemeViews);		
	}

	@Transactional
	public void removeAccountSchemeView(String accountSchemeViewId) {
		accountSchemeViewDao.removeAccountSchemeView(accountSchemeViewId);		
	}

	public AccountSchemeView getAccountSchemeView(String accountSchemeViewId) {
		return accountSchemeViewDao.getAccountSchemeView(accountSchemeViewId);
	}

	public List<AccountSchemeView> getAllAccountSchemeViews() {
		return accountSchemeViewDao.getAllAccountSchemeViews();
	}

	public PageModel<RankingListUserDTO> queryAccountSchemeViewDTOsBySchemeId(PageParam pageParam,
			String schemeId,String userType) {
		return accountSchemeViewDao.queryAccountSchemeViewDTOsBySchemeId(pageParam,schemeId,userType);
	}

	public MyRankDTO getMyRankDTOByMemberId(String memberId, String schemeId) {
		return accountSchemeViewDao.getMyRankDTOByMemberId(memberId,schemeId);
	}
	
	
}
