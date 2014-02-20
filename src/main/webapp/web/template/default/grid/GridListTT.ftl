 <!-js befor loading-->
 <div id='PM_recr_position_insert' style="width:100%;" >
 	    
	<br/>
<div width="100%">

<form  method='post' id='a4028803b3987ccf7013987d7b0e300c4' name ='a4028803b3987ccf7013987d7b0e300c4'>

	<table id='g4028803b3987ccf7013987d7b0e300c4' class='tablesorter' border="0" cellpadding="1" cellspacing="1" >
		<thead>
			<tr> 
				<td class='title' colspan="4"> 
				<img src='/eeplat/web/default/images/MyRightArrow.jpg'/> 
				<b> New </b> </td>
			</tr>
		</thead>	
		
		<tbody>
			
			
			
			<tr   >
				
				    <td    colspan="4"  style='height:25px;background-color:#BDEBEE' > <span style='font-weight:bold'>Information</span></td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Position Title</td>
				    <td    colspan="3" style='' > <input  style='border:#B3B3B3 1px solid;'  onclick="this.style.borderColor='#406B9B'" onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'"  type='text' name='positiontitle' id='gm_recr_position_insert_positiontitle' title='Position Title' class="{required:true} input-xlarge"  size="50"/>&nbsp;<font color='red'>*</font> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Status</td>
				    <td    style='' > 	<input class='resultlistpopup' type='hidden'   name='status' id='status'/><input type='text' style='border:#B3B3B3 1px solid;'  onchange="if(this.value==''){this.previousSibling.value='';}"' onclick="this.style.borderColor='#406B9B'" onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'" name='gm_recr_position_insert_status_show' id='gm_recr_position_insert_status_show'   class="{required:false} input-xlarge"  title='Status' size="20"/><IMG  class='popupimg' onclick=invokeStaticPopup(this,"{items:[{'objuid':'NewPosition','name':'New%20Position'},{'objuid':'PendingApproval','name':'Pending%20Approval'},{'objuid':'Open-Approved','name':'Open%20-%20Approved'},{'objuid':'Closed-Filled','name':'Closed%20-%20Filled'},{'objuid':'Closed-NotApproved','name':'Closed%20-%20Not%20Approved'},{'objuid':'Closed-Canceled','name':'Closed%20-%20Canceled'}]}")  src='/eeplat/web/default/images/darraw.gif' align=absMiddle> </td>
			     
				
				    <td nowrap='nowrap'> Type</td>
				    <td    colspan="1" style='' > 	<input class='resultlistpopup' type='hidden'   name='type' id='type'/><input type='text' style='border:#B3B3B3 1px solid;'  onchange="if(this.value==''){this.previousSibling.value='';}"' onclick="this.style.borderColor='#406B9B'" onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'" name='gm_recr_position_insert_type_show' id='gm_recr_position_insert_type_show'   class="{required:false} input-xlarge"  title='Type' size="20"/><IMG  class='popupimg' onclick=invokeStaticPopup(this,"{items:[{'objuid':'FullTime','name':'Full%20Time'},{'objuid':'PartTime','name':'Part%20Time'},{'objuid':'Internship','name':'Internship'},{'objuid':'Contractor','name':'Contractor'}]}")  src='/eeplat/web/default/images/darraw.gif' align=absMiddle> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Functional Area</td>
				    <td    colspan="3" style='' > 	<input type='hidden' class='resultlistpopup'  name='functionalarea' id='gm_recr_position_insert_functionalarea' serviceName='recr_dictionary_list_Functional_Area'  title='Functional Area' class="{required:true} input-xlarge" /><input  type='text' style='border:#B3B3B3 1px solid;margin-top:1px'  onclick="this.style.borderColor='#406B9B'" onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'" name='gm_recr_position_insert_functionalarea_show' id='gm_recr_position_insert_functionalarea_show'  class="{required:true} input-xlarge"  title='Functional Area' size='20' /><img  class='popupimg' onclick="invokePopup(this,'a4028803b3987ccf7013987d7b0e300c4','label',1,15)"  src='/eeplat/web/default/images/darraw.gif' align=absMiddle />&nbsp;<font color='red'>*</font> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Job Level</td>
				    <td    style='' > 	<input type='hidden' class='resultlistpopup'  name='joblevel' id='gm_recr_position_insert_joblevel' serviceName='recr_dictionary_findbyparentuid'  title='Job Level' class="{required:false} input-xlarge" /><input  type='text' style='border:#B3B3B3 1px solid;margin-top:1px'  onclick="this.style.borderColor='#406B9B'" onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'" name='gm_recr_position_insert_joblevel_show' id='gm_recr_position_insert_joblevel_show'  class="{required:false} input-xlarge"  title='Job Level' size='20' /><img  class='popupimg' onclick="invokePopup(this,'a4028803b3987ccf7013987d7b0e300c4','label',1,15)"  src='/eeplat/web/default/images/darraw.gif' align=absMiddle /> </td>
			     
				
				    <td nowrap='nowrap'> Travel Required</td>
				    <td    style='' > <input name="travelrequired" value="Y"  type="checkbox" class="{required:false} input-xlarge" /> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Hiring Manager</td>
				    <td    style='' > 	<input type='hidden' class='resultlistpopup'  name='hiringmanager' id='gm_recr_position_insert_hiringmanager' serviceName='do_org_user_list'  title='Hiring Manager'/><input  type='text' style='border:#B3B3B3 1px solid;margin-top:1px'  onchange="if(this.value==''){this.previousSibling.value='';}"' onclick="this.style.borderColor='#406B9B'" onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'" name='gm_recr_position_insert_hiringmanager_show' id='gm_recr_position_insert_hiringmanager_show'  class="{required:false} input-xlarge"  title='Hiring Manager' size='25' /><IMG  class='popupimg1' onclick="invokePopup(this,'a4028803b3987ccf7013987d7b0e300c4','name',1,15)"  src='/eeplat/web/default/images/darraw.gif' align=absMiddle /><IMG  class='popupimg2' onclick="invokePopupPml(this,'gm_recr_position_insert_hiringmanager','PM_do_org_user_SelectMain','','','Select Record','a4028803b3987ccf7013987d7b0e300c4','')"  src='/eeplat/web/default/images/darraw2.gif' align=absMiddle /> </td>
			     
				
				    <td nowrap='nowrap'> Location</td>
				    <td    style='' > 	<input type='hidden' class='resultlistpopup'  name='location' id='gm_recr_position_insert_location' serviceName='recr_dictionary_list_Location'  title='Location' class="{required:false} input-xlarge" /><input  type='text' style='border:#B3B3B3 1px solid;margin-top:1px'  onclick="this.style.borderColor='#406B9B'" onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'" name='gm_recr_position_insert_location_show' id='gm_recr_position_insert_location_show'  class="{required:false} input-xlarge"  title='Location' size='20' /><img  class='popupimg' onclick="invokePopup(this,'a4028803b3987ccf7013987d7b0e300c4','label',1,15)"  src='/eeplat/web/default/images/darraw.gif' align=absMiddle /> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Open Date</td>
				    <td    style='' > <input  id='gm_recr_position_insert_opendate' name='opendate' class="{required:false} input-xlarge"  title='Open Date' onClick="WdatePicker({dateFmt:'yyyy-M-d'})"  size="25"/> </td>
			     
				
				    <td    colspan="2"  style='' > &nbsp;</td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Hire By</td>
				    <td    style='' > <input  id='gm_recr_position_insert_hireby' name='hireby' class="{required:false} input-xlarge"  title='Hire By' onClick="WdatePicker({dateFmt:'yyyy-M-d'})"  size="25"/> </td>
			     
				
				    <td    colspan="2"  style='' > &nbsp;</td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Close Date</td>
				    <td    colspan="3" style='' > <input  id='gm_recr_position_insert_closedate' name='closedate' class="{required:false} input-xlarge"  title='Close Date' onClick="WdatePicker({dateFmt:'yyyy-M-d'})"  size="25"/> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td    colspan="4"  style='height:25px;background-color:#BDEBEE' > <span style='font-weight:bold'>Compensation</span></td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Min Pay</td>
				    <td    colspan="3" style='' > <input  style='border:#B3B3B3 1px solid;'  onclick="this.style.borderColor='#406B9B'" onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'"  type='text' name='minpay' id='gm_recr_position_insert_minpay' title='Min Pay' class="{required:true,number:true,remote:'web/default/checkParameter.jsp?parauid=4028803b3987ccf7013987d7300e001f',messages:{remote:''}} input-xlarge"  size="25"/>&nbsp;<font color='red'>*</font> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Max Pay</td>
				    <td    colspan="3" style='' > <input  style='border:#B3B3B3 1px solid;'  onclick="this.style.borderColor='#406B9B'" onmouseover="this.style.borderColor='#99E300'" onmouseout="this.style.borderColor='#A1BCA3'"  type='text' name='maxpay' id='gm_recr_position_insert_maxpay' title='Max Pay' class="{required:true,number:true,remote:'web/default/checkParameter.jsp?parauid=4028803b3987ccf7013987d7300d001e',messages:{remote:'Min Pay should never exceed Max Pay.'}} input-xlarge"  size="25"/>&nbsp;<font color='red'>*</font> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td    colspan="4"  style='height:25px;background-color:#BDEBEE' > <span style='font-weight:bold'>Description</span></td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Job Description</td>
				    <td    colspan="3" style='' > <textarea  name='jobdescription' id='gm_recr_position_insert_jobdescription' title='Job Description' class="{required:false} input-xlarge"  cols="50" rows="5"></textarea> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Responsibilites</td>
				    <td    colspan="3" style='' > <textarea  name='responsibilities' id='gm_recr_position_insert_responsibilities' title='Responsibilites' class="{required:false} input-xlarge"  cols="50" rows="5"></textarea> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Skill Required</td>
				    <td    colspan="3" style='' > <textarea  name='skillsrequired' id='gm_recr_position_insert_skillsrequired' title='Skill Required' class="{required:false} input-xlarge"  cols="50" rows="5"></textarea> </td>
			     
				 	</tr>
				 	<tr   >
				
				    <td nowrap='nowrap'> Education Requirements</td>
				    <td    colspan="3" style='' > <textarea  name='educationalrequirements' id='gm_recr_position_insert_educationalrequirements' title='Education Requirements' class="{required:false} input-xlarge"  cols="50" rows="5"></textarea> </td>
			     
				 	</tr>
				 

				<tr class="buttonMore" > <td  style="text-align:center;align:center"  colspan="4" >
			           <button  type="button"  style=""   id='4028803b3987ccf7013987d7cee600da' class='ctlBtn btn' data-icon='gear' data-theme='b' >Save</button>
<script>
 function fnCB4028803b3987ccf7013987d7cee600da(){
		try{
			if($('#F' + 'PM_recr_position_insert').size()>0){
	  			$('#F' + 'PM_recr_position_insert').dialog('close');
	  		}else{
	  			$('#' + 'PM_recr_position_insert').parents(".ui-dialog-content").dialog('close');
		  	}  	
	  	}catch(e){
	  	}		
	 
	  
   
 }

$('#4028803b3987ccf7013987d7cee600da').bind('click',function(){
        callService({'btn':this,
        		 'callType':'uf',
        		 'serviceUid':'4028803b3987ccf7013987d730550032',
        		 'paras':'invokeButtonUid=4028803b3987ccf7013987d7cee600da',
		         'callback':fnCB4028803b3987ccf7013987d7cee600da,
		         'formName':'a4028803b3987ccf7013987d7b0e300c4'
		         ,'pml':'PM_recr_position_Result'
		         		,'title':'Result'
		         ,'pmlWidth':''
	   			 ,'pmlHeight':''
		         
		         	,'target':'PM_recr_position_Result'
		         });
  }
);
</script>  &nbsp; 
				</td></tr>
		</tbody>
	  </table>
</form>	
</div>	
<script>
function toggleMore(obj){

//this.css('display') == 'none'

    var a = $(obj).parent().parent().nextAll(":not(.buttonMore)");
	a.toggle(a.css('display') == 'none');
	var html = $(obj).text()=="更多信息" ? "<b>更少信息</b>" : "<b>更多信息</b>";
	$(obj).html(html);

}
 

</script>


</div>
 <!-js after loading-->

