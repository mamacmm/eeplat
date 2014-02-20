 <#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()> 
<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 
 
   <div class="myhtml5file span12">
  	<input type="hidden"   name="${model.colName}" value="${model.value!}" /> 
	<input type="text"  class="span6" id="${model.fullColID}"  name="${model.colName}_show1" title="${valuetrunc?default(i18n('未选择文件'))}"  value="${valuetrunc?default(i18n('未选择文件'))}" />
	<input type="button"  id="${model.fullColID}_btn" class="span3" value="${i18n('浏览')}" />
	<input type="file" id="${model.fullColID}_show"  name="${model.colName}_show"  value="${model.value?if_exists}"/>
	<span style="color:red">${model.note!}</span> 
</div> 

  <script>
  
    {
       var  anuuid = generateUUID();
        $("#${model.fullColID}").data("uuid",anuuid);
////multiple="multiple" 这个属性可以支持多个文件上传	
	   $("#${model.fullColID}_show").html5_upload({
	   				autoclear:false,
	   				uuid:$("#${model.fullColID}").data("uuid"),
                    url: "web/default/upload_action_uploadify_aliyun.jsp?uuid="+ $("#${model.fullColID}").data("uuid"),
                    sendBoundary: window.FormData || $.browser.mozilla,
                    onFinishOne : function(event, progress, name, number, total) {
						$("#img${model.fullColID}").attr("src","http://eeplatfile.oss-cn-hangzhou.aliyuncs.com/" + encodeURIComponent( name + ";") + $("#${model.fullColID}").data("uuid") );                 
                    }
                });
     }         
                
	$("#${model.fullColID}_btn").bind(
	 "click",
	 function(){
	 	$("#${model.fullColID}_show").click();
	 }
	)
  </script>  