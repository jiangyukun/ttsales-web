/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename BusUserBonusDaoImpl.java
 * @package cn.ttsales.work.persistence.bus.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.bus.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.domain.BusUserBonus;
import cn.ttsales.work.dto.MyBonusDetailDTO;
import cn.ttsales.work.persistence.bus.BusUserBonusDao;


/**
 * BusUserBonus Dao Impl
 * @author dandyzheng
 *
 */
@Repository("busUserBonusDao")
public class BusUserBonusDaoImpl extends AbstractFacade implements BusUserBonusDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public BusUserBonus saveBusUserBonus(BusUserBonus busUserBonus) {
		return persist(busUserBonus);
	}

	public List<BusUserBonus> saveBusUserBonuss(List<BusUserBonus> busUserBonuss) {
		return persist(busUserBonuss);
	}

	public BusUserBonus editBusUserBonus(BusUserBonus busUserBonus) {
		return merge(busUserBonus);
	}

	public List<BusUserBonus> editBusUserBonuss(List<BusUserBonus> busUserBonuss) {
		return merge(busUserBonuss);
	}

	public void removeBusUserBonus(BusUserBonus busUserBonus) {
		remove(getBusUserBonus(busUserBonus.getUserBonusId()));
	}

	public void removeBusUserBonuss(List<BusUserBonus> busUserBonuss) {
		remove(busUserBonuss);
	}

	public void removeBusUserBonus(String busUserBonusId) {
		remove(busUserBonusId);
	}

	public BusUserBonus getBusUserBonus(String busUserBonusId) {
		return find(BusUserBonus.class, busUserBonusId);
	}

	public List<BusUserBonus> getAllBusUserBonuss() {
		return this.find("from BusUserBonus s");
	}

	public List<BusUserBonus> getBusBonusesByUserId(String userId) {
		return this.find("from BusUserBonus s where s.userId=?",userId);
	}

	public List<MyBonusDetailDTO> getMyBonusDetailInMonths(String userId,
			String userType, String date) {
		String sql = " SELECT a.bonus_id, c.title, b.bonus_name, round(a.money, 2), DATE_FORMAT(a.create_time, '%Y-%m-%d') "
				+ " FROM bus_user_bonus a "
				+ " LEFT JOIN bus_bonus b ON b.bonus_id = a.bonus_id "
				+ " LEFT JOIN pro_product_scheme c ON b.scheme_id = c.scheme_id "
				+ " WHERE a.user_id = ? AND a.user_type = ? AND a.create_time LIKE ? "
				+ " ORDER BY a.create_time DESC;";
		
		@SuppressWarnings("unchecked")
		List<Object[]> r = this.findNative(sql, new Object[]{userId, userType, "%" + date + "%"});
		if (ArrayUtil.isEmpty(r)) {
			return null;
		}
		List<MyBonusDetailDTO> bonus = new ArrayList<MyBonusDetailDTO>();
		for (Object[] o : r) {
			bonus.add(new MyBonusDetailDTO((String)o[0], (String)o[1], (String)o[2], (Float.valueOf(o[3].toString())), (String)o[4]));
		}
		return bonus;
	}

}
