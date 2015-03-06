package cn.ttsales.work.web.common;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.Base64Util;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.RepOpenUser;
import cn.ttsales.work.domain.RepUserCross;
import cn.ttsales.work.domain.WxAppUser;
import cn.ttsales.work.dto.QyClaimLotteryDTO;
import cn.ttsales.work.service.msg.EntHelperResponseService;
import cn.ttsales.work.service.rep.RepOpenUserService;
import cn.ttsales.work.service.rep.RepUserCrossService;
import cn.ttsales.work.service.sys.WxAppUserService;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.wxapi.MpApi;
import cn.ttsales.work.wxapi.mp.pojo.OpenUserInfo;

@Controller
@RequestMapping(value="common/")
public class WeixinAuthorize {
	
	@Autowired
	private RepOpenUserService repOpenUserService;
	
	@Autowired
	private EntHelperResponseService entHelperResponseService;
	
	@Autowired
	private WxAppUserService wxAppUserService;
	
	@Autowired
	private RepUserCrossService repUserCrossService;
	
	@RequestMapping(value="authorize.do")
	public void weixinAuthorize(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam String backurl, String saveInfo) throws Exception {
		
		String host = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.website");
		String redirectUri = host + "/common/authorizeInfo.do";
		
		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.mp.id");
		String codeUrl = MpApi.getInstance().getRedirectCodeUrl(appId, URLEncoder.encode(redirectUri + "?backurl=" + backurl, "utf-8"), "code", "snsapi_userinfo", "01");
 		//进入授权页面
		String openId = CookiesUtil.getOpenId(request);
		if(StringUtil.isEmpty(openId)){
			response.sendRedirect(codeUrl);
		}else{
			backurl = Base64Util.DecoderBase64(backurl);
			QyClaimLotteryDTO lotDto = new QyClaimLotteryDTO();
			String userId = CookiesUtil.getUserId(request);
			if(StringUtil.isEmpty(userId)){
				//公众用户
				lotDto.setOpenId(openId);
			}else{
				lotDto.setUserId(userId);
			}
			lotDto.setSchemeId(getSchemeId(backurl));
			lotDto.setLotteryType("03");
			lotDto.setDeptId("405");
			//entHelperResponseService.claimQyLottery(null, lotDto);
   			if (backurl.contains(request.getContextPath())) {
				backurl = backurl.replace(request.getContextPath(), "");
			}
			StringBuffer redirctUrl = new StringBuffer(host + backurl);
			response.sendRedirect(redirctUrl.toString());
		}
 	}

	@RequestMapping(value="authorizeInfo.do")
	public void weixinAuthorizeUserInfo(HttpServletRequest request, HttpServletResponse response
			,@RequestParam String backurl, @RequestParam String code) throws IOException {
		
		backurl = Base64Util.DecoderBase64(backurl);
		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.mp.id");
		String secret = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.mp.secret");
		
		String host = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.website");
		
		if (backurl.contains(request.getContextPath())) {
			backurl = backurl.replace(request.getContextPath(), "");
		}
		StringBuffer redirctUrl = new StringBuffer(host + backurl);
		
		//回调url是否存在参数
		if (backurl.contains("?")) {
			redirctUrl.append("&authorize=t");
		} else {
			redirctUrl.append("?authorize=t");
		}
		OpenUserInfo uInfo = MpApi.getInstance().getUserinfo(appId, secret, code);
		RepOpenUser rUser = openUserInfoToRepOpenuser(uInfo);
 		String userId = CookiesUtil.getUserId(request);
		QyClaimLotteryDTO lotDto = new QyClaimLotteryDTO();
		if(StringUtil.isEmpty(userId)){
			//公众用户
			lotDto.setOpenId(rUser.getOpenId());
		}else{
			lotDto.setUserId(userId);
		}
		lotDto.setSchemeId(getSchemeId(backurl));
		lotDto.setLotteryType("03");
		lotDto.setDeptId("405");
		
		//更新RepUserCross
		RepUserCross repUserCross = repUserCrossService.getRepUserCross(CookiesUtil.getOpenUserCrossId(request));
		if(!StringUtil.isEmpty(repUserCross)){
			repUserCross.setUserId(rUser.getOpenId());
			repUserCross.setType("03");
			repUserCrossService.editRepUserCross(repUserCross);
		}
  		
//		entHelperResponseService.claimQyLottery(null, lotDto);
 
 		if(StringUtil.isEmpty(wxAppUserService.queryWxAppUserByAppIdAndUserId(appId, uInfo.getOpenId()))){
			WxAppUser wxAppUser = new WxAppUser();
			wxAppUser.setAppid(appId);
			wxAppUser.setUserId(uInfo.getOpenId());
			wxAppUser.setWcOpenId(uInfo.getOpenId());
			wxAppUserService.saveWxAppUser(wxAppUser);
		}
 		
		String userCrossId = CookiesUtil.getOpenUserCrossId(request);
		repOpenUserService.addRepOpenUser(rUser,userCrossId);
		CookiesUtil.addOpenId(uInfo.getOpenId(), response); 
		CookiesUtil.addCookie(uInfo.getOpenId()+"_"+getSchemeId(backurl),uInfo.getOpenId(),365*24*60*60, response);
		CookiesUtil.addCookie("authorized", "y", 365 * 24 * 30 * 60, response);
		//编码参数返回调地址
		String[] r = redirctUrl.toString().split("[?]");
		response.sendRedirect(r[0] + "?params=" + Base64Util.EncoderBase64(r[1]));
	}
	
	public RepOpenUser openUserInfoToRepOpenuser(OpenUserInfo uInfo) {
		RepOpenUser rUser = new RepOpenUser();
		rUser.setOpenId(uInfo.getOpenId());
		rUser.setCity(uInfo.getCity());
		rUser.setCountry(uInfo.getCountry());
		rUser.setHeadImgUrl(uInfo.getHeadImgUrl());
		rUser.setNickName(uInfo.getNickName());
		rUser.setProvince(uInfo.getProvince());
		rUser.setSex(uInfo.getSex());
		return rUser;
	}
	
	public String getSchemeId(String url) {
		String r = null;
		
		if (!StringUtil.isEmpty(url)) {
			String[] ps = url.split("&");
			for(int i = 0, l = ps.length; i < l; i++) {
				if(ps[i].contains("schemeId")) {
					r = ps[i].substring(ps[i].indexOf("=") + 1);
					break;
				}
			}
		}
		return r;
	}
}
