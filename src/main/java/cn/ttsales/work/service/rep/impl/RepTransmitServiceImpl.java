/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename RepTransmitServiceImpl.java
 * @package cn.ttsales.work.service.rep.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.rep.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.exception.BusinessException;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.BusPopularize;
import cn.ttsales.work.domain.RepTransmit;
import cn.ttsales.work.domain.RepUserCross;
import cn.ttsales.work.dto.RepPopularizeTransmitReportDTO;
import cn.ttsales.work.dto.RepTransmitDTO;
import cn.ttsales.work.dto.RepTransmitReportDTO;
import cn.ttsales.work.persistence.bus.BusPopularizeDao;
import cn.ttsales.work.persistence.ent.EntMemberDao;
import cn.ttsales.work.persistence.rep.RepTransmitDao;
import cn.ttsales.work.persistence.rep.RepUserCrossDao;
import cn.ttsales.work.service.rep.RepTransmitService;


/**
 * RepTransmit Service Impl
 * @author dandyzheng
 *
 */
@Service("repTransmitService")
public class RepTransmitServiceImpl implements RepTransmitService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private RepTransmitDao repTransmitDao;
	
	@Autowired
	private RepUserCrossDao repUserCrossDao;
	@Autowired
	private BusPopularizeDao busPopularizeDao;
	
	@Autowired
	private EntMemberDao entMemberDao;

	@Transactional
	public RepTransmit saveRepTransmit(RepTransmit repTransmit) {
		return repTransmitDao.saveRepTransmit(repTransmit);
	}

	@Transactional
	public List<RepTransmit> saveRepTransmits(List<RepTransmit> repTransmits) {
		return null;
	}

	@Transactional
	public RepTransmit editRepTransmit(RepTransmit repTransmit) {
		return repTransmitDao.editRepTransmit(repTransmit);
	}

	@Transactional
	public List<RepTransmit> editRepTransmits(List<RepTransmit> repTransmits) {
		return repTransmitDao.editRepTransmits(repTransmits);
	}

	@Transactional
	public void removeRepTransmit(RepTransmit RepTransmit) {
		repTransmitDao.removeRepTransmit(RepTransmit);
	}

	@Transactional
	public void removeRepTransmits(List<RepTransmit> RepTransmits) {
		repTransmitDao.removeRepTransmits(RepTransmits);		
	}

	@Transactional
	public void removeRepTransmit(String repTransmitId) {
		repTransmitDao.removeRepTransmit(repTransmitId);		
	}

	public RepTransmit getRepTransmit(String repTransmitId) {
		return repTransmitDao.getRepTransmit(repTransmitId);
	}

	public List<RepTransmit> getAllRepTransmits() {
		return repTransmitDao.getAllRepTransmits();
	}
	
	public List<RepTransmitDTO> queryAllChildTransmits(String userId,String popularizeId){
		List<RepUserCross> repUserCrosss = repUserCrossDao.queryRepUserCross(userId, SASConstants.USER_CROSS_TYPE_ENT);
		
		if(repUserCrosss == null || repUserCrosss.size() == 0){
			throw new BusinessException("SAS-001001");
		}
		List<RepTransmitDTO> repTransmitDTOs = new ArrayList<RepTransmitDTO>();
		List<RepTransmit> list = repTransmitDao.queryRepTransmit(repUserCrosss.get(0).getUserCrossId(), popularizeId);
		Iterator<RepTransmit> it = list.iterator();
		while(it.hasNext()){
			RepTransmit repTransmit = it.next();
			repTransmitDTOs.addAll(repTransmitDao.queryAllChildTransmits(repTransmit.getTransmitId()));
		}
		return repTransmitDTOs;
	}
	
	
	public List<RepTransmitDTO> createEdgeNodes(String userId,String popularizeId,String depth){
		depth  = StringUtil.isEmpty(depth) ? SASConstants.TREE_DEFALUT_DEPTH : depth;
		List<RepTransmitDTO> resultRepTransmitDTOs = new ArrayList<RepTransmitDTO>();
		List<RepTransmitDTO> repTransmitDTOs = this.queryAllChildTransmits(userId, popularizeId);
		RepTransmitDTO rootRepTransmitDTO = this.getRootRepTransmitDTO();
		OrgMember org = entMemberDao.getOrgMember(userId);
		rootRepTransmitDTO.setNickName(org.getName());
		resultRepTransmitDTOs.add(0,this.getEdgeNodes(rootRepTransmitDTO, repTransmitDTOs,resultRepTransmitDTOs));
		
		return resultRepTransmitDTOs;
	}
	
	public List<RepTransmitDTO> createChildEdgeNodes(String repTransmitId){
		List<RepTransmitDTO> resultRepTransmitDTOs = new ArrayList<RepTransmitDTO>();
		List<RepTransmitDTO> repTransmitDTOs = repTransmitDao.queryAllChildTransmits(repTransmitId);
		RepTransmitDTO rootRepTransmitDTO = this.getRootRepTransmitDTO();
		rootRepTransmitDTO.setTransmitId(repTransmitId);
		this.getEdgeNodes(rootRepTransmitDTO, repTransmitDTOs,resultRepTransmitDTOs);
		return resultRepTransmitDTOs;
	}
	
	private RepTransmitDTO getEdgeNodes(RepTransmitDTO parentRepTransmitDTO,List<RepTransmitDTO> repTransmitDTOs,List<RepTransmitDTO> resultRepTransmitDTOs){
		List<RepTransmitDTO> childRepTransmitDTOs = this.getChildRepTransmitDTOs(parentRepTransmitDTO, repTransmitDTOs);
		RepTransmitDTO tempRepTransmitDTO = null;
		for(int i=0;i<childRepTransmitDTOs.size();i++){
			RepTransmitDTO repTransmitDTO = childRepTransmitDTOs.get(i);
			if(repTransmitDTO.isHasChildren()){
				tempRepTransmitDTO = getEdgeNodes(repTransmitDTO,repTransmitDTOs,resultRepTransmitDTOs);
				if(repTransmitDTO.getDepth() == 1){
					parentRepTransmitDTO.setAllTraCount(
						String.valueOf(
							Integer.valueOf(StringUtil.isEmpty(parentRepTransmitDTO.getAllTraCount()) ? "0":parentRepTransmitDTO.getAllTraCount()).intValue() +
							Integer.valueOf(tempRepTransmitDTO.getAllTraCount()).intValue()
						)
					);
					parentRepTransmitDTO.setAllReadCount(
							String.valueOf(
									Integer.valueOf(StringUtil.isEmpty(parentRepTransmitDTO.getAllReadCount()) ? "0":parentRepTransmitDTO.getAllReadCount()).intValue()
									+
									Integer.valueOf((StringUtil.isEmpty(tempRepTransmitDTO.getAllReadCount()) ? "0":tempRepTransmitDTO.getAllReadCount())).intValue()
									//- 
									//Integer.valueOf(repTransmitDTO.getCurrReadCount()).intValue()
									
							)
					);
					
				}else{
					parentRepTransmitDTO.setAllTraCount(
							String.valueOf(
									Integer.valueOf(StringUtil.isEmpty(parentRepTransmitDTO.getAllTraCount()) ? "0":parentRepTransmitDTO.getAllTraCount()).intValue() +
									//Integer.valueOf((StringUtil.isEmpty(tempRepTransmitDTO.getAllTraCount()) ? "0":tempRepTransmitDTO.getAllTraCount())).intValue())
									Integer.valueOf(tempRepTransmitDTO.getAllTraCount()).intValue() + 1
							)
					);
					parentRepTransmitDTO.setAllReadCount(
							String.valueOf(
									Integer.valueOf(StringUtil.isEmpty(parentRepTransmitDTO.getAllReadCount()) ? "0":parentRepTransmitDTO.getAllReadCount()).intValue() + 
									Integer.valueOf((StringUtil.isEmpty(tempRepTransmitDTO.getAllReadCount()) ? "0":tempRepTransmitDTO.getAllReadCount())).intValue()
							)
					);
				}
				
				
				
				
				resultRepTransmitDTOs.add(0,tempRepTransmitDTO);
			}else{
				if(repTransmitDTO.getDepth() != 1){
					/*parentRepTransmitDTO.setAllReadCount(
							String.valueOf(
									Integer.valueOf(StringUtil.isEmpty(parentRepTransmitDTO.getAllReadCount()) ? "0":parentRepTransmitDTO.getAllReadCount()).intValue() + 
									Integer.valueOf((StringUtil.isEmpty(repTransmitDTO.getCurrReadCount()) ? "0":repTransmitDTO.getCurrReadCount())).intValue()
							)
					);*/
					
				
					parentRepTransmitDTO.setAllTraCount(
							String.valueOf(
									Integer.valueOf(parentRepTransmitDTO.getAllTraCount()).intValue() +
									Integer.valueOf(repTransmitDTO.getCurrTraCount()).intValue() + 1
							)
					);
				}
				
				parentRepTransmitDTO.setAllReadCount(
						String.valueOf(
								Integer.valueOf(StringUtil.isEmpty(parentRepTransmitDTO.getAllReadCount()) ? "0":parentRepTransmitDTO.getAllReadCount()).intValue() + 
								Integer.valueOf((StringUtil.isEmpty(repTransmitDTO.getCurrReadCount()) ? "0":repTransmitDTO.getCurrReadCount())).intValue()
						)
				);
				
				resultRepTransmitDTOs.add(0,repTransmitDTO);
				//return parentRepTransmitDTO;
			}
		}
		return parentRepTransmitDTO;
	}
	
	private RepTransmitDTO getRootRepTransmitDTO(){
		RepTransmitDTO root = new RepTransmitDTO();
		root.setSno("0");
		root.setHasChildren(true);
		root.setCurrReadCount("0");
		root.setCurrTraCount("0");
		root.setAllReadCount("0");
		root.setAllTraCount("0");
		root.setTransmitId("0");
		root.setPTransmitId("root");
		root.setDepth(0);
		root.setPopularizeId("");
		root.setUserCrossId("");
		//root.setNickName(nickName)
		return root;
	}
	
	private List<RepTransmitDTO> getChildRepTransmitDTOs(RepTransmitDTO parentRepTransmitDTO,List<RepTransmitDTO> repTransmitDTOs){
		List<RepTransmitDTO> childRepTransmitDTOs = new ArrayList<RepTransmitDTO>();
		Iterator<RepTransmitDTO> it = repTransmitDTOs.iterator();
		while(it.hasNext()){
			RepTransmitDTO repTransmitDTO = it.next();
			if(repTransmitDTO.getPTransmitId().equals(parentRepTransmitDTO.getTransmitId())){
				childRepTransmitDTOs.add(repTransmitDTO);
			}
		}
		return childRepTransmitDTOs;
	}
	
	
	public List<RepPopularizeTransmitReportDTO> queryReportTransmitData(String userId){
		List<RepUserCross> repUserCrosss = repUserCrossDao.queryRepUserCross(userId, SASConstants.USER_CROSS_TYPE_ENT);
		
		if(repUserCrosss == null || repUserCrosss.size() == 0){
			throw new BusinessException("SAS-001001");
		}
		List<RepPopularizeTransmitReportDTO>  repPopularizeTransmitReportDTOs = new ArrayList<RepPopularizeTransmitReportDTO>();
		List<BusPopularize> busPopularizes = busPopularizeDao.queryBusPopularizesByMemberId(userId);
		for(int i =0; i < busPopularizes.size();i++){
			List<RepTransmitReportDTO> repTransmitReportDTOs = this.repTransmitDao.queryReportTransmitData(repUserCrosss.get(0).getUserCrossId(), busPopularizes.get(i).getPopularizeId());
			RepPopularizeTransmitReportDTO repPopularizeTransmitReportDTO = new RepPopularizeTransmitReportDTO();
			repPopularizeTransmitReportDTO.setBusPopularize(busPopularizes.get(i));
			repPopularizeTransmitReportDTO.setRepTransmitReportDTOs(repTransmitReportDTOs);
			repPopularizeTransmitReportDTOs.add(repPopularizeTransmitReportDTO);
		}
		return repPopularizeTransmitReportDTOs;
	}

	public int queryTransmitRank(String userCrossId, String schemeId) {
		return repTransmitDao.queryTransmitRank(userCrossId, schemeId);
	}
}
