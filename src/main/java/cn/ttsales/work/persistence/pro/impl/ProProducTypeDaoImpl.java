/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename ProProducTypeDaoImpl.java
 * @package cn.ttsales.work.persistence.pro.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.pro.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.domain.ProProducType;
import cn.ttsales.work.persistence.pro.ProProducTypeDao;


/**
 * ProProducType Dao Impl
 * @author dandyzheng
 *
 */
@Repository("proProducTypeDao")
public class ProProducTypeDaoImpl extends AbstractFacade implements ProProducTypeDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public ProProducType saveProProducType(ProProducType proProducType) {
		return persist(proProducType);
	}

	public List<ProProducType> saveProProducTypes(List<ProProducType> proProducTypes) {
		return persist(proProducTypes);
	}

	public ProProducType editProProducType(ProProducType proProducType) {
		return merge(proProducType);
	}

	public List<ProProducType> editProProducTypes(List<ProProducType> proProducTypes) {
		return merge(proProducTypes);
	}

	public void removeProProducType(ProProducType proProducType) {
		remove(getProProducType(proProducType.getTypeId()));
	}

	public void removeProProducTypes(List<ProProducType> proProducTypes) {
		remove(proProducTypes);
	}

	public void removeProProducType(String proProducTypeId) {
		remove(proProducTypeId);
	}

	public ProProducType getProProducType(String proProducTypeId) {
		return find(ProProducType.class, proProducTypeId);
	}

	public List<ProProducType> getAllProProducTypes() {
		return this.find("from ProProducType s");
	}

}
