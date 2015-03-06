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
public class ReserveSuccessedTemplateSendMsg {
	
	
	/*{{first.DATA}}
	预约门店：{{keyword1.DATA}}
	预约时间：{{keyword2.DATA}}
	接待者：{{keyword3.DATA}}
	联系电话：{{keyword4.DATA}}
	地址：{{keyword5.DATA}}
	{{remark.DATA}}*/
	
	private TemplateSendMsgData first;
	private TemplateSendMsgData keyword1;
	private TemplateSendMsgData keyword2;
	private TemplateSendMsgData keyword3;
	private TemplateSendMsgData keyword4;
	private TemplateSendMsgData keyword5;
	private TemplateSendMsgData remark;
	
	public ReserveSuccessedTemplateSendMsg(){
		
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

	public TemplateSendMsgData getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(TemplateSendMsgData keyword3) {
		this.keyword3 = keyword3;
	}

	public TemplateSendMsgData getKeyword4() {
		return keyword4;
	}

	public void setKeyword4(TemplateSendMsgData keyword4) {
		this.keyword4 = keyword4;
	}

	public TemplateSendMsgData getKeyword5() {
		return keyword5;
	}

	public void setKeyword5(TemplateSendMsgData keyword5) {
		this.keyword5 = keyword5;
	}

	public TemplateSendMsgData getRemark() {
		return remark;
	}

	public void setRemark(TemplateSendMsgData remark) {
		this.remark = remark;
	}

	
	
	
	
}
