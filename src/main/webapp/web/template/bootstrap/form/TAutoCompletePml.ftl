	<div class='input-append'>
		<input  class="resultlistpopup" id="${model.fullColID}" type="hidden" name="${model.colName}"   value="${model.value?if_exists}" />
		<input class="span10 resultlistpopup_show"   type="text" name="${model.colName}_show"  ${validRules?if_exists} changejs="${model.onChangeJs?if_exists}"  serviceName="${model.linkService.name}"  value="${label?if_exists}"/>
		<span class='add-on'><img  class='popupimg'  src='${contextPath}images/darraw.gif' /> </span>
		<span class='add-on'><img  class='popupimg2'  popconfig="${popconfig?if_exists}" src='${contextPath}images/darraw2.gif' /> </span>
	</div>	
<script>
		var combox = $( ".resultlistpopup_show" ).eeplatcombox();
</script>
