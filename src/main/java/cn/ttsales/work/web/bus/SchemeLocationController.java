package cn.ttsales.work.web.bus;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.Base64Util;
import cn.ttsales.work.web.common.MpOAuthController;

@Controller
@RequestMapping(value="bus/schemeLoction/")
public class SchemeLocationController extends MpOAuthController{

	@Override
	@RequestMapping(value="pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> map) {
		
		ModelAndView mv = new ModelAndView();
		String params = Base64Util.DecoderBase64(map.get("params"));
		String schemeId = getUrlParValue(params, "schemeId");
		
		if (schemeId == null) {
			mv.setViewName("redirect:/pages/htmls/notFoundScheme.html");			
		} else {
			mv.setViewName("redirect:" + "/pages/htmls/product_scheme_" + schemeId
					+ ".html?v=" + SASConstants.SAS_VERSION + "&params="
					+ map.get("params"));
		}
		return mv;
	}
	
	public String getUrlParValue(String url, String pName) {
		Pattern p = Pattern.compile("(^|&)" + pName + "=([^&]*)(&|$)", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(url);
		
		while(m.find()) {
			return m.group(2);
		}
		return null;
	}
}
