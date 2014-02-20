<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()>  

  <ul data-role='listview' >
  
   <#if (data?size > 0)>
	  <#list data as ins>
	      <li>
	           <#if (controCols?size==0) > 
		        <a  data-direction="reverse" href="/${webmodule}/${linkPaneName}.pml?dataBus=setContext&contextKey=${ins.bo.name}&contextValue=${ins.uid}&contextNIUid=${(ins.map.contextniuid)?if_exists}&contextPIUid=${ins.map.contextpiuid?if_exists}">
		       </#if> 
				   <#list showCols as item> 
							${item.l10n}:&nbsp; <#if '${dataBind(ins,item)}' ==''> ${item.htmlValue}  </#if> <br/>
			      </#list>
	           <#if (controCols?size==0) > 
				</a>
			   </#if>	
			   <!--如果后面控制器大于2项，控制器每列3项-->
			   <#if (controCols?size > 2) > 
				   <div class="ui-grid-b"> 
					 <#list  controCols as item>
					     <#if ((item_index % 3)==0)>
						     <div class="ui-block-a">
						    <#if '${dataBind(ins,item)}' ==''>  ${item.htmlValue} </#if> 
						     </div>
					     </#if>
					     <#if ((item_index % 3) ==1)>
						     <div class="ui-block-b">
						    <#if '${dataBind(ins,item)}' ==''>  ${item.htmlValue} </#if> 
						     </div>
					     </#if>
					     <#if ((item_index % 3) ==2)>
						     <div class="ui-block-c">
						    <#if '${dataBind(ins,item)}' ==''>  ${item.htmlValue} </#if> 
						     </div>
					     </#if>
					 </#list>  
					</div> 
			 <#else>	
			 	 <#list  controCols as item>
			  		 <#if '${dataBind(ins,item)}' ==''>  ${item.htmlValue} </#if> 
			  	  </#list>	 
			 </#if>
			 	
		  </li> 
	   </#list><!--End data-->
	  <#if ((pageNo?exists) && (  resultSize > model.rowSize ) )>
       <li>
		     		<div class="gigantic pagination">
			    <a href="#" class="first" data-action="first">&laquo;</a>
			    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
			    <input type="text" readonly="readonly" />
			    <a href="#" class="next" data-action="next">&rsaquo;</a>
			    <a href="#" class="last" data-action="last">&raquo;</a>
			</div>
			<br/>
	   </li>
	 </#if> <!--End Page Split--> 
    <#else>
          <li>
            ${i18n('暂无数据！')}
          </li>
    </#if>
	</ul>  

<p/>	
   <fieldset class="ui-grid-a">
					<#list bottomForms as item> 
										<div class="ui-block-b">
										      <#if (item.controller.name!="com.exedosoft.plat.ui.jquery.form.TClose") >
				                      <#if '${dataBind(null,item)}' ==''> ${item.htmlValue}  </#if>
				                  </#if>    
				            </div>   
		 			</#list>
     </fieldset>
     
 <#if (model.rowSize?exists && model.rowSize > 0 && pmlName?exists)>
  <script>  
   $('.pagination').jqPagination({
		current_page:${pageNo}, 
		max_page	:${pageSize},
		paged		: function(page) {
				   var pmlUrl = getPmlUrl('${pmlName}',page,'${rowSize}');
				   loadPml({'pml':pmlUrl,'target':'${pmlName}','formName':'${formName}'});
		}
	});
	</script>		
</#if>		

	
