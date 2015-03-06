/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename EntDeptmentMemberDaoImpl.java
 * @package cn.ttsales.work.persistence.ent.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.ent.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.domain.EntDeptmentMember;
import cn.ttsales.work.persistence.ent.EntDeptmentMemberDao;


/**
 * EntDeptmentMember Dao Impl
 * @author dandyzheng
 *
 */
@Repository("entDeptmentMemberDao")
public class EntDeptmentMemberDaoImpl extends AbstractFacade implements EntDeptmentMemberDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public EntDeptmentMember saveEntDeptmentMember(EntDeptmentMember entDeptmentMember) {
		return persist(entDeptmentMember);
	}

	public List<EntDeptmentMember> saveEntDeptmentMembers(List<EntDeptmentMember> entDeptmentMembers) {
		return persist(entDeptmentMembers);
	}

	public EntDeptmentMember editEntDeptmentMember(EntDeptmentMember entDeptmentMember) {
		return merge(entDeptmentMember);
	}

	public List<EntDeptmentMember> editEntDeptmentMembers(List<EntDeptmentMember> entDeptmentMembers) {
		return merge(entDeptmentMembers);
	}

	public void removeEntDeptmentMember(EntDeptmentMember entDeptmentMember) {
		remove(getEntDeptmentMember(entDeptmentMember.getEntDeptmentMemberId()));
	}

	public void removeEntDeptmentMembers(List<EntDeptmentMember> entDeptmentMembers) {
		remove(entDeptmentMembers);
	}

	public void removeEntDeptmentMember(String entDeptmentMemberId) {
		remove(entDeptmentMemberId);
	}

	public EntDeptmentMember getEntDeptmentMember(String entDeptmentMemberId) {
		return find(EntDeptmentMember.class, entDeptmentMemberId);
	}

	public List<EntDeptmentMember> getAllEntDeptmentMembers() {
		return this.find("from EntDeptmentMember s");
	}

	public List<EntDeptmentMember> getEntDeptmentMembersByDeptIds(String ids) {
		return this.find("from EntDeptmentMember s where s.deptId in ("+ids+")");
	}

	public String checkEntMemberIsBelongsEntStore(String memberId) {
		String jpql = "select s from EntDeptmentMember s, EntStore e " +
				"where s.memberId = ? and s.deptId = e.entDeptment.deptId";
		
		List<EntDeptmentMember> edms = this.find(jpql, memberId);
		
		if (!ArrayUtil.isEmpty(edms)) {
			return edms.get(0).getDeptId();
		}
		return null;
	}

	public List<EntDeptmentMember> getEntMemberBelongsEntStore(String memberId) {
		String jpql = "select s from EntDeptmentMember s, EntStore e " +
				"where s.memberId = ? and s.deptId = e.entDeptment.deptId";
		
		return this.find(jpql, memberId);
	}
	
	public List<EntDeptmentMember> getEntDeptMemberByMemberId(String memberId) {
		String jpql = "select s from EntDeptmentMember s where s.memberId = ?";
		
		return this.find(jpql, memberId);
	}

	public List<EntDeptmentMember> getEntDeptMemberNotBelongsEntStore(String memberId) {
		String jpql = "select s from EntDeptmentMember s, EntDeptment e "
				+ " where s.deptId = e.deptId and s.memberId = ? and e.deptType = '0'";
		
		return this.find(jpql, memberId);
	}

	public boolean checkUserInDept(String memberId, String deptId) {
		String jpql = "select s from EntDeptmentMember s where s.memberId = '"+memberId+"' and s.deptId like '"+deptId+"%' ";
		List<EntDeptmentMember> entDeptmentMembers = this.find(jpql);
		if(!ArrayUtil.isEmpty(entDeptmentMembers)){
			return true;
		} 
		return false;
	}
}
