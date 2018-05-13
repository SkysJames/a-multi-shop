angular.module('personPanel',[])
.directive('personPanel',function(){
	return {
		restrict:'E',
		scope : {
			
		},
		templateUrl : $contextPath +"/sky/server/directive/common/template/personPanel.html",
		link : function(scope,element,attrs){
			//面板ID对象
			scope.panelIdObj = {
					person	: "person",//个人信息面板
					password	: "password",//密码修改面板
			};
			scope.currentPanel = scope.panelIdObj.person;//当前面板
			scope.isEdit = false;//控制是否编辑个人信息
			scope.editUser = {};//编辑的用户个人信息
			scope.passwd = {};//修改的密码对象
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 切换面板
			 */
			$scope.togglePanel = function(panel){
				$scope.currentPanel = panel;
			};
			
			/**
			 * 控制是否编辑个人信息
			 */
			$scope.toggleEdit = function(flag){
				if(flag != undefined){
					$scope.isEdit = flag;
				}else{
					$scope.isEdit = !$scope.isEdit;
				}
			};
			
			/**
			 * 显示编辑个人信息
			 */
			$scope.showEditPerson = function(){
				$scope.editUser.id = $scope.$root.currentUser.id;
				$scope.editUser.name = $scope.$root.currentUser.name;
				$scope.editUser.remark = $scope.$root.currentUser.remark;
				$scope.toggleEdit(true);
			};
			
			/**
			 * 编辑保存个人信息
			 */
			$scope.savePerson = function(){
				$scope.savePersonWait = true;
				serverIndexHttpService.editPerson($scope.editUser)
				.then(function(response){
					var data = response.data;
					if(data.statusCode == "200"){
						$scope.$root.getCurrentUser();//更新当前登录用户
						$scope.toggleEdit(false);//关闭编辑
						$scope.savePersonWait = false;
						common.triggerSuccessMesg(data.message);
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 修改保存密码
			 */
			$scope.savePassword = function(){
				if($scope.checkPassword()){
					$scope.savePasswordWait = true;
					serverIndexHttpService.editPersonPawd($scope.$root.currentUser.id, $scope.passwd.oldPasswd, $scope.passwd.newPasswd)
					.then(function(response){
						var data = response.data;
						if(data.statusCode == "200"){
							$scope.$root.getCurrentUser();//更新当前登录用户
							$scope.passwd = {};//清空密码
							$scope.savePasswordWait = false;
							common.triggerSuccessMesg(data.message);
						}else{
							common.triggerFailMesg(data.message);
						}
					},function(err){
						console.log(err);
					});
				}
				
			};
			
			/**
			 * 检查密码是否可保存
			 */
			$scope.checkPassword = function(){
				if($scope.passwd.oldPasswd != $scope.$root.currentUser.passwd){
					common.triggerFailMesg("请输入正确的旧密码");
					return false;
				}
				
				if($scope.passwd.newPasswd.length < 8){
					common.triggerFailMesg("新密码的长度至少为8位");
					return false;
				}
				
				if($scope.passwd.newPasswd != $scope.passwd.confirmPasswd){
					common.triggerFailMesg("两次输入的密码有误");
					return false;
				}
				
				return true;
			};
			
		}
	};
});