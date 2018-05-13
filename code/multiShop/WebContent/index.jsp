<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    
  </head>
  
  <body>
    <%
    	response.sendRedirect(path + "/home/client-index");
    %>
  </body>
</html>
