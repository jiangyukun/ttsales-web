/**
 * 业绩跟踪
 * date：2014-12-22
 * lastChange：2014-12-24
 */
var perTracing = (function(){
	function setPopularizeId(popularizeId) {
		sessionStorage.setItem('popularizeId', popularizeId);
	}
	
	function displayNoData(type) {
		$('#perfromance_content').html(template(type, null));
		$('#my_btn').html(template('btns', {state: 0}));
	}
	
	function handleSchemeData(data) {
		var spid = sessionStorage.getItem('popularizeId');
		if (spid) {
			for (var i = 0, len = data.schemeArray.length; i < len; i++) {
				if (data.schemeArray[i].popularizeId == spid) {
					break;
				}
			}
			i == len ? data.select_pid = data.schemeArray[0].popularizeId: data.select_pid = spid;
		} else {
			data.select_pid = data.schemeArray[0].popularizeId;	
		}
		return data;
	}
	
	function queryPerformance() {
		setPopularizeId($('#select_product').val());
		$.ajax({
			url: 'report/performanceTracing/querySchemePreformance.do',
			data: {
				popularizeId: $('#select_product').val()
			},
			success: function(data) {
				$('#perfromance_content').html(template('perfromance_content_template', data));
				if (data.shememId) {
					$('#my_btn').html(template('btns', {state: 1}));
				} else {
					$('#my_btn').html(template('btns', {state: 0}));
				}
			},
			error: function() {
				displayNoData('load_performance_error');
			},
			dataType: 'json'
		});
	}
	
	return {
		initData: function() {
			$.ajax({
				url: 'report/performanceTracing/initData.do', 
				success: function(data) {
					if (data.schemeArray) {
						$('#select_product').html(template('scheme_select', handleSchemeData(data)));
						queryPerformance();
					} else {
						displayNoData('no_performance_data');
					}
				},
				error: function() {
					displayNoData('load_schemes_error');
				},
				dataType: 'json'
			});
		},
		queryPerformance: queryPerformance,
		goFriendsDevote: function() {
			var selP = $('#select_product').val();
			setPopularizeId(selP);
			location.href = 'pages/report/friendsDevote.html?v=v1.0.9_20150005&popularizeId=' + selP;
		}
	}
})();

$(function() {
	weixin.config({
        debug: false,
        jsApiList: ['onMenuShareTimeline','hideOptionMenu','hideMenuItems', 'showOptionMenu', 'closeWindow','onMenuShareAppMessage','getNetworkType']
    });
	weixin.ready(function () {
		weixin.hideOptionMenu();
		perTracing.initData();
	});
//	$.sas.weixin.onWeixinPageReady(function() {
//		$.sas.weixin.hideOptionMenu();
//		perTracing.initData();
//	});
});