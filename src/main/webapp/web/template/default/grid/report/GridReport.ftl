<iframe  id="if${model.name}"  frameborder='0'  src="web/default/report.jsp?gridModelUid=${model.objUid}"  style="width:${model.containerPane.paneWidth?default('100%')};">
</iframe>

<script>
	var	height = $(window).height();
	var top2 = $(".gMain").offset().top;
	$("#if${model.name}").css("height",height-top2);
</script>


