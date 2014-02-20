<tr>
	<td colspan='2'>
		<div id='${model.objUid}'></div>
	</td>
</tr>
<script>
	(function(){
	alert(0);
		<#if model.linkPaneModel?exists>
			var p = {
				pml:'${model.linkPaneModel.name}'
				,target:'${model.objUid}'
				,paras:'${model.colName}=${model.value}'
			};
			loadPml(p);
		</#if>
	})();
</script>