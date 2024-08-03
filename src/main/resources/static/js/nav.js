var ww = document.body.clientWidth; // 獲取當前視窗的寬度，並將其存儲在變量 ww 中

$(document).ready(function() { // 當文檔準備就緒時執行以下函數
	$(".nav li a").each(function() { // 遍歷每個導航欄中的 <a> 標籤
		if ($(this).next().length > 0) { // 如果 <a> 標籤後面有其他元素
			$(this).addClass("parent"); // 給這個 <a> 標籤添加 "parent" 類
		};
	})

	$(".toggleMenu").click(function(e) { // 當 .toggleMenu 被點擊時執行以下函數
		e.preventDefault(); // 阻止默認的點擊行為
		$(this).toggleClass("active"); // 切換 .toggleMenu 的 "active" 類
		$(".nav").toggle(); // 切換導航欄的顯示/隱藏狀態
	});
	adjustMenu(); // 調整菜單的顯示方式
})

$(window).bind('resize orientationchange', function() { // 當視窗大小改變或設備方向改變時執行以下函數
	ww = document.body.clientWidth; // 更新視窗的寬度
	adjustMenu(); // 調整菜單的顯示方式
});

var adjustMenu = function() {
	if (ww < 768) {
		$(".toggleMenu").css("display", "inline-block");
		if (!$(".toggleMenu").hasClass("active")) {
			$(".nav").hide();
		} else {
			$(".nav").show();
		}
		$(".nav li").unbind('mouseenter mouseleave');
		$(".nav li a.parent").unbind('click').bind('click', function(e) {
			// must be attached to anchor element to prevent bubbling
			e.preventDefault();
			$(this).parent("li").toggleClass("hover");
		});
	} 
	else if (ww >= 768) {
		$(".toggleMenu").css("display", "none");
		$(".nav").show();
		$(".nav li").removeClass("hover");
		$(".nav li a").unbind('click');
		$(".nav li").unbind('mouseenter mouseleave').bind('mouseenter mouseleave', function() {
		 	// must be attached to li so that mouseleave is not triggered when hover over submenu
		 	$(this).toggleClass('hover');
		});
	}
}

