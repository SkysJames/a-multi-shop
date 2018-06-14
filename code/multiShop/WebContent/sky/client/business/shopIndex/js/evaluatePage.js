angular.module('evaluatePage.module',[])
.controller("evaluatePageCtrl",['$timeout', '$scope', '$filter', '$document', "clientIndexHttpService", 
function($timeout, $scope, $filter, $document, clientIndexHttpService){
	//根作用域
	$scope.shopIndexScope = angular.element($('#shopIndexId')).scope();
	//搜索条件对象
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 30,	//每页数据量
			pageCount	: 1,
			totalCount	: 0,
			tableName	: common.tableContants.TB_SHOP,//店铺表名
			objId		: shopId,	//店铺ID
			clientStatus	: common.evaluateContants.status.NOSEND,	//状态为大于未发送状态
			mark			: "",	//评分，空未全部
			hasPic		: "",	//是否有图片，空未全部，'1'-有图
	};
	//选中的导航
	$scope.selectedNav = "evaluate";
	
	
	/**
	 * 选择过滤评分
	 */
	$scope.selectMark = function(mark){
		$scope.condition.mark = mark;
		$scope.pagedEvaluateList();
	};
	
	/**
	 * 过滤是否有图
	 */
	$scope.filterHasPic = function(){
		$scope.pagedEvaluateList();
	};
	
	/**
	 * 分页获取商品列表
	 */
	$scope.pagedEvaluateList = function(pageNo){
		if(pageNo){
			$scope.condition.pageNo = pageNo;
		}else{
			$scope.condition.pageNo = 1;
		}
		
		$scope.isLoadingEvaluate = true;
		clientIndexHttpService.pagedEvaluateList($scope.condition)
		.then(function(response){
			var data = response.data;
			
			$scope.evaluateList = data.pager.resultList;
			$scope.condition.pageCount = data.pager.pageCount;
			$scope.condition.totalCount = data.pager.totalCount;
			$scope.isLoadingEvaluate = false;
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 添加店铺评价
	 */
	$scope.deleteEvaluate = function(eval){
		if(!$currentUser){
			return;
		}
		
		if($currentUser.userId != eval.userId){
			return;
		}
		
		common.triggerAlertMesg("确定删除？", "", function(){}, function(){
			clientIndexHttpService.deleteEvaluate(eval)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					$scope.pagedEvaluateList();
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
		//分页获取评价列表
		$scope.pagedEvaluateList();
		
		//还原屏幕
		$(".sh-phone").removeClass("sh-phone-scroll");
		$(".shop-index-page").removeClass("shop-index-page-scroll");
		
		//手机端的详情面板滚动事件
		$(".se-content").scroll(function(){
			var allHeight = $(".se-content>.se-for-scroll").height();
			
			//判断向下滚动是否大于0，且可扩大屏幕
			if($(".se-content").scrollTop() > 0 
					&& allHeight > ($(".se-content").height() + $(".sn-phone").height() + $(".sh-phone").height())){
				$(".sh-phone").addClass("sh-phone-scroll");
				$(".shop-index-page").addClass("shop-index-page-scroll");
			}
			
			//判断是否滚动到div的顶部
			if($(".se-content").scrollTop() == 0){
				$(".sh-phone").removeClass("sh-phone-scroll");
				$(".shop-index-page").removeClass("shop-index-page-scroll");
			}
		});
	};
	$document.ready($scope.initFunc);
}]);
