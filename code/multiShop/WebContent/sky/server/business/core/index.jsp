<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
	<%@ include file="/sky/common/server.inc.jsp"%>
    
    <title>${systemName }管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    
    <!-- 导入相应的css -->
	<link rel="stylesheet" href="${ contextPath }/sky/server/business/core/css/index.css" />
	
	<!-- 导入相应的js -->
	<script type="text/javascript" src="${ contextPath }/sky/server/business/core/js/server-index.controller.js"></script>
  </head>
  
  <body class="server-index" data-ng-app="serverIndexApp" data-ng-controller="serverIndexCtrl">
  	<!-- 背景 -->
  	<canvas id="server-canvas"></canvas>
  	
  	<!-- 主页头部 -->
  	<div class="index-header">
  		<div class="header-headline">
  			${systemName }管理系统
  		</div>
  		
  		<!-- 登录时间 -->
  		<div class="header-time">
  			登录时间：
  			{{$root.currentUser.loginTime|formatDate}}
  		</div>
  		
  		<!-- 登录用户 -->
  		<div class="header-setting">
  			<div class="dropdown">
				<a href="javascript:void(0);" class="dropdown-toggle setting-menu" data-toggle="dropdown">
					<i class="fa fa-user"></i>&nbsp;{{$root.currentUser.name}}
					<span class="caret header-caret"></span>
				</a>
				<ul class="dropdown-menu header-dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="javascript:void(0);" data-ng-click="showPersonPanel();"><i class="fa fa-user-circle"></i>&nbsp;个人信息</a></li>
					<li class="divider"></li>
					<li><a href="${contextPath}/home/home-logout"><i class="fa fa-power-off"></i>&nbsp;退出</a></li>
				</ul>
			</div>
  		</div>
  	</div>
  	
  	<!-- 主页主体部分 -->
  	<div class="index-container" data-ng-class="{'hide-left': isHideLeft}">
  		<!-- 主体部分的左部导航 -->
  		<div class="index-left">
  			<ul class="left-menu">
  				<li data-ng-click="toggleDetailPanel()">
		  			<i data-ng-show="isHideLeft" class="fa fa-arrow-circle-right" title="展开列表"></i>
		  			<i data-ng-show="!isHideLeft" class="fa fa-arrow-circle-left" title="收缩列表"></i>
		  			<span data-ng-show="!isHideLeft">&nbsp;收缩列表</span>
  				</li>
  				<li data-ng-class="{'selected': currentNav.id==$root.navObj.index.id}" data-ng-click="$root.showDetailPanel($root.navObj.index)">
  					<i class="fa fa-home" title="首页"></i>
  					<span data-ng-show="!isHideLeft">&nbsp;首页</span>
  				</li>
  				<li data-ng-class="{'spreaded': $root.navObj.system.spreaded}">
  					<div data-ng-class="{'nav_selected': currentNav.parent==$root.navObj.system.id}" data-ng-click="toggleSecondNav($root.navObj.system)">
	  					<i class="fa fa-cubes" title="系统管理"></i>
	  					<span data-ng-show="!isHideLeft">&nbsp;系统管理</span>
	  					<i class="fa" data-ng-class="{'fa-chevron-up': $root.navObj.system.spreaded, 'fa-chevron-down': !$root.navObj.system.spreaded}"></i>
  					</div>
  					<ul class="second-menu">
  						<li data-ng-class="{'selected': currentNav.id==$root.navObj.system.secondNav.system_basic.id}" data-ng-click="$root.showDetailPanel($root.navObj.system.secondNav.system_basic)">
  							<span>&nbsp;基本信息</span>
  						</li>
  						<li data-ng-class="{'selected': currentNav.id==$root.navObj.system.secondNav.system_basic.id}" data-ng-click="$root.showDetailPanel($root.navObj.system.secondNav.system_about)">
  							<span>&nbsp;关于我们</span>
  						</li>
  					</ul>
  				</li>
  				<li data-ng-if="$root.currentUser.allRights.indexOf('product_manage') > -1" 
  					data-ng-class="{'selected': currentNav.id==$root.navObj.product.id}" data-ng-click="$root.showDetailPanel($root.navObj.product)">
  					<i class="fa fa-cubes" title="产品管理"></i>
  					<span data-ng-show="!isHideLeft">&nbsp;产品管理</span>
  				</li>
  				<li data-ng-if="$root.currentUser.allRights.indexOf('news_manage') > -1"
  					data-ng-class="{'selected': currentNav.id==$root.navObj.news.id}" data-ng-click="$root.showDetailPanel($root.navObj.news)">
  					<i class="fa fa-newspaper-o" title="新闻管理"></i>
  					<span data-ng-show="!isHideLeft">&nbsp;新闻管理</span>
  				</li>
  				<li data-ng-if="$root.currentUser.allRights.indexOf('visitor_manage') > -1" 
  					data-ng-class="{'selected': currentNav.id==$root.navObj.visitor.id}" data-ng-click="$root.showDetailPanel($root.navObj.visitor)">
  					<i class="fa fa-laptop" title="访客管理"></i>
  					<span data-ng-show="!isHideLeft">&nbsp;访客管理</span>
  				</li>
  				<li data-ng-if="$root.currentUser.allRights.indexOf('user_manage') > -1" 
  					data-ng-class="{'selected': currentNav.id==$root.navObj.user.id}" data-ng-click="$root.showDetailPanel($root.navObj.user)">
  					<i class="fa fa-users" title="用户管理"></i>
  					<span data-ng-show="!isHideLeft">&nbsp;用户管理</span>
  				</li>
  				<li data-ng-if="$root.currentUser.allRights.indexOf('oplog_manage') > -1" 
  					data-ng-class="{'selected': currentNav.id==$root.navObj.oplog.id}" data-ng-click="$root.showDetailPanel($root.navObj.oplog)">
  					<i class="fa fa-calendar" title="日志管理"></i>
  					<span data-ng-show="!isHideLeft">&nbsp;日志管理</span>
  				</li>
  			</ul>
  		</div>
  		
  		<!-- 主体部分的具体内容 -->
  		<div class="index-content">
  			<major-index data-ng-if="currentNav.id==$root.navObj.index.id"></major-index>
  			<news-manage data-ng-if="currentNav.id==$root.navObj.news.id"></news-manage>
  			<product-manage data-ng-if="currentNav.id==$root.navObj.product.id"></product-manage>
  			<visitor-manage data-ng-if="currentNav.id==$root.navObj.visitor.id"></visitor-manage>
  			<oplog-manage data-ng-if="currentNav.id==$root.navObj.oplog.id"></oplog-manage>
  			<user-manage data-ng-if="currentNav.id==$root.navObj.user.id"></user-manage>
  		</div>
  	</div>
  	
  	<!-- 个人信息 -->
  	<person-panel></person-panel>
  	
  </body>
</html>
