<!-js before loading-->
<#if model.beforScript?exists>
	<script>
		eval(${model.beforScript});
	</script>
</#if>
 
<div id='${model.name}' class="html5-content html5-content-all html5-content-theme">
	<div id='html5-workbench' class='html5-scrollcontent html5-noscrollbar wh100 margintop3px bgpiczhiliaoshu'>
	${items_html}
	</div>
</div>
<!-js after loading-->
<#if model.afterScript?exists>
	<script>
		eval(${model.afterScript});
	</script>
</#if>
<script>
	<#if  model.resource?exists && model.resource.resourcePath?exists>
		$("#${model.name}").empty().loadDefault("${model.resource.resourcePath}");
	</#if>
</script>