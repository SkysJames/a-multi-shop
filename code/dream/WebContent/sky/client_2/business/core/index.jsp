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
	<link rel="stylesheet" href="${ contextPath }/sky/client/business/core/css/index.css" />
	
	<!-- 导入相应的js -->
	<script type="text/javascript" src="${ contextPath }/sky/client/business/core/js/index.js"></script>
  </head>
  
  <body data-ng-app="indexApp" data-ng-controller="indexCtrl">
  	<!-- 页面头部start -->
	<client-top current-nav="currentNav"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 轮播图片start -->
	<slide-show slide-list="slideList"></slide-show>
  	<!-- 轮播图片end -->
  	
  	<!-- 产品start -->
  	<div class="index-product">
	  	<!-- 产品类型start -->
	  	<div class="container">
	  		<div class="index-top">
	  			<h3>产品类型</h3>
	  		</div>
	  		<div class="type-module">
	  			<ul>
	  				<li data-ng-repeat="item in proTypes" data-ng-click="triggerPage(clientNavs.d_product.url+'?type='+item)">
	  					<div class="header" style="background-image: url({{item | imgProType}})"></div>
	  					<div class="content">
	  						<h3>{{item | stringProType}}</h3>
	  						<p>{{item | contentProType}}</p>
	  					</div>
	  				</li>
	  			</ul>
	  		</div>
	  	</div>
	  	<!-- 产品类型end -->
	  	
	  	<!-- 产品中心start -->
	  	<div class="container">
	  		<div class="index-top">
	  			<h3>产品中心</h3>
	  			<a href="javascript:void(0)" data-ng-click="triggerPage(clientNavs.d_product.url)">
	  				<i class="fa fa-toggle-right"></i>&nbsp;查看更多
	  			</a>
	  		</div>
	  		<div class="item-module">
	  			<ul>
	  				<li data-ng-repeat="item in productList" data-ng-click="triggerPage(clientNavs.d_product.url+'?code='+item.id)">
	  					<div class="header">
	  						<label>{{item.name | showBlankValue}}</label><i class="fa fa-angle-right"></i>
	  						<p>{{item.description | showBlankValue}}</p>
	  					</div>
	  					<div class="bg-img" style="background-image: url({{item.picPathList[0]|getImgUrl}})"></div>
	  				</li>
	  			</ul>
	  		</div>
	  	</div>
	  	<!-- 产品中心end -->
  	</div>
  	<!-- 产品end -->
  	
  	<!-- 新闻start -->
  	<div class="index-news">
  		<div class="container">
  			<div class="header">
  				<label data-ng-class="{'active':item==newsCondition.newsType}" 
  					data-ng-repeat="item in newsTypes" data-ng-click="getNewsListByType(item)">{{item | stringNewsType}}</label>
  			</div>
  			<div class="content">
  				<ul>
  					<li data-ng-repeat="item in newsList" data-ng-click="triggerPage(clientNavs.c_news.url+'?code='+item.id)">
  						<div class="left-img" style="background-image: url({{item.picPathList[0]|getImgUrl}})"></div>
  						<div class="right-con">
  							<h3>{{item.name | showBlankValue}}</h3>
  							<p>{{item.content | getSmallContent}}<a href="javascript:void(0)">[详情]</a></p>
  							<span>{{item.createTime | formatDate}}</span>
  						</div>
  					</li>
  				</ul>
  			</div>
  		</div>
  	</div>
  	<!-- 新闻end -->
	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
  </body>
</html>
