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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BeanUtil;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.core.util.FileUtil;
import cn.ttsales.work.core.util.MD5Util;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.service.ent.EntMemberService;
import cn.ttsales.work.service.msg.EntHelperResponseService;
import cn.ttsales.work.web.common.util.DomUtil;
import cn.ttsales.work.wxapi.CorpApi;
import cn.ttsales.work.wxapi.EventType;
import cn.ttsales.work.wxapi.MsgType;
import cn.ttsales.work.wxapi.Result;
import cn.ttsales.work.wxapi.corp.pojo.UserResult;

/**
 * Servlet implementation class EntEvaluateServlet
 */
public class EntHelperServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(EntHelperServlet.class);
	private String token = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			"wx.corp.agentHelper.token");
	private String encodingAesKey = BundleUtil.getProperty(
			SASConstants.RESOURCE_WEIXIN, "wx.corp.agentHelper.aesKey");
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

		log.info("EntHelperServlet doGet");

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
		PrintWriter out = response.getWriter();
		Result result = CorpApi.getInstance().fromTencent(fromXMLText, encodingAesKey,
				msgSignature, token, timestamp, nonce, appId);
		if (result.getCode() == 0) {// 消息解密正确
			Document document = DomUtil.loadDocument(result.getResult());
			Element root = document.getDocumentElement();
			List<Element> elementList = DomUtil.getChildElements(root);
			Map<String, String> requestMap = new HashMap<String, String>();
			for (Element e : elementList)
				requestMap.put(e.getNodeName(), e.getTextContent());

			String fromUserName = requestMap.get("FromUserName");
			//String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");
			//String agentId = requestMap.get("AgentID");
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
				case SUBSCRIBE:{
					//避免多少发送请求
					out.print("");
					out.close();
					out=null;
					UserResult user = CorpApi.getInstance().getUser(this.appId, this.secret, fromUserName);
 					WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
					EntMemberService entMemberService = (EntMemberService) wac.getBean("entMemberService");
 					EntHelperResponseService entHelperResponseService = (EntHelperResponseService) wac.getBean("entHelperResponseService");
					OrgMember orgMember = entMemberService.getOrgMember(user.getUserid());
					boolean isFirstSub = true;
					//TODO
					if(!StringUtil.isEmpty(orgMember)&&!StringUtil.isEmpty(orgMember.getSubscribeTime())){
						isFirstSub = false;
					}   
  					orgMember = changeUserToMember(user,orgMember);
  					orgMember = entMemberService.editOrgMember(orgMember);
 					//关注用户进行个性化处理
 					entHelperResponseService.deal(orgMember,isFirstSub);
  					break;
				}
				case UNSUBSCRIBE:
					WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
					EntMemberService entMemberService = (EntMemberService) wac.getBean("entMemberService");
					OrgMember entMember = entMemberService.getOrgMember(fromUserName);
					entMember.setUnsubscribeTime(DateUtil.getCurrentDateTimeStr());
					entMember.setSubscribeState(SASConstants.MEMBER_SUB_STATE_UNSUB);
					entMemberService.editOrgMember(entMember);
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
		if(out!=null){
			out.close();
			out=null;
		}
	}

	private OrgMember changeUserToMember(UserResult user, OrgMember orgMember) {
		if(orgMember == null){
			orgMember = BeanUtil.beanCopy(user, OrgMember.class);
			orgMember.setPassword(MD5Util.EncoderByMd52("123456"));
		}
		//orgMember.set
		orgMember.setEmail(user.getEmail());
		orgMember.setGender(user.getGender());
		orgMember.setMemberId(user.getUserid());
		orgMember.setMobile(user.getTel());
		orgMember.setName(user.getName());
		orgMember.setNickName(user.getName());
		orgMember.setPhone(user.getTel());
		orgMember.setMobile(user.getMobile()); 
		orgMember.setPosition(user.getPosition());
		orgMember.setWeixin(user.getWeixinid());
		String imgUrl = user.getAvatar();
			if(!StringUtil.isEmpty(imgUrl)){
				//存的都是/64的小图
				int i=imgUrl.lastIndexOf("/");
				imgUrl = imgUrl.substring(0,i+1)+"64";
		}else{
			imgUrl="http://uu.ttsales.cn/SASWeb/resources/sas/img/no_head_pic.png";
		}
			orgMember.setHeadUrl(imgUrl);
		orgMember.setHeadPic(FileUtil.getBytesFromUrl(imgUrl).toString());
		orgMember.setSubscribeTime(DateUtil.getCurrentDateTimeStr());
		orgMember.setSubscribeState(SASConstants.MEMBER_SUB_STATE_SUB);
		return orgMember;
	}

}
