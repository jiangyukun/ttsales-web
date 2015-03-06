/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename RepTransmitReportDTO.java
 * @package cn.ttsales.work.dto
 * @author dandyzheng
 * @date 2014-8-29
 */
package cn.ttsales.work.dto;

import java.util.List;

import cn.ttsales.work.domain.BusPopularize;

/**
 * @author dandyzheng
 *
 */
public class RepPopularizeTransmitReportDTO {
	private BusPopularize busPopularize;
	private List<RepTransmitReportDTO> repTransmitReportDTOs;
	
	public BusPopularize getBusPopularize() {
		return busPopularize;
	}
	public void setBusPopularize(BusPopularize busPopularize) {
		this.busPopularize = busPopularize;
	}
	public List<RepTransmitReportDTO> getRepTransmitReportDTOs() {
		return repTransmitReportDTOs;
	}
	public void setRepTransmitReportDTOs(
			List<RepTransmitReportDTO> repTransmitReportDTOs) {
		this.repTransmitReportDTOs = repTransmitReportDTOs;
	}
	
}
