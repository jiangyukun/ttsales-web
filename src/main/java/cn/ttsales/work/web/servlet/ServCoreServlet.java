/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename SignatureServlet.java
 * @package cn.ttsales.work.web.weixin
 * @author dandyzheng
 * @date 2014-8-12
 */
package cn.ttsales.work.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BeanUtil;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.domain.RepOpenUser;
import cn.ttsales.work.location.LocationCacheManager;
import cn.ttsales.work.service.rep.RepOpenUserService;
import cn.ttsales.work.web.common.util.DomUtil;
import cn.ttsales.work.wxapi.EventType;
import cn.ttsales.work.wxapi.MpApi;
import cn.ttsales.work.wxapi.MsgType;
import cn.ttsales.work.wxapi.mp.pojo.OpenUserInfo;
import cn.ttsales.work.wxapi.mp.resp.NewsRespMsg;
import cn.ttsales.work.wxapi.mp.resp.NewsRespMsg.Articles;
import cn.ttsales.work.wxapi.mp.resp.NewsRespMsg.Item;

/**
 * @author
 * 
 */
public class ServCoreServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -423141104336507067L;
	private String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			"wx.mp.id");
	private String secret = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN, 
			"wx.mp.secret");
	private String token = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			"wx.mp.token");
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String token = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.mp.token");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		if (MpApi.getInstance().verifySignature(token, signature, timestamp, nonce)) {
			out.print(echostr);
		} else {
			out.print("failed!");
		}
		out.close();
		out = null;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验
		if (MpApi.getInstance().verifySignature(token, signature, timestamp, nonce)) {
			String respMessage = "";
			try {

				// xml请求解析，将解析结果存储在HashMap中
				Map<String, String> requestMap = new HashMap<String, String>();

				InputStream inputStream = request.getInputStream();
				Document document = DomUtil.loadDocument(inputStream);
				Element root = document.getDocumentElement();
				List<Element> elementList = DomUtil.getChildElements(root);

				// 遍历全部子节点
				for (Element e : elementList)
					requestMap.put(e.getNodeName(), e.getTextContent());

				// 释放资源
				inputStream.close();
				inputStream = null;

				// 发送方帐号（open_id）
				String fromUserName = requestMap.get("FromUserName");
				// 公众帐号
				String toUserName = requestMap.get("ToUserName");
				//Create Time
				@SuppressWarnings("unused")
				String createTime = requestMap.get("CreateTime");
				// 消息类型
				String msgType = requestMap.get("MsgType");
				//System.out.println("MsgType："+msgType);
				switch(MsgType.getByName(msgType)) {
				case EVENT:{
					// 事件类型
					String eventType = requestMap.get("Event");
					switch(EventType.getByName(eventType)) {
					case SUBSCRIBE:{
						WebApplicationContext wac = WebApplicationContextUtils
								.getRequiredWebApplicationContext(getServletContext());
						MpApi mpApi = MpApi.getInstance();
						OpenUserInfo openUserInfo = mpApi.getBaseUserinfo(appId,secret,fromUserName);
						
						RepOpenUserService repOpenUserService = (RepOpenUserService) wac.getBean("repOpenUserService");
						RepOpenUser repOpenUser = BeanUtil.beanCopy(openUserInfo, RepOpenUser.class);
						repOpenUser.setSubscribeTime(openUserInfo.getSubscribeTime());
						repOpenUser.setSubscribeState(SASConstants.OPEN_SUB_STATE_SUB);
 						repOpenUserService.editRepOpenUser(repOpenUser);
						
 						NewsRespMsg news = new NewsRespMsg();
  						news.setToUserName(fromUserName);
  						news.setFromUserName(toUserName);
 						news.setArticleCount(1);
 						Articles articles = news.new Articles();
 						List<Item> items = new ArrayList<Item>();
 						Item itemtop = news.new Item();
 						itemtop.setTitle("动动拇指，赚外快");
 						itemtop.setPicUrl(BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.website") + "/" + BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.web.resources") +"/sas/img/intro/act2.jpg");
 						itemtop.setDescription("亲，开始赚钱吧~~~");
 						itemtop.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMTA0ODAzNg==&mid=203524808&idx=1&sn=d3929a0e67553644cbec749f4b7d71b1#rd");
 						items.add(0,itemtop);
 						articles.setItem(items);
 						news.setArticles(articles);
 						respMessage = news.toXml();
 						
						/*BusReserveHanderService busReserveHanderService = (BusReserveHanderService) wac
								.getBean("busReserveHanderService");
						busReserveHanderService
								.sendCustomerSuccessed(fromUserName);*/

						//msg.append("谢谢您的关注！");
						break;
					}
					case CLICK:{
						String eventKey = requestMap.get("EventKey");
						if ("MY_RESERVE".equals(eventKey)) {// 获取获取我的预约订单
							
						/*	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
							BusReserveHanderService busReserveHanderService = (BusReserveHanderService) wac.getBean("busReserveHanderService");
							BaseRespMsg msgs = busReserveHanderService.sendCustomerReserveNewsInfo(fromUserName);
							//news.setToUserName(fromUserName);
							msgs.setFromUserName(toUserName);
							respMessage = msgs.toXml();*/
						
						}
						break;
					}
					case LOCATION:{
						String latitude = requestMap.get("Latitude");//纬度
						String longitude = requestMap.get("Longitude");//经度
						String precision = requestMap.get("Precision");//精度
						LocationCacheManager.putLocation(fromUserName, latitude, longitude, precision);
						break;
					}
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
					case SCANCODE_PUSH:{
						String eventKey = requestMap.get("EventKey");
						if ("SIGN_IN".equals(eventKey)) {// 扫码签到事件推送
							;//TODO
						
						}
						break;
					}
					case SCANCODE_WAITMSG:
						break;
					case UNSUBSCRIBE:
						WebApplicationContext wac = WebApplicationContextUtils
						.getRequiredWebApplicationContext(getServletContext());
 						RepOpenUserService repOpenUserService = (RepOpenUserService) wac.getBean("repOpenUserService");
						RepOpenUser repOpenUser =  repOpenUserService.getRepOpenUser(fromUserName);
						repOpenUser.setSubscribeState(SASConstants.OPEN_SUB_STATE_UNSUB);
						repOpenUser.setUnsubscribeTime(DateUtil.getCurrentDateTimeStr());
						repOpenUserService.editRepOpenUser(repOpenUser);
		 				break;
					case VIEW:
						break;
					default:
						break;
					}
					break;
				}
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
				default:
					break;			
				}			
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.print(respMessage);
			out.close();
		} else {
			out.print("failed!");
		}
		// super.doPost(request, response);
	}

}
