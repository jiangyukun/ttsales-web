/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename BusLotteryServiceImpl.java
 * @package cn.ttsales.work.service.bus.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.bus.impl;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.BusLottery;
import cn.ttsales.work.domain.WxAppBonus;
import cn.ttsales.work.domain.WxUserBonus;
import cn.ttsales.work.dto.LotteryRecordDTO;
import cn.ttsales.work.dto.QyClaimLotteryDTO;
import cn.ttsales.work.persistence.bus.BusLotteryDao;
import cn.ttsales.work.persistence.rep.RepReadCountDao;
import cn.ttsales.work.persistence.sys.WxAppBonusDao;
import cn.ttsales.work.persistence.sys.WxUserBonusDao;
import cn.ttsales.work.service.bus.BusLotteryService;


/**
 * BusLottery Service Impl
 * @author dandyzheng
 *
 */
@Service("busLotteryService")
public class BusLotteryServiceImpl implements BusLotteryService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private BusLotteryDao busLotteryDao;
	@Autowired
	private WxAppBonusDao wxAppBonusDao;
	@Autowired
	private WxUserBonusDao wxUserBonusDao;
	@Autowired
	private RepReadCountDao repReadCountDao;

	@Transactional
	public BusLottery saveBusLottery(BusLottery busLottery) {
		return busLotteryDao.saveBusLottery(busLottery);
	}

	@Transactional
	public List<BusLottery> saveBusLotterys(List<BusLottery> busLotterys) {
		return null;
	}

	@Transactional
	public BusLottery editBusLottery(BusLottery busLottery) {
		return busLotteryDao.editBusLottery(busLottery);
	}

	@Transactional
	public List<BusLottery> editBusLotterys(List<BusLottery> busLotterys) {
		return busLotteryDao.editBusLotterys(busLotterys);
	}

	@Transactional
	public void removeBusLottery(BusLottery BusLottery) {
		busLotteryDao.removeBusLottery(BusLottery);
	}

	@Transactional
	public void removeBusLotterys(List<BusLottery> BusLotterys) {
		busLotteryDao.removeBusLotterys(BusLotterys);		
	}

	@Transactional
	public void removeBusLottery(String busLotteryId) {
		busLotteryDao.removeBusLottery(busLotteryId);		
	}

	public BusLottery getBusLottery(String busLotteryId) {
		return busLotteryDao.getBusLottery(busLotteryId);
	}

	public List<BusLottery> getAllBusLotterys() {
		return busLotteryDao.getAllBusLotterys();
	}

	public int queryValidReadCount(String deptId, String schemeId, String userId, String openId) {
		
		int readLotCount = 0;
		int validReadCount = 0;
		if (userId != null && openId != null) {
			readLotCount = busLotteryDao.queryLotteryCountByReadGet(deptId, userId, openId);
			validReadCount = repReadCountDao.querySomeOneValidReadCount(schemeId, userId, openId);
		} else if (userId != null) {
			readLotCount = busLotteryDao.queryLotteryCountByReadGet(deptId, userId);
			validReadCount = repReadCountDao.querySomeOneValidReadCount(schemeId, userId);
		} else if (openId != null) {
			readLotCount = busLotteryDao.queryLotteryCountByReadGet(deptId, openId);
			validReadCount = repReadCountDao.querySomeOneValidReadCount(schemeId, openId);
		}
		
		int rc = validReadCount - (readLotCount * 2);
		
		return rc < 0 ? 0 : rc;
	}

	public List<BusLottery> queryLotterysByRank(String deptId, int sum) {
		return busLotteryDao.queryLotterysByRank(deptId, sum);
	}
	
	@Transactional
	public int updateLotteryUserId(String userId,String deptId) {
		return busLotteryDao.updateLotteryUserId(userId, deptId);
	}

	public int queryCanLotteryCount(String deptId, String userId, String openId) {
		
		if (userId != null && openId != null) {
			return busLotteryDao.queryCanLotteryCount(deptId, userId, openId);
		} else if (userId != null) {
			return busLotteryDao.queryCanLotteryCount(deptId, userId);
		} else if (openId != null) {
			return busLotteryDao.queryCanLotteryCount(deptId, openId);
		} else {
			return -1;
		}
	}

	@Transactional
	public JSONObject createWxUserBonusByLottery(String appId, QyClaimLotteryDTO lotDto) {
		JSONObject result = new JSONObject();
		try{
			BusLottery lottery = busLotteryDao.getUserLotteryByRank(lotDto.getUserId(), lotDto.getOpenId(), lotDto.getDeptId());
			if (lottery == null) {
				result.put("bonus", "-1");
				result.put("bonusId", "no-bonus");
				result.put("state", "success");
				return result;
			} else {
				//更新抽奖记录
				lottery.setLotteryTime(DateUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss"));
				lottery.setHasLottery(1);
				busLotteryDao.editBusLottery(lottery);
				
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
					ub.setOwnerId(lottery.getUserId());
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
				result.put("state", "success");
			}
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", "application-error");
		}
		return result;
	}

	public List<LotteryRecordDTO> queryLotterRecordByUserIdAndDeptId( 
			String deptId, String userId, String openId) {
		
		return busLotteryDao.queryLotterRecordByUserIdAndDeptId(deptId, userId, openId);
	}

	public int queryLotteryCountByReadGet(String deptId, String...userIds) {
		return busLotteryDao.queryLotteryCountByReadGet(deptId, userIds);
	}

	public boolean isHaveTransmitLottery(String userId, String schemeId) {
		return busLotteryDao.isHaveTransmitLottery(userId, schemeId);
	}
}
