var myFriend = function(){
	var defaults = {
		friendId : null,
		timeOut:null,
		page:1,
		rows:50,
	};
	
	function queryData(event,content){
		$.ajax({
			type:'POST',
			url:'sys/myFriends/initData.do',
			data : {
				page : defaults.page,
				rows : defaults.rows
			},
			dataType : 'json',
			success : function(data,textStatus, jqXHR) {
				//如果非企业号成员打开本页面,跳转到 【关于我们】页面
				if(data.code=='jumpToAboutUs'){
					window.location.href="pages/sys/aboutUs.jsp";
					return;
				}
				
				if(data.total == 0){
					$("#no_friends_tips").show();
					content.parent().parent().hide();
					event.allComplete();
					return;
				}

				var html = template('friend_item', data);
				if(data.rows.length < defaults.rows){
					content.append(html);
					event.allComplete();
				}else{
					content.append(html);
					event.complete();
				}
				defaults.page = defaults.page + 1;
			}
		});
	}
	
	function queryData11(event,content){
		$.ajax({
			type:'POST',
			url:'sys/myFriends/initData.do',
			data : {
				page : defaults.page,
				rows : defaults.rows
			},
			dataType : 'json',
			success : function(data,textStatus, jqXHR) {
				//如果非企业号成员打开本页面,跳转到 【关于我们】页面
				if(data.code=='jumpToAboutUs'){
					window.location.href="pages/sys/aboutUs.jsp";
					return;
				}
				
				if(data.rows.length == 0 && defaults.page == 1){
					$("#no_friends_tips").show();
					$("#scrollContent").hide();
					return;
				}

				var html = template('friend_item', data);
				if(data.rows.length < defaults.rows){
					if(event){
						event.getAppendContext().append(html);
						event.complete();
						event.allComplete();
					}else{
						$("#friendList").find("ul").html(html);
					}
				}else{
					if(event){
						event.getAppendContext().append(html);
						event.complete();
					}else{
						$("#friendList").find("ul").html(html);
					}
				}
				defaults.page = defaults.page + 1;
			}
		});
	}
	
	
	return {
		initPage: function(){
			$('#friendList').rtScroll({eventName: "scrollEnd", init: true}, function(event, context) {
				queryData(event,context);
			})
			$('#friendList').on('elementClicked', function(event, elem) {
				var id = $(elem).attr('id');
				if (id)  {
					if (id.indexOf('click_btn') != -1) {
						location.href =encodeURI(encodeURI("pages/sys/invitation.html?fName="+elem.dataset.fname+"&inviId="+elem.dataset.memberid));
					}
				}
			});
			/*queryData();
			
			$("#scrollContent").scrollFootEvent(function (event) {
				queryData(event);
			 });*/
		},
		confirm : function(){
			$.ajax({
				type:'POST',
				url:'sys/myFriends/deleteFriend.do',
				data:{
					'friendId':defaults.friendId
				},
				dataType : 'json',
				success : function(data, textStatus, jqXHR) {
					var code = data.code;
					if(code==1){
						$("[data-id='"+defaults.friendId+"']").remove();
					}
				}
			});
			$( "#cancleDialog" ).hide();
		},
		/*再次邀请*/
		/*invite : function(fName,userId,inviId){
			alert("222");
			location.href =encodeURI(encodeURI("pages/sys/invitation.html?fName="+fName+"&userId="+userId+"&inviId="+inviId));
		},*/
		/*取消按钮*/
		negative : function(){
			$( "#cancleDialog" ).hide();
		},
	/* 	mousedown : function(userId){
			defaults.timeOut = window.setTimeout(function (){
				defaults.friendId = userId;
				var friendName = $("[data-id='"+userId+"'] .name_input").val();
				$( "#show_content" ).text("你确定要删除好友【"+friendName+"】吗？");
				$( "#cancleDialog" ).show();  	
			}
			,500);	
		},
		mouseup : function(){
			clearTimeout(defaults.timeOut)
		} */
	};
}();

	
$(function() {
	weixin.config({
        debug: false,
        jsApiList: ['onMenuShareTimeline','hideOptionMenu','hideMenuItems', 'showOptionMenu', 'closeWindow','onMenuShareAppMessage','getNetworkType']
    });
	weixin.ready(function () {
		weixin.hideOptionMenu();
		myFriend.initPage();
	});
//	$.sas.weixin.onWeixinPageReady(function(){ 
//		$.sas.weixin.hideOptionMenu();
//		myFriend.initPage();
//	});
});