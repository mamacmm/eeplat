			<div class="main-content" id="main-content" >
				<div class="breadcrumbs" id="breadcrumbs">

						<script type="text/javascript">
								try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
							<ul class="nav nav-tabs" id="myTab">
											<li class="active">
												<a data-toggle="tab" pmlName="_opener_tab" href="#_opener_tab">
													<i class="green icon-home bigger-110"></i>
													Home
												</a>
											</li>
							</ul>


				</div>
				<div id="_opener_tab" class="page-content">
				
				 <#if items_html?exists >
				 	${items_html}
				 <#else>
<!-----------------------------------------------begin Default  MainPage------------------------------------------------------------------------------>				 
					<div class="row-fluid">
						<!--PAGE CONTENT BEGINS HERE-->
	
						<div class="alert alert-block alert-success">
							<button  type="button"  class="close" data-dismiss="alert">
								<i class="icon-remove"></i>
							</button>

							<i class="icon-ok green"></i>

         Welcome to							<strong class="green">
								EEPlat
								<small>(v5.0)</small></strong>, the full meta-data driven,multi-tenant,enterprise application development&runtime platform..
						</div>

						<div class="space-6"></div>
					</div><!--/row-->
				</#if>	
<!-----------------------------------------------end  Default  MainPage------------------------------------------------------------------------------>				 
				</div><!--/#page-content-->
			</div><!--/#main-content-->
			
			<script>
				eeplatTabs();
			</script>
						