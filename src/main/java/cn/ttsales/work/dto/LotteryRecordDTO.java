package cn.ttsales.work.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LotteryRecordDTO {
	@Id
	private String lotteryId;
	private String bonusName;
	private String lotteryCash;
	private String lotteryTime;
	private String deptName;
	private String name;

	public String getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String getBonusName() {
		return bonusName;
	}

	public void setBonusName(String bonusName) {
		this.bonusName = bonusName;
	}

	public String getLotteryCash() {
		return lotteryCash;
	}

	public void setLotteryCash(String lotteryCash) {
		this.lotteryCash = lotteryCash;
	}

	public String getLotteryTime() {
		return lotteryTime;
	}

	public void setLotteryTime(String lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
