package cn.ttsales.work.web.sys;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.jpa.PageModel;
import cn.ttsales.work.core.jpa.PageParam;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.service.ent.EntMemberService;
import cn.ttsales.work.web.common.CorpOAuthController;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping("sys/myFriends")
public class MyFriendsController extends CorpOAuthController {
	
	@Autowired
	private EntMemberService entMemberService;
	
	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map) {
		//String userId = map.get("userId");
		ModelAndView m = new ModelAndView();
		//String memberId = (String)map.get("userId");
		/*"redirect:/test.do";
		mav = new ModelAndView(url)*/
		//m.addObject("memberId", map.get("userId"));
		m.setViewName("redirect:/pages/sys/myFriends.html?v="+SASConstants.SAS_VERSION);
		return m;
		
		
		
	}

	@RequestMapping("initData.do")
	protected ModelAndView initData(HttpServletRequest request,
			HttpServletResponse response,PageParam pageParam) throws IOException {
		//ModelAndView m = new ModelAndView();
		String userId  = CookiesUtil.getUserId(request);
		//userId="d0a677a532f44c2784fa9651a40bd349";
		//userId = "zhaoxiaobin";
		//如果非企业号成员打开本页面,跳转到 【关于我们】页面
		if(StringUtil.isEmpty(userId)){
			ResponseUtil.toClient(response, "jumpToAboutUs", "");
		}else{
			PageModel<OrgMember> entMembers = entMemberService.getOrgMembersByPOrgMemberId(userId,pageParam);
			//ResponseUtil.toClient(response, entMembers);
			ResponseUtil.toClient(response, JsonUtil.fromObject(entMembers, new JsonConfig()));
		}
		
		return null;
	}
	
	
	@RequestMapping("deleteFriend.do")
	public void deleteFriend(HttpServletRequest request,
			HttpServletResponse response,String friendId) throws IOException{
		JSONObject jsonObject = new JSONObject();
		if(StringUtil.isEmpty(friendId)){
			jsonObject.put("code", "0");
		}else{
			entMemberService.removeOrgMember(friendId);
			jsonObject.put("code", "1");
		}
		ResponseUtil.toClient(response, jsonObject);
	}
	
	
	
	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,"wx.corp.agentShare.id");
	}
	
}
