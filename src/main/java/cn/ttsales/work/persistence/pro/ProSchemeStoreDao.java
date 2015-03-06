/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename ProSchemeStoreDao.java
 * @package cn.ttsales.work.persistence.pro
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.pro;

import java.util.List;

import cn.ttsales.work.domain.ProSchemeStore;



/**
 * ProSchemeStore DAO
 * @author dandyzheng
 *
 */
public interface ProSchemeStoreDao {
	/**
	 * 保存 ProSchemeStore
	 * @param proSchemeStore ProSchemeStore
	 * @return ProSchemeStore 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProSchemeStore saveProSchemeStore(ProSchemeStore proSchemeStore);
	
	/**
	 * 批量保存ProSchemeStore
	 * @param proSchemeStores ProSchemeStores
	 * @return List<ProSchemeStore>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProSchemeStore> saveProSchemeStores(List<ProSchemeStore> proSchemeStores);
	
	/**
	 * 修改ProSchemeStore
	 * @param proSchemeStore ProSchemeStore
	 * @return ProSchemeStore
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProSchemeStore editProSchemeStore(ProSchemeStore proSchemeStore);
	
	/**
	 * 批量修改ProSchemeStore
	 * @param proSchemeStores ProSchemeStores
	 * @return List<ProSchemeStore>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProSchemeStore> editProSchemeStores(List<ProSchemeStore> proSchemeStores);
	
	/**
	 * 删除ProSchemeStore
	 * @param ProSchemeStore ProSchemeStore
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProSchemeStore(ProSchemeStore proSchemeStore);
	
	/**
	 * 批量删除ProSchemeStore
	 * @param ProSchemeStores ProSchemeStores
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProSchemeStores(List<ProSchemeStore> proSchemeStores);
	
	/**
	 * 根据ProSchemeStore' id，删除ProSchemeStore
	 * @param proSchemeStoreId ProSchemeStore's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProSchemeStore(String proSchemeStoreId);
	
	/**
	 * 根据ProSchemeStore' id，获取ProSchemeStore
	 * @param proSchemeStoreId ProSchemeStore's id
	 * @return ProSchemeStore
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProSchemeStore getProSchemeStore(String proSchemeStoreId); 
	
	/**
	 * 获取所有ProSchemeStore
	 * @return List<ProSchemeStore>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProSchemeStore> getAllProSchemeStores();
	
		
}
