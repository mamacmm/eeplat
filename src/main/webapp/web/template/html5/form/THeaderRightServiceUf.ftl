<div id='${model.objUid}' class='html5-header-right html5-middle cursorpt txtcenter'>
	<span class="glyphicon glyphicon-<#if model.image?exists>${model.image}<#else>hand-up</#if>"></span>
</div>

<script type='text/javascript'>
	(function(){
		var jq1 = $('#${model.objUid}');
		<#if model.linkService?exists>
		jq1.bind('click',function(){
			callService({'btn':this,
                 <#if (model.note)?exists>
                 'msg':'${model.note}',
                 </#if>
        		 'callType':'uf',
        		 'serviceUid':'${model.linkService.objUid}',
        		 'paras':'invokeButtonUid=${model.objUid}',
		         'callback':function(){
		         	<#if (!((model.inputConstraint)?exists && model.inputConstraint=='noCloseOpener'))>
		         		html5.popdialog();
		         	</#if>
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