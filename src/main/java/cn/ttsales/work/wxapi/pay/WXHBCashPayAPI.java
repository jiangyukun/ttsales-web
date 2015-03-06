package cn.ttsales.work.wxapi.pay;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.wxapi.WxConstants;

@Component
public class WXHBCashPayAPI implements InitializingBean,DisposableBean{
	
	
	private String mchId;
	private RedpackV2Api api;

	public void afterPropertiesSet(){
		mchId = settings("wx.pay.mchid");/*包红包发放方（商户）*/		
		
		Map<String,String> config = new LinkedHashMap<String, String>();
		config.put("wxappid", settings("wx.pay.wxappid"));//发送红包方的公众账号的appid 
		config.put("nick_name", settings("wx.pay.nickName"));//资金提供方名称
		config.put("send_name", settings("wx.pay.sendName"));//红包发送者名称
		config.put("client_ip", settings("wx.pay.clientIp"));//客户端ip
		config.put("act_name", settings("wx.pay.actName"));//活动名称
		config.put("remark", settings("wx.pay.remark"));//备注 
		config.put("logo_imgurl", settings("wx.pay.logoImgurl"));//商户logo的url
		config.put("share_content", settings("wx.pay.shareContent"));//分享文案 
		config.put("share_url", settings("wx.pay.shareUrl"));//分享链接
		config.put("share_imgurl", settings("wx.pay.shareImgurl"));//分享的图片url 
		config.put("total_num", "1");

		api = new RedpackV2Api(mchId, settings("wx.pay.key")/*商户密钥*/, config);
	}
	
	public void destroy() {
		api.close();
	}
	
	private static String settings(String prop){
		return BundleUtil.getProperty(WxConstants.WEIXIN_PAY_PROPERTIES, prop);
	}

	/**
	 * 微信红包支付
	 * @param reOpenid
	 * @param totalAmount
	 * @param wishing
	 * @return
	 */
	public Map<String,String> wxhbCashPayPost(String reOpenid ,int totalAmount,String wishingType){
		
		Map<String,String> request = new LinkedHashMap<String, String>();
		request.put("nonce_str", String.valueOf(System.currentTimeMillis()));
		request.put("mch_billno", getSpBillno(mchId));
		request.put("re_openid", reOpenid);
		request.put("total_amount", String.valueOf(totalAmount));
		request.put("min_value", String.valueOf(totalAmount));
		request.put("max_value", String.valueOf(totalAmount));
		request.put("wishing", BundleUtil.getProperty("weixin_pay_wishing", "wx.pay.wishing_"+wishingType));//祝福语

		return api.sendRedpack(request);
	}
	
	/**
	 * 微信红包支付
	 * @param reOpenid
	 * @param totalAmount
	 * @param wishing
	 * @return
	 */
	public Map<String,String> reWxhbCashPayPost(String reOpenid ,int totalAmount,String wishingType,String mchbillno){
		
		Map<String,String> request = new LinkedHashMap<String, String>();
		request.put("nonce_str", String.valueOf(System.currentTimeMillis()));
		request.put("mch_billno",mchbillno);
		request.put("re_openid", reOpenid);
		request.put("total_amount", String.valueOf(totalAmount));
		request.put("min_value", String.valueOf(totalAmount));
		request.put("max_value", String.valueOf(totalAmount));
		request.put("wishing", BundleUtil.getProperty("weixin_pay_wishing", "wx.pay.wishing_"+wishingType));//祝福语

		return api.sendRedpack(request);
	}
	
	/**
	 * 获取商户订单号
	 * @param spid
	 * @return
	 */
	protected String getSpBillno(String spid) {
		String randomNum = String.valueOf(System.currentTimeMillis()).substring(3);
		String date = DateUtil.getCurrentDateStr().replace("-", "");
		return spid+date+randomNum;
	}



	
}
