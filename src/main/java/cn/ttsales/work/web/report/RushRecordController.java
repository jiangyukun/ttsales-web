package cn.ttsales.work.web.report;

import java.io.IOException;
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
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping("report/rushRecord/")
public class RushRecordController extends CorpOAuthController{
	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map) {
		//TODO 实时统计
		ModelAndView m = new ModelAndView();
		//m.addObject("userId", map.get("userId"));
		m.setViewName("redirect:/pages/report/rushRecord.html?v="+SASConstants.SAS_VERSION);
		return m;
	}
	
	@RequestMapping("initData.do")
	protected ModelAndView initData(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		result.put("months", getMonths());
		ResponseUtil.toClient(response, result);
		return null;
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
	 * 默认注册到抢单记录代理
	 */
	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,"wx.corp.agentStore.id");
	}
	
}
