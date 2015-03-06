/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename SysLotteryServiceImpl.java
 * @package cn.ttsales.work.service.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys.impl;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.SysLottery;
import cn.ttsales.work.domain.WxAppBonus;
import cn.ttsales.work.domain.WxUserBonus;
import cn.ttsales.work.dto.LotteryRecordDTO;
import cn.ttsales.work.persistence.sys.SysLotteryDao;
import cn.ttsales.work.persistence.sys.WxAppBonusDao;
import cn.ttsales.work.persistence.sys.WxUserBonusDao;
import cn.ttsales.work.service.sys.SysLotteryService;


/**
 * SysLottery Service Impl
 * @author dandyzheng
 *
 */
@Service("sysLotteryService")
public class SysLotteryServiceImpl implements SysLotteryService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private SysLotteryDao sysLotteryDao;
	@Autowired
	private WxAppBonusDao wxAppBonusDao;
	@Autowired
	private WxUserBonusDao wxUserBonusDao;

	@Transactional
	public SysLottery saveSysLottery(SysLottery sysLottery) {
		return sysLotteryDao.saveSysLottery(sysLottery);
	}

	@Transactional
	public List<SysLottery> saveSysLotterys(List<SysLottery> sysLotterys) {
		return null;
	}

	@Transactional
	public SysLottery editSysLottery(SysLottery sysLottery) {
		return sysLotteryDao.editSysLottery(sysLottery);
	}

	@Transactional
	public List<SysLottery> editSysLotterys(List<SysLottery> sysLotterys) {
		return sysLotteryDao.editSysLotterys(sysLotterys);
	}

	@Transactional
	public void removeSysLottery(SysLottery SysLottery) {
		sysLotteryDao.removeSysLottery(SysLottery);
	}

	@Transactional
	public void removeSysLotterys(List<SysLottery> SysLotterys) {
		sysLotteryDao.removeSysLotterys(SysLotterys);		
	}

	@Transactional
	public void removeSysLottery(String sysLotteryId) {
		sysLotteryDao.removeSysLottery(sysLotteryId);		
	}

	public SysLottery getSysLottery(String sysLotteryId) {
		return sysLotteryDao.getSysLottery(sysLotteryId);
	}

	public List<SysLottery> getAllSysLotterys() {
		return sysLotteryDao.getAllSysLotterys();
	}

	@Transactional
	public int updateLotteryUserId(String userId,String deptId) {
		return sysLotteryDao.updateLotteryUserId(userId, deptId);
	}
	
	public SysLottery getUserLotteryByRank(String userId, String deptId) {
		return sysLotteryDao.getUserLotteryByRank(userId, deptId);
	}

	public int queryCanLotteryCount(String userId, String deptId) {
		return sysLotteryDao.queryCanLotteryCount(userId, deptId);
	}

	@Transactional
	public JSONObject createWxUserBonusByLottery(String appId, String deptId, String userId) {
		JSONObject result = new JSONObject();
		try{
			SysLottery lottery = sysLotteryDao.getUserLotteryByRank(userId, deptId);
			if (lottery == null) {
				result.put("bonus", "-1");
				result.put("bonusId", "no-bonus");
				return result;
			} else {
				//更新抽奖记录
				lottery.setLotteryTime(DateUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss"));
				lottery.setHasLottery(1);
				sysLotteryDao.editSysLottery(lottery);
				
				if (StringUtil.isEmpty(lottery.getBonusId())) {
					result.put("bonus", "-1");
					result.put("bonusId", "empty-bonus");
				} else {
					WxAppBonus appBonus = wxAppBonusDao.getWxAppBonus(lottery.getBonusId());
					//新建红包记录
					WxUserBonus ub = new WxUserBonus();
					ub.setAppid(appId);
					ub.setUserBonusCode(lottery.getLotteryId());
					ub.setWxAppBonus(appBonus);
					ub.setpUserBonusId("0");
					ub.setOwnerId(userId);
					ub.setMoney(lottery.getMoney());
					ub.setMoneyAuth("00");
					ub.setBonusType("1");
					ub.setOperation("00");
					ub.setBonusStatus("00");
					ub.setIsPush(0);
					ub.setCreateTime(DateUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss"));
					ub.setOperationTime(null);
					ub.setDeadTime(null);
					ub.setVersion(0);
					ub.setReceiveVerify("00");
					ub.setVerifyUrl(null);
					ub.setShareMoney(0);
					ub.setShareMoneyAuth("00");;
					ub.setShareShowUrl(null);
					ub.setSharePullOpp("00");
					ub.setShareNumber(0);
					
					ub = wxUserBonusDao.saveWxUserBonus(ub);
					
					result.put("bonus", lottery.getMoney());
					result.put("bonusId", ub.getUserBonusId());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			result.put("bonus",  "appliction-error");
			result.put("bonusId", "appliction-error");
		}
		return result;
	}

	public List<LotteryRecordDTO> queryLotterRecordByUserIdAndDeptId(
			String userId, String deptId) {
		return sysLotteryDao.queryLotterRecordByUserIdAndDeptId(userId, deptId);
	}

	public SysLottery getLotteryByRank(String deptId) {
		return sysLotteryDao.getLotteryByRank(deptId);
	}
}
