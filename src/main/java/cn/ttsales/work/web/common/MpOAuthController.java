package cn.ttsales.work.web.common;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.wxapi.MpApi;

/**
 * 
 * @author zhangmizhong
 * 
 */

/*
 * @Controller
 * 
 * @RequestMapping("common/")
 */
public abstract class MpOAuthController {
	private Logger log = Logger.getLogger(MpOAuthController.class);

	@RequestMapping("init.do")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map)
			throws IOException {
		log.info("call MpOAuthController init");

		String url = request.getRequestURL().toString();
		log.info("url: " + url);
		url= url.replaceAll(":80", "");
		log.info("url: " + url);
		//textwx.xlcpk//SASWeb/stor
		String queryString = request.getQueryString();
		if (null != queryString && !"".equals(queryString))
			url = url + "?" + queryString;

		url = URLEncoder.encode(url.replaceAll("init.do", "oAuthInit.do"),
				"utf-8");
//		String appId = "wxcbd1ecfae56ebfd3";// Test Code
		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
		 "wx.mp.id");
		String toOAuthUrl = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.url.getCode", appId, url,
				"code", "snsapi_base", "STATE");
		log.info("toOAuthUrl: " + toOAuthUrl);
		response.sendRedirect(toOAuthUrl);
		return null;

	}

	@RequestMapping("oAuthInit.do")
	public ModelAndView oAuthInit(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, String> map, @RequestParam String code,
			@RequestParam String state) throws IOException {
		log.info("call MpOAuthController oAuthInit");

		log.info("code：" + code);
		log.info("state：" + state);
//		String appId = "wxcbd1ecfae56ebfd3";// Test Code
//		String secret = "2e4efe0708bb72fa793b595f370d26e2";// Test Code
		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
		 "wx.mp.id");
		String secret = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
		 "wx.mp.secret");

		String openId = MpApi.getInstance()
				.getOauth2AccessToken(appId, secret, code).getOpenid();

		//map.put("openId", openId);
		CookiesUtil.addOpenId(openId, response);
		return this.pageInit(request, response, map);
	}

	protected abstract ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map);

}
