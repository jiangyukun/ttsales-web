var popularize = function(){
	var defaults = {
		controllerUrl : 'bus/popularize'
	};
	
	return {
		initData : function(){
			$.ajax({
	 			type : "GET",
	 			url : defaults.controllerUrl + "/initData.do?state=01",
	 			success : function(data, textStatus, jqXHR) {
	 				var list = {busPopularizes:data};
	 				if(list.busPopularizes.length!=0){
	 					var html = template('busPopularizes_list', list);
		 				$("#content").html(html);
	 				}else{
	 					$("#no_products_tips").show();
	 				}
	 			},
	 			dataType : "json"
	 		}); 
		},
		gotoDetail : function(schemeId, popularizeId, index){
			var par = $.base64Encode("schemeId=" + schemeId + "&popularizeId="+popularizeId+"&pTransmitId=0&fTransmitId=0");
			window.location.href = $.sas.common.getRootPath()+'/pages/htmls/product_scheme_'+schemeId+'.html?v=v1.0.9_20150005&params='+par;
		}
	};
}();

$(function(){
	weixin.config({
        debug: false,
        jsApiList: ['onMenuShareTimeline','hideOptionMenu','hideMenuItems', 'showOptionMenu', 'closeWindow','onMenuShareAppMessage','getNetworkType']
    });
	weixin.ready(function () {
		weixin.hideOptionMenu();
		popularize.initData();
	});
//	$.sas.weixin.onWeixinPageReady(function(){ 
//		$.sas.weixin.hideOptionMenu();
//		popularize.initData();
//	});
}); 