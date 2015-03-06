/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename OrgMemberDao.java
 * @package cn.ttsales.work.persistence.ent
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.ent;

import java.util.List;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.jpa.PageModel;
import cn.ttsales.work.core.jpa.PageParam;

/**
 * OrgMember DAO
 * 
 * @author dandyzheng
 *
 */
public interface EntMemberDao {
	/**
	 * 保存 EntMember
	 * 
	 * @param entMember
	 *            orgMember
	 * @return EntMember
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public OrgMember saveOrgMember(OrgMember orgMember);

	/**
	 * 批量保存OrgMember
	 * 
	 * @param OrgMembers
	 *            OrgMembers
	 * @return List<OrgMember>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<OrgMember> saveOrgMembers(List<OrgMember> orgMembers);

	/**
	 * 修改OrgMember
	 * 
	 * @param OrgMember
	 *            OrgMember
	 * @return OrgMember
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public OrgMember editOrgMember(OrgMember orgMember);

	/**
	 * 批量修改OrgMember
	 * 
	 * @param OrgMembers
	 *            OrgMembers
	 * @return List<OrgMember>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<OrgMember> editOrgMembers(List<OrgMember> orgMembers);

	/**
	 * 删除OrgMember
	 * 
	 * @param OrgMember
	 *            OrgMember
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeOrgMember(OrgMember orgMember);

	/**
	 * 批量删除OrgMember
	 * 
	 * @param OrgMembers
	 *            OrgMembers
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeOrgMembers(List<OrgMember> orgMembers);

	/**
	 * 根据OrgMember' id，删除OrgMember
	 * 
	 * @param OrgMemberId
	 *            OrgMember's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeOrgMember(String orgMemberId);

	/**
	 * 根据OrgMember' id，获取OrgMember
	 * 
	 * @param OrgMemberId
	 *            OrgMember's id
	 * @return OrgMember
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public OrgMember getOrgMember(String orgMemberId);

	/**
	 * 获取所有OrgMember
	 * 
	 * @return List<OrgMember>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<OrgMember> getAllOrgMembers();

	/**
	 * 判断member是否存在
	 * 
	 * @param email
	 * @param mobile
	 * @param weixin
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-10-28
	 * @see
	 */
	public boolean hasExistOrgMember(String email, String mobile, String weixin);

	/**
	 * 获取一个新的memberId
	 * 
	 * @param memberId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-10-28
	 * @see
	 */
	public String getNewMemberId(String memberId);

	/**
	 * 根据memberId获取 邀请的好友
	 * 
	 * @param userId
	 * @return
	 */
	public PageModel<OrgMember> getOrgMembersByPOrgMemberId(String memberId,
			PageParam pageParam);

	/**
	 * 根据用户名获取OrgMember
	 * 
	 * @param username
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-12-18
	 * @see
	 */
	public OrgMember getOrgMemberByUsername(String username);

	/**
	 * 根据关注状态获取members
	 * 
	 * @param subState
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-12-22
	 * @see
	 */
	public List<OrgMember> getOrgMembersBySubscribeState(String subState);

}
