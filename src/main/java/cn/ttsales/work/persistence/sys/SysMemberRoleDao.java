/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename SysMemberRoleDao.java
 * @package cn.ttsales.work.persistence.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys;

import java.util.List;

import cn.ttsales.work.domain.SysMemberRole;
import cn.ttsales.work.domain.SysRole;



/**
 * SysMemberRole DAO
 * @author dandyzheng
 *
 */
public interface SysMemberRoleDao {
	/**
	 * 保存 SysMemberRole
	 * @param sysMemberRole SysMemberRole
	 * @return SysMemberRole 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysMemberRole saveSysMemberRole(SysMemberRole sysMemberRole);
	
	/**
	 * 批量保存SysMemberRole
	 * @param sysMemberRoles SysMemberRoles
	 * @return List<SysMemberRole>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysMemberRole> saveSysMemberRoles(List<SysMemberRole> sysMemberRoles);
	
	/**
	 * 修改SysMemberRole
	 * @param sysMemberRole SysMemberRole
	 * @return SysMemberRole
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysMemberRole editSysMemberRole(SysMemberRole sysMemberRole);
	
	/**
	 * 批量修改SysMemberRole
	 * @param sysMemberRoles SysMemberRoles
	 * @return List<SysMemberRole>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysMemberRole> editSysMemberRoles(List<SysMemberRole> sysMemberRoles);
	
	/**
	 * 删除SysMemberRole
	 * @param SysMemberRole SysMemberRole
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysMemberRole(SysMemberRole sysMemberRole);
	
	/**
	 * 批量删除SysMemberRole
	 * @param SysMemberRoles SysMemberRoles
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysMemberRoles(List<SysMemberRole> sysMemberRoles);
	
	/**
	 * 根据SysMemberRole' id，删除SysMemberRole
	 * @param sysMemberRoleId SysMemberRole's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysMemberRole(String sysMemberRoleId);
	
	/**
	 * 根据SysMemberRole' id，获取SysMemberRole
	 * @param sysMemberRoleId SysMemberRole's id
	 * @return SysMemberRole
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysMemberRole getSysMemberRole(String sysMemberRoleId); 
	
	/**
	 * 获取所有SysMemberRole
	 * @return List<SysMemberRole>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysMemberRole> getAllSysMemberRoles();
	
	/**
	 * 根据memberId获取角色
	 * @param memberId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-10-22
	 * @see
	 */
	public List<SysRole> getSysRolesByMemberId(String memberId);
	
		
}
