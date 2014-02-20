<div id='touchitempane${model.data.uid}' class='html5-touchpop-item'>${model.l10n}</div>
<script type='text/javascript'>
	(function(){
		var jq1 = $('#touchitempane${model.data.uid}');
		<#if model.linkPaneModel?exists>
		var linkpane = '${model.linkPaneModel.name}';
		var target;
		<#if (model.targetPaneModel)?exists>	         
		target = '${model.targetPaneModel.name}';
		</#if>
		jq1.bind('mousedown',function(e){myjs.stopevent(e);});
		jq1.bind('touchstart',function(e){myjs.stopevent(e);});
		jq1.bind('click',function(e){
			myjs.stopevent(e);
			var dealBus = "dataBus=setContext&contextKey=${model.gridModel.service.bo.name}" + "&contextValue=${model.data.uid}";
	 	    callPlatBus({'paras':dealBus});
			var p = {
				pml:linkpane
			};
			if(target && target != '_opener'){
				p.target = target;
			}
			loadPml(p);
			$(document).trigger('poppanehide');
		});
		</#if>
	})();
</script>