package cn.ttsales.work.wxapi.corp.pojo;

import java.util.Date;

import cn.ttsales.work.core.util.DateUtil;

public class AccessToken {

	// 获取到的凭证
	private String access_token;
	// 凭证有效时间，单位：秒
	private int expires_in;
	
	private Date refreshTime;

	@SuppressWarnings("unused")
	private AccessToken() {

	}

	public AccessToken(String access_token, int expires_in) {
		this.access_token = access_token;
		this.expires_in = expires_in;
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

	public Date getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}

}
