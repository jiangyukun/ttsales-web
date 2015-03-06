package cn.ttsales.work.web.bus;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.domain.BusPopularize;
import cn.ttsales.work.domain.RepOpenUser;
import cn.ttsales.work.domain.RepReadCount;
import cn.ttsales.work.domain.RepTransmit;
import cn.ttsales.work.domain.RepUserCross;
import cn.ttsales.work.service.bus.BusPopularizeService;
import cn.ttsales.work.service.rep.RepReadCountService;
import cn.ttsales.work.service.rep.RepTransmitService;
import cn.ttsales.work.service.rep.RepUserCrossService;
import cn.ttsales.work.web.common.util.ResponseUtil;
import cn.ttsales.work.wxapi.MpApi;
import cn.ttsales.work.wxapi.mp.pojo.OpenUserInfo;

@Controller
@RequestMapping("bus/popularizeDetail")
public class PopularizeDetailController {
	private Logger log = Logger.getLogger(PopularizeDetailController.class);
	@Autowired
	private BusPopularizeService busPopularizeService;
	@Autowired
	private RepTransmitService repTransmitService;
	@Autowired
	private RepUserCrossService repUserCrossService;
	@Autowired
	private RepReadCountService repReadCountService;
 
	@Deprecated
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> map,
			@RequestParam String userType, @RequestParam String popularizeId,
			@RequestParam String pTransmitId) {
		ModelAndView m = new ModelAndView();

		BusPopularize busPopularize = busPopularizeService
				.getBusPopularize(popularizeId);
		m.addObject("busPopularize", busPopularize);
		m.addObject("userType", userType);
		m.addObject("pTransmitId", pTransmitId);
		m.setViewName("pages/bus/popularizeDetail");
		return m;
	}
	@Deprecated
	@RequestMapping("forwardPopularize.do")
	protected void forwardPopularize(HttpServletRequest request,
			HttpServletResponse response, RepTransmit repTransmit)
			throws IOException {

		repTransmit.setTraTime(DateUtil.getCurrentDateTimeStr());
		repTransmitService.editRepTransmit(repTransmit);
		log.info(repTransmit.getTransmitId());
		ResponseUtil.toClient(response, repTransmit.getTransmitId());
	}
	@Deprecated
	@RequestMapping("readPopularize.do")
	protected void readPopularize(HttpServletRequest request,
			HttpServletResponse response, RepReadCount repReadCount)
			throws IOException {

		repReadCount.setReadTime(DateUtil.getCurrentDateTimeStr());
		repReadCountService.saveRepReadCount(repReadCount);
		log.info("repReadCount created!  transmitId:"
				+ repReadCount.getTransmitId() + " readTime:"
				+ repReadCount.getReadTime());
	}

	@RequestMapping("newUserCrossId.do")
	protected void newUserCrossId(HttpServletRequest request,
			HttpServletResponse response, RepUserCross repUserCross)
			throws IOException {

		log.info("type:" + repUserCross.getType());
		repUserCross.setCreateTime(DateUtil.getCurrentDateTimeStr());
		repUserCrossService.saveRepUserCross(repUserCross);// 生成新的crossid
		log.info("UUID:" + repUserCross.getUserCrossId());
		ResponseUtil.toClient(response, repUserCross.getUserCrossId());

	}

	
	@Deprecated
	@RequestMapping("oAuth.do")
	public void oAuth(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String url) throws IOException {
		MpApi mpApi = MpApi.getInstance();
		log.info("url:" + url);
		url = url.replaceFirst("pageInit.do", "oAuthPageInit.do");
		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.mp.id");
		String redirectCodeUrl = mpApi.getRedirectCodeUrl(appId, url, "code",
				"snsapi_userinfo", "oAuth");
		ResponseUtil.toClient(response, redirectCodeUrl);
	}
	@Deprecated
	@RequestMapping("oAuthPageInit.do")
	protected ModelAndView oAuthPageInit(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> map,
			@RequestParam String userType, @RequestParam String popularizeId,
			@RequestParam String pTransmitId, @RequestParam String code,
			@RequestParam String state) {
		ModelAndView m = new ModelAndView();

		BusPopularize busPopularize = busPopularizeService
				.getBusPopularize(popularizeId);
		m.addObject("busPopularize", busPopularize);
		m.addObject("userType", userType);
		m.addObject("pTransmitId", pTransmitId);
		m.addObject("code", code);
		m.addObject("state", state);
		m.setViewName("pages/bus/popularizeDetail");
		return m;
	}
	@Deprecated
	@RequestMapping("newOpenId.do")
	protected void newOpenId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map,
			@RequestParam String userCrossId, @RequestParam String code)
			throws IOException {
		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.mp.id");
		String secret = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.mp.secret");
		
		MpApi mpApi = MpApi.getInstance();
		OpenUserInfo openUserInfo = mpApi.getUserinfo(appId,secret,code);
		
		RepOpenUser repOpenUser = new RepOpenUser(); 
		BeanUtils.copyProperties(openUserInfo, repOpenUser);
		repOpenUser.setSubscribeTime(openUserInfo.getSubscribeTime());
		repOpenUser.setSubscribeState("1");		
		RepUserCross repUserCross = repUserCrossService
				.getRepUserCross(userCrossId);
		repUserCross.setUserId(repOpenUser.getOpenId());
				
		repUserCrossService.editOpenUser(repUserCross, repOpenUser);
	
		ResponseUtil.toClient(response, repOpenUser.getNickName());
	}
}
