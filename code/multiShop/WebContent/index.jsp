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
  </head>
  
  <body data-ng-app="indexApp" data-ng-controller="indexCtrl">
  	<!-- 页面头部start -->
	<client-top table-name="tb_shop" selected-type="selectedType" type-list="oneTypeList"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 系统名称，搜索框start -->
	<index-header table-name="tb_shop" keywords="keywords" index-ans="indexAns"></index-header>
	<!-- 系统名称，搜索框end -->
	
	<!-- 分割线 start -->
	<div class="my-line"></div>
	<!-- 分割线 end -->
	
	<!-- 轮播图片start -->
	<slide-show table-name="tb_shop" type-list="oneTypeList" slide-list="slideList" slide-href-list="slideHrefList" index-ans="indexAns"></slide-show>
  	<!-- 轮播图片end -->
  	
  	<!-- 推荐店铺start -->
  	<div class="index-commodel index-recommend">
  		<div class="ishop-header common-padding">
  			<h2>推荐店铺</h2>
  		</div>
  		<div class="ishop-content">
  			<ul>
  				<li data-ng-repeat="item in reShopList" data-ng-click="toPage('/home/shop-index?shopId='+item.id)">
  					<div class="ishop-img" style="background-image: url({{item.logoPathList|getImgByImgList}})"></div>
  					<div class="ishop-con">
  						<h3 title="{{item.name | showBlankValue}}">{{item.name | showBlankValue}}</h3>
  						<p title="简介：{{item.brief | showBlankValue}}">简介：{{item.brief | showBlankValue}}</p>
  						<p title="评分：{{item.mark}}分">
  							评分：{{item.mark}}分
  							<label title="人气{{item.popularity}}"><i class="fa fa-heart"></i>&nbsp;{{item.popularity}}</label>
  						</p>
  						<p title="地址：{{item.address | showBlankValue}}">地址：{{item.address | showBlankValue}}</p>
  					</div>
  				</li>
  			</ul>
  		</div>
  	</div>
  	<!-- 推荐店铺end -->
  	
  	<!-- 类型店铺start -->
  	<div class="index-commodel index-shop" data-ng-class="{'index-shop-show': typet.shopList && typet.shopList.length>0}" data-ng-repeat="typet in twoTypeList">
  		<div class="ishop-header common-padding">
  			<h2>{{typet.name}}</h2>
  			<a href="home/shop-search?type={{typet.id}}">
  				更多&nbsp;<i class="fa fa-chevron-right"></i>
  			</a>
  		</div>
  		<div class="ishop-content">
  			<ul>
  				<li data-ng-repeat="item in typet.shopList" data-ng-click="toPage('/home/shop-index?shopId='+item.id)">
  					<div class="ishop-img" style="background-image: url({{item.logoPathList|getImgByImgList}})"></div>
  					<div class="ishop-con">
  						<h3 title="{{item.name | showBlankValue}}">{{item.name | showBlankValue}}</h3>
  						<p title="简介：{{item.brief | showBlankValue}}">简介：{{item.brief | showBlankValue}}</p>
  						<p title="评分：{{item.mark}}分">
  							评分：{{item.mark}}分
  							<label title="人气{{item.popularity}}"><i class="fa fa-heart"></i>&nbsp;{{item.popularity}}</label>
  						</p>
  						<p title="地址：{{item.address | showBlankValue}}">地址：{{item.address | showBlankValue}}</p>
  					</div>
  				</li>
  			</ul>
  		</div>
  	</div>
  	<loading-panel data-ng-show="isLoadingShop" font-size="18"></loading-panel>
  	<!-- 类型店铺end -->
	  	
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
  <script type="text/javascript" src="${ contextPath }/sky/client/business/core/js/index.js"></script>
  
</html>
