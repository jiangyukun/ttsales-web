/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename RepReadCountDaoImpl.java
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
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.domain.RepReadCount;
import cn.ttsales.work.persistence.rep.RepReadCountDao;


/**
 * RepReadCount Dao Impl
 * @author dandyzheng
 *
 */
@Repository("repReadCountDao")
public class RepReadCountDaoImpl extends AbstractFacade implements RepReadCountDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
//	private SimpleDateFormat rsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	private static DecimalFormat nf = new DecimalFormat("#.00");
	
	 
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public RepReadCount saveRepReadCount(RepReadCount repReadCount) {
		return persist(repReadCount);
	}

	public List<RepReadCount> saveRepReadCounts(List<RepReadCount> repReadCounts) {
		return persist(repReadCounts);
	}

	public RepReadCount editRepReadCount(RepReadCount repReadCount) {
		return merge(repReadCount);
	}

	public List<RepReadCount> editRepReadCounts(List<RepReadCount> repReadCounts) {
		return merge(repReadCounts);
	}

	public void removeRepReadCount(RepReadCount repReadCount) {
		remove(getRepReadCount(repReadCount.getReadCountId()));
	}

	public void removeRepReadCounts(List<RepReadCount> repReadCounts) {
		remove(repReadCounts);
	}

	public void removeRepReadCount(String repReadCountId) {
		remove(repReadCountId);
	}

	public RepReadCount getRepReadCount(String repReadCountId) {
		return find(RepReadCount.class, repReadCountId);
	}

	public List<RepReadCount> getAllRepReadCounts() {
		return this.find("from RepReadCount s");
	}

	public int querySomeOneValidReadCount(String schemeId, String... userIds) {
		int uRead = 0;
		int oRead = 0;
		
		if (userIds[0] != null) {
			StringBuffer tempSql = new StringBuffer(" SELECT d.read_time " + 
					 " FROM rep_user_cross a " +
					 " LEFT JOIN rep_transmit b ON a.user_cross_id = b.user_cross_id " +
					 " LEFT JOIN bus_popularize c " +
					 " ON b.popularize_id = c.popularize_id " +
					 " INNER JOIN rep_read_count d ON b.transmit_id = d.transmit_id " +
					 " WHERE d.is_valid = 1 AND (c.scheme_id = '501001' or c.scheme_id = '501002') " + 
					 " AND a.user_id = ? ");
			
			@SuppressWarnings("unchecked")
			List<String> rl = this.findNative(tempSql.toString(), new Object[]{userIds[0]});
			
			if (!ArrayUtil.isEmpty(rl)) {
				uRead = rl.size();
			}
		}
		
		if (userIds[1] != null) {
			oRead = queryPopuReadCount(userIds[1]);
		}
		
		return uRead + oRead;
		
		/*int s = queryPopuReadCount(userIds);
		if(s != -1){
			return s;
		}*/
		
		/*StringBuffer tempSql = new StringBuffer(" SELECT d.read_time " + 
				 " FROM rep_user_cross a " +
				 " LEFT JOIN rep_transmit b ON a.user_cross_id = b.user_cross_id " +
				 " LEFT JOIN bus_popularize c " +
				 "ON b.popularize_id = c.popularize_id " +
				 " INNER JOIN rep_read_count d ON b.transmit_id = d.transmit_id " +
				 " WHERE d.is_valid = 1 AND (c.scheme_id = '501001' or c.scheme_id = '501002')");
		Object[] tempPrams = null;
		
		if (userIds.length > 1) {
			tempPrams = new Object[]{userIds[0], userIds[1]};
			tempSql.append(" AND (a.user_id = ? or a.user_id = ?)");
		} else {
			tempPrams = new Object[]{userIds[0]};
			tempSql.append(" AND a.user_id = ? ");
		}
		tempSql.append(" ORDER BY d.read_time ");
		
		@SuppressWarnings("unchecked")
		List<String> rl = this.findNative(tempSql.toString(), tempPrams);
		int size = 0;
		
		if (!ArrayUtil.isEmpty(rl)) {
			size = rl.size();
			
			if(size >= 30) {
				try {
					double sTime = rsdf.parse(rl.get(0)).getTime();
					double eTime = rsdf.parse(rl.get(size - 1)).getTime();
					
					if (sTime == eTime) {
						size = 0;
					} else {
						//单条阅读平均时间小于2秒为无效阅读
						if (Double.valueOf(nf.format(((eTime - sTime) / 1000) / size)) <= 2) {
							size = 0;
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
					System.err.println("read size query faile");
					size = 0;
				}
			}
		}
		
		return size;*/
				
		/*StringBuffer sql = new StringBuffer(" SELECT CONCAT(count(d.read_count_id), '') " + 
				 " FROM rep_user_cross a " +
				 " LEFT JOIN rep_transmit b ON a.user_cross_id = b.user_cross_id " +
				 " LEFT JOIN bus_popularize c ON b.popularize_id = c.popularize_id " +
				 " INNER JOIN rep_read_count d ON b.transmit_id = d.transmit_id " +
				 " WHERE d.is_valid = 1 AND (c.scheme_id = '501001' or c.scheme_id = '501002')");
		Object[] params = null;
		
		if (userIds.length > 1) {
			params = new Object[]{userIds[0], userIds[1]};
			sql.append(" AND (a.user_id = ? or a.user_id = ?)");
		} else {
			params = new Object[]{userIds[0]};
			sql.append(" AND a.user_id = ? ");
		}
		
		return Integer.valueOf((String)this.findNative(sql.toString(), params).get(0));*/
	}
	
	private int queryPopuReadCount(String openId) {
		/*if (userIds.length != 1 ) {
			return -1;
		}*/
		String popularize_id1 = "8e37fb7e6829344fc8ca22ca6ac1d6b6";
		String popularize_id2 = "4b9be4865940f53b94efa6b2278bf86e";
		StringBuffer sql  = new StringBuffer();
		sql.append("SELECT s.user_cross_id FROM rep_user_cross s ");
		sql.append("INNER JOIN rep_transmit t ON s.user_cross_id = t.user_cross_id ");
		sql.append("WHERE s.user_id = ? AND (t.popularize_id = ? or t.popularize_id = ?)");
		@SuppressWarnings("unchecked")
		List<Object> list = this.findNative(sql.toString(),new String[]{openId,popularize_id1,popularize_id2});
		
		if(list != null && list.size() > 0){
			StringBuffer sqlbuf = new StringBuffer();
			sqlbuf.append("select CONCAT('',count(DISTINCT f.user_id)) as c from rep_read_count r");
			sqlbuf.append(" INNER JOIN rep_transmit b ON r.transmit_id = b.transmit_id");
			sqlbuf.append(" INNER JOIN rep_user_cross c ON b.user_cross_id = c.user_cross_id");
			sqlbuf.append(" INNER JOIN rep_user_cross f ON r.user_cross_id = f.user_cross_id");
			sqlbuf.append(" where c.user_id = ? and f.user_id is not null and");
			sqlbuf.append(" r.is_valid = '1' and (r.popularize_id = ? or r.popularize_id = ?) ");
			@SuppressWarnings("unchecked")
			List<String> l  = this.findNative(sqlbuf.toString(),new String[]{openId, popularize_id1,popularize_id2});
			int a = Integer.valueOf(l.get(0).toString()).intValue();
			System.out.println("=============" + openId + "================" + a + "====================");
			return a;
		} else {
			StringBuffer tempSql = new StringBuffer(" SELECT d.read_time " + 
					 " FROM rep_user_cross a " +
					 " INNER JOIN rep_transmit b ON a.user_cross_id = b.user_cross_id " +
					 " INNER JOIN bus_popularize c " +
					 " ON b.popularize_id = c.popularize_id " +
					 " INNER JOIN rep_read_count d ON b.transmit_id = d.transmit_id AND b.popularize_id = d.popularize_id " +
					 " WHERE d.is_valid = 1 AND (c.scheme_id = '501001' or c.scheme_id = '501002') " + 
					 " AND a.user_id = ? ");
			
			@SuppressWarnings("unchecked")
			List<String> rl = this.findNative(tempSql.toString(), new Object[]{openId});
			if (!ArrayUtil.isEmpty(rl)) {
				return rl.size();
			}
			return 0;
		}
	}
}
