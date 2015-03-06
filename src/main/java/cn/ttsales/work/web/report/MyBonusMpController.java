package cn.ttsales.work.web.report;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.web.common.MpOAuthController;

@Controller
@RequestMapping(value="report/myBonusMp/")
public class MyBonusMpController extends MpOAuthController{

	@Override
	@RequestMapping(value="initPage.do")
	protected ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response, Map<String, String> map) {
		ModelAndView m = new ModelAndView();
//		m.addObject("another", map.get("another"));
//		m.addObject("userId", map.get("openId"));
//		m.addObject("userType", "03");
//		m.setViewName("redirect:/pages/report/myBonus.html?v="+SASConstants.SAS_VERSION);
		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.mp.id");
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.rbssite");
 		m.setViewName("redirect:"+baseUrl+"/weixin/bonus/init.do?appid="+appId+"&userid="+ map.get("openId")+"&v="+SASConstants.SAS_VERSION);
		return m;
	}
}
