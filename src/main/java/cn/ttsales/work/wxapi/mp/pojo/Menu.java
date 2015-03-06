package cn.ttsales.work.wxapi.mp.pojo;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
	
	public Menu addButton(Button button) {
		List<Button> buttons = new ArrayList<Button>();
		if (this.button != null) {
			if (this.button.length >=3)
				throw new RuntimeException("菜单按钮数量不能大于3！");
			for(int i=0;i<this.button.length;i++) {
				buttons.add(this.button[i]);
			}
		}
		buttons.add(button);
		this.button = buttons.toArray(new Button[] {});
		return this;
	}

	public static interface Button {
		
	}
	
	public static enum KeyButtonType {
		CLICK("click"), SCANCODE_PUSH("scancode_push"), SCANCODE_WAITMSG("scancode_waitmsg"), PIC_SYSPHOTO("pic_sysphoto"), PIC_PHOTO_OR_ALBUM("pic_photo_or_album"), PIC_WEIXIN("pic_weixin"), LOCATION_SELECT("location_select");
		private String name;

		private KeyButtonType(String name) {  
			this.setName(name);  
	  }

		public String getName() {
			return name;
		}

		private void setName(String name) {
			this.name = name;
		}  
		public static KeyButtonType getByName(String name){
			for (KeyButtonType type : KeyButtonType.values()) {
				if (type.getName().equals(name))
					return type;
			}
			return null;
		}
	}
	public static abstract class CommonButton implements Button {
		private String name;

		private String type;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		protected void setType(String type) {
			this.type = type;
		}
	}

	public static class KeyButton extends CommonButton {
		private String key;

		public KeyButton(KeyButtonType type, String name, String key) {
			this.setType(type.getName());
			this.setName(name);
			this.setKey(key);
		}
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}

	public static class ViewButton extends CommonButton {

		private String url;

		public ViewButton(String name, String url) {
			this.setType("view");
			this.setName(name);
			this.setUrl(url);
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}

	public static class ParentButton implements Button {
		private String name;
		private CommonButton[] sub_button;
		
		public ParentButton(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public CommonButton[] getSub_button() {
			return sub_button;
		}

		public void setSub_button(CommonButton[] sub_button) {
			this.sub_button = sub_button;
		}
		public ParentButton addSubButton(CommonButton subButton) {
			List<CommonButton> subButtons = new ArrayList<CommonButton>();
			if (this.sub_button != null) {
				if (this.sub_button.length >=5)
					throw new RuntimeException("子菜单按钮数量不能大于5！");
				for(int i=0;i<this.sub_button.length;i++) {
					subButtons.add(this.sub_button[i]);
				}
			}
			subButtons.add(subButton);
			this.sub_button = subButtons.toArray(new CommonButton[]{});
			return this;
		}

	}
}
