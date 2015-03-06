

$(function(){

	$.stopDefault("touchstart");

	var page = {
		init: function(){
			page.isPlay = true;
			$("#soundSwich").touchend(page.playSound);

			page.imgarr = [
				"p1_boom_mask.png",
				"p1_boom.png",
				"p1_earth.png",
				"p1_firecracker_light.png",
				"p1_firecracker.png",
				"p1_fireline.png",
				"p1_firework.png",
				"p1_hand.png",
				"p1_house_1.png",
				"p1_house_2.png",
				"p1_incense_light.png",
				"p1_incense.png",
				"p1_sky.png",
				"p2_bg.png",
				"p2_deng.png",
				"p2_family.png",
				"p2_huapen.png",
				"p2_intv.png",
				"p2_phone.png",
				"p2_s1.png",
				"p2_s2.png",
				"p2_s3.png",
				"p2_s4.png",
				"p2_s5.png",
				"p2_s6.png",
				"p2_shafa.png",
				"p2_table.png",
				"p2_talk.png",
				"p2_talkbox.png",
				"p2_tv.png",
				"p2_watch.png",
				"p2_yinxiang.png",
				"p3_bg.png",
				"p3_dots.png",
				"p3_hand.png",
				"p3_map.png",
				"p3_pic1.jpg",
				"p3_pic2.jpg",
				"p3_pic3.jpg",
				"p3_pic4.jpg",
				"p3_pic5.jpg",
				"p3_point.png",
				"p3_quan_1.jpg",
				"p3_quan_2.jpg",
				"p3_quan_3.jpg",
				"p3_road.png",
				"p3_swipe.png",
				"p4_car.png",
				"p4_choujiang.png",
				"p4_chunlian.png",
				// "p4_gold.png",
				// "p4_hb_close.png",
				// "p4_hb_light.png",
				// "p4_hb_open.png",
				// "p4_hb_openbtn.png",
				// "p4_hb.png",
				"p4_henpi.png",
				"p4_info.png",
				"p4_infotxt1.png",
				// "p4_lalala.png",
				// "p4_poom.png",
				// "p4_small_hb.png",
				"sound.png"
			];
			page.loadimg();

			$("#page1 .clicker").touchend(page.p1.click);
			$("#page3 .point").touchend(page.p3.click);
			// $("#page4 .openbtn").touchend(page.p4.hbclick);
		},
		loadimg: function(){
			var p = $("#page0");
			p.show().addClass("init");
			page.loadi = 0;
			page.loadDom = p.find("#ld");
			page.loadnextimg();
		},
		loadnextimg: function(){
			if (page.loadi < page.imgarr.length){
				var img = new Image();
				img.src = "pages/htmls/images/spring/" + page.imgarr[page.loadi];
				page.loadDom.width(page.loadi / page.imgarr.length * 200);
				page.loadi += 1;
				img.onload = page.loadnextimg;
			} else {
				page.loadDom.width(200);
				$("#page0").pageTo($("#page1"),{
					type: "none",
					callback: page.p1.init
				});
			}
		},
		playSound: function(){
			$("#audio0")[0].pause();
			$("#audio1")[0].pause();
			$("#audio2")[0].pause();
			$("#audio3")[0].pause();
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
		playSoundBy: function(i){
			if (page.isPlay){
				$("#audio0")[0].pause();
				$("#audio1")[0].pause();
				$("#audio2")[0].pause();
				$("#audio3")[0].pause();
				$("#audio" + i)[0].play();
			}
			page.currentMp3 = i;
		},
		p1: {
			lightXArr: [2,1,0,-1,-1,-2,-2,-2,-3,-3,-3,-3,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-3,-3,-3,-2,-2,-1,-1,0,0,0,0,1,1,1,1,1,2,2,2,2,2,2,2,1,1,1,1,1,1,1,0,0,0,0,-1,-2,-3,-3,-3,-3,-4,-4,-4,-4],
			init: function(){
				$("#soundSwich").show();
				var p = $("#page1");
				p.show().addClass("init");
				page.p1.$fc = p.find(".firecracker");
				page.p1.fcTop = 0;
				page.playSoundBy(3);

				setTimeout(function(){
					p.find(".house1").addClass("show");
					setTimeout(function(){
						p.find(".house2").addClass("show");
					},100);
				},300);
				setTimeout(function(){
					p.find(".firecracker").addClass("show");
				},600);
				setTimeout(function(){
					p.find(".incense, .clicker").addClass("show");
					setTimeout(function(){
						p.find(".hand").addClass("show");
					},500);
				},800);
			},
			click: function(){
				var p = $("#page1");
				p.find(".incense").addClass("use");
				p.find(".hand").removeClass("show");
				setTimeout(function(){
					p.find(".incense").removeClass("use").addClass("out");
					p.find(".firecracker sub").addClass("show");
					page.playSoundBy(0);
					setTimeout(function(){
						page.p1.lightRun();
					},500);
				},1200);
			},
			lightRun: function(){
				page.p1.$fc.find("sub").css({"top": page.p1.fcTop + "px", "left": page.p1.lightXArr[page.p1.fcTop] + "px"});
				page.p1.$fc.find("span").css({"height": (70 - page.p1.fcTop) + "px"});
				page.p1.fcTop += 1;
				if (page.p1.fcTop < page.p1.lightXArr.length){
					setTimeout(page.p1.lightRun, 30);
				} else {
					page.p1.boom();
				}
			},
			boom: function(){
				var p = $("#page1");
				p.find(".boom").addClass("show");
				p.find(".firework").addClass("show");
				page.playSoundBy(1);
				setTimeout(function(){
					p.find(".boom2").addClass("show");
					setTimeout(function(){
						p.find(".sky").addClass("show");
						setTimeout(page.p1.out, 2500);
					},500);
				},70);
			},
			out: function(){
				$("#page1").pageTo($("#page2"),{
					type: "move",
					callback: page.p2.init
				});
			}
		},
		p2: {
			init: function(){
				page.playSoundBy(2);

				var p = $("#page2");
				p.show().addClass("init");
				setTimeout(function(){
					p.find(".shafa").addClass("show");
				},200);
				setTimeout(function(){
					p.find(".table").addClass("show");
				},300);
				setTimeout(function(){
					p.find(".tv").addClass("show");
					setTimeout(function(){
						p.find(".deng").addClass("show");
					},100);
					setTimeout(function(){
						p.find(".watch").addClass("show");
					},200);
					setTimeout(function(){
						p.find(".phone").addClass("show");
					},150);
					setTimeout(function(){
						p.find(".huapen").addClass("show");
					},250);
				},600);
				setTimeout(function(){
					p.find(".yinxiang").addClass("show");
				},900);
				setTimeout(function(){
					p.find(".zhijia.l .line").addClass("show");
					setTimeout(function(){
						p.find(".zhijia.l .s1").addClass("show");
					},100);
					setTimeout(function(){
						p.find(".zhijia.l .s2").addClass("show");
					},200);
					setTimeout(function(){
						p.find(".zhijia.l .s3").addClass("show");
					},150);
				},1100);
				setTimeout(function(){
					p.find(".zhijia.r .line").addClass("show");
					setTimeout(function(){
						p.find(".zhijia.r .s1").addClass("show");
					},100);
					setTimeout(function(){
						p.find(".zhijia.r .s2").addClass("show");
					},200);
					setTimeout(function(){
						p.find(".zhijia.r .s3").addClass("show");
					},150);
				},1300);
				setTimeout(function(){
					p.find(".family").addClass("show");
					setTimeout(function(){
						p.find(".tv span").addClass("show");
						p.find(".talk, .talkbox").addClass("show");
						setTimeout(page.p2.out,2500);
					},200);
				},1500);
			},
			out: function(){
				var w = $(window).width();
				page.p3.init();
				$("#page2").addClass("out");
				setTimeout(function(){
					$("#page2").hide();
				},1000);
			}
		},
		p3: {
			init: function(){
				var p = $("#page3");
				p.show().addClass("init");
				setTimeout(function(){
					p.find(".quan.q1").addClass("out");
				},100);
				setTimeout(function(){
					p.find(".quan.q2").addClass("out");
				},300);
				setTimeout(function(){
					p.find(".quan.q3").addClass("out");
				},500);
				setTimeout(function(){
					p.find(".quan.q4").addClass("out");
				},700);
				setTimeout(function(){
					p.find(".map, .dots").addClass("showAll");
				},500);
				setTimeout(function(){
					p.find(".point, .hand").addClass("show");
				},1200);
				page.p3.picMax = 5;
				page.p3.picCurrent = 1;
			},
			click: function(){
				var p = $("#page3");
				p.find(".dots").addClass("hide");
				p.find(".point, .hand").addClass("out");
				p.find(".map").addClass("showMoto");
				setTimeout(function(){
					p.find(".road").addClass("show");
					setTimeout(function(){
						p.find(".motoPop").addClass("show");
						setTimeout(function(){
							p.find(".motoPop .txt").addClass("show");
							setTimeout(function(){
								p.find(".motoPop .swipeTip").addClass("show");
								p.find(".motoPop").swipe(page.p3.swipe, true);
							},500);
						},200);
					},1000);
				},700);
			},
			picFlip: function(){
				if (!page.p3.lock){
					page.p3.lock = true;
					$("#_motoPic" + page.p3.picCurrent).addClass("flip");
					setTimeout(function(){
						$(".motoPop .pics .flip").attr("class", "pic");
						page.p3.lock = false;
					},700);
					var p2, p3, p4, p5;
					p2 = (page.p3.picCurrent + 1) % page.p3.picMax;
					if (p2 == 0){
						p2 = page.p3.picMax;
					}
					p3 = (page.p3.picCurrent + 2) % page.p3.picMax;
					if (p3 == 0){
						p3 = page.p3.picMax;
					}
					p4 = (page.p3.picCurrent + 3) % page.p3.picMax;
					if (p4 == 0){
						p4 = page.p3.picMax;
					}
					p5 = (page.p3.picCurrent + 4) % page.p3.picMax;
					if (p5 == 0){
						p5 = page.p3.picMax;
					}
					$("#_motoPic" + p2).attr("class", "pic p1");
					$("#_motoPic" + p3).attr("class", "pic p2");
					$("#_motoPic" + p4).attr("class", "pic p3");
					$("#_motoPic" + p5).attr("class", "pic p4");
					page.p3.picCurrent += 1;
					if (page.p3.picCurrent > page.p3.picMax){
						page.p3.picCurrent = 1;
					}
					$("#page3").find(".motoPop .txt .c").removeClass("c");
					$("#page3").find(".motoPop .txt .t" + page.p3.picCurrent).addClass("c");
				}
			},
			swipe: function(e, o){
				if (e == "moving" && o.y < -50 && !page.p3.lock){
					if (page.p3.picCurrent == page.p3.picMax){
						$("#page3 .motoPop").offSwipe();
						page.p3.out();
					} else {
						page.p3.picFlip();
					}
				}
			},
			out: function(){
				$("#page3").pageTo($("#page4"),{
					type: "move",
					callback: page.p4.init
				});
			}
		},
		p4: {
			init: function(){
				var p = $("#page4");
				p.show().addClass("init");
				var scroll = new IScroll("#scroll", { mouseWheel: true, click: true});
				scroll.refresh();
				p.find(".car").addClass("show");
				setTimeout(function(){
					p.find(".chunlian").addClass("show");
					setTimeout(function(){
						p.find(".henpi").addClass("show");
					},500);
				},300);
				// setTimeout(function(){
				// 	p.find(".hbpop").addClass("show");
				// 	setTimeout(function(){
				// 		p.find(".hbpop .bd").addClass("show");
				// 		p.find(".hbpop .openbtn").addClass("show");
				// 	},500);
				// },2000);
			},
			hbclick: function(){
				// var p = $("#page4");
				// p.find(".hbpop .openbtn").addClass("out");
				// p.find(".hbpop .close").addClass("out");
				// p.find(".hbpop .open, .hbpop .gold").addClass("show");
				// setTimeout(function(){
				// 	p.find(".hbpop .light").addClass("show");
				// 	setTimeout(function(){
				// 		p.find(".hbpop .poom").addClass("show");
				// 		$("#hbpop").on("touchend", page.p4.closepop);
				// 		p.find(".pagehb").touchend(page.p4.openhb);
				// 	},200);
				// },300);
			},
			closepop: function(){
				// var p = $("#page4");
				// p.find(".pagehb").addClass("show");
				// p.find(".hbpop").removeClass("show");
				// p.find(".hbpop .bd").removeClass("show");
			},
			openhb: function(){
				// var p = $("#page4");
				// p.find(".pagehb").removeClass("show");
				// p.find(".hbpop").addClass("show");
				// p.find(".hbpop .bd").addClass("show");
			}
		}
	};
	page.init();


});







