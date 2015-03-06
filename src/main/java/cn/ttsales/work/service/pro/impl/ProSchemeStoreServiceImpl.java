/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename ProSchemeStoreServiceImpl.java
 * @package cn.ttsales.work.service.pro.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.pro.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.domain.ProSchemeStore;
import cn.ttsales.work.persistence.pro.ProSchemeStoreDao;
import cn.ttsales.work.service.pro.ProSchemeStoreService;


/**
 * ProSchemeStore Service Impl
 * @author dandyzheng
 *
 */
@Service("proSchemeStoreService")
public class ProSchemeStoreServiceImpl implements ProSchemeStoreService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private ProSchemeStoreDao proSchemeStoreDao;

	@Transactional
	public ProSchemeStore saveProSchemeStore(ProSchemeStore proSchemeStore) {
		return proSchemeStoreDao.saveProSchemeStore(proSchemeStore);
	}

	@Transactional
	public List<ProSchemeStore> saveProSchemeStores(List<ProSchemeStore> proSchemeStores) {
		return null;
	}

	@Transactional
	public ProSchemeStore editProSchemeStore(ProSchemeStore proSchemeStore) {
		return proSchemeStoreDao.editProSchemeStore(proSchemeStore);
	}

	@Transactional
	public List<ProSchemeStore> editProSchemeStores(List<ProSchemeStore> proSchemeStores) {
		return proSchemeStoreDao.editProSchemeStores(proSchemeStores);
	}

	@Transactional
	public void removeProSchemeStore(ProSchemeStore ProSchemeStore) {
		proSchemeStoreDao.removeProSchemeStore(ProSchemeStore);
	}

	@Transactional
	public void removeProSchemeStores(List<ProSchemeStore> ProSchemeStores) {
		proSchemeStoreDao.removeProSchemeStores(ProSchemeStores);		
	}

	@Transactional
	public void removeProSchemeStore(String proSchemeStoreId) {
		proSchemeStoreDao.removeProSchemeStore(proSchemeStoreId);		
	}

	public ProSchemeStore getProSchemeStore(String proSchemeStoreId) {
		return proSchemeStoreDao.getProSchemeStore(proSchemeStoreId);
	}

	public List<ProSchemeStore> getAllProSchemeStores() {
		return proSchemeStoreDao.getAllProSchemeStores();
	}
	
	
}
