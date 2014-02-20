<#include "TFormBase.ftl">
<button type="button"  style="${model.style?if_exists}"  id='${model.objUid}' <#compress><@JudgeStyle model/></#compress>   <#compress><@JudgeBootIcon model/></#compress> >  ${model.l10n}</button>

<script>

$('#${model.objUid}').bind('click',function(){
	<#if (model.gridModel.containerPane.name)?exists>
	  	/// close tab
		try{
				$("#li${model.gridModel.containerPane.name}").remove();
				$("#pc${model.gridModel.containerPane.name}").remove();
				$(".nav-tabs>li:last>a").trigger("click");
		}catch(e){
				  	
		}
  	</#if>
  }
);
</script>