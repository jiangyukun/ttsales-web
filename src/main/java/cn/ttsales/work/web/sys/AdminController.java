package cn.ttsales.work.web.sys;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.domain.SysMenu;
import cn.ttsales.work.service.ent.EntMemberService;
import cn.ttsales.work.service.sys.SysMenuService;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping("admin/")
public class AdminController {
	
	@Autowired
	private EntMemberService entMemberService;
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping("login.do")
	public void login(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String username ,@RequestParam String password)
			throws IOException {
		OrgMember orgMember = entMemberService.getOrgMemberByUsername(username);
		JSONObject result = new JSONObject();
		if(orgMember==null){
			result.put("code","-2");
		}else if(!orgMember.getPassword().equals(password)){
			result.put("code","-1");
		}else{
			result.put("code","0");
			WebUtils.setSessionAttribute(request, SASConstants.LOGIN_USER, orgMember);
		}
		result.put("user", JsonUtil.fromObject(orgMember, new JsonConfig()));
		ResponseUtil.toClient(response, result);
	}
	
	@RequestMapping("getMenu.do")
	public void getMenu(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		OrgMember loginMember = (OrgMember) WebUtils.getSessionAttribute(request, SASConstants.LOGIN_USER);
		List<SysMenu> sysMenus = sysMenuService.getSysMenusByMemberId(loginMember.getMemberId());
		ResponseUtil.toClient(response, JsonUtil.fromList(sysMenus, new JsonConfig()));
	}
	
	@RequestMapping(value="editPassword.do",method=RequestMethod.POST)  
	public void editPassword(HttpServletRequest request, HttpServletResponse response,String oldPassword,String newPassword) throws IOException {
		OrgMember loginMember = (OrgMember) WebUtils.getSessionAttribute(request, SASConstants.LOGIN_USER);
		JSONObject result = new JSONObject();
		if(!oldPassword.equals(loginMember.getPassword())){
			result.put("msg", "原密码错误");
		}else{
			loginMember.setPassword(newPassword);
			WebUtils.setSessionAttribute(request, SASConstants.LOGIN_USER,loginMember);
			entMemberService.editOrgMember(loginMember);
			result.put("msg", "密码修改成功");
		}
 		ResponseUtil.toClient(response, result);
	}
	@RequestMapping(value="logout.do",method=RequestMethod.POST)  
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		WebUtils.setSessionAttribute(request, SASConstants.LOGIN_USER, null);
		result.put("message", "操作成功");
 		ResponseUtil.toClient(response, result);
	}
 	
}
