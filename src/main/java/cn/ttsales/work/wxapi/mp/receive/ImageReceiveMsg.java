package cn.ttsales.work.wxapi.mp.receive;

public class ImageReceiveMsg extends BaseReceiveMsg {
	// 图片链接
	private String PicUrl;
	// 媒体ID
	private String MediaId;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
