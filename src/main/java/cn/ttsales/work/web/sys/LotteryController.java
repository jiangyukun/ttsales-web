package cn.ttsales.work.web.sys;

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
import cn.ttsales.work.service.sys.SysLotteryService;
import cn.ttsales.work.web.common.CorpOAuthController;
import cn.ttsales.work.web.common.HttpRequestProxy;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping(value="sys/lottery/")
public class LotteryController extends CorpOAuthController{
	private static ConcurrentLinkedQueue<Map<String, Object>> drawLotQueue = new ConcurrentLinkedQueue<Map<String, Object>>();
	private static byte[] drawLotLock = new byte[0];
	
	@Autowired
	private SysLotteryService sysLotteryService;

	@Override
	@RequestMapping(value="pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response,@RequestParam Map<String, String> map) {
		
		ModelAndView mv = new ModelAndView();
		String url = "";
		String deptId = map.get("deptId");
		
		if ("401".equals(deptId) || "490".equals(deptId)) {
			url = "redirect:/pages/lottery/lottery.html?v=";
		} else if ("40401".equals(deptId) || "40402".equals(deptId)) {
			url = "redirect:/pages/lottery/lottery_audi.html?v=";
		}
			
		String params = Base64Util.EncoderBase64("deptId=" + deptId + "&appId=" + map.get("appId"));
		mv.setViewName(url + SASConstants.SAS_VERSION + "&params=" + params);
		return mv;
	}
	
	@RequestMapping(value="initLottery.do")
	public void initLottery(HttpServletRequest request, HttpServletResponse response,
			String deptId) throws IOException {
		
		String userId = CookiesUtil.getUserId(request);
		if (userId == null) {
			userId = "";
		}
		JSONObject lottery = new JSONObject();
		lottery.put("lotSum", sysLotteryService.queryCanLotteryCount(userId, deptId));
		lottery.put("userRecord", sysLotteryService.queryLotterRecordByUserIdAndDeptId(userId, deptId));
		lottery.put("deptRecord", sysLotteryService.queryLotterRecordByUserIdAndDeptId(null, deptId));
		
		ResponseUtil.toClient(response, lottery);
	}
	
	@RequestMapping(value="checkLotSum.do")
	public void checkLotSum(HttpServletRequest request, HttpServletResponse response,
			String deptId) throws IOException {
		
		String userId = CookiesUtil.getUserId(request);
		if (userId == null) {
			userId = "";
		}
		JSONObject lottery = new JSONObject();
		lottery.put("lotSum", sysLotteryService.queryCanLotteryCount(userId, deptId));

		ResponseUtil.toClient(response, lottery);
	} 
	
	@RequestMapping(value="drawLottery.do")
	public void DrawLottery(HttpServletRequest request, HttpServletResponse response, 
			String appId, String deptId) throws IOException {
		
		String userId = CookiesUtil.getUserId(request);
		if (userId == null) {
			userId = "";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", appId);
		map.put("deptId", deptId);
		map.put("userId", userId);
		map.put("response", response);
		
		drawLotQueue.add(map);
		
		while(!drawLotQueue.isEmpty()) {
			synchronized(drawLotLock) {
				Map<String, Object> tmap = drawLotQueue.poll();
				if (tmap == null) {
					continue;
				}
				ResponseUtil.toClient((HttpServletResponse)(tmap.get("response")), sysLotteryService.createWxUserBonusByLottery((String)tmap.get("appId"), (String)tmap.get("deptId"), (String)tmap.get("userId")));
			}
		}
	}
	
	@RequestMapping(value="sendLottery.do") 
	public void SendLottery(HttpServletRequest request, HttpServletResponse response, String bonusId) throws Exception {
		
		HttpRequestProxy hrp = new HttpRequestProxy();
		Map<String,String> postData = new HashMap<String, String>();
	    postData.put("userBonusId", bonusId);
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.rbssite"); 
 	    String jsonStr = hrp.doRequest(baseUrl+"/weixin/bonus/sendSingleBonus.do?", postData, null, "utf-8");
	    ResponseUtil.toClient(response, jsonStr);
	}

	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,"wx.corp.agentHelper.id");
	}
}