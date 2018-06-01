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
};

/**
 * 点击网页任意地方触发事件
 * @returns
 */
$(document).click(function(){
	//令手机端底部的二级菜单消失
    $(".ct-bottom-item-two").hide();
});