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
	<link rel="stylesheet" href="${ contextPath }/sky/client/business/about/css/about.css" />
	
	<!-- 导入相应的js -->
	<script type="text/javascript" src="${ contextPath }/sky/client/business/about/js/about.js"></script>
  </head>
  
  <body data-ng-app="aboutApp" data-ng-controller="aboutCtrl">
  	<!-- 页面头部start -->
	<client-top current-nav="currentNav"></client-top>
  	<!-- 页面头部end -->
	
	<!-- 背景图片start -->
	<bg-show bg-url="currentBgUrl"></bg-show>
  	<!-- 背景图片end -->
  	
  	<!-- 我们是谁start -->
  	<div class="about-who" id="about-who">
  		<div class="about-content">
  			<div class="about-title">
	  			<h1>我们是谁</h1>
  			</div>
  			<p>『梦想蓝天网络科技』 主要以互联网基础服务、系统管理网站服务为主导。</p>
  			<p>服务项目包括域名注册、 虚拟主机、网站建设、系统管理、课程设计、微信小程序等，以帮助客户轻松、高速、 高效的应用互联网，提高企业竞争能力。</p>
  			<p>『梦想蓝天网络科技』正在以创新、引领互联网科技前沿作为自身进步的灵魂和事业兴旺发达的不懈动力，积极建立适应知识经济、技术创新的机制与经营方式，全力向技术产业方面发展。</p>
  			<p>团队有多年的互联网基础应用服务运营经验，凭借优秀的运营管理队伍、业界资深技术工程师，强大的资金、技术、电信资源的优势，为国内外的企业和个人用户提供优秀的互联网服务，并吸引了国内外优秀的知名企业于我们合作，共同致力于互联网应用服务。不断推陈出新是我们企业的源动力，令用户百分百满意的服务是我们团队中每一个人牢记在心的理念。『梦想蓝天网络科技』与广大同仁一道，全力打造互联网基础应用知名品牌！</p>
  			<p>『梦想蓝天网络科技』始终秉持“客户为尊，永续创新”的服务宗旨，服务质量在业界有口皆碑。公司的经营理念是“成功源于共享，合作成就未来”，凭借优秀的团队及强大的资金、技术等资源优势，致力于为全球的中小企业、商务人士提供稳定高效、极富价值和乐趣的互联网应用服务。</p>
  		</div>
  		<div class="about-bottom">
  			<dl data-ng-repeat="whoImg in whoImgs">
  				<dd>
  					<img alt="" data-ng-src="{{whoImg.imgUrl}}">
  				</dd>
  				<dt>
  					<span class="about-bottom-title">{{whoImg.title1}}</span>
  				</dt>
  				<dt>
  					<span class="about-bottom-title">{{whoImg.title2}}</span>
  				</dt>
  			</dl>
  		</div>
  	</div>
  	<!-- 我们是谁end -->
  	
  	<!-- 服务优势start -->
  	<div class="about-service" id="about-service">
  		<div class="about-content">
  			<div class="about-title">
	  			<h1>服务优势</h1>
	  			<span>创意、流程、执行缺一不可</span>
  			</div>
  		</div>
  		<div class="about-bottom">
  			<dl data-ng-repeat="serviceImg in serviceImgs">
  				<dd>
  					<img alt="" data-ng-src="{{serviceImg.imgUrl}}">
  				</dd>
  				<dt class="text-left">
  					<span class="about-bottom-content">{{serviceImg.content}}</span>
  				</dt>
  			</dl>
  		</div>
  	</div>
  	<!-- 服务优势end -->
  	
  	<!-- 我们的团队start -->
  	<div class="about-team" id="about-team">
  		<div class="about-content">
  			<div class="about-title">
	  			<h1>我们的团队</h1>
  			</div>
  			<p>我们是追求品质与力求不断超越自己的团队，每一位成员也是亲密的伙伴，与公司一起成长与发展。我们尊重每次合作的机会与挑战，不断精进，力求完美。团队秉承专注、专业的设计服务思维，让客户通过设计发挥产品的最大价值，并发掘无限的可能。热爱设计并坚信设计的力量让我们不断前进…</p>
  		</div>
  		<div class="about-bottom">
  			<dl data-ng-repeat="teamImg in teamImgs">
  				<dd>
  					<img alt="" data-ng-src="{{teamImg.imgUrl}}">
  				</dd>
  			</dl>
  		</div>
  	</div>
  	<!-- 我们的团队end -->
  	
	<!-- 页面底部start -->
	<client-bottom></client-bottom>
	<!-- 页面底部end -->
	
  </body>
</html>
