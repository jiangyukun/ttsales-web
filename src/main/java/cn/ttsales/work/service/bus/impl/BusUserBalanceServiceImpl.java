/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename BusUserBalanceServiceImpl.java
 * @package cn.ttsales.work.service.bus.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.bus.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.domain.BusUserAdvance;
import cn.ttsales.work.domain.BusUserBalance;
import cn.ttsales.work.persistence.bus.BusUserBalanceDao;
import cn.ttsales.work.service.bus.BusUserAdvanceService;
import cn.ttsales.work.service.bus.BusUserBalanceService;
import cn.ttsales.work.wxapi.pay.WXHBCashPayAPI;


/**
 * BusUserBalance Service Impl
 * @author dandyzheng
 *
 */
@Service("busUserBalanceService")
public class BusUserBalanceServiceImpl implements BusUserBalanceService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private BusUserBalanceDao busUserBalanceDao;
	
	@Autowired
	WXHBCashPayAPI cashPayApi;
	
	@Autowired
	private BusUserAdvanceService busUserAdvanceService;

	@Transactional
	public BusUserBalance saveBusUserBalance(BusUserBalance busUserBalance) {
		return busUserBalanceDao.saveBusUserBalance(busUserBalance);
	}

	@Transactional
	public List<BusUserBalance> saveBusUserBalances(List<BusUserBalance> busUserBalances) {
		return null;
	}

	@Transactional
	public BusUserBalance editBusUserBalance(BusUserBalance busUserBalance) {
		return busUserBalanceDao.editBusUserBalance(busUserBalance);
	}

	@Transactional
	public List<BusUserBalance> editBusUserBalances(List<BusUserBalance> busUserBalances) {
		return busUserBalanceDao.editBusUserBalances(busUserBalances);
	}

	@Transactional
	public void removeBusUserBalance(BusUserBalance BusUserBalance) {
		busUserBalanceDao.removeBusUserBalance(BusUserBalance);
	}

	@Transactional
	public void removeBusUserBalances(List<BusUserBalance> BusUserBalances) {
		busUserBalanceDao.removeBusUserBalances(BusUserBalances);		
	}

	@Transactional
	public void removeBusUserBalance(String busUserBalanceId) {
		busUserBalanceDao.removeBusUserBalance(busUserBalanceId);		
	}

	public BusUserBalance getBusUserBalance(String busUserBalanceId) {
		return busUserBalanceDao.getBusUserBalance(busUserBalanceId);
	}

	public List<BusUserBalance> getAllBusUserBalances() {
		return busUserBalanceDao.getAllBusUserBalances();
	}

	public List<BusUserBalance> queryBusUserBalance(String userId,
			String userType) {
		return busUserBalanceDao.queryBusUserBalance(userId, userType);
	}

	@Transactional
	public int receiveBusUserBalance(BusUserBalance busUserBalance, float amount) {
		return busUserBalanceDao.receiveBusUserBalance(busUserBalance, amount);
	}

	@Transactional
	public int rollbackBusUserBalance(BusUserBalance busUserBalance,
			float amount) {
		return busUserBalanceDao.rollbackBusUserBalance(busUserBalance, amount);
	}

	@Transactional
	public Map<String,String> handleReceiveBusUserBalance(String openId,BusUserBalance busUserBalance,
			float amount) {
		Logger log = Logger.getLogger(SASConstants.LOG_PAY);
		Map<String,String> map = new HashMap<String,String>();
		/*int rows = this.receiveBusUserBalance(busUserBalance, amount);
		if(rows == 0){
			map.put("return_code", "error");
			return map;
		}*/
		Integer totalAmount = (int) (amount*100);
		//totalAmount  = 100;
		//TODO
		map = cashPayApi.wxhbCashPayPost(openId, totalAmount, "01");
		log.info(this.getPayMessage(openId, busUserBalance, map, totalAmount));
		//红包发送成功
//		if("SUCCESS".equals(map.get("return_code"))&&"SUCCESS".equals(map.get("result_code"))){
//			BusUserAdvance busUserAdvance = new BusUserAdvance();
//			busUserAdvance.setUserId(busUserBalance.getUserId());
////			Float money = Float.valueOf(Integer.valueOf(reObject.get("total_amount"))/100);
//			busUserAdvance.setMoney(amount);
//			busUserAdvance.setSpBillno(String.valueOf(map.get("mch_billno")));
//			busUserAdvance.setUserType(busUserBalance.getUserType());
//			busUserAdvance.setHasReceive(1);
//			busUserAdvance.setCreateTime(DateUtil.getCurrentDateTimeStr());
//			busUserAdvanceService.saveBusUserAdvance(busUserAdvance);
//		}else if("FAIL".equals(map.get("return_code"))){
//			this.rollbackBusUserBalance(busUserBalance, amount);
//		}
		
		if("SUCCESS".equals(map.get("return_code"))&&"SUCCESS".equals(map.get("result_code"))){
			BusUserAdvance busUserAdvance = new BusUserAdvance();
			busUserAdvance.setUserId(busUserBalance.getUserId());
//			Float money = Float.valueOf(Integer.valueOf(reObject.get("total_amount"))/100);
			busUserAdvance.setMoney(amount);
			busUserAdvance.setSpBillno(String.valueOf(map.get("mch_billno")));
			busUserAdvance.setUserType(busUserBalance.getUserType());
			busUserAdvance.setHasReceive(1);
			busUserAdvance.setCreateTime(DateUtil.getCurrentDateTimeStr());
			busUserAdvanceService.saveBusUserAdvance(busUserAdvance);
		}else if("NOAUTH".equals(map.get("err_code"))||
				"PARAM_ERROR".equals(map.get("err_code"))||
				"OPENID_ERROR".equals(map.get("err_code"))||
				"NOTENOUGH".equals(map.get("err_code"))||
				"TIME_LIMITED".equals(map.get("err_code"))||
				"DAY_OVER_LIMITED".equals(map.get("err_code"))||
				"SECOND_OVER_LIMITED".equals(map.get("err_code"))){
			this.rollbackBusUserBalance(busUserBalance, amount);
		}else{
			map = cashPayApi.reWxhbCashPayPost(openId, totalAmount, "01",map.get("mch_billno"));
			if("SUCCESS".equals(map.get("return_code"))&&"SUCCESS".equals(map.get("result_code"))){
				BusUserAdvance busUserAdvance = new BusUserAdvance();
				busUserAdvance.setUserId(busUserBalance.getUserId());
//				Float money = Float.valueOf(Integer.valueOf(reObject.get("total_amount"))/100);
				busUserAdvance.setMoney(amount);
				busUserAdvance.setSpBillno(String.valueOf(map.get("mch_billno")));
				busUserAdvance.setUserType(busUserBalance.getUserType());
				busUserAdvance.setHasReceive(1);
				busUserAdvance.setCreateTime(DateUtil.getCurrentDateTimeStr());
				busUserAdvanceService.saveBusUserAdvance(busUserAdvance);
			}else if("NOAUTH".equals(map.get("err_code"))||
					"PARAM_ERROR".equals(map.get("err_code"))||
					"OPENID_ERROR".equals(map.get("err_code"))||
					"NOTENOUGH".equals(map.get("err_code"))||
					"TIME_LIMITED".equals(map.get("err_code"))||
					"DAY_OVER_LIMITED".equals(map.get("err_code"))||
					"SECOND_OVER_LIMITED".equals(map.get("err_code"))){
				this.rollbackBusUserBalance(busUserBalance, amount);
			}
		}
		return map;
	}
	
	private String getPayMessage(String openId,BusUserBalance busUserBalance,Map<String,String> map,Integer totalAmount){
			
			StringBuffer sb = new StringBuffer();
			sb.append(openId);
			sb.append(SASConstants.LOG_SPLIT);
			sb.append(totalAmount.intValue());
			sb.append(SASConstants.LOG_SPLIT);
			sb.append(DateUtil.getCurrentDateTimeStr());
			sb.append(SASConstants.LOG_SPLIT);
			sb.append(map.get("return_code"));
			sb.append(SASConstants.LOG_SPLIT);
			sb.append(map.get("err_code"));
			sb.append(SASConstants.LOG_SPLIT);
			sb.append(map.get("err_code_des"));
			return sb.toString();
			
		}
	
	
}
