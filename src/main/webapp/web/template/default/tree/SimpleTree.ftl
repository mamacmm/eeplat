<div class=tree id='${model.nodeName}'></div>
<script language="javascript">
	webFXTreeHandler.resetContext();
	<#if (actionUrl?exists)>
		var tree = new WebFXLoadTree('${model.l10n}','${treeModelUrl}',"${actionUrl?if_exists}");
		$("#${model.nodeName}").append(tree.toHtml());
	<#else>
		var tree = new WebFXLoadTree('${model.l10n}','${treeModelUrl}');
		$("#${model.nodeName}").append(tree.toHtml());
	</#if>
	resscrEvt();
</script>
 

	
	
