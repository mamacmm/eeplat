<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()> 
<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 

<#--开始输出空行-->
<#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
<#else>  <#--没有定义的话，输出一个空行-->  
	<br/>
</#if>
<div class="container">
<#if model.headTitle?exists>
  <div class="page-header">
			    <h1>${model.headTitle}</h1>
  </div>
</#if>

<#assign colNum = model.colNum?default(1)/>
<div class="row">
	<div class="span8">
	<form action="" method='post' id='a${model.objUid}' name ='a${model.objUid}' class="form-horizontal">
	<fieldset>
		<#if model.caption?exists>
			<legend>
				${ model.caption}
			</legend>	
		</#if>
			
			<#assign i = 0/>
			<#if (model.normalGridFormLinks?size > 0)>
			<div class="control-group">
			<#list model.normalGridFormLinks as item>
				<#--控制换行-->
			  	<#if (i>0 && (((i == colNum) ) ||item.newLine || (item_index > 0 && model.normalGridFormLinks[item_index-1].newLine) ) )>
				 	<#assign i = 0/>
				 	</div>
				 	<div class="control-group">
				</#if> 	
				
				<#assign htmlValue  = ''/>
				
				<#if dataBind(data,item) ==''> 
					<#assign htmlValue  = item.htmlValue/> 
				</#if>
				<#if (item.nameColspan?exists && item.nameColspan == 0) >
				        <div class="controls">
				        	${htmlValue}
				        </div>
				<#else>                                                 
				       <label class="control-label" for="${item.fullColID}"> ${item.l10n} </label> 
				        <div class="controls">
				        	${htmlValue}
				        </div>
				</#if>     
			    <#assign i = i + 1/>    
			</#list>
				 	</div>
			</#if>
			
			
	<#--下面是按钮部分-->
			<#if  (model.bottomOutGridFormLinks?size > 0)>
			 <div class="form-actions">
				<#list model.bottomOutGridFormLinks as item> 
			          <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if> &nbsp; 
				</#list>
			 </div>
			</#if> 	
	   </fieldset>
		<#if (model.hiddenGridFormLinks?size > 0) > 
					<#list model.hiddenGridFormLinks as item> 
					    <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if> &nbsp; 
					</#list>
		</#if>
	</form>
	 </div>	
   </div>
</div>	


