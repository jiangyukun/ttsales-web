package cn.ttsales.work.core;

import java.util.Locale;

/**
 * @author MXY,2012-7-27
 * 
 */
public class SASConstants {
	public final static String CURR_LOCALE = Locale.CHINA.toString();

	public final static String SAS_VERSION = "V1.0.9_20150205";

	public final static String RESOURCE_MSG = "messages";
	public final static String RESOURCE_BUNDLE = "Bundle";
	public final static String RESOURCE_WEIXIN = "weixin";
	public final static String RESOURCE_EMAIL = "email";
	
	public final static String LOGIN_USER = "LOGIN_USER";
	public static boolean ISPAY = true;
	
	
	public final static String LOG_PAY = "pay";
	public final static String LOG_SPLIT = "\001";
  
	
	/**树递归默认层数3*/
	public final static String TREE_DEFALUT_DEPTH = "3";
 	
	/**USER_CROSS_TYPE 用户对照类型 01:匿名 02:企业成员 03:公众*/
	public final static String USER_CROSS_TYPE_ANO = "01";
	public final static String USER_CROSS_TYPE_ENT = "02";
	public final static String USER_CROSS_TYPE_MP = "03";
	
	public final static String SYNC_STATE_DELETEED = "-2";
	public final static String SYNC_STATE_DELETE = "-1";
	public final static String SYNC_STATE_ADD = "0";
	public final static String SYNC_STATE_UPDATE = "1";
	public final static String SYNC_STATE_UPLOADED = "2";
	
	public final static String TRACK_SHOW_NUM="5";

	/**企业号关注状态1：关注，2：冻结，4：未关注*/
	public final static String MEMBER_SUB_STATE_SUB="1";
	public final static String MEMBER_SUB_STATE_UNSUB="4";
	public final static String MEMBER_SUB_STATE_LOCK="2";
	
	/**服务号关注状态1：关注，2：未关注*/
	public final static String OPEN_SUB_STATE_SUB="1";
	public final static String OPEN_SUB_STATE_UNSUB="0";
	
	/**文案显示不显示*/
	public final static String POPULARIIZE_SHOW="01";
	public final static String POPULARIIZE_NOSHOW="00";
	
	/**找不到微信头像的而使用的临时头像的文件地址*/
	public final static String NO_HEAD_URL ="/resources/sas/img/no_head_pic.png";
	/**邀请好友使用背景图的文件地址*/
	public final static String INVITATION_BACGROUND_PIC ="/resources/sas/img/invitation.png";
	/**邀请好友使用背景图的文件地址*/
	public final static String INVITATION_CENTER_PIC ="/resources/sas/img/invic.png";
	/**邀请好友使用二维码的文件地址*/
	public final static String INVITATION_ERCODE_PIC ="/resources/sas/img/qrcodeWeiche.jpg";
	/**临时存放邀请图片的文件地址*/
	public final static String TEMP_INVI_PIC_ADDR ="/resources/upload/invitmp/";
	/**临时存放邀请图片的文件地址中间部分*/
	public final static String TEMP_INVI_PIC_ADDR_CENT ="/resources/upload/invitmp/cent/";
	/**邀请图片生成失败后返回图片的文件地址*/
	public final static String WRONG_INVI_PIC_ADDR ="/resources/sas/img/invitation.jpg";
	/**微车logo图片*/
	public final static String WEI_CHE_LOGO_PIC ="/resources/sas/img/logo.png";
	
	/**我的名片底图*/
	public final static String BUSINESS_CARD_BG = "/resources/sas/img/CardBg.jpg";
	/**女*/
	public final static String FEMALE = "/resources/sas/img/female.png";
	/**男*/
	public final static String MAN = "/resources/sas/img/man.png";
	
	/**生成验证码底图地址*/
	public final static String VERIFY_BG_IMG = "/resources/sas/img/verifyCode/verifyBg.jpg";
	/**临时存放验证码图片文件地址*/
	public final static String VERIFY_IMG_PATH ="/resources/sas/upload/verifyCode";
	
}
