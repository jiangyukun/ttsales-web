/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename OrgDepartmentDaoImpl.java
 * @package cn.ttsales.work.persistence.ent.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.ent.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.org.domain.OrgDepartment;
import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.persistence.ent.EntDeptmentDao;


/**
 * OrgDepartment Dao Impl
 * @author dandyzheng
 *
 */
@Repository("entDeptmentDao")
public class EntDeptmentDaoImpl extends AbstractFacade implements EntDeptmentDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public OrgDepartment saveOrgDepartment(OrgDepartment orgDepartment) {
		return persist(orgDepartment);
	}

	public List<OrgDepartment> saveOrgDepartments(List<OrgDepartment> orgDepartments) {
		return persist(orgDepartments);
	}

	public OrgDepartment editOrgDepartment(OrgDepartment orgDepartment) {
		return merge(orgDepartment);
	}

	public List<OrgDepartment> editOrgDepartments(List<OrgDepartment> orgDepartments) {
		return merge(orgDepartments);
	}

	public void removeOrgDepartment(OrgDepartment orgDepartment) {
		remove(getOrgDepartment(orgDepartment.getDeptId()));
	}

	public void removeOrgDepartments(List<OrgDepartment> orgDepartments) {
		remove(orgDepartments);
	}

	public void removeOrgDepartment(String orgDepartmentId) {
		remove(orgDepartmentId);
	}

	public OrgDepartment getOrgDepartment(String orgDepartmentId) {
		return find(OrgDepartment.class, orgDepartmentId);
	}

	public List<OrgDepartment> getAllOrgDepartments() {
		return this.find("from OrgDepartment s");
	}

	public List<OrgDepartment> getOrgDepartmentsByParentId(String id) {
		return this.find("from OrgDepartment s where s.pDeptId = ?",id);
	}

	public List<OrgDepartment> getOrgDepartmentsByStateAndOrder(String state,String orderBy) {
		return this.find("from OrgDepartment s where s.state = ? order by s.deptLevel "+orderBy,state);
	}

	public void updateDept(String deptId, String wxid, String state) {
		String sql = "update ent_deptment t set t.state = '"+state+"' , t.wx_dept_id = '"+wxid+"' where t.dept_id = '"+deptId+"'";
		this.executeNative(sql);
		
	}

	public String queryThreeLevelOrgDepartment(String memberId) {
		String jpql = " SELECT dept_name FROM ent_deptment a " +
				 "  WHERE a.dept_id = ( " +
				 " 	SELECT CONCAT('4', SUBSTRING(a.dept_id FROM 2 FOR 2)) " +
				 " 	FROM ent_deptment_member a " +
				 " 	WHERE a.member_id = ? limit 1 )";
		
		@SuppressWarnings("unchecked")
		List<String> deptName = this.findNative(jpql, new Object[]{memberId});
		if (ArrayUtil.isEmpty(deptName)) {
			return null;
		}
		return deptName.get(0);
	}
}
