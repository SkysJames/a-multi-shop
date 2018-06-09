/**
 * client的公共对象
 */
var clientCommon = {
		//系统轮播图片（例子）
		demoSliders	: [
			$contextPath + "/sky/client/component/slideShow/img/slideImg_1.jpg",
			$contextPath + "/sky/client/component/slideShow/img/slideImg_2.jpg",
			$contextPath + "/sky/client/component/slideShow/img/slideImg_3.jpg",
			$contextPath + "/sky/client/component/slideShow/img/slideImg_4.jpg",
			$contextPath + "/sky/client/component/slideShow/img/slideImg_5.jpg",
		],
		
		//系统轮播图片链接（例子）
		demoSliderHrefs	: [
			$contextPath,
			$contextPath,
			$contextPath,
			$contextPath,
			$contextPath,
		],
};

/**
 * 点击网页任意地方触发事件
 * @returns
 */
$(document).click(function(){
	//令手机端底部的二级菜单消失
    $(".ct-bottom-item-two").hide();
    //令手机端右边客服弹出框消失
    $(".ct-phone").removeClass("ct-phone-show");
});