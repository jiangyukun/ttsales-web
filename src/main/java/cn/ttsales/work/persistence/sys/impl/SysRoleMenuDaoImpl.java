/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename SysRoleMenuDaoImpl.java
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
import cn.ttsales.work.domain.SysMenu;
import cn.ttsales.work.domain.SysRoleMenu;
import cn.ttsales.work.persistence.sys.SysRoleMenuDao;


/**
 * SysRoleMenu Dao Impl
 * @author dandyzheng
 *
 */
@Repository("sysRoleMenuDao")
public class SysRoleMenuDaoImpl extends AbstractFacade implements SysRoleMenuDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public SysRoleMenu saveSysRoleMenu(SysRoleMenu sysRoleMenu) {
		return persist(sysRoleMenu);
	}

	public List<SysRoleMenu> saveSysRoleMenus(List<SysRoleMenu> sysRoleMenus) {
		return persist(sysRoleMenus);
	}

	public SysRoleMenu editSysRoleMenu(SysRoleMenu sysRoleMenu) {
		return merge(sysRoleMenu);
	}

	public List<SysRoleMenu> editSysRoleMenus(List<SysRoleMenu> sysRoleMenus) {
		return merge(sysRoleMenus);
	}

	public void removeSysRoleMenu(SysRoleMenu sysRoleMenu) {
		remove(getSysRoleMenu(sysRoleMenu.getRoleMenuId()));
	}

	public void removeSysRoleMenus(List<SysRoleMenu> sysRoleMenus) {
		remove(sysRoleMenus);
	}

	public void removeSysRoleMenu(String sysRoleMenuId) {
		remove(sysRoleMenuId);
	}

	public SysRoleMenu getSysRoleMenu(String sysRoleMenuId) {
		return find(SysRoleMenu.class, sysRoleMenuId);
	}

	public List<SysRoleMenu> getAllSysRoleMenus() {
		return this.find("from SysRoleMenu s");
	}

	public List<SysMenu> getSysMenusByRoleIds(String roleIds) {
		return this.find("select distinct s.sysMenu from SysRoleMenu s where s.sysRole.roleId in ("+roleIds+") order by s.sysMenu.orderNumber");
	}

}
