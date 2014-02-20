<iframe  id="if${model.name}"  frameborder='0' src="web/default/baidumap.jsp?jsondata=${jsondata}" style="width:${model.paneWidth?default('100%')};"/>
<script>
	var	height = $(window).height();
	var top2 = $(".gMain").offset().top;
	$("#if${model.name}").css("height",height-top2);
</script>