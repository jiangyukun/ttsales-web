/*
 * dependency jquery.sas.js, humane.min.js
 */
jQuery.sas.location = {
	iCallback : function () {},
	iOpenId : "",
	//iReTry : true,
	iRequestCount:0,
	iTimes:2,
	//iSucessed : false,
	get : function(openId,callback) {//callback
		//通过浏览器获取位置
/*		$.mobile.loading( 'show', {
			  text: "正在校验当前位置，请稍后...",
			  textVisible: true,
			  theme: $.mobile.loader.prototype.options.theme,
			  textonly: false,
			  html: ""
		});*/
		$.rt.rtLoading.show('定位中..');
		//this.iSucessed = false;
		this.iOpenId = openId;
		this.iCallback = callback;
		//this.getPositionByLocal();
		this.getPositionByServer();
	},
	getPositionByLocal : function () {
		//humane.info("getPositionByLocal start...");
		var obj = this;
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(
					function (position) {obj.successByLocal(position,obj);}, 
					function (error) {obj.showErrorByLocal(error,obj);}
					);
		} else {
			//humane.info("当前浏览器不支持获取地理位置，正尝试从微信服务器获取...");
		}
	},
	successByLocal : function(position,obj) {
		var result = obj.iCallback(position);
		if (!result) {
			//humane.info("正尝试重新获取...");
			window.setTimeout(function(){obj.getPositionByLocal();},2000);
		} else{
			//$.mobile.loading("hide");
			$.rt.rtLoading.remove();
		}
	},
	showErrorByLocal : function(error,obj) {
		switch (error.code) {
			case error.PERMISSION_DENIED: {
				//humane.info("用户拒绝从本地获取地理定位。");
				break;
			}
			case error.POSITION_UNAVAILABLE: {
				//humane.info("从本地获取的地理位置信息不可用。");
				break;
			}
			case error.TIMEOUT: {
				/*
				if (obj.iReTry) {
					humane.info("从本地获取地理位置超时，正尝试重新获取...");
					window.setTimeout(function(){obj.getPositionByLocal();},5000);
				} else
					humane.info("从本地获取地理位置超时。");*/
				break;
			}
			case error.UNKNOWN_ERROR: {
				
				/*if (obj.iReTry) {
					humane.info("从本地获取地理位置失败，正尝试重新获取..");
					window.setTimeout(function(){obj.getPositionByLocal();},5000);
				} else
					humane.info("从本地获取地理位置超时。");*/
				break;
			}
			default: {
				//humane.info(error.message);
				break;
			}
		}
		//重发
		obj.iRequestCount = obj.iRequestCount +1;
		if(obj.iRequestCount < obj.iTimes){
			window.setTimeout(function(){obj.getPositionByLocal();},2000);
		}else{
			humane.info("获取地理位置失败,无法签到。");
			//$.mobile.loading("hide");
			$.rt.rtLoading.remove();
		}
	},
	getPositionByServer : function () {
		var obj = this;
		var url = $.sas.common.getRootPath()+"/store/location/myLocation.do?openId="+this.iOpenId;
		$.getJSON(url, function(json) {
			if (json.coords != null &&(typeof json.coords) != 'undefined')
				obj.successByServer(json,obj);
			else
				obj.showErrorByServer(json,obj);		
		});
	},
	successByServer : function(position,obj) {
		obj.iCallback(position);
		//$.mobile.loading("hide");
		$.rt.rtLoading.remove();
	},
	showErrorByServer : function(error,obj) {
		obj.iRequestCount = obj.iRequestCount + 1;
		if(obj.iRequestCount < obj.iTimes){
			window.setTimeout(function(){obj.getPositionByServer();},5000);
		}else{
			//客户端开始
			obj.iRequestCount = 0;
			obj.getPositionByLocal();
		}
	}
};

