<!-js before loading-->
<#if model.beforScript?exists>
	<script>
		eval(${model.beforScript});
	</script>
</#if>
 
<div id='${model.name}' class="html5-footer html5-footer-theme">
	${items_html}
</div>	
<!-js after loading-->
<#if model.afterScript?exists>
	<script>
		eval(${model.afterScript});
	</script>
</#if>

<script>
	<#if  model.resource?exists && model.resource.resourcePath?exists>
		$("#${model.name}").empty().loadDefault("${model.resource.resourcePath}","",null,true);
	</#if>
</script>