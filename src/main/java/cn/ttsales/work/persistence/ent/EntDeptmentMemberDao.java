/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename EntDeptmentMemberDao.java
 * @package cn.ttsales.work.persistence.ent
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.ent;

import java.util.List;

import cn.ttsales.work.domain.EntDeptmentMember;



/**
 * EntDeptmentMember DAO
 * @author dandyzheng
 *
 */
public interface EntDeptmentMemberDao {
	/**
	 * 保存 EntDeptmentMember
	 * @param entDeptmentMember EntDeptmentMember
	 * @return EntDeptmentMember 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public EntDeptmentMember saveEntDeptmentMember(EntDeptmentMember entDeptmentMember);
	
	/**
	 * 批量保存EntDeptmentMember
	 * @param entDeptmentMembers EntDeptmentMembers
	 * @return List<EntDeptmentMember>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<EntDeptmentMember> saveEntDeptmentMembers(List<EntDeptmentMember> entDeptmentMembers);
	
	/**
	 * 修改EntDeptmentMember
	 * @param entDeptmentMember EntDeptmentMember
	 * @return EntDeptmentMember
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public EntDeptmentMember editEntDeptmentMember(EntDeptmentMember entDeptmentMember);
	
	/**
	 * 批量修改EntDeptmentMember
	 * @param entDeptmentMembers EntDeptmentMembers
	 * @return List<EntDeptmentMember>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<EntDeptmentMember> editEntDeptmentMembers(List<EntDeptmentMember> entDeptmentMembers);
	
	/**
	 * 删除EntDeptmentMember
	 * @param EntDeptmentMember EntDeptmentMember
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeEntDeptmentMember(EntDeptmentMember entDeptmentMember);
	
	/**
	 * 批量删除EntDeptmentMember
	 * @param EntDeptmentMembers EntDeptmentMembers
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeEntDeptmentMembers(List<EntDeptmentMember> entDeptmentMembers);
	
	/**
	 * 根据EntDeptmentMember' id，删除EntDeptmentMember
	 * @param entDeptmentMemberId EntDeptmentMember's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeEntDeptmentMember(String entDeptmentMemberId);
	
	/**
	 * 根据EntDeptmentMember' id，获取EntDeptmentMember
	 * @param entDeptmentMemberId EntDeptmentMember's id
	 * @return EntDeptmentMember
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public EntDeptmentMember getEntDeptmentMember(String entDeptmentMemberId); 
	
	/**
	 * 获取所有EntDeptmentMember
	 * @return List<EntDeptmentMember>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<EntDeptmentMember> getAllEntDeptmentMembers();

	/**
	 * 根据部门ids获取部门成员中间表
	 * @param ids 以,分隔
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-9-13
	 * @see
	 */
	public List<EntDeptmentMember> getEntDeptmentMembersByDeptIds(String ids);
	
	/**
	 * 根据memberId，查看是否属于某个门店
	 * author: Administrator
	 * date: 2014-11-11
	 * returen_type: String
	 */
	public String checkEntMemberIsBelongsEntStore(String memberId);
	
	
	/**
	 * 根据memberId，获取用户所属的门店列表
	 * author: Administrator
	 * date: 2014-11-11
	 * returen_type: String
	 */
	public List<EntDeptmentMember> getEntMemberBelongsEntStore(String memberId);
	
	/**
	 * 根据memberId，获取用户所属部门关系记录
	 * @param memberId
	 * @return
	 */
	public List<EntDeptmentMember> getEntDeptMemberByMemberId(String memberId);
	
	/**
	 *  根据memberId，获取用户除门店以外的部门关系记录
	 * @param memberId
	 * @return
	 */
	public List<EntDeptmentMember> getEntDeptMemberNotBelongsEntStore(String memberId);

	/**
	 * 判断用户是否在当前部门下
	 * @param memberId
	 * @param deptId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2015-1-20
	 * @see
	 */
	public boolean checkUserInDept(String memberId, String deptId);
}
