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
	<client-top table-name="tb_shop" keywords="condition.keywords" selected-type="selectedType" type-list="typeList"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 系统名称，搜索框start -->
	<index-header not-index="true" table-name="tb_shop" keywords="condition.keywords" index-ans="indexAns"></index-header>
	<!-- 系统名称，搜索框end -->
	
	<!-- 分割线 start -->
	<div class="my-line"></div>
	<!-- 分割线 end -->
	
	<!-- 搜索条件start -->
	<div class="index-commodel index-condition">
		<div class="icondition-header common-padding">
			<h4>
				<span class="icondition-all" data-ng-click="selectOneType()">全部店铺</span>
				<span data-ng-show="selectedType.parentName">&nbsp;>&nbsp;{{selectedType.parentName|showBlankValue}}</span>
				<span data-ng-show="selectedType.name">&nbsp;>&nbsp;{{selectedType.name|showBlankValue}}</span>
			</h4>
		</div>
		<div class="icondition-content common-padding">
			<table>
				<tr>
					<td class="icondition-td1">一级分类</td>
					<td>
						<div class="icondition-td2" data-ng-class="{'icondition-more': isOneTypeMore}">
							<span data-ng-click="isOneTypeMore = !isOneTypeMore">
								更多&nbsp;<i class="fa" data-ng-class="{'fa-chevron-down':!isOneTypeMore, 'fa-chevron-up':isOneTypeMore}"></i>
							</span>
							<a data-ng-class="{'active': !selectedType.parentId || selectedType.parentId==''}" 
								data-ng-click="selectOneType()" href="javascript:void(0)" >全部</a>
							<a data-ng-class="{'active': selectedType.parentId && selectedType.parentId==item.id}" 
								data-ng-click="selectOneType(item)" data-ng-repeat="item in typeList" href="javascript:void(0)" >{{item.name|showBlankValue}}</a>
						</div>
					</td>
				</tr>
				<tr data-ng-show="twoTypeList && twoTypeList.length>0">
					<td class="icondition-td1">二级分类</td>
					<td>
						<div class="icondition-td2" data-ng-class="{'icondition-more': isTwoTypeMore}">
							<span data-ng-click="isTwoTypeMore = !isTwoTypeMore">
								更多&nbsp;<i class="fa" data-ng-class="{'fa-chevron-down':!isTwoTypeMore, 'fa-chevron-up':isTwoTypeMore}"></i>
							</span>
							<a data-ng-class="{'active': selectedType.id && selectedType.id==item.id}" 
								data-ng-click="selectTwoType(item)" data-ng-repeat="item in twoTypeList" href="javascript:void(0)" >{{item.name|showBlankValue}}</a>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 搜索条件end -->
	
  	<!-- 店铺列表start -->
  	<div class="index-commodel index-shop" data-ng-class="{'index-shop-show': shopList && shopList.length>0}">
  		<div class="ishop-content">
  			<ul>
  				<li data-ng-repeat="item in shopList" data-ng-click="">
  					<div class="ishop-img" style="background-image: url({{item.logoPathList|getImgByImgList}})"></div>
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
