<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.sky.util.SysParameterUtil" %>
<%@page import="com.sky.contants.EntityContants" %>
<%@page import="com.sky.util.JsonUtil" %>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());

	String systemName = SysParameterUtil.getStringValue("system_name", "");
	pageContext.setAttribute("systemName", systemName);
	
	pageContext.setAttribute("opTypeMap", JsonUtil.toJson(EntityContants.OplogContants.actionMaps));
	
%>

<!-- 导入外部插件 -->
<%@include file="/resource/plugin/plugin-lib.jsp"%>
<!-- 导入基础工具类 -->
<%@include file="/resource/util.js/util-lib.jsp"%>

<!-- 导入指令 -->
<%@include file="/sky/server/directive/directive-lib.jsp"%>

<!-- 导入相应的js -->
<script type="text/javascript" src="${ contextPath }/sky/server/business/core/js/canvas-bg.js"></script>
<script type="text/javascript" src="${ contextPath }/sky/server/business/core/js/server-index.filter.js"></script>
<script type="text/javascript" src="${ contextPath }/sky/server/business/core/js/server-index.httpService.js"></script>

<!-- 初始化全局参数 -->
<script type="text/javascript">
var $contextPath = '${ contextPath }';//上下文地址
var $currentUserId = '${ loginUser.userId }';//当前登录用户ID
var $opTypeMap = JSON.parse('${ opTypeMap }');//日志类型map
var $qq = '';//qq号码
</script>

<!-- 导入相应的css -->
<link rel="stylesheet" href="${ contextPath }/sky/common/css/common.css" />

<!-- 导入相应的js -->
<script type="text/javascript" src="${ contextPath }/sky/common/js/common.js"></script>






