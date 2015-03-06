var reserve = function(){
	var defaults = {
		name: '',
		iweixinid: ''
	};
	
	function checkInviteForm(){
		defaults.name = $("#iuserName").val().trim();
		
		if (defaults.name.length > 40) {
			humane.info('姓名长度过长');
			return false;
		} else if (defaults.name == '') {
			humane.info('请输入姓名');
			return false;
		} else if (/^\d+/.test(defaults.name) || !/^[\u4e00-\u9fa5a-zA-Z\d\s]+$/.test(defaults.name)) {
			humane.info('请输入正确的姓名,汉字或英文开头,不包含特殊符号');
			return false;
		}

		defaults.iweixinid = $("#iweixinid").val().trim();
		if(defaults.iweixinid == ''){
			humane.info('请输入关联微信的手机号/微信号');
			return false;
		} else if (/^\d+/.test(defaults.iweixinid) && !/^(13|15|18|17)(\d{9}|(\d{1}[-\s]?\d{4}[-\s]?\d{4}))$/.test(defaults.iweixinid)){
			humane.info('请输入正确关联微信的手机号');
			return false;
		} else if (/^[^\d]+/.test(defaults.iweixinid) && !/^[a-zA-Z]{1}[a-zA-Z\d_-]{5,19}$/.test(defaults.iweixinid)) {
			humane.info('微信号仅支持6-20个字母、数字、下划线或减号，以字母开头');
			return false;
		}

		if (/^\d+/.test(defaults.iweixinid)) {
			defaults.iweixinid = defaults.iweixinid.replace(/[-\s]/g, '');
		}
		return true;
	}
	
	return {
		onInvite: function(){
			if(checkInviteForm()){
				$.rt.rtLoading.show('加载中..');
 				 setTimeout(function() {
 					 $.ajax({
	          			async: false,  
	          			type: 'POST',
	          			url: 'sys/invitation/saveInviMember.do',
	          			data:{
	          				name: defaults.name,
	          				weixinid: defaults.iweixinid,
	          				gender: '0',
	          				joinStore: $('#join_store').prop('checked'),
	          				shareProduct: $('#share_product').prop('checked')
	          			},
	          			success : function(data, textStatus, jqXHR) {
	          				if(data.code == '-1'){
	          					humane.info('该好友已加入微车,不能再次邀请加入');
	          				}else{
	          					var url = encodeURI(encodeURI('pages/sys/invitation.html?fName=' + $('#iuserName').val() + '&inviId=' + data.memberId));
	          					location.replace(url + '&v=v1.0.9_20150005');
	          				}
	            		},
	          			dataType : 'json'
	          		}); 
          			$.rt.rtLoading.hide();
 				 }, 200);
 			}
		},
		initPageData: function() {
			$.rt.rtLoading.show('加载中..');
			$.ajax({
				url: 'sys/invitation/initData.do',
				success: function(result) {
					if (result.code == 'notUser') { //没有该用户
						location.replace('pages/sys/aboutUs.html?v=v1.0.9_20150005');
						return;
					} else if (result.code == 'notSto') {//用户没有在门店里
						$('#join_store').prop('checked', false).parent().hide();
					}
					$('.page-full-screen').show();
					var imgUrl = $.sas.util.getUrlParam('imgUrl');
					if(imgUrl != null && imgUrl != ""){
						$('body').append("<div id=\"rt_guide\" data-role=\"rtGuide\" data-options=\"{guideImg:'resources/sas/img/guide/" + imgUrl + "'}\"></div>");
						$("[data-role='rtGuide']").rtGuide({});
						$.fn.rtGuide.methods.show($('#rt_guide'));
					}
				},
				complete: function() {
					$.rt.rtLoading.hide();
				},
				dataType: 'json'
			});
		}
	};
}();

$(function() {
	weixin.config({
        debug: false,
        jsApiList: ['onMenuShareTimeline','hideOptionMenu','hideMenuItems', 'showOptionMenu', 'closeWindow','onMenuShareAppMessage','getNetworkType']
    });
	weixin.ready(function () {
		weixin.hideOptionMenu();
		$.rt.rtCheckbox();
		reserve.initPageData();
	});
//	$.sas.weixin.onWeixinPageReady(function(){
//		$.rt.rtCheckbox();
//		$.sas.weixin.hideOptionMenu();
//		reserve.initPageData();
//	});
});