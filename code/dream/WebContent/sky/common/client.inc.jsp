<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.sky.util.SysParameterUtil" %>
<%@page import="com.sky.contants.EntityContants" %>
<%@page import="com.sky.util.JsonUtil" %>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());

	String systemName = SysParameterUtil.getStringValue("system_name", "");
	pageContext.setAttribute("systemName", systemName);
	
	String systemSubname = SysParameterUtil.getStringValue("system_subname", "");
	pageContext.setAttribute("systemSubname", systemSubname);
	
	String companyName = SysParameterUtil.getStringValue("company_name", "");
	pageContext.setAttribute("companyName", companyName);
	
	String companyAddress = SysParameterUtil.getStringValue("company_address", "");
	pageContext.setAttribute("companyAddress", companyAddress);
	
	String companyRecord = SysParameterUtil.getStringValue("company_record", "");
	pageContext.setAttribute("companyRecord", companyRecord);
	
	String copyRight = SysParameterUtil.getStringValue("copy_right", "");
	pageContext.setAttribute("copyRight", copyRight);
	
	String telephone = SysParameterUtil.getStringValue("telephone", "");
	pageContext.setAttribute("telephone", telephone);
	
	String phone = SysParameterUtil.getStringValue("phone", "");
	pageContext.setAttribute("phone", phone);
	
	String email = SysParameterUtil.getStringValue("email", "");
	pageContext.setAttribute("email", email);
	
	String qq = SysParameterUtil.getStringValue("qq", "");
	pageContext.setAttribute("qq", qq);
	
	String serviceTime = SysParameterUtil.getStringValue("service_time", "");
	pageContext.setAttribute("serviceTime", serviceTime);
	
%>

<!-- 导入外部插件 -->
<%@include file="/resource/plugin/plugin-lib.jsp"%>
<!-- 导入基础工具类 -->
<%@include file="/resource/util.js/util-lib.jsp"%>

<!-- 导入部件 -->
<%@include file="/sky/client/component/component-lib.jsp"%>

<!-- 导入相应的css文件 -->
<link rel="stylesheet" href="${ contextPath }/sky/client/business/core/css/common-index.css" />

<!-- 导入相应的js -->
<script type="text/javascript" src="${ contextPath }/sky/client/business/core/js/client-index.filter.js"></script>
<script type="text/javascript" src="${ contextPath }/sky/client/business/core/js/client-index.httpService.js"></script>

<!-- 初始化全局参数 -->
<script type="text/javascript">
var $contextPath = '${ contextPath }';//上下文地址
var $systemName = '${ systemName }';//系统名称
var $systemSubname = '${ systemSubname }';//系统副标题
var $companyName = '${ companyName }';//公司名称
var $companyAddress = '${ companyAddress }';//公司地址
var $companyRecord = '${ companyRecord }';//公司备案号
var $copyRight = '${ copyRight }';//版权有效期
var $telephone = '${ telephone }';//联系手机
var $phone = '${ phone }';//联系电话
var $email = '${ email }';//邮箱地址
var $qq = '${ qq }';//qq号码
var $serviceTime = '${ serviceTime }';//服务时间
</script>

<!-- 导入相应的css -->
<link rel="stylesheet" href="${ contextPath }/sky/common/css/common.css" />

<!-- 导入相应的js -->
<script type="text/javascript" src="${ contextPath }/sky/common/js/common.js"></script>

