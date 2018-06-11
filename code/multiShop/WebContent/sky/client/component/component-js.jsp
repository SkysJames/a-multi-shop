<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 这里加载common的js文件 -->
<script src="${contextPath }/sky/client/component/indexHeader/js/indexHeader.js"></script>
<script src="${contextPath }/sky/client/component/clientTop/js/clientTop.js"></script>
<script src="${contextPath }/sky/client/component/clientBottom/js/clientBottom.js"></script>
<script src="${contextPath }/sky/client/component/slideShow/js/slideShow.js"></script>
<script src="${contextPath }/sky/client/component/slideAnnounce/js/slideAnnounce.js"></script>
<script src="${contextPath }/sky/client/component/shopHeader/js/shopHeader.js"></script>
<script src="${contextPath }/sky/client/component/shopNav/js/shopNav.js"></script>
<script src="${contextPath }/sky/client/component/productHeader/js/productHeader.js"></script>

<!-- 独立的directive放在独立的module里面，内容简单的就方在同一个module里面 -->
<script>
$directiveList = [
	'indexHeader',
	'clientTop',
	'clientBottom',
	'slideShow',
	'slideAnnounce',
	'shopHeader',
	'shopNav',
	'productHeader',
];
</script>

 
