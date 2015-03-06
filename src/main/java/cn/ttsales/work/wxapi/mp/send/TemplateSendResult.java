package cn.ttsales.work.wxapi.mp.send;

import cn.ttsales.work.wxapi.BaseResult;

public class TemplateSendResult extends BaseResult {
	private String  msgid;
	
	
	

	public TemplateSendResult() {
		super();
	}

	public TemplateSendResult(String errcode, String errmsg) {
		super(errcode, errmsg);
	}
	
	

	public TemplateSendResult(String errcode, String errmsg,String msgid) {
		super(errcode, errmsg);
		this.msgid = msgid;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	
	
}
