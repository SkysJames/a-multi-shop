/**
 * 公共对象
 */
var common = {
		
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
		 * 角色常量
		 */
		rightGroupContants	: {
			adminRightgroup			: "admin_rightgroup",//管理员角色
			shopkeeperRightgroup		: "shopkeeper_rightgroup",//店铺店长角色
			salesclerkRightgroup		: "salesclerk_rightgroup",//店铺店员角色
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
		 * 消息常量
		 */
		messageContants		: {
			status	: {
				NOSEND		: "0",//未发送
				NORECEIVE	: "1",//已发送未接收
				RECEIVED		: "2",//已接收
			}
		},
		
		/**
		 * 表名常量
		 */
		tableContants	: {
			TB_SHOP		: "tb_shop",//店铺表名
			TB_PRODUCT	: "tb_product",//商品表名
		},
		
		/**
		 * 公告常量
		 */
		announceContants	: {
			status	: {
				UNUSING	: "0",//状态不可用
				USING	: "1",//状态可用
			},
		},
		
		/**
		 * 类型常量
		 */
		typetContants		: {
			rootParentId		: "-1",//类型为根类型
		},
		
		/**
		 * 店铺类型常量
		 */
		shopContants		: {
			shopSystem	: "system",//系统
			shopBbs		: "bbs",//论坛
			status	: {
				INEXIST		: "-1",//不存在的店铺
				UNUSING		: "0",//禁用
				NOAPPEOVE	: "1",//申请待审批
				USING		: "2",//启用
			}
		},
		
		/**
		 * 产品类型常量
		 */
		productContants	: {
			status	: {
				UNUSING		: "0",//禁用
				OFFTABLE		: "1",//下架
				ONTABLE		: "2",//上架
			}
		},
		
		/**
		 * 公用的消息提示音
		 */
		notifySound : function(){
			var audioElement = document.createElement('audio');
			var mp3path = $contextPath + '/resource/sound/newMsg.mp3';
	        audioElement.setAttribute('src', mp3path);
	        $.get();
	        audioElement.addEventListener("load", function () {
	            audioElement.play();
	        }, true);
	        audioElement.pause();
	        audioElement.play();
		},
		
		/**
		 * 公用的消息提示
		 */
		notifyMsg : function(status, title, content, negligible, sound) {
			var notifySound = this.notifySound;
			title = title || '提示';
			content = content || '';
			
			if ("Notification" in window) {
				var iconUrl = $contextPath + '/sky/common/core/img/';
				var waitCloseTime = 5000; // 自动关闭时间
				
				if (status) {
					iconUrl += 'notify_1.png';
				} else {
					iconUrl += 'notify_2.png';
					waitCloseTime = 8000;
				}
				// 判断是否有权限
				if (Notification.permission === "granted") {
					var notification = new Notification(title, {
						"icon": iconUrl,
						"body": content.replace(/<br>/g, '\n'),
					});
					notification.addEventListener("show", function(){
						var e = notification;
						if(sound){//提示音
							notifySound();
						}
						setTimeout(function(){e.close();}, waitCloseTime);
					});
				}
				// 如果没权限，则请求权限
				else if (Notification.permission !== 'denied') {
					var thisContext = this;
					Notification.requestPermission(function(permission) {
						// Whatever the user answers, we make sure we store the
						// information
						if (!('permission' in Notification)) {
							Notification.permission = permission;
						}
						// 如果接受请求
						if (permission === "granted") {
							var notification = new Notification(title, {
								"icon": iconUrl,
								"body": content.replace(/<br>/g, '\n'),
							});
							notification.addEventListener("show", function(){
								var e = notification;
								setTimeout(function(){e.close();}, waitCloseTime);
							});
						} else {
							if (!negligible) {
								thisContext.notifyMsgOld(status, title, content);
							}
						}
					});
				} else {
					if (!negligible) {
						this.notifyMsgOld(status, title, content);
					}
				}
			} else {
				if (!negligible) {
					this.notifyMsgOld(status, title, content);
				}
			}
		},
		
		/**
		 * 公用的旧版提示
		 */
		notifyMsgOld : function(status, title, content) {
			var boxColor = '#5384AF';
			var boxIcon = 'fa fa-bell';
			
			if (!status) {
				boxColor = '#C46A69';
				boxIcon = 'fa fa-warning';
			}
			
			content += '<br><div style="font-size: 12px;">开启右下角通知：<br>设置-显示高级设置-隐私设置-内容设置-通知-管理例外情况-删除禁止本系统, 并允许所有</div>';
			
			$.smallBox({
				title : title,
				content : content,
				color : boxColor,
				timeout : 5000,
				icon : boxIcon
			});
		},
		
		/**
		 * 成功提示消息
		 */
		triggerSuccessMesg : function(title, content){
			this.notifyMsg(true, title, content);
		},
		
		/**
		 * 失败提示消息
		 */
		triggerFailMesg : function(title, content){
			this.notifyMsg(false, title, content);
		},
		
		/**
		 * 确认框的提示消息
		 */
		triggerAlertMesg : function(title, content, navFunc, posFunc, $scope){
			$.SmartMessageBox({
				title:title,
				content:content,
				buttons:"[取消][确定]",
			},function(ButtonPress,Value){
				if(ButtonPress == "取消"){
					$scope.$apply(function(){
						navFunc();
					});
				}else if(ButtonPress == "确定"){
					$scope.$apply(function(){
						posFunc();
					});
				}
			});
		},
		
		/**
		 * 将以,分隔代表多个的字符串转换为列表
		 */
		packetStrToList : function(string){
			var list = [];
			if(string && string.length>0){
				list = string.split(",");
			}
			return list;
		},
		
		/**
		 * 将列表转换成字符串（以,分隔）
		 */
		packetListToStr : function(list){
			var string = "";
			if(list && list.length>0){
				for(var index in list){
					string += list[index] + ",";
				}
				if(string != ""){
					string = string.substring(0, string.length-1);
				}
			}
			return string;
		},
		
		/**
		 * 通过url打开页面
		 * isLocation true-本页面打开，false-新窗口打开
		 */
		toPage	: function(url, isLocation){
			if(isLocation){
				window.location.href = url;
			}else{
				window.open(url);
			}
		},
		
		/**
		 * 将滚动条滚动到指定位置，如 #id
		 */
		scrollTo : function(target){
			$("html, body").animate({scrollTop: $(target).offset().top}, {duration: 500,easing: "swing"});
		},
		
		/**
		 * 日期控件
		 */
		MydatePicker	: function(){
			WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});
		},
		
};
