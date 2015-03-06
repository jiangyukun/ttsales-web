package cn.ttsales.work.wxapi.pay;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

public class RedpackV2Api implements Closeable{
	private static final Log log = LogFactory.getLog(RedpackV2Api.class);
	
	private static final String API_URI = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	private static final String TLS   = "TLS";
	private static final String PKCS12 = "PKCS12";
	
	private volatile DefaultHttpClient apiClient;
	protected String mch_id, mch_key;
	protected LinkedHashMap<String, String> defaultParams = new LinkedHashMap<String, String>();
	
	public RedpackV2Api(String mch_id,String mch_key, Map<String,String> defaultParams){
		initApi(mch_id,mch_key,defaultParams);
	}
	protected RedpackV2Api(){}
		
	protected final void initApi(String mch_id,String mch_key, Map<String,String> defaultParams){
		if(defaultParams != null) this.defaultParams.putAll(defaultParams);
		if(mch_id == null && this.defaultParams.containsKey("mch_id")) mch_id = this.defaultParams.get("mch_id");
		this.mch_id = mch_id;
		this.mch_key = mch_key;
		this.defaultParams.put("mch_id", mch_id);
	}
	
	public Map<String,String> sendRedpack(Map<String,String> requestParams){
		try{
			String requestXml = makeRequestXml(requestParams);
			if(log.isDebugEnabled()) log.debug(String.format("POST %s \n%s", API_URI,requestXml));
			HttpPost request = new HttpPost(API_URI);
			request.setEntity(new StringEntity(requestXml, "UTF-8"));
			return ensureApiClient().execute(request,new ResponseHandler<Map<String,String>>(){
				public Map<String,String> handleResponse(HttpResponse response)
						throws HttpResponseException, IOException {
			        boolean statusOk = response.getStatusLine().getStatusCode() < 300;
			        HttpEntity entity = response.getEntity();
			        String responseXml = statusOk && entity != null ? new String(EntityUtils.toByteArray(entity),"UTF-8") : "";
					if(log.isDebugEnabled()){
						StringBuilder sb = new StringBuilder();
						sb.append(response.getStatusLine()).append('\n');
						for(Header header : response.getAllHeaders()){
							sb.append(header.toString()).append('\n');
						}
						sb.append(responseXml).append('\n');
						log.debug(sb.toString());
					}
					if(!statusOk) throw new HttpResponseException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
			        return makeResponse(responseXml);
				}
			});
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	private static final Pattern TAG_PATTERN = Pattern.compile("<(\\w+)>\\s*(?:<!\\[CDATA\\[)?([\\s\\S]*?)(?:\\]\\]>)?\\s*</\\1>");
	protected Map<String, String> makeResponse(String responseXml) {
		Map<String,String> ret = new LinkedHashMap<String, String>();
    	Matcher tagXml = TAG_PATTERN.matcher(responseXml);
        if (tagXml.find() && "xml".equals(tagXml.group(1))) {
        	Matcher tagField = TAG_PATTERN.matcher(tagXml.group(2));
        	while(tagField.find()){
        		ret.put(tagField.group(1), tagField.group(2));
        	}
        }
		return ret;
	}


	protected Map<String,String> makeRequestParams(Map<String,String> requestParams){
		Map<String, String> map = new LinkedHashMap<String, String>(defaultParams);
		map.putAll(requestParams);
		map.remove("sign");
		map.remove("key");
		return map;
	}
	
	protected String makeRequestXml(Map<String,String> requestParams){
		requestParams = makeRequestParams(requestParams);
		StringBuilder xml = new StringBuilder("<xml>");
		for(Map.Entry<String, String> param : requestParams.entrySet()){
			xml.append(String.format("<%1$s>%2$s</%1$s>",param.getKey(),param.getValue()));
		}
		xml.append(String.format("<sign>%s</sign>",makeRequestSign(requestParams, mch_key)));
		return xml.append("</xml>").toString();
	}
	
	protected String makeRequestSign(Map<String,String> requestParams, String mch_key){
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String> param : new TreeMap<String, String>(requestParams).entrySet()){
			if(StringUtils.hasLength(param.getValue()))
				sb.append(String.format("%s=%s&",param.getKey(),param.getValue()));
		}
		sb.append("key=").append(mch_key);
		try {
			return DigestUtils.md5Hex(sb.toString().getBytes("UTF-8")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected HttpClient ensureApiClient(){
		if(apiClient == null) synchronized(this) {
			if(apiClient == null){
				try{
					SchemeRegistry registry = new SchemeRegistry();
					registry.register(new Scheme("https", 443, new SSLSocketFactory(initSSLContext(mch_id))));
			        this.apiClient = new DefaultHttpClient(new ThreadSafeClientConnManager(registry));
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			}
		}
		return apiClient;
	}
	
	protected SSLContext initSSLContext(String mch_id) throws Exception{
		String p12cert = String.format("META-INF/wx-certs/%s.p12",mch_id),
				password = mch_id;

		KeyStore keystore = KeyStore.getInstance(PKCS12);
		InputStream in = RedpackV2Api.class.getClassLoader().getResourceAsStream(p12cert);
		keystore.load(in, password.toCharArray());
		in.close();

		KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmfactory.init(keystore, password.toCharArray());

        SSLContext sslcontext = SSLContext.getInstance(TLS);	
        sslcontext.init(kmfactory.getKeyManagers(), null,null);
        return sslcontext;
	}	
	
	public synchronized void close(){
		if(apiClient != null){
			apiClient.getConnectionManager().shutdown();
			apiClient = null;
		}
	}
}
