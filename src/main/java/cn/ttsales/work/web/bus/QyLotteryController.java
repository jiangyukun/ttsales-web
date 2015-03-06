package cn.ttsales.work.web.bus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.Base64Util;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.dto.QyClaimLotteryDTO;
import cn.ttsales.work.service.bus.BusLotteryService;
import cn.ttsales.work.service.msg.EntHelperResponseService;
import cn.ttsales.work.web.common.CorpOAuthController;
import cn.ttsales.work.web.common.HttpRequestProxy;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping(value="qy/lottery/")
public class QyLotteryController extends CorpOAuthController{
	
	@Autowired
	private BusLotteryService busLotteryService;
	@Autowired
	private EntHelperResponseService entHelperResponseService;
	
	private static ConcurrentLinkedQueue<Map<String, Object>> drawLotQueue = new ConcurrentLinkedQueue<Map<String, Object>>();
	private static byte[] drawLotLock = new byte[0];
	private String corpAppId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.corp.id");
	private String mpAppId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.mp.id");
	
	//	private static String schemeId = "999003";
	private static String schemeId = "501001";

	@Override
	@RequestMapping(value="pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response,@RequestParam Map<String, String> map) {
		
		ModelAndView mv = new ModelAndView();
			
		String params = Base64Util.EncoderBase64("deptId=" + map.get("deptId"));
		mv.setViewName("redirect:/pages/lottery/lottery_gqsl.html?v=" + SASConstants.SAS_VERSION + "&params=" + params);
		return mv;
	}
	
	@RequestMapping(value="outerIn.do")
	protected ModelAndView outerIn(HttpServletRequest request, 
			HttpServletResponse response,@RequestParam Map<String, String> map) {
		
		ModelAndView mv = new ModelAndView();
			
		String params = Base64Util.EncoderBase64("deptId=405");
		mv.setViewName("redirect:/pages/lottery/lottery_gqsl.html?v=" + SASConstants.SAS_VERSION + "&params=" + params);
		return mv;
	}
	
	@RequestMapping(value="initLottery.do")
	public void initLottery(HttpServletRequest request, HttpServletResponse response,
			String deptId) throws IOException {

		JSONObject lottery = new JSONObject();
		String userId = CookiesUtil.getUserId(request);
		String openId = CookiesUtil.getCookieValue("openId", request);
		
		if (userId == null && openId == null) {
			lottery.put("state", "user-wrong");
		} else {
			lottery.put("lotSum", busLotteryService.queryCanLotteryCount(deptId, userId, openId));
			lottery.put("readCount", busLotteryService.queryValidReadCount(deptId, schemeId, userId, openId));
			lottery.put("userRecord", busLotteryService.queryLotterRecordByUserIdAndDeptId(deptId, userId, openId));
			lottery.put("deptRecord", busLotteryService.queryLotterRecordByUserIdAndDeptId(deptId, null, null));
			lottery.put("state", "success");
		}
		
		ResponseUtil.toClient(response, lottery);
	}
	
	@RequestMapping(value="checkLotSum.do")
	public void checkLotSum(HttpServletRequest request, HttpServletResponse response,
			String deptId) throws IOException {
		
		String userId = CookiesUtil.getUserId(request);
		String openId = CookiesUtil.getCookieValue("openId", request);
		
		JSONObject lottery = new JSONObject();
		lottery.put("lotSum", busLotteryService.queryCanLotteryCount(deptId, userId, openId));

		ResponseUtil.toClient(response, lottery);
		
	} 
	
	@RequestMapping(value="drawLottery.do")
	public void drawLottery(HttpServletRequest request, HttpServletResponse response, 
			String deptId) throws IOException {
		
		String userId = CookiesUtil.getUserId(request);
		String openId = CookiesUtil.getCookieValue("openId", request);
		
		if (userId == null && openId == null) {
			JSONObject result = new JSONObject();
			result.put("state", "user-wrong");
			ResponseUtil.toClient(response, result);
			return;
		}
		
		QyClaimLotteryDTO lotDto = new QyClaimLotteryDTO(userId, openId, deptId, "", "");
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("appId", corpAppId);
		} else {
			map.put("appId", mpAppId);
		}
		map.put("lotDto", lotDto);
		map.put("response", response);
		
		drawLotQueue.add(map);
		
		while(!drawLotQueue.isEmpty()) {
			synchronized(drawLotLock) {
				Map<String, Object> tmap = drawLotQueue.poll();
				if (tmap == null) {
					continue;
				}
				ResponseUtil.toClient((HttpServletResponse)(tmap.get("response")), busLotteryService.createWxUserBonusByLottery((String)tmap.get("appId"), (QyClaimLotteryDTO)tmap.get("lotDto")));
			}
		}
		
	}
	
	@RequestMapping(value="sendLottery.do") 
	public void sendLottery(HttpServletRequest request, HttpServletResponse response, String bonusId) throws Exception {
		
		HttpRequestProxy hrp = new HttpRequestProxy();
		Map<String,String> postData = new HashMap<String, String>();
	    postData.put("userBonusId", bonusId);
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.rbssite"); 
 	    String jsonStr = hrp.doRequest(baseUrl+"/weixin/bonus/sendSingleBonus.do?", postData, null, "utf-8");
	    ResponseUtil.toClient(response, jsonStr);
	}
	
	@RequestMapping(value="claimLottery.do") 
	public void claimLottery(HttpServletRequest request, HttpServletResponse response, String deptId) throws Exception {

		String userId = CookiesUtil.getUserId(request);
		String openId = CookiesUtil.getCookieValue("openId", request);
		
		if (userId == null && openId == null) {
			JSONObject result = new JSONObject();
			result.put("state", "user-wrong");
			ResponseUtil.toClient(response, result);
			return;
		}
		
		QyClaimLotteryDTO lotDto = new QyClaimLotteryDTO();
		lotDto.setUserId(userId);
		lotDto.setOpenId(openId);
		lotDto.setDeptId(deptId);
		lotDto.setLotteryType("02");
		lotDto.setSchemeId(schemeId);
		
		entHelperResponseService.claimQyLottery(response, lotDto);
	}
	
	@RequestMapping(value="setMyCookies.do")
	public void setMyCookies(HttpServletResponse response) {
		CookiesUtil.addUserIdCookie("zhaoxiaobin", response);
		CookiesUtil.addCookie("openId", "oZPJ4s1BydhdPiJbBUesBsZZ0V5A", 365 * 24 * 60, response);
	}
	
	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,"wx.corp.agentHelper.id");
	}
}