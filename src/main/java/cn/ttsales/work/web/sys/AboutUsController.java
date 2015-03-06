package cn.ttsales.work.web.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.web.common.CorpOAuthController;

@Controller
@RequestMapping("sys/aboutUs")
public class AboutUsController extends CorpOAuthController {
	
	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map) {
		ModelAndView mv = new ModelAndView();
		String userId = map.get("userId");
		if(StringUtil.isEmpty(userId)){
			mv.addObject("join","1");
		}else{
			mv.addObject("join","2");
		}
		mv.setViewName("pages/sys/aboutUs");	
		return mv;
	}

	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,"wx.corp.agentHelper.id");
	}
 
	
}
