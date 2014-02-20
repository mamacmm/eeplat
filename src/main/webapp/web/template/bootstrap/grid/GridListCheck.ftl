<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/> 
<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()>  

 
	<#if model.caption?exists>
		<div class="table-header">
				${model.caption}
		</div>
	</#if> 

	<#if (model.topOutGridFormLinks?size > 0) > 
			<div class="btn-group"> 
									<#list model.topOutGridFormLinks as item>
											${item.htmlValue}
									</#list>
		    </div>
	</#if>
<div class="row-fluid">
<div class='dataTables_wrapper' >

<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>

	<table id='g${model.objUid}' class="table table-striped table-bordered table-hover dataTable"   >
		<thead>
		  <tr>
			<#--隐藏列，数据部分输出记录的主键-->
			<th  style='display:none' ></th>
			<#if model.NO><#--是否有数字序列-->
				<th  align='center' width='5%'  nowrap='nowrap'>${i18n('序号')}</th>
			</#if>
			<th style="align: center"  width='5%' nowrap='nowrap'>
				${i18n('全选')} <input type ='checkbox'   name='checkinstanceheader' 
				id="check_${model.objUid}"/>
			</th>
		<#macro JudgeAlign item>
		    <#if item.align?exists>
		    	align='${item.align}'
		    <#else>
		        align='center' 
		    </#if>
		</#macro>
		<#--输出其它的头标题-->
		<#list model.normalGridFormLinks as item>
            <th id='${item.colName}'  <#if item.width?exists> width='${item.width}'</#if>  <#if item.noWrapLabel>nowrap='nowrap'</#if> <#compress><@JudgeAlign item/> </#compress>>${item.l10n} </th> 
		</#list>
		</tr>
		</thead>
		<#--Table Header部分输出完毕-->
		<tbody>



	   <#list data as ins>
			<tr  value='${ins.uid?if_exists}'  title='${ins.name?if_exists}'>
			<#if model.NO><#--是否有数字序列-->
				<td align='center'>#{ins_index+1}</td>
			</#if>
			
			<td style="text-align: center"  >&nbsp;&nbsp;<input type ='checkbox' title='${ins.name?if_exists}' value='${ins.uid}' class='list_check'  name='checkinstance'  <#if ins.map.eeplat_childtable_selected?? >checked='true' eeplatselected='true'</#if>  />  </td>
			
			
			
			<#list model.normalGridFormLinks as item> 
		            <td  	<#if item.pageStatistics > class="${item.objUid}"</#if>  <#if item.noWrapValue>nowrap='nowrap'</#if> <#compress>  <@JudgeAlign item/></#compress>  style="${item.style?if_exists}" > <#if '${dataBind(ins,item)}' ==''> ${item.htmlValue} </#if> </td> 
			</#list>
			</tr>
	    </#list>



	     
	    <#--本页统计--> 
	    <#if (model.statisticPageOutGridFormLinks?size > 0) >
		    <tr>
				<#if model.NO><#--是否有数字序列-->
					<td align='center'>&nbsp;</td>
				</#if>
				<td style="align: center" >&nbsp; </td>
				<#list model.normalGridFormLinks as item>
					
						<#if item.pageStatistics >
				            <td  <#if item.noWrapValue>nowrap='nowrap'</#if> <#compress>  <@JudgeAlign item/></#compress>  style="${item.style?if_exists}" >
				               <span style="font-weight:bold" id = "${item.objUid}"> </span>
				               <script>
				                   var total = 0;
				                   $(".${item.objUid}").each(function(i,o){
				                   	     if($(o).text()!=null){
					                   	    try{
					                   	      total = total + parseFloat($(o).text());
					                   	     }catch(e){}
				                   	   }
				                   
				                   });
				                   $("#${item.objUid}").html(toDecimal2(total));
				               </script>
				             </td>
				        <#else>
				        	<td align='center'>&nbsp;</td>			        
				        </#if>      
				</#list>
				</tr>
	    </#if>
	     <#if statistics?exists>
	      	<tr>
		      	<#if model.NO><#--是否有数字序列-->
					<td align='center'>统计</td>
				</#if>
				<td colspan="${model.normalGridFormLinks?size + 1}"> ${statistics_details?if_exists}   </td>
	        </tr>
	     </#if>
	     
	     
		</tbody>
		

	</table>
	
	<#if (model.bottomOutGridFormLinks?size > 0) > 
	    <table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="text-align:center" >
				<tr><td align="center" style="text-align:center">
				<#list model.bottomOutGridFormLinks as item> 
				   <#if item.newLine><br/></#if> <#if '${dataBind(null,item)}' ==''>  ${item.htmlValue} </#if> &nbsp; 
				</#list>
				</td></tr>
		</table>
	</#if>
	
	<#if (model.hiddenGridFormLinks?size > 0) > 
				<#list model.hiddenGridFormLinks as item> 
				    ${item.htmlValue}  &nbsp; 
				</#list>
	</#if>
</form>	


</div> <!--End Table Wrapper-->	
</div>


<script language="javascript">
///////////点击全选
		$('#check_${model.objUid}').bind('click',function(e){
		
			var check = $('#check_${model.objUid}').prop("checked");
			if(typeof check == "undefined") {
				check = false;
			}
			
			$('#g${model.objUid} .list_check').each(
			 	function() {
			 		gridCheck(check,this,"#g${model.objUid}",e);
			 		$(this).prop("checked",check);
			 	}
			);
			///同时把第一条记录selected
			if($('#check_${model.objUid}').attr("checked")){
				$('#g${model.objUid} tbody  tr').eq(0).addClass("selected");
			}else{
				$('#g${model.objUid} tbody  tr').eq(0).removeClass("selected");
			}
		});
//////////点击checkbox		
		$('#g${model.objUid} .list_check').bind(  'click',function(e){
			gridCheck($(this).prop('checked'),this,"#g${model.objUid}",e);
		});

		$('#g${model.objUid} tbody  tr').bind('click',function(){
			$('#g${model.objUid} tbody  tr.selected').removeClass("selected");//去掉原来的选中selected
			$(this).addClass("selected");
//			$(this).find(".list_check").attr("checked",true);//点击就选中，容易出现问题
		});
		<#if model.subscribeAll> 
		$('#g${model.objUid} tbody  tr').bind('dblclick',function(){
			var selectedValue = $(this).attr('value');
			var dealBus = "&dataBus=setContext&contextKey=${model.service.bo.name}" + "&contextValue=" + selectedValue;
			$(".toolbar .selected").removeClass("selected");
			$(this).addClass("selected");
		    <#if ((model.service.bo.mainPaneModel.fullCorrHref)?exists) >
				popupDialog('${model.service.bo.mainPaneModel.name}','${model.service.bo.mainPaneModel.title}','${model.service.bo.mainPaneModel.fullCorrHref}' + dealBus,'${model.service.bo.mainPaneModel.paneWidth?if_exists}','${model.service.bo.mainPaneModel.paneHeight?if_exists}')
			</#if> 

		});
		</#if>
		$('#g${model.objUid} tbody  tr').bind('mouseover',function(){
			$(this).addClass("mover");
		}).bind('mouseout',function(){
			$('#g${model.objUid} tbody  tr').removeClass("mover");
		});
		

</script>
