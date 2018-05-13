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
	<link rel="stylesheet" href="${ contextPath }/sky/client/business/contact/css/contact.css" />
	
	<!-- 导入相应的js -->
	<script type="text/javascript" src="${ contextPath }/sky/client/business/contact/js/contact.js"></script>
  </head>
  
  <body data-ng-app="contactApp" data-ng-controller="contactCtrl">
  	<!-- 页面头部start -->
	<client-top current-nav="currentNav"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 背景图片start -->
	<bg-show bg-url="currentBgUrl" height="450px"></bg-show>
  	<!-- 背景图片end -->
  	
  	<!-- 联系模块start -->
  	<div class="contact-module">
  		<p>公司：{{companyName|showBlankValue}}</p>
  		<p>地址：{{companyAddress|showBlankValue}}</p>
  		<p>电话：{{phone|showBlankValue}}</p>
  		<p>邮箱：{{email|showBlankValue}}</p>
  		<p>Q&nbsp;Q：{{qq|showBlankValue}}<a href="{{qqContactUrl}}" target="_blank"><i class="sky-icon qq"></i>点击交谈</a></p>
  		<p>
  			<div class="wechat">
				<img alt="" data-ng-src="{{wechatUrl}}">
				<div class="wechat-right">
					<i class="fa fa-wechat"></i>
					<p>扫二维码关注</p>
				</div>
			</div>
  		</p>
  	</div>
  	<!-- 联系模块end -->
  	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
  </body>
</html>
