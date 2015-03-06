package cn.ttsales.work.wxapi.mp.pojo;

import java.util.Date;

import cn.ttsales.work.core.util.DateUtil;

public class AccessToken {

	// 获取到的凭证
	private String access_token;
	// 凭证有效时间，单位：秒
	private int expires_in;
	private Date refreshTime;
	
	private String refresh_token;
	private String openid;
	private String scope;

	@SuppressWarnings("unused")
	private AccessToken() {

	}

	public AccessToken(String access_token, int expires_in) {
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.refreshTime = DateUtil.getCurrentTime();
	}
	
	public AccessToken(String access_token, int expires_in,String refresh_token,String openid,String scope) {
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.refresh_token = refresh_token;
		this.openid = openid;
		this.scope = scope;
		this.refreshTime = DateUtil.getCurrentTime();
	}


	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	


	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String toString(){
		return "";
	}

	public Date getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}
}
