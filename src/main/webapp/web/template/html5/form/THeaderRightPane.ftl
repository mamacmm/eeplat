<div id='${model.objUid}' class='html5-header-right html5-middle cursorpt txtcenter'>
	<span class="glyphicon glyphicon-<#if model.image?exists>${model.image}<#else>hand-up</#if>"></span>
</div>

<script type='text/javascript'>
	(function(){
		var jq1 = $('#${model.objUid}');
		<#if model.linkPaneModel?exists>
		var linkpane = '${model.linkPaneModel.name}';
		var target;
		<#if (model.targetPaneModel)?exists>	         
		target = '${model.targetPaneModel.name}';
		</#if>
		jq1.bind('click',function(){
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