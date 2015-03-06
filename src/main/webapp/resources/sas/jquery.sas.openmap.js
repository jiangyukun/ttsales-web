/*
 * dependency jquery.sas.js
 */
jQuery.sas.openmap = {
	key : "X74BZ-7RLHU-F4YVY-44G7T-BXWX7-RZBCJ",
	getLocation : function(successCallback,errorCallback) {
		if (!navigator.geolocation) {
			alert("Geolocation is not supported by this browser.");
			return;
		}
		navigator.geolocation.getCurrentPosition(
			successCallback,
			errorCallback
		);
	},
	getLocationByIP:function(successCallback){
		var geolocation = new soso.maps.Geolocation();
		geolocation.position({}, function(results, status) {
	        if (status == soso.maps.GeolocationStatus.OK) {
	           /* alert(results.latLng);
	            alert(results.name);
	            alert(results.dtail);*/
	            successCallback(results);
	        } else {
	            alert("通过IP获取地理位置失败，原因: " + status);
	        }
	    });
	},
	//逆地址解析(坐标位置描述)
	geocoder : function(callback){
		var url = "http://apis.map.qq.com/ws/geocoder/v1/?output=jsonp&callback=?&key="+this.key+"&location=";
		this.getLocation(
			function(position) {
				url += position.coords.latitude + ","+ position.coords.longitude;
				$.getJSON(url, function(json) {
					callback(json);
				});
			},
			function(){
				$.sas.openmap.getLocationByIP(function(results){
					url += results.latLng.lat +"," +results.latLng.lng;
					$.getJSON(url, function(json) {
						callback(json);
					});
				});
			}
		);
	},
	getAdInfo : function(callback) {
		this.geocoder(function(json){
			callback(json.result.ad_info);
		});
	},
	errorCallback : function(error) {
		switch (error.code) {
		case error.PERMISSION_DENIED:
			alert("用户拒绝获取地理定位。");
			break;
		case error.POSITION_UNAVAILABLE:
			alert("地理位置信息是不可用。");
			break;
		case error.TIMEOUT:
			alert("获取地理位置超时。");
			break;
		case error.UNKNOWN_ERROR:
			alert("获取地理位置失败。");
			break;
		default:
			alert(error.message);
			break;
		}
	}
};

