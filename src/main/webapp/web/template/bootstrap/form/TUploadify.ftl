 <#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()> 
<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 
 
 
  <div class="myhtml5file span12"> 
	<input type="text"  class="span6" id="${model.objUid}"  name="${model.colName}" title="${model.value?default(i18n('未选择文件'))}"  value="${model.value?default(i18n('未选择文件'))}" />
	<input type="button"  id="${model.objUid}_btn" class="span3" value="${i18n('浏览')}" />
	<input type="file" id="${model.objUid}_show"  name="${model.colName}_show"  value="${model.value?if_exists}"/> 
</div> 

  <script>
////multiple="multiple" 这个属性可以支持多个文件上传	
	   $("#${model.objUid}_show").html5_upload({
	   				autoclear:false,
                    url: "web/default/upload_action_uploadify.jsp",
                    sendBoundary: window.FormData || $.browser.mozilla,
                    onStart: function(event, total) {
                        return true;
                        return confirm("You are trying to upload " + total + " files. Are you sure?");
                    },
                    onProgress: function(event, progress, name, number, total) {
                        console.log(progress, number);
                    }
                });
                
	$("#${model.objUid}_btn").on(
	 "click",
	 function(){
	 	$("#${model.objUid}_show").click();
	 }
	)
  </script>  