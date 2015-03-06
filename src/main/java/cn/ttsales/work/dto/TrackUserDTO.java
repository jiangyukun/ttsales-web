/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename BusReserveStoreDTO.java
 * @package cn.ttsales.work.dto
 * @author dandyzheng
 * @date 2014-9-17
 */
package cn.ttsales.work.dto;

public class TrackUserDTO {
	private String transmitId;
	private String pTransmitId;
	private String userCrossId;
	private String nickName;
	private String headUrl;
	private String traTime;
	private String gender;

	public TrackUserDTO() {
		super();
	}

	public String getUserCrossId() {
		return userCrossId;
	}

	public void setUserCrossId(String userCrossId) {
		this.userCrossId = userCrossId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getTransmitId() {
		return transmitId;
	}

	public void setTransmitId(String transmitId) {
		this.transmitId = transmitId;
	}

	public String getpTransmitId() {
		return pTransmitId;
	}

	public void setpTransmitId(String pTransmitId) {
		this.pTransmitId = pTransmitId;
	}

	public String getTraTime() {
		return traTime;
	}

	public void setTraTime(String traTime) {
		this.traTime = traTime;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
