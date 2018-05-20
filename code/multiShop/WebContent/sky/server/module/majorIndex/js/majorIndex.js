angular.module('majorIndex.module',[])
.controller("majorIndexCtrl",['$timeout', '$scope', '$rootScope', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $document, serverIndexHttpService){
	//访客查询条件
	$scope.visitorCondition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 0,		//总页码数
			status		: common.visitorContants.status.USING,
	};
	
	//获取访客列表
	$scope.pagedVisitorList = function(){
		$scope.isLoadingVisitor = true;
		serverIndexHttpService.pagedVisitorList($scope.visitorCondition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.visitorList = data.pager.resultList;
				$scope.visitorCondition.totalCount = data.pager.totalCount;
				$scope.isLoadingVisitor = false;
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//改变当前导航指向
		serverCommon.navChange("#/majorIndex");
		//获取访客列表
		$scope.pagedVisitorList();
	};
	$document.ready($scope.initFunc);
}]);