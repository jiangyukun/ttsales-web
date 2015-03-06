/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename SysRoleMenuServiceImpl.java
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

import cn.ttsales.work.domain.SysRoleMenu;
import cn.ttsales.work.persistence.sys.SysRoleMenuDao;
import cn.ttsales.work.service.sys.SysRoleMenuService;


/**
 * SysRoleMenu Service Impl
 * @author dandyzheng
 *
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Transactional
	public SysRoleMenu saveSysRoleMenu(SysRoleMenu sysRoleMenu) {
		return sysRoleMenuDao.saveSysRoleMenu(sysRoleMenu);
	}

	@Transactional
	public List<SysRoleMenu> saveSysRoleMenus(List<SysRoleMenu> sysRoleMenus) {
		return null;
	}

	@Transactional
	public SysRoleMenu editSysRoleMenu(SysRoleMenu sysRoleMenu) {
		return sysRoleMenuDao.editSysRoleMenu(sysRoleMenu);
	}

	@Transactional
	public List<SysRoleMenu> editSysRoleMenus(List<SysRoleMenu> sysRoleMenus) {
		return sysRoleMenuDao.editSysRoleMenus(sysRoleMenus);
	}

	@Transactional
	public void removeSysRoleMenu(SysRoleMenu SysRoleMenu) {
		sysRoleMenuDao.removeSysRoleMenu(SysRoleMenu);
	}

	@Transactional
	public void removeSysRoleMenus(List<SysRoleMenu> SysRoleMenus) {
		sysRoleMenuDao.removeSysRoleMenus(SysRoleMenus);		
	}

	@Transactional
	public void removeSysRoleMenu(String sysRoleMenuId) {
		sysRoleMenuDao.removeSysRoleMenu(sysRoleMenuId);		
	}

	public SysRoleMenu getSysRoleMenu(String sysRoleMenuId) {
		return sysRoleMenuDao.getSysRoleMenu(sysRoleMenuId);
	}

	public List<SysRoleMenu> getAllSysRoleMenus() {
		return sysRoleMenuDao.getAllSysRoleMenus();
	}
	
	
}
