<#assign datatojson = "com.exedosoft.plat.template.Data2Json"?new()/> 
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

<div id='${model.name}' class='html5-header-content'>
	<#if data?exists>
		<#list model.normalGridFormLinks as item> 
			<#if '${dataBind(data,item)}' ==''> 
				${item.htmlValue}
			<#else>
				${item.htmlValue}
			</#if>
		</#list>
	<#else>
		<#list model.normalGridFormLinks as item> 
			${item.htmlValue}
		</#list>
	</#if>
</div>

<#if (model.bottomOutGridFormLinks?size > 0) > 
	<#list model.bottomOutGridFormLinks as item> 
	    <#if '${dataBind(ins,item)}' ==''> 
			${item.htmlValue}
		<#else>
			${item.htmlValue}
		</#if>
	</#list>
</#if>