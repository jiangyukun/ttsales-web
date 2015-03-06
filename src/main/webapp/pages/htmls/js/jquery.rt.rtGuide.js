/*
 * dependency [jquery.sas.js , jgestures.min.js]
 */
;(function($){
	
	if (!$) {
		throw 'jQuery.rt.rtCheckBox need jQuery.';
	}
	
	$.rt = $.rt || {};
	

	function fadeIn(jq) {
		jq.show();
		if (jq.hasClass("rt-guide-anim-fadeOut"))
			jq.removeClass("rt-guide-anim-fadeOut");
		jq.addClass("rt-guide-anim-fadeIn");
	}

	function fadeOut(jq) {
		if (jq.hasClass("rt-guide-anim-fadeIn"))
			jq.removeClass("rt-guide-anim-fadeIn");
		jq.addClass("rt-guide-anim-fadeOut");
		setTimeout(function() {
			jq.hide();
		}, 400);
	}
	
	function createOverlay(src){
		var html = "<div id=\"rt-guide-overlay\">";
		    html +=  "<img id=\"rt-guide-img\" src=\""+ src +"\" class=\"rt-guide-img\" />";
		    html += "</div>";
		if($('#rt-guide-overlay').length == 0){
			$("body").append(html);
		}else{
			$('#rt-guide-overlay').remove();
			$("body").append(html);
		}
	}
	
	function createGuide(target){
		var t = $(target);
		var opts = $.data(target, 'rtGuide').options;
		var guideBtn = $(opts.guideBtn);
		var guideImg = opts.guideImg;
 		createOverlay(guideImg);
		var jqOverlay = $("#rt-guide-overlay");
		var jqImgShare = $("#rt-guide-img");
		if(guideBtn.length > 0){
			guideBtn.on("mousedown", function() {
				//startBtnDownAnim(jqBtnShare, jqBtnSpanShare);
			}).on("mouseup", function() {
				fadeIn(jqOverlay);
				jqImgShare.show();
			});
		}
		jqOverlay.on("mouseup", function() {
			fadeOut(jqOverlay);
			jqImgShare.hide();
		});
	}
	
	function hide(){
		var jqOverlay = $("#rt-guide-overlay");
		var jqImgShare = $("#rt-guide-img");
		if(jqOverlay.length > 0){
			fadeOut(jqOverlay);
		}
		if(jqImgShare.length > 0){
			jqImgShare.hide();
		}
	}
	
	function show(){
		var jqOverlay = $("#rt-guide-overlay");
		var jqImgShare = $("#rt-guide-img");
		if(jqOverlay.length > 0){
			fadeIn(jqOverlay);
		}
		if(jqImgShare.length > 0){
			jqImgShare.show();
		}
	}
	
	$.fn.rtGuide  = function(options, param){
		if (typeof options == 'string') {
			return $.fn.rtGuide.methods[options](this, param);
		}
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'rtGuide');
			if (state) {
				$.extend(state.options,$.rt.parse.parseOptions(this),options);
			} else {
				$.data(this, 'rtGuide', {
					options: $.extend({},$.fn.rtGuide.defaults,$.rt.parse.parseOptions(this),options)
				}); 
			}
			createGuide(this);
		});
	};
	
	$.fn.rtGuide.methods = {
		hide: function(jq){
			return jq.each(function(){
				hide();
			});
		},
		show: function(jq){
			return jq.each(function(){
				show();
			});
		}
	};
	
	
	$.fn.rtGuide.defaults = {
		guideImg: '',	
		guideBtn: ''
	};
})(jQuery);

$(function(){
	$("[data-role='rtGuide']").rtGuide({
		 
	});
});