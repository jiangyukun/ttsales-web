package cn.ttsales.work.web.report;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.web.common.CorpOAuthController;

@Controller
@RequestMapping("report/myPerformance/")
public class MyPerformanceController extends CorpOAuthController {
//	@Autowired
//	private TransmitTempService transmitTempService;
	
	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map) {
		//TODO 实时统计
		//transmitTempService.updateTransmitTemp(DateUtil.getCurrentDateStr());
		ModelAndView m = new ModelAndView();
		String userId = (String) map.get("userId");
 		m.addObject("months",getMonths());
		m.addObject("userId", userId);
		m.setViewName("pages/report/myPerformance");
		return m;
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

	/**
	 * 默认注册到产品推广代理
	 */
	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.agentPopularize.id");
	}

}
