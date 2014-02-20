<#include "TFormBase.ftl">
 <button  type="button"    id='${model.objUid}' class='save btn'>${model.l10n?default("保存")}</button>
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
  	
  	
  	<#if (model.linkPaneModel?exists) >
  	
			var pageNo = $(document.body).data("${model.linkPaneModel.name}.pageNo");
			var pageSize = $(document.body).data("${model.linkPaneModel.name}.pageSize");
			var dataParas = $(document.body).data("${model.linkPaneModel.name}"); 
			if (pageNo != null && pageSize != null) {
				dataParas = pageSplitDeal(dataParas,pageNo,pageSize);
			}
		    loadPml({
  			 <#if (model.linkPaneModel?exists && model.linkPaneModel.linkType==5)>
	   			 	'resourcePath':'${model.linkPaneModel.resource.resourcePath}',
	   			 </#if>
	   			 		'paras':dataParas,
		   			 	'pml':'${model.linkPaneModel.name}',
		   			 	'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}',
		   			 	'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}',
			   		 'title':'${model.linkPaneModel.title}',
		  	       'formName':'${model.targetForms}'
			   		  <#if (model.targetPaneModel)?exists>	         
					,'target':'${model.targetPaneModel.name}'
					 </#if> }
			);
	 </#if>
  }
  
 function trimEndComma(addStr){
   if(addStr.charAt(addStr.length-1)==','){
	 	 addStr = addStr.substring(0,addStr.length-1);
    }
    return addStr;
 } 

 $('#${model.objUid}').bind('click',function(){
 
 		     var addStr = "{";
 		     var modiStr = "{";
 		     var inputName = "";
 		     var addCheckIds = "{";
 		     var removeCheckIds = "{";

 		    <#list childIds as gridId>
   
	 		     addCheckIds =addCheckIds  + "${gridId}:[";
	 		     removeCheckIds =removeCheckIds  + "${gridId}:[";
	 		     
	 		     addStr = addStr + "${gridId}:[";
	 		     modiStr = modiStr + "${gridId}:{";
	 		      
	 		     $('#g${gridId} tbody tr').each(
	 		        function(){
	 		        /////////////////////////addCheckIds
		  		    if( $(this).find(".addvaluee").size() > 0){
		      		    addCheckIds = addCheckIds +  $(this).find(".addvaluee").val()+ ",";
		  		    }
		  		    ///////////////////removeCheckIds
		  		    if( $(this).find(".removevaluee").size() > 0){
		      		    removeCheckIds = removeCheckIds +  $(this).find(".removevaluee").val()+ ",";
		  		    }
		  		    
	 		        ///////////////////////modify 
	 		        	if($(this).attr("edit")=='true'){
	 		        		if($(this).attr("value")==null){//add
	 		        		   addStr = addStr + O2String($(this).find(":input").serializeArray()) + ",";
	 		        		   if(inputName==""){
	 		        		   	 inputName = $(this).find(":input").eq(0).attr("name");
	 		        		   }
	 		        		}else{//update
	 		        		   modiStr = modiStr +  $(this).attr("value") +":" +  O2String($(this).find(":input").serializeArray()) + ",";
	 		        		}
	 		        	}		        
	 		        }
	 		     );
	 		     ////add
	 		   
 				addStr = trimEndComma(addStr) + "],";
 				////modiStr
 				modiStr = trimEndComma(modiStr) + "},";
 				////checkid
 				addCheckIds = trimEndComma(addCheckIds) + "],";
 				
 				////removecheckid
 				removeCheckIds = trimEndComma(removeCheckIds) + "],";
 				
 		    </#list>
	    	addStr = trimEndComma(addStr) + "}";
	    	modiStr = trimEndComma(modiStr) + "}";
	    	addCheckIds = trimEndComma(addCheckIds) + "}";
	    	removeCheckIds = trimEndComma(removeCheckIds) + "}";

 		    
 		    console.log("MainID:::a${mainId}");
 		    console.log("ParasForm::" + getParasOfForms('a${mainId}'));
 		     var dealBus = getParasOfForms('a${mainId}') + "&formUid=${model.objUid}&";
 		     console.log("DealBus::::::::::" + dealBus);
 		     console.log("AddCheckIds:::::::::" + addCheckIds);
 		     console.log("removeCheckIds:::::::::" + removeCheckIds);
 		     
             dealBus = dealBus + "addStr="+ addStr + "&modiStr=" + modiStr  +"&mmLinkStr="+ addCheckIds +"&mmRemoveLinkStr="+ removeCheckIds;
             
               //表单验证
			if(validate('${model.targetForms}')){
	     	    callAction({'btn':this,
					'actionName':'com.exedosoft.plat.action.CoreSaveEditMultiAction',  
					'callback':fnCB${model.objUid}, 
					'paras': dealBus
				});
			}
			

  });
 
 </script>
