不是用用的这个，用的在线编写的模板。
<div class="imglistcontainer">

		 <div class="ca-nav">
		       <span class="ca-nav-prev">Previous</span>
		       <span class="ca-nav-next">Next</span>
		</div>
			
	   <#list data as ins>
	   <#if ins_index <= 9>
		<#if ((ins_index % 3)==0)>
			<ul class="items">
		</#if>	
			
			    <li>
			    	<p class="image">
			    	<a href="${(ins.map.siteurl)?default("http://www.eeplat.com")}"><img src="${(ins.map.imgurl)?default("web/default/images/180.jpg")}"  height="150px" width="150px"  alt="${(ins.map.name)?default("EEPlat")}"></a></p>
			    	<h3>${(ins.map.name)?default("EEPlat")}</h3>
			    	<p class="info">${(ins.map.description)?default("EEPlat是元数据驱动的多租户解决方案，是元数据、模板、JavaScript（Rhino）的开发及运行平台。")}</p>
			    </li>
			    
			
		  <#if ((ins_index % 3)==0)>
			<ul class="items">
		  </#if>	
	     </#list>
</div>