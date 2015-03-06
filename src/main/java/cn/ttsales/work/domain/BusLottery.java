package cn.ttsales.work.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the sys_lottery database table.
 * 
 */
@Entity
@Table(name = "bus_lottery")
public class BusLottery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "lottery_id")
	private String lotteryId;

	@Column(name = "dept_id")
	private String deptId;

	@Column(name = "bonus_id")
	private String bonusId;

	@Column(name = "money")
	private float money;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_type")
	private String userType;

	@Column(name = "rank")
	private int rank;

	@Column(name = "has_lottery")
	private int hasLottery;

	@Column(name = "lottery_type")
	private String lotteryType;

	@Column(name = "scheme_id")
	private String schemeId;

	@Column(name = "lottery_time")
	private String lotteryTime;

	public BusLottery() {
	}

	public String getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getBonusId() {
		return bonusId;
	}

	public void setBonusId(String bonusId) {
		this.bonusId = bonusId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getHasLottery() {
		return hasLottery;
	}

	public void setHasLottery(int hasLottery) {
		this.hasLottery = hasLottery;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getLotteryTime() {
		return lotteryTime;
	}

	public void setLotteryTime(String lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

}