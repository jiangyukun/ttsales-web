/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename OrgStoreDaoImpl.java
 * @package cn.ttsales.work.persistence.ent.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.ent.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.org.domain.OrgStore;
import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.persistence.ent.EntStoreDao;


/**
 * EntStore Dao Impl
 * @author dandyzheng
 *
 */
@Repository("entStoreDao")
public class EntStoreDaoImpl extends AbstractFacade implements EntStoreDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public OrgStore saveOrgStore(OrgStore orgStore) {
		return persist(orgStore);
	}

	public List<OrgStore> saveOrgStores(List<OrgStore> orgStores) {
		return persist(orgStores);
	}

	public OrgStore editOrgStore(OrgStore orgStore) {
		return merge(orgStore);
	}

	public List<OrgStore> editOrgStores(List<OrgStore> orgStores) {
		return merge(orgStores);
	}

	public void removeOrgStore(OrgStore orgStore) {
		remove(getOrgStore(orgStore.getStoreId()));
	}

	public void removeOrgStores(List<OrgStore> orgStores) {
		remove(orgStores);
	}

	public void removeOrgStore(String orgStoreId) {
		remove(orgStoreId);
	}

	public OrgStore getOrgStore(String orgStoreId) {
		return find(OrgStore.class, orgStoreId);
	}

	public List<OrgStore> getAllOrgStores() {
		return this.find("from OrgStore s");
	}

	public List<OrgStore> getOrgStoresByDeptId(String id) {
		return this.find("from OrgStore s where s.entDeptment.deptId = ?",id);
	}

	public List<OrgStore> getOrgStoresByConditions(String name, String address,
			String contacts) {
		String jpql = "from OrgStore s where  ";
		if(!StringUtil.isEmpty(name)){
			jpql = jpql + "s.storeName like '%"+name+"%' and    ";
		}
		if(!StringUtil.isEmpty(address)){
			jpql = jpql + "s.address like '%"+address+"%' and    ";
		}
		if(!StringUtil.isEmpty(contacts)){
			jpql = jpql + "s.contacts like '%"+contacts+"%' and    ";
		}
		jpql = jpql.substring(0,jpql.length()-7);
		
		return this.find(jpql);
	}

	public List<OrgStore> queryOrgStoresByRegionId(String regionId,String schemeId) {
		if(StringUtil.isEmpty(regionId)){
			return this.find("select s from OrgStore s,ProSchemeStore a where s.storeId = a.storeId and a.schemeId = ? order by s.storeId asc",schemeId);
		}
		return this.find("select s from OrgStore s,ProSchemeStore a where s.storeId = a.storeId and s.regionId = ? and a.schemeId = ? order by s.storeId asc",regionId,schemeId);
	}

	@SuppressWarnings("unchecked")
	public List<OrgStore> getOrgStoresByUserId(String userId) {
		List<String> deptIds = this.findNative("select d.dept_id from ent_deptment_member t,ent_deptment d,ent_member m " +
				"where t.member_id = m.member_id and t.dept_id = d.dept_id and t.member_id = '"+userId+"' and d.dept_type = '1'");
 		if(ArrayUtil.isEmpty(deptIds)){
 			return null;
 		}
		String deptIdsStr = "";
		for (String deptId : deptIds) {
			deptIdsStr = deptIdsStr +"'" +deptId + "',";
		}
		deptIdsStr=deptIdsStr.substring(0, deptIdsStr.length()-1);
		List<OrgStore> stores = this.find("from OrgStore s where s.entDeptment.deptId in ("+deptIdsStr+")");
		return stores;
	}

	public List<OrgStore> queryOrgStoreByPopularizeId(String popularizeId,String schemeId) {
		String jpql = "select s from OrgStore s, BusPopularize b,EntDeptmentMember c,EntDeptment d,ProProductScheme e,ProSchemeStore f where "
				+ "b.memberId = c.memberId and c.deptId = d.deptId and d.deptId = s.entDeptment.deptId and "
				+ "b.proProductScheme.schemeId = e.schemeId and b.proProductScheme.schemeId = f.schemeId and f.storeId = s.storeId  "
				+ "and b.popularizeId = ? and b.proProductScheme.schemeId = ? order by s.storeId asc";
		return this.find(jpql,popularizeId,schemeId);
	}

}
