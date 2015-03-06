package cn.ttsales.work.core.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import cn.ttsales.work.core.SASConstants;

/**
 * 采用容大后台进行短信的发送
 * @author gaoyang
 *
 */
public class SMSUtil {

	public static final String USERNAME = "ltxx";
	public static final String PASSWORD = "f69f63003e5cca670fd465760e10caa3"; //ltsoftP123!@#ABC MD5加密

	public static final String sendURI = "http://116.213.72.20/SMSHttpService/send.aspx";

	public static final String balanceURI = "http://116.213.72.20/SMSHttpService/Balance.aspx";

	public static final String checkDeadWordURI = "http://116.213.72.20/SMSHttpService/CheckDeadWord.aspx";

	/**
	 * 发送短信
	 * @param content 发送内容
	 * @param cellphoneNumber 手机号
	 * @return 发送成功返回null，否则返回错误原因
	 * @author gaoyang
	 * @date 2014年1月21日
	 * @see
	 */
	public static String send(String content, String cellphoneNumber) {
		content = "【微车】" + content;
		//判断是否余额不足
		String balance = post(balanceURI + "?" + "username=" + USERNAME
				+ "&password=" + PASSWORD);
		try {
			Double.parseDouble(balance);
		} catch (Exception e) {
			return "余额不足:" + balance;
		}

		//发送的内容进行编码
		String encodeContent = "";
		try {
			encodeContent = URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		
		//判断是否有敏感词
		String deadWord = post(checkDeadWordURI + "?" + "msg=" + encodeContent);
		if (deadWord != null && !"".equals(deadWord)) {
			return "敏感词:" + deadWord;
		}

		//进行发送
		String result = post(sendURI + "?" + "username=" + USERNAME
				+ "&password=" + PASSWORD + "&mobile=" + cellphoneNumber
				+ "&content=" + encodeContent + "&Extcode=&senddate=&batchID=");

		//发送成功返回null，否则返回错误原因
		/*if ("0".equals(result))
			return null;
		else*/
			return result;
	}

	private static String post(String smsURI) {
		try {
			String BOUNDARY = java.util.UUID.randomUUID().toString();
			String MULTIPART_FROM_DATA = "multipart/form-data";
			URL uri = new URL(smsURI);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setReadTimeout(8 * 1000); // 缓存的最长时间
			conn.setDoInput(true); // 允许输入
			conn.setDoOutput(true); // 允许输出
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
					+ ";boundary=" + BOUNDARY);
			ObjectOutputStream out = new ObjectOutputStream(
					conn.getOutputStream());
			out.writeObject("");
			out.flush();
			int res = conn.getResponseCode();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder buffer = new StringBuilder();
			if (res == 200) {
				String line;
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
			}
			out.close();
			reader.close();
			conn.disconnect();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}

	public static void main(String[] args) {
		String msg = BundleUtil.getProperty(
				SASConstants.RESOURCE_BUNDLE, "sas.reserve.customer.info",
				"dfdfd",
				"2014-11-11 09:00",
				"肯定考上了对方"
				);
		System.out.println(send(msg, "18758004530"));
	}
}
