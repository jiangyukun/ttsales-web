/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename SysLotteryDaoImpl.java
 * @package cn.ttsales.work.persistence.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.domain.SysLottery;
import cn.ttsales.work.dto.LotteryRecordDTO;
import cn.ttsales.work.persistence.sys.SysLotteryDao;


/**
 * SysLottery Dao Impl
 * @author dandyzheng
 *
 */
@Repository("sysLotteryDao")
public class SysLotteryDaoImpl extends AbstractFacade implements SysLotteryDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public SysLottery saveSysLottery(SysLottery sysLottery) {
		return persist(sysLottery);
	}

	public List<SysLottery> saveSysLotterys(List<SysLottery> sysLotterys) {
		return persist(sysLotterys);
	}

	public SysLottery editSysLottery(SysLottery sysLottery) {
		return merge(sysLottery);
	}

	public List<SysLottery> editSysLotterys(List<SysLottery> sysLotterys) {
		return merge(sysLotterys);
	}

	public void removeSysLottery(SysLottery sysLottery) {
		remove(getSysLottery(sysLottery.getLotteryId()));
	}

	public void removeSysLotterys(List<SysLottery> sysLotterys) {
		remove(sysLotterys);
	}

	public void removeSysLottery(String sysLotteryId) {
		remove(sysLotteryId);
	}

	public SysLottery getSysLottery(String sysLotteryId) {
		return find(SysLottery.class, sysLotteryId);
	}

	public List<SysLottery> getAllSysLotterys() {
		return this.find("from SysLottery s");
	}

	public int updateLotteryUserId(String userId, String lotteryId) {
		String jpql = "update SysLottery s set s.userId = ? where s.lotteryId = ?";
		return this.execute(jpql, new Object[]{userId, lotteryId});
	}

	public SysLottery getLotteryByRank(String deptId) {
		String jpql = "from SysLottery s where (s.userId is null or s.userId = '') and s.deptId = ? order by s.rank ";
		List<SysLottery> lotterys = this.findRange(jpql, deptId, 1, 1);
		if (ArrayUtil.isEmpty(lotterys)) {
			return null;
		} else {
			return lotterys.get(0);
		}
	}
	
	public SysLottery getUserLotteryByRank(String userId, String deptId) {
		String jpql = "from SysLottery s where  s.userId = ? "
				+ " and deptId = ? and s.hasLottery = 0 order by s.rank ";
		
		List<SysLottery> lotterys = this.findRange(jpql, userId, deptId, 1, 1);
		if (ArrayUtil.isEmpty(lotterys)) {
			return null;
		} else {
			return lotterys.get(0);
		}
	}

	public int queryCanLotteryCount(String userId, String deptId) {
		String sql = " select concat(count(distinct s.lottery_id), '') "
				+ " from sys_lottery s where s.user_id = ? and has_lottery = 0 and s.dept_id = ? ";
		
		@SuppressWarnings("unchecked")
		List<Object> r = this.findNative(sql, new Object[]{userId, deptId});
		if (r != null) {
			return Integer.parseInt((String)r.get(0));
		}
		return 0;
	}

	public List<LotteryRecordDTO> queryLotterRecordByUserIdAndDeptId(
			String userId, String deptId) {
		
		StringBuffer sql = new StringBuffer(" SELECT a.lottery_id AS lotteryId, d.bonus_name AS bonusName, " +
						" a.money AS lotteryCash, DATE_FORMAT(a.lottery_time, '%m-%d %H:%i') AS lotteryTime, " +
						" c.dept_name AS deptName, CONCAT(LEFT(b.`name`, 1),REPEAT('*', CHAR_LENGTH(b.`name`) - 1)) AS `name` " +
						" FROM sys_lottery a " +
						" LEFT JOIN ent_member b ON a.user_id = b.member_id " +
						" LEFT JOIN ent_deptment_member e ON a.user_id = e.member_id" +
						" LEFT JOIN ent_deptment c ON e.dept_id = c.dept_id " +
						" LEFT JOIN wx_app_bonus d ON a.bonus_id = d.bonus_id ");
		Object[] params = null;
		if (userId == null) {
			params = new Object[]{deptId + "%", deptId};
			sql.append(" WHERE a.has_lottery = 1 AND e.dept_id LIKE ? AND a.dept_id = ? " +
					" AND (a.bonus_id IS NOT null or a.bonus_id <> '') " +
					" ORDER BY a.money DESC, a.lottery_time DESC LIMIT 10");
		} else {
			params = new Object[]{userId, deptId + "%", deptId};
			sql.append(" WHERE a.user_id = ? AND a.has_lottery = 1 " + 
					" AND e.dept_id LIKE ? AND a.dept_id = ? " +
					" AND (a.bonus_id IS NOT null or a.bonus_id <> '') " +
					" ORDER BY a.lottery_time DESC");
		}
		
		List<LotteryRecordDTO> rs = this.findNative(sql.toString(), params, LotteryRecordDTO.class);
		
		if (ArrayUtil.isEmpty(rs)) {
			return null;
		}
		return rs;
	}
}
