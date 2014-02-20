<tr>
	<td>${model.l10n}</td>
	<td>
		<#if model.inputConfig?exists>
			<#if model.inputConfig == 'textarea'>
				<textarea name='${model.colName}' <#if model.isNull?exists><#if model.isNull == 0>class='{required:true}'</#if></#if>>${model.value?if_exists}</textarea>
			<#else>
				<input name='${model.colName}' value='${model.value?if_exists}' type='${model.inputConfig}' <#if model.isNull?exists><#if model.isNull == 0>class='{required:true}'</#if></#if>></input>
			</#if>
		<#else>
			<input name='${model.colName}' value='${model.value?if_exists}' type='text' <#if model.isNull?exists><#if model.isNull == 0>class='{required:true}'</#if></#if>></input>
		</#if>
	</td>
</tr>