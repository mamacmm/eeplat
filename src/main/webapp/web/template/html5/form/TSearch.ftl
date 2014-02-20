<div class='html5-search'>
	<div class="input-group">
      <input id='search${model.objUid}text' type="text" name='${model.inputConfig?if_exists}' value='${model.value?if_exists}' class="form-control">
      <span class="input-group-btn">
        <button id='search${model.objUid}' class="btn btn-default html5-search-button" type="button">${model.l10n}</button>
      </span>
    </div>
</div>
<script type='text/javascript'>
	(function(sid){
		var jq1 = $('#' + sid);
		var jq2 = $('#' + sid + 'text');
		jq1.click(function(){
				<#if model.linkPaneModel?exists>
					var target;
					<#if (model.targetPaneModel)?exists>	         
						target = '${model.targetPaneModel.name}';
					</#if>
					var paras = null;
					if(jq2.val() && $.trim(jq2.val()) != ''){
						paras = '${model.inputConfig?if_exists}=' + $.trim(jq2.val());
					}
					var p = {
						pml:'${model.linkPaneModel.name}'
						,target:target
						,paras:paras
						,notpush:true
					};
					loadPml(p);
				<#else>
					alert('没有关联面板!');
				</#if>
		});
	})('search${model.objUid}');
</script>