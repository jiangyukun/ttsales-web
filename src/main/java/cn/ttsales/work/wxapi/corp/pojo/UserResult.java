/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename User.java
 * @package cn.ttsales.work.wxapi.corp.pojo
 * @author dandyzheng
 * @date 2014-10-10
 */
package cn.ttsales.work.wxapi.corp.pojo;

import java.util.List;

import cn.ttsales.work.wxapi.BaseResult;

/**
 * @author dandyzheng
 * 
 */
public class UserResult extends BaseResult {

	private String userid;
	private String name;
	private List<Integer> department;
	private String position;
	private String mobile;
	private String gender;
	private String tel;
	private String email;
	private String weixinid;
	private String avatar;
	private String status;
	private Extattr extattr;

	public UserResult() {

	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getDepartment() {
		return department;
	}

	public void setDepartment(List<Integer> department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Extattr getExtattr() {
		return extattr;
	}

	public void setExtattr(Extattr extattr) {
		this.extattr = extattr;
	}

	public static class Extattr {
		private List<Attrs> attrs;

		public Extattr() {
		}

		public Extattr(List<Attrs> attrs) {
			this.attrs = attrs;
		}

		public List<Attrs> getAttrs() {
			return attrs;
		}

		public void setAttrs(List<Attrs> attrs) {
			this.attrs = attrs;
		}

		public static class Attrs {
			private String name;
			private String value;

			public Attrs() {
			}

			public Attrs(String name, String value) {
				this.name = name;
				this.value = value;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}

		}
	}
}
