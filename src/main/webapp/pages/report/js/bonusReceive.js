var bonusReceive = function(){
	var defaults = {
	};
	function beforeConfrim(){
		var myDate = new Date();
		var hours = myDate.getHours();
		if(hours<8){
			humane.info('请在8点之后再来领取红包吧！');
			return false;
		}
		//var agree = $('#agree').prop('checked');
		var bonus =  $('#rmb_receive').val();
		var surplus = $('#rmb_surplus').text();
//		if(agree==false){
//			humane.info('您还没同意微车平台红包领取协议');
//			return false
//		}
		bonus = bonus.trim();
		var r = /^\d+(\.\d+)?$/;
		if(!r.test(bonus)){
			humane.info('红包金额格式不不正确');
			return false
		}
		var numbers = bonus.split('.');
		if(numbers.length>1 && numbers[1].length>2){
			humane.info('红包金额应保留到小数点后两位');
			return false
		}
		bonus = parseFloat(bonus);
		surplus = parseFloat(surplus);
		if(surplus>200.00&&bonus>200.00){
			humane.info('红包金额应小于200.00元');
			return false	
		}else if(bonus>surplus){
			humane.info('余额不足');
			return false
		}else if(bonus<1.00){
			humane.info('红包金额应大于1.00元');
			return false
		}else{
			return true;
		}
	}
	function round2(Num1,Num2){
	     if(isNaN(Num1)||isNaN(Num2)){
	           return(0.00);
	     }else{
	    	 return(Num1.toFixed(Num2));
	     }
	}
	return {
		initPage:function(){
			$.ajax({
				type:'POST',
				url:'report/bonusReceive/initPage.do',
				data:{
					userType:$.sas.util.getUrlParam('userType')
				},
				dataType : 'json',
				success : function(data,textStatus, jqXHR) {
					var agreement = data.info;
					if('1'==agreement){
						$.rt.rtCheckbox.method.rtCheck($('#agree'))
					}else{
						$.rt.rtCheckbox.method.rtUnCheck($('#agree'));
					}
				}
			});
			var balance = $.sas.util.getUrlParam('balance');
			balance = parseFloat(balance);
			$('#rmb_surplus').text(round2(balance,2));
			if(balance>200.00){
				$('#rmb_receive').val(200.00);
			}else{
				$('#rmb_receive').val(round2(balance,2));
			}
		},
		confirm:function(){
			$('#confirmBtn').attr('disabled','true');
			$.rt.rtLoading.show('领取中..');
			if(beforeConfrim()){
				$.ajax({
					type:'POST',
					url:'report/bonusReceive/receiveBonus.do',
					data:{
						openId:$.sas.util.getUrlParam('openId'),
						userType:$.sas.util.getUrlParam('userType'),
						amount:$('#rmb_receive').val()
					},
					dataType : 'json',
					success : function(data,textStatus, jqXHR) {
						$.rt.rtLoading.remove();
						var code = data.code;
						if(code=="success"){
							window.location.replace('pages/report/receiveSuccess.html?v=v1.0.9_20150005&amount='+$('#rmb_receive').val()
									+'&userType='+$.sas.util.getUrlParam('userType'));
						}else if(code=="DAY_ OVER_LIMITED"){
							humane.info('今天的红包发完了，改天再来试试吧!');
						}else if(code=="SECOND_OVER_LIMITED"){
							humane.info('红包领取失败，过一分钟再来试试吧！');
						}else if(code=="TIME _LIMITED"){
							humane.info('请在8点之后再来领取红包吧！');
						}else if(code=="SYSTEMERROR"){
							humane.info('系统繁忙，请再试！');
						}else if(code=="error_NOTENOUGH"){
							humane.info('账户余额不足');
						}else if(code == "error_not_allow_pay"){
							humane.info('平台已禁止领取');
						}else{
							humane.info('红包领取失败');
						}
					},complete:function(){
						$('#confirmBtn').removeAttr("disabled");
					}
				});
			}else{
				$('#confirmBtn').removeAttr("disabled");
			}
		},
		cancel:function(){
			window.history.back();
		},
		jumptoAgreement:function(){
			location.href = 'pages/report/bonusAgreement.html?v=v1.0.9_20150005';
		},
		changeCheckBox:function(){
			$.ajax({
				type:'POST',
				url:'report/bonusReceive/changeAgreement.do',
				data:{
					userType:$.sas.util.getUrlParam('userType'),
					hasAgreement:$('#agree').prop('checked')?"1":"0"
				},
				dataType : 'json',
				success : function(data,textStatus, jqXHR) {
				}
			});
		}
	}
}();
$(function() {
	weixin.config({
        debug: false,
        jsApiList: ['onMenuShareTimeline','hideOptionMenu','hideMenuItems', 'showOptionMenu', 'closeWindow','onMenuShareAppMessage','getNetworkType']
    });
	weixin.ready(function () {
		weixin.hideOptionMenu();
		$.rt.rtCheckbox();
		$.rt.rtCheckbox.method.rtBindMethod($('#agree'), bonusReceive.changeCheckBox); //绑定事件
		bonusReceive.initPage();
	});
//	$.sas.weixin.onWeixinPageReady(function(){ 
//		$.sas.weixin.hideOptionMenu();
//		$.rt.rtCheckbox();
//		$.rt.rtCheckbox.method.rtBindMethod($('#agree'), bonusReceive.changeCheckBox); //绑定事件
//		bonusReceive.initPage();
//	});
});
