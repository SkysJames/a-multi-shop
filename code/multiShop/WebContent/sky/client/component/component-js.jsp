<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 这里加载common的js文件 -->
<script src="${contextPath }/sky/client/component/clientTop/js/clientTop.js"></script>
<script src="${contextPath }/sky/client/component/clientBottom/js/clientBottom.js"></script>
<script src="${contextPath }/sky/client/component/slideShow/js/slideShow.js"></script>
<script src="${contextPath }/sky/client/component/slideAnnounce/js/slideAnnounce.js"></script>

<!-- 独立的directive放在独立的module里面，内容简单的就方在同一个module里面 -->
<script>
$directiveList = [
	'clientTop',
	'clientBottom',
	'slideShow',
	'slideAnnounce',
];
</script>

 
