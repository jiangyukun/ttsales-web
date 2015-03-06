/*
 * dependency [jquery.sas.js]
 */
;(function($) {
	var animEndEventNames = {
			'WebkitAnimation' : 'webkitAnimationEnd',
			'OAnimation' : 'oAnimationEnd',
			'msAnimation' : 'MSAnimationEnd',
			'animation' : 'animationend'
		};
	
	function createPage(target) {
		var opts = $.data(target, 'sasPage').options;
		var t = $(target);
		t.removeClass("pt-page").addClass("pt-page");
		t.data( 'originalClassList', t.attr( 'class' ) );
		t.children("[data-role='header']").addClass("sas-page-header");
		t.children("[data-role='content']").addClass("sas-page-content");
		t.children("[data-role='footer']").addClass("sas-page-footer");
		if(opts.currPage){
			$(".pt-page-current").removeClass("pt-page-current");
			t.addClass("pt-page-current");
		}else{
			$("[data-role='page']").eq( 0 ).removeClass("pt-page-current").addClass("pt-page-current");
		}
		
	}
	
	
	function getCurrPage(target){
		return $(".pt-page-current")
	}
	
	function bindEvents(target){
		var opts = $.data(target, 'sasPage').options;
	}
	
	
	function toPage(target,options){
		if(!options.toPage){
			return false;
		}
		var toPage = $(options.toPage);
		var currPage =  $(target);
		
		var toPageOpts = $.data(toPage[0], 'sasPage').options;
		var currPageOpts = $.data(target, 'sasPage').options;
		currPageOpts = $.extend(currPageOpts, options);
		toPageOpts = $.extend(toPageOpts, options);
		
		var outClass =  '';
		var inClass  =  '';
		
		if(currPageOpts.outPage){
			var c = currPageOpts.outPage.split(" ");
			$.each(c,function(index,p){
				if($.trim(p)){
					outClass += "pt-page-" +  $.trim(p) + " ";
				}
			});
		}
		if(toPageOpts.inPage){
			var c = toPageOpts.inPage.split(" ");
			$.each(c,function(index,p){
				if($.trim(p)){
					inClass += "pt-page-" + $.trim(p) +" ";
				}
			});
		}
		//if(!opts.animEndEventName){
		currPageOpts.animEndEventName = animEndEventNames[ Modernizr.prefixed( 'animation' ) ];
		//}
		
		var endCurrPage = false;
		var endNextPage = false;
		
		if(!inClass){
			endCurrPage = true;
		}
		
		if(!outClass){
			endCurrPage = true;
		}
		
		//$(currPage).addClass( outClass );
		currPage.addClass( outClass ).on( currPageOpts.animEndEventName, function() {
			currPage.off( currPageOpts.animEndEventName );
			endCurrPage = true;
			if( endNextPage ) {
				endCurrPage = false;
				endNextPage = false;
				resetPage( currPage, toPage );
			}
		} );
		toPage.addClass( 'pt-page-current' );
		toPage.addClass( inClass ).on( currPageOpts.animEndEventName, function() {
			toPage.off( currPageOpts.animEndEventName );
			endNextPage = true;
			if( endCurrPage ) {
				endCurrPage = false;
				endNextPage = false;
				resetPage( currPage, toPage );
			}
		} );

		if( !currPageOpts.support ) {
			endCurrPage = false;
			endNextPage = false;
			resetPage( currPage, toPage );
		}
		
		if(!inClass && !outClass){
			resetPage( currPage, toPage );
		}
		
	}
	
	function resetPage( $outpage, $inpage ) {
		/*$outpage.removeClass('pt-page-current');
		$inpage.addClass('pt-page-current');*/
		
		$inpage.attr( 'class', $inpage.data( 'originalClassList' ) + ' pt-page-current' );
		$outpage.attr( 'class', $outpage.data( 'originalClassList' ) );
	}
	
	function refresh(){
		
	}
	
	
	$.fn.sasPage  = function(options, param){
		if (typeof options == 'string') {
			return $.fn.sasPage.methods[options](this, param);
		}
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'sasPage');
			if (state) {
				$.extend(state.options, options,$.sas.parse.parseOptions(this));
			} else {
				$.data(this, 'sasPage', {
					options: $.extend({},$.fn.sasPage.defaults, options,$.sas.parse.parseOptions(this))
				});
			}
			createPage(this);
			bindEvents(this);
		});
	};
	
	$.fn.sasPage.methods = {
		options: function(jq){
			
		},
		onLoad:function(jq){
			return jq.each(function(){
				//getSelectedTab(this,which).trigger("onSelect");
			});
		},
		onAfterOut : function(jq){
			return jq.each(function(){
				//getSelectedTab(this,which).trigger("onSelect");
			});
		},
		onBenforeIn : function(jq){
			return jq.each(function(){
				//getSelectedTab(this,which).trigger("onSelect");
			});
		},
		toPage:function(jq,options){
			return jq.each(function(){
				//getSelectedTab(this,which).trigger("onSelect");
				toPage(this,options);
			});
		},
		refresh:function(jq){
			return jq.each(function(){
				refresh(this);
			});
		}
		
	};

	$.fn.sasPage.defaults = {
		animcursor : 1,
		animEndEventName:'animation',
		support : Modernizr.cssanimations,
		onLoad : function(panel) {
		
		},
		onAfterOut : function(e, title, index) {
			
		},
		onBenforeIn : function(e, title, index) {
			
		}
	};
})(jQuery);


$(function(){
	$("[data-role='page']").sasPage();
}); 
