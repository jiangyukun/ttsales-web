/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename OrgStoreService.java
 * @package cn.ttsales.work.service.ent
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.ent;

import java.util.List;

import cn.ttsales.org.domain.OrgStore;


/**
 * orgStore Service
 * @author dandyzheng
 *
 */
public interface EntStoreService {
	/**
	 * 保存 OrgStore
	 * @param OrgStore OrgStore
	 * @return OrgStore 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public OrgStore saveOrgStore(OrgStore orgStore);
	
	/**
	 * 批量保存OrgStore
	 * @param OrgStores OrgStores
	 * @return List<OrgStore>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<OrgStore> saveOrgStores(List<OrgStore> orgStores);
	
	/**
	 * 修改OrgStore
	 * @param OrgStore OrgStore
	 * @return OrgStore
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public OrgStore editOrgStore(OrgStore orgStore);
	
	/**
	 * 批量修改OrgStore
	 * @param OrgStores OrgStores
	 * @return List<OrgStore>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<OrgStore> editOrgStores(List<OrgStore> orgStores);
	
	/**
	 * 删除OrgStore
	 * @param OrgStore OrgStore
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeOrgStore(OrgStore orgStore);
	
	/**
	 * 批量删除OrgStore
	 * @param OrgStores OrgStores
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeOrgStores(List<OrgStore> orgStores);
	
	/**
	 * 根据orgStore' id，删除orgStore
	 * @param orgStoreId orgStore's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeOrgStore(String OrgStoreId);
	
	/**
	 * 根据OrgStore' id，获取OrgStore
	 * @param OrgStoreId OrgStore's id
	 * @return OrgStore
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public OrgStore getOrgStore(String orgStoreId); 
	
	/**
	 * 获取所有OrgStore
	 * @return List<OrgStore>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<OrgStore> getAllOrgStores();
	/**
	 * 根据部门id获取门店
	 * @param id
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-9-12
	 * @see
	 */

	public List<OrgStore> getOrgStoresByDeptId(String id);	
	
	/**
	 * 获取门店的签到二维码，如果不存在，则生成新的二维码并返回
	 * @param userId
	 * @return 
	 */
	public byte[] getQCCode(String storeId,String serverName, String contextPath);
	/**
	 * 根据条件获取OrgStores
	 * @param name
	 * @param address
	 * @param contacts
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-9-15
	 * @see
	 */
	public List<OrgStore> getOrgStoresByConditions(String name, String address,
			String contacts);	
	
	/**
	 * 根据门店所在的地区ID,参与广告,获取所有门店
	 * @param regionId 地点ID
	 * @param schemeId 文案ID
	 * @return
	 * @author dandyzheng
	 * @date 2014-9-15
	 * @see
	 */
	public  List<OrgStore> queryOrgStoresByRegionId(String regionId,String schemeId);

	/**
	 * 根据用户id获取门店
	 * @param userId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-9-16
	 * @see
	 */
	public List<OrgStore> getOrgStoresByUserId(String userId);	
	
	/**
	 * 根据推广ID，获取门店
	 * @param popularizeId
	 * @return
	 * @author dandyzheng
	 * @date 2014年11月10日
	 * @see
	 */
	public List<OrgStore> queryOrgStoreByPopularizeId(String popularizeId,String schemeId);
}
