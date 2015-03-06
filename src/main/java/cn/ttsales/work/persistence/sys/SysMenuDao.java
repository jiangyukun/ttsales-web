/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename SysMenuDao.java
 * @package cn.ttsales.work.persistence.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys;

import java.util.List;

import cn.ttsales.work.domain.SysMenu;



/**
 * SysMenu DAO
 * @author dandyzheng
 *
 */
public interface SysMenuDao {
	/**
	 * 保存 SysMenu
	 * @param sysMenu SysMenu
	 * @return SysMenu 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysMenu saveSysMenu(SysMenu sysMenu);
	
	/**
	 * 批量保存SysMenu
	 * @param sysMenus SysMenus
	 * @return List<SysMenu>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysMenu> saveSysMenus(List<SysMenu> sysMenus);
	
	/**
	 * 修改SysMenu
	 * @param sysMenu SysMenu
	 * @return SysMenu
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysMenu editSysMenu(SysMenu sysMenu);
	
	/**
	 * 批量修改SysMenu
	 * @param sysMenus SysMenus
	 * @return List<SysMenu>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysMenu> editSysMenus(List<SysMenu> sysMenus);
	
	/**
	 * 删除SysMenu
	 * @param SysMenu SysMenu
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysMenu(SysMenu sysMenu);
	
	/**
	 * 批量删除SysMenu
	 * @param SysMenus SysMenus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysMenus(List<SysMenu> sysMenus);
	
	/**
	 * 根据SysMenu' id，删除SysMenu
	 * @param sysMenuId SysMenu's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysMenu(String sysMenuId);
	
	/**
	 * 根据SysMenu' id，获取SysMenu
	 * @param sysMenuId SysMenu's id
	 * @return SysMenu
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysMenu getSysMenu(String sysMenuId); 
	
	/**
	 * 获取所有SysMenu
	 * @return List<SysMenu>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysMenu> getAllSysMenus();
	
	
		
}
