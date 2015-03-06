package cn.ttsales.work.web.common;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.RepUserCross;
import cn.ttsales.work.service.rep.RepUserCrossService;
import cn.ttsales.work.wxapi.CorpApi;

/**
 * 
 * @author dandyzheng
 * 
 */

/*
 * @Controller
 * 
 * @RequestMapping("common/")
 */
public abstract class CorpOAuthController {
	private Logger log = Logger.getLogger(CorpOAuthController.class);
	
	@Autowired
	protected RepUserCrossService repUserCrossService;
	
	@RequestMapping("init.do")
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> map)
			throws IOException {
		log.info("call commonController init");
		String userId ="";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
	        for(Cookie c :cookies ){
	        	if("MemberUserId".equals(c.getName()))
	        		userId = c.getValue();
	        }
		}
		//if (true) {
		if (null == userId || "".endsWith(userId)) {
			String url = request.getRequestURL().toString(); 
			String queryString = request.getQueryString();
			if (null != queryString && !"".equals(queryString))
				url = url + "?" + queryString;
			url = URLEncoder.encode(url.replaceAll("init.do", "oAuthInit.do"), "utf-8");
			String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.corp.id");
	    	String toOAuthUrl = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.url.getCode",appId,url,"code","snsapi_base","STATE");;
	    	log.info("toOAuthUrl: " + toOAuthUrl);
	    	response.sendRedirect(toOAuthUrl);
			return null;
		} 
		map.put("userId", userId);
		return this.pageInit(request, response, map);

		
	}

	@RequestMapping("oAuthInit.do")
	public ModelAndView oAuthInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map,
			@RequestParam String code, @RequestParam String state)
			throws IOException {
		log.info("call CorpOAuthController oAuthInit");
		log.info("code：" + code);
		log.info("state：" + state);
		String corpAppId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.id");
		String corpSecret = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.corp.secret");
		String agentId = map.get("agentId");
		if (null == agentId || "".endsWith(agentId))//未指定代理，则用controller指定的默认代理
			agentId = this.getAgentId();
		
		CorpApi corpApi = CorpApi.getInstance();

		
		String userId = corpApi.getUserId(corpAppId, corpSecret, code, agentId);
		if(!StringUtil.isEmpty(userId)){
			Cookie userIdCookie = new Cookie("MemberUserId",userId);
			userIdCookie.setPath("/");
			userIdCookie.setMaxAge(365*24*60*60);
			response.addCookie(userIdCookie);	

		
		
			RepUserCross repUserCross = new RepUserCross();
			// 检查当前用户是否已生成crossid，防止重复生成
			List<RepUserCross> rRepUserCrosses = repUserCrossService
					.queryRepUserCrossByUserId(userId, SASConstants.USER_CROSS_TYPE_ENT);
			if (!rRepUserCrosses.isEmpty()) {// 已存在crossid
				repUserCross = rRepUserCrosses.get(0);// 返回已有的crossid
			} else {
				repUserCross.setCreateTime(DateUtil.getCurrentDateTimeStr());
				repUserCross.setUserId(userId);
				repUserCross.setType(SASConstants.USER_CROSS_TYPE_ENT);
				repUserCrossService.saveRepUserCross(repUserCross);// 生成新的crossid
			}
			
			Cookie crossIdCookie = new Cookie("MemberUserCrossId",repUserCross.getUserCrossId());
			crossIdCookie.setMaxAge(365*24*60*60);
			crossIdCookie.setPath("/");
			response.addCookie(crossIdCookie);
		}
		map.put("userId", userId);
		
		return this.pageInit(request, response, map);
	}

	protected abstract ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map);

	protected abstract String getAgentId();
}
