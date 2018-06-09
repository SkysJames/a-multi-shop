<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.sky.util.SysParameterUtil" %>
<%@page import="com.sky.contants.EntityContants" %>
<%@page import="com.sky.util.JsonUtil" %>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());

	//系统名称
	String systemName = SysParameterUtil.getStringValue("system_name", "");
	pageContext.setAttribute("systemName", StringUtils.isNotBlank(systemName)?systemName:"梦想蓝天");
	
	//系统图标
	String systemIcon = SysParameterUtil.getStringValue("system_icon", "");
	pageContext.setAttribute("systemIcon", StringUtils.isNotBlank(systemIcon)?systemIcon:(request.getContextPath() + "/sky/common/core/img/system_icon.ico"));
	
	//系统logo
	String systemLogo = SysParameterUtil.getStringValue("system_logo", "");
	pageContext.setAttribute("systemLogo", StringUtils.isNotBlank(systemLogo)?systemLogo:(request.getContextPath() + "/sky/common/core/img/system_logo.png"));
	
	//默认百度地图经纬度
	String defaultBmap = SysParameterUtil.getStringValue("default_bmap", "");
	pageContext.setAttribute("defaultBmap", StringUtils.isNotBlank(defaultBmap)?defaultBmap:"113.273154,23.146502");
	
	pageContext.setAttribute("opTypeMap", JsonUtil.toJson(EntityContants.OplogContants.actionMaps));
	
%>
<!-- 初始化全局参数 -->
<script type="text/javascript">
var $contextPath = '${ contextPath }';//上下文地址
var $systemName = '${ systemName }';//系统名称
var $currentUser = JSON.parse('${ JsonUtil.toJson(loginUser) }');//当前登录用户
var $defaultBmaps = '${ defaultBmap }'.split(",");//默认百度地图经纬度
var $opTypeMap = JSON.parse('${ opTypeMap }');//日志类型map
</script>

<!-- 导入外部插件 -->
<%@include file="/resource/plugin/plugin-css.jsp"%>

<!-- 导入公共的css -->
<link rel="stylesheet" href="${contextPath }/sky/common/core/css/common.css" />
<!-- 导入公共的组件css -->
<%@include file="/sky/common/component/component-css.jsp"%>

<!-- 导入模块css -->
<%@include file="/sky/server/module/module-css.jsp"%>
<!-- 导入组件css -->
<%@include file="/sky/server/component/component-css.jsp"%>
