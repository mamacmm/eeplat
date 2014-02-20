<iframe  id="if${model.name}"  frameborder='0'  src="exedo/webv3/report.jsp?gridModelUid=${model.objUid}&type=html&filename=${model.l10n}"  style="width:${model.containerPane.paneWidth?default('100%')};">
</iframe>

<script>
	
	$("#if${model.name}").load(function(){
		var mainheight = $(this).contents().find("body").height()+30;
		$(this).height(mainheight);
	}); 
	
	
	function jasper4HTML()
	{
		$("#if${model.name}").attr("src","exedo/webv3/report.jsp?gridModelUid=${model.objUid}&type=html&filename=${model.l10n}");
	}
	
	function jasper4PDF()
	{
		$("#if${model.name}").attr("src","exedo/webv3/report.jsp?gridModelUid=${model.objUid}&type=pdf&filename=${model.l10n}");
	}
	
	function jasper4XLS()
	{
		$("#if${model.name}").attr("src","exedo/webv3/report.jsp?gridModelUid=${model.objUid}&type=xls&filename=${model.l10n}");
	}
	
</script>


