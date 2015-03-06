package cn.ttsales.work.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyCustomerDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String reserveId;
	private String headImgUrl;
	private String name;
	private String pinyin;
	private String pinyinHead;
	private String phoneNumber;
	private String arriveTime;
	private String sex;

	public MyCustomerDTO() {
		super();
	}
	
	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}
	
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getPinyinHead() {
		return pinyinHead;
	}

	public void setPinyinHead(String pinyinHead) {
		this.pinyinHead = pinyinHead;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
