/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename SysMemberRoleServiceImpl.java
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

import cn.ttsales.work.domain.SysMemberRole;
import cn.ttsales.work.persistence.sys.SysMemberRoleDao;
import cn.ttsales.work.service.sys.SysMemberRoleService;


/**
 * SysMemberRole Service Impl
 * @author dandyzheng
 *
 */
@Service("sysMemberRoleService")
public class SysMemberRoleServiceImpl implements SysMemberRoleService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private SysMemberRoleDao sysMemberRoleDao;

	@Transactional
	public SysMemberRole saveSysMemberRole(SysMemberRole sysMemberRole) {
		return sysMemberRoleDao.saveSysMemberRole(sysMemberRole);
	}

	@Transactional
	public List<SysMemberRole> saveSysMemberRoles(List<SysMemberRole> sysMemberRoles) {
		return null;
	}

	@Transactional
	public SysMemberRole editSysMemberRole(SysMemberRole sysMemberRole) {
		return sysMemberRoleDao.editSysMemberRole(sysMemberRole);
	}

	@Transactional
	public List<SysMemberRole> editSysMemberRoles(List<SysMemberRole> sysMemberRoles) {
		return sysMemberRoleDao.editSysMemberRoles(sysMemberRoles);
	}

	@Transactional
	public void removeSysMemberRole(SysMemberRole SysMemberRole) {
		sysMemberRoleDao.removeSysMemberRole(SysMemberRole);
	}

	@Transactional
	public void removeSysMemberRoles(List<SysMemberRole> SysMemberRoles) {
		sysMemberRoleDao.removeSysMemberRoles(SysMemberRoles);		
	}

	@Transactional
	public void removeSysMemberRole(String sysMemberRoleId) {
		sysMemberRoleDao.removeSysMemberRole(sysMemberRoleId);		
	}

	public SysMemberRole getSysMemberRole(String sysMemberRoleId) {
		return sysMemberRoleDao.getSysMemberRole(sysMemberRoleId);
	}

	public List<SysMemberRole> getAllSysMemberRoles() {
		return sysMemberRoleDao.getAllSysMemberRoles();
	}
	
	
}
