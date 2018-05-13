<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
	<%@ include file="/sky/common/client.inc.jsp"%>
    
    <title>${systemName }</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no">
    
    <!-- 导入相应的css -->
	<link rel="stylesheet" href="${ contextPath }/sky/client/business/news/css/news.css" />
	
	<!-- 导入相应的js -->
	<script type="text/javascript" src="${ contextPath }/sky/client/business/news/js/news.js"></script>
	<script type="text/javascript" src="${ contextPath }/sky/client/business/news/js/newsDetail.js"></script>
  
  	<%
	Integer type = (Integer)request.getAttribute("type");
	String code = (String)request.getAttribute("code");
	%>
	<script type="text/javascript">
	var type = <%=type%>;
	var code = '<%=code==null?"":code%>';
	</script>
  </head>
  
  <body data-ng-app="newsApp" data-ng-controller="newsCtrl">
  	<!-- 页面头部start -->
	<client-top current-nav="currentNav"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 背景图片start -->
	<bg-show bg-url="currentBgUrl" head-text="currentItem.name" height="{{currentItem.height}}"></bg-show>
  	<!-- 背景图片end -->
  	
  	<!-- 新闻模块start -->
  	<div class="news-module" data-ng-show="currentItem == null">
  		<!-- 新闻模块左边部分start -->
  		<div class="news-left">
  			<ul>
  				<li data-ng-class="{'active':item==condition.newsType}" 
  					data-ng-repeat="item in newsTypes" data-ng-click="filterType(item)">
  					<i class="fa fa-list-alt"></i>&nbsp;{{item | stringNewsType}}
  				</li>
  			</ul>
  		</div>
  		<!-- 新闻模块左边部分end -->
  		
  		<!-- 新闻模块右边部分start -->
  		<div class="news-right">
  			<div class="news-content">
  				<loading-panel data-ng-show="isLoadingNews" font-size="22"></loading-panel>
			
				<nodata-panel data-ng-show="!isLoadingNews && newsList.length==0" font-size="22"></nodata-panel>
  			
  				<div class="news-list" data-ng-show="!isLoadingNews" data-ng-repeat="item in newsList">
  					<div class="news-list-pic" style="background-image: url({{item.picPathList[0] | getImgUrl}});" data-ng-click="$root.selectedItem(item)"></div>
  					<div class="news-list-content">
  						<div>
  							<h1 title="{{item.name | showBlankValue}}" data-ng-click="$root.selectedItem(item)">
  								{{item.name | showBlankValue}}
  							</h1>
  						</div>
  						<div>{{item.newsType | stringNewsType}}&nbsp;&nbsp;&nbsp;&nbsp;{{item.createTime | formatDate}}</div>
  						<div>{{item.content | getSmallContent}}</div>
  						<div><a class="news-linked" href="javascript:void(0)" data-ng-click="$root.selectedItem(item)">查看全文</a></div>
  					</div>
  				</div>
  			</div>
  			<common-pager class="common-pager" page-count="{{condition.pageCount}}" current-page="condition.pageNo" 
				total-rows="condition.totalCount" to-page-call-back="getNewsList(pageNo)"></common-pager>
  		</div>
  		<!-- 新闻模块右边部分end -->
  	</div>
  	<!-- 新闻模块end -->
  	
  	<!-- 新闻模块详情start -->
  	<news-detail class="item-detail" data-ng-show="currentItem != null" current-item="currentItem">
  	</news-detail>
  	<!-- 新闻模块详情end -->
	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
  </body>
</html>
