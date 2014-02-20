<input id='hidden${model.value}' type='hidden'></input>
<script type='text/javascript'>
	(function(){
		var jq1 = $('#hidden${model.value}');
		var pt = jq1.parent();
		<#if model.linkPaneModel?exists>
		var linkpane = '${model.linkPaneModel.name}';
		var target;
		<#if (model.targetPaneModel)?exists>	         
		target = '${model.targetPaneModel.name}';
		</#if>
		pt.bind('click',function(){
			var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}" + "&contextValue=${model.data.uid}";
	 	    callPlatBus({'paras':dealBus});
			var p = {
				pml:linkpane
			};
			if(target && target != '_opener'){
				p.target = target;
			}
			loadPml(p);
		});
		</#if>
	})();
</script>