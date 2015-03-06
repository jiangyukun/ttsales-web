/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename MsgData.java
 * @package cn.ttsales.work.web.wxapi.pojo
 * @author dandyzheng
 * @date 2014-9-12
 */
package cn.ttsales.work.wxapi.mp.send;

/**
 * @author dandyzheng
 *
 */
public class TemplateSendMsgData {
	private String value;
	private String color = "#000000";
	
	public TemplateSendMsgData(){
		
	}
	
	public TemplateSendMsgData(String value) {
		super();
		this.value = value;
	}
	
	public TemplateSendMsgData(String value, String color) {
		super();
		this.value = value;
		this.color = color;
	}


	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
