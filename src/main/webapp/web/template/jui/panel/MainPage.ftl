		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				
				
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="pageFormContent" id="eeplatMainPage" layoutH="80" style="margin-right:230px">
						<!-- 主页内容写在这里 -->
						   <#if resourceUrl?? >
							   <script> 
										loadPml({
											'pml' : '${resourceUrl}',
											'target' : 'eeplatMainPage'
										})
								</script>
							</#if>
						<!-- 主页内容写在这里 -->
						</div>
					</div>
				</div>	

			</div>
		</div>