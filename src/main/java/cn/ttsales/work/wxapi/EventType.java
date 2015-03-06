package cn.ttsales.work.wxapi;

public enum EventType {
	// 订阅
	SUBSCRIBE("subscribe"),
	// 取消订阅
	UNSUBSCRIBE("unsubscribe"),
	// 扫带参二维码关注
	SCAN("SCAN"),
	//上报地理位置
	LOCATION("LOCATION"),
	// 按钮点击
	CLICK("click"),
	// 链接
	VIEW("view"),
	// 扫码
	SCANCODE_PUSH("scancode_push"),
	// 扫码等待消息
	SCANCODE_WAITMSG("scancode_waitmsg"),
	// 拍照
	PIC_SYSPHOTO("pic_sysphoto"),
	// 拍照或相册发图
	PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),
	// 微信相册发图
	PIC_WEIXIN("pic_weixin"),
	// 选择地理位置
	LOCATION_SELECT("location_select"),
	//
	OTHER("other")
;

	private String name;

	private EventType(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public static EventType getByName(String name) {
		for (EventType type : EventType.values()) {
			if (type.getName().equalsIgnoreCase(name))
				return type;
		}
		return EventType.OTHER;
	}
}
