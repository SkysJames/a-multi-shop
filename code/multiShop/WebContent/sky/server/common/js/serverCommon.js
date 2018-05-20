/**
 * server的公共对象
 */
var serverCommon = {
		/**
		 * 用户类型常量
		 */
		userContants		: {
			userStatus	: {
				UNUSING	: 0,//状态不可用
				USING	: 1,//状态可用
			},
			loginStatus	: {
				OFFLINE	: 0,//状态离线
				ONLINE	: 1,//状态上线
			},
		},
		
		/**
		 * 访客类型常量
		 */
		visitorContants	: {
			status	: {
				UNUSING	: 0,//状态不可用
				USING	: 1,//状态可用
			},
		},
		
		/**
		 * 产品类型常量
		 */
		productContants	: {
			proType	: {
				DEFAULT		: 0,//默认类型，网页设计
				E1_ERP		: 1,//管理系统
				E2_COURSE	: 2,//课程设计
				E3_WECHAT	: 3,//微信小程序
			},
		},
		
		/**
		 * 面板类型常量
		 */
		panelContants : {
			INDEX_PANEL	: "INDEX_PANEL",//主面板
			SAVE_PANEL	: "SAVE_PANEL",//添加面板
			EDIT_PANEL	: "EDIT_PANEL",//编辑面板
			DEPARTMENT_INDEX		: "DEPARTMENT_INDEX",//部门主面板
			DEPARTMENT_SAVE		: "DEPARTMENT_SAVE",//部门添加面板
			DEPARTMENT_EDIT		: "DEPARTMENT_EDIT",//部门编辑面板
			RIGHT_GROUP_INDEX	: "RIGHT_GROUP_INDEX",//角色主面板
			RIGHT_GROUP_SAVE		: "RIGHT_GROUP_SAVE",//角色添加面板
			RIGHT_GROUP_EDIT		: "RIGHT_GROUP_EDIT",//角色编辑面板
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
		},
		
		/**
		 * 将滚动条滚动到指定位置，如 #id
		 */
		scrollTo : function(target){
			$("html, body").animate({scrollTop: $(target).offset().top}, {duration: 500,easing: "swing"});
		},
		
};
