/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename AccountRecordViewServiceImpl.java
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

import cn.ttsales.work.domain.AccountRecordView;
import cn.ttsales.work.persistence.acc.AccountRecordViewDao;
import cn.ttsales.work.service.acc.AccountRecordViewService;


/**
 * AccountRecordView Service Impl
 * @author dandyzheng
 *
 */
@Service("accountRecordViewService")
public class AccountRecordViewServiceImpl implements AccountRecordViewService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private AccountRecordViewDao accountRecordViewDao;

	@Transactional
	public AccountRecordView saveAccountRecordView(AccountRecordView accountRecordView) {
		return accountRecordViewDao.saveAccountRecordView(accountRecordView);
	}

	@Transactional
	public List<AccountRecordView> saveAccountRecordViews(List<AccountRecordView> accountRecordViews) {
		return null;
	}

	@Transactional
	public AccountRecordView editAccountRecordView(AccountRecordView accountRecordView) {
		return accountRecordViewDao.editAccountRecordView(accountRecordView);
	}

	@Transactional
	public List<AccountRecordView> editAccountRecordViews(List<AccountRecordView> accountRecordViews) {
		return accountRecordViewDao.editAccountRecordViews(accountRecordViews);
	}

	@Transactional
	public void removeAccountRecordView(AccountRecordView AccountRecordView) {
		accountRecordViewDao.removeAccountRecordView(AccountRecordView);
	}

	@Transactional
	public void removeAccountRecordViews(List<AccountRecordView> AccountRecordViews) {
		accountRecordViewDao.removeAccountRecordViews(AccountRecordViews);		
	}

	@Transactional
	public void removeAccountRecordView(String accountRecordViewId) {
		accountRecordViewDao.removeAccountRecordView(accountRecordViewId);		
	}

	public AccountRecordView getAccountRecordView(String accountRecordViewId) {
		return accountRecordViewDao.getAccountRecordView(accountRecordViewId);
	}

	public List<AccountRecordView> getAllAccountRecordViews() {
		return accountRecordViewDao.getAllAccountRecordViews();
	}
	
	
}
