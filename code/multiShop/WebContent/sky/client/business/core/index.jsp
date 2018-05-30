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
    <!-- 导入相应的css -->
	<link rel="stylesheet" href="${ contextPath }/sky/client/business/core/css/index.css" />
	
	<!-- 导入系统图标 -->
    <link rel="icon" href="${systemIcon }" type="image/x-icon">
  
    <title>${systemName }</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no">
  </head>
  
  <body data-ng-app="indexApp" data-ng-controller="indexCtrl">
  	<!-- 页面头部start -->
	<client-top></client-top>
  	<!-- 页面头部end -->
	
	<!-- 系统名称，搜索框start -->
	<index-header keywords="keywords" index-ans="indexAns"></index-header>
	<!-- 系统名称，搜索框end -->
	
	<!-- 轮播图片start -->
	<slide-show table-name="tb_shop" type-list="oneTypeList" slide-list="slideList" index-ans="indexAns"></slide-show>
  	<!-- 轮播图片end -->
  	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
	<slide-announce-model ans-win-id="systemAnsWinId" index-ans="indexAns"></slide-announce-model>
	
  </body>
  
  <!-- 后台基本js -->
  <%@ include file="/sky/client/common/client.inc-js.jsp"%>
  
  <!-- 导入相应的js -->
  <script type="text/javascript" src="${ contextPath }/sky/client/business/core/js/index.js"></script>
  <script type="text/javascript" src="${ contextPath }/sky/client/business/core/js/indexHeader.js"></script>
  
</html>
