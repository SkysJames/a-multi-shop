<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
	<%@ include file="/sky/common/client.inc.jsp"%>
    
    <title>${systemName }</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no">
    
    <!-- 导入相应的css -->
	<link rel="stylesheet" href="${ contextPath }/sky/client/business/product/css/product.css" />
	
	<!-- 导入相应的js -->
	<script type="text/javascript" src="${ contextPath }/sky/client/business/product/js/product.js"></script>
	<script type="text/javascript" src="${ contextPath }/sky/client/business/product/js/productDetail.js"></script>
  
  	<%
	Integer type = (Integer)request.getAttribute("type");
	String code = (String)request.getAttribute("code");
	%>
	<script type="text/javascript">
	var type = <%=type%>;
	var code = '<%=code==null?"":code%>';
	</script>
  </head>
  
  <body data-ng-app="productApp" data-ng-controller="productCtrl">
  	<!-- 页面头部start -->
	<client-top current-nav="currentNav"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 背景图片start -->
	<bg-show bg-url="currentBgUrl" head-text="currentItem.name" height="{{currentItem.height}}"></bg-show>
  	<!-- 背景图片end -->
  	
  	<!-- 产品模块start -->
  	<div class="product-module" data-ng-show="currentItem == null">
  		<!-- 产品模块顶部start -->
  		<div class="product-top">
  			<ul>
  				<li data-ng-class="{'active':item==condition.proType}" 
  					data-ng-repeat="item in proTypes" data-ng-click="filterType(item)">
  					{{item | stringProType}}
  				</li>
  			</ul>
  		</div>
  		<!-- 产品模块顶部end -->
  		
  		<!-- 产品模块主体部分start -->
	  	<div class="module-container">
	  		<div class="module-content text-left">
	  			<loading-panel data-ng-show="isLoadingProduct" font-size="22"></loading-panel>
			
				<nodata-panel data-ng-show="!isLoadingProduct && productList.length==0" font-size="22"></nodata-panel>
				
	  			<dl data-ng-show="!isLoadingProduct" data-ng-repeat="item in productList">
	  				<dd style="background-image: url({{item.picPathList[0]|getImgUrl}})">
	  					<a href="javascript:void(0)" title="{{item.name|showBlankValue}}" data-ng-click="$root.selectedItem(item)"></a>
	  				</dd>
	  				<dt class="module-title" title="{{item.name | showBlankValue}}" data-ng-click="$root.selectedItem(item)">
	  					{{item.name | showBlankValue}}
	  				</dt>
	  				<dt class="module-text">{{item.proType | stringProType}}&nbsp;&nbsp;{{item.createTime | formatDate}}</dt>
	  				<dt class="module-text">{{item.description}}</dt>
	  				<dt class="module-text"><a class="module-linked" href="javascript:void(0)" data-ng-click="$root.selectedItem(item)">查看详情</a></dt>
	  			</dl>
	  		</div>
	  		<common-pager class="common-pager" page-count="{{condition.pageCount}}" current-page="condition.pageNo" 
				total-rows="condition.totalCount" to-page-call-back="getProductList(pageNo)"></common-pager>
	  	</div>
  		<!-- 产品模块主体部分end -->
  	</div>
  	<!-- 产品模块end -->
  	
  	<!-- 产品模块详情start -->
  	<product-detail class="item-detail" data-ng-show="currentItem != null" current-item="currentItem">
  	</product-detail>
  	<!-- 产品模块详情end -->
	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
  </body>
</html>