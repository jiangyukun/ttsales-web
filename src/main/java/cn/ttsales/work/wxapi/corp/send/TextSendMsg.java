/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename TextReqMsg.java
 * @package cn.ttsales.work.web.wxapi.cropbean
 * @author dandyzheng
 * @date 2014-9-15
 */
package cn.ttsales.work.wxapi.corp.send;

import cn.ttsales.work.wxapi.MsgType;
import cn.ttsales.work.wxapi.WxConstants;

/**
 * 企业号TEXT消息属性
 * @author dandyzheng
 *
 */
public class TextSendMsg extends BaseSendMsg {
	private Text text;
	private String safe;

	public TextSendMsg() {
		
	}

	public TextSendMsg(Text text,String safe) {
		this.text = text;
		this.safe = safe;
	}
	
	public TextSendMsg(String touser, String toparty, String totag,
			String msgtype, String agentid) {
		super(touser, toparty, totag, msgtype, agentid);
	}
	
	public TextSendMsg(String touser, String toparty, String totag,
			String msgtype, String agentid, Text text,String safe) {
		super(touser, toparty, totag, msgtype, agentid);
		this.text = text;
		this.safe = safe;
	}

	public TextSendMsg(String agentId, String content) {
		super(MsgType.TEXT.getName(), agentId);
		this.text = new Text(content);
		this.safe = WxConstants.UN_SAFE;
	}
	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public String getSafe() {
		return safe;
	}

	public void setSafe(String safe) {
		this.safe = safe;
	}
	
	public static class Text {
		private String content;
		
		public Text() {
			
		}
		public Text(String content) {
			this.content = content;
		}
		public String getContent() {
			return content;
		}
	
		public void setContent(String content) {
			this.content = content;
		}
	}
	
}
