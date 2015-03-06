var rankingList = function(){
	var defaults = {
		page:1,
		rows:10
	};
	function queryData11(event,schemeId){
		$.ajax({
			type:'POST',
			url:'bus/ranking/queryData.do',
			data:{
				schemeId : schemeId,
				page:defaults.page,
				rows:defaults.rows
			},
			dataType : 'json',
			success : function(data,textStatus, jqXHR) {
				if($.sas.util.isNull(data.scheme)){
					$('#my_ranking').text("暂无排名！");
					$('#my_bonus').text('￥0');
					if(event){
						event.complete();
						event.allComplete();
					}
					return;
				}
				if($.sas.util.isNull(data.myRanking) && defaults.page == 1){
					$('#my_ranking').text("暂无排名！");
					$('#my_bonus').text('￥0');
				}
				
				if(!$.sas.util.isNull(data.myRanking)){
					$('#my_ranking').text(data.myRanking.rank);
					$('#my_bonus').text('￥'+data.myRanking.bonus);
				}
				
				if(!$.sas.util.isNull(data.rankingList)){
					var html = template('ranking_item', data.rankingList);
					if(defaults.page == 1){
						if(event){
							event.getAppendContext().html("");
						}
						$("#rankingList").find("ul").html("");
						$("#rankingList").find("ul").html(html);
						defaults.page = defaults.page + 1;
						return;
					}
					if(data.rankingList.rows.length >= defaults.rows){
						if(event){
							event.getAppendContext().append(html);
							event.complete();
						}
					}else{
						if(event){
							event.getAppendContext().append(html);
							event.complete();
							event.allComplete();
						}
					}
				}
				defaults.page = defaults.page + 1;
			}
		});
	}
	
	function queryData(event,context,schemeId){
		$.ajax({
			type:'POST',
			url:'bus/ranking/queryData.do',
			data:{
				schemeId : schemeId,
				page:defaults.page,
				rows:defaults.rows
			},
			dataType : 'json',
			success : function(data,textStatus, jqXHR) {
				if($.sas.util.isNull(data.scheme)){
					$('#my_ranking').text("暂无排名！");
					$('#my_bonus').text('￥0');
					if(event){
						event.complete();
						event.allComplete();
					}
					return;
				}
				if($.sas.util.isNull(data.myRanking) && defaults.page == 1){
					$('#my_ranking').text("暂无排名！");
					$('#my_bonus').text('￥0');
				}
				
				if(!$.sas.util.isNull(data.myRanking)){
					$('#my_ranking').text(data.myRanking.rank);
					$('#my_bonus').text('￥'+data.myRanking.bonus);
				}
				
				
				
				if(!$.sas.util.isNull(data.rankingList)){
					if(data.rankingList.total == 0){
						context.html("");
						event.allComplete();
						return;
					}
					var html = template('ranking_item', data.rankingList);
					context.append(html);
					if(data.rankingList.rows.length >= defaults.rows){
						event.complete();
					}else{
						event.allComplete();
					}
				}
				defaults.page = defaults.page + 1;
			}
		});
	}
	return {
		initPage: function(){
			$.ajax({
				type:'POST',
				url:'bus/ranking/initPage.do',
				dataType : 'json',
				success : function(data,textStatus, jqXHR) {
					var opts = "<option value='' disabled selected>选择产品</option>";
					for(i=0;i<data.data.length;i++){
						if(i==0){
							opts =opts+"<option value='"+data.data[i].proProductScheme.schemeId+"' selected='selected'>"+data.data[i].proProductScheme.proProduct.productName+"</option>" 
						}else{
							opts =opts+"<option value='"+data.data[i].proProductScheme.schemeId+"'>"+data.data[i].proProductScheme.proProduct.productName+"</option>" 
						}
					} 
					$('#select_product').append(opts);
				}
			});
		},
		initData:function(){
			$('[data-role="rtScroll"]').rtScroll({eventName: "scrollEnd", init: true}, function(event, context) {
				queryData(event,context,"");
			})
			
			/*queryData(null,"");
			
			$("#scrollContent").scrollFootEvent(function (event) {
				queryData(event,"");
			});*/
			
			/*$.ajax({
				type:'POST',
				url:'bus/ranking/initData.do',
				dataType : 'json',
				success : function(data,textStatus, jqXHR) {
					if(data.code=='noScheme'||data.code=='noRankingList'){
						$('#my_ranking').text("暂无排名！");
						$('#my_bonus').text('￥0');
					}else if(data.code=='noMyRankingList'){
						$('#my_ranking').text("暂无排名！");
						$('#my_bonus').text('￥0');
						var html = template('ranking_item', data);
						$("#rankingList").html(html);
					}else{
						var html = template('ranking_item', data);
						$("#rankingList").html(html);
						var rank = data.info.rank;
						var bonus = data.info.bonus;
						$('#my_ranking').text(rank);
						$('#my_bonus').text('￥'+bonus);
					}
				}
			});*/
		},
		selectProduct:function(){
//			selectChange(event);
			defaults.page = 1;
			var schemeId = $("#select_product").find("option:selected").val();
			$('[data-role="rtScroll"]').rtScroll({eventName: "scrollEnd", init: true}, function(event, context) {
				//context.html("");
				queryData(event,context,schemeId);
			})
			
			/*$.ajax({
				type:'POST',
				url:'bus/ranking/updateData.do',
				data:{
					schemeId:$("#select_product").find("option:selected").val()
				},
				dataType : 'json',
				success : function(data,textStatus, jqXHR) {
					if(data.data.length==0){
						$('#rankingList').html("");
						$('#my_ranking').text("暂无排名！");
						$('#my_bonus').text('￥0');
					}else if(data.code=='noScheme'||data.code=='noRankingList'){
						$('#my_ranking').text("暂无排名！");
						$('#my_bonus').text('￥0');
					}else if(data.code=='noMyRankingList'){
						$('#my_ranking').text("暂无排名！");
						$('#my_bonus').text('￥0');
						var html = template('ranking_item', data);
						$("#rankingList").html(html);
					}else{
						var html = template('ranking_item', data);
						$("#rankingList").html(html);
						var rank = data.info.rank;
						var bonus = data.info.bonus;
						$('#my_ranking').text(rank);
						$('#my_bonus').text('￥'+bonus);
					}
				}
			});*/
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
		rankingList.initPage();
		rankingList.initData();
	});
//	$.sas.weixin.onWeixinPageReady(function(){ 
//		$.sas.weixin.hideOptionMenu();
//		rankingList.initPage();
//		rankingList.initData();
//	});
	
}); 