 <!-js befor loading-->
 <#if model.beforScript?exists>
	<script>
	    eval(${model.beforScript});
	</script>
 </#if>
 <#assign divClass = "row-fluid"/>
  <#if model.parent?exists >
  	<#assign divClass = "span12"/>
 </#if>
 
 <div id='${model.name}' class="${divClass}">
	${items_html}
 </div>	
 <!-js after loading-->
 <#if model.afterScript?exists>
	<script>
	    eval(${model.afterScript});
	 </script>
 </#if>