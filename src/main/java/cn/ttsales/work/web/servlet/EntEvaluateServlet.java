package cn.ttsales.work.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.wxapi.CorpApi;
import cn.ttsales.work.wxapi.Result;

/**
 * Servlet implementation class EntEvaluateServlet
 */
public class EntEvaluateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(EntEvaluateServlet.class);
	private String token = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			"wx.corp.agentEvaluate.token");
	private String encodingAesKey = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			"wx.corp.agentEvaluate.aesKey");
	private String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			"wx.corp.id");
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("EntEvaluateServlet doGet");
		// 微信加密签名
		String msgSignature = request.getParameter("msg_signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		try {
			Result result = CorpApi.getInstance().verifySignature(echostr, encodingAesKey, msgSignature, token, timestamp, nonce, appId);
			
			PrintWriter out = response.getWriter();
			if (result.getCode() == 0 ) {
				out.print(result.getResult());
			
			} else {
				out.print("VerifySignature failed! error code:" + result.getCode());
				log.error("VerifySignature failed! error code:" + result.getCode());
			}
			
			out.close();
			out = null;
			
//			System.out.println(result.getCode());
//			System.out.println(result.getResult());
		} catch (Exception e) {
			log.error(e.getMessage());			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("");
		out.close();
		out=null;
	}

}
