<#include "TFormBase.ftl">
<#if (theValue?exists)>
 <#if (model.linkPaneModel?exists) >
	<a  id='${model.objUid}${model.data.uid}' data-role="button"  style="${model.style?if_exists}"  href='#' value="${theValue}">${theValue}</a>
	<script>
	
	  $('#${model.objUid}${model.data.uid}').bind('click',function(){
		    loadPml({
  			 <#if (model.linkPaneModel?exists && model.linkPaneModel.linkType==5)>
	   			 	'resourcePath':'${model.linkPaneModel.resource.resourcePath}',
	   			 </#if>
		   			 	'pml':'${model.linkPaneModel.name}',
		   			 	'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}',
		   			 	'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}',
		   			 'paras':'dataBus=setContext&contextKey=${model.data.bo.name}&contextValue=${model.data.uid}&contextNIUid=${(model.data.map.contextniuid)?if_exists}&contextPIUid=${model.data.map.contextpiuid?if_exists}',
			   		 'title':'${model.linkPaneModel.title}',
		  	       'formName':'${model.targetForms}'
			   		  <#if (model.targetPaneModel)?exists>	         
					,'target':'${model.targetPaneModel.name}'
					 </#if> }
			);
	  });
	</script>
	<#else>
		${theValue}
  </#if>	 
</#if>