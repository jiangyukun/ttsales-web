package cn.ttsales.org.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the org_member database table.
 * 
 */

@Entity
@Table(name = "org_member")
@SuppressWarnings("serial")
public class OrgMember extends OrgBaseEntity implements Serializable {
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "member_id")
	private String memberId;

	@Column(name = "p_member_id")
	private String parentMemberId;

	@Column(name = "name")
	private String name;

	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "weixin")
	private String weixin;

	@Column(name = "position")
	private String position;

	@Column(name = "phone")
	private String phone;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;

	@Column(name = "gender")
	private String gender;

	@Column(name = "head_url")
	private String headUrl;

	@Column(name = "head_pic")
	private String headPic;

	@Column(name = "state")
	private String state;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "invite_time")
	private String inviteTime;

	@Column(name = "has_agreement")
	private Integer hasAgreement;

	@Column(name = "subscribe_state")
	private String subscribeState;

	@Column(name = "subscribe_time")
	private String subscribeTime;

	@Column(name = "unsubscribe_time")
	private String unsubscribeTime;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getParentMemberId() {
		return parentMemberId;
	}

	public void setParentMemberId(String parentMemberId) {
		this.parentMemberId = parentMemberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInviteTime() {
		return inviteTime;
	}

	public void setInviteTime(String inviteTime) {
		this.inviteTime = inviteTime;
	}

	public Integer getHasAgreement() {
		return hasAgreement;
	}

	public void setHasAgreement(Integer hasAgreement) {
		this.hasAgreement = hasAgreement;
	}

	public String getSubscribeState() {
		return subscribeState;
	}

	public void setSubscribeState(String subscribeState) {
		this.subscribeState = subscribeState;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnsubscribeTime() {
		return unsubscribeTime;
	}

	public void setUnsubscribeTime(String unsubscribeTime) {
		this.unsubscribeTime = unsubscribeTime;
	}

}
