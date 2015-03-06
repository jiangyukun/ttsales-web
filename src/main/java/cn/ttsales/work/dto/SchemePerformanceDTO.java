package cn.ttsales.work.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SchemePerformanceDTO {
	
	@Id
	private String shememId;
	private int myTrasmitCount;
	private int myReadCount;
	private int myReversCount;
	private int myArriveCount;
	private int allTrasmitCount;
	private int allReadCount;
	private int allReversCount;
	private int allArriveCount;
	
	public SchemePerformanceDTO() {
		this.shememId = "null";
		this.myTrasmitCount = 0;
		this.myReadCount = 0;
		this.myReversCount = 0;
		this.myArriveCount = 0;
		this.allTrasmitCount = 0;
		this.allReadCount = 0;
		this.allReversCount = 0;
		this.allArriveCount = 0;
	}
	
	public String getShememId() {
		return shememId;
	}

	public void setShememId(String shememId) {
		this.shememId = shememId;
	}

	public int getMyTrasmitCount() {
		return myTrasmitCount;
	}

	public void setMyTrasmitCount(int myTrasmitCount) {
		this.myTrasmitCount = myTrasmitCount;
	}

	public int getMyReadCount() {
		return myReadCount;
	}

	public void setMyReadCount(int myReadCount) {
		this.myReadCount = myReadCount;
	}

	public int getMyReversCount() {
		return myReversCount;
	}

	public void setMyReversCount(int myReversCount) {
		this.myReversCount = myReversCount;
	}

	public int getMyArriveCount() {
		return myArriveCount;
	}

	public void setMyArriveCount(int myArriveCount) {
		this.myArriveCount = myArriveCount;
	}

	public int getAllTrasmitCount() {
		return allTrasmitCount;
	}

	public void setAllTrasmitCount(int allTrasmitCount) {
		this.allTrasmitCount = allTrasmitCount;
	}

	public int getAllReadCount() {
		return allReadCount;
	}

	public void setAllReadCount(int allReadCount) {
		this.allReadCount = allReadCount;
	}

	public int getAllReversCount() {
		return allReversCount;
	}

	public void setAllReversCount(int allReversCount) {
		this.allReversCount = allReversCount;
	}

	public int getAllArriveCount() {
		return allArriveCount;
	}

	public void setAllArriveCount(int allArriveCount) {
		this.allArriveCount = allArriveCount;
	}
}
