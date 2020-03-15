$(function() {
	//index  main js
	var w = $(window).width();
	var h = $(window).height();
	var phone_H = w / 1.78;

	$(window).resize(function () {
		w = $(window).width();
		h = $(window).height();
		$("#wrap_banner").width(w);
	});
	
	//首页背景随机换图
	// var random = Math.ceil(Math.random() * 10);
	// var bgSrc = "images/bgindex/bg_index";
	// var bgArr = [bgSrc+"1.jpg",bgSrc+"1.jpg",bgSrc+"2.jpg",bgSrc+"3.jpg",bgSrc+"4.jpg",bgSrc+"5.jpg",bgSrc+"6.jpg",bgSrc+"7.jpg",bgSrc+"8.jpg",bgSrc+"9.jpg",bgSrc+"10.jpg"];
	// var radomSrc = bgArr[random];
	
	
	$("#wrap_banner").css({
		//"background-image": "url(" +radomSrc+ ")",
		"background-size": "100% 100%",
		"background-position": "top center",
		"background-repeat": "no-repeat"
	});

	if (w > 800) {
		$("#wrap_banner").css("height",h+ 'px');
		$("#wrap_banner").width(w);
		$(".subcon").panel({iWheelStep:32});
	} else {
		$("body").attr("ontouchstart");
		//document.body.addEventListener('touchstart',function(){});
		$("#wrap_banner").css("height",phone_H+ 'px');
		$("#information .info_item:gt(2)").css("display","none");
		$("#news ul li:gt(0)").css("display","none");
		$("#hot_link ul li:eq(0) .hot_item:gt(1)").css("display","none");
		$("#hot_link ul li:eq(1) .hot_item:gt(1)").css("display","none");
		var startX,startY;
		$("#right_nav").on("touchstart", function(e) {
			// 判断默认行为是否可以被禁用
			// if (e.cancelable) {
			// 	// 判断默认行为是否已经被禁用
			// 	if (!e.defaultPrevented) {
			// 		e.preventDefault();
			// 	}
			// }   
			startX = e.originalEvent.changedTouches[0].pageX,
			startY = e.originalEvent.changedTouches[0].pageY;
		});
		$("#right_nav").on("touchend", function(e) {                       
			moveEndX = e.originalEvent.changedTouches[0].pageX,
			moveEndY = e.originalEvent.changedTouches[0].pageY,
			X = moveEndX - startX,
			Y = moveEndY - startY;
			//左滑
			if ( X > 20 ) {
				closeMark ();             
			}
		});
		$(".right_pos").on("click",function (e) {
			e.stopPropagation();
			$("html,body").css({
				"width": "100%",
				"height": "100%",
				"overflow": "hidden"
			});
			$("#right_mark").css("display","block");
			var _idx = $(".right_pos").index(this);
			$("#right_nav").stop().animate({
				"right": '0'
			});
			$("#right_nav .subcon").eq(_idx).addClass("block");
			$("#right_nav .subcon").eq(_idx).siblings().removeClass("block");
		});
		
	};
		
	$("#right_nav").css("height",h+ 'px');
	var data_box = $("#data_box").offset().top + 10;
	$("#follow .bg_phone").css("bottom", "-1000px");
	var data_box_t = $("#data_box").outerHeight() + 120;
	var flag = true;
	//首页监听滚动事件
	$(window).scroll(function() {
		var win_top = $(window).scrollTop();
		var scroll_top = $("#follow .bg_phone").offset().top;
		var _dis = scroll_top - win_top - h;
		if (win_top > 0) {
			$("#wrap_arr").css("display","none");
		} else {
			$("#wrap_arr").css("display","block");
		};
		if (win_top > data_box_t) {
			if (flag) {
				$('.timer').each(count);
				flag = false;
			}
		};
		if(_dis <= 600) {
			$("#follow .bg_phone").animate({
				"bottom": "-4px"
			}, 1500);
		};
		if(win_top >= 20) {
			$("#return").fadeIn(500);
			$("#wrap_nav").css("display","block");
		} else {
			$("#return").fadeOut(200);
			$("#wrap_nav").css("display","none");
		};
		if(win_top >= data_box && w > 800) {
			$("#data_box").css({
				"position": "fixed",
				"top": "-90px"
			});
			$("#hot_link").css({
				"margin-top": data_box_t + "px"
			});
            //$("#wrap_nav").css("display","none");
		} else {
			$("#data_box").css({
				"position": "relative",
				"top": "inherit"
			});
			$("#hot_link").css({
				"margin-top": "0px"
			});
            //$("#wrap_nav").css("display","block");
		};
		if(win_top >= data_box - 200 && win_top <= data_box + 500 || win_top == 0) {
			$("#wrap_nav").css("display","none");
		} else {
			$("#wrap_nav").css("display","block");
		}
		
	});
	
	$("#right_nav .subcon").each(function (i) {
		var _self = ".subcon" + i;
		$(this).addClass("subcon" + i);
	});

	
	
	//首页右侧菜单缩放效果
	// 记录当前展开的导航栏 tab的index
	var _TabIndex = 0;
	var rightNav_w = $("#right_nav").width();
	$("#right_nav").css("right",-rightNav_w + "px");
	$(".menu_tab .tab").on("click",function (e) {
		e.stopPropagation();
		var _index = $(this).index();
		_TabIndex = _index;
		$("html,body").css({
			"width": "100%",
			"height": "100%",
			"overflow": "hidden"
		});
		$("#right_mark").css("display","block");
		$("#right_nav").stop().animate({
			"right": '0'
		}).css("z-index","999");
		$("#right_nav .subcon").eq(_index).addClass("block").siblings().removeClass("block");
		$('.right_item').eq(_index).animate({
			"right": '0'
		});
	});
	
	
	var rightTab_T = $("#right_nav .right_tab").position().top;
	var rightTab_H = $("#right_nav .right_tab").height();
	var return_T = rightTab_T + rightTab_H + 20;
	$("#return").css({
		"top": return_T + 'px'
	});
	var toright_W = $("#right_nav .toright").width() + 4;
	var rightCon_W = $("#right_nav .right_con").width();
	$(".right_pos").mouseenter(function (e) {
		e.stopPropagation();
		$(this).parent('.right_item').stop().animate({
			"right": '0'
		});
		$(this).parent('.right_item').siblings().stop().animate({
			"right": -toright_W + 'px'
		});
	});
	$(".toright").on("click",function (e) {
		e.stopPropagation();
		$("html,body").css({
			"width": "100%",
			"height": "100%",
			"overflow": "hidden"
		});
		$("#right_mark").css("display","block");
		var _idx = $(".toright").index(this);
		$("#right_nav").stop().animate({
			"right": '0'
		});
		$("#right_nav .subcon").eq(_idx).addClass("block");
		$("#right_nav .subcon").eq(_idx).siblings().removeClass("block");
		$("#return").css({
			"top": return_T + 'px',
			"right": rightCon_W + 10 + 'px'
		});
	});
	
	$("#right_mark, .btn_closemark").on("click",function (e) {
		e.stopPropagation();
		closeMark ();
	});
	function closeMark () {
		$("html,body").css({
			"width": "100%",
			"height": "auto",
			"overflow": "auto"
		});
		$("#right_nav").stop().animate({
			"right": -rightNav_w + 'px'
		});
		$("#right_mark").css("display","none");
		$(".right_item").stop().animate({
			"right": -toright_W + 'px'
		});
		$("#return").css({
			"top": return_T + 'px',
			"right": '10px'
		});
	};
	$("#right_nav").mouseleave(function () {
		var _display = $("#right_mark").css("display");
		if (_display == "none") {
			$(".right_item").stop().animate({
				"right": -toright_W + 'px'
			});
		}
	});
	//定义数组存放点击的索引值去改变背景图
	var imgArr = [];
	$(".student_nav li p.tosubnav").on("click",function () {
		var _this  = $(this);
		var _index = $(".student_nav li p.tosubnav").index(this) + 1;
		var iconSrc = "web/"; //web/
		imgArr.push(_index);
		var second = imgArr[imgArr.length - 2];
		var last = imgArr[imgArr.length - 1];
		if (_this.hasClass("subnav_on")) {
			_this.removeClass("subnav_on");
			_this.siblings(".sub_stunav").slideUp();
			$(this).find("img").stop().attr("src",iconSrc + "images/icon/icon" + _index + ".png");
			
		} else {
			_this.addClass("subnav_on");
			_this.siblings(".sub_stunav").slideDown();
			_this.parents("li").siblings().find(".sub_stunav").slideUp();
			_this.parents("li").siblings().find(".tosubnav").removeClass("subnav_on");
			$(this).find("img").stop().attr("src", iconSrc + "images/icon/icon" + _index + "_on.png");
			if (second != undefined && second == last) {
				$(".student_nav ul li").eq(second).find("img").attr("src", iconSrc + "images/icon/icon" + second + ".png");
			}
			if (second != undefined && second != last) {
				$(".student_nav ul li").eq(second - 1).find("img").attr("src", iconSrc + "images/icon/icon" + second + ".png");
			}
		}
	});

	// 搜索点击事件
	// document.getElementById('searchForm').onsubmit = function() {
	// 	setScroll(".subcon" + _TabIndex);				
	// 	return false;
	// }
	
	//限定搜索显示字数
	$(".link_wrap p").each(function () {
		var _txt = $(this).text();
		$(this).text(cutstr(_txt,100));
	});
	
	$(".info_right p,#news li span").hover(function () {
		var _txt = $(this).text();
		$(this).attr("title",_txt);
	});
	//热点链接鼠标移上去效果
//	var aLink = new sHover("sHoverItem", "sIntro");
//	aLink.set({
//		slideSpeed: 5,
//		opacityChange: true,
//		opacity: 80
//	});
	
	//热点链接轮播效果
	var i = 0;
	var $mainImage = $(".main_image ul");
	var clone = $(".main_image ul li").first().clone();
	$mainImage.append(clone);

	var liLength = $(".main_image ul li").length;
	var liW = $(".main_image ul li").first().width();
	$mainImage.width(liLength * liW);

	for(var j = 0; j < liLength - 1; j++) {
		$(".flicking_con").append("<span></span>");
	};

	$(".flicking_con span").first().addClass("active");

	var timer = setInterval(toLeft, 3000);

	$(".main_visual").hover(function() {
		clearInterval(timer);
	}, function() {
		timer = setInterval(toLeft, 3000);
	});

	$(".flicking_con span").on("click", function() {
		var _index = $(this).index();
		$mainImage.stop().animate({
			left: -liW * _index
		}, 500);
		$(this).addClass('active').siblings().removeClass('active');
	});

	function toLeft() {
		i++;
		if(i == liLength) {
			$mainImage.css({
				left: 0
			});
			i = 1;
		}
		$mainImage.stop().animate({
			left: -liW * i
		}, 500);
		$('.flicking_con span').eq(0).addClass('active').siblings().removeClass('active');
		$('.flicking_con span').eq(i).addClass('active').siblings().removeClass('active');
	};

	function toRight() {
		i--;
		if(i == -1) {
			$mainImage.css({
				left: -(liLength - 1) * 500
			});
			i = liLength - 2;
		}
		$mainImage.stop().animate({
			left: -i * 500
		}, 500);
		$('.flicking_con span').eq(i).addClass('active').siblings().removeClass('active');
	};

	//首页搜索按钮点击弹出搜索页面
//	var searchVal = $("#search_txt").val();
//	$("#search_btn").on("click", function(e) {
//		e.stopPropagation();
//		searchVal = $("#search_txt").val();
//		$("#wrap_search,#wrap_nav,#right_nav").css("display", "none");
//		$("html,body").css({
//			"width": "100%",
//			"height": "100%",
//			"overflow-y": "hidden"
//		});
//		$("#form_mark").css({
//			"display": "block",
//			"width": '100%',
//			"height": '100%'
//		});
//		$("#mark_serText").val(searchVal);
//		if (searchVal.length != 0) {
//			$(".result_list").css("display","block");    //有搜索结果显示$(".result_list")
//			$(".noresult").css("display","none");        //无所搜结果显示$(".noresult")
//		} else { 
//			$(".result_list").css("display","none");	 //无所搜结果显示$(".noresult")
//			$(".noresult").css("display","block");       //有搜索结果显示$(".result_list")
//		};
//	});
	//点击关闭搜索显示的弹层
//	$(".close_form").on("click",function (e) {
//		e.stopPropagation();
//		$("html,body").css({
//			"width": "100%",
//			"height": "auto",
//			"overflow-y": "auto"
//		});
//		$("#form_mark").css("display","none");
//		$("#wrap_search,#wrap_nav,#right_nav").css("display", "block");
//	});
//	$("#form_mark .result_list .result_item:odd").css("float", "right");
//	$("#form_mark .result_item p").each(function () {
//		var _text = $(this).text();
//		$(this).text(cutstr(_text,60));
//	});
	
	
	//限定字数
	function cutstr(str, len) {
		var str_length = 0;
		var str_len = 0;
		str_cut = new String();
		str_len = str.length;
		for(var i = 0; i < str_len; i++) {
			a = str.charAt(i);
			str_length++;
			if(escape(a).length > 4) {
				str_length++;
			}
			str_cut = str_cut.concat(a);
			if(str_length >= len) {
				str_cut = str_cut.concat("…");
				return str_cut;
			}
		}
		//如果给定字符串小于指定长度，则返回源字符串；
		if(str_length < len) {
			return str;
		}
	};
	
	//返回顶部
	$("#link_return").on("click", function() {
		$("html,body").animate({
			scrollTop: 0
		}, 500);
	});
	
})