/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename BusReserveStoreDTO.java
 * @package cn.ttsales.work.dto
 * @author dandyzheng
 * @date 2014-9-17
 */
package cn.ttsales.work.dto;

 

public class TrackPopularizeDTO  {
 	 private String userCrossId;
	 private String nickName;
	 private int count;
	 private String type;
	 
	 
	public TrackPopularizeDTO() {
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
 
	
}
