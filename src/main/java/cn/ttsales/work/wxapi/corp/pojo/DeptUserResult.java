/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename Result.java
 * @package cn.ttsales.work.web.wxapi.cropbean
 * @author dandyzheng
 * @date 2014-9-15
 */
package cn.ttsales.work.wxapi.corp.pojo;

import cn.ttsales.work.wxapi.BaseResult;
import net.sf.json.JSONArray;

/**
 * @author dandyzheng
 *
 */
public class DeptUserResult extends BaseResult{
	private JSONArray userlist;
	
	public DeptUserResult() {
	}

	public DeptUserResult(JSONArray userlist) {
		this.userlist = userlist;
	}
	
	public DeptUserResult(String errcode, String errmsg,JSONArray userlist) {
		super(errcode,errmsg);
		this.userlist = userlist;
	}

	public JSONArray getUserlist() {
		return userlist;
	}

	public void setUserlist(JSONArray userlist) {
		this.userlist = userlist;
	}
}
