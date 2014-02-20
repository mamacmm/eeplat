<!-js before loading-->
<#if model.beforScript?exists>
	<script>
		eval(${model.beforScript});
	</script>
</#if>
 
<div id='${model.name}' class="html5-page">
	${items_html}
</div>	
<!-js after loading-->
<#if model.afterScript?exists>
	<script>
		eval(${model.afterScript});
	</script>
</#if>