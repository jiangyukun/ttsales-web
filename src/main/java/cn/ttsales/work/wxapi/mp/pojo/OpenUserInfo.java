/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename MpUserInfo.java
 * @package cn.ttsales.work.web.wxapi
 * @author dandyzheng
 * @date 2014-8-15
 */
package cn.ttsales.work.wxapi.mp.pojo;


/**
 * @author dandyzheng
 *
 */
public class OpenUserInfo {
	
	private String openId;

	private String sex;

	private String nickName;

	private String province;

	private String city;
	
	private String country;
	
	private String headImgUrl;
	
	private String privilege;
	
	private String subscribeTime;
	
	private String subscribe;
	
	public OpenUserInfo(){
		
	}
	
	public OpenUserInfo(String openId, String sex, String nickName,
			String province, String city, String country, String headImgUrl,
			String privilege) {
		this.openId = openId;
		this.sex = sex;
		this.nickName = nickName;
		this.province = province;
		this.city = city;
		this.country = country;
		this.headImgUrl = headImgUrl;
		this.privilege = privilege;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	
	
	
}
