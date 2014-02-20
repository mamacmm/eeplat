<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=3edc2ee9a23f0512dcee41d7910f15e4"></script>
<title>EEPlat地图</title>
<style type="text/css">
body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
</style>
<%
  String jsonData = request.getParameter("jsondata");

  System.out.println("JSONData:::" + jsonData);

%>

</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">


var myData = eval(<%=jsonData%>) ;



// 百度地图API功能
var map = new BMap.Map("allmap");            // 创建Map实例
var point = new BMap.Point(103.6, 31.5);    // 创建点坐标
map.centerAndZoom(point,10);                     // 初始化地图,设置中心点坐标和地图级别。
map.enableScrollWheelZoom();                            //启用滚轮放大缩小


var opts = {
  position : point,    // 指定文本标注所在的地理位置
  offset   : new BMap.Size(30, -30)    //设置文本偏移量
 
}
var label = new BMap.Label("震中", opts);  // 创建文本标注对象
	label.setStyle({
		 color : "red",
		 fontSize : "20px",
		 height : "25px",
		 lineHeight : "25px",
		 fontFamily:"微软雅黑"
	 });
map.addOverlay(label); 
	 
if(myData.length > 0){
	if(myData[0].lat){
		for(var i = 0 ; i < myData.length; i++){
			var mylabel = new BMap.Label(myData[i].caption, 
					{
						position : new BMap.Point(myData[i].lan, myData[i].lat),
						offerset : new BMap.Size(30, -30) 
					});  // 创建文本标注对象
			mylabel.setStyle({
				 color : "blue",
				 borderColor:"blue",
				 fontSize : "12px",
				 height : "20px",
				 lineHeight : "20px",
				 fontFamily:"微软雅黑"
			 });
			map.addOverlay(mylabel);					
			
		}
	}
}	 
	 
</script>
