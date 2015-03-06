package cn.ttsales.work.web.sys;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.dto.MyPerformanceDTO;
import cn.ttsales.work.dto.PerformanceAnalyzeDTO;
import cn.ttsales.work.dto.TrackUserDTO;
import cn.ttsales.work.service.sys.TransmitTempService;
import cn.ttsales.work.web.common.util.ResponseUtil;

@Controller
@RequestMapping("sys/transmitTemp")
public class TransmitTempController {

	@Autowired
	private TransmitTempService transmitTempService;
	 
	
	@RequestMapping("updateTempTable.do")
	public void updateTempTable(HttpServletRequest request,
			HttpServletResponse response){
		String date ="2014-11-20";
 		while (!date.equals(DateUtil.getDateString(new Date()))) {
 			transmitTempService.updateTransmitTemp(date);
 			Date day = null;
			try {
				day = DateUtil.addDate(date, 1);
			} catch (ParseException e) {
 				e.printStackTrace();
			}
 			date = DateUtil.getDateString(day);
		}
			transmitTempService.updateTransmitTemp(date);

	}
	
	
	@RequestMapping("getMyPerformance.do")
	public void getMyPerformance(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String selectMonth,
			@RequestParam String userId) throws IOException{
		List<MyPerformanceDTO> myPerformanceDTOs = transmitTempService.getMyPerformance(selectMonth,userId);
		ResponseUtil.toClient(response,
				JsonUtil.fromList(myPerformanceDTOs, new JsonConfig()));
 	}
	
	@RequestMapping("getPerformanceAnalyze.do")
	public void getPerformanceAnalyze(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String popularizeId,@RequestParam int showType,@RequestParam String userId) throws IOException{
		List<PerformanceAnalyzeDTO> performanceAnalyzeDTOs = transmitTempService.getPerformanceAnalyze(popularizeId,showType,userId);
		ResponseUtil.toClient(response,
				JsonUtil.fromList(performanceAnalyzeDTOs, new JsonConfig()));
 	}
	
//	@RequestMapping("showTrackLineBak.do")
//	public void showTrackLine(HttpServletRequest request,
//			HttpServletResponse response, @RequestParam String popularizeId,@RequestParam String userCrossId,@RequestParam int showType) throws IOException {
//		List<TrackUserDTO> users = transmitTempService.getTrackUserDTOs(popularizeId,userCrossId);
//		JSONObject userPerformance = transmitTempService.getUserPerformance(popularizeId,userCrossId,showType);
//		 
//  		JSONObject result = new JSONObject();
// 		result.put("users", JsonUtil.fromList(users, new JsonConfig()));
// 		result.put("userPerformance", userPerformance);
//		ResponseUtil.toClient(response, result);  
//
//	}
	
	@RequestMapping("showTrackLine.do")
	public void showTrackLineBak(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String popularizeId,@RequestParam String userCrossId) throws IOException {
//		//TODO 测试
//		popularizeId = "1d2178759f2b7603b13c2a0621bb9316";
//		userCrossId  = "f9449b8df3b8492586e90c535775f5e5";
		List<TrackUserDTO> users = transmitTempService.getTrackUserDTOs(popularizeId,userCrossId);
 		ResponseUtil.toClient(response,null, users, null);
	}
	
	@RequestMapping("clearCookie.do")
	public void clearCookie(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
	        for(Cookie c :cookies ){
	        	Cookie cookie = new Cookie(c.getName(),null);
	        	cookie.setMaxAge(0);
	        	cookie.setPath("/");
        		response.addCookie(cookie);
 	        }
		}
 	}
	
}
