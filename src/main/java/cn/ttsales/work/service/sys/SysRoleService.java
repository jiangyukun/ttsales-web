/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename SysRoleService.java
 * @package cn.ttsales.work.service.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys;

import java.util.List;

import cn.ttsales.work.domain.SysRole;


/**
 * SysRole Service
 * @author dandyzheng
 *
 */
public interface SysRoleService {
	/**
	 * 保存 SysRole
	 * @param sysRole SysRole
	 * @return SysRole 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysRole saveSysRole(SysRole sysRole);
	
	/**
	 * 批量保存SysRole
	 * @param sysRoles SysRoles
	 * @return List<SysRole>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysRole> saveSysRoles(List<SysRole> sysRoles);
	
	/**
	 * 修改SysRole
	 * @param sysRole SysRole
	 * @return SysRole
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysRole editSysRole(SysRole sysRole);
	
	/**
	 * 批量修改SysRole
	 * @param sysRoles SysRoles
	 * @return List<SysRole>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysRole> editSysRoles(List<SysRole> sysRoles);
	
	/**
	 * 删除SysRole
	 * @param SysRole SysRole
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysRole(SysRole sysRole);
	
	/**
	 * 批量删除SysRole
	 * @param SysRoles SysRoles
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysRoles(List<SysRole> sysRoles);
	
	/**
	 * 根据SysRole' id，删除SysRole
	 * @param sysRoleId SysRole's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysRole(String sysRoleId);
	
	/**
	 * 根据SysRole' id，获取SysRole
	 * @param sysRoleId SysRole's id
	 * @return SysRole
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysRole getSysRole(String sysRoleId); 
	
	/**
	 * 获取所有SysRole
	 * @return List<SysRole>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysRole> getAllSysRoles();	
}
