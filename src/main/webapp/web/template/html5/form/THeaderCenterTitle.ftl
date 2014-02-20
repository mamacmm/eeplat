<div class='html5-header-center  html5-middle txtcenter fontbold'>
	<div>
	<#if model.value?exists>
		${model.value}
	<#else>
		${model.l10n}
	</#if>
	</div>
</div>