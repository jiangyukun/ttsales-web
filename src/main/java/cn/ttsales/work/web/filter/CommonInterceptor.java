package cn.ttsales.work.web.filter;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;

public class CommonInterceptor implements HandlerInterceptor{
	private Logger log = Logger.getLogger(CommonInterceptor.class);  
	//企业号APPID
	private String APPID = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.corp.id");

	/**利用正则映射到需要拦截的路径*/
	private String mappingURL = "products";
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();  
        log.info("preHandle url:"+url);
        log.info("preHandle QueryString:"+request.getQueryString());
//        if(url.indexOf("init.do") > 0){
//        	url = URLEncoder.encode(url.replaceAll("init.do", "oAuthInit.do"), "utf-8");
//        	String toOAuthUrl = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.url.getCode",this.APPID,url,"code","snsapi_base","STATE");;
//    		response.sendRedirect(toOAuthUrl);
//        	return false;
//        }
        return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void setMappingURL(String mappingURL) {    
		this.mappingURL = mappingURL;    
	}  

}
