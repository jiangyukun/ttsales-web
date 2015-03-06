/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename OrgDepartmentDao.java
 * @package cn.ttsales.work.persistence.ent
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.ent;

import java.util.List;

import cn.ttsales.org.domain.OrgDepartment;



/**
 * OrgDepartment DAO
 * @author dandyzheng
 *
 */
public interface EntDeptmentDao {
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
	 * 批量保存OrgDepartment
	 * @param OrgDepartments OrgDepartments
	 * @return List<OrgDepartment>
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
	 * 根据状态和排序获取OrgDepartments
	 * @param state
	 * @param orderBy "" "desc"
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-10-10
	 * @see
	 */
	public List<OrgDepartment> getOrgDepartmentsByStateAndOrder(String state,String orderBy);
	
	/**
	 * 新增时更新dept
	 * @param deptId
	 * @param wxid
	 * @param state
	 * @author zhaoxiaobin
	 * @date 2015-1-28
	 * @see
	 */
	public void updateDept(String deptId, String wxid, String state);
	
	/**
	 * 查询人员所在的三级部门
	 * @param memberId
	 * @return
	 */
	public String queryThreeLevelOrgDepartment(String memberId);
}
