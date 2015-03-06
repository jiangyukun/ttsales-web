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
 * The persistent class for the wx_app_bonus database table.
 * 
 */
@Entity
@Table(name = "wx_app_bonus")
public class WxAppBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "bonus_id", unique = true, nullable = false, length = 40)
	private String bonusId;

	@Column(name = "act_name")
	private String actName;

	@ManyToOne
	@JoinColumn(name = "appid")
	private WxApp wxApp;

	@Column(name = "scheme_id")
	private String schemeId;

	@Column(name = "bonus_code")
	private String bonusCode;

	@Column(name = "bonus_name")
	private String bonusName;

	@Column(name = "nick_name")
	private String nickName;

	private String remark;

	@Column(name = "send_name")
	private String sendName;

	private String wishing;

	public WxAppBonus() {
	}

	public String getBonusId() {
		return this.bonusId;
	}

	public void setBonusId(String bonusId) {
		this.bonusId = bonusId;
	}

	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public WxApp getWxApp() {
		return this.wxApp;
	}

	public void setWxApp(WxApp wxApp) {
		this.wxApp = wxApp;
	}

	public String getBonusCode() {
		return this.bonusCode;
	}

	public void setBonusCode(String bonusCode) {
		this.bonusCode = bonusCode;
	}

	public String getBonusName() {
		return this.bonusName;
	}

	public void setBonusName(String bonusName) {
		this.bonusName = bonusName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSendName() {
		return this.sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getWishing() {
		return this.wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

}