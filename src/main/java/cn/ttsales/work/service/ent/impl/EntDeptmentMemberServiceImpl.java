/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename EntDeptmentMemberServiceImpl.java
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

import cn.ttsales.work.domain.EntDeptmentMember;
import cn.ttsales.work.persistence.ent.EntDeptmentMemberDao;
import cn.ttsales.work.service.ent.EntDeptmentMemberService;


/**
 * EntDeptmentMember Service Impl
 * @author dandyzheng
 *
 */
@Service("entDeptmentMemberService")
public class EntDeptmentMemberServiceImpl implements EntDeptmentMemberService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private EntDeptmentMemberDao entDeptmentMemberDao;

	@Transactional
	public EntDeptmentMember saveEntDeptmentMember(EntDeptmentMember entDeptmentMember) {
		return entDeptmentMemberDao.saveEntDeptmentMember(entDeptmentMember);
	}

	@Transactional
	public List<EntDeptmentMember> saveEntDeptmentMembers(List<EntDeptmentMember> entDeptmentMembers) {
		return entDeptmentMemberDao.saveEntDeptmentMembers(entDeptmentMembers);
	}

	@Transactional
	public EntDeptmentMember editEntDeptmentMember(EntDeptmentMember entDeptmentMember) {
		return entDeptmentMemberDao.editEntDeptmentMember(entDeptmentMember);
	}

	@Transactional
	public List<EntDeptmentMember> editEntDeptmentMembers(List<EntDeptmentMember> entDeptmentMembers) {
		return entDeptmentMemberDao.editEntDeptmentMembers(entDeptmentMembers);
	}

	@Transactional
	public void removeEntDeptmentMember(EntDeptmentMember EntDeptmentMember) {
		entDeptmentMemberDao.removeEntDeptmentMember(EntDeptmentMember);
	}

	@Transactional
	public void removeEntDeptmentMembers(List<EntDeptmentMember> EntDeptmentMembers) {
		entDeptmentMemberDao.removeEntDeptmentMembers(EntDeptmentMembers);		
	}

	@Transactional
	public void removeEntDeptmentMember(String entDeptmentMemberId) {
		entDeptmentMemberDao.removeEntDeptmentMember(entDeptmentMemberId);		
	}

	public EntDeptmentMember getEntDeptmentMember(String entDeptmentMemberId) {
		return entDeptmentMemberDao.getEntDeptmentMember(entDeptmentMemberId);
	}

	public List<EntDeptmentMember> getAllEntDeptmentMembers() {
		return entDeptmentMemberDao.getAllEntDeptmentMembers();
	}

	public List<EntDeptmentMember> getEntDeptmentMembersByDeptIds(String ids) {
		return entDeptmentMemberDao.getEntDeptmentMembersByDeptIds(ids);
	}

	public String checkEntMemberIsBelongsEntStore(String memberId) {
		return entDeptmentMemberDao.checkEntMemberIsBelongsEntStore(memberId);
	}

	public List<EntDeptmentMember> getEntMemberBelongsEntStore(String memberId) {
		return entDeptmentMemberDao.getEntMemberBelongsEntStore(memberId);
	}

	public boolean checkUserInDept(String memberId, String deptId) {
		return entDeptmentMemberDao.checkUserInDept(memberId,deptId);
	}
}
