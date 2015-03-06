var trackLine = function(){
	var defaults = {
	};
	return {
		initPage:function(){
			$.ajax({
				type:'POST',
				url:'sys/transmitTemp/showTrackLine.do',
				data:{
					userCrossId:$.sas.util.getUrlParam('userCrossId'),
					popularizeId:$.sas.util.getUrlParam('popularizeId'),
				},
				dataType : 'json',
				success : function(data,textStatus, jqXHR) {
					var html = template('track_line', data);
					$("#trackLine").html(html);
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
		trackLine.initPage();
	});
//	$.sas.weixin.onWeixinPageReady(function(){ 
//		$.sas.weixin.hideOptionMenu();
//		trackLine.initPage();
//	});
});
