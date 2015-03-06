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
public class ReserveTemplateSendMsg {
	
	
	/*{{first.DATA}}
	预约时间：{{keyword1.DATA}}
	预约电话：{{keyword2.DATA}}
	{{remark.DATA}}*/
	
	private TemplateSendMsgData first;
	private TemplateSendMsgData keyword1;
	private TemplateSendMsgData keyword2;
	private TemplateSendMsgData remark;
	
	public ReserveTemplateSendMsg(){
		
	}

	public TemplateSendMsgData getFirst() {
		return first;
	}

	public void setFirst(TemplateSendMsgData first) {
		this.first = first;
	}

	public TemplateSendMsgData getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(TemplateSendMsgData keyword1) {
		this.keyword1 = keyword1;
	}

	public TemplateSendMsgData getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(TemplateSendMsgData keyword2) {
		this.keyword2 = keyword2;
	}


	public TemplateSendMsgData getRemark() {
		return remark;
	}

	public void setRemark(TemplateSendMsgData remark) {
		this.remark = remark;
	}

	
	
	
	
}
