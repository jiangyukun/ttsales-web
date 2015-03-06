jQuery.rt = jQuery.rt || {};
jQuery.rt.util = {
	getRequest : function() {
		var url = location.search; // 获取url中"?"符后的字串
		var theRequest = new Object();
		if (url.indexOf("?") != -1) {
			var str = url.substr(1);
			strs = str.split("&");
			for ( var i = 0; i < strs.length; i++) {
				var index = strs[i].indexOf("=");
 				theRequest[strs[i].substring(0,index)] = unescape(strs[i].substring(index+1,strs[i].length));
 			}
		}
		return theRequest;
	},
	getUrl : function(){
		var url = window.location.href;
		var index = url.indexOf("?");
		if (index != -1) {
			url = url.substr(0,index);
		}
		return url;
	},
	getUrlParam : function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	},
	uuid : function(len, radix) {
		var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('');
		var uuid = [], i;
		radix = radix || chars.length;

		if (len) {
			// Compact form
			for (i = 0; i < len; i++){
				uuid[i] = chars[0 | Math.random() * radix];
			}
		} else {
			// rfc4122, version 4 form
			var r;
			// rfc4122 requires these characters
			uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
			uuid[14] = '4';
			// Fill in random data. At i==19 set the high bits of clock sequence
			// as
			// per rfc4122, sec. 4.1.5
			for (i = 0; i < 36; i++) {
				if (!uuid[i]) {
					r = 0 | Math.random() * 16;
					uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
				}
			}
		}
		return uuid.join('').replace(/-/g, '');
	},
	isNull : function(obj) {
		if(obj == null || obj == "" || obj == "undefined"){
			return true;
		}
		return false;
	},
	formatDate : function(date, format) {
		var paddNum = function(num) {
			num += "";
			return num.replace(/^(\d)$/, "0$1");
		};
		var getWeekDay = function(d) {
			if (d == 1) {
				return "星期一";
			} else if (d == 2) {
				return "星期二";
			} else if (d == 3) {
				return "星期三";
			} else if (d == 4) {
				return "星期四";
			} else if (d == 5) {
				return "星期五";
			} else if (d == 6) {
				return "星期六";
			} else if (d == 0) {
				return "星期日";
			}
		};
		// 指定格式字符
		var cfg = {
			yyyy : date.getFullYear(), // 年 : 4位
			yy : date.getFullYear().toString().substring(2),// 年 : 2位
			M : date.getMonth() + 1, // 月 : 如果1位的时候不补0
			MM : paddNum(date.getMonth() + 1), // 月 : 如果1位的时候补0
			d : date.getDate(), // 日 : 如果1位的时候不补0
			dd : paddNum(date.getDate()),// 日 , 如果1位的时候补0
			h : date.getHours(), // 时
			hh : paddNum(date.getHours()), // 时,如果1位的时候补0
			m : date.getMinutes(), // 分
			mm : paddNum(date.getMinutes()), // 分,如果1位的时候补0
			s : date.getSeconds(), // 秒
			ss : paddNum(date.getSeconds()), // 秒 ,如果1位的时候补0
			w : getWeekDay(date.getDay())
		// 周几
		};
		format || (format = "yyyy-MM-dd hh:mm:ss");
		return format.replace(/([a-z])(\1)*/ig, function(m) {
			return cfg[m];
		});
	},
	stringify : function( obj ) {
		var arr = [];
		$.each( obj, function( key, val ) {
			var next = key + ": ";
			if(typeof val =='string'){
				next += $.isPlainObject( val ) ? printObj( val ) : "'"+val+"'";
			}else{
				next += $.isPlainObject( val ) ? printObj( val ) : val;

			}
			arr.push( next );
		});
		return "{ " + arr.join( ", " ) + " }";
	},

	/*  */
	createTag: {
		div: function () {
			return $('<div>');
		},
		span: function () {
			return $('<span>');
		}
	},
	generateID: function (object, prefix, suffix) {
		var obj = {};
		for (var o in object) {
			if (object.hasOwnProperty(o) && typeof object[o] == 'string') {
				obj[o] = prefix + object[o] + suffix;
			}
		}
		return obj;
	}
	/* end */

};