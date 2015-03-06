/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename Result.java
 * @package cn.ttsales.work.web.wxapi.cropbean
 * @author dandyzheng
 * @date 2014-9-15
 */
package cn.ttsales.work.wxapi.corp.send;

import cn.ttsales.work.wxapi.BaseResult;

/**
 * @author dandyzheng
 *
 */
public class MsgSendResult extends BaseResult{
	
	
	private String invaliduser;
	private String invalidparty;
	private String invalidtag;
	
	
	public MsgSendResult() {
	}

	public MsgSendResult(String invaliduser,
			String invalidparty, String invalidtag) {
		this.invaliduser = invaliduser;
		this.invalidparty = invalidparty;
		this.invalidtag = invalidtag;
	}
	
	public MsgSendResult(String errcode, String errmsg, String invaliduser,
			String invalidparty, String invalidtag) {
		super(errcode,errmsg);
		this.invaliduser = invaliduser;
		this.invalidparty = invalidparty;
		this.invalidtag = invalidtag;
	}


	public String getInvaliduser() {
		return invaliduser;
	}


	public void setInvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}


	public String getInvalidparty() {
		return invalidparty;
	}


	public void setInvalidparty(String invalidparty) {
		this.invalidparty = invalidparty;
	}


	public String getInvalidtag() {
		return invalidtag;
	}


	public void setInvalidtag(String invalidtag) {
		this.invalidtag = invalidtag;
	}

	@Override
	public String toString() {
		return "MsgReqResult [errcode="+ errcode +",errmsg="+ errmsg +",invaliduser=" + invaliduser + ", invalidparty="
				+ invalidparty + ", invalidtag=" + invalidtag + "]";
	}

	
	
}
