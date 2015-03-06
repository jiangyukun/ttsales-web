var receiveSuccess = function(){
	var def = {
			amount : $.sas.util.getUrlParam('amount'),
			userType : $.sas.util.getUrlParam('userType'),
			balance :$.sas.util.getUrlParam('balance')
	};
	return {
		initPage:function(){
			var value = def.amount*1.0;
			$('#rmb_amount').text(value.toFixed(2)+'元');
		},
		cancel:function(){
			if(def.userType=='02'){
				window.location.replace('report/myBonus/init.do?another=replace');
			}else{
				window.location.replace('report/myBonusMp/init.do?another=replace');
			}
		},
	}
}();
$(function() {
	weixin.config({
        debug: false,
        jsApiList: ['onMenuShareTimeline','hideOptionMenu','hideMenuItems', 'showOptionMenu', 'closeWindow','onMenuShareAppMessage','getNetworkType']
    });
	weixin.ready(function () {
		weixin.hideOptionMenu();
		receiveSuccess.initPage();
	});
//	$.sas.weixin.hideOptionMenu();
//	receiveSuccess.initPage();
});
