package cn.ttsales.work.wxapi.mp.receive;

public class VideoReceiveMsg extends BaseReceiveMsg {
	// 媒体ID
	private String MediaId;
	// 缩略图ID
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
