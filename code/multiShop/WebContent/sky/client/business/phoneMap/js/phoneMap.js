/**
 * 获取url的参数
 * @returns
 */
function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串   
	var theRequest = new Object();   
	if (url.indexOf("?") != -1) {   
		var str = url.substr(1);   
		strs = str.split("&");   
		for(var i = 0; i < strs.length; i ++) {   
			theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]);   
		}   
	}   
	return theRequest;   
}

//url参数对象
var reqObj = GetRequest();
//标题
var title = reqObj.title?reqObj.title:"";
//内容
var content = reqObj.content?reqObj.content:"";
//经度
var lng = reqObj.lng?reqObj.lng:"113.383897";
//纬度
var lat = reqObj.lat?reqObj.lat:"23.13188";

//设置标题
$(".pmap-header h4").text(title);
$("title").text(title);

//百度地图
var pmap = new BMap.Map("phoneMap");

//增加比例控件
pmap.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT,offset: new BMap.Size(10,10)}));
//增加缩放控件
pmap.addControl(new BMap.ZoomControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, offset: new BMap.Size(20, 20)}));


//店铺的定位图标
var point = new BMap.Point(lng, lat);
var marker = new BMap.Marker(point);
pmap.centerAndZoom(point, 14);
pmap.addOverlay(marker);

////图标的点击事件
//marker.addEventListener("click", function(){
//	var infoWindow = new BMap.InfoWindow(content, {title:title});
//	pmap.openInfoWindow(infoWindow, point);
//});
