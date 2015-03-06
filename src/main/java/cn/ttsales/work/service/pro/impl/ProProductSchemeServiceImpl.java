/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename ProProductSchemeServiceImpl.java
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

import cn.ttsales.work.domain.ProProductScheme;
import cn.ttsales.work.persistence.pro.ProProductSchemeDao;
import cn.ttsales.work.service.pro.ProProductSchemeService;


/**
 * ProProductScheme Service Impl
 * @author dandyzheng
 *
 */
@Service("proProductSchemeService")
public class ProProductSchemeServiceImpl implements ProProductSchemeService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private ProProductSchemeDao proProductSchemeDao;

	@Transactional
	public ProProductScheme saveProProductScheme(ProProductScheme proProductScheme) {
		return proProductSchemeDao.saveProProductScheme(proProductScheme);
	}

	@Transactional
	public List<ProProductScheme> saveProProductSchemes(List<ProProductScheme> proProductSchemes) {
		return null;
	}

	@Transactional
	public ProProductScheme editProProductScheme(ProProductScheme proProductScheme) {
		return proProductSchemeDao.editProProductScheme(proProductScheme);
	}

	@Transactional
	public List<ProProductScheme> editProProductSchemes(List<ProProductScheme> proProductSchemes) {
		return proProductSchemeDao.editProProductSchemes(proProductSchemes);
	}

	@Transactional
	public void removeProProductScheme(ProProductScheme ProProductScheme) {
		proProductSchemeDao.removeProProductScheme(ProProductScheme);
	}

	@Transactional
	public void removeProProductSchemes(List<ProProductScheme> ProProductSchemes) {
		proProductSchemeDao.removeProProductSchemes(ProProductSchemes);		
	}

	@Transactional
	public void removeProProductScheme(String proProductSchemeId) {
		proProductSchemeDao.removeProProductScheme(proProductSchemeId);		
	}

	public ProProductScheme getProProductScheme(String proProductSchemeId) {
		return proProductSchemeDao.getProProductScheme(proProductSchemeId);
	}

	public List<ProProductScheme> getAllProProductSchemes() {
		return proProductSchemeDao.getAllProProductSchemes();
	}

	public List<ProProductScheme> getProProductSchemesByConditions(
			String title, String summary, String state) {
		return proProductSchemeDao.getProProductSchemesByConditions(title,summary,state);
	}

	public List<ProProductScheme> getProProductSchemesByPorductId(
			String productId) {
		return proProductSchemeDao.getProProductSchemesByPorductId(productId);
	}
	
	
}
