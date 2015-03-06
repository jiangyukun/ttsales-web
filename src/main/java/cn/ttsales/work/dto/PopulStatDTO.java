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
 * @author dandyzheng
 * 
 */

public class PopulStatDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String schemeId;
	private String date;
	private int readCount;
	private int transmitCount;
	private int reserveCount;
	private int arriveCount;

	public PopulStatDTO() {
		super();
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getTransmitCount() {
		return transmitCount;
	}

	public void setTransmitCount(int transmitCount) {
		this.transmitCount = transmitCount;
	}

	public int getReserveCount() {
		return reserveCount;
	}

	public void setReserveCount(int reserveCount) {
		this.reserveCount = reserveCount;
	}

	public int getArriveCount() {
		return arriveCount;
	}

	public void setArriveCount(int arriveCount) {
		this.arriveCount = arriveCount;
	}

}
