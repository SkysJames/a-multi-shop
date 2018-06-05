/**
 * server的公共对象
 */
var serverCommon = {
		
		/**
		 * 初始化并返回kindeditor编辑器
		 */
		initKindEditor: function(target){
			return KindEditor.create(target, {
				width		: "100%",
				resizeType	: 1,		//只允许调整高度
				filterMode	: true, //默认过滤HTML代码
				allowPreviewEmoticons : true,	//预览表情
				allowImageUpload	: true,	//允许上传图片
				uploadJson	: $contextPath + "/common/file!uploadKindEditorFile",	//图片的上传地址
				afterUpload	: function(){this.sync();},	//图片上传后，将上传的内容同步到textarea
				afterBlur	: function(){this.sync();},	//失去焦点时，将上传的内容同步到textarea
				items : [
					"source", "|", "undo", "redo", "|", "preview", "template", "code", "cut", "copy", "paste", "plainpaste", "wordpaste",
					"|", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist","insertunorderedlist", "indent", 
					"outdent", "subscript", "superscript", "clearhtml","quickformat", "selectall", "|", "fullscreen", "/", "formatblock", 
					"fontname", "fontsize", "|","forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "lineheight",
					"removeformat", "|", "image", "table", "hr","emoticons", "baidumap", "pagebreak", "anchor", "link", "unlink", "about"
					]
			});
		},
		
		/**
		 * 是否为管理员权限，即有某某管理权限，所属店铺为系统类型
		 */
		isAdminRight	: function(right){
			if($currentUser && $currentUser.allRights && right
					&& $currentUser.allRights.indexOf(right)>-1
					&& $currentUser.shopId==common.shopContants.shopSystem){
				return true;
			}
			return false;
		},
		
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
		 * 初始化树形结构展示
		 */
		initTreeView: function(){
			$('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
			$('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', '收缩').on('click', function(e) {
				var children = $(this).parent('li.parent_li').find(' > ul > li');
				if (children.is(':visible')) {
					children.hide('fast');
					$(this).attr('title', '展开').find(' > i').removeClass().addClass('fa fa-lg fa-plus-circle');
				} else {
					children.show('fast');
					$(this).attr('title', '收缩').find(' > i').removeClass().addClass('fa fa-lg fa-minus-circle');
				}
				e.stopPropagation();
			});
		},
		
};
