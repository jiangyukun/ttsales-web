/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename RepUserCrossDaoImpl.java
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
import cn.ttsales.work.domain.RepUserCross;
import cn.ttsales.work.persistence.rep.RepUserCrossDao;


/**
 * RepUserCross Dao Impl
 * @author dandyzheng
 *
 */
@Repository("repUserCrossDao")
public class RepUserCrossDaoImpl extends AbstractFacade implements RepUserCrossDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public RepUserCross saveRepUserCross(RepUserCross repUserCross) {
		return persist(repUserCross);
	}

	public List<RepUserCross> saveRepUserCrosss(List<RepUserCross> repUserCrosss) {
		return persist(repUserCrosss);
	}

	public RepUserCross editRepUserCross(RepUserCross repUserCross) {
		return merge(repUserCross);
	}

	public List<RepUserCross> editRepUserCrosss(List<RepUserCross> repUserCrosss) {
		return merge(repUserCrosss);
	}

	public void removeRepUserCross(RepUserCross repUserCross) {
		remove(getRepUserCross(repUserCross.getUserCrossId()));
	}

	public void removeRepUserCrosss(List<RepUserCross> repUserCrosss) {
		remove(repUserCrosss);
	}

	public void removeRepUserCross(String repUserCrossId) {
		remove(repUserCrossId);
	}

	public RepUserCross getRepUserCross(String repUserCrossId) {
		return find(RepUserCross.class, repUserCrossId);
	}

	public List<RepUserCross> getAllRepUserCrosss() {
		return this.find("from RepUserCross s");
	}

	public List<RepUserCross> queryRepUserCross(String userId, String type) {
		return this.find("from RepUserCross s where s.userId = ? and s.type = ?",userId,type);
	}

}
