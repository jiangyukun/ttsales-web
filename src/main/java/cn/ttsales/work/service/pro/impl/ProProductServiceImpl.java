/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename ProProductServiceImpl.java
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

import cn.ttsales.work.domain.ProProduct;
import cn.ttsales.work.persistence.pro.ProProductDao;
import cn.ttsales.work.service.pro.ProProductService;


/**
 * ProProduct Service Impl
 * @author dandyzheng
 *
 */
@Service("proProductService")
public class ProProductServiceImpl implements ProProductService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private ProProductDao proProductDao;

	@Transactional
	public ProProduct saveProProduct(ProProduct proProduct) {
		return proProductDao.saveProProduct(proProduct);
	}

	@Transactional
	public List<ProProduct> saveProProducts(List<ProProduct> proProducts) {
		return null;
	}

	@Transactional
	public ProProduct editProProduct(ProProduct proProduct) {
		return proProductDao.editProProduct(proProduct);
	}

	@Transactional
	public List<ProProduct> editProProducts(List<ProProduct> proProducts) {
		return proProductDao.editProProducts(proProducts);
	}

	@Transactional
	public void removeProProduct(ProProduct ProProduct) {
		proProductDao.removeProProduct(ProProduct);
	}

	@Transactional
	public void removeProProducts(List<ProProduct> ProProducts) {
		proProductDao.removeProProducts(ProProducts);		
	}

	@Transactional
	public void removeProProduct(String proProductId) {
		proProductDao.removeProProduct(proProductId);		
	}

	public ProProduct getProProduct(String proProductId) {
		return proProductDao.getProProduct(proProductId);
	}

	public List<ProProduct> getAllProProducts() {
		return proProductDao.getAllProProducts();
	}

	public List<ProProduct> getProProductsByAdvertiserId(String advertiserId) {
		return proProductDao.getProProductsByAdvertiserId(advertiserId);
	}
	
	
}
