<#include "TFormBase.ftl">
<button type="button" style="${model.style?if_exists}"  id='${model.objUid}' <#compress><@JudgeStyle model/></#compress> >    <#compress><@JudgeBootIcon model/></#compress >  ${model.l10n}</button>
<script>
$('#${model.objUid}').bind('click',function(){
	<#if (model.gridModel.containerPane.name)?exists>
		 <#if (model.linkPaneModel)?exists>
			loadPml({
				   		 'pml':'${model.linkPaneModel.name}',
				   		 'pmlWidth':'${model.linkPaneModel.paneWidth?if_exists}',
	   			 		 'pmlHeight':'${model.linkPaneModel.paneHeight?if_exists}',
				   		 'title':'${model.linkPaneModel.title}'
				   		  <#if (model.linkPaneModel.hiddenPane)?exists>
				   		 ,'formName':'a${model.linkPaneModel.hiddenPane.gridModel.objUid}'
				   		 </#if>
				   		  <#if (model.targetPaneModel)?exists>	         
						,'target':'${model.targetPaneModel.name}'
						 </#if> }
		    );
		    
		  </#if>  
		try{
			if($('#F' + '${model.gridModel.containerPane.name}').size()>0){
	  			$('#F' + '${model.gridModel.containerPane.name}').dialog('close');
				return;
	  		}else{
	  			$('#' + '${model.gridModel.containerPane.name}').parents(".ui-dialog-content").dialog('close');
	  			<#if (model.gridModel.containerPane.parent)?exists>	
	  				$('#F' + '${model.gridModel.containerPane.parent.name}').dialog('close');
					return;
	  			</#if>
		  	}  	
	  	}catch(e){
	  	}
	  	
	  	/// close tab
		try{
				$("#li${model.gridModel.containerPane.name}").remove();
				$("#pc${model.gridModel.containerPane.name}").remove();
				$(".nav-tabs>li:last>a").trigger("click");
		}catch(e){
				  	
		}
  	</#if>
  	
  }
);
</script>