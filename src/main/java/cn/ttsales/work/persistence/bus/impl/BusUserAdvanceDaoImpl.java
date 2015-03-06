/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename BusUserAdvanceDaoImpl.java
 * @package cn.ttsales.work.persistence.bus.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.bus.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.domain.BusUserAdvance;
import cn.ttsales.work.persistence.bus.BusUserAdvanceDao;


/**
 * BusUserAdvance Dao Impl
 * @author dandyzheng
 *
 */
@Repository("busUserAdvanceDao")
public class BusUserAdvanceDaoImpl extends AbstractFacade implements BusUserAdvanceDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public BusUserAdvance saveBusUserAdvance(BusUserAdvance busUserAdvance) {
		return persist(busUserAdvance);
	}

	public List<BusUserAdvance> saveBusUserAdvances(List<BusUserAdvance> busUserAdvances) {
		return persist(busUserAdvances);
	}

	public BusUserAdvance editBusUserAdvance(BusUserAdvance busUserAdvance) {
		return merge(busUserAdvance);
	}

	public List<BusUserAdvance> editBusUserAdvances(List<BusUserAdvance> busUserAdvances) {
		return merge(busUserAdvances);
	}

	public void removeBusUserAdvance(BusUserAdvance busUserAdvance) {
		remove(getBusUserAdvance(busUserAdvance.getUserAdvanceId()));
	}

	public void removeBusUserAdvances(List<BusUserAdvance> busUserAdvances) {
		remove(busUserAdvances);
	}

	public void removeBusUserAdvance(String busUserAdvanceId) {
		remove(busUserAdvanceId);
	}

	public BusUserAdvance getBusUserAdvance(String busUserAdvanceId) {
		return find(BusUserAdvance.class, busUserAdvanceId);
	}

	public List<BusUserAdvance> getAllBusUserAdvances() {
		return this.find("from BusUserAdvance s");
	}

	public List<BusUserAdvance> getBusUserAdvanceByUserId(String userId) {
		return  this.find("from BusUserAdvance s where s.userId=?",userId);
	}

}
