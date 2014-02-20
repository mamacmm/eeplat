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
											<form  method='post' id='a${model.objUid}' name ='a${model.objUid}' class="form-horizontal">
											
														<#if (model.normalGridFormLinks?size > 0)>
														
														<div class="row-fluid">
															<#list model.normalGridFormLinks as item>
															
															<#if (i>0 && ((i == colNum) ||item.newLine || (item_index > 0 &&  model.normalGridFormLinks[item_index-1].newLine) ) )>
															 	<#assign i = 0/>
															 	</div>
																<div class="row-fluid">
															</#if> 	
															<#assign htmlValue  = ''/>
															
															<#if '${dataBind(data,item)}' ==''> 
																<#assign htmlValue  = item.htmlValue/>
																<#if ( (colNum == 1) || item.newLine   )>
																	<div class="span12">
																<#elseif colNum == 2>
																	<div class="span6">
																<#else>
																	<div class="span4">
																</#if>
																<#if (item.nameColspan?exists && item.nameColspan == 0) >
																	${htmlValue}
																<#else>	
																		<div class="control-group">
																				<label class="control-label" for="${item.fullColID}">${item.l10n}</label>
																				
																				<div class="controls">
																					${htmlValue}
																				</div>
																		</div>
																</#if>		
															     </div>
															     <#assign i = i + 1/>
															</#if>
														
															</#list>
														</div>	
														</#if>
												<!--Hidden-->
												<#if (model.hiddenGridFormLinks?size > 0) > 
															<#list model.hiddenGridFormLinks as item> 
															    <#if '${dataBind(data,item)}' ==''>  ${item.htmlValue} </#if>  &nbsp; 
															</#list>
												</#if>
												

											</form>	
										</div>
						<#if !isdialog>		
										</div><!--End widget Main-->
					    </#if>					
										<div class="widget-toolbox padding-8 clearfix center">
												<#--下面是按钮部分-->
												<#if (model.bottomOutGridFormLinks?size > 0) > 
														<div class="btn-group"> 
																				<#list model.bottomOutGridFormLinks as item>
																						${item.htmlValue}
																				</#list>
													    </div>
												</#if>
										</div>
						<#if !isdialog>						
									</div>
								</div>
						</#if>		
<script>

	<#if !isdialog>	
		widget_boxes(); 
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