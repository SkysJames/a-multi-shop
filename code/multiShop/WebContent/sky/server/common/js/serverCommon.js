/**
 * server的公共对象
 */
var serverCommon = {
		
		/**
		 * 判断当前用户是否有权限待在当前页面
		 */
		hasRightStay	: function(right){
			if(!$currentUser || !right || $currentUser.allRights.indexOf(right) < 0){
				common.triggerFailMesg("该用户无此权限模块");
				//返回首页
				serverCommon.returnIndexPage();
			}
		},
		
		/**
		 * 返回首页
		 */
		returnIndexPage	: function(){
			window.location.href = $contextPath + "/home/server-index";
		},
		
		/**
		 * 根据分好类的权限列表，获取其中勾选了的权限ID，以,隔开
		 */
		getRightsByTypeRightList : function(typeRightList){
			var rights = "";
			if(typeRightList){
				for(var i in typeRightList){
					for(var j in typeRightList[i].rightList){
						var right = typeRightList[i].rightList[j];
						if(right.checked){
							rights += right.id + ",";
						}
					}
				}
			}
			
			if(rights && rights.length>0){
				rights = rights.substring(0, rights.length-1);
			}
			
			return rights;
		},
		
		/**
		 * 根据权限字符串（以,分隔）初始typeRightList的权限勾选情况
		 */
		checkedTypeRightList : function(typeRightList, rights){
			for(var i in typeRightList){
				for(var j in typeRightList[i].rightList){
					var right = typeRightList[i].rightList[j];
					if(rights && rights.indexOf(right.id)>-1){
						right.checked = true;
					}else{
						right.checked = false;
					}
				}
			}
			return typeRightList;
		},
		
		/**
		 * 当前导航指向改变
		 */
		navChange : function(url){
			// remove all active class
			$('nav li.active').removeClass("active");
			// match the url and add the active class
			$('nav li:has(a[href="' + url + '"])').addClass("active");
			//导航标题
			drawBreadCrumb();
		},
		
		/**
		 * 将滚动条滚动到指定位置，如 #id
		 */
		scrollTo : function(target){
			$("html, body").animate({scrollTop: $(target).offset().top}, {duration: 500,easing: "swing"});
		},
		
};
