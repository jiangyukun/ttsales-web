package cn.ttsales.work.web.rep;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.domain.BusPopularize;
import cn.ttsales.work.service.bus.BusPopularizeService;
import cn.ttsales.work.web.common.CorpOAuthController;

@Controller
@RequestMapping("rep/trackPopularizes")
public class TrackPopularizesController extends CorpOAuthController {

	@Autowired
	private BusPopularizeService busPopularizeService;
	
//	@Autowired
//	private TransmitTempService transmitTempService;
	
	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map) {
		//TODO 实时统计
		//transmitTempService.updateTransmitTemp(DateUtil.getCurrentDateStr());
		String userId = (String) map.get("userId");
 		String selectPopularize = (String) map.get("selectPopularize");
		List<BusPopularize> busPopularizes = busPopularizeService
				.queryBusPopularizesByMemberId(userId);
		ModelAndView m = new ModelAndView();
		m.addObject("userId", userId);
		m.addObject("selectPopularize", selectPopularize);
		m.addObject("busPopularizes", busPopularizes);
		m.setViewName("pages/rep/trackPopularizes");
		return m;
	}

	/**
	 * 默认注册到产品推广代理
	 */
	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.agentPopularize.id");
	}
}
