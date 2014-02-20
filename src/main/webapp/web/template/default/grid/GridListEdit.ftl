<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<#--开始输出空行-->
<#if model.numTopP?exists>
	<#list 1..model.numTopP as x>  
		<br/>
    </#list>
<#else>  <#--没有定义的话，输出一个空行-->  
	<br/>
</#if>
 

<div width="100%" height="100%">
<#if model.caption?exists>
	 <div class="gridTitle" >
			
							<#assign icon = (model.icon)?default("MyRightArrow.jpg")/>
		 					<img src='${contextPath}images/${icon}'/> <b> ${model.caption} </b> 
		
	 </div>
</#if> 		
<#if (model.topOutGridFormLinks?size > 0) > 
	<DIV class="toolbar" style="BORDER-RIGHT: #8db2e3 1px solid; BORDER-TOP: #8db2e3 1px solid; BORDER-LEFT: #8db2e3 1px solid; BORDER-BOTTOM: #8db2e3 1px solid">
		<DIV align="left"><!--布局用-->
			<TABLE>
				<TBODY>
					<TR>
						<TD style="WIDTH: 2px"></TD><!--左缩进-->
						<#list model.topOutGridFormLinks as item>
							<TD>
								<TABLE  cellSpacing='0' cellPadding='0'>
									<TBODY>
										<TR>
											<TD class="b_left"></TD>
											<TD class="b_center">${item.htmlValue}</TD>
											<TD class="b_right"></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
							<#if ((item_index+1)!=(model.topOutGridFormLinks?size))>
							<TD><SPAN class="spacer"></SPAN></TD><!--分隔条-->
							</#if>
						</#list>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
	</DIV>
</#if>

<table style="display:none">

		<tr id="tr${model.objUid}"  edit="false" style="display:none">
			<#if model.NO><#--是否有数字序列-->
				<td align='center'></td>
			</#if>
			<#if model.checkBox><#--定义CheckBox-->
				<td style="align: center" >&nbsp;&nbsp;<input type ='checkbox' class='list_check'  name='checkinstance'/> <input type ='hidden'  name='checkinstance_hidden'/> </td>
			<#elseif model.radio>
				<td align='center'><input type ='radio'  name='checkinstance'/>   <input type ='hidden'  name='checkinstance_hidden'/>  </td>
			</#if>
			<#--输出其它的头标题 ins:{'l10n':'中国','name':'china','location':'a'}    item:{'key':'l10n'}--> 
			<#list model.normalGridFormLinks as item> 
		            <td  <#if item.noWrapValue>nowrap='nowrap'</#if> <#compress>  <@JudgeAlign item/></#compress>  style="${item.style?if_exists}" >  ${item.htmlValue}  </td> 
			</#list>
		</tr>
</table>

<form  method='post' id='a${model.objUid}' name ='a${model.objUid}'>

	<table id='g${model.objUid}' class='tablesorter' border="0" cellpadding="1" cellspacing="1" >
		<thead>
		  <tr>
			<#--隐藏列，数据部分输出记录的主键-->
			<th  style='display:none' class="{sorter: false}" ></th>
			<#if model.NO><#--是否有数字序列-->
				<th  align='center' width='5%' class="{sorter: 'digit'}" nowrap='nowrap'>序号</th>
			</#if>
		<#if model.checkBox><#--定义CheckBox-->
			<th style="align: center"  width='5%' nowrap='nowrap' class="{sorter: false}">
				${i18n('全选')} <input type ='checkbox'   name='checkinstanceheader' 
				id="check_${model.objUid}"/>
			</th>
		</#if>
		<#--定义宏 判断输出什么类型的align-->
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
			<tr  value='${ins.uid?if_exists}' edit="false" title='${ins.name?if_exists}'>
			<#if model.NO><#--是否有数字序列-->
				<td align='center'>#{ins_index+1}</td>
			</#if>
			<#if model.checkBox><#--定义CheckBox-->
				<td style="align: center" >&nbsp;&nbsp;<input type ='checkbox' title='${ins.name?if_exists}' value='${ins.uid}' class='list_check'  name='checkinstance'/> <input type ='hidden' value='${ins.uid}' name='checkinstance_hidden'/> </td>
			<#elseif model.radio>
				<td align='center'><input type ='radio' title='${ins.name?if_exists}' value='${ins.uid}'  name='checkinstance'/>   <input type ='hidden' value='${ins.uid}' name='checkinstance_hidden'/>  </td>
			</#if>
			<#--输出其它的头标题 ins:{'l10n':'中国','name':'china','location':'a'}    item:{'key':'l10n'}--> 
			<#list model.normalGridFormLinks as item> 
		            <td  <#if item.noWrapValue>nowrap='nowrap'</#if> <#compress>  <@JudgeAlign item/></#compress>  style="${item.style?if_exists}" > <#if '${dataBind(ins,item)}' ==''> ${item.htmlValue} </#if> </td> 
			</#list>
			</tr>
	     </#list>
	     
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


</div>	

<script language="javascript">

		$('#check_${model.objUid}').bind('click',function(){
			var check = $('#check_${model.objUid}').attr("checked");
			if(typeof check == "undefined") {
				check = false;
			}
			$('#g${model.objUid} .list_check').attr('checked',check);
			///同时把第一条记录selected
			if($('#check_${model.objUid}').attr("checked")){
				$('#g${model.objUid} tbody  tr').eq(0).addClass("selected");
			}else{
				$('#g${model.objUid} tbody  tr').eq(0).removeClass("selected");
			}
		});
		
		$('#g${model.objUid} .list_check').bind('click',function(e){
			if(!$(this).attr('checked')){
				$(this).parent().parent().removeClass("selected");
				if($('#g${model.objUid} .selected').size()==0){
					$('#g${model.objUid} .list_check:checked:first').parent().parent().addClass("selected");				
				}
				e.stopPropagation();
			}
		});
		
		////一旦修改则置为true						
		$('#g${model.objUid} :input').bind('change input propertychange',function(e){
					$(this).parent().parent().attr("edit","true");				
			}
		);

		$('#g${model.objUid} tbody  tr').bind('click',function(){
			$('#g${model.objUid} tbody  tr.selected').removeClass("selected");//去掉原来的选中selected
			$(this).addClass("selected");
		});
		$('#g${model.objUid} tbody  tr').bind('mouseover',function(){
			$(this).addClass("mover");
		}).bind('mouseout',function(){
			$('#g${model.objUid} tbody  tr').removeClass("mover");
		});


</script>
