<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  
  	<!-- 前端基本css -->
	<%@ include file="/sky/client/common/client.inc-css.jsp"%>
	
	<!-- 导入系统图标 -->
    <link rel="icon" href="${systemIcon }" type="image/x-icon">
  
    <title>${systemName }</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no">
    
    <%
	String keywords = (String)request.getAttribute("keywords");
	String type = (String)request.getAttribute("type");
	%>
	<script type="text/javascript">
	var keywords = '<%=keywords==null?"":keywords%>';
	var type = '<%=type==null?"":type%>';
	</script>
  </head>
  
  <body data-ng-app="shopSearchApp" data-ng-controller="shopSearchCtrl">
  	<!-- 页面头部start -->
	<client-top table-name="tb_shop" type-list="oneTypeList"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 系统名称，搜索框start -->
	<index-header key-type="shop" keywords="condition.keywords" index-ans="indexAns"></index-header>
	<!-- 系统名称，搜索框end -->
	
	<!-- 搜索条件start -->
	<div class="index-commodel index-condition">
		<div class="icondition-header">
			<h2>
				全部
				<span>&nbsp;>&nbsp;</span>
			</h2>
			<div class="icondition-content">
				<table>
					<tr>
						<td class="icondition-td1">一级分类</td>
						<td class="icondition-td2">
							<a data-ng-class="{'active': !condition.shopType || condition.shopType==''}" href="javascript:void(0)" >全部</a>
						</td>
					</tr>
					<tr>
						<td>二级分类</td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- 搜索条件end -->
	
  	<!-- 店铺列表start -->
  	<div class="index-commodel index-shop" data-ng-class="{'index-shop-show': shopList && shopList.length>0}">
  		<div class="ishop-content">
  			<ul>
  				<li data-ng-repeat="item in shopList" data-ng-click="">
  					<div class="ishop-img" style="background-image: url({{item.picPathList|getImgByImgList}})"></div>
  					<div class="ishop-con">
  						<h3 title="{{item.name | showBlankValue}}">{{item.name | showBlankValue}}</h3>
  						<p title="简介：{{item.brief | showBlankValue}}">简介：{{item.brief | showBlankValue}}</p>
  						<p title="评分：{{item.mark}}分">
  							评分：{{item.mark}}分
  							<label title="人气{{item.popularity}}"><i class="fa fa-heart"></i>&nbsp;{{item.popularity}}</label>
  						</p>
  						<p title="地址：{{item.address | showBlankValue}}">地址：{{item.address | showBlankValue}}</p>
  					</div>
  				</li>
  			</ul>
  		</div>
  	</div>
	<nodata-panel data-ng-show="!isLoadingShop && shopList.length==0" font-size="18"></nodata-panel>
	<loading-panel data-ng-show="isLoadingShop" font-size="18"></loading-panel>
  	<!-- 店铺列表end -->
  	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
	<!-- 公告消息模态框start -->
	<slide-announce-model ans-win-id="systemAnsWinId" index-ans="indexAns"></slide-announce-model>
	<!-- 公告消息模态框end -->
	
  </body>
  
  <!-- 后台基本js -->
  <%@ include file="/sky/client/common/client.inc-js.jsp"%>
  
  <!-- 导入相应的js -->
  <script type="text/javascript" src="${ contextPath }/sky/client/business/shopSearch/js/shopSearch.js"></script>
  
</html>
