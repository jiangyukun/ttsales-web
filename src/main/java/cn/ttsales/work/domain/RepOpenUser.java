package cn.ttsales.work.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the rep_transmit database table.
 * 
 */
@Entity
@Table(name = "rep_open_user")
public class RepOpenUser extends cn.ttsales.work.domain.common.Common implements
		Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "open_id", unique = true, nullable = false, length = 40)
	private String openId;

	@Column(name = "sex", length = 2)
	private String sex;

	@Column(name = "nick_name", length = 50)
	private String nickName;

	@Column(name = "province", length = 30)
	private String province;

	@Column(name = "city", length = 50)
	private String city;

	@Column(name = "country", length = 10)
	private String country;

	@Column(name = "head_img_url", length = 200)
	private String headImgUrl;

	@Column(name = "privilege", length = 100)
	private String privilege;

	@Column(name = "subscribe_time")
	private String subscribeTime;

	@Column(name = "subscribe_state")
	private String subscribeState;
	
	@Column(name = "unsubscribe_time")
	private String unsubscribeTime;

	@Column(name = "has_agreement")
	private int hasAgreement;

	public RepOpenUser() {
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

	public int getHasAgreement() {
		return hasAgreement;
	}

	public void setHasAgreement(int hasAgreement) {
		this.hasAgreement = hasAgreement;
	}

	public String getSubscribeState() {
		return subscribeState;
	}

	public void setSubscribeState(String subscribeState) {
		this.subscribeState = subscribeState;
	}

	public String getUnsubscribeTime() {
		return unsubscribeTime;
	}

	public void setUnsubscribeTime(String unsubscribeTime) {
		this.unsubscribeTime = unsubscribeTime;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	 

}