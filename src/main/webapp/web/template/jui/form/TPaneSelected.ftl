<#include "TFormBase.ftl">
<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 

<a  id='${model.objUid}'   style="padding:0px;${model.style?if_exists}"  <#compress><@JudgeStyle model/></#compress> >  <span id='span${model.objUid}'>${model.l10n}</span></a>
<script>
	 $('#span${model.objUid}').bind('click',function(e){  
	 
	 	var selectedValue=$(this).parent().parent().parent().parent().parent().find("div.grid").find("input").attr('value');
		if(selectedValue==null){
			 alert("${i18n('请选择一条记录！')}");
			 e.stopPropagation();
			 return false;
		 }

	   var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}" + "&contextValue=" + selectedValue;
	  
 	   <#if (model.linkPaneModel?exists && (model.linkPaneModel.linkType==5))>
 	     callPlatBus({'paras':dealBus});	   
	  </#if> 
	   
	   <#if ((model.linkPaneModel)?exists) >
	   loadPml({
	   			 'paras':dealBus, 

	   			 <#if (model.linkPaneModel?exists && model.linkPaneModel.linkType==5)>
	   			 	'resourcePath':'${model.linkPaneModel.resource.resourcePath}',
	   			 </#if>
	   			 	'pml':'${model.linkPaneModel.name}',
	   			 	'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}',
	   			 	'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}',

	       		 'title':'${model.linkPaneModel.title}'
	      		  <#if (model.targetPaneModel)?exists>	         
	      				,'target':'${model.targetPaneModel.name}'
			 </#if> }
	    );
	   </#if> 
  });
 </script>