package cn.ttsales.work.web.report;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ttsales.work.service.sys.TransmitTempService;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping(value="report/friendsDevote/")
public class FriendsDevoteController{

	@Autowired
	TransmitTempService transmitTempService;

	@RequestMapping("getPerformanceAnalyze.do")
	public void getPerformanceAnalyze(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam String popularizeId,@RequestParam String rankRange) throws IOException{
		
		ResponseUtil.toClient(response, transmitTempService.queryPerformanceRank(popularizeId, rankRange));
 	}
}
