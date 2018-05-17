<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sky.util.SysParameterUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

pageContext.setAttribute("contextPath", request.getContextPath());

String contextPath = request.getContextPath();

String systemName = SysParameterUtil.getStringValue("system_name", "");
pageContext.setAttribute("systemName", systemName);
%>

<script type="text/javascript">
var $contextPath = '<%=contextPath%>';
</script>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
	<%@ include file="/resource/plugin/plugin-lib.jsp"%>
    
    <title>${systemName }管理系统</title>
    
    <!-- 导入相应css -->
	<link rel="stylesheet" type="text/css" href="sky/server/business/login/css/login.css" />
	
    <!-- 导入相应js -->
	<script type="text/javascript" src="sky/server/business/login/js/login.controller.js"></script>

  </head>
  
  <body class="login-body" data-ng-app="loginApp" data-ng-controller="loginCtrl">
    <div class="login-container">
	    <div class="login-header">
	    	${systemName }管理系统
	    </div>
	    <div class="login-content">
	    	<form action="${contextPath}/home/home-login" method="post">
	    		<div class="login-error">
				    <c:if test="${resultMap.message != null}">
				    	<i class="fa fa-remove"></i>
				    	<span>${resultMap.message}</span>
				    </c:if>
			    </div>
	    		<div class="input-group">
					<span class="input-group-span">帐号</span>
					<input type="text" class="form-control input-group-in" name="loginUser.userId" placeholder="用户名" required>
				</div>
	    		<div class="input-group">
					<span class="input-group-span">密码</span>
					<input type="password" class="form-control input-group-in" name="loginUser.userPwd" placeholder="输入您的密码" required>
				</div>
				<div class="input-group">
					<button class="btn input-group-btn" type="submit">立 即 登 录</button>
				</div>
	    	</form>
	    </div>
    </div>
  </body>
</html>
