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
 * The persistent class for the wx_user_bonus database table.
 * 
 */
@Entity
@Table(name = "wx_user_bonus")
public class WxUserBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "user_bonus_id", unique = true, nullable = false, length = 40)
	private String userBonusId;

	private String appid;

	@Column(name = "user_bonus_code")
	private String userBonusCode;

	@ManyToOne
	@JoinColumn(name = "bonus_id")
	private WxAppBonus wxAppBonus;

	@Column(name = "bonus_status")
	private String bonusStatus;

	@Column(name = "bonus_type")
	private String bonusType;

	@Column(name = "create_time")
	private String createTime;

	@Column(name = "dead_time")
	private String deadTime;

	@Column(name = "is_push")
	private int isPush;

	private float money;
	
	@Column(name = "money_auth")
	private String moneyAuth;
 
	@Column(name = "owner_id")
	private String ownerId;

	private String operation;

	@Column(name = "p_user_bonus_id")
	private String pUserBonusId;

	@Column(name = "operation_time")
	private String operationTime;

	private int version;

	@Column(name = "receive_verify")
	private String receiveVerify;

	@Column(name = "verify_url")
	private String verifyUrl;

	@Column(name = "share_money")
	private float shareMoney;

	@Column(name = "share_money_auth")
	private String shareMoneyAuth;

	@Column(name = "share_show_url")
	private String shareShowUrl;

	@Column(name = "share_pull_opp")
	private String sharePullOpp;

	@Column(name = "share_number")
	private int shareNumber;
	 

	public WxUserBonus() {
	}

	public String getUserBonusId() {
		return this.userBonusId;
	}

	public void setUserBonusId(String userBonusId) {
		this.userBonusId = userBonusId;
	}

	public String getAppid() {
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public WxAppBonus getWxAppBonus() {
		return this.wxAppBonus;
	}

	public void setWxAppBonus(WxAppBonus wxAppBonus) {
		this.wxAppBonus = wxAppBonus;
	}

	public String getBonusStatus() {
		return this.bonusStatus;
	}

	public void setBonusStatus(String bonusStatus) {
		this.bonusStatus = bonusStatus;
	}

	public String getBonusType() {
		return this.bonusType;
	}

	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDeadTime() {
		return this.deadTime;
	}

	public void setDeadTime(String deadTime) {
		this.deadTime = deadTime;
	}

	public int getIsPush() {
		return this.isPush;
	}

	public void setIsPush(int isPush) {
		this.isPush = isPush;
	}

	public float getMoney() {
		return this.money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUserBonusCode() {
		return userBonusCode;
	}

	public void setUserBonusCode(String userBonusCode) {
		this.userBonusCode = userBonusCode;
	}

	public String getpUserBonusId() {
		return pUserBonusId;
	}

	public void setpUserBonusId(String pUserBonusId) {
		this.pUserBonusId = pUserBonusId;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getReceiveVerify() {
		return receiveVerify;
	}

	public void setReceiveVerify(String receiveVerify) {
		this.receiveVerify = receiveVerify;
	}

	public String getVerifyUrl() {
		return verifyUrl;
	}

	public void setVerifyUrl(String verifyUrl) {
		this.verifyUrl = verifyUrl;
	}

	public float getShareMoney() {
		return shareMoney;
	}

	public void setShareMoney(float shareMoney) {
		this.shareMoney = shareMoney;
	}

	public String getShareMoneyAuth() {
		return shareMoneyAuth;
	}

	public void setShareMoneyAuth(String shareMoneyAuth) {
		this.shareMoneyAuth = shareMoneyAuth;
	}

	public String getShareShowUrl() {
		return shareShowUrl;
	}

	public void setShareShowUrl(String shareShowUrl) {
		this.shareShowUrl = shareShowUrl;
	}

	public String getSharePullOpp() {
		return sharePullOpp;
	}

	public void setSharePullOpp(String sharePullOpp) {
		this.sharePullOpp = sharePullOpp;
	}

	public int getShareNumber() {
		return shareNumber;
	}

	public void setShareNumber(int shareNumber) {
		this.shareNumber = shareNumber;
	}

	public String getMoneyAuth() {
		return moneyAuth;
	}

	public void setMoneyAuth(String moneyAuth) {
		this.moneyAuth = moneyAuth;
	}
 

}