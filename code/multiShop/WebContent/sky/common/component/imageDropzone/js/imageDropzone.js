angular.module('imageDropzone',[])
.directive('imageDropzone',function(){
	return {
		restrict : 'E',
		templateUrl: $contextPath + '/sky/common/component/imageDropzone/template/imageDropzone.html',
		scope:{
			dropzoneId		: "@",
			imagePathList	: "=",
			maxFiles			: "=",//上传的文件数
		},
		link : function(scope,element,attrs){ 
			
		},
		controller : function($scope,$element,$attrs,$filter,$timeout,$document){
			$scope.contextPath = $contextPath;
			$scope.acceptedFiles = '.jpg,.jpeg,.bmp,.png,.ico,.gif';
			$scope.maxFilesize = 10;
			$scope.maxFiles	 = $scope.maxFiles?$scope.maxFiles:5;
			$scope.config = {
                url				: $contextPath + "/common/file!uploadTempFile",//必要参数，文件的上传地址
                method			: "post",
    				paramName		: "file",//相当于<input>元素的name属性，默认为file
	    			maxFilesize		: $scope.maxFilesize,//限制文件的大小，单位是MB
	    			maxFiles			: $scope.maxFiles,//默认为null，可以指定为一个数值，限制最多文件数量
	    			addRemoveLinks	: true,//添加每个文件删除或取消的链接
	    			acceptedFiles	: $scope.acceptedFiles,//接收的文件类型
	    			uploadMultiple	: false,//是否在一个请求中发送多个文件。如果它设置为true，则后备文件输入元素也将具有该multiple属性
	    			dictMaxFilesExceeded	: "最多只能上传" + $scope.maxFiles + "个图片",
	    			dictFileTooBig		: "只支持" + $scope.maxFilesize + "M以内",//文件大小过大时的提示文本
	    			dictDefaultMessage	: "点击添加<br>或拖动图片至此",//没有任何文件被添加时的提示文本
	    			dictInvalidFileType	: "只支持" + $scope.acceptedFiles + "文件类型",
	    			dictFallbackMessage	: "浏览器不受支持",
	    			dictResponseError	: "文件上传失败!",
	    			dictCancelUpload		: "取消",
	    			dictRemoveFile		: "删除",
	    			previewTemplate		: '<div id="preview-template" style="display: none;"></div>',
	    			init : function(){
	    				this.on("addedfile", function(file) {
	    	            });
	    	            this.on("success",function(file,data){
	    	            		//上传成功触发的事件
	    	                if(!$scope.imagePathList){
	    	                		$scope.imagePathList=[];
	               		}
	    	                
	    	                if($scope.imagePathList.length >= $scope.maxFiles){
	    	                		common.triggerFailMesg("上传失败", $scope.config.dictMaxFilesExceeded);
	    	                		return;
	    	                }
	    	                
	    	                $scope.imagePathList.unshift(data.fileMap.savePath);
	    	                $scope.$apply();
	    	            });
	    	            this.on("error",function (file, data) {
	    	                //上传失败触发的事件
//	    	                console.log('fail', file, data);
	    	            		common.triggerFailMesg("上传失败",data);
	    	            });
	    	            this.on("removedfile",function(file){
	    	                //删除文件时触发的方法
//	    	            		console.log("删除文件", file);
	    	            });
	    	            this.on("complete",function (file) {
	    	            		//上传成功或失败后触发的方法
	    	            		this.removeFile(file);
	    	            });
	    			},
            };
			
			/**
			 * 删除图片
			 */
			$scope.deleteImage = function(index){
				$scope.imagePathList.splice(index,1);
			};
            
			Dropzone.autoDiscover = false;
			
			$timeout(function(){
				$scope.dropzone = new Dropzone('#' + $scope.dropzoneId, $scope.config);
			},100);
		}
	}
});