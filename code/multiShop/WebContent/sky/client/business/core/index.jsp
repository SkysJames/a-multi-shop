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
  	<div class="module-container">
  		<div class="module-header">
	  		<div class="module-head">产品</div>
	  		<div class="module-subhead">
	  			<ul>
	  				<li data-ng-repeat="item in proTypes">
	  					<a href="javascript:void(0)" data-ng-click="triggerPage(clientNavs.d_product.url+'?type='+item)">{{item | stringProType}}</a>&nbsp;/&nbsp;
	  				</li>
	  			</ul>
	  		</div>
  		</div>
  		<div class="module-content">
  			<dl data-ng-repeat="product in productList">
  				<dd style="background-image: url({{product.picPathList[0]|getImgUrl}})">
  					<a href="javascript:void(0)" title="{{product.name|showBlankValue}}" 
  						data-ng-click="triggerPage(clientNavs.d_product.url+'?code='+product.id)"></a>
  				</dd>
  				<dt class="module-title" title="{{product.name | showBlankValue}}"
  					data-ng-click="triggerPage(clientNavs.d_product.url+'?code='+product.id)">{{product.name | showBlankValue}}</dt>
  			</dl>
  		</div>
  	</div>
  	<!-- 产品end -->
  	
  	<div class="index-about">
  		<h1>我们是设计师、工程师、梦想者<br>也是您的用户体验专家</h1>
  		<p>
  		『梦想蓝天网络科技』主要以互联网基础服务、系统管理网站服务为主导。 服务项目包括域名注册、 虚拟主机、网站建设、系统管理、课程设计、微信小程序等，以帮助客户轻松、高速、 高效的应用互联网，提高企业竞争能力。 
  		『梦想蓝天网络科技』正在以创新、引领互联网科技前沿作为自身进步的灵魂和事业兴旺发达的不懈动力，积极建立适应知识经济、技术创新的机制与经营方式，全力向技术产业方面发展。 团队有多年的互联网基础应用服务运营经验，凭借优秀的运营管理队...
  		</p>
  		<a href="javascript:void(0);" data-ng-click="scrollTo('#clientBottomId')">联系我们</a>
  		<a href="javascript:void(0);" data-ng-click="triggerPage(clientNavs.b_about.url)">了解更多</a>
  	</div>
  	
  	<!-- 新闻start -->
  	<div class="module-container">
  		<div class="module-header">
	  		<div class="module-head">动态</div>
	  		<div class="module-subhead">
	  			创意、流程、执行缺一不可
	  		</div>
  		</div>
  		<div class="module-content">
  			<dl data-ng-repeat="news in newsList">
  				<dd style="background-image: url({{news.picPathList[0]|getImgUrl}})">
  					<a href="javascript:void(0)" title="{{news.name|showBlankValue}}" 
  						data-ng-click="triggerPage(clientNavs.c_news.url+'?code='+news.id)"></a>
  				</dd>
  				<dt class="module-title" title="{{news.name | showBlankValue}}"
  					data-ng-click="triggerPage(clientNavs.c_news.url+'?code='+news.id)">{{news.name | showBlankValue}}</dt>
  			</dl>
  		</div>
  	</div>
  	<!-- 新闻end -->
	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
  </body>
</html>
