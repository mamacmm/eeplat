  <div id="fq${model.objUid}"></div>
  <div style="vertical-align: middle">
	  <div style="float:left;margin-top:5px">
	  	<input type="text" readonly="readonly" style="width:185px;height:22px" name="${model.colName}" <#if (model.value?exists)>value="${model.value}"</#if> />
	  </div>	
  	<input  class="uploadify" id="uf${model.objUid}" type='file' value='上传'/>
  
  <div>
  <script>
   	uploadify("uf${model.objUid}","fq${model.objUid}","${model.inputConfig?if_exists}","${model.inputConstraint?if_exists}",true,"${sessionid}","${model.note?if_exists}");
  </script>  