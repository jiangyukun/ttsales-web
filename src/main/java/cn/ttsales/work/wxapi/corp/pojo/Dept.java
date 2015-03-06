/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename Dept.java
 * @package cn.ttsales.work.wxapi.corp.pojo
 * @author dandyzheng
 * @date 2014-10-10
 */
package cn.ttsales.work.wxapi.corp.pojo;


/**
 * @author dandyzheng
 *
 */
public class Dept {
	private String id;
	private String name;
	private String parentid;
	private String order;
	
	public Dept(){
		
	}
	
	public Dept(String id, String name, String parentid, String order) {
		this.id = id;
		this.name = name;
		this.parentid = parentid;
		this.order = order;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public String toCreateDept(){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"name\":\"");
		sb.append(this.getName());
		sb.append("\",");
		sb.append("\"parentid\":\"");
		sb.append(this.getParentid());
		sb.append("\",");
		sb.append("\"order\":\"");
		sb.append(this.getOrder());
		sb.append("\"");
		sb.append("}");
		return sb.toString();
	}
	
	public String toUpdateDept(){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"id\":\"");
		sb.append(this.getId());
		sb.append("\",");
		sb.append("\"name\":\"");
		sb.append(this.getName());
		sb.append("\",");
		sb.append("\"parentid\":\"");
		sb.append(this.getParentid());
		sb.append("\",");
		sb.append("\"order\":\"");
		sb.append(this.getOrder());
		sb.append("\"");
		sb.append("}");
		return sb.toString();
	}
}
