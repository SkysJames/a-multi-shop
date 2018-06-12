<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  
  	<!-- 前端基本css -->
	<%@ include file="/sky/client/common/client.inc-css.jsp"%>
	
	<!-- 导入系统图标 -->
    <link rel="icon" href="${systemIcon }" type="image/x-icon">
  
    <title>${systemName }</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no">
    
    <%
	String keywords = (String)request.getAttribute("keywords");
	%>
	<script type="text/javascript">
	var keywords = '<%=keywords==null?"":keywords%>';
	</script>
  </head>
  
  <body data-ng-app="productSearchApp" data-ng-controller="productSearchCtrl">
  	<!-- 页面头部start -->
	<client-top table-name="tb_shop" keywords="condition.keywords" selected-type="selectedType" type-list="typeList"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 系统名称，搜索框start -->
	<index-header not-index="true" table-name="tb_product" keywords="condition.keywords" index-ans="indexAns"></index-header>
	<!-- 系统名称，搜索框end -->
	
	<!-- 分割线 start -->
	<div class="my-line"></div>
	<!-- 分割线 end -->
	
  	<!-- 商品列表start -->
  	<div class="index-commodel shop-product">
  		<div class="shoppro-content">
  			<ul>
  				<li data-ng-repeat="item in productList" data-ng-click="toPage('/home/product-index?productId='+item.id, true)">
  					<div class="shoppro-img" style="background-image: url({{item.picPathList|getImgByImgList}})"></div>
  					<div class="shoppro-con">
  						<h3 title="{{item.price}}元">
  							<i class="fa fa-rmb"></i>&nbsp;{{item.price}}
  							<label title="浏览量：{{item.clickCount}}">浏览量：{{item.clickCount}}</label>
  						</h3>
  						<a title="{{item.name | showBlankValue}}" href="javascript:void(0)">{{item.name | showBlankValue}}</a>
  						<p title="{{item.shopName | showBlankValue}}">{{item.shopName | showBlankValue}}</p>
  					</div>
  				</li>
  			</ul>
  		</div>
  	</div>
	<nodata-panel data-ng-show="!isLoadingProduct && productList.length==0" font-size="18"></nodata-panel>
	<loading-panel data-ng-show="isLoadingProduct" font-size="18"></loading-panel>
  	<!-- 商品列表end -->
  	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
	<!-- 公告消息模态框start -->
	<slide-announce-model ans-win-id="systemAnsWinId" index-ans="indexAns"></slide-announce-model>
	<!-- 公告消息模态框end -->
	
  </body>
  
  <!-- 后台基本js -->
  <%@ include file="/sky/client/common/client.inc-js.jsp"%>
  
  <!-- 导入相应的js -->
  <script type="text/javascript" src="${ contextPath }/sky/client/business/productSearch/js/productSearch.js"></script>
  
</html>
