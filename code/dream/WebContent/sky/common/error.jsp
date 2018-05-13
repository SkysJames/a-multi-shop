<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ERROR PAGE</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <p style="font-size: 30px;font-weight: 600;color: blue;">This is a error page. </p>
	<p style="font-size: 20px;font-weight: 500;color: red;">这是一个错误页面，系统内部错误了！<br>
  </body>
</html>
