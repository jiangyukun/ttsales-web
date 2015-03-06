package cn.ttsales.work.service.msg.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.BusLottery;
import cn.ttsales.work.domain.SysLottery;
import cn.ttsales.work.domain.WxUserBonus;
import cn.ttsales.work.dto.QyClaimLotteryDTO;
import cn.ttsales.work.persistence.ent.EntDeptmentMemberDao;
import cn.ttsales.work.service.bus.BusLotteryService;
import cn.ttsales.work.service.msg.EntHelperResponseService;
import cn.ttsales.work.service.rep.RepReadCountService;
import cn.ttsales.work.service.sys.SysLotteryService;
import cn.ttsales.work.service.sys.WxUserBonusService;
import cn.ttsales.work.web.common.util.ResponseUtil;
import cn.ttsales.work.wxapi.CorpApi;
import cn.ttsales.work.wxapi.corp.send.NewsSendMsg;
import cn.ttsales.work.wxapi.corp.send.NewsSendMsg.Article;
import cn.ttsales.work.wxapi.corp.send.NewsSendMsg.Articles;
import cn.ttsales.work.wxapi.corp.send.TextSendMsg;

@Service("entHelperResponseService")
public class EntHelperResponseServiceImpl implements EntHelperResponseService {
	Logger log = Logger.getLogger(EntHelperResponseServiceImpl.class);
	
	private String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			"wx.corp.id");
	private String secret = BundleUtil.getProperty(
			SASConstants.RESOURCE_WEIXIN, "wx.corp.secret");
	private String helperAngentId = BundleUtil.getProperty(
			SASConstants.RESOURCE_WEIXIN, "wx.corp.agentHelper.id");
	private String friendAngentId = BundleUtil.getProperty(
			SASConstants.RESOURCE_WEIXIN, "wx.corp.agentShare.id");
	private String guangqiAngentId = BundleUtil.getProperty(
			SASConstants.RESOURCE_WEIXIN, "wx.corp.agentGuangqi.id");
	
	private static ConcurrentLinkedQueue<String> claimQueue = new ConcurrentLinkedQueue<String>();
	private static byte[] claimLock = new byte[0];
	
	private static ConcurrentLinkedQueue<Map<String, Object>> qyLotteryQueue = new ConcurrentLinkedQueue<Map<String, Object>>();
	private static byte[] qyLotteryLock = new byte[0];

	@Autowired
	private EntDeptmentMemberDao entDeptmentMemberDao;
	@Autowired
	private SysLotteryService sysLotteryService;
	@Autowired
	private RepReadCountService repReadCountService;
	@Autowired
	private BusLotteryService busLotteryService;

	@Autowired
	private WxUserBonusService wxUserBonusService;
	
	public void deal(OrgMember orgMember,boolean isFirstSub) {
 		sendIntroductionMsg(orgMember.getMemberId());  

		//北汽的用户401
		String beiqiDeptId="401";
		if(entDeptmentMemberDao.checkUserInDept(orgMember.getMemberId(),beiqiDeptId)){
			//发送消息
			sendLotteryMsgToBeiqi(orgMember.getMemberId(),beiqiDeptId); 
			//修改分配抽奖次序
			if(isFirstSub){
				updateLotteryUserId(orgMember.getMemberId(),beiqiDeptId);
				if(!orgMember.getParentMemberId().equals("0")){
					sendLotteryMsgToBeiqi2(orgMember.getParentMemberId(),beiqiDeptId); 
	 				updateLotteryUserId(orgMember.getParentMemberId(),beiqiDeptId);
				}
			} 
 		}
		//绿藤的用户490
		String lvtengDeptId="490";
		if(entDeptmentMemberDao.checkUserInDept(orgMember.getMemberId(),lvtengDeptId)){
			//发送消息
			sendLotteryMsgToBeiqi(orgMember.getMemberId(),lvtengDeptId); 
			//修改分配抽奖次序
			if(isFirstSub){
				updateLotteryUserId(orgMember.getMemberId(),lvtengDeptId);
				if(!orgMember.getParentMemberId().equals("0")){
					sendLotteryMsgToBeiqi2(orgMember.getParentMemberId(),lvtengDeptId); 
	 				updateLotteryUserId(orgMember.getParentMemberId(),lvtengDeptId);
				}
			}
		}
		//正通嵊州的用户40402
		String aozeDeptId = "40402";
		if(entDeptmentMemberDao.checkUserInDept(orgMember.getMemberId(),aozeDeptId)){
			//发送消息
			sendLotteryMsgToZhengtong(orgMember.getMemberId(),aozeDeptId); 
			//修改分配抽奖次序
			if(isFirstSub){
				updateLotteryUserId(orgMember.getMemberId(),aozeDeptId);
				if(!orgMember.getParentMemberId().equals("0")){
					sendLotteryMsgToZhengtong2(orgMember.getParentMemberId(),aozeDeptId); 
	 				updateLotteryUserId(orgMember.getParentMemberId(),aozeDeptId);
				}
			}
		}
		//正通上海的用户40301
		String zhengtongDeptId = "40401";
		if(entDeptmentMemberDao.checkUserInDept(orgMember.getMemberId(),zhengtongDeptId)){
			//发送消息
			sendLotteryMsgToZhengtong(orgMember.getMemberId(),zhengtongDeptId); 
			//修改分配抽奖次序
			if(isFirstSub){
				updateLotteryUserId(orgMember.getMemberId(),zhengtongDeptId);
				if(!orgMember.getParentMemberId().equals("0")){
					sendLotteryMsgToZhengtong2(orgMember.getParentMemberId(),zhengtongDeptId); 
	 				updateLotteryUserId(orgMember.getParentMemberId(),zhengtongDeptId);
				}
			}
		}
		
		//广汽的用户405
		String guangqiDeptId = "405";
		if(entDeptmentMemberDao.checkUserInDept(orgMember.getMemberId(),guangqiDeptId)){
			//发送消息
			sendLotteryMsgToGuangQi(orgMember.getMemberId(),guangqiDeptId); 
			//修改分配抽奖次序
			if(isFirstSub){
				//红包消息
				sengHBMsgTOGuangQi(orgMember);
				QyClaimLotteryDTO lotDto = new QyClaimLotteryDTO();
				lotDto.setUserId(orgMember.getMemberId());
				lotDto.setLotteryType("01");
				lotDto.setDeptId(guangqiDeptId);
				lotDto.setSchemeId("501001");
				claimQyLottery(null, lotDto);
				if(!orgMember.getParentMemberId().equals("0")){
					QyClaimLotteryDTO lotDtoP = new QyClaimLotteryDTO();
					lotDtoP.setUserId(orgMember.getParentMemberId());
					lotDtoP.setLotteryType("01");
					lotDtoP.setDeptId(guangqiDeptId);
					lotDtoP.setSchemeId("501001");
					sendLotteryMsgToGuangQi2(orgMember.getParentMemberId(), guangqiDeptId); ;
					claimQyLottery(null, lotDtoP);
				}
			}
		}
	}
	
	private void sengHBMsgTOGuangQi(OrgMember orgMember) {
		WxUserBonus wxUserBonus = wxUserBonusService.getWxUserBonusByOwnerIdAndbonusId(orgMember.getMemberId(),"gqslrw1");
		if(!StringUtil.isEmpty(wxUserBonus)){
			String msgText =  "亲，您收到了一个现金红包，请到【我的红包】中领取。";
 			TextSendMsg textMsg = new TextSendMsg(guangqiAngentId, msgText);
			textMsg.setTouser(orgMember.getMemberId());
			CorpApi.getInstance().sendMsg(appId, secret, textMsg);
		}
	}

	public void claimQyLottery(HttpServletResponse response, QyClaimLotteryDTO lotDto) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lotDto", lotDto);
		map.put("response", response);
		
		qyLotteryQueue.add(map);
		while(!qyLotteryQueue.isEmpty()) {
			synchronized(qyLotteryLock) {
				Map<String, Object> tmap = qyLotteryQueue.poll();
				if (tmap == null) {
					continue;
				}
				System.err.println("===================qy request in=================================");
				JSONObject result = new JSONObject();
				try {
					lotDto = (QyClaimLotteryDTO)map.get("lotDto");
					int sum = 1;		//兑换总数
					int LotCountbyRead = 0; //有效阅读数兑换的抽奖次数
					int validReadCount = 0; //有效阅读数
					
					if("02".equals(lotDto.getLotteryType())){ //阅读数兑换抽奖数
						LotCountbyRead = busLotteryService.queryLotteryCountByReadGet(lotDto.getDeptId(), lotDto.getUserId(), lotDto.getOpenId());
						
					//	if (lotDto.getUserId() != null && lotDto.getOpenId() != null) {
						validReadCount = repReadCountService.querySomeOneValidReadCount(lotDto.getSchemeId(), lotDto.getUserId(), lotDto.getOpenId());
				//		} else if (lotDto.getUserId() != null) {
				//			validReadCount = repReadCountService.querySomeOneValidReadCount(lotDto.getSchemeId(), lotDto.getUserId());
				//		} else if (lotDto.getOpenId() != null) {
				//			validReadCount = repReadCountService.querySomeOneValidReadCount(lotDto.getSchemeId(), lotDto.getOpenId());
				//		}
						
						//TODO start 阅读数异常处理代码 
						if(!StringUtil.isEmpty(lotDto.getOpenId())&&StringUtil.isEmpty(lotDto.getUserId())){
							if(validReadCount > 80){
								validReadCount = 80;
							} 
						}
						//TODO end
						sum = (validReadCount / 2) - LotCountbyRead;
						sum = sum < 0 ? 0 : sum;
		
					} else if ("03".equals(lotDto.getLotteryType())) {
						String id = lotDto.getOpenId() == null ? lotDto.getUserId(): lotDto.getOpenId();
						if (busLotteryService.isHaveTransmitLottery(id, lotDto.getSchemeId())) {
							sum = 0;
						}
					}
					
					if (sum == 0) {//抽奖数不足
						result.put("state", "empty-readCount");
						result.put("readCount", "0");
					} else {
						List<BusLottery> blots = busLotteryService.queryLotterysByRank(lotDto.getDeptId(), sum);
						
						if (ArrayUtil.isEmpty(blots)) {
							result.put("state", "not-enough-convert");
							int rc = validReadCount - (LotCountbyRead * 2);
							result.put("readCount", rc < 0 ? 0 : rc);
						} else {
							for (BusLottery blot : blots) {
								if (!StringUtil.isEmpty(lotDto.getUserId())) {
									blot.setUserId(lotDto.getUserId());
									blot.setUserType("02");
								} else if (!StringUtil.isEmpty(lotDto.getOpenId())) {
									blot.setUserId(lotDto.getOpenId());
									blot.setUserType("03");
								}
								blot.setLotteryType(lotDto.getLotteryType());
								blot.setSchemeId(lotDto.getSchemeId());
							}
							busLotteryService.editBusLotterys(blots);
							result.put("state", "success");
							result.put("faileCount", sum - blots.size());
							result.put("successCount", blots.size());
							int rc = validReadCount - 2 * (blots.size() + LotCountbyRead);
							result.put("readCount", rc < 0 ? 0 : rc);
						}
					}
					
					Object r = tmap.get("response");
					if (r != null) {
						ResponseUtil.toClient((HttpServletResponse)r, result);
					}
				} catch (Exception e) {
					e.printStackTrace();
					result.put("state", "application-error");
					Object r = tmap.get("response");
					if (r != null) {
						try {
							ResponseUtil.toClient((HttpServletResponse)r, result);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				result.put("lotteryType", lotDto.getLotteryType());
				System.err.println("=================qy end ==" + result.toString() + "========================");
			}
		}
	}
 
	public void updateLotteryUserId(String userId, String deptId) {
		claimQueue.add(userId + "," + deptId);
		while(!claimQueue.isEmpty()) {
			synchronized(claimLock) {
				System.err.println("===================request in=================================");
				String uId = claimQueue.poll();
				if (StringUtil.isEmpty(uId)) {
					continue;
				}
				SysLottery lottery = null;
				String[] ids = uId.split(",");
				userId = ids[0];
				deptId = ids[1];
				try {
					int r = 0;
					lottery = sysLotteryService.getLotteryByRank(deptId);
					if (lottery == null) {
						r = -1;
					} else {
						System.err.println(lottery.getLotteryId());
						r = sysLotteryService.updateLotteryUserId(userId, lottery.getLotteryId());
					}
 					if (r == -1) {
						System.out.println("lottery all receive~");
					} else if (r == 0) {
						claimQueue.add(uId);
					} else {
						System.out.println(lottery.getLotteryId() + "   lottery receive success~");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(Thread.currentThread().getName() + "  lotteryId:  " + lottery.getLotteryId() + "  lottery receive faile~");
				}
			}
		}
	
	}
	
	@Async
	public void sendWelcomeMsg(String userId) {
		String msgText = BundleUtil.getProperty(
				SASConstants.RESOURCE_BUNDLE, "sas.welcome.text.helper");
		
		TextSendMsg textMsg = new TextSendMsg(helperAngentId, msgText);
		textMsg.setTouser(userId);
		CorpApi.getInstance().sendMsg(appId, secret, textMsg);
		
	}
	
	@Async
	public void sendIntroductionMsg(String userId) {
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.website"); 
   		String url = baseUrl+"/pages/intro/introduction.html?v="+SASConstants.SAS_VERSION;
		String picurl = baseUrl+"/resources/sas/img/intro/act.jpg"; 
		NewsSendMsg newsMsg = new NewsSendMsg(helperAngentId);  
 		newsMsg.setTouser(userId);  
 		List<Article> articles = new ArrayList<Article>();
		articles.add(new Article("微车介绍&操作指南","亲，来看看怎么用微车赚外快吧~~不是销售，也能拿提成！",url,picurl));
		Articles news = new Articles();  
		news.setArticles(articles); 
		newsMsg.setNews(news);
 		CorpApi.getInstance().sendMsg(appId, secret, newsMsg);
 	}
	
	@Async
	public void sendLotteryMsgToGuangQi(String userId,String deptId) {
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.website"); 
    		String url = baseUrl+"/qy/lottery/init.do?appId="+appId+"&deptId="+deptId;
		String picurl = baseUrl+"/resources/sas/img/lottery/lottery_guangqi.jpg";
		NewsSendMsg newsMsg = new NewsSendMsg(guangqiAngentId); 
 		newsMsg.setTouser(userId);  
 		List<Article> articles = new ArrayList<Article>();
		articles.add(new Article("幸运大转盘,现金红包等你拿","欢迎加入【广汽三菱】全员营销活动，您已获一次抽奖机会，成功邀请朋友加入，可得更多抽奖机会！",url,picurl));
		Articles news = new Articles(); 
		news.setArticles(articles); 
		newsMsg.setNews(news);
 		CorpApi.getInstance().sendMsg(appId, secret, newsMsg);
 	}
	
	@Async
	public void sendLotteryMsgToGuangQi2(String userId,String deptId) {
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.website"); 
    		String url = baseUrl+"/qy/lottery/init.do?appId="+appId+"&deptId="+deptId;
		String picurl = baseUrl+"/resources/sas/img/lottery/lottery_guangqi.jpg";
		NewsSendMsg newsMsg = new NewsSendMsg(guangqiAngentId); 
 		newsMsg.setTouser(userId);  
 		List<Article> articles = new ArrayList<Article>();
		articles.add(new Article("幸运大转盘,现金红包等你拿","邀请的好友加入啦，您又多了一次抽奖机会，快去试试手气！",url,picurl));
		Articles news = new Articles(); 
		news.setArticles(articles); 
		newsMsg.setNews(news);
 		CorpApi.getInstance().sendMsg(appId, secret, newsMsg);
 	}
	
	@Async
	public void sendLotteryMsgToZhengtong(String userId,String deptId) {
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.website"); 
    		String url = baseUrl+"/sys/lottery/init.do?appId="+appId+"&deptId="+deptId;
		String picurl = baseUrl+"/resources/sas/img/lottery/lottery_audi.jpg";
		NewsSendMsg newsMsg = new NewsSendMsg(friendAngentId); 
 		newsMsg.setTouser(userId);  
 		List<Article> articles = new ArrayList<Article>();
		articles.add(new Article("幸运大转盘,红包等你拿","欢迎加入【正通】全员营销活动，您已获一次抽奖机会，成功邀请朋友加入，可得更多抽奖机会！",url,picurl));
		Articles news = new Articles(); 
		news.setArticles(articles); 
		newsMsg.setNews(news);
 		CorpApi.getInstance().sendMsg(appId, secret, newsMsg);
 	}
	
	@Async
	public void sendLotteryMsgToZhengtong2(String userId,String deptId) {
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.website"); 
    		String url = baseUrl+"/sys/lottery/init.do?appId="+appId+"&deptId="+deptId;
		String picurl = baseUrl+"/resources/sas/img/lottery/lottery_audi.jpg";
		NewsSendMsg newsMsg = new NewsSendMsg(friendAngentId); 
 		newsMsg.setTouser(userId);  
 		List<Article> articles = new ArrayList<Article>();
		articles.add(new Article("幸运大转盘,红包等你拿","邀请的好友加入啦，您又多了一次抽奖机会，快去试试手气！",url,picurl));
		Articles news = new Articles(); 
		news.setArticles(articles); 
		newsMsg.setNews(news);
 		CorpApi.getInstance().sendMsg(appId, secret, newsMsg);
 	}
	
	
	@Async
	public void sendLotteryMsgToBeiqi(String userId,String deptId) {
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.website"); 
    		String url = baseUrl+"/sys/lottery/init.do?appId="+appId+"&deptId="+deptId;
		String picurl = baseUrl+"/resources/sas/img/lottery/beiqi.jpg";
		NewsSendMsg newsMsg = new NewsSendMsg(friendAngentId); 
 		newsMsg.setTouser(userId);  
 		List<Article> articles = new ArrayList<Article>();
		articles.add(new Article("幸运大转盘,红包等你拿","欢迎加入【北京汽车】全员营销活动，您已获一次抽奖机会，成功邀请朋友加入，可得更多抽奖机会！",url,picurl));
		Articles news = new Articles(); 
		news.setArticles(articles); 
		newsMsg.setNews(news);
 		CorpApi.getInstance().sendMsg(appId, secret, newsMsg);
 	}
	
	@Async
	public void sendLotteryMsgToBeiqi2(String userId,String deptId) {
		String baseUrl = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE, "sas.website"); 
    		String url = baseUrl+"/sys/lottery/init.do?appId="+appId+"&deptId="+deptId;
		String picurl = baseUrl+"/resources/sas/img/lottery/beiqi.jpg";
		NewsSendMsg newsMsg = new NewsSendMsg(friendAngentId); 
 		newsMsg.setTouser(userId);  
 		List<Article> articles = new ArrayList<Article>();
		articles.add(new Article("幸运大转盘,红包等你拿","邀请的好友加入啦，您又多了一次抽奖机会，快去试试手气！",url,picurl));
		Articles news = new Articles(); 
		news.setArticles(articles); 
		newsMsg.setNews(news);
 		CorpApi.getInstance().sendMsg(appId, secret, newsMsg);
 	}
}
