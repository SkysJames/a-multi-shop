<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 这里加载directive的css文件 -->
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/common/css/common-directive.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/common/css/imageDropzone.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/common/css/imagePanel.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/common/css/nodataPanel.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/common/css/loadingPanel.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/common/css/commonPager.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/common/css/personPanel.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/majorIndex/css/majorIndex.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/newsManage/css/newsManage.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/productManage/css/productManage.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/visitorManage/css/visitorManage.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/oplogManage/css/oplogManage.css" />
<link rel="stylesheet" href="${ contextPath }/sky/server/directive/userManage/css/userManage.css" />

<!-- 这里加载directive的js文件 -->
<script src="${contextPath }/sky/server/directive/common/js/imageDropzone.directive.js"></script>
<script src="${contextPath }/sky/server/directive/common/js/imagePanel.directive.js"></script>
<script src="${contextPath }/sky/server/directive/common/js/nodataPanel.directive.js"></script>
<script src="${contextPath }/sky/server/directive/common/js/loadingPanel.directive.js"></script>
<script src="${contextPath }/sky/server/directive/common/js/commonPager.directive.js"></script>
<script src="${contextPath }/sky/server/directive/common/js/personPanel.directive.js"></script>
<script src="${contextPath }/sky/server/directive/majorIndex/js/majorIndex.directive.js"></script>
<script src="${contextPath }/sky/server/directive/newsManage/js/newsManage.directive.js"></script>
<script src="${contextPath }/sky/server/directive/newsManage/js/newsManageSave.directive.js"></script>
<script src="${contextPath }/sky/server/directive/productManage/js/productManage.directive.js"></script>
<script src="${contextPath }/sky/server/directive/productManage/js/productManageSave.directive.js"></script>
<script src="${contextPath }/sky/server/directive/visitorManage/js/visitorManage.directive.js"></script>
<script src="${contextPath }/sky/server/directive/visitorManage/js/visitorManageSave.directive.js"></script>
<script src="${contextPath }/sky/server/directive/oplogManage/js/oplogManage.directive.js"></script>
<script src="${contextPath }/sky/server/directive/userManage/js/user/userManage.directive.js"></script>
<script src="${contextPath }/sky/server/directive/userManage/js/user/userManageSave.directive.js"></script>
<script src="${contextPath }/sky/server/directive/userManage/js/department/departmentManage.directive.js"></script>
<script src="${contextPath }/sky/server/directive/userManage/js/department/departmentManageSave.directive.js"></script>
<script src="${contextPath }/sky/server/directive/userManage/js/rightGroup/rightGroupManage.directive.js"></script>
<script src="${contextPath }/sky/server/directive/userManage/js/rightGroup/rightGroupManageSave.directive.js"></script>

<!-- 独立的directive放在独立的module里面，内容简单的就方在同一个module里面 -->
<script>
$directiveList = [
	'ui.select2',
	'daterangepicker',
	'imageDropzone',
	'imagePanel',
	'nodataPanel',
	'loadingPanel',
	'commonPager',
	'personPanel',
	'majorIndex',
	'newsManage',
	'newsManageSave',
	'productManage',
	'productManageSave',
	'visitorManage',
	'visitorManageSave',
	'oplogManage',
	'userManage',
	'userManageSave',
	'departmentManage',
	'departmentManageSave',
	'rightGroupManage',
	'rightGroupManageSave',
];
</script>

 
