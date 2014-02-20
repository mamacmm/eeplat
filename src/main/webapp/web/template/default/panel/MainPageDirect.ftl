<div class=gRi vAlign=top> 
	<div class=mRight id ="mRight">
	
	
<div id="workbench_container">
  <div id="gzt1" class="gztmodel">
    <div id="gztit1" class="title"><div class="icon"></div>
    <div class="titcon">Section1</div></div>
	<div class="gzmid"><div class="midleft"></div>
		<div class="midcon">
		

		
<#if pending?exists> 
<span style="color: #000000;font-size:120%;font-weight:blod;">需要您审批的工作流有&nbsp;${pending} &nbsp;条!</span> &nbsp;&nbsp;&nbsp;<a href="#" onclick="pendingPML();">进入审批页面</a><br>
</#if>

		 			
		</div>
	</div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt2" class="gztmodel">
	<div id="gztit2" class="title"><div class="icon"></div>
	<div class="titcon">Section2</div>	</div>
	<div class="gzmid"><div class="midleft"></div>
	<div class="midcon">
	    		
	</div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt3" class="gztmodel">
	<div id="gztit3" class="title"><div class="icon"></div>
	<div class="titcon">Section3</div></div>
	<div class="gzmid"><div class="midleft">
	</div><div class="midcon"></div>
	</div>
	<div class="gztfoot"><div class="footleft"></div></div>
  </div>
  <div id="gzt4" class="gztmodel">
	<div id="gztit4" class="title"><div class="icon"></div>
	<div class="titcon">Section4</div></div>
	<div class="gzmid"><div class="midleft"></div>
	<div class="midcon"></div></div>
	<div class="gztfoot"><div class="footleft"></div></div>
 </div>
	
	
	
	</div>
 </div>
 
<script language="javascript">

function pendingPML(){

	 loadPml({'pml':'PM_wf_pending',
	 		   'title':'待办列表',
              'target':'_opener_tab'});
	
}

</script>	