/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename SysRoleServiceImpl.java
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

import cn.ttsales.work.domain.SysRole;
import cn.ttsales.work.persistence.sys.SysRoleDao;
import cn.ttsales.work.service.sys.SysRoleService;


/**
 * SysRole Service Impl
 * @author dandyzheng
 *
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private SysRoleDao sysRoleDao;

	@Transactional
	public SysRole saveSysRole(SysRole sysRole) {
		return sysRoleDao.saveSysRole(sysRole);
	}

	@Transactional
	public List<SysRole> saveSysRoles(List<SysRole> sysRoles) {
		return null;
	}

	@Transactional
	public SysRole editSysRole(SysRole sysRole) {
		return sysRoleDao.editSysRole(sysRole);
	}

	@Transactional
	public List<SysRole> editSysRoles(List<SysRole> sysRoles) {
		return sysRoleDao.editSysRoles(sysRoles);
	}

	@Transactional
	public void removeSysRole(SysRole SysRole) {
		sysRoleDao.removeSysRole(SysRole);
	}

	@Transactional
	public void removeSysRoles(List<SysRole> SysRoles) {
		sysRoleDao.removeSysRoles(SysRoles);		
	}

	@Transactional
	public void removeSysRole(String sysRoleId) {
		sysRoleDao.removeSysRole(sysRoleId);		
	}

	public SysRole getSysRole(String sysRoleId) {
		return sysRoleDao.getSysRole(sysRoleId);
	}

	public List<SysRole> getAllSysRoles() {
		return sysRoleDao.getAllSysRoles();
	}
	
	
}
