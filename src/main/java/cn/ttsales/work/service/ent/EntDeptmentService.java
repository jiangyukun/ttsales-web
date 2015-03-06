/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename OrgDepartmentService.java
 * @package cn.ttsales.work.service.ent
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.ent;

import java.util.List;

import cn.ttsales.org.domain.OrgDepartment;


/**
 * orgDepartment Service
 * @author dandyzheng
 *
 */
public interface EntDeptmentService {
	/**
	 * 保存 OrgDepartment
	 * @param OrgDepartment OrgDepartment
	 * @return OrgDepartment 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public OrgDepartment saveOrgDepartment(OrgDepartment orgDepartment);
	
	/**
	 * 批量保存orgDepartment
	 * @param orgDepartments orgDepartments
	 * @return List<orgDepartment>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<OrgDepartment> saveOrgDepartments(List<OrgDepartment> orgDepartments);
	
	/**
	 * 修改OrgDepartment
	 * @param OrgDepartment OrgDepartment
	 * @return OrgDepartment
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public OrgDepartment editOrgDepartment(OrgDepartment orgDepartment);
	
	/**
	 * 批量修改OrgDepartment
	 * @param OrgDepartments OrgDepartments
	 * @return List<OrgDepartment>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<OrgDepartment> editOrgDepartments(List<OrgDepartment> orgDepartments);
	
	/**
	 * 删除OrgDepartment
	 * @param OrgDepartment OrgDepartment
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeOrgDepartment(OrgDepartment orgDepartment);
	
	/**
	 * 批量删除OrgDepartment
	 * @param OrgDepartments OrgDepartments
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeOrgDepartments(List<OrgDepartment> orgDepartments);
	
	/**
	 * 根据OrgDepartment' id，删除OrgDepartment
	 * @param OrgDepartmentId OrgDepartment's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeOrgDepartment(String orgDepartmentId);
	
	/**
	 * 根据OrgDepartment' id，获取OrgDepartment
	 * @param OrgDepartmentId OrgDepartment's id
	 * @return OrgDepartment
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public OrgDepartment getOrgDepartment(String orgDepartmentId); 
	
	/**
	 * 获取所有OrgDepartment
	 * @return List<OrgDepartment>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<OrgDepartment> getAllOrgDepartments();
	/**
	 * 根据父部门id获取部门
	 * @param id
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-9-12
	 * @see
	 */
	public List<OrgDepartment> getOrgDepartmentsByParentId(String id);
	 
	/**
	 * 查询人员所在的三级部门
	 * @param memberId
	 * @return
	 */
	public String queryThreeLevelOrgDepartment(String memberId);
}
