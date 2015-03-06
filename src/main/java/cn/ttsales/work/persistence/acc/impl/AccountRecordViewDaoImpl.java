/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename AccountRecordViewDaoImpl.java
 * @package cn.ttsales.work.persistence.acc.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.acc.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.domain.AccountRecordView;
import cn.ttsales.work.persistence.acc.AccountRecordViewDao;


/**
 * AccountRecordView Dao Impl
 * @author dandyzheng
 *
 */
@Repository("accountRecordViewDao")
public class AccountRecordViewDaoImpl extends AbstractFacade implements AccountRecordViewDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public AccountRecordView saveAccountRecordView(AccountRecordView accountRecordView) {
		return persist(accountRecordView);
	}

	public List<AccountRecordView> saveAccountRecordViews(List<AccountRecordView> accountRecordViews) {
		return persist(accountRecordViews);
	}

	public AccountRecordView editAccountRecordView(AccountRecordView accountRecordView) {
		return merge(accountRecordView);
	}

	public List<AccountRecordView> editAccountRecordViews(List<AccountRecordView> accountRecordViews) {
		return merge(accountRecordViews);
	}

	public void removeAccountRecordView(AccountRecordView accountRecordView) {
		remove(getAccountRecordView(accountRecordView.getId()));
	}

	public void removeAccountRecordViews(List<AccountRecordView> accountRecordViews) {
		remove(accountRecordViews);
	}

	public void removeAccountRecordView(String accountRecordViewId) {
		remove(accountRecordViewId);
	}

	public AccountRecordView getAccountRecordView(String accountRecordViewId) {
		return find(AccountRecordView.class, accountRecordViewId);
	}

	public List<AccountRecordView> getAllAccountRecordViews() {
		return this.find("from AccountRecordView s");
	}

}
