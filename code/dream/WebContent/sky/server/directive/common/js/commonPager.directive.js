angular.module('commonPager',[])
.directive('commonPager',function(){
	return {
		restrict :'E',
		scope:{
			/**
			 * toPageCallBack -- 用户点击页数时候的回调函数 -- 传入要获取的页
			 * ctrl -- function(page){
			 * 			
			 * 		}
			 * pageCount -- 页数总计
			 * currentPage -- 当前页
			 */
			toPageCallBack:'&',
			pageCount :'@',
			currentPage : '=',
			totalRows : '=',
			zIndex:"@",
		},
		replace: true,
		templateUrl : $contextPath + '/sky/server/directive/common/template/commonPager.html',
		link :function(scope,element,attrs){
			if(attrs.fixed == "true"){
				scope.fixed = true;
			}
			scope.selectedPage = 1;
			scope.toPage=function(pageNum){
				var obj ={};
				var pageNumber = 1;//当输入的页码不符合要求时，跳转到第1页
				if(pageNum!=null){
					pageNumber=pageNum;
				}
				if(scope.pageCount==null || scope.pageCount<=0){//没有数据，默认有1页
					scope.pageCount=1;
				}
				if(pageNumber<1 || pageNumber >scope.pageCount || pageNumber == ''){
					if(pageNumber > scope.pageCount){
						pageNumber = scope.pageCount;
						
					}else if(pageNumber<1){
						pageNumber =1;
					}else{
						pageNumber =1;
					}
				}
				obj.pageNo = pageNumber;
				scope.currentPage=pageNumber;
				scope.selectedPage = pageNumber;
				scope.toPageCallBack(obj);
			}
		},
		controller : function($scope,$element,$attrs){
			$scope.pageArray=new Array();
			$scope.$watch('currentPage',function(){
				$scope.selectedPage = $scope.currentPage;
			});
			$scope.$watch('selectedPage',function(){
//				if($scope.selectedPage < 1){
//					$scope.selectedPage =1;
//				}
				if($scope.selectedPage >  $scope.pageCount){
					$scope.selectedPage =$scope.pageCount;
				}
			});
			$scope.checkIfEnterPress = function($event,selectedPage){
				if($event.charCode == 13){
					$scope.toPage(selectedPage);
				}
			}
			$scope.$watch('pageCount',function(){
				if(angular.isDefined($scope.pageCount)
						&& $scope.pageCount >= 0){
					$scope.pageArray = new Array();
					if($scope.pageCount==0){//页数为0，没有数据，则显示为第1页
						$scope.pageArray.push(1);
					}else{
						for(var index = 1; index <= $scope.pageCount ; index++){
							$scope.pageArray.push(index);
						}
					}
//					$scope.toPage(1);
				}
			});
			
		}
		
	}
});