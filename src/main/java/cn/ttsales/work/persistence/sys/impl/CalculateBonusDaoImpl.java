/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename SysSaleResultDaoImpl.java
 * @package cn.ttsales.work.persistence.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys.impl;

import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.BusBonus;
import cn.ttsales.work.domain.BusUserBonus;
import cn.ttsales.work.persistence.bus.BusBonusDao;
import cn.ttsales.work.persistence.bus.BusUserBonusDao;
import cn.ttsales.work.persistence.sys.CalculateBonusDao;

@Repository("calculateBonusDao")
public class CalculateBonusDaoImpl extends AbstractFacade implements
		CalculateBonusDao {

	@PersistenceContext(unitName = "MAIN_DATABASE_PER")
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Autowired
	private BusBonusDao busBonusDao;

	@Autowired
	private BusUserBonusDao busUserBonusDao;

	@SuppressWarnings("unchecked")
	@Transactional
	public void calculateBonusByBonusId(String bonusId) {
		// 获取红包
		BusBonus busBonus = busBonusDao.getBusBonus(bonusId);
		if (busBonus == null) {
			return;
		}
		// 先删除
		this.executeNative("delete from bus_user_bonus where bonus_id = '"+ bonusId + "' and create_time like '%"+DateUtil.getCurrentDateStr()+"%'");

		if (StringUtil.isEmpty(busBonus.getCalculateClass())) {
			this.executeNative(busBonus.getCalculateSql());
			return;
		}
		
		List<Object[]> results = this.findNative(busBonus.getCalculateSql());
		String className = busBonus.getCalculateClass();
		try {
			Class<?> calculateClass = Class.forName("cn.ttsales.work.core.util.calculate."+className);
			Object obj = calculateClass.newInstance();
			Method method = obj.getClass().getMethod("calculate", List.class);
			List<BusUserBonus> busUserBonuses = (List<BusUserBonus>) method.invoke(obj, results);
			//busUserBonusDao.editBusUserBonuss(busUserBonuses);
		} catch (Exception e) {
 			e.printStackTrace(); 
		}
	}
}
