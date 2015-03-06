var lottery = function() {
	var defaults = {
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
			url: 'qy/lottery/initLottery.do',
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
		if (data.state == 'user-wrong') {
			humane.info('用户信息错误');
			return;
		}
		defaults.lotSum = data.lotSum;
		$('#lottery_num_span').html(data.lotSum);
		$('#lottery_read_span').html(data.readCount);
		$('#get_prize_table').html(template('get_prize_template', data));
		$('#history_prize_table').html(template('history_prize_template', data));
	}
	
	function checkLotSum(count) {
		count == undefined ? defaults.lotSum--: defaults.lotSum += count;
		$('#lottery_num_span').html(defaults.lotSum);
		$.ajax({
			type: 'POST',
			url: 'qy/lottery/checkLotSum.do',
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
		defaults.deptId = $.sas.util.getParamByRequest(params, 'deptId');
	}
	
	function initPosition(){
		var docWidth = $(document).width();
		var k = docWidth / 640; //放大缩小系数
		$('#lottery-bg').css('height', k * 955 + 'px');
		
		var turntableWidth = k * 572;
		var turntableMargin = -(turntableWidth / 2);
		$('#turntable').attr('style', 'width:' + turntableWidth + 'px; height:' + turntableWidth + 'px; margin-top:' + turntableMargin + 'px; margin-left:' + turntableMargin + 'px'); 
		$('#lottery_div').attr('style', 'height:' + turntableWidth + 'px; top:' + k * 348 + 'px;'); 
		$('#lottery_num').css('top', (turntableWidth-20)+'px'); 
		
		var runWidth = k * 150;
		var runHeight = k * 200;
		var runMarginH = -(k * 125);
		var runMarginW = -(runWidth / 2);
		$('#pointer').attr('style', 'width:' + runWidth + 'px; height:' + runHeight + 'px; margin-top:' + runMarginH + 'px; margin-left:' + runMarginW + 'px'); 
		
		var btnRunWidth = k * 114;
		var btnRunMargin = -(btnRunWidth / 2);
		$('#btn_start').attr('style', 'width:' + btnRunWidth + 'px; height:' + btnRunWidth + 'px; margin-top:' + btnRunMargin + 'px; margin-left:' + btnRunMargin + 'px'); 
		
		$('#car').attr('style', 'width:' + k * 555 + 'px; height:' + k * 413 + 'px; top:' + k * 24 + 'px;'); 

		if($.cookie('MemberUserId')){   //内部  
			$('#slogan_internal').attr('style', 'width:' + k * 261 + 'px; height:' + k * 153 + 'px; top:' + k * 283 + 'px;'); 
			$('#slogan_internal').show();
			$('#remark_internal').show();
		}else {			//外部 
			$('#slogan_external').attr('style', 'width:' + k * 261 + 'px; height:' + k * 79 + 'px; top:' + k * 283 + 'px;'); 
			$('#slogan_external').show();
			$('#remark_external').show();
		}
	}

	function lottery(){ 
		humane.info('亲，来年再见哦~~更多红包等你拿~~恭祝新年快乐！');
		return ;
		/*if (defaults.lotSum <= 0) {
//			if ($.cookie('MemberUserId')) {
//				$('#dialog_cotent').html('您已没有抽奖机会，邀请好友或发动好友阅读，得更多抽奖机会。' );
//				$('#dialog_btn').val('邀请好友').off('click').on('click', function(){location.href = 'sys/invitation/init.do';});
//			} else {
//				$('#dialog_cotent').html('您已没有抽奖机会，发动好友阅读，得更多抽奖机会。' );
//				$('#dialog_btn').val('确定').off('click').on('click', function(){$('#dialog').rtDialog('hide');});
//			}
//			$('#dialog').rtDialog('show');
			humane.info('恭祝新年快乐！抽奖次数不足哦。');
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

		drawLottery();*/
	};
	
	function drawLottery() {
		$.ajax({
	        type: 'POST', 
	        url: 'qy/lottery/drawLottery.do',
	        data: {
	        	deptId: defaults.deptId
	        },
	        success: function(data, xhrTxt){
	        	if (data.state == 'user-wrong') {
	        		$('#turntable').stopRotate();
	    			humane.info('用户信息错误');
	    			return;
	        	}
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
									$("#lottery_guide").rtGuide({
										guideImg: "resources/sas/img/lottery/0" + GetRandomNum(1,5) + ".png"
									});
									checkLotSum();     
								}else{
									$("#lottery_guide").rtGuide({
										guideImg: "resources/sas/img/lottery/" + data.bonus + ".png"
									});
									initData(false);
									sendBonus();
								}
								$.fn.rtGuide.methods.show($("#lottery_guide"));
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
		}else if(key=='18'){
			return '1';
		}else if(key=='1'){
			return '3';
		}else if(key=='2'){
			return '5';
		}else if(key=='5'){
			return '7';
		}else if(key=='8'){
			return '9';
		} else {
			return '0';
		}
	}; 
 	
	function sendBonus() {
		$.ajax({
			type: 'POST',
			url: 'qy/lottery/sendLottery.do',
			data: {
				bonusId: defaults.bonusId
			},
			success: function (data) {
				if(data.code == '0') {
					humane.info('恭祝新年快乐！红包已推送！');
				} else {
					humane.info('恭祝新年快乐！红包已发放,进入【我的红包】领取！');
				}
			},
			error: function(data) {
				humane.info('恭祝新年快乐！红包已发放,进入【我的红包】领取！');
			},
			dataType: 'json'
		});
	}
	
	function exchange() {
		humane.info('亲，来年再见哦~~更多红包等你拿~~恭祝新年快乐！');
		return;
		/*if ($('#lottery_read_span').html() < 2) {
			humane.info('恭祝新年快乐！次数没攒够呢，再接再厉转发哦。');
			return;
		}
		$.rt.rtLoading.show('兑换中');
		$.ajax({
			type: 'POST',
			url: 'qy/lottery/claimLottery.do',
			data: {
				deptId: defaults.deptId
			},
			success: function (data) {
				if (data.state == 'user-wrong') {
	    			humane.info('用户信息错误');
	        	} else if (data.state == 'empty-readCount') {
	    			humane.info('恭祝新年快乐！次数没攒够呢，再接再厉转发哦。');
	        	} else if (data.state == 'not-enough-convert') {
	    			humane.info('恭祝新年快乐！抽奖机会已全部兑换完啦。');
	        	} else if (data.state == 'success') {
	        		humane.info('恭祝新年快乐！兑换成功啦。');
	    			$('#lottery_read_span').html(data.readCount);
	    			checkLotSum(data.successCount);
	        	}
				
			},
			error: function(data) {
				humane.info('兑换失败，请稍等兑换~');
			},
			complete: function() {
				$.rt.rtLoading.hide();
			},
			dataType: 'json'
		});*/
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
		},
		
		exchange: exchange
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
