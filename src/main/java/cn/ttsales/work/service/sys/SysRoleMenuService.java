/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename SysRoleMenuService.java
 * @package cn.ttsales.work.service.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys;

import java.util.List;

import cn.ttsales.work.domain.SysRoleMenu;


/**
 * SysRoleMenu Service
 * @author dandyzheng
 *
 */
public interface SysRoleMenuService {
	/**
	 * 保存 SysRoleMenu
	 * @param sysRoleMenu SysRoleMenu
	 * @return SysRoleMenu 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysRoleMenu saveSysRoleMenu(SysRoleMenu sysRoleMenu);
	
	/**
	 * 批量保存SysRoleMenu
	 * @param sysRoleMenus SysRoleMenus
	 * @return List<SysRoleMenu>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysRoleMenu> saveSysRoleMenus(List<SysRoleMenu> sysRoleMenus);
	
	/**
	 * 修改SysRoleMenu
	 * @param sysRoleMenu SysRoleMenu
	 * @return SysRoleMenu
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysRoleMenu editSysRoleMenu(SysRoleMenu sysRoleMenu);
	
	/**
	 * 批量修改SysRoleMenu
	 * @param sysRoleMenus SysRoleMenus
	 * @return List<SysRoleMenu>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysRoleMenu> editSysRoleMenus(List<SysRoleMenu> sysRoleMenus);
	
	/**
	 * 删除SysRoleMenu
	 * @param SysRoleMenu SysRoleMenu
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysRoleMenu(SysRoleMenu sysRoleMenu);
	
	/**
	 * 批量删除SysRoleMenu
	 * @param SysRoleMenus SysRoleMenus
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysRoleMenus(List<SysRoleMenu> sysRoleMenus);
	
	/**
	 * 根据SysRoleMenu' id，删除SysRoleMenu
	 * @param sysRoleMenuId SysRoleMenu's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysRoleMenu(String sysRoleMenuId);
	
	/**
	 * 根据SysRoleMenu' id，获取SysRoleMenu
	 * @param sysRoleMenuId SysRoleMenu's id
	 * @return SysRoleMenu
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysRoleMenu getSysRoleMenu(String sysRoleMenuId); 
	
	/**
	 * 获取所有SysRoleMenu
	 * @return List<SysRoleMenu>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysRoleMenu> getAllSysRoleMenus();	
}
