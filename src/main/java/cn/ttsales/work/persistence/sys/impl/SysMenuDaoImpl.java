/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename SysMenuDaoImpl.java
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
import cn.ttsales.work.persistence.sys.SysMenuDao;


/**
 * SysMenu Dao Impl
 * @author dandyzheng
 *
 */
@Repository("sysMenuDao")
public class SysMenuDaoImpl extends AbstractFacade implements SysMenuDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public SysMenu saveSysMenu(SysMenu sysMenu) {
		return persist(sysMenu);
	}

	public List<SysMenu> saveSysMenus(List<SysMenu> sysMenus) {
		return persist(sysMenus);
	}

	public SysMenu editSysMenu(SysMenu sysMenu) {
		return merge(sysMenu);
	}

	public List<SysMenu> editSysMenus(List<SysMenu> sysMenus) {
		return merge(sysMenus);
	}

	public void removeSysMenu(SysMenu sysMenu) {
		remove(getSysMenu(sysMenu.getMenuId()));
	}

	public void removeSysMenus(List<SysMenu> sysMenus) {
		remove(sysMenus);
	}

	public void removeSysMenu(String sysMenuId) {
		remove(sysMenuId);
	}

	public SysMenu getSysMenu(String sysMenuId) {
		return find(SysMenu.class, sysMenuId);
	}

	public List<SysMenu> getAllSysMenus() {
		return this.find("from SysMenu s");
	}

	

}
