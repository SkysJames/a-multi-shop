angular.module('personPanel',[])
.directive('personPanel',function(){
	return {
		restrict:'E',
		scope : {
			
		},
		templateUrl : $contextPath +"/sky/server/component/personPanel/template/personPanel.html",
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
				$scope.editUser = _.cloneDeep($scope.currentUser);
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
						common.triggerSuccessMesg(data.message);
						window.location.reload();
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
					serverIndexHttpService.editPersonPawd($scope.currentUser.id, $scope.passwd.oldPasswd, $scope.passwd.newPasswd)
					.then(function(response){
						var data = response.data;
						if(data.statusCode == "200"){
							$scope.initCurrentUser();//更新当前用户
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
				if($scope.passwd.oldPasswd != $scope.currentUser.passwd){
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
			
			/**
			 * 初始化当前用户信息
			 */
			$scope.initCurrentUser = function(){
				serverIndexHttpService.getUserById($currentUser.userId)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						$scope.currentUser = data.user;
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//初始化当前用户信息
				$scope.initCurrentUser();
			};
			$document.ready($scope.initFunc);
			
		}
	};
});