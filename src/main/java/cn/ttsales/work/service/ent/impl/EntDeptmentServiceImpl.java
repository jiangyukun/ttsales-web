/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename OrgDepartmentServiceImpl.java
 * @package cn.ttsales.work.service.ent.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.ent.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.org.domain.OrgDepartment;
import cn.ttsales.work.persistence.ent.EntDeptmentDao;
import cn.ttsales.work.service.ent.EntDeptmentService;


/**
 * EntDeptment Service Impl
 * @author dandyzheng
 *
 */
@Service("entDeptmentService")
public class EntDeptmentServiceImpl implements EntDeptmentService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private EntDeptmentDao entDeptmentDao;

	@Transactional
	public OrgDepartment saveOrgDepartment(OrgDepartment entDeptment) {
		return entDeptmentDao.saveOrgDepartment(entDeptment);
	}

	@Transactional
	public List<OrgDepartment> saveOrgDepartments(List<OrgDepartment> entDeptments) {
		return null;
	}

	@Transactional
	public OrgDepartment editOrgDepartment(OrgDepartment entDeptment) {
		return entDeptmentDao.editOrgDepartment(entDeptment);
	}

	@Transactional
	public List<OrgDepartment> editOrgDepartments(List<OrgDepartment> entDeptments) {
		return entDeptmentDao.editOrgDepartments(entDeptments);
	}

	@Transactional
	public void removeOrgDepartment(OrgDepartment EntDeptment) {
		entDeptmentDao.removeOrgDepartment(EntDeptment);
	}

	@Transactional
	public void removeOrgDepartments(List<OrgDepartment> EntDeptments) {
		entDeptmentDao.removeOrgDepartments(EntDeptments);		
	}

	@Transactional
	public void removeOrgDepartment(String entDeptmentId) {
		entDeptmentDao.removeOrgDepartment(entDeptmentId);		
	}

	public OrgDepartment getOrgDepartment(String entDeptmentId) {
		return entDeptmentDao.getOrgDepartment(entDeptmentId);
	}

	public List<OrgDepartment> getAllOrgDepartments() {
		return entDeptmentDao.getAllOrgDepartments();
	}

	public List<OrgDepartment> getOrgDepartmentsByParentId(String id) {
		return entDeptmentDao.getOrgDepartmentsByParentId(id);
	}

	public String queryThreeLevelOrgDepartment(String memberId) {
		return entDeptmentDao.queryThreeLevelOrgDepartment(memberId);
	}
}
