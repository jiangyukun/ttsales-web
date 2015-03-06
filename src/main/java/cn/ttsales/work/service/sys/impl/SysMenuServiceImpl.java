/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename SysMenuServiceImpl.java
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

import cn.ttsales.work.domain.SysMenu;
import cn.ttsales.work.domain.SysRole;
import cn.ttsales.work.persistence.sys.SysMemberRoleDao;
import cn.ttsales.work.persistence.sys.SysMenuDao;
import cn.ttsales.work.persistence.sys.SysRoleMenuDao;
import cn.ttsales.work.service.sys.SysMenuService;


/**
 * SysMenu Service Impl
 * @author dandyzheng
 *
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysMemberRoleDao sysMemberRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Transactional
	public SysMenu saveSysMenu(SysMenu sysMenu) {
		return sysMenuDao.saveSysMenu(sysMenu);
	}

	@Transactional
	public List<SysMenu> saveSysMenus(List<SysMenu> sysMenus) {
		return null;
	}

	@Transactional
	public SysMenu editSysMenu(SysMenu sysMenu) {
		return sysMenuDao.editSysMenu(sysMenu);
	}

	@Transactional
	public List<SysMenu> editSysMenus(List<SysMenu> sysMenus) {
		return sysMenuDao.editSysMenus(sysMenus);
	}

	@Transactional
	public void removeSysMenu(SysMenu SysMenu) {
		sysMenuDao.removeSysMenu(SysMenu);
	}

	@Transactional
	public void removeSysMenus(List<SysMenu> SysMenus) {
		sysMenuDao.removeSysMenus(SysMenus);		
	}

	@Transactional
	public void removeSysMenu(String sysMenuId) {
		sysMenuDao.removeSysMenu(sysMenuId);		
	}

	public SysMenu getSysMenu(String sysMenuId) {
		return sysMenuDao.getSysMenu(sysMenuId);
	}

	public List<SysMenu> getAllSysMenus() {
		return sysMenuDao.getAllSysMenus();
	}

	public List<SysMenu> getSysMenusByMemberId(String memberId) {
		List<SysRole> sysRoles = sysMemberRoleDao.getSysRolesByMemberId(memberId);
		String ids = "";
		for (SysRole sysRole : sysRoles) {
			ids = ids + sysRole.getRoleId()+",";
		}
		ids = ids.substring(0, ids.length()-1);
		List<SysMenu> sysMenus = sysRoleMenuDao.getSysMenusByRoleIds(ids);
		return sysMenus;
	}
	
	
}
