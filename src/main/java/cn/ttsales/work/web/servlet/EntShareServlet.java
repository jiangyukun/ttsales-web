package cn.ttsales.work.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.web.common.util.DomUtil;
import cn.ttsales.work.wxapi.CorpApi;
import cn.ttsales.work.wxapi.EventType;
import cn.ttsales.work.wxapi.MsgType;
import cn.ttsales.work.wxapi.Result;

/**
 * Servlet implementation class EntEvaluateServlet
 */
public class EntShareServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(EntShareServlet.class);
	private String token = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			"wx.corp.agentShare.token");
	private String encodingAesKey = BundleUtil.getProperty(
			SASConstants.RESOURCE_WEIXIN, "wx.corp.agentShare.aesKey");
	private String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			"wx.corp.id");
	private String secret = BundleUtil.getProperty(
			SASConstants.RESOURCE_WEIXIN, "wx.corp.secret");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("EntShareServlet doGet");

		// 微信加密签名
		String msgSignature = request.getParameter("msg_signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		try {
			Result result = CorpApi.getInstance().verifySignature(echostr,
					encodingAesKey, msgSignature, token, timestamp, nonce,
					appId);

			PrintWriter out = response.getWriter();
			if (result.getCode() == 0) {
				out.print(result.getResult());
				log.info("VerifySignature success! echostr:"
						+ result.getResult());
			} else {
				out.print("VerifySignature failed! error code:"
						+ result.getCode());
				log.error("VerifySignature failed! error code:"
						+ result.getCode());
			}

			out.close();
			out = null;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 微信加密签名
		String msgSignature = request.getParameter("msg_signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");

		BufferedReader reader = request.getReader();
		StringBuilder jb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null)
			jb.append(line);
		String fromXMLText = jb.toString();

		// 通过检验signature对请求进行校验
		Result result;
		PrintWriter out = response.getWriter();
		result = CorpApi.getInstance().fromTencent(fromXMLText, encodingAesKey,
				msgSignature, token, timestamp, nonce, appId);
		if (result.getCode() == 0) {// 消息解密正确
			Document document = DomUtil.loadDocument(result.getResult());
			Element root = document.getDocumentElement();
			List<Element> elementList = DomUtil.getChildElements(root);
			Map<String, String> requestMap = new HashMap<String, String>();
			for (Element e : elementList)
				requestMap.put(e.getNodeName(), e.getTextContent());

			// 发送方ID（user_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 代理id
			String agentId = requestMap.get("AgentID");
			switch (MsgType.getByName(msgType)) {
			case IMAGE:
				break;
			case LINK:
				break;
			case LOCATION:
				break;
			case MPNEWS:
				break;
			case MUSIC:
				break;
			case NEWS:
				break;
			case TEXT:
				break;
			case VIDEO:
				break;
			case VOICE:
				break;
			case EVENT: {
				// 事件类型
				String eventType = requestMap.get("Event");
				switch (EventType.getByName(eventType)) {
				case CLICK: {
					break;
				}
				case LOCATION:
					break;
				case LOCATION_SELECT:
					break;
				case PIC_PHOTO_OR_ALBUM:
					break;
				case PIC_SYSPHOTO:
					break;
				case PIC_WEIXIN:
					break;
				case SCAN:
					break;
				case SCANCODE_PUSH:
					break;
				case SCANCODE_WAITMSG:
					break;
				case SUBSCRIBE:
					break;
				case UNSUBSCRIBE:
					break;
				case VIEW:
					break;
				default:
					break;

				}
				break;
			}
			default:
				break;

			}

		} else {
			out.print("VerifySignature failed! error code:" + result.getCode());
			log.error("VerifySignature failed! error code:" + result.getCode());
		}

		out.close();
	}

}
