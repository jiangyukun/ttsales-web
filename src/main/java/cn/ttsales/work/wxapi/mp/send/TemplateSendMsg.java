/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename TemplateReqMsg.java
 * @package cn.ttsales.work.web.wxapi.pojo
 * @author dandyzheng
 * @date 2014-9-12
 */
package cn.ttsales.work.wxapi.mp.send;

/**
 * 模版消息
 * @author dandyzheng
 * @param <T>
 * 
 */
public class TemplateSendMsg<T> {
	private String touser;
	private String template_id;
	private String url = "";
	private String topcolor = "#FF0000";
	private T data;
	
	public TemplateSendMsg(){
		
	}
	public TemplateSendMsg(String touser, String template_id, String url,
			String topcolor, T data) {
		super();
		this.touser = touser;
		this.template_id = template_id;
		this.url = url;
		this.topcolor = topcolor;
		this.data = data;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
	
}
