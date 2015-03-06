package cn.ttsales.work.dto;

public class PopulStatSumDTO {
	private String schemeId;
	private int allReadCount;
	private int allTransmitCount;
	private int allReserveCount;
	private int allArriveCount;

	public PopulStatSumDTO() {
		super();
	}

	public PopulStatSumDTO(String schemeId, int allReadCount,
			int allTransmitCount, int allReserveCount, int allArriveCount) {
		super();
		this.schemeId = schemeId;
		this.allReadCount = allReadCount;
		this.allTransmitCount = allTransmitCount;
		this.allReserveCount = allReserveCount;
		this.allArriveCount = allArriveCount;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public int getAllReadCount() {
		return allReadCount;
	}

	public void setAllReadCount(int allReadCount) {
		this.allReadCount = allReadCount;
	}

	public int getAllTransmitCount() {
		return allTransmitCount;
	}

	public void setAllTransmitCount(int allTransmitCount) {
		this.allTransmitCount = allTransmitCount;
	}

	public int getAllReserveCount() {
		return allReserveCount;
	}

	public void setAllReserveCount(int allReserveCount) {
		this.allReserveCount = allReserveCount;
	}

	public int getAllArriveCount() {
		return allArriveCount;
	}

	public void setAllArriveCount(int allArriveCount) {
		this.allArriveCount = allArriveCount;
	}

}
