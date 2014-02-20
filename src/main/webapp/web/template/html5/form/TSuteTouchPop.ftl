<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<div id='touchpop${model.data.uid}' class='html5-touchpop html5-poppane'>
   <#list model.linkForms as item>
      <#if '${dataBind(model.data,item)}' ==''>
        <#if item.newLine><br/></#if>${item.htmlValue}
      </#if>
   </#list>
</div>
<script>
	(function(sid){
		var jq1 = $('#' + sid);
		jq1.hide();
		jq1.parent().bind('tap',function(a){
			myjs.stopevent(a);
			jq1.show();
			$(document).one('mousedown',function(e){
				myjs.stopevent(e);
				jq1.hide();
			});
			$(document).one('touchstart',function(e){
				myjs.stopevent(e);
				jq1.hide();
			});
		});
	})('touchpop${model.data.uid}');
</script>