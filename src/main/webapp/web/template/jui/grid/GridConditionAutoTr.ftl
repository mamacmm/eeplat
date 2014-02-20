<#--开始输出空行-->
<#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
</#if>

<div class="pageHeader">
<#assign line = 0/>
<#assign lineMore = false/>
<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>
<div class="searchBar">
	<table id='g${model.objUid}' class='searchContent tablesorter' style="width:100%;margin:0px" border="0" cellpadding="1" cellspacing="1" >
		<tbody>
			<tr><td>
			<#assign colNum = (model.colNum)?default(3)/>
			<#assign i = 0/>
			<#list model.normalGridFormLinks as item>
				<#if ( lineMore == false ) >
			
					 <#if (i == colNum) && (item_index+1)!=model.normalGridFormLinks?size>
					 	<#assign i = 0/>
					 	<#assign line = line+1/>
					 	<#if (line > 1)>
						 	<#assign lineMore = true/>
							<button class="ctlBtn">更多条件</button>
					 	<#else>
							</tr></td>
							<tr><td>
						</#if>
					 </#if> 
					 <#if  (item.newLine && item_index > 0 )>
					 	<#assign line = line+1/>
					 	<#if (line > 1)>
						 	<#assign lineMore = true/>
							<button class="ctlBtn">更多条件</button>
					 	<#else>
							</tr></td>
							<tr><td>
						</#if>
					 	<#assign i = 0/>
					 </#if>
					 <#if ( lineMore == false ) >
			         	${item.l10n}    ${item.htmlValue} &nbsp;
			         </#if>	 
			         <#assign i = i + 1/>
				</#if>
			</#list>
			<#list model.allOutGridFormLinks as item> 
				 <#if  (item.newLine && item_index > 0 ) >
					</tr></td>
					<tr><td>
				 </#if>
		          ${item.htmlValue} &nbsp; 
			</#list>
			</td></tr>
		</tbody>
	</table>

	
</div>
</form>	
</div>