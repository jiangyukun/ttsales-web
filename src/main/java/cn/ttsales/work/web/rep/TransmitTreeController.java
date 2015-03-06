package cn.ttsales.work.web.rep;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.domain.BusPopularize;
import cn.ttsales.work.dto.RepTransmitDTO;
import cn.ttsales.work.service.bus.BusPopularizeService;
import cn.ttsales.work.service.rep.RepTransmitService;
import cn.ttsales.work.web.common.CorpOAuthController;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping("rep/transmitTree/")
public class TransmitTreeController extends CorpOAuthController{
	
	@Autowired
	private BusPopularizeService busPopularizeService;
	
	@Autowired
	private RepTransmitService bepTransmitService;

	@Override
	@RequestMapping("pageInit.do")
	protected ModelAndView pageInit(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> map) {
		ModelAndView m = new ModelAndView();
		String memberId = (String)map.get("userId");
		//memberId = "zhangmizhong";
		
		List<BusPopularize> busPopularizes = busPopularizeService.queryBusPopularizesByMemberId(memberId);
		m.addObject("busPopularizes", busPopularizes);
		m.addObject("userId", memberId);
		m.addObject("url", "rep/transmitTree/viewTransmitTree.do");
		m.setViewName("pages/rep/trackPopularizes");
		return m;
	}
	
	@RequestMapping("viewTransmitTree.do")
	public ModelAndView viewTransmitTree(HttpServletRequest request,HttpServletResponse response,@RequestParam String userId,@RequestParam String popularizeId) throws IOException{
		ModelAndView m = new ModelAndView();
		m.addObject("userId", userId);
		m.addObject("popularizeId", popularizeId);
		m.setViewName("pages/rep/transmitTree");
		return m;
	}
	
	@RequestMapping("createTransmitTree.do")
	public ModelAndView createTransmitTree(HttpServletRequest request,HttpServletResponse response,@RequestParam String userId,@RequestParam String popularizeId) throws IOException{
		userId = "zhangmizhong";
		List<RepTransmitDTO> repTransmitDTOs = bepTransmitService.createEdgeNodes(userId, popularizeId, "20");
		ResponseUtil.toClient(response, JsonUtil.fromList(repTransmitDTOs, new JsonConfig()));
		return null;
	}
	/**
	 * 默认注册到产品推广代理
	 */
	@Override
	protected String getAgentId() {
		return BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,"wx.corp.agentPopularize.id");
	}
}
