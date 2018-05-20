<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>${systemName }管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    
    <!-- 导入系统图标 -->
    <link rel="icon" href="${systemIcon }" type="image/x-icon">
    
    <!-- 后台基本css -->
	<%@ include file="/sky/server/common/server.inc-css.jsp"%>
	<!-- smartadmin css -->
	<link rel="stylesheet" href="${contextPath }/sky/server/business/core/css/smart-admin.css" />
    <!-- 导入相应的css -->
	<link rel="stylesheet" href="${contextPath }/sky/server/business/core/css/index.css" />
  </head>
  
  <body class="server-index" data-ng-app="serverIndexApp" data-ng-controller="serverIndexCtrl">
  	<!-- HEADER -->
	<header id="header">
		<div id="logo-group">
			<!-- PLACE YOUR LOGO HERE -->
			<span id="logo"> 
				<img src="${systemLogo }" alt="SmartAdmin"> 
				${systemName }管理系统
			</span>
			<!-- END LOGO PLACEHOLDER -->
		</div>

		<!-- pulled right: nav area -->
		<div class="pull-right">

			<!-- collapse menu button -->
			<div id="hide-menu" class="btn-header pull-right">
				<span> <a href="javascript:void(0);" title="隐藏左导航"><i class="fa fa-reorder"></i></a> </span>
			</div>
			<!-- end collapse menu -->

			<!-- logout button -->
			<div id="logout" class="btn-header transparent pull-right">
				<span> <a href="${contextPath}/home/server-logout" title="退出" data-logout-msg="您确定要退出系统吗？"><i class="fa fa-sign-out"></i></a> </span>
			</div>
			<!-- end logout button -->

			<!-- fullscreen button -->
			<div id="fullscreen" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0);" onclick="launchFullscreen(document.documentElement);" title="满屏"><i class="fa fa-arrows-alt"></i></a> </span>
			</div>
			<!-- end fullscreen button -->

		</div>
		<!-- end pulled right: nav area -->

	</header>
	<!-- END HEADER -->
	
	
	<!-- Left panel : Navigation area -->
	<!-- Note: This width of the aside area can be adjusted through LESS variables -->
	<aside id="left-panel">

		<!-- User info -->
		<div class="login-info">
			<span>
				<a href="javascript:void(0);">
					<img src="${contextPath }/sky/server/business/core/img/user.jpeg" alt="me" /> 
					<span>${loginUser.username }</span>
				</a> 
			</span>
		</div>
		<!-- end user info -->

		<nav>
			<ul>
				<li class="">
					<a href="#/majorIndex" title="主页"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">主页</span></a>
				</li>
<!-- 				<li> -->
<!-- 					<a href="javascript:void(0);"><i class="fa fa-lg fa-fw fa-inbox"></i> <span class="menu-item-parent">系统管理</span><span class="badge pull-right inbox-badge">14</span></a> -->
<!-- 				</li> -->
				<li>
					<a href="ajax/table.html"><i class="fa fa-lg fa-fw fa-inbox"></i> <span class="menu-item-parent">系统管理</span></a>
				</li>
				<li>
					<a href="#"><i class="fa fa-lg fa-fw fa-comments-o"></i> <span class="menu-item-parent">消息管理</span></a>
					<ul>
						<li>
							<a href="ajax/table.html">消息列表</a>
						</li>
						<li>
							<a href="ajax/table.html">举报列表</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="#/oplog"><i class="fa fa-lg fa-fw fa-calendar"></i> <span class="menu-item-parent">日志管理</span></a>
				</li>
				<li>
					<a href="#"><i class="fa fa-lg fa-fw fa-pencil-square-o"></i> <span class="menu-item-parent">Forms</span></a>
					<ul>
						<li>
							<a href="ajax/form-elements.html">Smart Form Elements</a>
						</li>
						<li>
							<a href="ajax/form-templates.html">Smart Form Layouts</a>
						</li>
						<li>
							<a href="ajax/validation.html">Smart Form Validation</a>
						</li>
						<li>
							<a href="ajax/bootstrap-forms.html">Bootstrap Form Elements</a>
						</li>
						<li>
							<a href="ajax/plugins.html">Form Plugins</a>
						</li>
						<li>
							<a href="ajax/wizard.html">Wizards</a>
						</li>
						<li>
							<a href="ajax/other-editors.html">Bootstrap Editors</a>
						</li>
						<li>
							<a href="ajax/dropzone.html">Dropzone <span class="badge pull-right inbox-badge bg-color-yellow">new</span></a>
						</li>
					</ul>
				</li>
				<li>
					<a href="ajax/calendar.html"><i class="fa fa-lg fa-fw fa-calendar"><em>3</em></i> <span class="menu-item-parent">Calendar</span></a>
				</li>
				<li>
					<a href="#"><i class="fa fa-lg fa-fw fa-file"></i> <span class="menu-item-parent">Other Pages</span></a>
					<ul>
						<li>
							<a href="ajax/forum.html">Forum Layout</a>
						</li>
						<li>
							<a href="ajax/profile.html">Profile</a>
						</li>
						<li>
							<a href="ajax/timeline.html">Timeline</a>
						</li>
					</ul>	
				</li>
			</ul>
		</nav>
		<span class="minifyme"> <i class="fa fa-arrow-circle-left hit"></i> </span>

	</aside>
	<!-- END NAVIGATION -->
	
	
	<!-- MAIN PANEL -->
	<div id="main" role="main">

		<!-- RIBBON -->
		<div id="ribbon">

			<span class="ribbon-button-alignment"> 
				<span id="refresh" class="btn btn-ribbon" 
					data-title="refresh"  rel="tooltip" data-placement="right" 
					data-original-title="<i class='text-warning fa fa-warning'></i> 注意，重置所有的页面缓存" data-html="true">
						<i class="fa fa-refresh"></i>
				</span> 
			</span>

			<!-- breadcrumb -->
			<ol class="breadcrumb">
				<!-- This is auto generated -->
			</ol>
			<!-- end breadcrumb -->

			<!-- You can also add more buttons to the
			ribbon for further usability

			Example below:

			<span class="ribbon-button-alignment pull-right">
			<span id="search" class="btn btn-ribbon hidden-xs" data-title="search"><i class="fa-grid"></i> Change Grid</span>
			<span id="add" class="btn btn-ribbon hidden-xs" data-title="add"><i class="fa-plus"></i> Add</span>
			<span id="search" class="btn btn-ribbon" data-title="search"><i class="fa-search"></i> <span class="hidden-mobile">Search</span></span>
			</span> -->

		</div>
		<!-- END RIBBON -->

		<ng-view></ng-view> 
<!-- 		<!-- MAIN CONTENT --> -->
<!-- 		<div id="content"> -->

<!-- 		</div> -->
<!-- 		<!-- END MAIN CONTENT --> -->

	</div>
	<!-- END MAIN PANEL -->
	
  	
  </body>
  
  <!-- 后台基本js -->
  <%@ include file="/sky/server/common/server.inc-js.jsp"%>
  
  <!-- smartadmin js -->
  <script type="text/javascript" src="${contextPath }/sky/server/business/core/js/smart-admin.js"></script>
  
  <!-- 导入相应的js -->
  <script type="text/javascript" src="${contextPath }/sky/server/business/core/js/server-index.controller.js"></script>
	
</html>
