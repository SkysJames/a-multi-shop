<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 这里加载common的js文件 -->
<script src="${contextPath }/sky/common/component/commonPager/js/commonPager.js"></script>
<script src="${contextPath }/sky/common/component/loadingPanel/js/loadingPanel.js"></script>
<script src="${contextPath }/sky/common/component/nodataPanel/js/nodataPanel.js"></script>
<script src="${contextPath }/sky/common/component/imageDropzone/js/imageDropzone.js"></script>
<script src="${contextPath }/sky/common/component/imagePanel/js/imagePanel.js"></script>

<!-- 独立的directive放在独立的module里面，内容简单的就方在同一个module里面 -->
<script>
$commonDirectiveList = [
	'ui.select2',
	'daterangepicker',
	'commonPager',
	'loadingPanel',
	'nodataPanel',
	'imageDropzone',
	'imagePanel',
];
</script>

 
