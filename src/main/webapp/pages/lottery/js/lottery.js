var lottery = function() {
	var defaults = {
			appId: '',
			deptId: '',
			bonusId: '',
			lotSum: 0,
			rotSpeed: 5000   //转速 ：  5s/圈
		};

	var interval = '';
	
	var rotates = [0, 36, 72, 108, 144, 180, 216, 252, 288, 324];
	var no_prize = [0, 2, 4, 6, 8];
	
	function initPage() {
 		initParams();
		initData(true);
	}
	
	function initData(isShow) {
		if(isShow){
			$.rt.rtLoading.show('加载中..');
		}
		
		$.ajax({
			url: 'sys/lottery/initLottery.do',
			data: {
				deptId: defaults.deptId
			},
			success: function (data, xhrTxt) {
				initRecords(data);
				initPosition();
				$('#page').show();
			},
			complete: function() {
				if(isShow){
					$.rt.rtLoading.hide();
				}
			},
			dataType: 'json'
		});
	}
	
	function initRecords(data) {
		defaults.lotSum = data.lotSum;
		$('#lottery_num_span').html(data.lotSum);
		$('#get_prize_table').html(template('get_prize_template', data));
		$('#history_prize_table').html(template('history_prize_template', data));
	}
	
	function checkLotSum() {
		defaults.lotSum--;
		$('#lottery_num_span').html(defaults.lotSum);
		$.ajax({
			type: 'POST',
			url: 'sys/lottery/checkLotSum.do',
			data: {
				deptId: defaults.deptId
			},
			success: function (data) {
				defaults.lotSum = data.lotSum;
				$('#lottery_num_span').html(data.lotSum);
			},
			dataType: 'json'
		});
	}
	
	function initParams() {
		var params = $.base64Decode($.sas.util.getUrlParam('params'));
		defaults.appId = $.sas.util.getParamByRequest(params, 'appId');
		defaults.deptId = $.sas.util.getParamByRequest(params, 'deptId');
	}
	
	function initPosition(){
		var docWidth = $(document).width();
		var k = docWidth / 640; //放大缩小系数
		$('#lottery-bg').css('height', k * 955 + 'px');
		$('#invite_friend').css('width', k * 216 + 'px'); 
		$('#invite_friend').css('height', k * 90 + 'px'); 
		$('#invite_friend').css('top', k * 240 + 'px'); 
		$('#invite_friend').css('left', k * 330 + 'px'); 
		$('#lottery_div').css('top', k * 348 + 'px'); 
		
		var turntableWidth = k * 572;
		var turntableMargin = -(turntableWidth / 2);
		$('#turntable').css('width', turntableWidth + 'px'); 
		$('#turntable').css('height', turntableWidth + 'px'); 
		$('#turntable').css('margin-top', turntableMargin + 'px'); 
		$('#turntable').css('margin-left', turntableMargin + 'px'); 
		$('#lottery_div').css('height', turntableWidth+'px');
		$('#lottery_num').css('top', (turntableWidth-20)+'px'); 
		
		var runWidth = k * 150;
		var runHeight = k * 200;
		var runMarginH = -(k * 125);
		var runMarginW = -(runWidth / 2);
		$('#pointer').css('width', runWidth + 'px'); 
		$('#pointer').css('height', runHeight + 'px'); 
		$('#pointer').css('margin-top', runMarginH + 'px'); 
		$('#pointer').css('margin-left', runMarginW + 'px');
		
		var btnRunWidth = k * 114;
		var btnRunMargin = -(btnRunWidth / 2);
		$('#btn_start').css('width', btnRunWidth + 'px'); 
		$('#btn_start').css('height', btnRunWidth + 'px'); 
		$('#btn_start').css('margin-top', btnRunMargin + 'px'); 
		$('#btn_start').css('margin-left', btnRunMargin + 'px');
	}

	function lottery(){ 
		if (defaults.lotSum <= 0) {
			$('#dialog_cotent').html('抽奖机会已用完，<br>成功邀请朋友可得抽奖机会！' );
			$('#dialog_btn').val('邀请好友').off('click').on('click', function(){location.href = 'sys/invitation/init.do';});
			$('#dialog').rtDialog('show');
			return;
		}

		$('#btn_start').attr('disabled',true).css('cursor','default'); 
		
		$('#turntable').rotate ({ 
			duration: defaults.rotSpeed * 20, //转动时间 
			angle: 0, //默认角度
			animateTo: 360 * 20, //转动角度   20圈
			easing: $.easing.easeOutSine, 
			callback: function(){} 
		});

		drawLottery();
	};
	
	function drawLottery() {
		$.ajax({
	        type: 'POST', 
	        url: 'sys/lottery/drawLottery.do',
	        data: {
	        	appId: defaults.appId,
	        	deptId: defaults.deptId
	        },
	        success: function(data, xhrTxt){
 	        	defaults.bonusId = data.bonusId;
	        	var rType = getValue(data.bonus);
 	        	if (rType == '0') {
	        		rType = no_prize[Math.floor(Math.random() * no_prize.length)];
	        	}
 	        	var rotate = GetRandomNum(rotates[rType] - 15, rotates[rType] + 15);
	        	var ringOld = Math.floor($('#turntable').getRotateAngle()[0] / 360) + 4;
 	        	var ring = 1 + (rotate / 360);  //圈数 
 	        	interval = setInterval(function() {
					var angle = $('#turntable').getRotateAngle()[0];
 					if ( angle % 360 < 20 && angle / 360 > ringOld ) {  
 						$('#turntable').rotate({   
							duration: defaults.rotSpeed * ring, //转动时间 
							angle: 0, //默认角度
							animateTo: 360 * ring, //转动角度 
							easing: $.easing.easeOutSine,  
							callback: function(){      
								if($.inArray(rType, no_prize) != -1){
									$('#dialog_cotent').html('再接再厉！');
									checkLotSum();     
								}else{
									$('#dialog_cotent').html("恭喜你获得<br><span class='lottery-num-span font-size-xl'>" + data.bonus + "元红包！</span>" );
									initData(false);
									sendBonus();
								}
								$('#dialog').rtDialog('show');
								$('#btn_start').attr('disabled',false).css('cursor','pointer');
							} 
						});
 						clearInterval(interval);
					} 
				}, 10);
	        },
	        dataType: 'json'
	    }); 
	}
	
	function getValue(key){
		if(key=='-1'){
			return '0';
		}else if(key=='88'){
			return '1';
		}else if(key=='2'){
			return '3';
		}else if(key=='8'){
			return '5';
		}else if(key=='9.6'){
			return '7';
		}else if(key=='58'){
			return '9';
		} else {
			return '0';
		}
	}; 
 	
	function sendBonus() {
		$.ajax({
			type: 'POST',
			url: 'sys/lottery/sendLottery.do',
			data: {
				bonusId: defaults.bonusId
			},
			success: function (data) {
				if(data.code == '0') {
					humane.info('红包已推送！');
				} else {
					humane.info('红包已发放,进入【赚外快】--【我的红包】领取！');
				}
			},
			error: function(data) {
				humane.info('红包已发放,进入【赚外快】--【我的红包】领取！');
			},
			dataType: 'json'
		});
	}
	
	function GetRandomNum(Min,Max){   
		Min=Min*1;
		Max=Max*1;
		var Range = Max - Min;   
		var Rand = Math.random();   
		return (Min + Math.round(Rand * Range));   
	};
	
	return {
		initPage: initPage,
		lottery: function(){
			lottery();
		},
		
		invite: function(){
			location.href = 'sys/invitation/init.do';
		}
	};
}();

$(function(){ 
	 weixin.config({
        debug: false,
        jsApiList: ['hideOptionMenu', 'showOptionMenu']
    });
	weixin.ready(function () {
		weixin.hideOptionMenu();
		lottery.initPage();
    }); 
}); 
