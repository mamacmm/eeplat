<input type="button" style="${model.style?if_exists}"   id="${model.objUid}" value="&nbsp;${model.l10n}&nbsp;" class='ctlBtn btn' >
<script>
  <#if (model.inputConfig?exists && model.inputConfig=="direct")>
	  $('#${model.objUid}').bind('click',function(){
	 		window.open('${appPml}.pml?isApp=true');
	  });
  <#elseif (projects?exists && projects=="true")>
  	  $('#${model.objUid}').bind('click',function(){
  		window.open('projects/');
	  });
  <#else>
	  $('#${model.objUid}').bind('click',function(){
	 		window.open('${appName}/');
	  });
   </#if>	  
</script>