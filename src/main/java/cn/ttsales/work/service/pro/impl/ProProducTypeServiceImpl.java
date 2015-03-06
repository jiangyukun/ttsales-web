/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename ProProducTypeServiceImpl.java
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

import cn.ttsales.work.domain.ProProducType;
import cn.ttsales.work.persistence.pro.ProProducTypeDao;
import cn.ttsales.work.service.pro.ProProducTypeService;


/**
 * ProProducType Service Impl
 * @author dandyzheng
 *
 */
@Service("proProducTypeService")
public class ProProducTypeServiceImpl implements ProProducTypeService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private ProProducTypeDao proProducTypeDao;

	@Transactional
	public ProProducType saveProProducType(ProProducType proProducType) {
		return proProducTypeDao.saveProProducType(proProducType);
	}

	@Transactional
	public List<ProProducType> saveProProducTypes(List<ProProducType> proProducTypes) {
		return null;
	}

	@Transactional
	public ProProducType editProProducType(ProProducType proProducType) {
		return proProducTypeDao.editProProducType(proProducType);
	}

	@Transactional
	public List<ProProducType> editProProducTypes(List<ProProducType> proProducTypes) {
		return proProducTypeDao.editProProducTypes(proProducTypes);
	}

	@Transactional
	public void removeProProducType(ProProducType ProProducType) {
		proProducTypeDao.removeProProducType(ProProducType);
	}

	@Transactional
	public void removeProProducTypes(List<ProProducType> ProProducTypes) {
		proProducTypeDao.removeProProducTypes(ProProducTypes);		
	}

	@Transactional
	public void removeProProducType(String proProducTypeId) {
		proProducTypeDao.removeProProducType(proProducTypeId);		
	}

	public ProProducType getProProducType(String proProducTypeId) {
		return proProducTypeDao.getProProducType(proProducTypeId);
	}

	public List<ProProducType> getAllProProducTypes() {
		return proProducTypeDao.getAllProProducTypes();
	}
	
	
}
