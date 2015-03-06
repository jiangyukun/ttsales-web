/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename RepTransmitDaoImpl.java
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
import cn.ttsales.work.domain.RepTransmit;
import cn.ttsales.work.dto.RepTransmitDTO;
import cn.ttsales.work.dto.RepTransmitReportDTO;
import cn.ttsales.work.persistence.rep.RepTransmitDao;


/**
 * RepTransmit Dao Impl
 * @author dandyzheng
 *
 */
@Repository("repTransmitDao")
public class RepTransmitDaoImpl extends AbstractFacade implements RepTransmitDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public RepTransmit saveRepTransmit(RepTransmit repTransmit) {
		return persist(repTransmit);
	}

	public List<RepTransmit> saveRepTransmits(List<RepTransmit> repTransmits) {
		return persist(repTransmits);
	}

	public RepTransmit editRepTransmit(RepTransmit repTransmit) {
		return merge(repTransmit);
	}

	public List<RepTransmit> editRepTransmits(List<RepTransmit> repTransmits) {
		return merge(repTransmits);
	}

	public void removeRepTransmit(RepTransmit repTransmit) {
		remove(getRepTransmit(repTransmit.getTransmitId()));
	}

	public void removeRepTransmits(List<RepTransmit> repTransmits) {
		remove(repTransmits);
	}

	public void removeRepTransmit(String repTransmitId) {
		remove(repTransmitId);
	}

	public RepTransmit getRepTransmit(String repTransmitId) {
		return find(RepTransmit.class, repTransmitId);
	}

	public List<RepTransmit> getAllRepTransmits() {
		return this.find("from RepTransmit s");
	}
	
	public List<RepTransmit> queryRepTransmit(String userCrossId,String popularizeId){
		return this.find("from RepTransmit s where s.userCrossId = ? and s.popularizeId = ? and s.pTransmitId = '0'",userCrossId,popularizeId);
	}

	public List<RepTransmitDTO> queryAllChildTransmits(String pTransmitId){
		String sql  = "{call pro_get_transmit_children(?)}";
		return this.findNative(sql,new String[]{pTransmitId},RepTransmitDTO.class);
	}
	
	public List<RepTransmitReportDTO> queryReportTransmitData(String userCrossId,String popularizeId ){
		String sql  = "{call pro_report_get_transmit_data(?,?,?)}";		
		return this.findNative(sql,new String[]{userCrossId,popularizeId,"0"},RepTransmitReportDTO.class);
	}

	public int queryTransmitRank(String userCrossId, String schemeId) {
		String sql = " SELECT CONCAT(d.rownum,'') FROM ( "
				+ " SELECT @rownum\\:=@rownum+1 AS rownum, c.* FROM (SELECT @rownum\\:=0) b, ( "
				+ " SELECT * FROM ( SELECT rt.user_cross_id, rt.tra_time, bp.scheme_id "
				+ " FROM rep_transmit rt "
				+ " LEFT JOIN bus_popularize bp ON rt.popularize_id = bp.popularize_id "
				+ " WHERE bp.scheme_id = ? ORDER BY rt.tra_time ASC"
				+ " ) a GROUP BY a.user_cross_id ORDER BY a.tra_time ASC"
				+ " )c ) d WHERE d.user_cross_id = ?";
		
		@SuppressWarnings("unchecked")
		List<Object> rank = this.findNative(sql, new Object[]{schemeId, userCrossId});
		if (ArrayUtil.isEmpty(rank)) {
			return 0;
		}
		return Integer.valueOf((String)rank.get(0));
	}
}
