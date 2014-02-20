<#include "TFormBase.ftl">
<a style="${model.style?if_exists}"  id='${model.objUid}'   <#compress><@JudgeStyle model/></#compress> >  <span>${model.l10n}</span></a>
<script>

  //var dealBus = "dataBus=setUserContext&contextKey=stationuid&contextValue=" + resend_date;
//'paras':dealBus,

  $('#${model.objUid}').bind('click',function(){
	    loadPml({

	   			 <#if (model.linkPaneModel?exists && model.linkPaneModel.linkType==5)>
	   			 	'resourcePath':'${model.linkPaneModel.resource.resourcePath}',
	   			 </#if>
	   			 	'pml':'${model.linkPaneModel.name}',
	   			 	'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}',
	   			 	'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}',

		   		 'title':'${model.linkPaneModel.title}',
		         'formName':'${model.targetForms}'
		   		  <#if (model.targetPaneModel)?exists>	         
				,'target':'${model.targetPaneModel.name}'
				 </#if> }
		);
  });
</script>