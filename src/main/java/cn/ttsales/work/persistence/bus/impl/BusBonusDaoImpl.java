/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename BusBonusDaoImpl.java
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
import cn.ttsales.work.domain.BusBonus;
import cn.ttsales.work.persistence.bus.BusBonusDao;


/**
 * BusBonus Dao Impl
 * @author dandyzheng
 *
 */
@Repository("busBonusDao")
public class BusBonusDaoImpl extends AbstractFacade implements BusBonusDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public BusBonus saveBusBonus(BusBonus busBonus) {
		return persist(busBonus);
	}

	public List<BusBonus> saveBusBonuss(List<BusBonus> busBonuss) {
		return persist(busBonuss);
	}

	public BusBonus editBusBonus(BusBonus busBonus) {
		return merge(busBonus);
	}

	public List<BusBonus> editBusBonuss(List<BusBonus> busBonuss) {
		return merge(busBonuss);
	}

	public void removeBusBonus(BusBonus busBonus) {
		remove(getBusBonus(busBonus.getBonusId()));
	}

	public void removeBusBonuss(List<BusBonus> busBonuss) {
		remove(busBonuss);
	}

	public void removeBusBonus(String busBonusId) {
		remove(busBonusId);
	}

	public BusBonus getBusBonus(String busBonusId) {
		return find(BusBonus.class, busBonusId);
	}

	public List<BusBonus> getAllBusBonuss() {
		return this.find("from BusBonus s");
	}

}
