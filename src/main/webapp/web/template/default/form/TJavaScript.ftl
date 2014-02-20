<button type="button"  class='ctlBtn btn'  style="${model.style?if_exists}"   id='${model.objUid}' >&nbsp;${model.l10n}&nbsp;</button>
<script>
$('#${model.objUid}').bind('click',function(){
	  <#if (model.doClickJs?exists)>	  
      	eval("${model.doClickJs}");
      </#if>
		<#if ((model.inputConfig)?exists && model.inputConfig=='closeTab')>
				var tabBtnSelector = "#dvTab  .on .btnTab";
		  		tabCloseWindow(tabBtnSelector);
		 </#if>
	  
	   <#if ( (model.linkPaneModel)?exists && (model.targetPaneModel)?exists )>
  	  	 $("#${model.targetPaneModel.name}").empty().load("${model.linkPaneModel.name}.pml");
	   </#if>	
	   }
);
</script>