<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 

<!-- jquery -->
<script type="text/javascript" src="${ contextPath }/resource/plugin/jquery/jquery-1.12.3.js"></script>

<!-- bootstrap -->
<script type="text/javascript" src="${ contextPath }/resource/plugin/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${ contextPath }/resource/plugin/bootstrap/css/bootstrap.min.css" />

<!-- font-awesome -->
<link rel="stylesheet" href="${ contextPath }/resource/plugin/font-awesome-4.7.0/css/font-awesome.min.css" />

<!-- angular -->
<%@include file="/resource/plugin/angular/lib.jsp" %>
<%@include file="/resource/plugin/angularui-select2/lib.jsp" %>

<!-- dropzone图片上传工具 -->
<%@include file="/resource/plugin/dropzone/lib.jsp" %>

<!-- smart-controls -->
<%@include file="/resource/plugin/smart-controls/smart-grid/lib.jsp" %>
<%@include file="/resource/plugin/smart-controls/smart-notification/lib.jsp" %>

<!-- daterangepicker日期选择器 -->
<%@include file="/resource/plugin/daterangepicker/lib.jsp" %>

<!-- lodash -->
<%@include file="/resource/plugin/lodash/lib.jsp" %>

<!-- slider轮播 -->
<%@include file="/resource/plugin/slider/lib.jsp" %>

<!-- kindeditor -->
<%@include file="/resource/plugin/kindeditor/lib.jsp" %>


