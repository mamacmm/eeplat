<div id="top-bar">
			<img src="${contextPath}simplelayout/images/logo.png" alt="Logo" class="floatleft" />
			<div id="right-side">
			 
			  <#if loginName?exists>
				<a href="#"  class="first">欢迎您，${loginName}</a>&ensp;
				<#if (isMember?exists  && isMember=='true' )>
					<a href="javascript:loadPml({'pml':'PM_club_user_register_modify','target':'pane_content'})">修改</a> &emsp;
					<a href="web/default/logoff.jsp">注销</a> &emsp;
				<#else>
					<a href="javascript:loadPml({'pml':'PM_club_user_register','target':'pane_content'})">注册会员</a> &emsp;
				</#if>
			 <#else>
			  	<a href="openid/weibo/call.jsp"  class="first"><img src="openid/weibo/logos/32.png" alt="Logo" class="floatleft" /> </a>
			 </#if>	
				
			</div>
</div>