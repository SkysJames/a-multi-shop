<%@page contentType="text/html; charset=UTF-8"%>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
%>
<!-- The daterangepicker styles -->
<link rel="stylesheet" href="${contextPath}/resource/plugin/daterangepicker/css/daterangepicker.css" type="text/css">
<link rel="stylesheet" href="${contextPath}/resource/plugin/daterangepicker/css/style.css" type="text/css" />

<!-- The daterangepicker js -->
<script type="text/javascript" src="${contextPath}/resource/plugin/daterangepicker/js/moment.min.js"></script>
<script type="text/javascript" src="${contextPath}/resource/plugin/daterangepicker/js/daterangepicker.js"></script>

<script type="text/javascript">
//今天日期字符串YYYY-MM-DD
var date = new Date();
var thisToday = "YYYY-MM-DD";
thisToday = thisToday.replace(/YYYY/,date.getFullYear());
thisToday = thisToday.replace(/MM/,date.getMonth()>=9?(date.getMonth() + 1):'0' + (date.getMonth() + 1));   
thisToday = thisToday.replace(/DD/,date.getDate()>9?date.getDate().toString():'0' + date.getDate());   

/**
 * 默认的日期对象
 */
var defaultDateRangePickerOptions = {
	locale :{
		applyLabel		: '确定',
		cancelLabel		: '取消',
		format			: "YYYY-MM-DD HH:mm:ss",
		separator		: ' ~ ',
		daysOfWeek		: ['日','一','二','三','四','五','六'],
		monthNames		: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		customRangeLabel: '自定义时间',
	},
	timePicker : true,
	timePicker24Hour : true,
	timePickerSeconds : true,
	"alwaysShowCalendars" : true,
	"showCustomRangeLabel" : false,
	"ranges" : {
		"今天" : [
		        moment(thisToday+" 00:00:00"),
		        moment(thisToday+" 23:59:59"),
		        ],
        "过去一周" : [
                moment(thisToday+" 23:59:59").subtract(7, 'days'),
                moment(thisToday+" 23:59:59"),
                ],
        "过去一月" : [
                moment(thisToday+" 23:59:59").subtract(moment().daysInMonth()-1, 'days'),
                moment(thisToday+" 23:59:59"),
                ],
	},
};	
</script>