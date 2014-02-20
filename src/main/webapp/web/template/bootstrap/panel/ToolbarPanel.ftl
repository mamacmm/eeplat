   <div id="${model.name}_toolbar" class="boot_toolbar"  align="left">
   	 <div class="btn-group"> 
       <#list items as item>
 		<button type="button"   class="btn" id="${item.objUid}"  style="${item.style?if_exists}" >   ${item.title} </button>
		<script>
		  $('#${item.objUid}').bind('click',function(){
			    loadPml({
		
			   			 <#if (item.linkType==5)>
			   			 	'resourcePath':'${item.linkPaneModel.resource.resourcePath}',
			   			 </#if>
			   			 	'pml':'${item.name}',
			   			 	'pmlWidth':'${item.paneWidth?if_exists}',
			   			 	'pmlHeight':'${item.paneHeight?if_exists}',
				   		 'title':'${item.title}',
 						 'target':'${model.name}_content'
					 }
				);
		  });
		</script>
   		</#list>
   		</div>
    </div>
   <div id="${model.name}_content" style="width:100%;overflow:visible"></div>
   <script  language='javascript'>
	  $("#${model.name}_toolbar button:first").click();
  </script>	
	