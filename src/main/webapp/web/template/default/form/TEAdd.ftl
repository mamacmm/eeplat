<#include "TFormBase.ftl">
 <button  type="button"  id='${model.objUid}' class='new btn'>${model.l10n?default("新增")}</button>
 <script>
 
   var globali = 2;
  $('#${model.objUid}').bind('click',function(){
      var o = $('#tr${model.gridModel.objUid}').clone();
      o.css("display","")
      o.attr("id","tr" + globali++);
      o.bind('click',function(){
			$('#g${model.gridModel.objUid} tbody  tr.selected').removeClass("selected");//去掉原来的选中selected
			$(this).addClass("selected");
		});
	  o.find(":input").bind('change',function(e){
			o.attr("edit","true");	
	  });
	  o.find(":input").removeAttr("id");
	  $('#g${model.gridModel.objUid}').append(o);
  });
 
 </script>
