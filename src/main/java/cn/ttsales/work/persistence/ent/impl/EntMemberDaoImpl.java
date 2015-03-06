/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename OrgMemberDaoImpl.java
 * @package cn.ttsales.work.persistence.ent.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.ent.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.jpa.PageModel;
import cn.ttsales.work.core.jpa.PageParam;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.persistence.ent.EntMemberDao;


/**
 * EntMember Dao Impl
 * @author dandyzheng
 *
 */
@Repository("entMemberDao")
public class EntMemberDaoImpl extends AbstractFacade implements EntMemberDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public OrgMember saveOrgMember(OrgMember orgMember) {
		return persist(orgMember);
	}

	public List<OrgMember> saveOrgMembers(List<OrgMember> orgMembers) {
		return persist(orgMembers);
	}

	public OrgMember editOrgMember(OrgMember orgMember) {
		return merge(orgMember);
	}

	public List<OrgMember> editOrgMembers(List<OrgMember> orgMembers) {
		return merge(orgMembers);
	}

	public void removeOrgMember(OrgMember orgMember) {
		remove(getOrgMember(orgMember.getMemberId()));
	}

	public void removeOrgMembers(List<OrgMember> orgMembers) {
		remove(orgMembers);
	}

	public void removeOrgMember(String orgMemberId) {
		remove(getOrgMember(orgMemberId));
	}

	public OrgMember getOrgMember(String orgMemberId) {
		return find(OrgMember.class, orgMemberId);
	}

	public List<OrgMember> getAllOrgMembers() {
		return this.find("from OrgMember s");
	}

	public boolean hasExistOrgMember(String email, String mobile, String weixin) {
		Object[] params = new Object[]{email,mobile,weixin};
		List<OrgMember> members = this.find("from OrgMember s where s.email = ? or s.mobile = ? or s.weixin = ?",params);
		if(!ArrayUtil.isEmpty(members)){
			return true;
		}
		return false;
	}

	public String getNewMemberId(String memberId) {
		List<OrgMember> members = this.find("from OrgMember s where s.memberId like '%"+memberId+"%' order by s.memberId desc");
		if(ArrayUtil.isEmpty(members)){
			return memberId;
		}else{
			String lastMemberId = members.get(0).getMemberId();
			int maxNum = getLastNum(lastMemberId);
			return memberId+String.valueOf((maxNum+1));
		}
	}
	
	public int getLastNum(String str){
		for (int i = 0; i < str.length(); i++){
			   if (Character.isDigit(str.charAt(i))){
				   String ss = str.substring(i,str.length());
				   return Integer.valueOf(ss);
			   }
		}
		return 1;
	}

	public PageModel<OrgMember> getOrgMembersByPOrgMemberId(String memberId,PageParam pageParam) {
		//List<OrgMember> members = this.find("from OrgMember s where s.pMemberId ='"+memberId+"' order by s.inviteTime desc");
		String jpql = "from OrgMember s where s.pMemberId = ? order by s.inviteTime desc";
		return this.find(jpql, pageParam, new String[]{memberId});
	}

	public OrgMember getOrgMemberByUsername(String username) {
		List<OrgMember> members= this.find("from OrgMember s where username = ?",username);
		if(ArrayUtil.isEmpty(members)){
			return null;
		}
		return members.get(0);
	}

	public List<OrgMember> getOrgMembersBySubscribeState(String subState) {
 		return this.find("from OrgMember s where s.subscribeState = ?",subState);
	}	 
}
