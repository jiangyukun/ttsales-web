package cn.ttsales.work.web;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.wxapi.MpApi;
import cn.ttsales.work.wxapi.mp.pojo.Menu;
import cn.ttsales.work.wxapi.mp.pojo.Menu.KeyButton;
import cn.ttsales.work.wxapi.mp.pojo.Menu.KeyButtonType;
import cn.ttsales.work.wxapi.mp.pojo.Menu.ViewButton;
import net.sf.json.JSONObject;

public class MpMenuCreater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.mp.id");
		String secret = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.mp.secret");

		Menu menu = new Menu();

		menu.addButton(new ViewButton("我的红包", "http://uu.ttsales.cn/SASWeb/report/myBonusMp/init.do"))
				.addButton(new KeyButton(KeyButtonType.CLICK, "我的预约", "MY_RESERVE")).addButton(
						new KeyButton(KeyButtonType.SCANCODE_PUSH, "到店签到", "SIGN_IN"));
 		String jsonMenu = JSONObject.fromObject(menu).toString();  
		System.out.println(jsonMenu);
		String result = MpApi.getInstance().createMenu(appId, secret, menu);
		System.out.println(result);

	}

}
