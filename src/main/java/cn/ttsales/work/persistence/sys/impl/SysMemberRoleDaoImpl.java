/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename SysMemberRoleDaoImpl.java
 * @package cn.ttsales.work.persistence.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.domain.SysMemberRole;
import cn.ttsales.work.domain.SysRole;
import cn.ttsales.work.persistence.sys.SysMemberRoleDao;


/**
 * SysMemberRole Dao Impl
 * @author dandyzheng
 *
 */
@Repository("sysMemberRoleDao")
public class SysMemberRoleDaoImpl extends AbstractFacade implements SysMemberRoleDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public SysMemberRole saveSysMemberRole(SysMemberRole sysMemberRole) {
		return persist(sysMemberRole);
	}

	public List<SysMemberRole> saveSysMemberRoles(List<SysMemberRole> sysMemberRoles) {
		return persist(sysMemberRoles);
	}

	public SysMemberRole editSysMemberRole(SysMemberRole sysMemberRole) {
		return merge(sysMemberRole);
	}

	public List<SysMemberRole> editSysMemberRoles(List<SysMemberRole> sysMemberRoles) {
		return merge(sysMemberRoles);
	}

	public void removeSysMemberRole(SysMemberRole sysMemberRole) {
		remove(getSysMemberRole(sysMemberRole.getMemberRoleId()));
	}

	public void removeSysMemberRoles(List<SysMemberRole> sysMemberRoles) {
		remove(sysMemberRoles);
	}

	public void removeSysMemberRole(String sysMemberRoleId) {
		remove(sysMemberRoleId);
	}

	public SysMemberRole getSysMemberRole(String sysMemberRoleId) {
		return find(SysMemberRole.class, sysMemberRoleId);
	}

	public List<SysMemberRole> getAllSysMemberRoles() {
		return this.find("from SysMemberRole s");
	}

	public List<SysRole> getSysRolesByMemberId(String memberId) {
		return this.find("select s.sysRole from SysMemberRole s where s.entMember.memberId = ?",memberId);
 	}

}
