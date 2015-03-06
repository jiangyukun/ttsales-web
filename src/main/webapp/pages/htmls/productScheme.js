var productScheme = function() {
		
	var global = {
		url: $.sas.util.getUrl(),
		popularizeId : "",
		memberId : "",
		pTransmitId : "",
		fTransmitId : "",
		transmitId : "",
		schemeId : "",
		descContent : "",
		shareTitle : "",
		imgUrl : $.sas.common.getRootPath() + "/resources/"
	};
	
	function  checkUserCrossId() {//生成用户标识
 		if (global.pTransmitId == "0") {// 企业用户
 			var localMemberId = $.cookie("MemberUserId");
			if (global.memberId != localMemberId)
				document.write("非法地址，禁止访问，请关闭当前窗口！");
		} else {//公众用户
 			var openUserCrossId = $.cookie("OpenUserCrossId");
			if (openUserCrossId == null || openUserCrossId == "null") {
				$.ajax({
					type : "POST",
					url : $.sas.common.getRootPath()+"/bus/popularizeDetail/newUserCrossId.do",
					async : false,
					data : {
						type : "03",
					},
					success : function(data) {
						$.cookie("OpenUserCrossId", data, {
							expires : 365,
							path: '/'
						});
					}
				});
			} 
		}
	};
	
	 function  initToolbar() {// 
    		if (global.pTransmitId == "0") {//企业用户
			$('#welcome_text1').removeClass("ui-hidden-accessible");
		} else {
			var oAuthUrl = $('#oAuthBtn').attr('href') + "?userCrossId=" + $.cookie("OpenUserCrossId");
			var reserveUrl = $('#reserveBtn').attr('href') + "?schemeId=" + global.schemeId + "&popularizeId="+ global.popularizeId + "&pTransmitId=" + global.pTransmitId+"&userCrossId="+$.cookie("OpenUserCrossId");
			$('#oAuthBtn').attr('href', oAuthUrl);
			$('#reserveBtn').attr('href', reserveUrl);
			$('#toolbar_region').removeClass("ui-hidden-accessible");
			$('#welcome_text2').removeClass("ui-hidden-accessible");
		} 
	};
	
	function readPopularize () {//阅读记录
		if (global.pTransmitId != null && global.pTransmitId != "") {
			if (global.pTransmitId == "0" ) {// 企业号内打开
				return;
			}
			if ($.cookie(global.popularizeId) != "Readed") {//重复阅读
				$.ajax({
					type : "POST",
					url : $.sas.common.getSasTraWebSite()+"/rep/tracks/recordReadCount.do",
					async : false,
					data : {
						transmitId : global.pTransmitId,
						userCrossId : $.cookie("OpenUserCrossId")
					},
					success : function(data) {
						$.cookie(global.popularizeId, "Readed", {
							expires : 7,
							path: '/'
						});
					},
					error:function (xhr, textStatus, errorThrown){
						alert(errorThrown);
					}
				});
			}
		}
	};
	function generateUrl () {
		global.transmitId = $.sas.util.uuid();
		if (global.pTransmitId != "0" && global.fTransmitId == "0"){//第一级好友
			global.fTransmitId = global.transmitId;
		}
		var encodeParams = $.base64Encode("memberId="+ global.memberId + "&popularizeId="+ global.popularizeId + "&pTransmitId=" + global.transmitId + "&fTransmitId=" + global.fTransmitId);
		return global.url + "?params="+ encodeParams;
	};
	
	function initSchemePage(){
		var bgWidth = $('#bg_width').val();
 		var bgHeight = $('#bg_height').val(); 
 		var textHeight = $('#head_text').outerHeight();
		$('body').css('background-position','0 '+textHeight+'px');
		var docWidth = $(document).width();
		var docHeight = docWidth/bgWidth*bgHeight+textHeight;
		$('body').css('height',docHeight); 
		$('body').css('width',docWidth);
 		//按钮改为浮动后，不需要以下代码
		//var buttonHeight = $('#toolbar_region').outerHeight();
	  	//$('#toolbar_region').css('top',(docHeight-buttonHeight-20)+"px");
	}
	return{		
		onPageReady : function () {//页面初始化函数
			$.sas.weixin.showOptionMenu();
		 	var encodeParams = $.sas.util.getUrlParam("params");
			var decodeParams = $.base64Decode(encodeParams);
			global.popularizeId = $.sas.util.getParamByRequest(decodeParams, "popularizeId");
			global.memberId = $.sas.util.getParamByRequest(decodeParams, "memberId");
			global.pTransmitId = $.sas.util.getParamByRequest(decodeParams, "pTransmitId");
			global.fTransmitId = $.sas.util.getParamByRequest(decodeParams, "fTransmitId");
			
			var localMemberId = $.cookie("MemberUserId");
			
			if (global.memberId == localMemberId) {
				global.pTransmitId = "0";
			}
			if (global.pTransmitId == "0") {
				global.fTransmitId = "0";
			}
		 	global.schemeId = $('#scheme_id').val();	
		 	global.descContent = $('#scheme_summary').val();
		 	global.shareTitle = $('#scheme_title').val();
		 	global.imgUrl = global.imgUrl + $('#scheme_thumbnailurl').val();
 			checkUserCrossId();
			initToolbar();
			initSchemePage();
			readPopularize();// 阅读
		},
		shareFriend : function () {//分享到给朋友回调函数
 			WeixinJSBridge.invoke('sendAppMessage', {
				"appid" : $.sas.common.getAppid(),
				"img_url" : global.imgUrl,
				"img_width" : "120",
				"img_height" : "120",
				"link" : generateUrl(),
				"desc" : global.descContent,
				"title" : global.shareTitle
			}, function(res) {
				// 返回res.err_msg,取值
				// send_app_msg:cancel 用户取消
				// send_app_msg:fail　发送失败
				// send_app_msg:ok 发送成功 android
				// send_app_msg:confirm 发送成功  ios
		 		if ("send_app_msg:ok" ==res.err_msg || "send_app_msg:confirm" ==res.err_msg ) {
		 			productScheme.forwardPopularize("01");	
				} 
			});
		},
		shareTimeline : function () {//分享到朋友圈回调函数
			humane.info("禁止分享到朋友圈！");
// 				WeixinJSBridge.invoke('shareTimeline', {
//				"appid" : $.sas.common.getAppid(),
//				"img_url" : global.imgUrl,
//				"img_width" : "120",
//				"img_height" : "120",
//				"link" : generateUrl(),
//				"desc" : global.descContent,
//				"title" : global.shareTitle
//			}, function(res) {
//				// 返回res.err_msg,取值
//				// share_timeline:cancel 用户取消
//				// share_timeline:fail　发送失败
//				// share_timeline:ok 发送成功
//		 		if ("share_timeline:ok" ==res.err_msg) {
//		 			productScheme.forwardPopularize("02");
//				}
//			});
		},
		shareWeibo : function () {//分享到微博回调函数
		
		},
 		forwardPopularize : function (traType) {//转发记录
			if (global.pTransmitId != null && global.pTransmitId != "") {
				var iUserCrossId;
				if (global.pTransmitId == "0" ){// 企业用户
					iUserCrossId = $.cookie("MemberUserCrossId");		
				}else{//公众用户
					iUserCrossId = $.cookie("OpenUserCrossId");
				}
				$.ajax({
					type : "POST",
					url : $.sas.common.getSasTraWebSite()+"/rep/tracks/recordTransmit.do",
					async : false,
					data : {
						transmitId : global.transmitId,
						popularizeId : global.popularizeId,
						pTransmitId : global.pTransmitId,
						firstTransmitId : global.fTransmitId,
						traType : traType,
						userCrossId : iUserCrossId
					},
					success : function(data) {
					},
					error:function (xhr, textStatus, errorThrown){
						alert(errorThrown);
					}
				});
			}
		}
	};
}();

$(function(){
	$.sas.weixin.onWeixinPageOnReady(productScheme.onPageReady,productScheme.shareFriend,productScheme.shareTimeline,productScheme.shareWeibo);
	$.sas.weixin.showOptionMenu();
});




