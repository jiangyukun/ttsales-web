package cn.ttsales.work.wxapi.mp.resp;

public class TextRespMsg extends BaseRespMsg {
	//消息内容
	private String Content;
	
	public TextRespMsg(){
		super.setMsgType(cn.ttsales.work.wxapi.MsgType.TEXT.getName());
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	@Override
	public String toXml() {
		StringBuilder msg = new StringBuilder();
		msg.append("<xml>");
		msg.append(super.toString());
		msg.append("<Content><![CDATA[");
		msg.append(this.getContent());
		msg.append("]]></Content>");
		msg.append("</xml>");
		return msg.toString();
	}
}
