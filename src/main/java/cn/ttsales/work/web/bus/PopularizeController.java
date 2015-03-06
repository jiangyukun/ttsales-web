package cn.ttsales.work.web.bus;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.domain.BusPopularize;
import cn.ttsales.work.service.bus.BusPopularizeService;
import cn.ttsales.work.web.common.CorpOAuthController;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping("bus/popularize")
public class PopularizeController extends CorpOAuthController{
	
	@Autowired
	private BusPopularizeService busPopularizeService;
	
	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map) {
		ModelAndView m = new ModelAndView();
		//m.addObject("memberId", (String)map.get("userId"));
		m.setViewName("redirect:/pages/bus/popularizes.html?v="+SASConstants.SAS_VERSION);
		return m;
	}
	
	@RequestMapping("initData.do")
	protected void initData(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String state) throws IOException {
		
		List<BusPopularize> busPopularizes = busPopularizeService.queryMemberBusPopularizedByState(CookiesUtil.getUserId(request), state);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"busPopularizes","proProductSchemes"});
		ResponseUtil.toClient(response, JsonUtil.fromList(busPopularizes, jsonConfig));
	}
	
	@RequestMapping("saveScheme.do")
	protected ModelAndView saveScheme(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map) throws IOException {
		//String memberId = (String)map.get("userId");
		String memberId = CookiesUtil.getUserId(request);
		String schemeId = (String)map.get("schemeId");
		boolean result  = this.busPopularizeService.saveScheme(memberId, schemeId);
		ResponseUtil.toClient(response, result);
		return null;
	}
	
	@RequestMapping("changeState.do")
	protected void changeState(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String popularizeId = request.getParameter("popularizeId");
		String state = request.getParameter("state");
		busPopularizeService.changeStage(popularizeId,state);
	}
	
	@RequestMapping("saveWishRecord.do")
	protected void saveWishRecord(HttpServletRequest request, String transmitId, String popularizeId) throws IOException {
		String userCrossId = CookiesUtil.getOpenUserCrossId(request);
		if (userCrossId != null) {
			busPopularizeService.saveWishRecord(userCrossId, transmitId, popularizeId);
		}
	}
	
	/**
	 * 默认注册到“产品推广”代理
	 */
	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,"wx.corp.agentPopularize.id");
	}
}
