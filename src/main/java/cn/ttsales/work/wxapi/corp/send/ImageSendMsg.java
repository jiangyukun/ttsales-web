package cn.ttsales.work.wxapi.corp.send;

import cn.ttsales.work.wxapi.MsgType;
import cn.ttsales.work.wxapi.WxConstants;

public class ImageSendMsg extends BaseSendMsg {
	
	private Image image;
	private String safe;
	
	public ImageSendMsg() {
		
	}
	public ImageSendMsg(String angentId, String mediaId) {
		super(MsgType.IMAGE.getName(), angentId);
		this.image = new Image(mediaId);
		this.safe = WxConstants.UN_SAFE;
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getSafe() {
		return safe;
	}

	public void setSafe(String safe) {
		this.safe = safe;
	}
	
	
	public static class Image {
		private String media_id;

		public Image() {
			
		}
		public Image(String mediaId) {
			this.media_id = mediaId;
		}
		
		public String getMedia_id() {
			return media_id;
		}

		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
		
	}
}
