  <div id="fq${model.objUid}"></div>
  <div style="vertical-align: middle">
	  <div style="float:left;margin-top:5px">
	  	<input type="hidden" name="${model.colName}" value="${model.value!}" />
	  	<input type="text"  style="width:185px;height:22px" name="${model.colName}_show" value="${valuetrunc!}" onchange="$(this).prev().val($(this).val())"/>
	  </div>	
  	<input  class="uploadify" id="uf${model.objUid}" type='file' value='上传'/>
  
  <div>
  <script>
    var  uploadurl = "upload_action_uploadify_aliyun.jsp";
   	uploadify("uf${model.objUid}","fq${model.objUid}","${model.inputConfig?if_exists}","${model.inputConstraint?if_exists}",true,"${sessionid}",uploadurl);
  </script>  