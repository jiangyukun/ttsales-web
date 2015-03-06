package cn.ttsales.work.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PerformanceRankDTO {
	@Id
	private String userCrossId;
	private String nickName;
	private int count;
	
	public PerformanceRankDTO() {
		super();
	}

	public PerformanceRankDTO(String userCrossId, String nickName,
			int count) {
		super();
		this.userCrossId = userCrossId;
		this.nickName = nickName;
		this.count = count;
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
}
