/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename BusUserBalanceDaoImpl.java
 * @package cn.ttsales.work.persistence.bus.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.bus.impl;

import java.text.NumberFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.domain.BusUserBalance;
import cn.ttsales.work.persistence.bus.BusUserBalanceDao;


/**
 * BusUserBalance Dao Impl
 * @author dandyzheng
 *
 */
@Repository("busUserBalanceDao")
public class BusUserBalanceDaoImpl extends AbstractFacade implements BusUserBalanceDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public BusUserBalance saveBusUserBalance(BusUserBalance busUserBalance) {
		return persist(busUserBalance);
	}

	public List<BusUserBalance> saveBusUserBalances(List<BusUserBalance> busUserBalances) {
		return persist(busUserBalances);
	}

	public BusUserBalance editBusUserBalance(BusUserBalance busUserBalance) {
		return merge(busUserBalance);
	}

	public List<BusUserBalance> editBusUserBalances(List<BusUserBalance> busUserBalances) {
		return merge(busUserBalances);
	}

	public void removeBusUserBalance(BusUserBalance busUserBalance) {
		remove(getBusUserBalance(busUserBalance.getBalanceId()));
	}

	public void removeBusUserBalances(List<BusUserBalance> busUserBalances) {
		remove(busUserBalances);
	}

	public void removeBusUserBalance(String busUserBalanceId) {
		remove(busUserBalanceId);
	}

	public BusUserBalance getBusUserBalance(String busUserBalanceId) {
		return find(BusUserBalance.class, busUserBalanceId);
	}

	public List<BusUserBalance> getAllBusUserBalances() {
		return this.find("from BusUserBalance s");
	}
	
	public List<BusUserBalance> queryBusUserBalance(String userId, String userType) {
		return this.find("from BusUserBalance s where s.userId = ? and s.userType = ?", new Object[]{userId, userType});
	}

	public int receiveBusUserBalance(BusUserBalance busUserBalance, float amount) {
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);
		float balance = Float.valueOf(format.format(busUserBalance.getBalance() - amount));
		
		String jpql = "update BusUserBalance s set s.balance = ? ,s.version=s.version + 1 where s.version = ?"
				+ " and s.balanceId = ? ";
		return this.execute(jpql, new Object[]{balance,busUserBalance.getVersion(),busUserBalance.getBalanceId()});
	}

	public int rollbackBusUserBalance(BusUserBalance busUserBalance,
			float amount) {
		
		/*NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);
		busUserBalance = this.getBusUserBalance(busUserBalance.getBalanceId());
		float balance = Float.valueOf(format.format(busUserBalance.getBalance() + amount));
		
		String jpql = "update BusUserBalance s set s.balance = ?,s.version=s.version + 1 where s.balanceId = ?";
		return this.execute(jpql, balance,busUserBalance.getBalanceId());*/
		
		String jpql = "update BusUserBalance s set s.balance = FORMAT(s.balance + ?,2) ,s.version=s.version + 1 where s.balanceId = ?";
		return this.execute(jpql, amount,busUserBalance.getBalanceId());
	}


}
