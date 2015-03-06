package cn.ttsales.work.web.sys;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ttsales.work.service.sys.CalculateBonusService;

@Controller
@RequestMapping("sys/calculate")
public class CalculateBonusController {
	
	@Autowired
	private CalculateBonusService calculateBonusService;
	
	
	@RequestMapping("calculateBonus.do")
	public void calculateBonus(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String bonusId)
			throws IOException {
		calculateBonusService.calculateBonusByBonusId(bonusId);
	}	 
}
