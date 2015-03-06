jQuery.rt.rtMask = {
	hasMask : function() {
		return $('#rt_mask').length !== 0;
	},

	onClick : function(callback) {
		if (!this.hasMask()) {
			return;
		}
		$('#rt_mask').unbind().bind('click', callback);
	},

	show : function() {
		if (this.hasMask()) {
			$('#rt_mask').removeClass("rt-mask-hide").removeClass("rt-mask-show").addClass("rt-mask-show");
			return;
		}
		$('body').append("<div id='rt_mask' class='rt-mask rt-mask-show'>");

	},

	hide : function() {
		if (!this.hasMask()) {
			return;
		}
		$('#rt_mask').removeClass("rt-mask-show").removeClass("rt-mask-hide").addClass("rt-mask-hide");
	}

};