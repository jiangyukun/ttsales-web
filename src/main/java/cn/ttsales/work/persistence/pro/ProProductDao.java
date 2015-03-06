/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename ProProductDao.java
 * @package cn.ttsales.work.persistence.pro
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.pro;

import java.util.List;

import cn.ttsales.work.domain.ProProduct;



/**
 * ProProduct DAO
 * @author dandyzheng
 *
 */
public interface ProProductDao {
	/**
	 * 保存 ProProduct
	 * @param proProduct ProProduct
	 * @return ProProduct 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProProduct saveProProduct(ProProduct proProduct);
	
	/**
	 * 批量保存ProProduct
	 * @param proProducts ProProducts
	 * @return List<ProProduct>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProProduct> saveProProducts(List<ProProduct> proProducts);
	
	/**
	 * 修改ProProduct
	 * @param proProduct ProProduct
	 * @return ProProduct
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProProduct editProProduct(ProProduct proProduct);
	
	/**
	 * 批量修改ProProduct
	 * @param proProducts ProProducts
	 * @return List<ProProduct>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProProduct> editProProducts(List<ProProduct> proProducts);
	
	/**
	 * 删除ProProduct
	 * @param ProProduct ProProduct
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProProduct(ProProduct proProduct);
	
	/**
	 * 批量删除ProProduct
	 * @param ProProducts ProProducts
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProProducts(List<ProProduct> proProducts);
	
	/**
	 * 根据ProProduct' id，删除ProProduct
	 * @param proProductId ProProduct's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProProduct(String proProductId);
	
	/**
	 * 根据ProProduct' id，获取ProProduct
	 * @param proProductId ProProduct's id
	 * @return ProProduct
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProProduct getProProduct(String proProductId); 
	
	/**
	 * 获取所有ProProduct
	 * @return List<ProProduct>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProProduct> getAllProProducts();

	/**
	 * 根据广告商id获取产品列表
	 * @param advertiserId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-9-19
	 * @see
	 */
	public List<ProProduct> getProProductsByAdvertiserId(String advertiserId);
	
		
}
