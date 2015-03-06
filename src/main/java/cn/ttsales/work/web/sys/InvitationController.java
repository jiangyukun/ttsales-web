package cn.ttsales.work.web.sys;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.FileUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.dto.ImageObject;
import cn.ttsales.work.service.ent.EntDeptmentMemberService;
import cn.ttsales.work.service.ent.EntDeptmentService;
import cn.ttsales.work.service.ent.EntMemberService;
import cn.ttsales.work.web.common.CorpOAuthController;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping(value="sys/invitation/")
public class InvitationController extends CorpOAuthController{
	private Logger log = Logger.getLogger(InvitationController.class);
	@Autowired
	private EntDeptmentMemberService entDeptmentMemberService;
	@Autowired
	private EntMemberService entMemberService;
	@Autowired
	private EntDeptmentService entDeptmentService;
	
	@Override
	@RequestMapping(value="pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> map) {
		
		ModelAndView m = new ModelAndView();
		String imgUrl = map.get("imgUrl");
		m.addObject("imgUrl", imgUrl);
		m.setViewName("redirect:/pages/sys/join.html?v="+SASConstants.SAS_VERSION);
		return m;
	}
	
	@RequestMapping(value="initData.do")
	public void initData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = CookiesUtil.getUserId(request);
		JSONObject result = new JSONObject();
		
		if (entMemberService.getOrgMember(userId) == null) { //不是企业成员
			result.put("code", "notUser");
			ResponseUtil.toClient(response, result);
			return;
		}
		
		if (entDeptmentMemberService.checkEntMemberIsBelongsEntStore(userId) == null) { //不属于门店
			result.put("code", "notSto");
			ResponseUtil.toClient(response, result);
			return;
		}
		ResponseUtil.toClient(response, result);
	}
	
	@RequestMapping("saveInviMember.do")
	public void saveMember(HttpServletRequest request,HttpServletResponse response, String name, String weixinid, String gender,
			String joinStore, String shareProduct) throws IOException {
		String userId = CookiesUtil.getUserId(request);
		JSONObject result = entMemberService.saveInviMember(name, weixinid, gender, userId, joinStore, shareProduct);
		
		if("0".equals(result.getString("code"))){
			try {
				//更新同步方法修改
			//	syncContactService.syncMember(result.getString("memberId"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ResponseUtil.toClient(response, result);
	}

	@RequestMapping(value="createInviPic.do")
	public void createInviPic(HttpServletRequest request, HttpServletResponse response, String fName, String inviId) throws IOException {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
        ServletContext servletContext = webApplicationContext.getServletContext();  
        
        String userId = CookiesUtil.getUserId(request);
        String deptName = entDeptmentService.queryThreeLevelOrgDepartment(inviId);
        
        int inviCode = Math.abs((inviId + userId + deptName).hashCode());
        String picFormat = ".png";
        String saveFormat = "png";
        JSONObject picUrls = new JSONObject();
        String path = SASConstants.TEMP_INVI_PIC_ADDR + inviCode + picFormat;
        if (FileUtil.checkExist(servletContext.getRealPath("/") + path)) {
        	log.info("存在"+path);
        	picUrls.put("succ", "ok");
        	picUrls.put("curl", request.getContextPath() + SASConstants.TEMP_INVI_PIC_ADDR_CENT + inviCode + picFormat);
        	picUrls.put("surl", request.getContextPath() + SASConstants.TEMP_INVI_PIC_ADDR + inviCode + picFormat);
        	ResponseUtil.toClient(response, picUrls);
        	return;
        }
        log.info("不存在"+path);
        fName = URLDecoder.decode(fName, "UTF-8");
        OrgMember member = entMemberService.getOrgMember(userId);
        
        InputStream headIn = null;
        if (StringUtil.isEmpty(member.getHeadUrl())) {
			headIn = servletContext.getResourceAsStream(SASConstants.NO_HEAD_URL);
		} else {
			headIn = getHeadFromWeixin(member.getHeadUrl());
			if (headIn == null) {
				headIn = servletContext.getResourceAsStream(SASConstants.NO_HEAD_URL);
			}
		}
        
        Font wFont = new Font("华康少女文字W5(P)", Font.PLAIN, 30); 
        Color wColor = new Color(0x6a6a6a);
        Color bColor = new Color(0x2b2a2a);

        InputStream cbgIn = servletContext.getResourceAsStream(SASConstants.INVITATION_CENTER_PIC);
        InputStream ercodeIn = servletContext.getResourceAsStream(SASConstants.INVITATION_ERCODE_PIC);
        
        ImageObject centP = new ImageObject(ImageIO.read(cbgIn));
        
        centP.mergeImage(headIn, 343, 540, 75, 75);
        centP.mergeImage(ercodeIn, 70, 420, 260, 260);
        
        centP.drawString("邀请函", new Font("华康少女文字W5(P)", Font.PLAIN, 48), new Color(0x2b2a2a), 250, 140);
		centP.drawString(fName+"，您好！", wFont, new Color(0x2b2a2a), 70, 195);
		centP.drawString("邀请人:" + member.getName(), wFont, new Color(0x6a6a6a), 343, 645);

		if (deptName == null) {
		  centP.drawString("【微车】邀请您参加企业推广活动，", wFont, wColor, 70, 240);
        } else {
    		centP.drawString(deptName + "邀请您参加企业推广活动，", wFont, wColor, 70, 240);
        }

		centP.drawString("动动拇指就能推广公司品牌！", wFont, wColor, 70, 275);
		centP.drawString("打开本图片，", wFont, wColor, 70, 310);
		centP.drawString("长按后选择", wFont, wColor, 70, 345);
		centP.drawString("【识别图中二维码】", wFont, bColor, 70 + 5 * 30, 345);
		centP.drawString("，", wFont, wColor, 70 + 14 * 30, 345);
		centP.drawString("点击", wFont, wColor, 70, 380);
		centP.drawString("【关注】", wFont, bColor, 70 + 2 * 30, 380);
		centP.drawString("即可！", wFont, wColor, 70 + 6 * 30, 380);
		
		InputStream bgIn = servletContext.getResourceAsStream(SASConstants.INVITATION_BACGROUND_PIC);
		ImageObject invi = new ImageObject(ImageIO.read(bgIn));
		invi.mergeImage(centP.getImage(), 20, 200, centP.getWidth(), centP.getHeight());
		invi.saveIamge(servletContext.getRealPath("/") + SASConstants.TEMP_INVI_PIC_ADDR + inviCode + picFormat, saveFormat);
		
		centP.scaleImage(1.5f);
		centP.saveIamge(servletContext.getRealPath("/") + SASConstants.TEMP_INVI_PIC_ADDR_CENT + inviCode + picFormat, saveFormat);
		
		picUrls.put("succ", "ok");
    	picUrls.put("curl", request.getContextPath() + SASConstants.TEMP_INVI_PIC_ADDR_CENT + inviCode + picFormat);
    	picUrls.put("surl", request.getContextPath() + SASConstants.TEMP_INVI_PIC_ADDR + inviCode + picFormat);
    	log.info("图片生成结束");
        ResponseUtil.toClient(response, picUrls);
	}
	
	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,"wx.corp.agentShare.id");
	}
	
	protected InputStream getHeadFromWeixin(String url) throws IOException {
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		int statusCode = client.executeMethod(getMethod);
		
		if (statusCode == 200) {
			return getMethod.getResponseBodyAsStream();
		}

		return null;
	}
}
