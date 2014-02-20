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
<div class="imglistcontainer">

    	 <div class="ca-nav">
		       <span class="firstPage" style="display:none"/>
		       <span class="ca-nav-prev prevPage">Previous</span>
		       <span class="ca-nav-next nextPage">Next</span>
		       <span class="lastPage" style="display:none"/>
		</div>
	
	   <#list data as ins>
         <#if ins_index < 9>
    		<#if ((ins_index == 0) || (ins_index == 3) || (ins_index == 6))>
    			<ul class="items">
    		</#if>	
    			
    			    <li>
    			    	<p class="image">
    			    	<a href="${(ins.map.siteurl)?default("http://www.eeplat.com")}"><img src="${(ins.map.imgurl)?default("web/default/images/180.jpg")}" width="150px"  height="150px"  alt="${(ins.map.name)?default("EEPlat")}"></a></p>
    			    	<h3>${(ins.map.name)?default("EEPlat")}</h3>
    			    	<p class="info"  title="${(ins.map.description)?default("EEPlat是元数据驱动的多租户解决方案。")}">${(ins.map.description)?default("EEPlat是元数据驱动的多租户解决方案。")}</p>
    			    </li>
    			    
    			
    		  <#if ((ins_index == 2) || (ins_index == 5) || (ins_index == 8))>
    			</ul>
    		  </#if>
         </#if>      
	     </#list>
</div>

<#if (model.rowSize?exists && model.rowSize > 0)>
	<div  style="margin:0 auto;text-align:center;width:818px;margin-top:10px">
	 				第<span class='pageNo'>${pageNo}</span>页&nbsp;
			    	 每页<span class='rowSize'>${rowSize}</span>条&nbsp;
			    	 共<span class='pageSize'>${pageSize}</span>页 &nbsp; 
			    	共<span class="resultSize">${resultSize}</span>条记录
	</div>
</#if>


<script language="javascript">
	    <#if (model.rowSize?exists && model.rowSize > 0 && pmlName?exists)>
			pageSplit('${model.containerPane.name}','${pmlName}','${formName}');
		</#if>	
</script>
