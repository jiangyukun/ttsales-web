package cn.ttsales.work.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * The persistent class for the rep_transmit database table.
 * 
 */
@Entity
public class RepTransmitDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String transmitId;

	private String popularizeId;

	private String pTransmitId;

	private String traTime;

	private String traType;

	private String userCrossId;

	private String sno;
	private int depth;
	
	private String allTraCount;
	private String allReadCount;
	private String currTraCount;
	private String currReadCount = "0";
	private boolean hasChildren;
	private String nickName;

	public String getTransmitId() {
		return this.transmitId;
	}

	public void setTransmitId(String transmitId) {
		this.transmitId = transmitId;
	}

	public String getPopularizeId() {
		return this.popularizeId;
	}

	public void setPopularizeId(String popularizeId) {
		this.popularizeId = popularizeId;
	}

	public String getPTransmitId() {
		return this.pTransmitId;
	}

	public void setPTransmitId(String pTransmitId) {
		this.pTransmitId = pTransmitId;
	}

	public String getTraTime() {
		return this.traTime;
	}

	public void setTraTime(String traTime) {
		this.traTime = traTime;
	}

	public String getTraType() {
		return this.traType;
	}

	public void setTraType(String traType) {
		this.traType = traType;
	}

	public String getUserCrossId() {
		return this.userCrossId;
	}

	public void setUserCrossId(String userCrossId) {
		this.userCrossId = userCrossId;
	}

	
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	

	public String getAllTraCount() {
		return allTraCount;
	}

	public void setAllTraCount(String allTraCount) {
		this.allTraCount = allTraCount;
	}

	public String getAllReadCount() {
		return allReadCount;
	}

	public void setAllReadCount(String allReadCount) {
		this.allReadCount = allReadCount;
	}

	public String getCurrTraCount() {
		return currTraCount;
	}

	public void setCurrTraCount(String currTraCount) {
		this.currTraCount = currTraCount;
	}

	public String getCurrReadCount() {
		return currReadCount;
	}

	public void setCurrReadCount(String currReadCount) {
		this.currReadCount = currReadCount;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String toString(){
		return userCrossId+":{allTraCount:"+allTraCount+"\n"+
				"allReadCount:"+allReadCount+"\n"+
				"currTraCount:"+currTraCount+"\n"+
				"currReadCount:"+currReadCount+"\n";
	}

}