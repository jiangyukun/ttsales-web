/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename SysRegionServiceImpl.java
 * @package cn.ttsales.work.service.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.domain.SysRegion;
import cn.ttsales.work.persistence.sys.SysRegionDao;
import cn.ttsales.work.service.sys.SysRegionService;


/**
 * SysRegion Service Impl
 * @author dandyzheng
 *
 */
@Service("sysRegionService")
public class SysRegionServiceImpl implements SysRegionService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private SysRegionDao sysRegionDao;

	@Transactional
	public SysRegion saveSysRegion(SysRegion sysRegion) {
		return sysRegionDao.saveSysRegion(sysRegion);
	}

	@Transactional
	public List<SysRegion> saveSysRegions(List<SysRegion> sysRegions) {
		return null;
	}

	@Transactional
	public SysRegion editSysRegion(SysRegion sysRegion) {
		return sysRegionDao.editSysRegion(sysRegion);
	}

	@Transactional
	public List<SysRegion> editSysRegions(List<SysRegion> sysRegions) {
		return sysRegionDao.editSysRegions(sysRegions);
	}

	@Transactional
	public void removeSysRegion(SysRegion SysRegion) {
		sysRegionDao.removeSysRegion(SysRegion);
	}

	@Transactional
	public void removeSysRegions(List<SysRegion> SysRegions) {
		sysRegionDao.removeSysRegions(SysRegions);		
	}

	@Transactional
	public void removeSysRegion(String sysRegionId) {
		sysRegionDao.removeSysRegion(sysRegionId);		
	}

	public SysRegion getSysRegion(String sysRegionId) {
		return sysRegionDao.getSysRegion(sysRegionId);
	}

	public List<SysRegion> getAllSysRegions() {
		return sysRegionDao.getAllSysRegions();
	}
	
	
	public List<SysRegion> querySysRegionsBySchemeId(String schemeId){
		return sysRegionDao.querySysRegionsBySchemeId(schemeId);
	}
	
}
