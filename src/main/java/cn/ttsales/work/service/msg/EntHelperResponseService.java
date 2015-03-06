package cn.ttsales.work.service.msg;

import javax.servlet.http.HttpServletResponse;

import cn.ttsales.org.domain.OrgMember;
import cn.ttsales.work.dto.QyClaimLotteryDTO;

public interface EntHelperResponseService {
	public void sendWelcomeMsg(String userId);
 
	public void sendLotteryMsgToBeiqi(String userId,String deptId);
	
	public void sendIntroductionMsg(String userId);

 	public void deal(OrgMember entMember, boolean isFirstSub);
 	
 	public void claimQyLottery(HttpServletResponse response, QyClaimLotteryDTO lotDto);
 	
 	public void updateLotteryUserId(String userId,String deptId);
	
}
