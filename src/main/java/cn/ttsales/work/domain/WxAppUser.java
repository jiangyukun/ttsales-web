package cn.ttsales.work.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the wx_app_user database table.
 * 
 */
@Entity
@Table(name="wx_app_user")
public class WxAppUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name="app_user_id", unique=true, nullable=false, length=40)
	private String appUserId;

	private String appid;

	@Column(name="user_id")
	private String userId;

	@Column(name="wc_open_id")
	private String wcOpenId;

	public WxAppUser() {
	}

	public String getAppUserId() {
		return this.appUserId;
	}

	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}

	public String getAppid() {
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWcOpenId() {
		return this.wcOpenId;
	}

	public void setWcOpenId(String wcOpenId) {
		this.wcOpenId = wcOpenId;
	}

}