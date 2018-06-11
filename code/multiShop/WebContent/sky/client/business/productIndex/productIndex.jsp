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
	String productId = (String)request.getAttribute("productId");
	%>
	<script type="text/javascript">
	var productId = '<%=productId==null?"":productId%>';
	</script>
  </head>
  
  <body id="productIndexId" data-ng-app="productIndexApp" data-ng-controller="productIndexCtrl">
  	<!-- 页面头部start -->
	<client-top table-name="tb_product" shop-info="shopInfo"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 头部店铺名称start -->
	<product-header shop-info="shopInfo"></product-header>
	<!-- 头部店铺名称end -->
	
	<!-- 商品信息部分start -->
	<div class="pindex-container">
		
	</div>
	<!-- 商品信息部分end -->
	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
  </body>
  
  <!-- 后台基本js -->
  <%@ include file="/sky/client/common/client.inc-js.jsp"%>
  
  <!-- 导入相应的js -->
  <script type="text/javascript" src="${ contextPath }/sky/client/business/productIndex/js/productIndex.js"></script>
  
</html>
