package cn.ttsales.work.wxapi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.exception.BusinessException;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.web.common.HttpRequestProxy;
import cn.ttsales.work.wxapi.mp.pojo.AccessToken;
import cn.ttsales.work.wxapi.mp.pojo.JsApiTicket;
import cn.ttsales.work.wxapi.mp.pojo.Menu;
import cn.ttsales.work.wxapi.mp.pojo.OpenUserInfo;
import cn.ttsales.work.wxapi.mp.pojo.Menu.KeyButton;
import cn.ttsales.work.wxapi.mp.pojo.Menu.KeyButtonType;
import cn.ttsales.work.wxapi.mp.pojo.Menu.ParentButton;
import cn.ttsales.work.wxapi.mp.pojo.Menu.ViewButton;
import cn.ttsales.work.wxapi.mp.send.TemplateSendMsg;
import cn.ttsales.work.wxapi.mp.send.TemplateSendResult;

public class MpApi {

	private Logger log = Logger.getLogger(MpApi.class);

	private static MpApi API;

	private MpApi() {

	}

	public static MpApi getInstance() {
		if (API == null)
			API = new MpApi();
		return API;
	}

	public String getRedirectCodeUrl(String appId, String redirectUri,
			String responseType, String scope, String state) {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.url.getCode", appId, redirectUri, responseType, scope,
				state);
	}

	/**
	 * 获取公众号访问凭证
	 * 
	 * @return
	 */

	private AccessToken accessToken;

	/**
	 * 判断token是否过期或者未获取
	 * 
	 * @return
	 */
	private boolean accessTokenIsExpires() {
		if (accessToken == null || accessToken.getAccess_token() == null
				|| "".equals(accessToken.getAccess_token()))
			return true;
		Date currentTime = DateUtil.getCurrentTime();
		if ((currentTime.getTime() - accessToken.getRefreshTime().getTime()) > (accessToken
				.getExpires_in() - 60)*1000)
			return true;
		return false;
	}

	public String getAccessToken(String appId, String secret) {
		if (this.accessTokenIsExpires()) {
			String getAccessTokenUrl = BundleUtil.getProperty(
					SASConstants.RESOURCE_WEIXIN, "wx.mp.url.getAccessToken",
					"client_credential", appId, secret);
			log.info("getAccessToken:" + getAccessTokenUrl);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("errcode", "00000");
			try {
				HttpRequestProxy hrp = new HttpRequestProxy();
				String result = hrp.doRequest(getAccessTokenUrl, null, null,
						"utf-8");
				log.info("result:" + result);
				jsonObject = JsonUtil.fromStr(result);
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
			if (jsonObject.get("errcode") != null
					&& !"00000".equals(jsonObject.getString("errcode"))) {// 失败
				throw new BusinessException("获取accessToken失败:"
						+ jsonObject.toString(), "SAS-00001");
			}

			this.accessToken = new AccessToken(
					jsonObject.getString("access_token"),
					jsonObject.getInt("expires_in"));
			log.info("accessToken:" + accessToken.getAccess_token());
		}
		return accessToken.getAccess_token();
	}
	
	
	/**
	 * 公众号用于调用微信JS接口的临时票据
	 */
	private JsApiTicket jsapiTicket;
	
	/**
	 * 判断jsapiTicket是否过期或者未获取
	 * 
	 * @return
	 */
	private boolean jsapiTicketIsExpires() {
		if (jsapiTicket == null || jsapiTicket.getJsapi_ticket() == null
				|| "".equals(jsapiTicket.getJsapi_ticket())){
			return true;
		}
		Date currentTime = DateUtil.getCurrentTime();
		if ((currentTime.getTime() - jsapiTicket.getRefreshTime().getTime()) > jsapiTicket.getExpires_in()*1000){
			return true;
		}
		return false;
	}

	public String getJsApiTicket(String appId, String secret) {
		if(this.jsapiTicketIsExpires()){
			String accessToken = this.getAccessToken(appId, secret);	
			JSONObject ticketJson = new JSONObject();
			HttpRequestProxy hrp = new HttpRequestProxy();
	        Map<String,String> postData = new HashMap<String, String>();
	        postData.put("access_token", accessToken);
	        postData.put("type", "jsapi");
			String ticketRet= "{}";
			try {
				ticketRet = hrp.doRequest("https://api.weixin.qq.com/cgi-bin/ticket/getticket?", postData, null,
						"utf-8");
				ticketJson = JsonUtil.fromStr(ticketRet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!"0".equals(ticketJson.getString("errcode"))) {// 失败
				throw new BusinessException("获取JsApiTicket失败:"
						+ ticketJson.toString(), "SAS-00001");
			}
			this.jsapiTicket = new JsApiTicket(
					ticketJson.getString("ticket"),
					ticketJson.getInt("expires_in"));
			log.info("jsapiTicket:" + jsapiTicket.getJsapi_ticket());
		}
		return jsapiTicket.getJsapi_ticket();
	}

	/**
	 * 通过code换取oAuth2的访问凭证,scope为“snsapi_base”的调用该方法
	 * 
	 * @param code
	 * @return
	 */
	public AccessToken getOauth2AccessToken(String appId, String secret,
			String code) {

		String getAccessTokenUrl = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.mp.url.getOauth2AccessToken",
				appId, secret, code, "authorization_code");
		log.info("getOauth2AccessToken:" + getAccessTokenUrl);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "00000");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doRequest(getAccessTokenUrl, null, null,
					"utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		if (jsonObject.get("errcode") != null
				&& !"00000".equals(jsonObject.getString("errcode"))) {// 失败
			throw new BusinessException("获取accessToken失败:"
					+ jsonObject.toString(), "SAS-00001");
		}

		AccessToken accessToken = new AccessToken(
				jsonObject.getString("access_token"),
				jsonObject.getInt("expires_in"));
		accessToken.setOpenid(jsonObject.getString("openid"));
		accessToken.setRefresh_token(jsonObject.getString("refresh_token"));
		accessToken.setScope(jsonObject.getString("scope"));
		log.info("accessToken:" + accessToken.getAccess_token());
		return accessToken;
	}

	/**
	 * 通过code换取用户信息,scope为“snsapi_userinfo”的调用该方法
	 * 
	 * @param code
	 * @return
	 */
	public OpenUserInfo getUserinfo(String appId, String secret, String code) {
		AccessToken accessToken = this.getOauth2AccessToken(appId, secret, code);
		// accessToken =
		// this.refreshOauth2AccessToken(accessToken.getRefresh_token());
		return this.getUserinfoByAccessToken(accessToken.getAccess_token(),
				accessToken.getOpenid(), "zh_CN");
	}
	
	/**
	 * scope为“snsapi_base”的调用该方法
	 * 
	 * @param code
	 * @return
	 */
	public OpenUserInfo getBaseUserinfo(String appId, String secret,String openId) {
		String accessToken = this.getAccessToken(appId, secret);
		
		String getUserInfoBaseUrl = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.mp.url.getUserInfoBase",
				accessToken, openId, "zh_CN");
		log.info("getUserInfoBaseUrl:" + getUserInfoBaseUrl);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "00000");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doRequest(getUserInfoBaseUrl, null, null,
					"utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		if (jsonObject.get("errcode") != null
				&& !"00000".equals(jsonObject.getString("errcode"))) {// 失败
			throw new BusinessException("获取UserInfoBase失败:"
					+ jsonObject.toString(), "SAS-00001");
		}
		
		OpenUserInfo openUserInfo = new OpenUserInfo();
		openUserInfo.setOpenId(jsonObject.getString("openid"));
		openUserInfo.setCity(jsonObject.getString("city"));
		openUserInfo.setCountry(jsonObject.getString("country"));
		openUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
		openUserInfo.setNickName(jsonObject.getString("nickname"));
		openUserInfo.setSex(jsonObject.getString("sex"));
		openUserInfo.setProvince(jsonObject.getString("province"));
//		openUserInfo.setPrivilege(jsonObject.getJSONArray("privilege").toString());
		try {
			long time = Long.valueOf(jsonObject.getString("subscribe_time"))*1000;
			Date day = new Date(time);
			openUserInfo.setSubscribeTime(DateUtil.getDateTimeString(day));  
		} catch (Exception e) {
			openUserInfo.setSubscribeTime(null);
		}
		return openUserInfo;
	}

	@SuppressWarnings("unused")
	private AccessToken refreshOauth2AccessToken(String appId,
			String refreshToken) {
		String refreshAccessTokenUrl = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN,
				"wx.mp.url.refreshOauth2AccessToken", appId, "refresh_token",
				refreshToken);
		log.info("refreshAccessTokenUrl:" + refreshAccessTokenUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "00000");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doRequest(refreshAccessTokenUrl, null, null,
					"utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		if (jsonObject.get("errcode") != null
				&& !"00000".equals(jsonObject.getString("errcode"))) {// 失败
			throw new BusinessException("刷新refreshAccessToken失败:"
					+ jsonObject.toString(), "SAS-00002");
		}

		AccessToken accessToken = new AccessToken(
				jsonObject.getString("access_token"),
				jsonObject.getInt("expires_in"));
		accessToken.setOpenid(jsonObject.getString("openid"));
		log.info("accessToken:" + accessToken.getAccess_token());
		log.info("openid:" + accessToken.getOpenid());
		return accessToken;
	}

	private OpenUserInfo getUserinfoByAccessToken(String accessToken,
			String openid, String lang) {
		String getUserInfoUrl = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.mp.url.getUserInfo",
				accessToken, openid, lang);
		log.info("getUserInfoUrl:" + getUserInfoUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "00000");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doRequest(getUserInfoUrl, null, null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		if (jsonObject.get("errcode") != null
				&& !"00000".equals(jsonObject.getString("errcode"))) {// 失败
			throw new BusinessException("刷新refreshAccessToken失败:"
					+ jsonObject.toString(), "SAS-00003");
		}

		OpenUserInfo openUserInfo = new OpenUserInfo();
		openUserInfo.setOpenId(jsonObject.getString("openid"));
		openUserInfo.setCity(jsonObject.getString("city"));
		openUserInfo.setCountry(jsonObject.getString("country"));
		openUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
		openUserInfo.setNickName(jsonObject.getString("nickname"));
		openUserInfo.setSex(jsonObject.getString("sex"));
		openUserInfo.setProvince(jsonObject.getString("province"));
	//	openUserInfo.setPrivilege(jsonObject.getJSONArray("privilege").toString());
		try {
			long time = Long.valueOf(jsonObject.getString("subscribe_time"))*1000;
			Date day = new Date(time);
			openUserInfo.setSubscribeTime(DateUtil.getDateTimeString(day));  
		} catch (Exception e) {
			openUserInfo.setSubscribeTime(null);
		}
		return openUserInfo;
	}

	/*
	 * {{first.DATA}} 上门服务时间：{{keynote1.DATA}} 联系电话：{{keynote2.DATA}}
	 * 微信号：{{keynote3.DATA}} {{remark.DATA}}
	 */

	@SuppressWarnings("rawtypes")
	public <T extends TemplateSendMsg> TemplateSendResult sendTemplateMsg(
			String appId, String secret, T templateReqMsg) {
		String accessToken = this.getAccessToken(appId, secret);
		String sendTemplateMsgUrl = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.mp.url.sendTemplateMsg",
				accessToken);
		log.info("sendTemplateMsgUrl:" + sendTemplateMsgUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "0");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doPostRequest(sendTemplateMsgUrl, JsonUtil
					.fromObject(templateReqMsg, new JsonConfig()).toString(),
					null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("发送模版消息失败:" + e.getMessage(),
					"SAS-00004");
		}
		return (TemplateSendResult) JSONObject.toBean(jsonObject,
				TemplateSendResult.class);
	}

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其它值表示失败
	 */
	public String createMenu(String appId, String secret, Menu menu) {

		// 拼装创建菜单的url
		String url = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.mp.url.createMenu", this.getAccessToken(appId, secret));
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		String result = "-1";
		try {
			result = new HttpRequestProxy().doPostRequest(url, jsonMenu, null,
					"utf-8");
			log.info("result:" + result);
			JSONObject jsonObject = JsonUtil.fromStr(result);
			if ("0".equals(jsonObject.getString("errcode")))
				log.info("Create menu successed!");
			else
				log.error("Create menu failed!errcode:"
						+ jsonObject.getString("errcode") + "; errmsg:"
						+ jsonObject.getString("errmsg"));
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 验证公众号签名有效性
	 * @param token
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public boolean verifySignature(String token, String signature, String timestamp,
			String nonce) {
		if (token==null || timestamp==null || nonce==null)
			return false;
		String[] arr = new String[] { token, timestamp, nonce };
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = "";
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			for (int i = 0; i < digest.length; i++) {
				tmpStr += byteToHexStr(digest[i]);
			}
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
		content = null;
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}
	
	public String urlToShort(String appId, String secret, String longUrlText) {
		String accessToken = this.getAccessToken(appId, secret);
		String longToShortUrl = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.mp.url.longToShortUrl",accessToken);

			
		HttpRequestProxy hrp = new HttpRequestProxy();
		
		String jsonText = "{\"action\":\"long2short\",\"long_url\":\""+longUrlText+"\"}";
		String result ="";
		try {
			result = hrp.doPostRequest(longToShortUrl, jsonText, null, "UTF-8");

		} catch (Exception e) {
			result = "{\"errcode\":-1,\"errmsg\":\""+e.getMessage()+"\"}";
		}	
		return result;	
			
	
	}
	
	/**
	 ** 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
	
	public static void main(String[] args) {

		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.mp.id");
		String secret = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.mp.secret");
		
		Menu menu = new Menu();

		menu.addButton(
				new ParentButton("发图")
						.addSubButton(
								new KeyButton(KeyButtonType.PIC_WEIXIN, "相册发图",
										"PIC_WEIXIN_1"))
						.addSubButton(
								new KeyButton(KeyButtonType.PIC_PHOTO_OR_ALBUM,
										"拍照或相册发图", "PIC_PHOTO_OR_ALBUM_1"))
						.addSubButton(
								new KeyButton(KeyButtonType.PIC_SYSPHOTO,
										"拍照发图", "PIC_SYSPHOTO_1")))
				.addButton(
						new ParentButton("扫码").addSubButton(
								new KeyButton(KeyButtonType.SCANCODE_PUSH,
										"扫码", "SCANCODE_PUSH_1")).addSubButton(
								new KeyButton(KeyButtonType.SCANCODE_WAITMSG,
										"扫码等待消息", "SCANCODE_WAITMSG_1")))
				.addButton(
						new ParentButton("其它")
								.addSubButton(
										new KeyButton(
												KeyButtonType.LOCATION_SELECT,
												"地理位置", "LOCATION_1"))
								.addSubButton(
										new ViewButton("链接", "http://m.baidu.com"))
								.addSubButton(
										new KeyButton(KeyButtonType.CLICK,
												"按钮", "CLICK_1")));

		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
		String result = MpApi.getInstance().createMenu(appId, secret, menu);
		System.out.println(result);

	}
}
