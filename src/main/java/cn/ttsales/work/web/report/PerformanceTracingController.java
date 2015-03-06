package cn.ttsales.work.web.report;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.BusPopularize;
import cn.ttsales.work.dto.SchemePerformanceDTO;
import cn.ttsales.work.service.bus.BusPopularizeService;
import cn.ttsales.work.service.sys.TransmitTempService;
import cn.ttsales.work.web.common.CorpOAuthController;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping(value="report/performanceTracing/")
public class PerformanceTracingController extends CorpOAuthController{
	@Autowired
	TransmitTempService transmitTempService;
	@Autowired
	BusPopularizeService busPopularizeService;

	@Override
	@RequestMapping(value="pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> map) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/pages/report/performanceTracing.html?v=" + SASConstants.SAS_VERSION);
		return mv;
	}

	@RequestMapping(value="initData.do")
	public void InitDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String memberId = CookiesUtil.getUserId(request);
		JSONObject traData = new JSONObject();
		
		List<BusPopularize> bps = busPopularizeService.queryMemberBusPopularizedByState(memberId);
		JSONArray popuArray = new JSONArray();
		if (!ArrayUtil.isEmpty(bps)) {
			for (BusPopularize bp : bps) {
				JSONObject pJson = new JSONObject();
				pJson.put("popularizeId", bp.getPopularizeId());
				pJson.put("title", bp.getProProductScheme().getTitle());
				popuArray.add(pJson);
			}
			traData.put("schemeArray", popuArray);
		}
		ResponseUtil.toClient(response, traData);
	}
	
	@RequestMapping(value="querySchemePreformance.do")
	public void querySchemePreformance(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String popularizeId) throws IOException{
		SchemePerformanceDTO sPerformance = transmitTempService.queryMemberPerformanceInScheme(popularizeId);
		
		JSONObject result = new JSONObject();
		if (!StringUtil.isEmpty(sPerformance)) {
			result = JsonUtil.fromObject(sPerformance, new JsonConfig());
		}
		ResponseUtil.toClient(response, result);
	}

	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.agentPopularize.id");
	}
}
