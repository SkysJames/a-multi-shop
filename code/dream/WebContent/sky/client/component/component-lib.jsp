<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 这里加载common的css文件 -->
<link rel="stylesheet" href="${ contextPath }/sky/client/component/commonPager/css/commonPager.css" />
<link rel="stylesheet" href="${ contextPath }/sky/client/component/loadingPanel/css/loadingPanel.css" />
<link rel="stylesheet" href="${ contextPath }/sky/client/component/nodataPanel/css/nodataPanel.css" />
<link rel="stylesheet" href="${ contextPath }/sky/client/component/clientTop/css/clientTop.css" />
<link rel="stylesheet" href="${ contextPath }/sky/client/component/clientBottom/css/clientBottom.css" />
<link rel="stylesheet" href="${ contextPath }/sky/client/component/slideShow/css/slideShow.css" />
<link rel="stylesheet" href="${ contextPath }/sky/client/component/bgShow/css/bgShow.css" />

<!-- 这里加载common的js文件 -->
<script src="${contextPath }/sky/client/component/commonPager/js/commonPager.js"></script>
<script src="${contextPath }/sky/client/component/loadingPanel/js/loadingPanel.js"></script>
<script src="${contextPath }/sky/client/component/nodataPanel/js/nodataPanel.js"></script>
<script src="${contextPath }/sky/client/component/clientTop/js/clientTop.js"></script>
<script src="${contextPath }/sky/client/component/clientBottom/js/clientBottom.js"></script>
<script src="${contextPath }/sky/client/component/slideShow/js/slideShow.js"></script>
<script src="${contextPath }/sky/client/component/bgShow/js/bgShow.js"></script>

<!-- 独立的directive放在独立的module里面，内容简单的就方在同一个module里面 -->
<script>
$directiveList = [
	'commonPager',
	'loadingPanel',
	'nodataPanel',
	'clientTop',
	'clientBottom',
	'slideShow',
	'bgShow',
];
</script>

 
