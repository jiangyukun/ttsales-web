package cn.ttsales.work.web.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;
import cn.ttsales.work.wxapi.CorpApi;
import cn.ttsales.work.wxapi.MpApi;

@Controller
@RequestMapping(value="weixin/jsSDK/")
public class GetSignController {
	
	private final static String APPID = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			 "wx.mp.id");
	private final static String SECRET = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			 "wx.mp.secret");
	private  MpApi mpApi = MpApi.getInstance();
	private final static String CORP_APPID = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			 "wx.corp.id");
	private final static String CORP_SECRET = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			 "wx.corp.secret");
	private  CorpApi corpApi = CorpApi.getInstance();
	
	@RequestMapping(value="getSign.do")
	protected ModelAndView getSign(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		
		System.out.println("=================================");
		if(url.indexOf("#")>0){
			url = url.substring(0,url.indexOf("#"));
		}
		String jsapi_ticket =  corpApi.getJsApiTicket(CORP_APPID,CORP_SECRET);
        Map<String, String> ret = sign(jsapi_ticket, url,CORP_APPID);
        JSONObject jsonObject =  JsonUtil.fromObject(ret, new JsonConfig());
        response.setHeader("Access-Control-Allow-Origin", "*");
        ResponseUtil.toClient(response, jsonObject);
		return null;
	}
	
	@RequestMapping(value="getSignForMp.do")
	protected ModelAndView getSignForCorp(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		if(url.indexOf("#")>0){
			url = url.substring(0,url.indexOf("#"));
		}
		String jsapi_ticket =  mpApi.getJsApiTicket(APPID,SECRET);
        Map<String, String> ret = sign(jsapi_ticket, url,APPID);
        JSONObject jsonObject =  JsonUtil.fromObject(ret, new JsonConfig());
        response.setHeader("Access-Control-Allow-Origin", "*");
        ResponseUtil.toClient(response, jsonObject);
		return null;
	}
	
    public  Map<String, String> sign(String jsapi_ticket, String url,String appid) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";
        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        ret.put("appId", appid);
        ret.put("timestamp", timestamp);
        ret.put("nonceStr", nonce_str);
        ret.put("signature", signature);
        System.out.println("signature:"+signature);
        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    

}
