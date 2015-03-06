/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename ProSchemeStoreDaoImpl.java
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
import cn.ttsales.work.domain.ProSchemeStore;
import cn.ttsales.work.persistence.pro.ProSchemeStoreDao;


/**
 * ProSchemeStore Dao Impl
 * @author dandyzheng
 *
 */
@Repository("proSchemeStoreDao")
public class ProSchemeStoreDaoImpl extends AbstractFacade implements ProSchemeStoreDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public ProSchemeStore saveProSchemeStore(ProSchemeStore proSchemeStore) {
		return persist(proSchemeStore);
	}

	public List<ProSchemeStore> saveProSchemeStores(List<ProSchemeStore> proSchemeStores) {
		return persist(proSchemeStores);
	}

	public ProSchemeStore editProSchemeStore(ProSchemeStore proSchemeStore) {
		return merge(proSchemeStore);
	}

	public List<ProSchemeStore> editProSchemeStores(List<ProSchemeStore> proSchemeStores) {
		return merge(proSchemeStores);
	}

	public void removeProSchemeStore(ProSchemeStore proSchemeStore) {
		remove(getProSchemeStore(proSchemeStore.getSchemeStoreId()));
	}

	public void removeProSchemeStores(List<ProSchemeStore> proSchemeStores) {
		remove(proSchemeStores);
	}

	public void removeProSchemeStore(String proSchemeStoreId) {
		remove(proSchemeStoreId);
	}

	public ProSchemeStore getProSchemeStore(String proSchemeStoreId) {
		return find(ProSchemeStore.class, proSchemeStoreId);
	}

	public List<ProSchemeStore> getAllProSchemeStores() {
		return this.find("from ProSchemeStore s");
	}

}
