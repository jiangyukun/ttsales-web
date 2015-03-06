/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename RepTransmitReportDTO.java
 * @package cn.ttsales.work.dto
 * @author dandyzheng
 * @date 2014-8-29
 */
package cn.ttsales.work.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author dandyzheng
 *
 */
@Entity
public class RepTransmitReportDTO {
	private static final long serialVersionUID = 1L;
	@Id
	private String traType;
	private int transmitCount;
	private int readCount;
	private String money;
	
	public RepTransmitReportDTO(){
		
	}
	
	public RepTransmitReportDTO(int transmitCount, int readCount,
			String traType) {
		this.transmitCount = transmitCount;
		this.readCount = readCount;
		this.traType = traType;
	}
	

	public int getTransmitCount() {
		return transmitCount;
	}
	public void setTransmitCount(int transmitCount) {
		this.transmitCount = transmitCount;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getTraType() {
		return traType;
	}
	public void setTraType(String traType) {
		this.traType = traType;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
