;(function($) {


	// time 2014-12-22 13:54 eason


	// 在手指抬起时判断是否有移动过手指
	// $.touchMoved();

	// hover效果，要配合css写上 e[hover] {}
	// $.fn.hover();
	// $.fn.hover(el); 委派事件

	// 移除hover效果
	// $.fn.removeHover();
	// $.fn.removeHover(el); 取消委派事件

	// 转场动画
	// $.fn.pageTo($(fn), { type , back , callback });

	// 是否在转场动画中
	// $.pageToing();

	// 返回当前获取焦点的input(textarea)，若没有则返回null
	// $.focus();

	// 是否为ios系统
	// $.isIOS();

	// 是否为android系统
	// $.isAndroid();

	// 禁止滚动，type为touchstart、touchmove之类的
	// $.stopDefault(type);

	// alert弹框(alert框为fixed定位)
	// $.alert.show({
	// 	title: "删除内容",
	// 	txt: "是否删除该内容",
	// 	txtY: "删除",
	// 	txtN: "取消",
	// 	callbackY: function(){
	// 		$.alert.show({
	// 			title: "提示",
	// 			txt: "该内容已被删除",
	// 			txtY: "确定"
	// 		});
	// 	}
	// });

	// alert最简单写法
	// $.alert.show("提示内容");
	
	// 关闭弹框,callback为关闭后的回调，可不写
	// $.alert.hide(callback);

	// loading框(loading框为fixed定位)
	// $.loading.show();
	// $.loading.hide();


	// 滑动事件
	// callback中带2个参数，第一个值string, 为"up", "down", "left", "right", "none", 如果moving为true,在滑动过程中一直会回调，期间第一个值为moving
	// 第二个参数为 {x: number, y: number}, 分别为x坐标与y坐标的偏移量(按下与抬起之间的偏移量)
	// $.fn.swipe(callback, moving);


	// 去头尾空格，如果第二个参数为true会把<>箭头换成&lt;和&gt;
	// $.trim(e, bool);

	// 获取字符串的真实长度，即中文为2个，英文为1个的形式
	// $.realLength(val);

	// 封装的touchend事件，第一个为回调，第二个为true则不判断手指有移动，并且该方法无需在最后写return false，不会有穿透事件
	// $.fn.touchend(cb, canMove);
	// $.fn.offTouchend();

	// 分享提示框
	// $.popupShare();


	// share弹框
	$.popupShare = function(){
		if (!$.popupShare.show){
			if ($("#_sharePopup_").length){
				$("#_sharePopup_").remove();
			}
			$.popupShare.show = true;
			var html = "<div id='_sharePopup_'><span></span></div>";
			$("body").append(html);
			setTimeout(function(){
				$("#_sharePopup_").addClass("show").touchend(fnCloseSharePopup);
			},0);
		}
	}

	function fnCloseSharePopup(){
		$("#_sharePopup_").offTouchend(fnCloseSharePopup);
		$("#_sharePopup_").removeClass("show");
		setTimeout(function(){
			$("#_sharePopup_").remove();
			$.popupShare.show = false;
		},350);
	}

	// touchend事件
	$.fn.touchend = function(cb, bool){
		if (cb && typeof cb == "function"){
			$(this)._a = 0;
			$(this).hover().on("touchend", function(){
				var _this = $(this);
				if ((!$.touchMoved() && !$.pageToing()) || bool){
					cb.apply(_this);
				}
				return false;
			});
		}
	};

	// 卸载touchend事件
	$.fn.offTouchend = function(){
		$(this).removeHover().off("touchend");
	}

	function fnTouchend(){
		var _this = $(this), cb = _this[0]._touchendCB, bool = _this[0]._touchCanMove;
		if ((!$.touchMoved() && !$.pageToing()) || bool){
			cb.apply(_this);
		}
		return false;
	}

	// 获取字符串的真实长度，即中文为2个，英文为1个的形式
	$.realLength = function(val){
		if (val && val.length) {
		    var realLength = 0, len = val.length, charCode = -1;
		    for (var i = 0; i < len; i++) {
		        charCode = val.charCodeAt(i);
		        if (charCode >= 0 && charCode <= 128) realLength += 1;
		        else realLength += 2;
		    }
		    return realLength;
		}
		return 0;
	}


	// 去空格 还有个功能就是把<>这些标签换成&lt;和&gt;
	$.trim = function(e, b) {
		if (b == undefined){ b = true;}
		if (b){
			return e.replace(/(^\s*)|(\s*$)/g,"").replace(/</g,"&lt;").replace(/>/g,"&gt;");
		} else {
			return e.replace(/(^\s*)|(\s*$)/g,"");
		}
	}

	$.alert = {
		show: function(o){
			if ($.focus()){
				$.focus().blur();
			}
			var t = 0, popup = $("#_alert_");
			if (popup.length != 0){
				popup.attr("class", "out");
				t = 200;
			}
			if (t == 0){
				$.alert._init(o);
			} else {
				setTimeout(function(){
					popup.remove();
					$.alert._init(o);
				}, t);
			}
		},
		_init: function(o){
			if (typeof o == "string"){
				o = {txt: o};
			}
			var popup = $("#_alert_");
			$.alert._o = o;
			o.title = o.title ? o.title : "提示";
			var html = "<div id='_alert_'><div class='popup'>";
			html += "<h1>" + o.title + "</h1><div class='p'><p>" + o.txt + "</div></p>";
			html += "<div class='btns'>";
			if (o.txtN){
				html += "<a href='javascript:void(0)' class='n'>" + o.txtN + "</a>";
			}
			o.txtY = o.txtY ? o.txtY : "确定";
			html += "<a href='javascript:void(0)' class='y'>" + o.txtY + "</a></div></div>";
			$("body").append(html);
			$(document).on("touchstart", $.alert._pdefault);
			popup = $("#_alert_");
			setTimeout(function(){
				popup.addClass("show");
			},0);
			popup.find(".popup .btns .y, .popup .btns .n").hover().on("touchend", $.alert._click);
		},
		_click: function(){
			if (!$.touchMoved()){
				if ($(this).hasClass("y")){
					$.alert.hide($.alert._o.callbackY || $.alert._o.callback);
				} else {
					$.alert.hide($.alert._o.callbackN);
				}
			}
			return false;
		},
		hide: function(callback){
			var popup = $("#_alert_");
			popup.attr("class", "out").find(".popup .btns .y, .popup .btns .n").removeHover().off("touchend", $.alert._click);
			if (typeof callback == "function"){
				callback();
			}
			$(document).off("touchstart", $.alert._pdefault);
			setTimeout(function(){
				popup.remove();
			},200);
			$.alert._o = null;
		},
		_pdefault: function(e){
			e.preventDefault();
		}
	};

	$.loading = {
		show: function(){
			if ($("#_loading_").length){
				$("#_loading_").remove();
			}
			var html = "<div id='_loading_'><span></span></div>";
			$("body").append(html);
			$(document).on("touchstart", $.alert._pdefault);
		},
		hide: function(){
			$(document).off("touchstart", $.alert._pdefault);
			$("#_loading_").remove();
		}
	}



	// 侦听整个页面input的focus和blur事件，使其在转场的时候失去焦点
	var inputFocus = {
		init: function() {
			$("body").on("focus blur", "input, textarea, select", this.inputEvents);
		},
		inputEvents: function(e) {
			if (e.type === "focusin") {
				inputFocus.is = true;
				inputFocus.elem = this;

			} else {
				inputFocus.is = false;
				inputFocus.elem = null;
			}
		},
		is: false,
		elem: null
	};
	inputFocus.init();

	// 当touchstart之后手指有移动后，$.touchMoved()为false，如果没有移动，为true
	var touchmove = {
		moved: false,
		init: function() {
			$(document).on("touchstart", this.moved);
			$(document).on("touchmove", this.moved);
		},
		moved: function(e)  {
			if (e.type == "touchstart") {
				touchmove._x = e.originalEvent.changedTouches[0].pageX;
				touchmove._y = e.originalEvent.changedTouches[0].pageY;
				touchmove.moved = false;
			} else if (e.type == "touchmove") {
				var _x = e.originalEvent.changedTouches[0].pageX;
				var _y = e.originalEvent.changedTouches[0].pageY;
				if (Math.abs(_x - touchmove._x) > 10 || Math.abs(_y - touchmove._y) > 10){
					touchmove.moved = true;
				}
			}
		}
	};
	touchmove.init();
	$.touchMoved = function(){
		return touchmove.moved;
	};

	// 为touchStart和touchEnd添加和删除hover效果
	// 并做延时处理，防止用户在拖拽的时候会也响应hover效果
	// 也就是说，用户只有在按住不动的10毫秒后响应
	var touchHover = {
		start: function(e){
			var _this = $(this);
			var _t = setTimeout(function(){
				_this.attr("hover", "on");
			}, 10);
			_this.attr("hoverTimeout", _t);
		},
		move: function(e) {
			var _this = $(this);
			clearTimeout(_this.attr("hoverTimeout"));
			_this.removeAttr("hoverTimeout");
			_this.removeAttr("hover");
		},
		end: function(e){
			var _this = $(this);
			clearTimeout(_this.attr("hoverTimeout"));
			_this.removeAttr("hoverTimeout");
			_this.removeAttr("hover");
		}
	};
	$.fn.hover = function(el) {
		if (el){
			this.on("touchstart", el, touchHover.start);
			this.on("touchmove", el, touchHover.move);
			this.on("touchend", el, touchHover.end);
		} else {
			this.on("touchstart", touchHover.start);
			this.on("touchmove", touchHover.move);
			this.on("touchend", touchHover.end);
		}
		return this;
	};
	$.fn.removeHover = function(el) {
		if (el){
			this.off("touchstart", el, touchHover.start);
			this.off("touchmove", el, touchHover.move);
			this.off("touchend", el, touchHover.end);
		} else {
			this.off("touchstart", touchHover.start);
			this.off("touchmove", touchHover.move);
			this.off("touchend", touchHover.end);
		}
		return this;
	};

	// 页面转场动画中的一些参数
	var pageToVars = {
		pageToing: false,
		current: null,
		target: null,
		type: null,
		callback: null,
		hover: null,
		ab: 1,
		back: null,
		afinish: false,
		bfinish: false,
		timeout: 0,
		timeout2: 0,
		runTime: 0.4,
		ease: "ease-out",
		// 根据pid属性判断两个页面的关系
		// 1表示目标页为后页, -1表示目标页为前页, 0表示两页不在同一层级
		catchId: function(a, b){
			if (a && b) {
				if (a > b){
					return 1;
				} else if (a < b){
					return -1;
				} else{
					return 0;
				}
			}
		},
		start: function() {
			// 如果正在转场，不执行动画
			if (pageToVars.pageToing) {
				return false;
			}
			clearTimeout(this.timeout2);
			pageToVars.pageToing = true;

			// body取消点击事件
			$("body").addClass("pageToing");

			// 如果转场动画为none或者设备为android2.x|3.x
			if (this.type == "none") {
				this.end();
				return false;
			}
			
			// 如果有高亮，添加
			if (this.hover) {
			    clearTimeout( this.hover.attr("hoverTimeout") );
                this.hover.removeAttr("hoverTimeout");
			    this.hover.attr("hover", "on");
			}

			// 如果没有设置过back参数，判断两页面的关系
			if (this.back == undefined) {
				this.ab = this.catchId(this.current.data("p"), this.target.data("p"));
			} else {
				this.ab = this.back ? -1 : 1;
			}

			// 初始化动画完成的布尔
			this.afinish = false;
			this.bfinish = false;

			// 开始做转场动画
			this.current.css({"zIndex": "2", "display": "block"}).on("webkitAnimationEnd", pageToVars.onevt);
			this.target.css({"zIndex": "1"}).on("webkitAnimationEnd", pageToVars.onevt);

			switch (pageToVars.type) {
				case "move":
					pageToVars.runTime = 0.8;
					pageToVars.ease = "cubic-bezier(0.86,0,0.07,1)";
					if (pageToVars.ab != -1) {
						pageToVars.keyframeA = "pageAnimateMoveInCurrent";
						pageToVars.keyframeB = "pageAnimateMoveInTarget";
					} else {
						pageToVars.keyframeA = "pageAnimateMoveOutCurrent";
						pageToVars.keyframeB = "pageAnimateMoveOutTarget";
					}
					break;
				case "slide":
					pageToVars.runTime = 0.8;
					pageToVars.ease = "cubic-bezier(0.86,0,0.07,1)";
					if (pageToVars.ab != -1) {
						pageToVars.keyframeA = "pageAnimateSlideInCurrent";
						pageToVars.keyframeB = "pageAnimateSlideInTarget";
					} else {
						pageToVars.keyframeA = "pageAnimateSlideOutCurrent";
						pageToVars.keyframeB = "pageAnimateSlideOutTarget";
					}
					break;
				case "fb":
					pageToVars.runTime = 0.4;
					pageToVars.ease = "ease-out";
					if (pageToVars.ab != -1) {
						pageToVars.keyframeA = "pageAnimateFbInCurrent";
						pageToVars.keyframeB = "pageAnimateFbInTarget";
					} else {
						pageToVars.keyframeA = "pageAnimateFbOutCurrent";
						pageToVars.keyframeB = "pageAnimateFbOutTarget";
					}
					break;
				case "cover":
					pageToVars.runTime = 0.3;
					pageToVars.ease = "ease-out";
					if (pageToVars.ab != -1) {
						pageToVars.keyframeA = "pageAnimateCoverInCurrent";
						pageToVars.keyframeB = "pageAnimateCoverInTarget";
					} else {
						pageToVars.keyframeA = "pageAnimateCoverOutCurrent";
						pageToVars.keyframeB = "pageAnimateCoverOutTarget";
					}
					break;
				case "fade":
					pageToVars.runTime = 0.3;
					pageToVars.ease = "ease-out";
					pageToVars.keyframeA = "pageAnimateFadeCurrent";
					pageToVars.keyframeB = "pageAnimateFadeTarget";
					break;
				case "scale":
					pageToVars.runTime = 0.15;
					pageToVars.ease = "ease-out";
					if (pageToVars.ab != -1) {
						pageToVars.keyframeA = "pageAnimateScaleInCurrent";
						pageToVars.keyframeB = "pageAnimateScaleInTarget";
					} else {
						pageToVars.keyframeA = "pageAnimateScaleOutCurrent";
						pageToVars.keyframeB = "pageAnimateScaleOutTarget";
					}
					break;
				case "poker":
					pageToVars.runTime = 0.25;
					pageToVars.ease = "linear";
					pageToVars.current.parent().css("-webkit-perspective", "2000px");
					if (pageToVars.ab != -1) {
						pageToVars.keyframeA = "pageAnimatePokerInCurrent";
						pageToVars.keyframeB = "pageAnimatePokerInTarget";
					} else {
						pageToVars.keyframeA = "pageAnimatePokerOutCurrent";
						pageToVars.keyframeB = "pageAnimatePokerOutTarget";
					}
					break;
			}

			// 添加animation样式，动画形式为scale或poker时，target暂时不添加，并隐藏
			if (this.type !== "scale" && this.type !== "poker") {
				var th = this;
				th.target.css({"display": "block", "opacity": "0"});
				setTimeout(function(){
					th.current.css({"-webkit-animation": pageToVars.keyframeA + " " + pageToVars.runTime + "s " + pageToVars.ease});
					th.target.css({"opacity": "", "-webkit-animation": pageToVars.keyframeB + " " + pageToVars.runTime + "s " + pageToVars.ease});
				},0);
			} else {
				this.current.css("-webkit-animation", pageToVars.keyframeA + " " + pageToVars.runTime + "s " + pageToVars.ease);
				this.target.css("display", "none");
			}

			// 动画安全锁，2秒后动画未结束，自动结束
			clearTimeout(this.timeout);
			this.timeout = setTimeout(function(){
				if (pageToVars.pageToing) {
					pageToVars.current.off("webkitAnimationEnd", pageToVars.onevt);
					pageToVars.target.off("webkitAnimationEnd", pageToVars.onevt);
					pageToVars.end();
				}
			},2000);
		},
		onevt: function(e) {
			if (e.type === "webkitAnimationEnd") {
				// 如果事件对象是current，说明current的动画已经完成
				// current隐藏，删除侦听，并删除动画时添加的样式
				// 如果转场动画为scale或poker，执行下半部分动画
				if (e.target == pageToVars.current[0]) {
					pageToVars.afinish = true;
					pageToVars.current.css("display", "none");
					pageToVars.current.off("webkitAnimationEnd", pageToVars.onevt);
					if (pageToVars.type === "scale" || pageToVars.type === "poker") {
						if (pageToVars.type === "poker") {
							pageToVars.current.parent().css("-webkit-perspective", "");
							pageToVars.current.parent().css("-webkit-transform-style", "");
							pageToVars.target.parent().css("-webkit-perspective", "2000px");
						}
						pageToVars.target.css("display", "block", "-webkit-animation", pageToVars.keyframeB + " " + pageToVars.runTime + "s " + pageToVars.ease);
					} else if (pageToVars.bfinish) {
						pageToVars.end();
					}
				} else if (e.target == pageToVars.target[0]) {
					pageToVars.bfinish = true;
					pageToVars.target.off("webkitAnimationEnd", pageToVars.onevt);
					if (pageToVars.bfinish) {
						pageToVars.end();
					}
				}
			}
		},
		end: function() {
			if (pageToVars.pageToing) {
				
				// body恢复点击事件
				$("body").removeClass("pageToing");
	            
	            // 如果有高亮，去除
	            if (this.hover) {
	                this.hover.removeAttr("hover");
	            }
	            
				// 去除动画样式
				pageToVars.current.css({"-webkit-animation": "", "z-index": ""});
				pageToVars.target.css({"-webkit-animation": "", "z-index": ""});
				if (pageToVars.type === "poker") {
					pageToVars.current.parent().css("-webkit-perspective", "");
					pageToVars.target.parent().css("-webkit-perspective", "");
				}

				// 转出去的页隐藏，转进来的页显示
				this.current.css("display", "none");
				this.target.css("display", "block");

				// 如果有回调函数，回调
				if (this.callback) {
					this.callback();
					this.callback = null;
				}

				// 动画结束变量赋值
				pageToVars.pageToing = false;
			}
		}
	};


	// 页面转场动画，适用于打包成app的应用
	$.fn.pageTo = function(el, o) {
		// 如果在转场时有input的获焦状态，则取消
		if (inputFocus.is) {
			$(inputFocus.elem).blur();
			inputFocus.is = false;
			inputFocus.elem = null;
		}
		// 新版写法 E.pageTo(E, { type: "move", callback: fn, hover: E});
		if (o) {
			pageToVars.current = this;
			pageToVars.target = el;
			pageToVars.type = o.type || "none";
			pageToVars.callback = o.callback;
			pageToVars.hover = o.hover;
			pageToVars.back = o.back;
			pageToVars.start();
		} else {
			if (pageToVars.callback) {
				pageToVars.callback();
			}
			this.css("display", "none");
			return el.css("display", "block");
		}
	};



	// 滑动事件
	$.fn.swipe = function(callback, moving){
		if (moving){
			$(this).on("touchstart touchmove touchend", onFnSwipe)[0]._swipeCB = callback;
		} else {
			$(this).on("touchstart touchend", onFnSwipe)[0]._swipeCB = callback;
		}
		$(this)[0]._moving = moving || false;
	};
	$.fn.offSwipe = function(){
		$(this).off("touchstart touchmove touchend", onFnSwipe)[0]._swipeCB = undefined;
		$(this)[0]._moving = undefined;
	};

	function onFnSwipe(e){
		var _this = $(this);
		var _x = e.originalEvent.changedTouches[0].pageX;
		var _y = e.originalEvent.changedTouches[0].pageY;
		var _moving = _this[0]._moving;

		if (e.type == "touchstart"){
			_this[0]._x = _x;
			_this[0]._y = _y;
		} else if (e.type == "touchend" && _this[0]._swipeCB) {
			var _ost = Math.abs((_x - _this[0]._x) / (_y - _this[0]._y));
			if (_ost < 1){
				if (_this[0]._y - _y < -20){
					_this[0]._swipeCB("down", {x: _x - _this[0]._x, y: _y - _this[0]._y});
				} else if (_this[0]._y - _y > 20){
					_this[0]._swipeCB("up", {x: _x - _this[0]._x, y: _y - _this[0]._y});
				} else {
					_this[0]._swipeCB("none", {x: _x - _this[0]._x, y: _y - _this[0]._y});
				}
			} else if (_ost > 3){
				if (_this[0]._x - _x < -20){
					_this[0]._swipeCB("right", {x: _x - _this[0]._x, y: _y - _this[0]._y});
				} else if (_this[0]._x - _x > 20){
					_this[0]._swipeCB("left", {x: _x - _this[0]._x, y: _y - _this[0]._y});
				} else {
					_this[0]._swipeCB("none", {x: _x - _this[0]._x, y: _y - _this[0]._y});
				}
			} else {
				_this[0]._swipeCB("none", {x: _x - _this[0]._x, y: _y - _this[0]._y});
			}
		} else if (_moving && e.type == "touchmove"){
			_this[0]._swipeCB("moving", {x: _x - _this[0]._x, y: _y - _this[0]._y});
		}
	}



	// 返回是否在动画过程中的bool
	$.pageToing = function(){
		return pageToVars.pageToing;
	};

	// 返回正获取焦点的input(textarea)
	$.focus = function(){
        return inputFocus.elem;
    };

    // 返回是否为ios系统
    $.isIOS = function(){
		return (/iphone|ipad/gi).test(navigator.appVersion);
	};

	// 返回是否为android系统
	$.isAndroid = function(){
		return (/android/gi).test(navigator.appVersion);
	};

	// 阻止系统的冒泡事件
	$.stopDefault = function(type){
		document.addEventListener(type, function (e) { e.preventDefault(); }, false);
	};




})(jQuery);
