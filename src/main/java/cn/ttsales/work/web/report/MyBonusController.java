package cn.ttsales.work.web.report;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.domain.BusUserAdvance;
import cn.ttsales.work.domain.BusUserBalance;
import cn.ttsales.work.domain.BusUserBonus;
import cn.ttsales.work.dto.MyBonusDetailDTO;
import cn.ttsales.work.service.bus.BusUserAdvanceService;
import cn.ttsales.work.service.bus.BusUserBalanceService;
import cn.ttsales.work.service.bus.BusUserBonusService;
import cn.ttsales.work.web.common.CorpOAuthController;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping(value="report/myBonus/")
public class MyBonusController extends CorpOAuthController{
	
//	@Autowired
//	private AccountViewService accountViewService;
	
	@Autowired
	private BusUserBonusService busUserBonusService;
	
	@Autowired
	private BusUserBalanceService busUserBalanceService;
	
	@Autowired
	private BusUserAdvanceService busUserAdvanceService;
	@Override
	@RequestMapping(value="initPage.do")
	protected ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response, Map<String, String> map) {
//		//旧红包
//		ModelAndView m = new ModelAndView();
//		m.addObject("another", map.get("another"));
//		//m.addObject("userId", map.get("userId"));
//		m.addObject("userType", "02");
//		m.setViewName("redirect:/pages/report/myBonus.html?v="+SASConstants.SAS_VERSION);
		//新红包
		ModelAndView m = new ModelAndView(); 
		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.corp.id");
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.rbssite");
 		m.setViewName("redirect:"+baseUrl+"/weixin/bonus/init.do?appid="+appId+"&userid="+map.get("userId")+"&v="+SASConstants.SAS_VERSION);
 		return m;
	}
	
	@RequestMapping(value="initData.do")
	protected void initDataBak(HttpServletRequest request, HttpServletResponse response, String userType) throws IOException {
		String userId = CookiesUtil.getUserId(request);
		List<BusUserBonus> busUserBonuses = busUserBonusService.getBusBonusesByUserId(userId);
		JSONObject money= new JSONObject();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		if(busUserBonuses==null){
			money.put("income", 0.00);
		}else{
			float income=0f;
			for (BusUserBonus busUserBonus : busUserBonuses) {
				income = income+busUserBonus.getMoney();
			}
			money.put("income", nf.format(income));
		}
		List<BusUserAdvance> busUserAdvances = busUserAdvanceService.getBusUserAdvanceByUserId(userId);
		if(busUserAdvances==null){
			money.put("outcome", 0.00);
		}else{
			float outcome=0f;
			for (BusUserAdvance busUserAdvance : busUserAdvances) {
				outcome = outcome+busUserAdvance.getMoney();
			}
			money.put("outcome", nf.format(outcome));
		}
		List<BusUserBalance> busUserBalances = busUserBalanceService.queryBusUserBalance(userId, userType);
		if(busUserBalances==null){
			money.put("balance", 0.00);
		}else{
			float balance=0f;
			for (BusUserBalance busUserBalance : busUserBalances) {
				balance = balance+busUserBalance.getBalance();
			}
			money.put("balance", nf.format(balance));
		}
		JSONObject result = new JSONObject();
		result.put("money", money);
		result.put("months",getMonths());
		ResponseUtil.toClient(response, result);
	}
//	@RequestMapping(value="initDataBak.do")
//	protected void initData(HttpServletRequest request, HttpServletResponse response, String userId, String userType) throws IOException {
//		
//		AccountView account = accountViewService.getAccountViewByUserId(userId, userType);
//		JSONObject result = new JSONObject();
//		JSONObject money;
//		NumberFormat nf = NumberFormat.getInstance();
//		nf.setMaximumFractionDigits(2);
//		nf.setMinimumFractionDigits(2);
//		if (account == null) {
//			money = new JSONObject();
//			money.put("balance", 0.00);
//			money.put("income", 0.00);
//			money.put("outcome", 0.00);
//		} else {
//			money = JsonUtil.fromObject(account, new JsonConfig());
//			money.put("income", nf.format(money.getDouble("income")));
//			if(nf.format(money.getDouble("balance")).equals("-0.00")){
//				money.put("balance","0.00" );	
//			}else{
//				money.put("balance",nf.format(money.getDouble("balance")));	
//			}
//			
//			money.put("outcome", nf.format(Math.abs(money.getDouble("outcome"))));
//		}
//		result.put("money", money);
//		result.put("months",getMonths());
//		
//		ResponseUtil.toClient(response, result);
//	}
	@RequestMapping(value="queryBonusDetail.do")
	public void queryBonusDetail(HttpServletRequest request, HttpServletResponse response, String userType, String date) {
//		List<MyBonusDetailDTO> bonusDetails = accountViewService.getMyBonusDetailInMonths(userId, userType, date);
		String userId = CookiesUtil.getUserId(request);
		List<MyBonusDetailDTO> bonusDetails = busUserBonusService.getMyBonusDetailInMonths(userId, userType, date);
		try {
			if (ArrayUtil.isEmpty(bonusDetails)) {
				ResponseUtil.toClient(response, "null");
				return ;
			}
			JSONArray bonusArray = JsonUtil.fromList(bonusDetails, new JsonConfig());
			JSONObject r = new JSONObject();
			r.put("bonus", bonusArray);
			ResponseUtil.toClient(response, r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private JSONArray getMonths() {
		JSONArray result = new JSONArray();
		Calendar c = Calendar.getInstance();
		for (int j = 0; j < 6; j++) {
			JSONObject d = new JSONObject();
			if(j!=0){
				c.add(Calendar.MONTH, -1);
			}
 			String year = String.valueOf(c.get(Calendar.YEAR));
			String month = String.valueOf(c.get(Calendar.MONTH)+1 < 10 ? "0"
					+ (c.get(Calendar.MONTH)+1) : c.get(Calendar.MONTH)+1);
			d.put("key", year + "年" + month + "月");
			d.put("value", year + "-" + month);
			result.add(d);
		}
		return result;

	}

	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.agentPopularize.id");
	}
}
