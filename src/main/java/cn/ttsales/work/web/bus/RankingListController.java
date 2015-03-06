package cn.ttsales.work.web.bus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.jpa.PageModel;
import cn.ttsales.work.core.jpa.PageParam;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.ListUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.BusPopularize;
import cn.ttsales.work.dto.MyRankDTO;
import cn.ttsales.work.dto.RankingListUserDTO;
import cn.ttsales.work.service.acc.AccountSchemeViewService;
import cn.ttsales.work.service.bus.BusPopularizeService;
import cn.ttsales.work.web.common.CorpOAuthController;
import cn.ttsales.work.web.common.util.CookiesUtil;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping("bus/ranking")
public class RankingListController extends CorpOAuthController {

	@Autowired
	private AccountSchemeViewService accountSchemeViewService;
	@Autowired
	private BusPopularizeService busPopularizeService;

	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map) {
		ModelAndView m = new ModelAndView();
		m.setViewName("redirect:/pages/bus/rankingList.html?v="+SASConstants.SAS_VERSION);
		return m;
	}


	@RequestMapping("queryData.do")
	protected ModelAndView queryData(HttpServletRequest request,
			HttpServletResponse response,PageParam pageParam,String schemeId)
			throws IOException {
		JSONObject result = new JSONObject();
		result.put("scheme", "");
		result.put("myRanking", "");
		result.put("rankingList", "");
		
		String userId = CookiesUtil.getUserId(request);
		//userId = "82cf80fca2c14bf69d4b78cb16754f76";
//		userId = "zhuweijie";
		schemeId = StringUtil.isEmpty(schemeId) ? this.getDefaultSchemeId(userId) : schemeId;
		result.put("scheme", schemeId);
		if(StringUtil.isEmpty(schemeId)){
			ResponseUtil.toClient(response, result);
			return null;
		}
		
		MyRankDTO  myRankDTO = null;
		if(1 == pageParam.getPage()){//排名只计算一次
			myRankDTO = this.getMyRankDTO(userId, schemeId);
			result.put("myRanking", myRankDTO == null ? "" : myRankDTO);
		}
		
		result.put("rankingList", this.getTopData(pageParam,userId, schemeId));

		ResponseUtil.toClient(response, result);
		return null;
	}
	
	private String getDefaultSchemeId(String userId){
 		List<BusPopularize> busPopularizes = busPopularizeService.queryMemberBusPopularizedByState(userId,SASConstants.POPULARIIZE_NOSHOW,SASConstants.POPULARIIZE_SHOW);
		// 如果没有文案
		if (!ListUtil.isEmpty(busPopularizes)) {
			List<BusPopularize> newBusPopularizes = new ArrayList<BusPopularize>();
			Map<String,BusPopularize> pros =new HashMap<String, BusPopularize>();
			
	 		for (BusPopularize busPopularize : busPopularizes) {
	 			pros.put(busPopularize.getProProductScheme().getProProduct().getProductId(), busPopularize);
	 		}
	 		for(String dataKey : pros.keySet())   {  
	 			newBusPopularizes.add(pros.get(dataKey)); 
	 		}
			return newBusPopularizes.get(0).getProProductScheme().getSchemeId();
			//ResponseUtil.toClient(response, "noScheme", "");
		}else{
			return null;
		}
	}
	
	private MyRankDTO getMyRankDTO(String memberId,String schemeId){
		return accountSchemeViewService.getMyRankDTOByMemberId(memberId, schemeId);
	}
	
	private PageModel<RankingListUserDTO> getTopData(PageParam pageParam,String memberId,String schemeId){
		return accountSchemeViewService.queryAccountSchemeViewDTOsBySchemeId(pageParam,schemeId,"02");
	}
	

	/*@RequestMapping("initData.do")
	protected ModelAndView initData(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		String userId = CookiesUtil.getUserId(request);
		List<BusPopularize> busPopularizes = busPopularizeService.queryBusPopularizesByMemberId(userId);
		// 如果没有文案
		if (ListUtil.isEmpty(busPopularizes)) {
			ResponseUtil.toClient(response, "noScheme", "");
			return null;
		}
		String schemeId = busPopularizes.get(0).getProProductScheme()
				.getSchemeId();
		//返回数据
		getData(request, response, userId, schemeId);
		return null;
	}
	
	@RequestMapping("updateData.do")
	protected ModelAndView updateData(HttpServletRequest request,
			HttpServletResponse response,String schemeId)
			throws IOException {
		String userId = CookiesUtil.getUserId(request);
		//返回数据
		getData(request, response, userId, schemeId);
		return null;
	}*/
	
	
	
	/**
	 * 获取排名信息并返回数据
	 * @param request
	 * @param response
	 * @param memberId
	 * @param schemeId
	 * @throws IOException
	 */
	/*private void getData(HttpServletRequest request,HttpServletResponse response,String memberId,String schemeId) throws IOException {
		// 根据文案获取排名前十的用户
		List<AccountSchemeViewDTO> accountSchemeViewDTOs = accountSchemeViewService.queryAccountSchemeViewDTOsBySchemeId(schemeId,"02");
		// 根据memberId获取我的排名
		MyRankDTO myRankDTO = accountSchemeViewService.getMyRankDTOByMemberId(memberId, schemeId);
		if (ListUtil.isEmpty(accountSchemeViewDTOs)) {
			ResponseUtil.toClient(response, "noRankingList", "");
		} else if (myRankDTO == null) {
			ResponseUtil.toClient(response, "noMyRankingList", "", null,accountSchemeViewDTOs, null);
		}else {
			JSONObject myRanObject = new JSONObject();
			myRanObject = JsonUtil.fromObject(myRankDTO, new JsonConfig());
			ResponseUtil.toClient(response, "1", String.valueOf(myRanObject),null, accountSchemeViewDTOs, null);
		}
	}*/


	@RequestMapping("initPage.do")
	protected ModelAndView initPage(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		String userId = CookiesUtil.getUserId(request);
//		userId = "zhuweijie";
		// 根据memberI获取推广信息表
		List<BusPopularize> busPopularizes = busPopularizeService.queryMemberBusPopularizedByState(userId,SASConstants.POPULARIIZE_NOSHOW,SASConstants.POPULARIIZE_SHOW);
		List<BusPopularize> newBusPopularizes = new ArrayList<BusPopularize>();
		Map<String,BusPopularize> pros =new HashMap<String, BusPopularize>();
		
 		for (BusPopularize busPopularize : busPopularizes) {
 			pros.put(busPopularize.getProProductScheme().getProProduct().getProductId(), busPopularize);
 		}
 		for(String dataKey : pros.keySet())   {  
 			newBusPopularizes.add(pros.get(dataKey)); 
 		}
 		
		ResponseUtil.toClient(response, null, newBusPopularizes, null);
		return null;
	}

	
	
	
	
	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.agentShare.id");
	}

}
