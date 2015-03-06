package cn.ttsales.work.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.domain.BusUserBonus;
import cn.ttsales.work.service.bus.BusUserBonusService;
import cn.ttsales.work.service.ent.EntMemberService;
import cn.ttsales.work.wxapi.CorpApi;
import cn.ttsales.work.wxapi.MsgType;
import cn.ttsales.work.wxapi.corp.send.MsgSendResult;
import cn.ttsales.work.wxapi.corp.send.TextSendMsg;
import cn.ttsales.work.wxapi.corp.send.TextSendMsg.Text;

@Controller
@RequestMapping("sys/sendMsg")
public class YlksCouponMessage {
	
	@Autowired
	private EntMemberService entMemberService;
	@Autowired
	private BusUserBonusService busUserBonusService;
	
	@RequestMapping("sendMsg12.do")
	public void sendMsg12(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		String corpId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.id");
		String secret = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.secret");
		String agentId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.agentPopularize.id");
	 
		List<BusUserBonus> busUserBonus = busUserBonusService.getAllBusUserBonuss();
		BusUserBonus zxb = new BusUserBonus();
		zxb.setUserId("zhaoxiaobin");
		busUserBonus.add(zxb);
  		for (BusUserBonus b : busUserBonus) {
			TextSendMsg t = new TextSendMsg();
			t.setAgentid(agentId);
			t.setSafe("0");
			t.setText(new Text("亲，伊莱克斯“双12赚外快”送您的红包即将到期啦，领取时间截止到2014年12月25日24时，逾期未领将作自动放弃处理。赶快去领取吧~~"));
			t.setTouser(b.getUserId());
			t.setMsgtype(MsgType.TEXT.getName());
			MsgSendResult result = CorpApi.getInstance().sendMsg(corpId, secret, t);
			System.out.println(result.toString());
		}
  		for (BusUserBonus b : busUserBonus) {
			TextSendMsg t = new TextSendMsg();
			t.setAgentid(agentId);
			t.setSafe("0");
			t.setText(new Text("红包到帐需要先领取再拆开哦，方法如下： \n1.点击《我的红包》，点击《领取》，再点击《确认领取》，会看到提示消息。 \n2.返回到微信主界面，点击《服务通知》或《微信红包》，可以看到一条《你收到一个红包》的消息。点击这条消息。 \n3.在弹出的界面中，点击拆开红包，页面提示“恭喜你，成功领取【微车】的红包”。 \n4.已成功领取红包。可以在《微信钱包》查看红包到帐金额。"));
			t.setTouser(b.getUserId());
			t.setMsgtype(MsgType.TEXT.getName());
			MsgSendResult result = CorpApi.getInstance().sendMsg(corpId, secret, t);
			System.out.println(result.toString());
		}
	}
	
	@RequestMapping("sendMsg50.do")
	public void sendMsg50(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		String corpId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.id");
		String secret = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.secret");
		String agentId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.agentPopularize.id");
	 
		List<BusUserBonus> busUserBonus = busUserBonusService.getBusBonusesByUserId(null);
		BusUserBonus s = new BusUserBonus();
		s.setUserId("d60da59300ef4872a0dc4d94f6ab0862");
		busUserBonus.add(s);
		BusUserBonus zxb = new BusUserBonus();
		zxb.setUserId("zhaoxiaobin");
		busUserBonus.add(zxb);
  		for (BusUserBonus b : busUserBonus) { 
			TextSendMsg t = new TextSendMsg();
			t.setAgentid(agentId);
			t.setSafe("0");
			t.setText(new Text("伊莱克斯“双12赚外快”活动，已经圆满结束。 \n恭喜您，您的转发带来的阅读量，排名在前100名， 可以得到50元微信现金红包。\n 同时，伊莱克斯“圣诞奇幻购”活动已在天猫旗舰店和天猫庆顺店开始，您将有机会获得周大福0.1克圣诞祈福金条。请您持续关注哦！ \n微信红包领取时间截止到2014年12月27日24时，逾期未领将作自动放弃处理。"));
			t.setTouser(b.getUserId());
			t.setMsgtype(MsgType.TEXT.getName());
			MsgSendResult result = CorpApi.getInstance().sendMsg(corpId, secret, t);
			System.out.println(result.toString());
		}
  		for (BusUserBonus b : busUserBonus) { 
			TextSendMsg t = new TextSendMsg();
			t.setAgentid(agentId);
			t.setSafe("0");
			t.setText(new Text("红包到帐需要先领取再拆开哦，方法如下： \n1.点击《我的红包》，点击《领取》，再点击《确认领取》，会看到提示消息。 \n2.返回到微信主界面，点击《服务通知》或《微信红包》，可以看到一条《你收到一个红包》的消息。点击这条消息。 \n3.在弹出的界面中，点击拆开红包，页面提示“恭喜你，成功领取【微车】的红包”。 \n4.已成功领取红包。可以在《微信钱包》查看红包到帐金额。"));
			t.setTouser(b.getUserId());
			t.setMsgtype(MsgType.TEXT.getName());
			MsgSendResult result = CorpApi.getInstance().sendMsg(corpId, secret, t);
			System.out.println(result.toString());
		}
  		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String corpId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.id");
		String secret = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.secret");
		String agentId = BundleUtil.getProperty(SASConstants.RESOURCE_WEIXIN,
				"wx.corp.agentPopularize.id");
		
	//	String url = "http://uu.ttsales.cn/SASWeb/report/myBonus/init.do";
	//	String picurl = "http://uu.ttsales.cn/SASWeb/resources/sas/img/scheme/201001/ylks1.jpg";
		TextSendMsg t = new TextSendMsg();
		t.setAgentid(agentId);
		t.setSafe("0");
		t.setText(new Text("红包到帐需要先领取再拆开哦，方法如下： \n1.点击《我的红包》，点击《领取》，再点击《确认领取》，会看到提示消息。 \n2.返回到微信主界面，点击《服务通知》或《微信红包》，可以看到一条《你收到一个红包》的消息。点击这条消息。 \n3.在弹出的界面中，点击拆开红包，页面提示“恭喜你，成功领取【微车】的红包”。 \n4.已成功领取红包。可以在《微信钱包》查看红包到帐金额。"));
		t.setTouser("sunqiaohong");
		t.setMsgtype(MsgType.TEXT.getName());
		MsgSendResult result = CorpApi.getInstance().sendMsg(corpId, secret, t);
		System.out.println(result.toString());
	}
}
