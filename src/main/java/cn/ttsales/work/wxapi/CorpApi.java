/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename AccessTokenInterfaceImpl.java
 * @package cn.ttsales.work.web.weixin
 * @author dandyzheng
 * @date 2014-8-12
 */
package cn.ttsales.work.wxapi;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.apache.log4j.Logger;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.exception.BusinessException;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.web.common.HttpRequestProxy;
import cn.ttsales.work.wxapi.corp.encrypt.Prpcrypt;
import cn.ttsales.work.wxapi.corp.encrypt.SHA1;
import cn.ttsales.work.wxapi.corp.encrypt.XMLParse;
import cn.ttsales.work.wxapi.corp.pojo.AccessToken;
import cn.ttsales.work.wxapi.corp.pojo.CreateDeptResult;
import cn.ttsales.work.wxapi.corp.pojo.Dept;
import cn.ttsales.work.wxapi.corp.pojo.DeptUserResult;
import cn.ttsales.work.wxapi.corp.pojo.User;
import cn.ttsales.work.wxapi.corp.pojo.UserResult;
import cn.ttsales.work.wxapi.corp.send.BaseSendMsg;
import cn.ttsales.work.wxapi.corp.send.MsgSendResult;
import cn.ttsales.work.wxapi.mp.pojo.JsApiTicket;

/**
 * @author
 * 
 */
public class CorpApi {
	private Logger log = Logger.getLogger(CorpApi.class);
	
	private static CorpApi API;
	
	public static CorpApi getInstance() {
		if (API == null )
			API = new CorpApi(); 
		return API;
	}
	
	private CorpApi() {
		
	}
	private AccessToken accessToken;
	
	
	/**
	 * 判断token是否过期或者未获取
	 * @return
	 */
	private boolean accessTokenIsExpires() {
		if (accessToken == null || accessToken.getAccess_token() == null || "".equals(accessToken.getAccess_token()))
			return true;
		Date currentTime = DateUtil.getCurrentTime();
		if ((currentTime.getTime()-accessToken.getRefreshTime().getTime()) > (accessToken.getExpires_in()-60)*1000)
			return true;
		return false;
	}
	/**
	 * 获取访问token
	 * @param corpAppId
	 * @param corpSecret
	 * @return
	 */
	public String getAccessToken(String corpAppId, String corpSecret) {
		
		if (this.accessTokenIsExpires()) {
			String getAccessTokenUrl = BundleUtil.getProperty(
					SASConstants.RESOURCE_WEIXIN, "wx.qy.url.getAccessToken",
					corpAppId, corpSecret);
			log.info("getAccessTokenUrl:" + getAccessTokenUrl);
			String result = "{}";
			
			HttpRequestProxy hrp = new HttpRequestProxy();
			try {
				result = hrp.doRequest(getAccessTokenUrl, null, null, "utf-8");
				log.info("result:" + result);
			} catch (Exception e1) {
				log.error(e1.getMessage());
				e1.printStackTrace();
			}
			JSONObject jsonObject = JsonUtil.fromStr(result);
			// 假设请求成功
			if (null != jsonObject) {
				try {
					this.accessToken = new AccessToken(
							jsonObject.getString("access_token"),
							jsonObject.getInt("expires_in"));
					log.info("accessToken:" + accessToken.getAccess_token());
				} catch (JSONException e) {
					accessToken = null;
					// 获取token失败
					StringBuilder errorLog = new StringBuilder();
					errorLog.append("获取token失败 errcode:").append(
							jsonObject.getString("errcode"));
					errorLog.append(" errmsg:").append(
							jsonObject.getString("errmsg"));
					log.error(errorLog.toString());
					throw e;
				}
			}
		}
		// System.out.println(accessToken.getToken());
		return accessToken.getAccess_token();
	}
	
	
	/**
	 * 通过code换取企业号用户id
	 */
	public String getUserId(String corpAppId, String corpSecret, String code, String agentId) {

		String userInfoUrl = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.qy.url.getUserInfo",
				this.getAccessToken(corpAppId, corpSecret), code, agentId);
		HttpRequestProxy hrp = new HttpRequestProxy();
		log.info("userInfoUrl:" + userInfoUrl);
		String result = "{}";
		try {
			result = hrp.doRequest(userInfoUrl, null, null, "utf-8");
			log.info("result:" + result);
		} catch (Exception e) {
			log.error("get userInfo error,url is [" + userInfoUrl + "]");
			e.printStackTrace();
		}
		JSONObject jsonObject = JsonUtil.fromStr(result);
		String userId = "";
		if (null != jsonObject) {
			try {
				userId = jsonObject.getString("UserId");
				log.info("userId:" + userId);
			} catch (JSONException e) {
				String errorcode = jsonObject.getString("errcode") ;
				if(errorcode.equals("46004")){
					return userId;
				}else{
					// 获取token失败
					StringBuilder errorLog = new StringBuilder();
					errorLog.append("获取userId失败 errcode:").append(
							jsonObject.getString("errcode"));
					errorLog.append(" errmsg:").append(
							jsonObject.getString("errmsg"));
					log.error(errorLog.toString());
					throw e;
				}
			}
		}
		return userId;

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
			String ticketRet= "{}";
			try {
				ticketRet = hrp.doRequest("https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?", postData, null,
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
	 * 回复消息给公众平台 1. 对要发送的消息进行AES-CBC加密； 2. 生成安全签名； 3. 将消息密文和安全签名打包成xml格式。
	 * 
	 * @param text 第三方需要发送的明文
	 * @return 返回码和第三方发给公众平台的xml消息
	 */
	public static Result toTencent(String text, String encodingAesKey, String nonce, String token,
			String appid, String timestamp){
		Prpcrypt pc = new Prpcrypt(encodingAesKey);

		// 加密
		Result encrypt = pc.encrypt(text, appid);
		if (encrypt.getCode() != 0) {
			return new Result(encrypt.getCode(), encrypt.getResult());
		}

		// 生成安全签名
		if (timestamp == "") {
			timestamp = Long.toString(System.currentTimeMillis());
		}

		Object[] signature = SHA1.getSHA1(token, timestamp, nonce, encrypt.getResult());
		if ((Integer) signature[0] != 0) {
			return new Result(encrypt.getCode(), "");
		}

		// System.out.println("发送给平台的签名是: " + signature[1].toString());
		// 生成发送的xml
		String result = XMLParse.generate(encrypt.getResult(), signature[1].toString(), timestamp,
				nonce);
		return new Result(0, result);
	}

	/**
	 * 公众平台发送消息给第三方 1. 利用收到的密文生成安全签名,进行签名验证; 2. 若验证通过，则提取xml中的加密消息； 3. 对消息进行解密。
	 * 
	 * @param text 第三方收到的xml格式加密消息
	 * @param msg_sign 第三方收到的签名
	 * @throws Exception 
	 */
	public Result fromTencent(String text, String encodingAesKey, String msgSignature,
			String token, String timestamp, String nonce, String appid){

		if (encodingAesKey.length() != 43) {
			return new Result(-40004, "");
		}

		// 密钥，公众账号的app secret
		Prpcrypt pc = new Prpcrypt(encodingAesKey);
		// 提取密文
		Object[] encrypt = XMLParse.extract(text);
		if ((Integer) encrypt[0] != 0) {
			return new Result((Integer) encrypt[0], "");
		}

		// 验证安全签名
		Object[] signature = SHA1.getSHA1(token, timestamp, nonce, encrypt[1].toString());
		if ((Integer) signature[0] != 0) {
			return new Result((Integer) signature[0], "");
		}

		// 和URL中的签名比较是否相等
		// System.out.println("第三方收到URL中的签名：" + msg_sign);
		// System.out.println("第三方校验签名：" + signature);
		if (!signature[1].equals(msgSignature)) {
			// System.out.println("签名错误 ");
			/* 不安全消息处理 */
			signature[0] = -40001;
			signature[1] = "";
			return new Result((Integer) signature[0], "");
		}
		// 解密
		Result result = pc.decrypt(encrypt[1].toString(), appid);
		return result;
	}
	/**
	 * 公众平台发送消息给第三方 1. 利用收到的密文生成安全签名,进行签名验证; 2. 若验证通过，则提取xml中的加密消息； 3. 对消息进行解密。
	 * 
	 * @param text 第三方收到的xml格式加密消息
	 * @param msg_sign 第三方收到的签名
	 */
	public Result verifySignature(String echostr, String encodingAesKey, String msgSignature,
			String token, String timestamp, String nonce, String appid){

		if (encodingAesKey.length() != 43) {
			return new Result(-40004, "");
		}

		// 密钥，公众账号的app secret
		Prpcrypt pc = new Prpcrypt(encodingAesKey);

		// 验证安全签名
		Object[] signature = SHA1.getSHA1(token, timestamp, nonce, echostr);
		if ((Integer) signature[0] != 0) {
			return new Result((Integer) signature[0], "");
		}

		// 和URL中的签名比较是否相等
		// System.out.println("第三方收到URL中的签名：" + msg_sign);
		// System.out.println("第三方校验签名：" + signature);
		if (!signature[1].equals(msgSignature)) {
			// System.out.println("签名错误 ");
			/* 不安全消息处理 */
			signature[0] = -40001;
			signature[1] = "";
			return new Result((Integer) signature[0], "");
		}
		// 解密
		Result result = pc.decrypt(echostr, appid);
		return result;
	}
	
	
	public String uploadMedia(String corpAppId, String corpSecret,PartSource partSource) {

		String uploadMediaUrl = BundleUtil.getProperty(
				SASConstants.RESOURCE_WEIXIN, "wx.qy.url.uploadMedia",
				this.getAccessToken(corpAppId, corpSecret), "image");
		log.info("uploadMedia:" + uploadMediaUrl);
		String result = "{}";
		String mediaId = null;
		HttpRequestProxy hrp = new HttpRequestProxy();
		try {
			result = hrp.postMultiParams(uploadMediaUrl, null, partSource,"utf-8");
			log.info("result:" + result);
		} catch (Exception e1) {
			log.error(e1.getMessage());
			e1.printStackTrace();
		}
		JSONObject jsonObject = JsonUtil.fromStr(result);
		// 假设请求成功
		if (null != jsonObject) {
			try {
				jsonObject.getString("type");
				mediaId = jsonObject.getString("media_id");
				jsonObject.getString("created_at");
				
			} catch (JSONException e) {

				// 获取token失败
				StringBuilder errorLog = new StringBuilder();
				errorLog.append("获取token失败 errcode:").append(
						jsonObject.getString("errcode"));
				errorLog.append(" errmsg:").append(
						jsonObject.getString("errmsg"));
				log.error(errorLog.toString());
				throw e;
			}
		}
		return mediaId;
	}
	
	/**
	 * 企业号主动发送消息
	 * @param corpAppId 
	 * @param corpSecret 
	 * @param reqMsg 继承至BaseReqMsg的消息BEAN，可是是文本、图文、媒体等等
	 * @return ReqResult
	 * @author dandyzheng
	 * @date 2014-9-15
	 * @see
	 */
	public<T extends BaseSendMsg> MsgSendResult sendMsg(String corpAppId,String corpSecret, T reqMsg){
		String accessToken = this.getAccessToken(corpAppId, corpSecret);
		String sendMsgUrl = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.qy.url.sendMsg",accessToken);
		log.info("sendMsgUrl:" + sendMsgUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "0");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doPostRequest(sendMsgUrl, JsonUtil.fromObject(reqMsg, new JsonConfig()).toString(), null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("发送消息失败:"+e.getMessage(),"SAS-00005");
		}
		return (MsgSendResult)JSONObject.toBean(jsonObject, MsgSendResult.class);
	}
	
	public CreateDeptResult createDept(String corpAppId,String corpSecret,Dept dept){
		String accessToken = this.getAccessToken(corpAppId, corpSecret);
		String createDeptUrl = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.qy.url.createDept",accessToken);
		log.info("createDeptUrl:" + createDeptUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "0");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doPostRequest(createDeptUrl, dept.toCreateDept(), null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("创建企业号部门失败:"+e.getMessage(),"SAS-00006");
		}
		return (CreateDeptResult)JSONObject.toBean(jsonObject, CreateDeptResult.class);
	}
	
	public BaseResult updateDept(String corpAppId,String corpSecret,Dept dept){
		String accessToken = this.getAccessToken(corpAppId, corpSecret);
		String updateDeptUrl = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.qy.url.updateDept",accessToken);
		log.info("updateDeptUrl:" + updateDeptUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "0");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doPostRequest(updateDeptUrl, dept.toUpdateDept(), null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("更新企业号部门失败:"+e.getMessage(),"SAS-00006");
		}
		return (BaseResult)JSONObject.toBean(jsonObject, BaseResult.class);
	}
	
	public BaseResult deleteDept(String corpAppId,String corpSecret,String deptid){
		String accessToken = this.getAccessToken(corpAppId, corpSecret);
		String deleteDeptUrl = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.qy.url.deleteDept",accessToken,deptid);
		log.info("deleteDeptUrl:" + deleteDeptUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "0");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doRequest(deleteDeptUrl, null, null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("删除企业号部门失败:"+e.getMessage(),"SAS-00006");
		}
		return (BaseResult)JSONObject.toBean(jsonObject, BaseResult.class);
	}
	
	
	public BaseResult createUser(String corpAppId,String corpSecret,User user){
		String accessToken = this.getAccessToken(corpAppId, corpSecret);
		String createUserUrl = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.qy.url.createUser",accessToken);
		log.info("createUserUrl:" + createUserUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "0");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doPostRequest(createUserUrl, user.toCreateUser(), null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("创建企业号用户失败:"+e.getMessage(),"SAS-00006");
		}
		return (BaseResult)JSONObject.toBean(jsonObject, BaseResult.class);
	}
	
	public BaseResult updateUser(String corpAppId,String corpSecret,User user){
		String accessToken = this.getAccessToken(corpAppId, corpSecret);
		String updateUserUrl = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.qy.url.updateUser",accessToken);
		log.info("updateUserUrl:" + updateUserUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "0");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doPostRequest(updateUserUrl, user.toUpdateUser(), null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("更新企业号用户失败:"+e.getMessage(),"SAS-00006");
		}
		return (BaseResult)JSONObject.toBean(jsonObject, BaseResult.class);
	}
	
	public BaseResult deleteUser(String corpAppId,String corpSecret,String userid){
		String accessToken = this.getAccessToken(corpAppId, corpSecret);
		String deleteUserUrl = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.qy.url.deleteUser",accessToken,userid);
		log.info("deleteUserUrl:" + deleteUserUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "0");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doRequest(deleteUserUrl, null, null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("删除企业号用户失败:"+e.getMessage(),"SAS-00006");
		}
		return (BaseResult)JSONObject.toBean(jsonObject, BaseResult.class);
	}
	
	public UserResult getUser(String corpAppId,String corpSecret,String userid){
		String accessToken = this.getAccessToken(corpAppId, corpSecret);
		String getUserUrl = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.qy.url.getUser",accessToken,userid);
		log.info("getUser:" + getUserUrl);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "0");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doRequest(getUserUrl, null, null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("获取企业号用户失败:"+e.getMessage(),"SAS-00006");
		}
		return (UserResult)JSONObject.toBean(jsonObject, UserResult.class);
	}
	
	public DeptUserResult getDeptUsers(String corpAppId,String corpSecret,String departmentId,String fetchChild,String stauts){
		String accessToken = this.getAccessToken(corpAppId, corpSecret);
		String listDeptUsers = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, "wx.qy.url.listDeptUsers",accessToken,departmentId,fetchChild,stauts);
		log.info("getDeptUsersUrl:" + listDeptUsers);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "0");
		try {
			HttpRequestProxy hrp = new HttpRequestProxy();
			String result = hrp.doRequest(listDeptUsers, null, null, "utf-8");
			log.info("result:" + result);
			jsonObject = JsonUtil.fromStr(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException("获取用户列表失败:"+e.getMessage(),"SAS-00006");
		}
		return (DeptUserResult)JSONObject.toBean(jsonObject, DeptUserResult.class);
	}
	
	


	

	
	public static void main(String[] args) throws InterruptedException, ParseException {
//		CorpApi c = CorpApi.getInstance();
//		MsgSendResult r = c.sendMsg("1", "1", new TextSendMsg());
//		System.out.println(r.toString());

	}
}
