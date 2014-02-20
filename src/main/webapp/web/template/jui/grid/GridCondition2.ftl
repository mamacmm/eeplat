<#--开始输出空行-->
<#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
<#else>  <#--没有定义的话，输出一个空行-->  
	<br/>
</#if>

<table class="searchContent">
	<tr><td>
	<#list model.normalGridFormLinks as item> 
				 <#if  item.newLine>
			</tr></td>
			<tr><td>
				 </#if>
		         ${item.l10n}    ${item.htmlValue} &nbsp; 
			</#list>
			
	</td></tr>
</table>
<div class="subBar">
	<ul>
		<#list model.allOutGridFormLinks as item> 
				 <#if  item.newLine>
			</tr></td>
			<tr><td>
				 </#if>
		          ${item.htmlValue} &nbsp; 
			</#list>
	</ul>
</div>

