package cn.ttsales.work.web.bus;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.service.ent.EntMemberService;
import cn.ttsales.work.service.rep.RepTransmitService;
import cn.ttsales.work.web.common.CorpOAuthController;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping(value="bus/coupon/")
public class CouponController extends CorpOAuthController{
	
	@Autowired
	RepTransmitService repTransimitService;
	@Autowired
	EntMemberService entMemberService;

	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> map) {
		ModelAndView m = new ModelAndView();
		m.setViewName("redirect:/pages/htmls/hideAndJump.html");
		String userId = map.get("userId");
		if (StringUtil.isEmpty(userId)) {
			m.addObject("msg","-1");
			return m; 
		} else {
			OrgMember member = entMemberService.getOrgMember(userId);
			if (StringUtil.isEmpty(member.getHeadUrl())) {
				m.addObject("msg","-2");
				return m;
			} 
			m.addObject("msg","0");
			m.addObject("url", "http://m.yhd.com/sale/121245");
			m.setViewName("redirect:/pages/htmls/hideAndJump.html");
			return m;
		}
	}

	@RequestMapping(value="obtainCoupon.do")
	public void getKaJuan(HttpServletRequest request, HttpServletResponse response,String userCrossId, String schemeId){
		int rank = repTransimitService.queryTransmitRank(userCrossId, schemeId);
		JSONObject result = new JSONObject(); 
		if (rank == 0) {
			result.put("msg", "请转发给好友后过一会会再来领取哦~");
		} else {
			result.put("msg", "ok");
			result.put("url", "http://m.yhd.com/sale/121239");
		}
		try {
			ResponseUtil.toClient(response, result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,"wx.corp.agentShare.id");
	}
}
