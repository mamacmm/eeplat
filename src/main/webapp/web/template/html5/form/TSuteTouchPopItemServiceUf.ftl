<div id='touchitemservice${model.data.uid}' class='html5-touchpop-item'>${model.l10n}</div>
<script type='text/javascript'>
	(function(){
		var jq1 = $('#touchitemservice${model.data.uid}');
		<#if model.linkService?exists>
		jq1.bind('mousedown',function(e){myjs.stopevent(e);});
		jq1.bind('touchstart',function(e){myjs.stopevent(e);});
		jq1.bind('click',function(e){
			myjs.stopevent(e);
			var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}" + "&contextValue=${model.data.uid}";
	 	    callPlatBus({'paras':dealBus});
			callService({'btn':this,
                 <#if (model.note)?exists>
                 'msg':'${model.note}',
                 </#if>
        		 'callType':'uf',
        		 'serviceUid':'${model.linkService.objUid}',
        		 'paras':'invokeButtonUid=${model.objUid}',
		         'callback':function(){

		         },
		         'formName':'${model.targetForms}'
		         <#if (model.linkPaneModel)?exists>
		         ,'pml':'${model.linkPaneModel.name}'
		         	<#if (model.linkPaneModel.title)?exists>
		         		,'title':'${model.linkPaneModel.title}'
		         	 </#if>
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
		         </#if>
		         });
		});
		</#if>
	})();
</script>