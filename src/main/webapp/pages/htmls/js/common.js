

$(function(){

	$.stopDefault("touchstart");

	var page = {
		init: function(){
			$("#wrap").swipe(page.swipe, true);
			page.p1.init();
			// page.currentMp3 = 1;
			// page.isPlay = true;
			// $("#audio1")[0].play();
			$("#soundSwich").touchend(page.playSound);
			
			//for ios低版本不能自动播放音频需要手动触发页面的任何地方，才能播放音频
			//安卓可以自动播放
			if($.isIOS()){
				$("#wrap").one("touchstart", function(e){
					$("#audio1")[0].play();
					page.currentMp3 = 1;
					page.isPlay = true;
				});
			}else{
				page.currentMp3 = 1;
				page.isPlay = true;
				$("#audio1")[0].play();
			}
		},
		playSound: function(){
			if (!page.isPlay){
				$("#audio" + page.currentMp3)[0].play();
				$("#soundSwich").removeClass("pause");
				page.isPlay = true;
			} else {
				$("#audio" + page.currentMp3)[0].pause();
				$("#soundSwich").addClass("pause");
				page.isPlay = false;
			}
		},
		swipe: function(e, o){
			if (e == "moving" && !$.pageToing() && !page.moveLock){
				if (o.y < -20 && page.currentPage < 6){

					// if (!page.audio1IsPlay && page.isPlay){
					// 	page.audio1IsPlay = true;
					// 	page.currentMp3 = 1;
					// 	$("#audio1")[0].play();
					// }

					var _current = $("#page" + page.currentPage), _targetP = page.currentPage + 1, _target = $("#page" + _targetP);
					_target.removeClass("init").find(".show").removeClass("show");
					_target.find(".out").removeClass("out");
					page.moveLock = true;

					// 切换页面
					_current.pageTo(_target, {
						type: "move",
						callback: page["p" + _targetP].init
					});
					
				} else if (o.y > 20 && page.currentPage > 1 && page.currentPage < 5){
					var _current = $("#page" + page.currentPage), _targetP = page.currentPage - 1, _target = $("#page" + _targetP);
					_target.removeClass("init").find(".show").removeClass("show");
					_target.find(".out").removeClass("out");
					page.moveLock = true;

					// 切换页面
					_current.pageTo(_target, {
						type: "move",
						back: true,
						callback: page["p" + _targetP].init
					});
				}
			} else {
				if (page.touchDeng && Math.abs(o.x) > 40){
					page.touchDeng = false;
					var p = $("#page4");
					p.find(".box .mask").addClass("out");
					p.find(".box .hand").addClass("out");
					p.find(".txt1").addClass("out");
					p.find(".box .deng").addClass("out");

					// 翻页
					page.moveLock = true;
					var _current = $("#page" + page.currentPage), _targetP = page.currentPage + 1, _target = $("#page" + _targetP);
					_target.removeClass("init").find(".show").removeClass("show");
					_target.find(".out").removeClass("out");

					setTimeout(function(){
						_current.pageTo(_target, {
							type: "none",
							callback: page["p" + _targetP].init
						});

						// page.currentMp3 = 2;
						// $("#audio1")[0].pause();
						// $("#audio1").remove();
						// if (!page.audio2IsPlay && page.isPlay){
						// 	page.audio2IsPlay = true;
						// 	$("#audio2")[0].play();
						// }

					},1000);
					
				}
			}
		},
		p1: {
			init: function(){
				page.currentPage = 1;
				page.moveLock = true;
				var p = $("#page1");
				p.show().addClass("init");

				setTimeout(function(){
					p.find(".hand1").addClass("show");
				},500);
				setTimeout(function(){
					p.find(".txt1").addClass("show");
					setTimeout(function(){
						p.find(".txt1").addClass("out");
					},3000);
				},200);
				setTimeout(function(){
					p.find(".txt2").addClass("show");
				},4500);
				setTimeout(function(){
					p.find(".hand2").addClass("show");
				},3000);
				setTimeout(function(){
					p.find(".hand1").addClass("out");
					p.find(".hand2").addClass("out");
					p.find(".hand3").addClass("show");
				},5500);
				setTimeout(function(){
					p.find(".swipeTip").addClass("show");
					page.moveLock = false;
				},6000);
			}
		},
		p2: {
			init: function(){
				page.currentPage = 2;
				page.moveLock = true;
				var p = $("#page2");
				p.show().addClass("init");
				
				setTimeout(function(){
					p.find(".hand1").addClass("show");
				},500);
				setTimeout(function(){
					p.find(".txt1").addClass("show");
					setTimeout(function(){
						p.find(".txt1").addClass("out");
					},3000);
				},200);
				setTimeout(function(){
					p.find(".car").addClass("show");
				},4500);
				setTimeout(function(){
					p.find(".hand2").addClass("show");
				},4800);
				setTimeout(function(){
					p.find(".txt2").addClass("show");
				},4500);
				setTimeout(function(){
					p.find(".swipeTip").addClass("show");
					page.moveLock = false;
				},5300);
			}
		},
		p3: {
			init: function(){
				page.currentPage = 3;
				page.moveLock = true;
				var p = $("#page3");
				p.show().addClass("init");
				
				setTimeout(function(){
					p.find(".txt1").addClass("show");
					setTimeout(function(){
						p.find(".txt1").addClass("out");
					},3000);
				},1000);
				setTimeout(function(){
					p.find(".car").addClass("show");
				},500);
				setTimeout(function(){
					p.find(".bg").addClass("show");
				},1500);
				setTimeout(function(){
					p.find(".door1").addClass("show");
					p.find(".mask2").addClass("show");
				},5000);
				setTimeout(function(){
					p.find(".door2").addClass("show");
				},6000);
				setTimeout(function(){
					p.find(".parents").addClass("show");
				},7000);
				setTimeout(function(){
					p.find(".txt2").addClass("show");
				},6000);
				setTimeout(function(){
					p.find(".swipeTip").addClass("show");
					page.moveLock = false;
				},7500);
			}
		},
		p4: {
			init: function(){
				page.currentPage = 4;
				page.moveLock = true;
				var p = $("#page4");
				p.show().addClass("init");
				
				setTimeout(function(){
					p.find(".bg").addClass("show");
				},500);
				setTimeout(function(){
					p.find(".txt1").addClass("show");
					p.find(".box").addClass("show");
					setTimeout(function(){
						p.find(".hand").addClass("show");
						page.touchDeng = true;
					},1000);
				},3500);
			}
		},
		p5: {
			init: function(){
				page.currentPage = 5;
				page.moveLock = true;
				var p = $("#page5");
				p.show().addClass("init");
				
				setTimeout(function(){
					p.find(".pop, .pop2, .logo, .title, .txt").addClass("show");
				},100);
				setTimeout(function(){
					p.find(".swipeTip").addClass("show");
					page.moveLock = false;
				},1000);
			}
		},
		p6: {
			init: function(){
				page.currentPage = 6;
				page.moveLock = false;
				var p = $("#page6");
				p.show().addClass("init");
				$("#wrap").offSwipe();
				var scroll = new IScroll("#scroll", { mouseWheel: true, click: true});
				scroll.refresh();
			}
		}
	};
	page.init();


});







