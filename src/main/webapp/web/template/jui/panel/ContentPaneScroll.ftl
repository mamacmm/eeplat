 <#if model.beforScript?exists>
	<script>
	    eval(${model.beforScript});
	</script>
 </#if>
<div id="${model.name}">
	<#if model.navigationTxt?exists>
		<div class='navcontainer'>
		    <div class='navleft'>
		       ${model.navigationTxt}
		    </div>
		 </div>
	 </#if>
 	${items_html}
</div>
 <#if model.afterScript?exists>
	<script>
	    eval(${model.afterScript});
	 </script>
 </#if>