package cn.ttsales.work.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the bus_user_bonus database table.
 * 
 */
@Entity
@Table(name = "bus_user_bonus")
public class BusUserBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "user_bonus_id")
	private String userBonusId;

	@ManyToOne
	@JoinColumn(name = "bonus_id")
	private BusBonus busBonus;

	@Column(name = "create_time")
	private String createTime;

	private float money;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_type")
	private String userType;

	public BusUserBonus() {
	}

	public String getUserBonusId() {
		return this.userBonusId;
	}

	public void setUserBonusId(String userBonusId) {
		this.userBonusId = userBonusId;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public float getMoney() {
		return this.money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public BusBonus getBusBonus() {
		return busBonus;
	}

	public void setBusBonus(BusBonus busBonus) {
		this.busBonus = busBonus;
	}

}