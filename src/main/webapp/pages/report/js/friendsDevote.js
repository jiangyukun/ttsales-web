/**
 * 好友贡献排行榜
 * date：2014-12-22
 * lastChange：2014-12-24
 */
var friendDevote = (function(){
	var defaults = {
		popularizeId : $.sas.util.getUrlParam('popularizeId'),
		isInitFriends: false,
		isInitAll: false,
		curIndex: sessionStorage.getItem('selected_tap') || 0
	}
	
	function initRtTabs() {
		$("[data-role='rtTabs']").rtTabs({
			onSelect: function(e, title, index){
				defaults.curIndex = index;
				setSelected(index);
				if (defaults.curIndex == 0) {
					if (!defaults.isInitFriends) {
						queryRank('friend');
					}
				} else {
					if (!defaults.isInitAll) {
						defaults.isInitAll = true;
						queryRank('all');
					}
				}
				var ot = (index + 1) % 2;
				$('#tab_' + index).show();
				$('#tab_' + ot).hide();
			}, 
			onUnSelect: function(e, title, index){}
			, selected: defaults.curIndex
		}); 
	}
	
	function setRank(datas) {
		var tabContaint = $('#tab_' + defaults.curIndex).html('');
		for (var pro in datas) {
			var singleData = new Object();
			if (datas[pro].length > 0) {
				singleData.rd = datas[pro];
			} else {
				singleData.rd = 'null';
			}
			singleData.type = pro;
			singleData.rank = defaults.curIndex;
			if (defaults.curIndex == 1) {
				singleData.popularizeId = defaults.popularizeId;
			}
			tabContaint.append(template('performance_rank', singleData));
		}
	}
	
	function setSelected(index) {
		sessionStorage.setItem('selected_tap', index);
	}
	
	function queryRank(type) {
		$.ajax({
			url: 'report/friendsDevote/getPerformanceAnalyze.do',
			data: {
				popularizeId: defaults.popularizeId,
				rankRange: type
			},
			beforeSend: function(xhr) {
				$.rt.rtLoading.show();
			},
			success: function(data) {
				type == 'friend' ? defaults.isInitFriends = true: defaults.isInitAll = true;
				setRank(data);
			},
			error: function() {
				type == 'friend' ? defaults.isInitFriends = false: defaults.isInitAll = false;
				$('#tab_' + defaults.curIndex).html('').append("<div class='no-preformance'>加载数据失败..." + $.sas.util.formatDate(new Date(), 'hh:mm:ss') + "</div>");
			},
			complete: function() {
				$.rt.rtLoading.hide();
			},
			dataType: 'json'
		});
	}
	
	return {
		initData: function() {
			if (!defaults.curIndex) {
				setSelected(0);
			}
			initRtTabs();
			$('.page-full-screen').show();
		},
		queryRank: queryRank
	}
})();

$(function() {
	weixin.config({
        debug: false,
        jsApiList: ['onMenuShareTimeline','hideOptionMenu','hideMenuItems', 'showOptionMenu', 'closeWindow','onMenuShareAppMessage','getNetworkType']
    });
	weixin.ready(function () {
		weixin.hideOptionMenu();
		friendDevote.initData();
	});
//	$.sas.weixin.onWeixinPageReady(function() {
//		$.sas.weixin.hideOptionMenu();
//		friendDevote.initData();
//	});
});