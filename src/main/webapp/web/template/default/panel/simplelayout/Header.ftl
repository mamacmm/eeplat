<div id="top-bar">
			<!-- <img src="${contextPath}simplelayout/images/logo.png" alt="Logo" class="floatleft" /> -->
			<span class="floatleft" style="color:white;padding-top:15px;font-size:25px">Simple Layout</span>
			<div id="right-side">
			 
			  <#if loginName?exists>
				<a href="#"  class="first">Welcome，${loginName}</a>&nbsp;
				<#if (isMember?exists)>
					<a href="web/default/logoff.jsp">Layout</a> 
				</#if>
			 </#if>	
			</div>
</div>