jQuery.rt.parse = {
	parseOptions: function(target,opts){
		var t = $(target);
		var options = {};
	
		var s = t.attr('data-options');
		if (s){
			if (s.substring(0, 1) != '{'){
				s = '{' + s + '}';
			}
			options = (new Function('return ' + s))();
		}
		
		if(opts){
			if (typeof options == 'string'){
				opts = eval(opts);
			}
			options = $.extend(options, eval(opts));
		}
	
		return options;
	}
};