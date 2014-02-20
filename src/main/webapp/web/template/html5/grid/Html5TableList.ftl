<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  

<#if (model.topOutGridFormLinks?size > 0) > 
	<#list model.topOutGridFormLinks as item>
		<#if '${dataBind(ins,item)}' ==''> 
			${item.htmlValue}
		<#else>
			${item.htmlValue}
		</#if>
	</#list>
</#if>
<script type='text/javascript'>
	
</script>
<form id='a${model.objUid}' class='w100'>
<table id='${model.name}' class='table table-striped html5-table'>
	<tbody>
		<#list model.normalGridFormLinks as item>
			<#if '${dataBind(data,item)}' ==''> 
				${item.htmlValue}
			<#else>
				${item.htmlValue}
			</#if>
		</#list>
	</tbody>
</table>
</form>
<#if (model.bottomOutGridFormLinks?size > 0) > 
	<#list model.bottomOutGridFormLinks as item> 
	    <#if '${dataBind(ins,item)}' ==''> 
			${item.htmlValue}
		<#else>
			${item.htmlValue}
		</#if>
	</#list>
</#if>