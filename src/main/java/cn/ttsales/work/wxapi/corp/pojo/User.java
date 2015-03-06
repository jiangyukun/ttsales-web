/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename User.java
 * @package cn.ttsales.work.wxapi.corp.pojo
 * @author dandyzheng
 * @date 2014-10-10
 */
package cn.ttsales.work.wxapi.corp.pojo;

import java.util.List;

import cn.ttsales.work.core.util.JsonUtil;
import net.sf.json.JsonConfig;

/**
 * @author dandyzheng
 * 
 */
public class User {

	private String userid;
	private String name;
	private List<Integer> department;
	private String position;
	private String mobile;
	private String gender;
	private String tel;
	private String email;
	private String weixinid;

	private String enable;

	private String avatar;
	private String status;

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

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
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
	
	public String toCreateUser(){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(this.toJson());
		sb.append("}");
		return sb.toString();
	}
	
	public String toUpdateUser(){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(this.toJson());
		sb.append("\",enable\":\"");
		sb.append(this.getEnable());
		sb.append("\"");
		sb.append("}");
		return sb.toString();
	}
	

	private String toJson() {
		StringBuffer sb = new StringBuffer();
		sb.append("\"userid\":\"");
		sb.append(this.getUserid());
		sb.append("\",");
		sb.append("\"name\":\"");
		sb.append(this.getName());
		sb.append("\",");
		sb.append("\"department\":");
		sb.append(JsonUtil.fromList(this.getDepartment(),new JsonConfig()));
		sb.append(",");
		if(getPosition()!=null){
			sb.append("\"position\":\"");
			sb.append(this.getPosition());
			sb.append("\",");
		}
		if(getMobile()!=null){
			sb.append("\"mobile\":\"");
			sb.append(this.getMobile());
			sb.append("\",");
		}
		if(getGender()!=null){
			sb.append("\"gender\":\"");
			sb.append(this.getGender());
			sb.append("\",");
		}
		if(getTel()!=null){
			sb.append("\"tel\":\"");
			sb.append(this.getTel());
			sb.append("\",");
		}
		if(getEmail()!=null){
			sb.append("\"email\":\"");
			sb.append(this.getEmail());
			sb.append("\",");
		}
		if(getWeixinid()!=null){
			sb.append("\"weixinid\":\"");
			sb.append(this.getWeixinid());
			sb.append("\"");
		}else{
			sb.deleteCharAt(sb.length()-1);
 		}
		return sb.toString();
	}

	

}
