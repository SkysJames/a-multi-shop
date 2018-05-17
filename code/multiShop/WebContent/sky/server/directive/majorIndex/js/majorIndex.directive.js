angular.module('majorIndex',[])
.directive('majorIndex',function(){
	return {
		restrict:'E',
		scope : {
			
		},
		templateUrl : $contextPath +"/sky/server/directive/majorIndex/template/majorIndex.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			//访客查询条件
			$scope.visitorCondition = {
					pageNo		: 1,		//当前页码
					pageSize		: 10,	//每页数据量
					totalCount	: 0,		//数据总量
					pageCount	: 0,		//总页码数
					status		: common.visitorContants.status.USING,
			};
			
			//获取访客列表
			$scope.getVisitorList = function(){
				$scope.isLoadingVisitor = true;
				serverIndexHttpService.getVisitorList($scope.visitorCondition)
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
				//获取访客列表
				$scope.getVisitorList();
			};
			$document.ready($scope.initFunc);
		}
	};
});