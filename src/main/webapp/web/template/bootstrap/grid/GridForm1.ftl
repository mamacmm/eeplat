			<#--定义dataBinding-->
			<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()> 
			<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 
					<#if !isdialog>						
								<div class="widget-box">
									<div class="widget-header">
										<h5>${model.caption?if_exists}</h5>
									</div>

									<div class="widget-body">
										<div class="widget-main">
												<#if (model.topOutGridFormLinks?size > 0) > 
													<div class="widget-toolbox padding-8 clearfix">
														<div class="btn-group"> 
																				<#list model.topOutGridFormLinks as item>
																						${item.htmlValue}
																				</#list>
													    </div>
													 </div>	
												</#if>
				     </#if>	
											<#assign colNum = 1/>
											<#assign i = 0/>
<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>

	<table id='g${model.objUid}' class='table  table-bordered ' >
		<thead>
		<#assign colNum = model.colNum?default(2)/>
		<#if model.caption?exists>
			<#assign icon = (model.icon)?default("MyRightArrow.jpg")/>
			<tr> 
				<td class='title' colspan="${colNum*2}"> 
				<#if !model.caption?contains("<") >
				<img src='${contextPath}images/${icon}'/> 
				</#if>
				<b> ${model.caption} </b> </td>
			</tr>
		</#if> 
		</thead>	
		
		<tbody>
			<#assign i = 0/>
			<#if (model.abstractGridFormLinks?size > 0)>
			<tr>
			<#list model.abstractGridFormLinks as item>
				<#--控制换行-->
			  	<#if (i>0 && (((i == colNum) ) ||item.newLine || (item_index > 0 && model.abstractGridFormLinks[item_index-1].newLine) ) )>
				 	<#assign i = 0/>
				 	</tr>
				 	<tr>
				</#if> 	
				
				<#assign htmlValue  = ''/>
				
				<#if dataBind(data,item) ==''> 
					<#assign htmlValue  = item.htmlValue/> 
				</#if>
				<#if (item.nameColspan?exists && item.nameColspan == 0) >
				    <td  <#if item.noWrapValue>nowrap='nowrap'</#if>   <#if (item.newLine || (item_index+1)==model.abstractGridFormLinks?size  || model.abstractGridFormLinks[item_index+1].newLine )> colspan="${(colNum-i-1)*2+2}"</#if> style='${item.style?if_exists}'> ${htmlValue} </td>
				<#else>                                                 
				    <td class="tdkey" <#if item.noWrapLabel>nowrap='nowrap'</#if>> ${item.l10n}</td> 
				    <td  <#if item.noWrapValue>nowrap='nowrap'</#if>   <#if (item.newLine || (item_index+1)==model.abstractGridFormLinks?size  || model.abstractGridFormLinks[item_index+1].newLine )> colspan="${(colNum-i-1)*2+1}"</#if> style='${item.style?if_exists}'>  ${htmlValue} </td>
				</#if>     
			    <#assign i = i + 1/>    
			</#list>
			</tr>
			</#if>
			
			
			<#if (model.moreGridFormLinks?size > 0) >
				<#if (model.abstractGridFormLinks?size > 0)>   
				<tr>
					<td colspan="${colNum*2}" style="cursor:pointer" ><span style="cursor:pointer" onclick="toggleMore(this);"><b>${i18n('More')}</b></span></td>
				</tr>
				</#if>
			<#macro JudgeDisplay >
			    <#if (model.abstractGridFormLinks?size > 0)>
			    	style="display:none"
			    </#if>
			</#macro>
			
			<tr  <#compress><@JudgeDisplay/></#compress> >
			<#assign i = 0/>
			<#list model.moreGridFormLinks as item>
			     <#--控制换行-->
			  	<#if (i>0 && ((i == colNum) ||item.newLine || (item_index > 0 &&  model.moreGridFormLinks[item_index-1].newLine) ) )>
				 	<#assign i = 0/>
				 	</tr>
				 	<tr  <#compress><@JudgeDisplay/></#compress> >
				</#if> 	
				<#assign htmlValue  = ''/>
				
				<#if dataBind(data,item) ==''> 
					<#assign htmlValue  = item.htmlValue/> 
				</#if>
				<#if (item.nameColspan?exists && item.nameColspan == 0) >
				    <td  <#if item.noWrapValue>nowrap='nowrap'</#if> <#if ((i == (colNum-1)) || item.newLine || (item_index+1)==model.moreGridFormLinks?size || model.moreGridFormLinks[item_index+1].newLine )> colspan="${(colNum-i-1)*2+2}" <#else>  colspan="${(colNum-i-1)*2}"  </#if> style='${item.style?if_exists}' > ${htmlValue}</td>
				<#else>
				    <td class="tdkey" <#if item.noWrapLabel>nowrap='nowrap'</#if>> ${item.l10n}</td>
				    <td  <#if item.noWrapValue>nowrap='nowrap'</#if> <#if (item.newLine || (item_index+1)==model.moreGridFormLinks?size || model.moreGridFormLinks[item_index+1].newLine )> colspan="${(colNum-i-1)*2+1}"</#if> style='${item.style?if_exists}' > ${htmlValue} </td>
			    </#if>
			     
				<#assign i = i + 1/>
			</#list>
			</tr>	
	<#--下面是按钮部分-->
			<#if  (model.bottomOutGridFormLinks?size > 0)>
				<tr class="buttonMore" > <td  style="text-align:center;align:center"  colspan="${colNum*2}" >
				<#list model.bottomOutGridFormLinks as item> 
			          <#if '${dataBind(data,item)}' ==''> ${item.htmlValue} </#if> &nbsp; 
				</#list>
				</td></tr>
				</#if>		
			</#if> 	
		</tbody>
	  </table>
	<#if (model.hiddenGridFormLinks?size > 0) > 
				<#list model.hiddenGridFormLinks as item> 
				    <#if '${dataBind(data,item)}' ==''>  ${item.htmlValue} </#if>  &nbsp; 
				</#list>
	</#if>
</form>	
</div>
						<#if isdialog>		
										</div><!--End widget Main-->
						<#else>				
									</div>
								</div>
						</#if>		
<script>
function toggleMore(obj){

//this.css('display') == 'none'

    var a = $(obj).parent().parent().nextAll(":not(.buttonMore)");
	a.toggle(a.css('display') == 'none');
	var html = $(obj).text()=="${i18n('More')}" ? "<b>${i18n('Less')}</b>" : "<b>${i18n('More')}</b>";
	$(obj).html(html);

}
<#if  (model.name?contains("Browse")) >
 $('#g${model.objUid} tbody  tr').bind('click',function(){
			$('#g${model.objUid} tbody  tr.selected').removeClass("selected");//去掉原来的选中selected
			$(this).addClass("selected");
 });
</#if>
 
///jquery validate 
   $("#a${model.objUid}").validate({
   		ignoreTitle: true,
	    highlight: function(label) {
	    	$(label).closest('.control-group').addClass('error');
	    },
	    success: function(label) {
	    	label
	    		.text('OK!').addClass('valid')
	    		.closest('.control-group').addClass('success');
	    }
	  });

</script>

