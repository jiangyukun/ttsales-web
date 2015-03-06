(function($) {
	
	function createRtDialog(target) {
		var dialog = $(target);
		dialog.addClass('rt-dialog');
		dialog.addClass('rt-dialog-noshow');
		var title = dialog.children("[data-role='rtDialog-title']");
		title.addClass('rt-dialog-title');
		var colseButtonTitle =title.children("[data-role='rtDialog-closeButton']");
		colseButtonTitle.addClass('rt-dialog-close-button');
		colseButtonTitle.unbind().bind('click', function() {
			dialog.rtDialog('hide'); 
		});
		var foot = dialog.children("[data-role='rtDialog-foot']");
		foot.addClass('rt-dialog-foot');
		var cancelButtonFoot =foot.find("[data-role='rtDialog-cancelButton']");
		var okButtonFoot =foot.find("[data-role='rtDialog-okButton']");
		cancelButtonFoot.addClass('rt-dialog-cancel-button');
		okButtonFoot.addClass('rt-dialog-ok-button');
		cancelButtonFoot.unbind().bind('click', function() {
			dialog.rtDialog('hide'); 
		});
		var content = dialog.children("[data-role='rtDialog-content']");
		content.addClass('rt-dialog-content');
		dialog.css({
			position : "fixed",
			left : ($(window).width() - $(target).outerWidth()) / 2,
			top : ($(window).height() - $(target).outerHeight()) / 2
		});
	}

	function show(target) {
		$.rt.rtMask.show();
		$(target).removeClass('rt-dialog-noshow');
	}

	function hide(target) {
		$.rt.rtMask.hide();
		$(target).addClass('rt-dialog-noshow');
	}

	$.fn.rtDialog = function(options, params) {
		if (typeof options === 'string') {
			return $.fn.rtDialog.methods[options](this, params);
		}
		options = options || {};
		return this.each(function() {
			$(this).rtDialog("options",options);  
			createRtDialog(this);
		});
	};
	
	
	$.fn.rtDialog.defaults = {

	};

	$.fn.rtDialog.methods = {
		 options : function (jq,options){
			return jq.each(function() {
		    	var state = $.data(this, 'rtDialog'); 
				if (state) {
					$.data(this, 'rtDialog', { 
						options :$.extend(state.options,$.rt.parse.parseOptions(this), options)
					});
				} else {
					$.data(this, 'rtDialog', {
						options : $.extend({}, $.fn.rtDialog.defaults,$.rt.parse.parseOptions(this), options)
					});  
				}   	
				$(this).attr('data-options',$.rt.util.stringify(options));
			});
		},
		show : function(jq) {
			return jq.each(function() {
				show(this);
			});
		},
		hide : function(jq) {
			return jq.each(function() {
				hide(this);
			});
		}

	};
})(jQuery);

$(function() {
	$("[data-role='rtDialog']").rtDialog();
});