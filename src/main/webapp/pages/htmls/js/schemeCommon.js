var schemeCommon = function() {
		
	var global = {
		url: $.sas.util.getUrl(),
		mCookieName:'MemberUserId',
		mCrossCookieName:'MemberUserCrossId',
		oCookieName:'OpenUserCrossId',
		noWifiInfo : '无法分享，请检查你的手机网络是否正常！',
		popularizeId : "",
		//memberId : "",
		fTransmitId : "",//首层
		gTransmitId : "",//上上层（爷爷）
		pTransmitId : "",//上一层（父亲）
		transmitId : "",//本层（自己）
		currAuthor : "",//当前转发者
		schemeId : "",
		shareTitle : "",
		descContent : "",
		imgUrl : $.sas.common.getRootPath() + "/resources/"
	};
	
	function getMCookie(){
		return $.cookie(global.mCookieName);
	};
	
	function setMCookie(data,expires){
		$.sas.util.setCookie(global.mCookieName,data,expires);
	};
	
	function getMCrossCookie(){
		return $.cookie(global.mCrossCookieName);
	};
	
	function setMCrossCookie(data,expires){
		$.sas.util.setCookie(global.mCrossCookieName,data,expires);
	};
	
	function getOCookie(){
		return $.cookie(global.oCookieName);
	};
	
	function setOCookie(data,expires){
		$.sas.util.setCookie(global.oCookieName,data,expires);
	};
	
	function getReadedCookie(){
		return $.cookie(global.schemeId);
	};
	
	function setReadedCookie(data,expires){
		$.sas.util.setCookie(global.schemeId,data,expires);
	};
	
	function toNoWifi(){
		//window.location.replace($.sas.common.getRootPath() + "/pages/nowifi.html");
		//window.location.reload();
	};
	
	function getGlobal() {
		return global;
	}
	
	function setGlobal() {
		var decodeParams = $.base64Decode($.sas.util.getUrlParam("params"));
		global.popularizeId = $.sas.util.getParamByRequest(decodeParams, "popularizeId");
		//global.memberId     = $.sas.util.getParamByRequest(decodeParams, "memberId");
		global.fTransmitId  = $.sas.util.getParamByRequest(decodeParams, "fTransmitId");
		global.gTransmitId  = $.sas.util.getParamByRequest(decodeParams, "gTransmitId");
		global.pTransmitId  = $.sas.util.getParamByRequest(decodeParams, "pTransmitId");
		global.currAuthor   = $.sas.util.getParamByRequest(decodeParams, "currAuthor");
		
		if($.sas.util.isNull(global.currAuthor)){
			global.gTransmitId = "0";
			global.currAuthor  = getMCookie();
		}else{
			if(global.currAuthor == getOCookie() || global.currAuthor == getMCookie()){
				global.pTransmitId  = global.gTransmitId;
			}else{
				global.currAuthor   = global.pTransmitId == "0" ? getMCookie() : getOCookie();
			}
		}
	 	global.schemeId     = $('#scheme_id').val();	
	 	global.descContent  = $('#scheme_summary').val();
	 	global.shareTitle   = $('#scheme_title').val();
	 	global.imgUrl       = global.imgUrl + $('#scheme_thumbnailurl').val();
	};
	
	function  createUserCrossId() {//生成用户标识
		if (global.pTransmitId == "0") {// 企业用户
 			/*var localMemberId = $.cookie("MemberUserId");
			if (global.memberId != localMemberId)
				document.write("非法地址，禁止访问，请关闭当前窗口！");*/
		} else {//公众用户
			if ($.sas.util.isNull(getOCookie())) {
				$.ajax({
					type : "POST",
					url : $.sas.common.getRootPath()+"/bus/popularizeDetail/newUserCrossId.do",
					async : false,
					data : {
						type : "03",
					},
					success : function(data) {
						setOCookie(data,365);
					},
					error:function (xhr, textStatus, errorThrown){
						//set cookie failed, oCookie is null
						alert(global.noWifiInfo);
						toNoWifi();
					}
				});
			}
		}
	};
	
	
	
	function recordSchemeReadCount () {
		var ua = window.navigator.userAgent.toLowerCase();
 		if(ua.match(/MicroMessenger/i) != 'micromessenger'){
 			return; 
		}
		//父转发为NULL，是异常数据，不记录阅读
		if($.sas.util.isNull(global.pTransmitId)){
			return;
		}
		// 企业号内打开,不记录阅读
		if (global.pTransmitId == "0" ) {
			return;
		}
		//一个文案只记录一次有效阅读
		var isValid = getReadedCookie() == "Readed" ? 0 : 1;
		$.ajax({
			type : "POST",
			url : $.sas.common.getSasTraWebSite()+"/rep/tracks/recordReadCount.do",
			async : false,
			data : {
				popularizeId : global.popularizeId,
				transmitId : global.pTransmitId,
				userCrossId : getOCookie(),
				isValid : isValid
			},
			success : function(data) {
				setReadedCookie("Readed",7);
			},
			error:function (xhr, textStatus, errorThrown){
				//set cookie failed, ReadedCookie is null
				alert(global.noWifiInfo);
				toNoWifi();
			}
		});
	};
	
	function genTransmitUrl () {
		global.transmitId = $.sas.util.uuid();
		if (global.pTransmitId != "0" && global.fTransmitId == "0"){//第一级好友
			global.fTransmitId = global.transmitId;
		}
		var encodeParams = $.base64Encode(
				//"memberId="+ global.memberId + 
				"&popularizeId="+ global.popularizeId + 
				"&gTransmitId=" + global.pTransmitId +
				"&pTransmitId=" + global.transmitId + 
				"&fTransmitId=" + global.fTransmitId +
				"&currAuthor=" + global.currAuthor
				);
		return global.url + "?params="+ encodeParams;
	};
	function initOptions(isShareFriend,isShareTimeline,isShareWeibo){
		var url = genTransmitUrl();
		//发送到朋友对象
		var shareFriendOpts = {
			appid : $.sas.common.getAppid(),
			imgUrl : global.imgUrl,
			imgWidth : "120",
			imgHeight : "120",
			desc : global.descContent,
			title : global.shareTitle,
			link : url,
			is_share:isShareFriend,
			success: function (res) {
				forwardPopularize("01");
				showSubDlg();
				var url1 =  genTransmitUrl();
				shareFriendOpts.link = url1;
				shareTimelineOpts.link =url1;
			},
			fail: function (res) {
			},
			trigger: function (result) {
	        },
	        cancel: function (res) {
	        }
		};
		
		//分享到朋友圈对象
		var shareTimelineOpts = {
			appid : $.sas.common.getAppid(),
			imgUrl : global.imgUrl,
			imgWidth : "120",
			imgHeight : "120",
			desc :  global.descContent,
			title : global.shareTitle,
			link : url,
			is_share : isShareTimeline,
			success: function (res) {
				forwardPopularize("02");
				showSubDlg();
				var url2 =  genTransmitUrl();
				shareFriendOpts.link = url2;
				shareTimelineOpts.link =url2;
			},
			fail: function (res) {
			},
			trigger: function (res) {
	        },
	        cancel: function (res) {
	        }
		};
		//分享到微博对象
		var shareWeiboAppOpts = {
			is_share : isShareWeibo,
			};
		weixin.onShareTimeline(shareTimelineOpts);
		weixin.onShareAppMessage(shareFriendOpts);
		weixin.onShareWeibo(shareWeiboAppOpts);
	}
	
	function showSubDlg(){ 
		if(global.pTransmitId == "0"){
			return ;  
		}  
 		var openId = $.cookie("openId"); 
		if(openId==undefined||openId==''){
 			$('#dialog_btn').val('关注我们').off('click').on('click', function(){location.href = 'bus/redPaper/init.do?userCrossId=' + schemeCommon.getOCookie();});
			$('#dialog').rtDialog('show');   
		}   
	}

//	function getNetWork(fn){
//		var getNetWorkOpts = {
//			success: function (res) {
//				fn();
//			},
//			fail: function (res) {
//				alert("无网络，请检查你的手机网络是否正常！");
//			},
//			trigger: function (res) {
//	        },
//	        cancel: function (res) {
//	        }
//		};
//		weixin.getNetworkType(getNetWorkOpts);
//	}
	function forwardPopularize(traType) {//转发记录
		//父转发为NULL，是异常数据，不记录转发
		if($.sas.util.isNull(global.pTransmitId)){
			return;
		}
		var iUserCrossId = global.pTransmitId == "0" ? getMCrossCookie() : getOCookie();
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
				alert(global.noWifiInfo + "（您本次分享未成功，请重新分享）");
			}
		});
	};
	
	
	return{
		onPageReady : function(childSchemeInit,isShareFriend,isShareTimeline,isShareWeibo){
			weixin.ready(function () {
				setGlobal();
				createUserCrossId();
				recordSchemeReadCount();
				weixin.showOptionMenu();
				initOptions(true,true,false);
				childSchemeInit();
		    });
		},
		getOCookie : function (){
			return getOCookie();
		},
		getMCookie : function (){
			return getMCookie();
		},
		getGlobal : function(){
			return getGlobal();
		}
	};
}();

$(function(){
	weixin.config({
        debug: false,
        jsApiList: ['onMenuShareTimeline','hideOptionMenu','hideMenuItems', 'showOptionMenu', 'onMenuShareAppMessage','getNetworkType']
    });
}); 

