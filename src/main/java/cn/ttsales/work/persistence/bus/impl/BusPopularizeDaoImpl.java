/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename BusPopularizeDaoImpl.java
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
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.domain.BusPopularize;
import cn.ttsales.work.persistence.bus.BusPopularizeDao;


/**
 * BusPopularize Dao Impl
 * @author dandyzheng
 *
 */
@Repository("busPopularizeDao")
public class BusPopularizeDaoImpl extends AbstractFacade implements BusPopularizeDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public BusPopularize saveBusPopularize(BusPopularize busPopularize) {
		return persist(busPopularize);
	}

	public List<BusPopularize> saveBusPopularizes(List<BusPopularize> busPopularizes) {
		return persist(busPopularizes);
	}

	public BusPopularize editBusPopularize(BusPopularize busPopularize) {
		return merge(busPopularize);
	}

	public List<BusPopularize> editBusPopularizes(List<BusPopularize> busPopularizes) {
		return merge(busPopularizes);
	}

	public void removeBusPopularize(BusPopularize busPopularize) {
		remove(getBusPopularize(busPopularize.getMemberId()));
	}

	public void removeBusPopularizes(List<BusPopularize> busPopularizes) {
		remove(busPopularizes);
	}

	public void removeBusPopularize(String busPopularizeId) {
		remove(busPopularizeId);
	}

	public BusPopularize getBusPopularize(String busPopularizeId) {
		return find(BusPopularize.class, busPopularizeId);
	}

	public List<BusPopularize> getAllBusPopularizes() {
		return this.find("from BusPopularize s");
	}
	
	public List<BusPopularize> queryBusPopularizesByMemberId(String memberId) {
		return this.find("from BusPopularize s where s.memberId = ? and s.state <>'00' order by s.createTime desc",memberId);
	}

	public void changeStage(String popularizeId, String state) {
		this.executeNative("update bus_popularize t set t.state='"+state+"' where t.popularize_id='"+popularizeId+"'");
	}

	public List<BusPopularize> queryMemberBusPopularizedByState(String memberId, String... states) {
		StringBuffer jpql = new StringBuffer(" from BusPopularize s where s.memberId = ? ");
		List<BusPopularize> bps = null;
		if (states.length == 0) {
			jpql.append(" order by s.createTime desc");
			bps = this.find(jpql.toString(), memberId);
		} else {
			List<String> ps = new ArrayList<String>();
			ps.add(memberId);
			jpql.append(" and s.state in ( ");
			for(int i = 0, len = states.length; i < len; i++) {
				jpql.append(" ? ");
				if (i < len - 1) {
					jpql.append(",");
				}
				ps.add(states[i]);
			}
			jpql.append(" ) order by s.createTime desc");
			bps = this.find(jpql.toString(), ps.toArray());
		}
		
		return bps;
	}

	public void saveWishRecord(String userCrossId, String transmitId, String popularizeId) {
		String sql = "INSERT INTO bus_wish_record (user_cross_id, wish_time, transmit_id, popularize_id) VALUES (?, ?, ?, ?)";
		this.executeNative(sql, new Object[]{userCrossId, DateUtil.getCurrentDateTimeStr(), transmitId, popularizeId});
	}
}
