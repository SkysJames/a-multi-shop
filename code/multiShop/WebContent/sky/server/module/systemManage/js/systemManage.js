angular.module('userManage.module',['userManageSave'])
.controller("userManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $filter, $document, serverIndexHttpService){
	//保存页面类型，null-不展示，'save'-添加，'edit'-编辑
	$scope.saveType = null;
	//用户查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			shopId		: $currentUser.shopId==common.shopContants.shopSystem?"":$currentUser.shopId,
			keywords		: "",
			loginTimeA	: "",
			loginTimeZ	: "",
			userStatus	: common.userContants.userStatus.USING,
	};
	//日期对象
	$scope.rangeDate = {
			startDate	: null,	//开始时间
			endDate		: null,	//结束时间
	};
	//筛选条件的日期选项
	$scope.dateRangePickerOptions = defaultDateRangePickerOptions;
	$scope.dateRangePickerOptions.eventHandlers = {
		'hide.daterangepicker' : function(event, picker){
			if($scope.rangeDate.startDate==null || $scope.rangeDate.endDate==null){
				$("#searchDateRangeId").val("");
			}
		},
		'apply.daterangepicker' : function(event, picker){
			$timeout(function(){
				$scope.rangeDate.startDate = picker.startDate;
				$scope.rangeDate.endDate = picker.endDate;
				if($scope.rangeDate.startDate==null || $scope.rangeDate.endDate==null){
					common.triggerFailMesg('请选择搜索的时间段!','');
					return;
				}
				$scope.pagedUserList();
			}, 100);
		},
	};
	
	/**
	 * 显示日期选择器
	 */
	$scope.showSearchDateRange = function(){
		$("#searchDateRangeId").data("daterangepicker").show();
	};
	
	/**
	 * 切换面板，显示/隐藏编辑面板
	 */
	$scope.togglePanel = function(saveType, selectedObj){
		$scope.saveType = saveType;
		if(selectedObj){
			$scope.userSave = _.cloneDeep(selectedObj);
		}else{
			$scope.userSave = null;
		}
	};
	
	/**
	 * 清空查询条件-日期
	 */
	$scope.clearDate = function(){
		$scope.rangeDate.startDate = null;
		$scope.rangeDate.endDate = null;
		
		$scope.pagedUserList();
	};
	
	/**
	 * 封装查询条件
	 */
	$scope.packageCondition = function(pageNo){
		//页码
		if(pageNo){
			$scope.condition.pageNo = pageNo;
		}else{
			$scope.condition.pageNo = 1;
		}
		
		//更新开始结束时间
		if($scope.rangeDate.startDate && $scope.rangeDate.endDate){
			$scope.condition.loginTimeA = $scope.rangeDate.startDate.format(DateUtil.timeFormat);
			$scope.condition.loginTimeZ = $scope.rangeDate.endDate.format(DateUtil.timeFormat);
		}else{
			$scope.condition.loginTimeA = "";
			$scope.condition.loginTimeZ = "";
		}
	};
	
	/**
	 * 获取用户列表
	 */
	$scope.pagedUserList = function(pageNo){
		$scope.packageCondition(pageNo);
		
		//获取过滤条件字符串
		$scope.getFilterText();
		
		$scope.isLoadingUser = true;
		serverIndexHttpService.pagedUserList($scope.condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.userList = data.pager.resultList;
				
				$scope.condition.totalCount = data.pager.totalCount;
				$scope.condition.pageCount = data.pager.pageCount;
				$scope.isLoadingUser = false;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 获取过滤条件字符串
	 */
	$scope.getFilterText = function(){
		$scope.filterText = "";
		if($scope.condition){
			if(""!=$scope.condition.keywords){
				$scope.filterText += "模糊搜索（" + $scope.condition.keywords + "）+ "
			}
			if(""!=$scope.condition.loginTimeA && ""!=$scope.condition.loginTimeZ){
				$scope.filterText += "登陆时间（" + $scope.condition.loginTimeA + "~" + $scope.condition.loginTimeZ + "）+ "
			}
			if(""!=$scope.condition.opType){
				$scope.filterText += "用户状态（" + $filter("stringUserStatus")($scope.condition.userStatus) + "）+ "
			}
		}
		
		if($scope.filterText.length > 0){
			$scope.filterText = $scope.filterText.substring(0, $scope.filterText.length-2);
		}
	};
	
	/**
	 * 删除用户
	 */
	$scope.deleteUser = function(user){
		common.triggerAlertMesg("确定要删除用户 " + user.name + "？", "", function(){}, function(){
			serverIndexHttpService.deleteUser(user)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					common.triggerSuccessMesg(data.message);
					$scope.pagedUserList();
				}else{
					common.triggerFailMesg(data.message);
				}
			},function(err){
				console.log(err);
			});
		}, $scope);
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//判断当前用户是否有权限待在当前页面
		serverCommon.hasRightStay('user_manage');
		//改变当前导航指向
		serverCommon.navChange("#/user");
		//获取用户列表
		$scope.pagedUserList();
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
	};
	$document.ready($scope.initFunc);
}]);
