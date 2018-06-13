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
	String productId = (String)request.getAttribute("productId");
	%>
	<script type="text/javascript">
	var productId = '<%=productId==null?"":productId%>';
	</script>
  </head>
  
  <body id="productIndexId" data-ng-app="productIndexApp" data-ng-controller="productIndexCtrl">
  	<!-- 页面头部start -->
	<client-top table-name="tb_product" shop-info="shopInfo"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 头部店铺名称start -->
	<product-header shop-info="shopInfo"></product-header>
	<!-- 头部店铺名称end -->
	
	<!-- 商品信息部分start -->
	<div class="pindex-container">
		<!-- PC端的商品基本信息start -->
		<div class="pindex-basic shop-only-pc">
			<div class="pindex-left">
				<div class="pindex-imgbig" style="background-image: url({{selectedImg|getImgUrl}})"></div>
				<div class="pindex-imgs" data-ng-show="!productInfo.picPathList || productInfo.picPathList.length==0">
					<ul>
						<li><img alt="暂无图片" data-ng-src="{{null|getImgUrl}}"></li>
					</ul>
				</div>
				<div class="pindex-imgs" data-ng-show="productInfo.picPathList && productInfo.picPathList.length>0">
					<ul>
						<li data-ng-repeat="item in productInfo.picPathList" data-ng-class="{'active': selectedImg==item}" 
							data-ng-mouseover="selectImg(item)"><img style="background-image: url({{item|getImgUrl}})"></li>
					</ul>
				</div>
				<div class="pindex-share">
					<a href="javascript:void(0)" data-ng-class="{'active': productHeaderScope.collectedObj}" data-ng-click="productHeaderScope.toggleCollect()">
						<i class="fa fa-star"></i>&nbsp;{{productHeaderScope.collectedObj?"已收藏":"收藏商品"}}
					</a>
					<a href="javascript:void(0)"><i class="fa fa-share-alt"></i>&nbsp;分享</a>
				</div>
			</div>
			<div class="pindex-right">
				<h4>{{productInfo.name}}</h4>
				<p data-ng-show="productInfo.brief && productInfo.brief.length>0">{{productInfo.brief}}</p>
				<div class="pindex-row price-row">
					<div class="pindex-td">价格</div>
					<div class="pindex-td pindex-price"><i class="fa fa-rmb"></i>&nbsp;<span>{{productInfo.price}}</span></div>
				</div>
				<div class="pindex-row">
					<div class="pindex-td">浏览量</div>
					<div class="pindex-td">{{productInfo.clickCount}}</div>
				</div>
				<div class="pindex-row">
					<div class="pindex-td">库存</div>
					<div class="pindex-td">{{productInfo.proStock}}</div>
				</div>
				<div class="pindex-row">
					<a href="javascript:void(0)" data-ng-click="toShopPage()">点击进入店铺</a>
				</div>
				<div class="pindex-row">
					<button data-ng-show="productInfo.status==2"><i class="fa fa-cart-plus"></i>&nbsp;加入购物车</button>
					<button data-ng-show="productInfo.status!=2" class="pro-down">商品已下架</button>
				</div>
			</div>
		</div>
		<!-- PC端的商品基本信息end -->
		
		<!-- 手机端的商品基本信息start -->
		<div class="pindex-basic shop-only-phone">
			<div class="pindex-phone-imgs">
				<ul>
					<li data-ng-repeat="item in productInfo.picPathList" data-ng-class="{'active': selectedImg==item}">
						<img style="background-image: url({{item|getImgUrl}})">
					</li>
				</ul>
			</div>
			<div class="pindex-phone-info">
				<h4>{{productInfo.name}}</h4>
				<small>
					<span>浏览量&nbsp;{{productInfo.clickCount}}</span>
					<span>库存&nbsp;{{productInfo.proStock}}</span>
				</small>
				<div class="pindex-price">
					<i class="fa fa-rmb"></i>&nbsp;<span>{{productInfo.price}}</span>
					<div class="pull-right">
						<button data-ng-show="productInfo.status==2"><i class="fa fa-cart-plus"></i>&nbsp;加入购物车</button>
						<button data-ng-show="productInfo.status!=2" class="pro-down">商品已下架</button>
					</div>
				</div>
				<p><a href="javascript:void(0)" data-ng-click="toShopPage()">点击进入店铺</a></p>
				<p data-ng-show="productInfo.brief && productInfo.brief.length>0">简介：&nbsp;{{productInfo.brief}}</p>
			</div>
		</div>
		<!-- 手机端的商品基本信息end -->
		
		<!-- 商品详情start -->
		<div class="pindex-description" data-ng-show="productInfo.description && productInfo.description.length>0">
			<h4>详情描述</h4>
			<div data-ng-bind-html="sce.trustAsHtml(productInfo.description)"></div>	
		</div>
		<!-- 商品详情end -->
	</div>
	<!-- 商品信息部分end -->
	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
  </body>
  
  <!-- 后台基本js -->
  <%@ include file="/sky/client/common/client.inc-js.jsp"%>
  
  <!-- 导入相应的js -->
  <script type="text/javascript" src="${ contextPath }/sky/client/business/productIndex/js/productIndex.js"></script>
  
</html>
