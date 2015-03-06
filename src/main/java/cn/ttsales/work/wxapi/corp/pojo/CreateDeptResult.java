/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename Result.java
 * @package cn.ttsales.work.web.wxapi.cropbean
 * @author dandyzheng
 * @date 2014-9-15
 */
package cn.ttsales.work.wxapi.corp.pojo;

import cn.ttsales.work.wxapi.BaseResult;

/**
 * @author dandyzheng
 *
 */
public class CreateDeptResult extends BaseResult{
	private String id;
	
	

	public CreateDeptResult() {
		
	}

	public CreateDeptResult(String errcode, String errmsg) {
		super(errcode, errmsg);
	}
	
	public CreateDeptResult(String errcode, String errmsg,String id) {
		super(errcode, errmsg);
		this.id = id;
	}

	public CreateDeptResult(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
