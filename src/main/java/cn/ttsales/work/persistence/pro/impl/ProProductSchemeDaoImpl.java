/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename ProProductSchemeDaoImpl.java
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
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.ProProductScheme;
import cn.ttsales.work.persistence.pro.ProProductSchemeDao;


/**
 * ProProductScheme Dao Impl
 * @author dandyzheng
 *
 */
@Repository("proProductSchemeDao")
public class ProProductSchemeDaoImpl extends AbstractFacade implements ProProductSchemeDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public ProProductScheme saveProProductScheme(ProProductScheme proProductScheme) {
		return persist(proProductScheme);
	}

	public List<ProProductScheme> saveProProductSchemes(List<ProProductScheme> proProductSchemes) {
		return persist(proProductSchemes);
	}

	public ProProductScheme editProProductScheme(ProProductScheme proProductScheme) {
		return merge(proProductScheme);
	}

	public List<ProProductScheme> editProProductSchemes(List<ProProductScheme> proProductSchemes) {
		return merge(proProductSchemes);
	}

	public void removeProProductScheme(ProProductScheme proProductScheme) {
		remove(getProProductScheme(proProductScheme.getSchemeId()));
	}

	public void removeProProductSchemes(List<ProProductScheme> proProductSchemes) {
		remove(proProductSchemes);
	}

	public void removeProProductScheme(String proProductSchemeId) {
		remove(proProductSchemeId);
	}

	public ProProductScheme getProProductScheme(String proProductSchemeId) {
		return find(ProProductScheme.class, proProductSchemeId);
	}

	public List<ProProductScheme> getAllProProductSchemes() {
		return this.find("from ProProductScheme s");
	}

	public List<ProProductScheme> getProProductSchemesByConditions(
			String title, String summary, String state) {
		String jpql = "from ProProductScheme s where  ";
		if(!StringUtil.isEmpty(title)){
			jpql = jpql + "s.title like '%"+title+"%' and    ";
		}
		if(!StringUtil.isEmpty(summary)){
			jpql = jpql + "s.summary like '%"+summary+"%' and    ";
		}
		if(!StringUtil.isEmpty(state)){
			jpql = jpql + "s.state ='"+state+"' and    ";
		}
		jpql = jpql.substring(0,jpql.length()-7);
		
		return this.find(jpql);
	}

	public List<ProProductScheme> getProProductSchemesByPorductId(
			String productId) {
		return this.find("from ProProductScheme s where s.proProduct.productId = ?",productId);
	}

}
