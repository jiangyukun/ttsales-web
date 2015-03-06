package cn.ttsales.work.wxapi.mp.resp;

import cn.ttsales.work.wxapi.mp.resp.NewsRespMsg.Item;

/**
 * 
 * @author dandyzheng
 *
 */
public abstract class BaseRespMsg {
	// 接收方帐号（收到的OpenID）
	private String ToUserName;
	// 开发人员微信号
	private String FromUserName;
	// 消息创建时间 （整型）
	private long CreateTime;
	// 消息类型（text/image/news/music/voice/video
	private String MsgType;
	/*// 位0x0001被标志时，星标刚收到的消息
	private int FuncFlag;*/
	

	public String getToUserName() {
		return ToUserName;
	}

	

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	/*public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}*/

	protected String getMsgType() {
		return MsgType;
	}

	protected void setMsgType(String msgType) {
		MsgType = msgType;
	}

	/*public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}*/
	
	public String toString(){
		StringBuilder msg = new StringBuilder();
		msg.append("<ToUserName><![CDATA[");
		msg.append(this.getToUserName());
		msg.append("]]></ToUserName>");
		
		msg.append("<FromUserName><![CDATA[");
		msg.append(this.getFromUserName());
		msg.append("]]></FromUserName>");
		
		msg.append("<CreateTime>");
		msg.append(System.currentTimeMillis() / 1000);
		msg.append("</CreateTime>");
		
		msg.append("<MsgType><![CDATA[");
		msg.append(this.getMsgType());
		msg.append("]]></MsgType>");
		return msg.toString();
	}
	
	public abstract String toXml();
}
