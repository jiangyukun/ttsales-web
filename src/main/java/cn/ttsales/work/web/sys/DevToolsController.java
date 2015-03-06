package cn.ttsales.work.web.sys;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;
import cn.ttsales.work.wxapi.MpApi;

@Controller
@RequestMapping("sys/devTools/")
public class DevToolsController {
	
	
	@RequestMapping("urlToShort.do")
	public void longurl2short(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String longUrl)
			throws IOException {
		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.mp.id");
		String secret = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.mp.secret");
		String result = MpApi.getInstance().urlToShort(appId, secret, longUrl);
		ResponseUtil.toClient(response, result);
		
	}
	

 	
}
