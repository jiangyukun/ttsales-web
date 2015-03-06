/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename ProProductDaoImpl.java
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
import cn.ttsales.work.domain.ProProduct;
import cn.ttsales.work.persistence.pro.ProProductDao;


/**
 * ProProduct Dao Impl
 * @author dandyzheng
 *
 */
@Repository("proProductDao")
public class ProProductDaoImpl extends AbstractFacade implements ProProductDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public ProProduct saveProProduct(ProProduct proProduct) {
		return persist(proProduct);
	}

	public List<ProProduct> saveProProducts(List<ProProduct> proProducts) {
		return persist(proProducts);
	}

	public ProProduct editProProduct(ProProduct proProduct) {
		return merge(proProduct);
	}

	public List<ProProduct> editProProducts(List<ProProduct> proProducts) {
		return merge(proProducts);
	}

	public void removeProProduct(ProProduct proProduct) {
		remove(getProProduct(proProduct.getProductId()));
	}

	public void removeProProducts(List<ProProduct> proProducts) {
		remove(proProducts);
	}

	public void removeProProduct(String proProductId) {
		remove(proProductId);
	}

	public ProProduct getProProduct(String proProductId) {
		return find(ProProduct.class, proProductId);
	}

	public List<ProProduct> getAllProProducts() {
		return this.find("from ProProduct s");
	}

	public List<ProProduct> getProProductsByAdvertiserId(String advertiserId) {
		return this.find("from ProProduct s where s.advertiserId = ? order by s.productId desc  ",advertiserId);
	}

}
