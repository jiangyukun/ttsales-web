var myBonus = (function() {
	var def = {
		selMonth : '',
		userType : $.sas.util.getUrlParam('userType')
	}
	
	function initBalanceAndMonth(data) {
		$('.red-package-header-container').html(template('red-package-balance', data));
		$('#select_month').html(template('red_package_select', data));
		
		if(parseFloat(data.money.balance) <= 0) {
			$('.cash-out-btn').attr('disabled', true);
		}
	}
	
	function initPackageDetail(date) {
		if (def.selMonth == date) {
			return;
		} else {
			def.selMonth = date;
			$.post('report/myBonus/queryBonusDetail.do', {'userType': def.userType, 'date': date}, function(result) {
				if (result == null) {
					$('.red-package-list-body').html(template('no_red_package', null));
				} else {
					var b = result.bonus;
					for(var i=0;i<b.length;i++){
						b[i].money = round2(b[i].money,2);
					};
					$('.red-package-list-body').html(template('red_package_item', result));
				}
			}, 'json');
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
		initPage : function() {
			$.get('report/myBonus/initData.do', {'userType': def.userType}, function(result) {
				initBalanceAndMonth(result);			
				var today = new Date();
				initPackageDetail(today.getFullYear() + '-' + (today.getMonth() + 1));
			}, 'json');
		},
		getMoney : function() {
			window.location.href='report/bonusReceive/init.do?userType='+def.userType+'&balance='+$('#balance').text();
		},
		selectMonth : function() {
			initPackageDetail($('#select_month').val());
		}
	};
})()

$(function() {
	weixin.config({
        debug: false,
        jsApiList: ['onMenuShareTimeline','hideOptionMenu','hideMenuItems', 'showOptionMenu', 'closeWindow','onMenuShareAppMessage','getNetworkType']
    });
	weixin.ready(function () {
		weixin.hideOptionMenu();
		myBonus.initPage();
	});
//	$.sas.weixin.onWeixinPageReady(function(){ 
//		$.sas.weixin.hideOptionMenu();
//		myBonus.initPage();
//	});
});