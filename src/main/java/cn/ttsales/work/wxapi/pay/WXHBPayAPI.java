package cn.ttsales.work.wxapi.pay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.core.util.MD5Util;
import cn.ttsales.work.wxapi.WxConstants;

public class WXHBPayAPI {
	
//	private Logger log = Logger.getLogger(CorpApi.class);
	
	private static WXHBPayAPI wxhbAPI;
	private static String mchId;
	private static String wxappid;
	private static String nickName;
	private static String sendName;
	private static String key;
	private static String clientIp;
	private String spBillno;
	
	static{
		mchId = BundleUtil.getProperty(WxConstants.WEIXIN_PAY_PROPERTIES, "wx.pay.mchid");//包红包发放方（商户）
		wxappid = BundleUtil.getProperty(WxConstants.WEIXIN_PAY_PROPERTIES, "wx.pay.wxappid");//发送红包方的公众账号的appid 
		nickName = BundleUtil.getProperty(WxConstants.WEIXIN_PAY_PROPERTIES, "wx.pay.nickName");//资金提供方名称
		sendName = BundleUtil.getProperty(WxConstants.WEIXIN_PAY_PROPERTIES, "wx.pay.sendName");//红包发送者名称
		key = BundleUtil.getProperty(WxConstants.WEIXIN_PAY_PROPERTIES, "wx.pay.key");//商户密钥
		clientIp = BundleUtil.getProperty(WxConstants.WEIXIN_PAY_PROPERTIES, "wx.pay.clientIp");//客户端ip
	}
	
	public static WXHBPayAPI getInstance() {
		if (wxhbAPI == null )
			wxhbAPI = new WXHBPayAPI(); 
		return wxhbAPI;
	}
	
	private WXHBPayAPI() {
	}
	/**
	 * 微信红包支付
	 * @param reOpenid
	 * @param totalAmount
	 * @param wishing
	 * @return
	 */
	public String wxhbPayPost(String reOpenid ,Long totalAmount,String wishingType){
		//TODO 测试
		reOpenid = "oZPJ4s_CBmS0DS3IRHixlGRf-jH0";
		totalAmount = 100l;
		JSONObject reObject = new JSONObject();
		StringBuffer url = new StringBuffer(BundleUtil.getProperty(WxConstants.WEIXIN_PAY_PROPERTIES, "wx.pay.url"));
		HttpClient client = new DefaultHttpClient();  
        HttpPost httppost = new HttpPost(url.toString());
        List<NameValuePair> params;
        String wishing="祝福语";
        if(wishingType.equals("01")){
        	wishing = BundleUtil.getProperty("weixin_pay_wishing", "wx.pay.wishing_01");//祝福语
        }
		try {
			// Post请求  
	        // 设置参数  
			params = getParams(wishing, totalAmount, reOpenid);
			httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			 // 发送请求  
            HttpResponse httpresponse = client.execute(httppost); 
            if(httpresponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(httpresponse.getEntity());
				reObject = JsonUtil.fromStr(result);
				reObject.put("spBillno", spBillno);
				return reObject.toString();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
		
	}
	
	private  List<NameValuePair> getParams(String wishing,Long totalAmount,String reOpenid) throws IOException{   
		spBillno = getSpBillno(mchId);//商户订单号（每个订单号必须唯一）
		StringBuffer paramUrl = getParamUrl(clientIp,mchId,spBillno,wxappid,nickName,sendName,wishing,totalAmount,reOpenid);
		String sign = getSign(paramUrl,key);//签名
		List<NameValuePair> params = new ArrayList<NameValuePair>();  
		params.add(new BasicNameValuePair("sign",sign));
		params.add(new BasicNameValuePair("sp_billno",spBillno));
		params.add(new BasicNameValuePair("spid",mchId));
		params.add(new BasicNameValuePair("wxappid",wxappid));
		params.add(new BasicNameValuePair("nick_name",nickName));
		params.add(new BasicNameValuePair("send_name",sendName));
		params.add(new BasicNameValuePair("re_openid",reOpenid));
		params.add(new BasicNameValuePair("total_amount",String.valueOf(totalAmount)));
		params.add(new BasicNameValuePair("min_value",String.valueOf(totalAmount)));
		params.add(new BasicNameValuePair("max_value",String.valueOf(totalAmount)));
		params.add(new BasicNameValuePair("total_num","1"));
		params.add(new BasicNameValuePair("wishing",wishing));
		params.add(new BasicNameValuePair("client_ip",clientIp));
		params.add(new BasicNameValuePair("act_name",sendName));
        return params;  
    }  
	/**
	 * 获取商户订单号
	 * @param spid
	 * @return
	 */
	private String getSpBillno(String spid) {
		String randomNum = String.valueOf(System.currentTimeMillis()).substring(3);
		String date = DateUtil.getCurrentDateStr().replace("-", "");
		return spid+date+randomNum;
	}

	/**
	 * 
	 * 获取Url串
	 * @param clientIp
	 * @param spid
	 * @param spBillno
	 * @param wxappid
	 * @param nickName
	 * @param sendName
	 * @param wishing
	 * @param totalAmount
	 * @param reOpenid
	 * @return
	 */
	private StringBuffer getParamUrl(String clientIp, String spid, String spBillno,
			String wxappid, String nickName, String sendName, String wishing,
			Long totalAmount, String reOpenid) {
		StringBuffer paramUrl = new StringBuffer();
		paramUrl.append("act_name=");
		paramUrl.append(sendName);
		paramUrl.append("&client_ip=");
		paramUrl.append(clientIp);
		paramUrl.append("&max_value=");
		paramUrl.append(totalAmount);
		paramUrl.append("&min_value=");
		paramUrl.append(totalAmount);
		paramUrl.append("&nick_name=");
		paramUrl.append(nickName);
		paramUrl.append("&re_openid=");
		paramUrl.append(reOpenid);
		paramUrl.append("&send_name=");
		paramUrl.append(sendName);
		paramUrl.append("&sp_billno=");
		paramUrl.append(spBillno);
		paramUrl.append("&spid=");
		paramUrl.append(spid);
		paramUrl.append("&total_amount=");
		paramUrl.append(totalAmount);
		paramUrl.append("&total_num=");
		paramUrl.append(1);
		paramUrl.append("&wishing=");
		paramUrl.append(wishing);
		paramUrl.append("&wxappid=");
		paramUrl.append(wxappid);
		return paramUrl;
	}


	/**
	 * 获取签名
	 * @param paramUrl
	 * @param key
	 * @return
	 */
	private String getSign(StringBuffer paramUrl,String key) {
		return MD5Util.EncoderByMd52((paramUrl.toString()+"&key="+key)).toUpperCase();
	}
	
}
