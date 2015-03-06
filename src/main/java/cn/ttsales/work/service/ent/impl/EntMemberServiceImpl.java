/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename EntMemberServiceImpl.java
 * @package cn.ttsales.work.service.ent.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.ent.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.jpa.MyUUIDGenerator;
import cn.ttsales.work.core.jpa.PageModel;
import cn.ttsales.work.core.jpa.PageParam;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.core.util.MD5Util;
import cn.ttsales.work.domain.BusPopularize;
import cn.ttsales.work.domain.EntDeptmentMember;
import cn.ttsales.work.persistence.bus.BusPopularizeDao;
import cn.ttsales.work.persistence.ent.EntDeptmentMemberDao;
import cn.ttsales.work.persistence.ent.EntMemberDao;
import cn.ttsales.work.service.ent.EntMemberService;


/**
 * EntMember Service Impl
 * @author dandyzheng
 *
 */
@Service("entMemberService")
public class EntMemberServiceImpl implements EntMemberService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private EntMemberDao entMemberDao;
	@Autowired
	private BusPopularizeDao busPopularizeDao;
	@Autowired
	private EntDeptmentMemberDao entDeptmentMemberDao;

	@Transactional
	public OrgMember saveOrgMember(OrgMember orgMember) {
		return entMemberDao.saveOrgMember(orgMember);
	}

	@Transactional
	public List<OrgMember> saveOrgMembers(List<OrgMember> orgMembers) {
		return null;
	}

	@Transactional
	public OrgMember editOrgMember(OrgMember orgMember) {
		return entMemberDao.editOrgMember(orgMember);
	}

	@Transactional
	public List<OrgMember> editOrgMembers(List<OrgMember> orgMembers) {
		return entMemberDao.editOrgMembers(orgMembers);
	}

	@Transactional
	public void removeOrgMember(OrgMember orgMember) {
		entMemberDao.removeOrgMember(orgMember);
	}

	@Transactional
	public void removeOrgMembers(List<OrgMember> orgMembers) {
		entMemberDao.removeOrgMembers(orgMembers);		
	}

	@Transactional
	public void removeOrgMember(String orgMemberId) {
		entMemberDao.removeOrgMember(orgMemberId);		
	}

	public OrgMember getOrgMember(String orgMemberId) {
		return entMemberDao.getOrgMember(orgMemberId);
	}

	public List<OrgMember> getAllOrgMembers() {
		return entMemberDao.getAllOrgMembers();
	}

	public boolean hasExistOrgMember(String email, String mobile, String weixin) {
		return entMemberDao.hasExistOrgMember(email,mobile,weixin);
	}

	public String getNewMemberId(String memberId) {
		return entMemberDao.getNewMemberId(memberId);
	}

	public PageModel<OrgMember> getOrgMembersByPOrgMemberId(String memberId,PageParam pageParam) {
		return entMemberDao.getOrgMembersByPOrgMemberId(memberId,pageParam);
	}
	
	@Transactional
	public JSONObject saveInviMember(String name, String weixinid, String gender,
			String userId, String joinStore, String shareProduct) {
		
		JSONObject result = new JSONObject();
		
		if (entMemberDao.hasExistOrgMember(weixinid, weixinid, weixinid)) {
			result.put("code", -1);
			return result;
		}
		
		//企业成员
		OrgMember member = new OrgMember();
		member.setName(name);
		member.setGender(gender);

		if (weixinid.matches("^\\d+$")) {
			member.setMobile(weixinid);
		} else {
			member.setWeixin(weixinid);
		}
		String memberId = MyUUIDGenerator.createUUID();
		member.setSubscribeState("4");
 		member.setMemberId(memberId);
		member.setParentMemberId(userId);
		member.setState(SASConstants.SYNC_STATE_ADD);
		member.setInviteTime(DateUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss"));
		member.setPassword(MD5Util.EncoderByMd52("123456"));
		
		//分享产品
		if ("true".equals(shareProduct)) {
			List<BusPopularize> bps = busPopularizeDao.queryBusPopularizesByMemberId(userId);
			if (!ArrayUtil.isEmpty(bps)) {
				List<BusPopularize> newbps = new ArrayList<BusPopularize>();
				for (BusPopularize b : bps) {
					BusPopularize nb = new BusPopularize();
					nb.setMemberId(memberId);
					nb.setState(b.getState());
					nb.setPopularizeId(MyUUIDGenerator.initUUID(b.getProProductScheme().getSchemeId()+memberId));
					nb.setProProductScheme(b.getProProductScheme());
					nb.setCreateTime(DateUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss"));
					newbps.add(nb);
				}
				busPopularizeDao.editBusPopularizes(newbps);
			}
		}
		
		//加入本店
		List<EntDeptmentMember> edms = null;
		if ("true".equals(joinStore)) {
			edms = entDeptmentMemberDao.getEntDeptMemberByMemberId(userId);
		} else {
			edms = entDeptmentMemberDao.getEntDeptMemberNotBelongsEntStore(userId);
		}
		if (!ArrayUtil.isEmpty(edms)) {
			List<EntDeptmentMember> newEdm = new ArrayList<EntDeptmentMember>();
			for (EntDeptmentMember e : edms) {
				EntDeptmentMember ne = new EntDeptmentMember();
				ne.setDeptId(e.getDeptId());
				ne.setMemberId(memberId);
				newEdm.add(ne);
			}
			entDeptmentMemberDao.saveEntDeptmentMembers(newEdm);
		}

		entMemberDao.editOrgMember(member);
		
		result.put("code",0);
		result.put("memberId", memberId);
		
		return result;
	}

	public OrgMember getOrgMemberByUsername(String username) {
 		return entMemberDao.getOrgMemberByUsername(username);
	}

	public List<OrgMember> getOrgMembersBySubscribeState(String subState) {
		return entMemberDao.getOrgMembersBySubscribeState(subState);
	}
}
