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
	String shopId = (String)request.getAttribute("shopId");
	Boolean shopAbout = (Boolean)request.getAttribute("shopAbout");
	%>
	<script type="text/javascript">
	var shopId = '<%=shopId==null?"":shopId%>';
	var shopAbout = <%=shopAbout%>;
	</script>
  </head>
  
  <body id="shopIndexId" data-ng-app="shopIndexApp" data-ng-controller="shopIndexCtrl">
  	<!-- 页面头部start -->
	<client-top table-name="tb_product" shop-info="shopInfo"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 店铺名称，搜索框start -->
	<shop-header shop-info="shopInfo" keywords="keywords" index-ans="indexAns"></shop-header>
	<!-- 店铺名称，搜索框end -->
	
	<!-- 路由部分start -->
	<ng-view></ng-view>
	<!-- 路由部分end -->
	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
	<!-- 公告消息模态框start -->
	<slide-announce-model ans-win-id="shopAnsWinId" index-ans="indexAns"></slide-announce-model>
	<!-- 公告消息模态框end -->
	
  </body>
  
  <!-- 后台基本js -->
  <%@ include file="/sky/client/common/client.inc-js.jsp"%>
  
  <!-- 导入相应的js -->
  <script type="text/javascript" src="${ contextPath }/sky/client/business/shopIndex/js/shopIndex.js"></script>
  <script type="text/javascript" src="${ contextPath }/sky/client/business/shopIndex/js/indexPage.js"></script>
  <script type="text/javascript" src="${ contextPath }/sky/client/business/shopIndex/js/aboutPage.js"></script>
  <script type="text/javascript" src="${ contextPath }/sky/client/business/shopIndex/js/evaluatePage.js"></script>
  
</html>
