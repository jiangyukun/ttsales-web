/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename SysRegionDaoImpl.java
 * @package cn.ttsales.work.persistence.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.domain.SysRegion;
import cn.ttsales.work.persistence.sys.SysRegionDao;


/**
 * SysRegion Dao Impl
 * @author dandyzheng
 *
 */
@Repository("sysRegionDao")
public class SysRegionDaoImpl extends AbstractFacade implements SysRegionDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public SysRegion saveSysRegion(SysRegion sysRegion) {
		return persist(sysRegion);
	}

	public List<SysRegion> saveSysRegions(List<SysRegion> sysRegions) {
		return persist(sysRegions);
	}

	public SysRegion editSysRegion(SysRegion sysRegion) {
		return merge(sysRegion);
	}

	public List<SysRegion> editSysRegions(List<SysRegion> sysRegions) {
		return merge(sysRegions);
	}

	public void removeSysRegion(SysRegion sysRegion) {
		remove(getSysRegion(sysRegion.getRegionId()));
	}

	public void removeSysRegions(List<SysRegion> sysRegions) {
		remove(sysRegions);
	}

	public void removeSysRegion(String sysRegionId) {
		remove(sysRegionId);
	}

	public SysRegion getSysRegion(String sysRegionId) {
		return find(SysRegion.class, sysRegionId);
	}

	public List<SysRegion> getAllSysRegions() {
		return this.find("from SysRegion s order by s.pinyinHead asc");
	}
	
	public List<SysRegion> querySysRegionsBySchemeId(String schemeId){
		return this.find("select DISTINCT s from SysRegion s,ProSchemeStore p,EntStore e where p.schemeId = ? and p.storeId = e.storeId and e.regionId = s.regionId order by s.pinyinHead asc",schemeId);
	}

}
