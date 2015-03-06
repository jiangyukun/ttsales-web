package cn.ttsales.work.service.msg.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import net.sf.json.JsonConfig;

import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.org.domain.OrgStore;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.dto.ImageObject;
import cn.ttsales.work.persistence.ent.EntMemberDao;
import cn.ttsales.work.service.ent.EntStoreService;
import cn.ttsales.work.service.msg.EntStoreResponseService;
import cn.ttsales.work.web.common.util.QRCodeUtil;
import cn.ttsales.work.wxapi.CorpApi;
import cn.ttsales.work.wxapi.corp.send.ImageSendMsg;
import cn.ttsales.work.wxapi.corp.send.TextSendMsg;

import com.google.zxing.WriterException;

@Service("entStoreResponseService")
public class EntStoreResponseServiceImpl implements EntStoreResponseService {
	Logger log = Logger.getLogger(EntStoreResponseServiceImpl.class);
	@Autowired
	EntStoreService entStoreService;
	
	@Autowired
	private EntMemberDao entMemberDao;
	private String appId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
			"wx.corp.id");
	private String secret = BundleUtil.getProperty(
			SASConstants.RESOURCE_WEIXIN, "wx.corp.secret");

	@Async
	public void sendSignInQCCode(String agentId, String userId,
			String serverName, String contextPath, String realPath) {
		log.info("sendSignInQCCode start++++++++++++++++++++");
		List<OrgStore> stores = entStoreService.getOrgStoresByUserId(userId);
		if (stores == null || stores.size() == 0) {//不属于门店
			TextSendMsg textMsg = new TextSendMsg(agentId, "You does not belongs to any store.");
			textMsg.setTouser(userId);
			CorpApi.getInstance().sendMsg(appId, secret, textMsg);
			log.info("sendSignInQCCode：member does not belongs to any store.");
			return;
		}
		
		//按销售人员签到 销售人员只属于一家门店
		OrgStore orgStore = stores.get(0);
		OrgMember orgMember = entMemberDao.getOrgMember(userId);
        
 		String url = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE,"sas.url.signInWithUser",
 				BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE,"sas.website"),
 				orgStore.getStoreId(),userId);
 		
		ByteArrayOutputStream logoOut  = new ByteArrayOutputStream();
		ImageObject logo = null;
		try {
			logo = new ImageObject(ImageIO.read(new File(realPath + SASConstants.WEI_CHE_LOGO_PIC)));
		} catch (IOException e1) {
			log.error(e1.getMessage());
			e1.printStackTrace();
		}
		
		try {
			QRCodeUtil.toQRCode(url, logo.getImage(), logoOut);
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return;
		} catch (WriterException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return;
		}
		
		ByteArrayOutputStream busiCardOut  = new ByteArrayOutputStream();
		//获取用户头像图片
        byte[] headPic = orgMember.getHeadPic().getBytes();
        BufferedImage head = null;
        if (headPic != null) {//已存储头像图片，从本地读取
        	try {
				head  = ImageIO.read(new ByteArrayInputStream(headPic));
			} catch (IOException e) {
				head = logo.getImage();
				log.error(e.getMessage());
				e.printStackTrace();
			}
        } else {
        	head = logo.getImage();
        }
		
		try {
			createBusiCard(orgStore, orgMember, realPath, head, new ByteArrayInputStream(logoOut.toByteArray()), busiCardOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String mediaId = CorpApi.getInstance()
				.uploadMedia(appId,secret,new ByteArrayPartSource("qcCode" + userId + ".jpg", busiCardOut.toByteArray()));
		ImageSendMsg imageMsg = new ImageSendMsg(agentId,mediaId);
		imageMsg.setTouser(userId);
		log.info(JsonUtil.fromObject(imageMsg, new JsonConfig()).toString());
		CorpApi.getInstance().sendMsg(appId, secret, imageMsg);
		
//		//以下为按店签到代码		
//		List<EntStore> stores = entStoreService.getEntStoresByUserId(userId);
//		//发送图片消息
//		for (int i = 0; i < stores.size(); i++) {
//			EntStore store = stores.get(i);
//			
//			TextSendMsg textMsg = new TextSendMsg(agentId, store.getStoreName());
//			textMsg.setTouser(userId);
//			
//			
//			byte[] qcCode = store.getTwoDimenPic();
//			if (qcCode == null)
//				qcCode = entStoreService.getQCCode(store.getStoreId(), serverName, contextPath);
//			
//			String mediaId = CorpApi.getInstance()
//					.uploadMedia(appId,secret,new ByteArrayPartSource("qcCode" + store.getStoreId() + ".jpg", qcCode));
//			ImageSendMsg imageMsg = new ImageSendMsg(agentId,mediaId);
//			imageMsg.setTouser(userId);
//
//			log.info(JsonUtil.fromObject(imageMsg, new JsonConfig()).toString());
//			
//			CorpApi.getInstance().sendMsg(appId, secret, textMsg);
//			CorpApi.getInstance().sendMsg(appId, secret, imageMsg);
//		}
		
		
//		//发送图文消息
//		MPNewsSendMsg msg = new MPNewsSendMsg();
//		msg.setAgentid(agentId);
//		msg.setMsgtype(MsgType.MESSAGE_TYPE_MPNEWS);
//		msg.setSafe(WxConstants.UN_SAFE);
//		msg.setTouser(userId);
//		List<MPNewsSendMsg.Article> articles = new ArrayList<MPNewsSendMsg.Article>();
//		for (int i = 0; i < stores.size(); i++) {
//			EntStore store = stores.get(i);
//			byte[] qcCode = store.getTwoDimenPic();
//			if (qcCode == null)
//				qcCode = entStoreService.getQCCode(store.getStoreId(), serverName, contextPath);
//			
//			String mediaId = CorpApi.getInstance()
//					.uploadMedia(appId,secret,new ByteArrayPartSource("qcCode" + store.getStoreId() + ".jpg", qcCode));
//
//			MPNewsSendMsg.Article article = new MPNewsSendMsg.Article();
//			article.setThumb_media_id(mediaId);
//			article.setContent(stores.get(i).getStoreName());
//			article.setTitle(stores.get(i).getStoreName());
//			article.setShow_cover_pic("1");
//
//			articles.add(article);
//		}
//		msg.setMpnews(new MPNewsSendMsg.Articles(articles));
//		log.info(JsonUtil.fromObject(msg, new JsonConfig()).toString());
//		CorpApi.getInstance().sendMsg(appId, secret, msg);
		log.info("sendSignInQCCode end++++++++++++++++++++");
	}
	
	private static void createBusiCard(OrgStore orgStore, OrgMember orgMember, String realPath, BufferedImage head, InputStream erCodeIn, OutputStream busiCardOut) throws IOException {
		ImageObject card = new ImageObject(ImageIO.read(new File(realPath + SASConstants.BUSINESS_CARD_BG)));
		
		Font afont = new Font("黑体", Font.PLAIN, 24);
		Color acolor = new Color(0x505050);
		
		card.circleRectangleIamge(head, 25, 15, 130, 130, 15, 15);
		//sex
		File sex;
		if (orgMember.getGender().equals("0")) {
			sex = new File(realPath + SASConstants.MAN);
		} else {
			sex = new File(realPath + SASConstants.FEMALE);
		}
		//name
		String name = "";
		if (StringUtil.isEmpty(orgMember.getName())) {
			name = "无名者";
		} else {
			name= orgMember.getName();
		}
		
		card.mergeImage(ImageIO.read(sex), 210 + countCharLength(name), 35, 22, 26);
		card.mergeImage(erCodeIn, 135, 305, 280, 280);
		card.drawString(name, new Font("黑体", Font.PLAIN, 33), new Color(0x4d4d4d), 185, 60);
		
		if (StringUtil.isEmpty(orgMember.getMobile())) {
			card.drawString("微信号：" + orgMember.getWeixin(), new Font("黑体", Font.PLAIN, 29), new Color(0x787878), 185, 115);
		} else {
			card.drawString("手机号：" + orgMember.getMobile(), new Font("黑体", Font.PLAIN, 29), new Color(0x787878), 185, 115);
		}
		
		card.drawString("门店：" + orgStore.getStoreName(), afont, acolor, 25, 710);
		card.drawString("地址：" + orgStore.getAddress(), afont, acolor, 25, 745);

		ImageIO.write(card.getImage(), "jpg", busiCardOut);
	}
	
	public static int countCharLength(String s) {
		int length = 0;

		for (int i = 0, len = s.length(); i < len; i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				length += 16;
			} else if (c >= 'a' && c <= 'z') {
				length += 16;
			} else if (c >= 'A' && c <= 'Z') {
				length += 17;
			} else {
				length += 33;
			}
		}
		return length;
	}
}
