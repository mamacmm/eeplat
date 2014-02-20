<#include "TFormBase.ftl">
<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 
<button type="button"   style="${model.style?if_exists}"  id='${model.objUid}' <#compress><@JudgeStyle model/></#compress>>     <#compress><@JudgeBootIcon model/></#compress>  ${model.l10n}</button>
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
				var tabBtnSelector = "#dvTab  .on .btnTab";
		  		tabCloseWindow(tabBtnSelector);
		 </#if>
	  
  	 <#if ((model.inputConfig)?exists && model.inputConfig=='loadParent')>
  		reloadTree();     
  	</#if>
   

 }
 
 $('#${model.objUid}').bind('click',function(){  	  

	  if($('#g${model.gridModel.objUid} tbody  tr.selected').length == 0){
	       if($(this).parent().parent().attr('value')!=null){
	  		    $(this).parent().parent().addClass("selected");				
			}else{	
			    	alert("${i18n('请选择一条记录！')}");
		         	return;
	      }
       }
	   var selectedValue = $('#g${model.gridModel.objUid} tbody  tr.selected').attr('value');
	   var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}&invokeButtonUid=${model.objUid}" + "&contextValue=" + selectedValue;
	   
	   	/////增加支持多条记录选中
	     var selects = $("#g${model.gridModel.objUid} tbody  tr input:checked");
	     if(selects.length > 0){
		     var values = '';
		     for(var i = 0; i < selects.length; i++){
		        if(i > 0){
		        	values = values + ',' + selects.eq(i).attr("value");
		        }else{
			        values = values + selects.eq(i).attr("value");
			    }
		     }
		     if(values.length > 0){
		        dealBus = dealBus + "&contextMultiKey=${model.gridModel.service.bo.name}&contextMultiValue=" + values;
		     }
	     }
	   //////end 增加支持多条记录选中
	   
	   $(".toolbar .selected").removeClass("selected");
	   $(this).addClass("selected");
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
