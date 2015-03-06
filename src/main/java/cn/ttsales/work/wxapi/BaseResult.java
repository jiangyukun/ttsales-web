/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename Result.java
 * @package cn.ttsales.work.web.wxapi.cropbean
 * @author dandyzheng
 * @date 2014-9-15
 */
package cn.ttsales.work.wxapi;

/**
 * @author dandyzheng
 *
 */
public class BaseResult {
	public final static String SUCCESS_CODE = "0";
	
	protected String errcode;
	protected String errmsg;
	
	
	
	public BaseResult() {
	}

	

	
	public BaseResult(String errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}


	public String getErrcode() {
		return errcode;
	}


	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}


	public String getErrmsg() {
		return errmsg;
	}


	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}


	
	
}
