/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename BaseReqMsg.java
 * @package cn.ttsales.work.web.wxapi.cropbean
 * @author dandyzheng
 * @date 2014-9-15
 */
package cn.ttsales.work.wxapi.corp.send;

/**
 * 企业号消息公用属性
 * 
 * @author dandyzheng
 *
 */
public class BaseSendMsg {
	private String touser;
	private String toparty;
	private String totag;
	private String msgtype;
	private String agentid;
	
	public BaseSendMsg() {
		super();
	}

	public BaseSendMsg(String touser, String toparty, String totag,
			String msgtype, String agentid) {
		super();
		this.touser = touser;
		this.toparty = toparty;
		this.totag = totag;
		this.msgtype = msgtype;
		this.agentid = agentid;
	}
	public BaseSendMsg(String msgtype, String agentid) {
		super();
		this.msgtype = msgtype;
		this.agentid = agentid;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getToparty() {
		return toparty;
	}
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	public String getTotag() {
		return totag;
	}
	public void setTotag(String totag) {
		this.totag = totag;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	
}
