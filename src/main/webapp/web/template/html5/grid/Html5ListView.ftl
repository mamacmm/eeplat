<#assign datatojson = "com.exedosoft.plat.template.Data2Json"?new()/> 
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  

<form id='a${model.objUid}' autocomplete="off" onsubmit="$(this).find('.html5-search-button').click();return false;">
<#if (model.topOutGridFormLinks?size > 0) > 
	<#list model.topOutGridFormLinks as item>
		<#if '${dataBind(secondservicedata,item)}' ==''> 
			${item.htmlValue}
		<#else>
			${item.htmlValue}
		</#if>
	</#list>
</#if>
<div class='w95 marginauto'>
<#if (pageNo == 1 && pageSize > 0)>
<#else>
<a href='javascript:void(0);' class='margintop3px btn btn-block btn-success' onclick="(function(){loadPml({pml:'${model.containerPane.name}',notpush:true,target:'${model.containerPane.name}',paras:getParasOfForms('a${model.objUid}') + '&pageNo=${pageNo-1}'});})()">上一页(${pageNo-1})</a>
</#if>
</div>

<div id='${model.name}' class='html5-listview html5-listview-theme'>
	
	<#if (data?size > 0) >
    <#list data as ins>
		<div class='html5-listview-item'>
			<#list model.normalGridFormLinks as item> 
				<#if '${dataBind(ins,item)}' ==''> 
					${item.htmlValue}
				</#if>
			</#list>
		</div>
	</#list>
	<#else>
	<div class='html5-listview-item txtcenter'>无数据</div>
	</#if>
</div>

<div class='w95 marginauto marginbottom5px'>
<#if pageNo == pageSize || pageSize == 0>
<#else>
<a href='javascript:void(0);' class='btn btn-block btn-success' onclick="(function(){loadPml({formName:'a${model.objUid}',pml:'${model.containerPane.name}',target:'${model.containerPane.name}',notpush:true,paras:getParasOfForms('a${model.objUid}') + '&pageNo=${pageNo+1}'});})()">下一页(${pageSize-pageNo})</a>
</#if>
</div>
</form>
<#if (model.bottomOutGridFormLinks?size > 0) > 
	<#list data as ins>
		<#list model.bottomOutGridFormLinks as item> 
		    <#if '${dataBind(ins,item)}' ==''> 
				${item.htmlValue}
			<#else>
				${item.htmlValue}
			</#if>
		</#list>
	</#list>
</#if>