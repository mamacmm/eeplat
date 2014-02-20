<#include "TFormBase.ftl">
<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 
<button type="button"  id="${model.objUid}"  style="${model.style?if_exists}"  <#compress><@JudgeStyle model/></#compress> > ${model.l10n} </button>
<script>
  

  $('#${model.objUid}').bind('click',function(){
  
	  if($('#g${model.gridModel.objUid} tbody  tr').attr('value')){
		  ///////////////////////////////////////////////////
			  if($('#g${model.gridModel.objUid} tbody  tr.selected').length == 0){
			       if($(this).parent().parent().attr('value')!=null){
			  		    $(this).parent().parent().addClass("selected");				
					}else{	
							alert("${i18n('请选择一条记录！')}");
				         	return;
			      }
		       }
			////////////////////////////end judge  
			   var selectedValue = $('#g${model.gridModel.objUid} tbody  tr.selected').attr('value');
			   var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}&invokeButtonUid=${model.objUid}" + "&contextValue=" + selectedValue;
		       callPlatBus({'paras':dealBus});	   
		  
		  /////////////////////////////////////////////////////
		  
			   <#if model.gridModel.service?exists>
		
				  try{
					   var selectedValue = $('#g${model.gridModel.objUid} tbody  tr.selected').attr('value');
					   var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}&invokeButtonUid=${model.objUid}" + "&contextValue=" + selectedValue;
  			      }catch(e){
				  }
			</#if>
	    }
	    loadPml({
   			 	'pml':'${link_url}',
		   		 'title':'${(model.linkPaneModel.title)?if_exists}',
		   		 'formName':'a${model.gridModel.objUid}',
		   		 'target':'_opener_window'}
		);
  });
</script>