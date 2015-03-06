package cn.ttsales.work.web.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesUtil {
	private static final String MEMBER_USERID_COOKIE_NAME = "MemberUserId";
	private static final String MEMBER_USER_CROSSID_COOKIE_NAME = "MemberUserCrossId";
	private static final String OPEN_USER_CROSSID_COOKIE_NAME = "OpenUserCrossId";
	private static final String OPEN_ID_COOKIE_NAME = "openId";
	
	/**
	 * 获取企业用户USERID
	 * @param request
	 * @return 企业userId 
	 * @author dandyzheng
	 * @date 2014年12月19日
	 */
	public static String getUserId(HttpServletRequest request){
		return getCookieValue(MEMBER_USERID_COOKIE_NAME,request);
	}
	
	public static void addUserIdCookie(String userId,HttpServletResponse response){
		addCookie(MEMBER_USERID_COOKIE_NAME,userId,365*24*60*60,response);
	}
	
	/**
	 * 获取企业用户 crossId
	 * @param request
	 * @return 企业用户 crossId
	 * @author dandyzheng
	 * @date 2014年12月19日
	 * @see
	 */
	public static String getMemberUserCrossId(HttpServletRequest request){
		return getCookieValue(MEMBER_USER_CROSSID_COOKIE_NAME,request);
	}
	
	public static void addMemberUserCrossIdCookie(String memberUserCrossId,HttpServletResponse response){
		addCookie(MEMBER_USER_CROSSID_COOKIE_NAME,memberUserCrossId,365*24*60*60,response);
	}
	
	/**
	 * 获取公众号CrossId
	 * @param request
	 * @return 公众号CrossId
	 * @author dandyzheng
	 * @date 2014年12月19日
	 * @see
	 */
	public static String getOpenUserCrossId(HttpServletRequest request){
		return getCookieValue(OPEN_USER_CROSSID_COOKIE_NAME,request);
	}
	
	public static void addOpenUserCrossIdCookie(String openUserCrossId,HttpServletResponse response){
		addCookie(OPEN_USER_CROSSID_COOKIE_NAME,openUserCrossId,365*24*60*60,response);
	}
	/**
	 * 获取OPENID
	 * @param request
	 * @return 企业openId 
	 * @author dandyzheng
	 * @date 2014年12月19日
	 */
	public static String getOpenId(HttpServletRequest request){
		return getCookieValue(OPEN_ID_COOKIE_NAME,request);
	}
	
	public static void addOpenId(String openId,HttpServletResponse response){
		addCookie(OPEN_ID_COOKIE_NAME,openId,365*24*60*60,response);
	}
	
	public static String getCookieValue(String cookieName,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		String vlaue = null;
		if(cookies!=null){
			for (int i = 0; i < cookies.length; i++){
				Cookie c = cookies[i];     
				if(c.getName().equalsIgnoreCase(cookieName)){
					vlaue = c.getValue();
					break;
				}
			} 
		}
		return vlaue;
	}
	
	public static void addCookie(String cookieName,String data,int expires,HttpServletResponse response){
		Cookie cookie = new Cookie(cookieName,data);
		cookie.setMaxAge(expires);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
