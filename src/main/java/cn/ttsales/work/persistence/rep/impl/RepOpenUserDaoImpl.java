/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename RepOpenUserDaoImpl.java
 * @package cn.ttsales.work.persistence.rep.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.rep.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.domain.RepOpenUser;
import cn.ttsales.work.persistence.rep.RepOpenUserDao;


/**
 * RepOpenUser Dao Impl
 * @author dandyzheng
 *
 */
@Repository("repOpenUserDao")
public class RepOpenUserDaoImpl extends AbstractFacade implements RepOpenUserDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public RepOpenUser saveRepOpenUser(RepOpenUser repOpenUser) {
		return persist(repOpenUser);
	}

	public List<RepOpenUser> saveRepOpenUsers(List<RepOpenUser> repOpenUsers) {
		return persist(repOpenUsers);
	}

	public RepOpenUser editRepOpenUser(RepOpenUser repOpenUser) {
		return merge(repOpenUser);
	}

	public List<RepOpenUser> editRepOpenUsers(List<RepOpenUser> repOpenUsers) {
		return merge(repOpenUsers);
	}

	public void removeRepOpenUser(RepOpenUser repOpenUser) {
		remove(getRepOpenUser(repOpenUser.getOpenId()));
	}

	public void removeRepOpenUsers(List<RepOpenUser> repOpenUsers) {
		remove(repOpenUsers);
	}

	public void removeRepOpenUser(String repOpenUserId) {
		remove(repOpenUserId);
	}

	public RepOpenUser getRepOpenUser(String repOpenUserId) {
		return find(RepOpenUser.class, repOpenUserId);
	}

	public List<RepOpenUser> getAllRepOpenUsers() {
		return this.find("from RepOpenUser s");
	}

	public List<RepOpenUser> getRepOpenUsersBySubscribeState(String subState) {
		return this.find("from RepOpenUser s where s.subscribeState = ?",subState);
	}

}
