		<div class="navbar" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>
			<div class="navbar-inner">
				<div class="container-fluid">
					<a href="#" class="brand">
					   <img border=0 src="${logoheader?default('web/bootstrap/assets/images/eeplat_icon.png')}" />
					</a><!--/.brand-->
					<ul class="nav ace-nav pull-right">
					<!--Project Begin-->
						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-pencil"></i> <b>${title!}</b>
							</a>
						    ${appdropdown}
						</li>
		 <!-- projects  end-->

		 <!-- user profile  begin-->
						<li class="light-blue user-profile">
							<a data-toggle="dropdown" href="#" class="user-menu dropdown-toggle">
								<img class="nav-user-photo" src="web/bootstrap/assets/avatars/empty.gif" alt="${(user.name)?if_exists}'s Photo" />
								<span id="user_info">
									<small>Welcome,</small>
									${(user.name)?if_exists}
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer" id="user_menu">
							   <#if isdev >
								<li>
									<a target='_blank' href="console.pml?isApp=true">
										<i class="icon-cog"></i>
										Setup
									</a>
								</li>
								<li class="divider"></li>
							  </#if>	

								<li>
									<a href="web/default/logoff.jsp">
										<i class="icon-off"></i>
										Logout
									</a>
								</li>
							</ul>
						</li>
		 <!-- user profile  end-->
					</ul><!--/.ace-nav-->
				</div><!--/.container-fluid-->
			</div><!--/.navbar-inner-->
		</div>