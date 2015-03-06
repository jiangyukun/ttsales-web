/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename BusReserveStoreDTO.java
 * @package cn.ttsales.work.dto
 * @author dandyzheng
 * @date 2014-9-17
 */
package cn.ttsales.work.dto;

import java.io.Serializable;

/**
 * 我的奖励页面对象
 * 
 * @author zhaoxiaobin
 * 
 */
public class MyPerformanceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String popularizeId;
	private String advertiserName;
	private String title;
	private int transmit01Count;
	private int transmit02Count;
	private int read01Count;
	private int read02Count;
	private int reserve01Count;
	private int reserve02Count;
	private int arrive01Count;
	private int arrive02Count;

	public MyPerformanceDTO() {
		super();
	}

	public String getPopularizeId() {
		return popularizeId;
	}

	public void setPopularizeId(String popularizeId) {
		this.popularizeId = popularizeId;
	}

	public String getAdvertiserName() {
		return advertiserName;
	}

	public void setAdvertiserName(String advertiserName) {
		this.advertiserName = advertiserName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTransmit01Count() {
		return transmit01Count;
	}

	public void setTransmit01Count(int transmit01Count) {
		this.transmit01Count = transmit01Count;
	}

	public int getTransmit02Count() {
		return transmit02Count;
	}

	public void setTransmit02Count(int transmit02Count) {
		this.transmit02Count = transmit02Count;
	}

	public int getRead01Count() {
		return read01Count;
	}

	public void setRead01Count(int read01Count) {
		this.read01Count = read01Count;
	}

	public int getRead02Count() {
		return read02Count;
	}

	public void setRead02Count(int read02Count) {
		this.read02Count = read02Count;
	}

	public int getReserve01Count() {
		return reserve01Count;
	}

	public void setReserve01Count(int reserve01Count) {
		this.reserve01Count = reserve01Count;
	}

	public int getReserve02Count() {
		return reserve02Count;
	}

	public void setReserve02Count(int reserve02Count) {
		this.reserve02Count = reserve02Count;
	}

	public int getArrive01Count() {
		return arrive01Count;
	}

	public void setArrive01Count(int arrive01Count) {
		this.arrive01Count = arrive01Count;
	}

	public int getArrive02Count() {
		return arrive02Count;
	}

	public void setArrive02Count(int arrive02Count) {
		this.arrive02Count = arrive02Count;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
