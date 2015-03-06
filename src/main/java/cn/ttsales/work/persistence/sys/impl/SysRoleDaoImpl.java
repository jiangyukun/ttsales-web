/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename SysRoleDaoImpl.java
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
import cn.ttsales.work.domain.SysRole;
import cn.ttsales.work.persistence.sys.SysRoleDao;


/**
 * SysRole Dao Impl
 * @author dandyzheng
 *
 */
@Repository("sysRoleDao")
public class SysRoleDaoImpl extends AbstractFacade implements SysRoleDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public SysRole saveSysRole(SysRole sysRole) {
		return persist(sysRole);
	}

	public List<SysRole> saveSysRoles(List<SysRole> sysRoles) {
		return persist(sysRoles);
	}

	public SysRole editSysRole(SysRole sysRole) {
		return merge(sysRole);
	}

	public List<SysRole> editSysRoles(List<SysRole> sysRoles) {
		return merge(sysRoles);
	}

	public void removeSysRole(SysRole sysRole) {
		remove(getSysRole(sysRole.getRoleId()));
	}

	public void removeSysRoles(List<SysRole> sysRoles) {
		remove(sysRoles);
	}

	public void removeSysRole(String sysRoleId) {
		remove(sysRoleId);
	}

	public SysRole getSysRole(String sysRoleId) {
		return find(SysRole.class, sysRoleId);
	}

	public List<SysRole> getAllSysRoles() {
		return this.find("from SysRole s");
	}

}
