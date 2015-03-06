jQuery.rt = jQuery.rt || {};
jQuery.rt.common = {
	getRootPath:function(){
		return $.rt.constants.rootPath;
	},
	getPageContainer:function(){
		return $.rt.common.getRootPath() + $.rt.constants.pageContainer;
	},
	go : function(pageOpts){
		if(!pageOpts || !pageOpts.url){return;}
		if((pageOpts.url).indexOf("#") > -1){//本页面 ID跳转
			if(pageOpts.inAnimation){
				$(pageOpts.url).removeClass(pageOpts.inAnimation).removeClass('rt-animation-current-page').addClass(pageOpts.inAnimation +" "+'rt-animation-current-page');
			}else{
				$(pageOpts.url).removeClass('rt-animation-current-page').addClass('rt-animation-current-page');
			}
			history.rtStack.go(pageOpts);
		}else{
			alert("页面ID不正确");
		}
		/*if(pageOpts.url.indexOf(".") > -1){//新页面
			
		}*/
	},
	goBack : function(){
		history.go(-1);
		/*var pageOpts = history.rtStack.goBack();
		if(pageOpts.outAnimation){
			$(pageOpts.url).addClass(options.outAnimation);
		}
		if(pageOpts.inAnimation){
			$(pageOpts.url).removeClass(options.inAnimation);
		}
		$(pageOpts.url).removeClass('rt-animation-current-page');*/
	},
	to : function(pageOpts){
		var lastPageOpts = history.rtStack.getLast();
		if(lastPageOpts.outAnimation){
			$(lastPageOpts.url).removeClass(pageOpts.outAnimation).removeClass('rt-animation-current-page').addClass(pageOpts.outAnimation+" "+ 'rt-animation-current-page');
		}
		if(lastPageOpts.inAnimation){
			$(lastPageOpts.url).removeClass(lastPageOpts.inAnimation);
		}
		//$(pageOpts.url).removeClass('rt-animation-current-page');
		var outPages = history.rtStack.to(pageOpts);
		for(var i=0; i < outPages.length -1; i++){
			if(outPages[i].url.outAnimation){
				$(outPages[i].url).removeClass(outPages[i].outAnimation);//.addClass(options.outAnimation);
			}
			if(outPages[i].url.inAnimation){
				$(outPages[i].url).removeClass(outPages[i].inAnimation);
			}
			$(outPages[i].url).removeClass('rt-animation-current-page');
		}
		
	},
	pop : function(pageOpts){
		if(pageOpts.inAnimation){
			$(pageOpts.url).removeClass(pageOpts.inAnimation);
		}
		if(pageOpts.outAnimation){
			$(pageOpts.url).removeClass(pageOpts.outAnimation).removeClass(
					'rt-animation-current-page')
					.addClass(pageOpts.outAnimation).addClass(
							'rt-animation-current-page');
		}
		//$(pageOpts.url).removeClass('rt-animation-current-page');
	}/*,
	toPage : function(targetPage,options){
		if(targetPage.indexOf(".") > -1){//新页面
			alert(targetPage);
			//window.location.href = "http://192.168.137.40:8089/RT-UI/demo/"+targetPage;
			//alert(window.location.href);
			if(options.ajax){
				
			}else{
				
			}
			return;
		}
		var t = $(targetPage);
		if(t){
			if(!options){
				options = {};
			}
			options.toPage = t;
			 $(".rt-page-current").rtPage("toPage",options);
			return t;
		}
	}*/
};