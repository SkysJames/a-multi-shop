<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 这里加载module的js文件 -->
<script src="${contextPath }/sky/server/module/majorIndex/js/majorIndex.js"></script>
<script src="${contextPath }/sky/server/module/systemManage/js/systemManage.js"></script>
<script src="${contextPath }/sky/server/module/oplogManage/js/oplogManage.js"></script>
<script src="${contextPath }/sky/server/module/visitorManage/js/visitorManage.js"></script>
<script src="${contextPath }/sky/server/module/visitorManage/js/visitorManageSave.js"></script>
<script src="${contextPath }/sky/server/module/userManage/js/userManage.js"></script>
<script src="${contextPath }/sky/server/module/userManage/js/userManageSave.js"></script>
<script>
$moduleList = [
	'majorIndex.module',
	'systemManage.module',
	'oplogManage.module',
	'visitorManage.module',
	'userManage.module',
];
</script>