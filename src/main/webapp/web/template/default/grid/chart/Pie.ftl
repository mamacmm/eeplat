<div id="${model.objUid}">
 
</div>
<script language="JavaScript">					
			var chart1 = new FusionCharts("/${webmodule}/plugin/FusionChartsFree/FCF_Pie3D.swf", "chart_pie3d", "450", "300", "0", "1");		   			
			chart1.setDataXML("${gXml}");
			chart1.render("${model.objUid}");
</script>