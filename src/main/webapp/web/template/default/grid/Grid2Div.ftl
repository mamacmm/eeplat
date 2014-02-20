<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<div style="margin:0 15px 5px 5px">
	   <#list data as ins>
	   			<#assign lastItem=""/>
	   		    <#assign formsize=model.normalGridFormLinks?size />
	   			<#list model.normalGridFormLinks as item> 
	   				<#if item_index < (formsize-1)>
		             	<#if '${dataBind(ins,item)}' ==''> ${item.htmlValue} </#if> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            <#else>
		                <#if '${dataBind(ins,item)}' ==''> <#assign lastItem =item.htmlValue/> </#if>	
		            </#if> 
				</#list>
			<div>
				${lastItem}
			</div>
	     </#list>
</div>