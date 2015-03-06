package cn.ttsales.work.web.report;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.web.common.CorpOAuthController;

@Controller
@RequestMapping("report/entPerformance/")
public class EntPerformanceController extends CorpOAuthController{
 

	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map) {
		ModelAndView m = new ModelAndView();
		String userId = (String)map.get("userId");
		//userId = "zhengdeyi";
		//List<RepPopularizeTransmitReportDTO> repPopularizeTransmitReportDTOs = bepTransmitService.queryReportTransmitData(userId);
		//m.addObject("repPopularizeTransmitReportDTOs", repPopularizeTransmitReportDTOs);
		m.addObject("userId", userId);
		//m.addObject("url", "rep/transmitTree/viewTransmitTree.do");
		m.setViewName("pages/report/entPerformance");
		return m;
	}

	/**
	 * 默认注册到“评估计费”代理
	 */
	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,"wx.corp.agentEvaluate.id");
	}
	
}
