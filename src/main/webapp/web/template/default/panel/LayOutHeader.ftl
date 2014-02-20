 <div class='gTop' id='${model.name}'>
 	<CENTER> <DIV id=outDv></div></CENTER>
	<DIV class=gTab  id=dvTab>
		<div class="oneTab on" onclick='loadWorkbench()'  tabId='workbench_container' paneId='${model.name}'  title='首页' style='WIDTH: 130px; ZOOM: 1' >
			<div class="tLe">&nbsp;</div>
			<div class="bdy" ><nobr><#if (langlocal=='zh') >&nbsp;&nbsp;&nbsp;首页 <#else>&nbsp;&nbsp;&nbsp;Home</#if></nobr></div>
			<div class="tRi">&nbsp;</div>
			<div class="btnTab"></div>
		</div>
	</DIV>
 </div>
<script>
	//加载头部
	<#if model.resource.resourcePath?exists>
	    var theUrl = "";
		if(globalURL.indexOf( '/web/default/' ) != -1){
			  theUrl = "/";
		}
		$("#outDv").empty().load(theUrl +  "${model.resource.resourcePath}?" + Math.random());
	</#if>
</script>
