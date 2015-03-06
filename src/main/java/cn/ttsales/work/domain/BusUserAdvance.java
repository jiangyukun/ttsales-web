package cn.ttsales.work.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the bus_user_advance database table.
 * 
 */
@Entity
@Table(name = "bus_user_advance")
public class BusUserAdvance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "user_advance_id")
	private String userAdvanceId;

	@Column(name = "create_time")
	private String createTime;

	private float money;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_type")
	private String userType;
	
	@Column(name = "sp_billno")
	private String spBillno;
	
	@Column(name = "has_receive")
	private int hasReceive;
	

	public BusUserAdvance() {
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

	public String getUserAdvanceId() {
		return userAdvanceId;
	}

	public void setUserAdvanceId(String userAdvanceId) {
		this.userAdvanceId = userAdvanceId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSpBillno() {
		return spBillno;
	}

	public void setSpBillno(String spBillno) {
		this.spBillno = spBillno;
	}

	public int getHasReceive() {
		return hasReceive;
	}

	public void setHasReceive(int hasReceive) {
		this.hasReceive = hasReceive;
	}

}