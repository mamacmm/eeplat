<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<#--开始输出空行-->
 <#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
<#else>  <#--没有定义的话，输出一个空行-->  
	<br/>
</#if>

   <#list data as ins>
         <#if ins_index < 9>
    		<#if ((ins_index == 0) || (ins_index == 3) || (ins_index == 6))>
            <div class="row-fluid">
               <ul class="thumbnails">
    		</#if>	
	    		<li class="span4">
	                <div class="thumbnail">
	                  <img src="${(ins.map.imgurl)?default("web/default/images/180.jpg")}" width="150px"  height="150px"  alt="${(ins.map.name)?default("EEPlat")}">
	                  <div class="caption">
	                    <h3>${(ins.map.name)?default("EEPlat")}</h3>
    			    	<p class="info"  title="${(ins.map.description)?default("EEPlat是元数据驱动的多租户解决方案。")}">
    			    	<#assign caption1 = (ins.map.description)?default("EEPlat是元数据驱动的多租户解决方案。") />
    			    	<#assign lens=caption1?length />
    			    	<#if lens < 50>
    			    		 ${caption1}
	    			    	 <#if lens < 30>
	    			    	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    			    	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    			    	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    			    	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    			    	</#if>
	    			    <#else>
	    			    	${(caption1)?substring(0,50)}......
    			    	</#if>
						</p>
	                  </div>
	                </div>
	              </li>
    		  <#if ((ins_index == 2) || (ins_index == 5) || (ins_index == 8) || (ins_index == (data?size-1)) ) >
	    		  </ul>
	    		</div>
    		  </#if>
         </#if>      
 </#list>


<#if (model.rowSize?exists && model.rowSize > 0)>
     <div id="Pagination${model.objUid}"   style="width:100%;"></div> 
</#if>


<script language="javascript">
	<#if (model.rowSize?exists && model.rowSize > 0 && pmlName?exists)>
			//pageSplit('${model.containerPane.name}','${pmlName}','${formName}'); ${resultSize}
			 $("#Pagination${model.objUid}").pagination("${resultSize}".replace(",",""), {  
	            callback: PageCallback,  
	            <#if (langlocal=='zh') >
	            prev_text: '上一页',       //上一页按钮里text  
	            next_text: '下一页',       //下一页按钮里text
	            </#if>  
	            items_per_page: ${rowSize},  //显示条数  
	            num_display_entries: 6,    //连续分页主体部分分页条目数  
	            current_page: ${pageNo}-1,   //当前页索引  
	            num_edge_entries: 2        //两侧首尾分页条目数  
	        });  
	        
	      	function PageCallback(index, containers){
	      	
				   var pmlUrl = getPmlUrl('${pmlName}',index+1,'${rowSize}');
				   if($('#${pmlName}').size() > 0){
				   	loadPml({'pml':pmlUrl,'target':'${pmlName}','formName':'${formName}'});
				   }
			}
		</#if>	
		
</script>
