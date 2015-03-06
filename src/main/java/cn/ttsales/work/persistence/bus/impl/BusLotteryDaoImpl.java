/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename BusLotteryDaoImpl.java
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
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.domain.BusLottery;
import cn.ttsales.work.dto.LotteryRecordDTO;
import cn.ttsales.work.persistence.bus.BusLotteryDao;


/**
 * BusLottery Dao Impl
 * @author dandyzheng
 *
 */
@Repository("busLotteryDao")
public class BusLotteryDaoImpl extends AbstractFacade implements BusLotteryDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public BusLottery saveBusLottery(BusLottery busLottery) {
		return persist(busLottery);
	}

	public List<BusLottery> saveBusLotterys(List<BusLottery> busLotterys) {
		return persist(busLotterys);
	}

	public BusLottery editBusLottery(BusLottery busLottery) {
		return merge(busLottery);
	}

	public List<BusLottery> editBusLotterys(List<BusLottery> busLotterys) {
		return merge(busLotterys);
	}

	public void removeBusLottery(BusLottery busLottery) {
		remove(getBusLottery(busLottery.getLotteryId()));
	}

	public void removeBusLotterys(List<BusLottery> busLotterys) {
		remove(busLotterys);
	}

	public void removeBusLottery(String busLotteryId) {
		remove(busLotteryId);
	}

	public BusLottery getBusLottery(String busLotteryId) {
		return find(BusLottery.class, busLotteryId);
	}

	public List<BusLottery> getAllBusLotterys() {
		return this.find("from BusLottery s");
	}

	public int queryLotteryCountByReadGet(String deptId, String... userIds) {
		
		StringBuffer sql = new StringBuffer("select concat(count(s.lottery_id)) from bus_lottery s ");
		Object[] params = null;
		if (userIds.length > 1) {
			params = new Object[]{userIds[0], userIds[1], deptId};
			sql.append(" where s.lottery_type = '02' and (s.user_id = ? or s.user_id = ?) and s.dept_id = ? ");
		} else {
			params = new Object[]{userIds[0], deptId};
			sql.append(" where s.lottery_type = '02' and s.user_id = ? and s.dept_id = ? ");
		}

		return Integer.valueOf((String)this.findNative(sql.toString(), params).get(0));
	}

	public int updateLotteryUserId(String userId, String lotteryId) {
		String jpql = "update BusLottery s set s.userId = ? where s.lotteryId = ?";
		return this.execute(jpql, new Object[]{userId, lotteryId});
	}

	public BusLottery getUserLotteryByRank(String userId, String openId, String deptId) {
		StringBuffer jpql = new StringBuffer("from BusLottery s where "
				+ " s.deptId = ? and s.hasLottery = 0 ");
		
		Object[] params = null;
		if (userId != null && openId != null) {
			params = new Object[]{deptId, userId, openId};
			jpql.append(" and ( s.userId = ? or s.userId = ? )");
		} else if (userId != null) {
			params = new Object[]{deptId, userId};
			jpql.append(" and s.userId = ? ");
		} else {
			params = new Object[]{deptId, openId};
			jpql.append(" and s.userId = ? ");
		}
		
		jpql.append(" order by s.rank ");
		
		List<BusLottery> lotterys = this.findRange(jpql.toString(), params, 1, 1);
		if (ArrayUtil.isEmpty(lotterys)) {
			return null;
		} else {
			return lotterys.get(0);
		}
	}

	public List<BusLottery> queryLotterysByRank(String deptId, int sum) {
		String jpql = "from BusLottery s where (s.userId is null or s.userId = '') "
				+ " and deptId = ? and s.hasLottery = 0 order by s.rank ";
		
		List<BusLottery> lotterys = this.findRange(jpql, deptId, 1, sum);
		if (ArrayUtil.isEmpty(lotterys)) {
			return null;
		} else {
			return lotterys;
		}
	}

	public int queryCanLotteryCount(String deptId, String... userIds) {
		StringBuffer sql = new StringBuffer(" select concat(count(distinct s.lottery_id), '') "
				+ " from bus_lottery s where has_lottery = 0 and s.dept_id = ? ");
				
		Object[] params;
		if (userIds.length > 1) {
			params = new Object[]{deptId, userIds[0], userIds[1]};
			sql.append(" and (s.user_id = ? or s.user_id = ?)");
		} else {
			params = new Object[]{deptId, userIds[0]};
			sql.append(" and s.user_id = ? ");
		}
		
		@SuppressWarnings("unchecked")
		List<Object> r = this.findNative(sql.toString(), params);
		if (r != null) {
			return Integer.parseInt((String)r.get(0));
		}
		return 0;
	}

	public List<LotteryRecordDTO> queryLotterRecordByUserIdAndDeptId(
			String deptId, String userId, String openId) {
		
		StringBuffer sql = new StringBuffer(" SELECT a.lottery_id AS lotteryId, d.bonus_name AS bonusName, " +
				" a.money AS lotteryCash, DATE_FORMAT(a.lottery_time, '%m-%d %H:%i') AS lotteryTime, '' AS deptName, " +
				" IF(b.`name` IS NULL, " + 
				" CONCAT(LEFT (c.nick_name, 1),REPEAT('*',CHAR_LENGTH(c.nick_name) - 1)), " + 
				" CONCAT(LEFT (b.`name`, 1),REPEAT('*',CHAR_LENGTH(b.`name`) - 1))) AS `name` " + 
				" FROM bus_lottery a " +
				" LEFT JOIN ent_member b ON a.user_id = b.member_id " +
				" LEFT JOIN rep_open_user c ON a.user_id = c.open_id " +
				" LEFT JOIN wx_app_bonus d ON a.bonus_id = d.bonus_id ");
		
		Object[] params = null;
		if (userId == null && openId == null) { //查询部门所有记录
			params = new Object[]{deptId};
			sql.append(" WHERE a.has_lottery = 1 AND a.dept_id = ? " +
					" AND (a.bonus_id IS NOT null or a.bonus_id <> '') " +
					" ORDER BY a.lottery_time DESC LIMIT 10 ");
		} else if (userId != null && openId != null) { //两种身份记录
			params = new Object[]{userId, openId, deptId};
			sql.append(" WHERE (a.user_id = ? or a.user_id = ? ) AND a.has_lottery = 1 " + 
					" AND a.dept_id = ? AND (a.bonus_id IS NOT null or a.bonus_id <> '') " +
					" ORDER BY a.lottery_time DESC");
		} else { //一种身份记录
			if (userId != null) {
				params = new Object[]{userId, deptId};
			} else {
				params = new Object[]{openId, deptId};
			}
			sql.append(" WHERE a.user_id = ? AND a.has_lottery = 1 AND a.dept_id = ? " +
					" AND (a.bonus_id IS NOT null or a.bonus_id <> '') " +
					" ORDER BY a.lottery_time DESC");
		}
		
		List<LotteryRecordDTO> rs = this.findNative(sql.toString(), params, LotteryRecordDTO.class);
		
		if (ArrayUtil.isEmpty(rs)) {
			return null;
		}
		return rs;
	}

	public boolean isHaveTransmitLottery(String userId, String schemeId) {
		if (!schemeId.equals("501001") && !schemeId.equals("501002")) {
			return true;
		}
		
		String jpql = "from BusLottery o where o.userId = ? and schemeId = ? and o.lotteryType = '03'";
		List<BusLottery> results = this.find(jpql, userId, schemeId);
		
		if (ArrayUtil.isEmpty(results)) {
			return false;
		}
		return true;
	}
}
