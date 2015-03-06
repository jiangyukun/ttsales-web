package cn.ttsales.work.web.bus;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.RepOpenUser;
import cn.ttsales.work.domain.RepUserCross;
import cn.ttsales.work.service.rep.RepOpenUserService;
import cn.ttsales.work.service.rep.RepUserCrossService;
import cn.ttsales.work.web.common.MpOAuthController;
import cn.ttsales.work.web.common.util.CookiesUtil;

@Controller
@RequestMapping("bus/redPaper")
public class RedPaper extends MpOAuthController {
	@Autowired
	private RepOpenUserService repOpenUserService;
	
	@Autowired
	private RepUserCrossService repUserCrossService;
	
	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> map) {
		String openId = map.get("openId");
		String userCrossId = CookiesUtil.getOpenUserCrossId(request);
		
		RepOpenUser openUser = repOpenUserService.getRepOpenUser(openId);
		if(StringUtil.isEmpty(openUser)){
			//新建RepOpenUser
			RepOpenUser repOpenUser = new RepOpenUser();
			repOpenUser.setOpenId(openId);
			repOpenUser.setSubscribeState(SASConstants.OPEN_SUB_STATE_UNSUB);
			repOpenUserService.editRepOpenUser(repOpenUser);
		}
	 
		//更新RepUserCross
		RepUserCross repUserCross = repUserCrossService.getRepUserCross(userCrossId);
		if(!StringUtil.isEmpty(repUserCross)){
			repUserCross.setUserId(openId);
			repUserCross.setType("03");
			repUserCrossService.editRepUserCross(repUserCross);
		}
		
		ModelAndView m = new ModelAndView();
		Cookie crossIdCookie = new Cookie("openId",openId);
		crossIdCookie.setMaxAge(365*24*60*60); 
		crossIdCookie.setPath("/");
		response.addCookie(crossIdCookie);
		  
		//m.setViewName("redirect:http://mp.weixin.qq.com/s?__biz=MzA3ODkyNDQyMQ==&mid=200748665&idx=1&sn=e219f1c09370d62ca40445565cd1b794#rd");
		m.setViewName("redirect:http://mp.weixin.qq.com/s?__biz=MzAxMTA0ODAzNg==&mid=201990744&idx=1&sn=6cb8f1d33afc7c117cff51655779de05#rd");
		return m;
	}

}
