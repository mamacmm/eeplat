<#include "TFormBase.ftl">
<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 
<a style="${model.style?if_exists}"  id='${model.objUid}'    <#compress><@JudgeStyle model/></#compress>>     <#compress><@JudgeBootIcon model/></#compress>  <span>${model.l10n}</span></a>
 <script>
 
  function fnCB${model.objUid}(){
  	<#if (!((model.inputConstraint)?exists && model.inputConstraint=='noCloseOpener'))>
	    <#if (model.gridModel.containerPane.name)?exists>

		try{
			if($('#F' + '${model.gridModel.containerPane.name}').size()>0){
	  			$('#F' + '${model.gridModel.containerPane.name}').dialog('close');
	  		}else{
	  			$('#' + '${model.gridModel.containerPane.name}').parents(".ui-dialog-content").dialog('close');
	  			<#if (model.gridModel.containerPane.parent)?exists>	
	  				$('#F' + '${model.gridModel.containerPane.parent.name}').dialog('close');
	  			</#if>
		  	}  	
	  	}catch(e){
	  	}	
	  	</#if>
	 </#if>
	 
	 <#if ((model.inputConfig)?exists && model.inputConfig=='closeTab')>
	 	<#if (model.gridModel.containerPane.name)?exists>
			var tabBtnSelector = "#dvTab  .oneTab[tabId='${model.gridModel.containerPane.name}'] .btnTab";
	  		tabCloseWindow(tabBtnSelector);
	  	</#if>
	 </#if>  
	  
  	 <#if ((model.inputConfig)?exists && model.inputConfig=='loadParent')>
  		reloadTree();     
  	</#if>
   

 }
 
 $('#${model.objUid}').bind('click',function(){  	  

	   	var selectedValue=$(this).parent().parent().parent().parent().find("div.grid").find("input").attr('value');
		if(selectedValue==null){
			 alert("${i18n('请选择一条记录！')}");
			 return;
		 }
		 
	   var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}&invokeButtonUid=${model.objUid}" + "&contextValue=" + selectedValue;

  	  callService({'btn':this,
				'callType':'uf',
				'callback':fnCB${model.objUid},
				'formName':'${model.targetForms}',
				'serviceUid':'${model.linkService.objUid}',        
				'paras': dealBus
				<#if (model.linkPaneModel)?exists>
				 ,'pml':'${model.linkPaneModel.name}'
				 ,'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}'
	   			 ,'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}'
		        <#else>
		         ,'pml':'${model.gridModel.containerPane.name}'
				</#if>
			    <#if (model.gridModel.containerPane.hiddenPane.gridModel)?exists>
			         ,'conFormName':'a${model.gridModel.containerPane.hiddenPane.gridModel.objUid}'
			     </#if>
			    <#if (model.targetPaneModel)?exists>	         
		         	,'target':'${model.targetPaneModel.name}'
		        <#else>
		        	,'target':'${model.gridModel.containerPane.name}'	 	
				</#if>
				<#if (model.echoJs)?exists>	         
		         ,'echoJs':'${model.echoJs}'
		         </#if>})
	});
</script>		         
