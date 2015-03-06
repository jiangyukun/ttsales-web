/**
	 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename BusPopularizeServiceImpl.java
 * @package cn.ttsales.work.service.bus.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.bus.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.domain.BusPopularize;
import cn.ttsales.work.domain.ProProductScheme;
import cn.ttsales.work.domain.RepUserCross;
import cn.ttsales.work.persistence.bus.BusPopularizeDao;
import cn.ttsales.work.persistence.ent.EntMemberDao;
import cn.ttsales.work.persistence.pro.ProProductDao;
import cn.ttsales.work.persistence.rep.RepUserCrossDao;
import cn.ttsales.work.service.bus.BusPopularizeService;


/**
 * BusPopularize Service Impl
 * @author dandyzheng
 *
 */
@Service("busPopularizeService")
public class BusPopularizeServiceImpl implements BusPopularizeService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private BusPopularizeDao busPopularizeDao;
	
	@Autowired
	private EntMemberDao entMemberDao;
	
	@Autowired
	private RepUserCrossDao repUserCrossDao;
	@Autowired
	private ProProductDao proProductDao;
	

	@Transactional
	public BusPopularize saveBusPopularize(BusPopularize busPopularize) {
		return busPopularizeDao.saveBusPopularize(busPopularize);
	}

	@Transactional
	public List<BusPopularize> saveBusPopularizes(List<BusPopularize> busPopularizes) {
		return busPopularizeDao.saveBusPopularizes(busPopularizes);
	}

	@Transactional
	public BusPopularize editBusPopularize(BusPopularize busPopularize) {
		return busPopularizeDao.editBusPopularize(busPopularize);
	}

	@Transactional
	public List<BusPopularize> editBusPopularizes(List<BusPopularize> busPopularizes) {
		return busPopularizeDao.editBusPopularizes(busPopularizes);
	}

	@Transactional
	public void removeBusPopularize(BusPopularize BusPopularize) {
		busPopularizeDao.removeBusPopularize(BusPopularize);
	}

	@Transactional
	public void removeBusPopularizes(List<BusPopularize> BusPopularizes) {
		busPopularizeDao.removeBusPopularizes(BusPopularizes);		
	}

	@Transactional
	public void removeBusPopularize(String busPopularizeId) {
		busPopularizeDao.removeBusPopularize(busPopularizeId);		
	}

	public BusPopularize getBusPopularize(String busPopularizeId) {
		return busPopularizeDao.getBusPopularize(busPopularizeId);
	}

	public List<BusPopularize> getAllBusPopularizes() {
		return busPopularizeDao.getAllBusPopularizes();
	}

	public List<BusPopularize> queryBusPopularizesByMemberId(String memberId) {
		return busPopularizeDao.queryBusPopularizesByMemberId(memberId);
	}	

	public List<BusPopularize> queryMemberBusPopularizedByState(String memberId, String... states) {
		return busPopularizeDao.queryMemberBusPopularizedByState(memberId, states);
	}
	
	@Transactional
	public boolean saveScheme(String userId,String schemeId){
		BusPopularize busPopularize = new BusPopularize();
		List<BusPopularize> list  = busPopularizeDao.queryBusPopularizesByMemberId(userId);
		if(list ==null || list.size() == 0){
			BeanUtils.copyProperties(busPopularizeDao.getBusPopularize("1"),busPopularize);
			busPopularize.setPopularizeId(null);
			busPopularize.setMemberId(userId);
 			busPopularize.setProProductScheme(new ProProductScheme(schemeId));
 			busPopularizeDao.editBusPopularize(busPopularize);
		}
		
		List<RepUserCross> repUserCrossList =  repUserCrossDao.queryRepUserCross(userId,SASConstants.USER_CROSS_TYPE_ENT);
		if(repUserCrossList ==null || repUserCrossList.size() == 0){
			RepUserCross repUserCross = new RepUserCross();
			repUserCross.setCreateTime(DateUtil.getCurrentDateTimeStr());
			repUserCross.setType("02");
			repUserCross.setUserId(userId);
			repUserCrossDao.saveRepUserCross(repUserCross);
		}
		
		OrgMember entMember = new OrgMember();
		//BeanUtils.copyProperties(entMemberDao.getEntMember("zhengdeyi"),entMember);
		entMember.setMemberId(userId);
		entMember.setName(userId);
		entMember.setParentMemberId("0");
		entMember.setName(userId);
		//entMember.setDeptId("1");
		
		entMemberDao.editOrgMember(entMember);
		return true;
	}
	
	@Transactional
	public void changeStage(String popularizeId, String state) {
		 busPopularizeDao.changeStage(popularizeId,state);
		
	}
	
	@Transactional
	public void saveWishRecord(String userCrossId, String transmitId, String popularizeId) {
		busPopularizeDao.saveWishRecord(userCrossId, transmitId, popularizeId);
	}
}
