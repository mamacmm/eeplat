	<div class='input-append'>
		<input  class="resultlistpopup" id="${model.fullColID}" type="hidden" name="${model.colName}"   value="${model.value?if_exists}" />
		<input class="span11  resultlistpopup_show"  ${validRules?if_exists} type="text" name="${model.colName}_show" changejs="${model.onChangeJs?if_exists}" serviceName="${(model.linkService.name)?if_exists}"  value="${label?if_exists}"/>
		<span class='add-on'><img  class='popupimg'  src='${contextPath}images/darraw.gif' /> </span>
	</div>	
<script>
		var combox = $( ".resultlistpopup_show" ).eeplatcombox();
</script>
