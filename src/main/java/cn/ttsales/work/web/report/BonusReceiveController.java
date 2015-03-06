package cn.ttsales.work.web.report;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.BusUserBalance;
import cn.ttsales.work.domain.RepOpenUser;
import cn.ttsales.work.service.bus.BusUserAdvanceService;
import cn.ttsales.work.service.bus.BusUserBalanceService;
import cn.ttsales.work.service.ent.EntMemberService;
import cn.ttsales.work.service.rep.RepOpenUserService;
import cn.ttsales.work.web.common.MpOAuthController;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;
import cn.ttsales.work.wxapi.pay.WXHBCashPayAPI;

@Controller
@RequestMapping(value="report/bonusReceive/")
public class BonusReceiveController extends MpOAuthController{
	private Logger log = Logger.getLogger(BonusReceiveController.class);
	
	
	@Autowired
	private BusUserAdvanceService busUserAdvanceService;
	@Autowired
	private EntMemberService entMemberService;
	@Autowired
	private RepOpenUserService repOpenUserService;
	
	@Autowired
	private BusUserBalanceService busUserBalanceService;
	
	@Override
	@RequestMapping(value="pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response, Map<String, String> map) {
		ModelAndView m = new ModelAndView();
		m.addObject("openId", map.get("openId"));
		//m.addObject("userId", map.get("userId"));
		m.addObject("userType", map.get("userType"));
		m.addObject("balance", map.get("balance"));
		m.setViewName("redirect:/pages/report/bonusReceive.html?v="+SASConstants.SAS_VERSION);
		return m;
	}
	
	@Autowired
	WXHBCashPayAPI cashPayApi;
	
	@RequestMapping(value="receiveBonus.do")
	protected ModelAndView receiveBonus(HttpServletRequest request, HttpServletResponse response,String openId,String userType,Float amount) throws IOException {
		//openId="oZPJ4s1BydhdPiJbBUesBsZZ0V5A123";
		
		if(!SASConstants.ISPAY){
			ResponseUtil.toClient(response, "error_not_allow_pay", "平台已禁止领取");
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		if(hours<8){
			ResponseUtil.toClient(response, "TIME_LIMITED", "");
			return null;
		}
			
		
		String userId = CookiesUtil.getUserId(request);
		
		log.info("userId:" + userId);
		if(StringUtil.isEmpty(userId)){
			ResponseUtil.toClient(response, "error", "该用户不合法");
			return null;
		}
		
		/*AccountView accountView = accountViewService.getAccountViewByUserId(userId, userType);*/
		List<BusUserBalance> busUserBalances = busUserBalanceService.queryBusUserBalance(userId, userType);
		
		if(busUserBalances==null || busUserBalances.size() ==0){
			ResponseUtil.toClient(response, "error_NOTENOUGH", "");
			return null;
		}else if(amount>Float.valueOf(busUserBalances.get(0).getBalance())){
			ResponseUtil.toClient(response, "error_NOTENOUGH", "");
			return null;
		}
		//Integer totalAmount = (int) (amount*100);
		
		int rows = busUserBalanceService.receiveBusUserBalance(busUserBalances.get(0), amount);
		if(rows == 0){
			ResponseUtil.toClient(response,"error", "");
			return null;
		}
		Map<String,String> result = busUserBalanceService.handleReceiveBusUserBalance(openId,busUserBalances.get(0), amount.floatValue());
		log.info(getpPayResultMsg(result,userId,userType));
		if("SUCCESS".equals(result.get("return_code"))&&"SUCCESS".equals(result.get("result_code"))){
			ResponseUtil.toClient(response, "success", "");
		}else if("FAIL".equals(result.get("return_code"))){
			ResponseUtil.toClient(response,result.get("err_code"), "");
		}else if("error".equals(result.get("return_code"))){
			ResponseUtil.toClient(response,"error", "");
		}
		return null;
		//ResponseUtil.toClient(response,result.getr);
		/*if(result == 0){
			ResponseUtil.toClient(response,"error");
			return null;
		}*/
		
		//ResponseUtil.toClient(response, "success", "");
		
		
		/*totalAmount  = 100;
		Map<String,String> reObject = cashPayApi.wxhbCashPayPost(openId, totalAmount, "01");
		//红包发送成功
		if("SUCCESS".equals(reObject.get("return_code"))&&"SUCCESS".equals(reObject.get("result_code"))){
			BusUserAdvance busUserAdvance = new BusUserAdvance();
			busUserAdvance.setUserId(userId);
//			Float money = Float.valueOf(Integer.valueOf(reObject.get("total_amount"))/100);
			busUserAdvance.setMoney(amount);
			busUserAdvance.setSpBillno(String.valueOf(reObject.get("mch_billno")));
			busUserAdvance.setUserType(userType);
			busUserAdvance.setHasReceive(1);
			busUserAdvance.setCreateTime(DateUtil.getCurrentDateTimeStr());
			busUserAdvanceService.saveBusUserAdvance(busUserAdvance);
			ResponseUtil.toClient(response, "success", "");
		}else if("FAIL".equals(reObject.get("return_code"))){
			ResponseUtil.toClient(response,reObject.get("err_code"), "");
		}
		return null;*/
	}
//	@RequestMapping(value="testReceiveBonus.do")
//	protected ModelAndView testreceiveBonus(HttpServletRequest request, HttpServletResponse response,String openId,String userId,String userType,Float amount) throws IOException {
//		//openId="oZPJ4sw33wwLhAA5gN1Lgi4DWIZU123";
//		
//		/*AccountView accountView = accountViewService.getAccountViewByUserId(userId, userType);*/
//		//TODO 测试
////		openId = "oZPJ4s65WIk69z2PJgqYh0oeQdVI";
//		Integer totalAmount  = 100;
//		Map<String,String> reObject = cashPayApi.wxhbCashPayPost(openId, totalAmount, "01");
//		log.info(getpPayResultMsg(reObject,userId,userType));
//		return null;
//	}
	
	@RequestMapping(value="initPage.do")
	protected ModelAndView initPage(HttpServletRequest request, HttpServletResponse response,String userType) throws IOException {
		String userId = CookiesUtil.getUserId(request);
		//企业号用户
		if("02".equals(userType)){
			OrgMember orgMember = entMemberService.getOrgMember(userId);
			if(orgMember!=null){
				ResponseUtil.toClient(response, "",String.valueOf(orgMember.getHasAgreement()));
			}
		}else{
			RepOpenUser repOpenUser = repOpenUserService.getRepOpenUser(userId);
			if(repOpenUser!=null){
				ResponseUtil.toClient(response, "",String.valueOf(repOpenUser.getHasAgreement()));
			}
		}
		return null;
	}
	private String getpPayResultMsg(Map<String,String> result,String  userId,String  userType){
		String SPLIT = ":";
		String tab = ";";
		StringBuffer sb = new StringBuffer();
		sb.append("return_code(返回状态码)");//返回状态码 
		sb.append(SPLIT);
		sb.append(result.get("return_code"));
		sb.append(tab);
		sb.append("return_msg(返回信息)");//返回信息 	
		sb.append(SPLIT);
		sb.append(result.get("return_msg"));//返回信息 	
		sb.append(tab);
		sb.append("result_code(业务结果)");//业务结果
		sb.append(SPLIT);
		sb.append(result.get("result_code"));
		sb.append(tab);
		sb.append("err_code(错误代码)");//错误代码 
		sb.append(SPLIT);
		sb.append(result.get("err_code"));
		sb.append(tab);
		sb.append("err_code_des(错误代码描述 )");//错误代码描述 
		sb.append(SPLIT);
		sb.append(result.get("err_code_des"));
		sb.append(tab);
		sb.append("total_amount(红包金额)");//红包金额述
		sb.append(SPLIT);
		sb.append(result.get("total_amount"));
		sb.append(tab);
		sb.append("mch_billno(商户订单号)");//商户订单号
		sb.append(SPLIT);
		sb.append(result.get("mch_billno"));
		sb.append(tab);
		sb.append("mch_id(红包金额)");//商户号
		sb.append(SPLIT);
		sb.append(result.get("mch_id"));
		sb.append(tab);
		sb.append("wxappid(微信AppId)");//wxappid
		sb.append(SPLIT);
		sb.append(result.get("wxappid"));
		sb.append(tab);
		sb.append("re_openid(openId)");//re_openid
		sb.append(SPLIT);
		sb.append(result.get("re_openid"));
		sb.append(tab);
		sb.append("user_id");//user_id
		sb.append(SPLIT);
		sb.append(userId);
		sb.append(tab);
		sb.append("userType");//userType
		sb.append(SPLIT);
		sb.append(userType);
		sb.append(tab);
		return sb.toString();
	}
	
	@RequestMapping(value="changeAgreement.do")
	protected ModelAndView changeAgreement(HttpServletRequest request, HttpServletResponse response,String userType,int hasAgreement) throws IOException {
		String userId = CookiesUtil.getUserId(request);
		//企业号用户
		if("02".equals(userType)){
			OrgMember orgMember = entMemberService.getOrgMember(userId);
			orgMember.setHasAgreement(hasAgreement);
			entMemberService.editOrgMember(orgMember);
		}else{
			RepOpenUser repOpenUser = repOpenUserService.getRepOpenUser(userId);
			repOpenUser.setHasAgreement(hasAgreement);
			repOpenUserService.editRepOpenUser(repOpenUser);
		}
		return null;
	}
	
	@RequestMapping(value="setPay.do")
	public ModelAndView setPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SASConstants.ISPAY = SASConstants.ISPAY ? false:true;
		ResponseUtil.toClient(response, "领取设置：" + SASConstants.ISPAY);
		return null;
	}

}
