<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.util.I18n"%>
<div style="overflow:auto;width:100%;height:100%;">
<!-- 创建工程 -->

  <div class="tile-strip seven-wide">
  
    	 <div class="live-tile cobalt" >
				 	<div>
				 		<a  href="javascript:loadPml({'pml':'PM_DO_Application_Insert','target':'_opener_tab','title':'<%=I18n.instance().get("Build a new application")%>'})" >
				 			   <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/AdministrativeTools.png"/>
				 		</a>	   
				 	</div>
			  	 	<a class="tile-title accent"  href="javascript:loadPml({'pml':'PM_DO_Application_Insert','target':'_opener_tab','title':'<%=I18n.instance().get("Build a new application")%>'})" ><%=I18n.instance().get("New Application")%></a>
	     </div>
	
   <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
	  <div class="live-tile cobalt">
			<div>
					<a href="dbmanager/"  target="_blank">
			 			   <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/database.png"/>
			 		</a>	   
			</div>
		    <a  class="tile-title accent" href="dbmanager/" target="_blank"><%=I18n.instance().get("DataBase Manager")%></a>&nbsp;&nbsp;
	   </div> 
  <%} else { %>
	  <div class="live-tile cobalt">
		     <div>
						<a   href="javascript:loadPml({'pml':'PM_do_datasource_Result','target':'_opener_tab','title':'<%=I18n.instance().get("DataBase Manager")%>'})">
				 			   <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/database.png"/>
				 		</a>	   
				 </div>

		  	<a  class="tile-title accent"  href="javascript:loadPml({'pml':'PM_do_datasource_Result','target':'_opener_tab','title':'<%=I18n.instance().get("DataBase Manager")%>'})"><%=I18n.instance().get("DataBase Manager")%></a>
	  </div>
		 <%} %>

  <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
	    <div class="live-tile cobalt">
				 	<div>
						<a  href="javascript:loadPml({'pml':'TabeCreator','target':'_opener_tab','title':'<%=I18n.instance().get("Table Create Wizard")%>'})">
				 			   <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/table_create.png"/>
				 		</a>	   
				 	</div>
		    <a class="tile-title accent"  href="javascript:loadPml({'pml':'TabeCreator','target':'_opener_tab','title':'<%=I18n.instance().get("Table Create Wizard")%>'})"><%=I18n.instance().get("Table Create Wizard")%></a>
	    </div>	
	<%} %>

  <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
		 <div class="live-tile cobalt">
				 	<div>
						<a   href="javascript:loadPml({'pml':'PM_DO_DataSource_getAllTables','target':'_opener_tab','title':'<%=I18n.instance().get("初始化表")%>'})">
				 			   <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/table_init.png"/>
				 		</a>	   
				 	</div>
			<a class="tile-title accent"  href="javascript:loadPml({'pml':'PM_DO_DataSource_getAllTables','target':'_opener_tab','title':'<%=I18n.instance().get("初始化表")%>'})"><%=I18n.instance().get("初始化表")%></a>
		 </div>	
     <%} %>
     
       <div class="live-tile cobalt" >
				 	<div>
				 		<a  href="javascript:loadPml({'pml':'PM_do_resource_change_logo','target':'_opener_tab','title':'<%=I18n.instance().get("Change Logo")%>'})" >
				 			   <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/logo.png"/>
				 		</a>	   
				 	</div>
			  	 	<a class="tile-title accent"  href="javascript:loadPml({'pml':'PM_do_resource_change_logo','target':'_opener_tab','title':'<%=I18n.instance().get("Change Logo")%>'})" ><%=I18n.instance().get("Change Logo")%></a>
	    </div>
	    
	    <div class="live-tile cobalt" >
				 	<div>
				 		<a  href="javascript:loadPml({'pml':'appshare','target':'_opener_tab','title':'<%=I18n.instance().get("AppShare应用商店")%>'})" >
				 			   <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/a.png"/>
				 		</a>	   
				 	</div>
			  	 	<a class="tile-title accent"  href="javascript:loadPml({'pml':'appshare','target':'_opener_tab','title':'<%=I18n.instance().get("AppShare应用商店")%>'})" ><%=I18n.instance().get("AppShare应用商店")%></a>
	    </div>
</div>


<!-- 第一层Tools -->
	<div class="tiles tile-group seven-wide">
	  <div class="tile-strip seven-wide">
				<div class="live-tile cobalt" >
				 	<div>
				 		<a  href="javascript:loadPml({'pml':'PM_do_ui_controller_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Controllers Manager")%>'})">
				 			   <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/controller.png"/>
				 		</a>	   
				 	</div>
			  	 	<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_ui_controller_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Controllers Manager")%>'})"><%=I18n.instance().get("Controllers Manager")%></a>
	            </div>
			    
				<div class="live-tile cobalt">
					<div><a  href="javascript:loadPml({'pml':'PM_do_actionconfig_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Actions Manager")%>'})">
					 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/Actionenter.png"/>
					</a>
				   </div>
				   <a class="tile-title accent"  href="javascript:loadPml({'pml':'PM_do_actionconfig_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Actions Manager")%>'})"><%=I18n.instance().get("Actions Manager")%></a>
				</div>
				<div class="live-tile cobalt">
				 	<div><a  href="javascript:loadPml({'pml':'PM_do_resource_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Resources Manager")%>'})">
				 	 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/Configure.png"/>
				 	</a>
					</div>
					<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_resource_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Resources Manager")%>'})"><%=I18n.instance().get("Resources Manager")%></a>
				</div>
			    <div class="live-tile cobalt">
			    	<div><a  href="javascript:loadPml({'pml':'PM_do_icon_Main','target':'_opener_tab','title':'<%=I18n.instance().get("JavaScript(Browse)")%>'})">
			    	 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/application_javascript.png"/>
			    	</a>
			    	</div>
			    	<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_icon_Main','target':'_opener_tab','title':'<%=I18n.instance().get("JavaScript(Browse)")%>'})"><%=I18n.instance().get("JavaScript(Browse)")%></a>
			    </div>
			    <div class="live-tile cobalt">
			    	<div><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_rhino','target':'_opener_tab','title':'<%=I18n.instance().get("JavaScript(Server)")%>'})">
			    	 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/Java.png"/>
			    	</a>
			    	</div>
			    	<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_icon_Main_rhino','target':'_opener_tab','title':'<%=I18n.instance().get("JavaScript(Server)")%>'})"><%=I18n.instance().get("JavaScript(Server)")%></a>
			    </div>
				<div class="live-tile cobalt">
					<div><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_css','target':'_opener_tab','title':'<%=I18n.instance().get("CSS")%>'})">
					 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/css.png"/>
					</a>
					</div>
					<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_icon_Main_css','target':'_opener_tab','title':'<%=I18n.instance().get("CSS")%>'})"><%=I18n.instance().get("CSS")%></a>
				</div>
				<div class="live-tile cobalt">
					<div><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_html','target':'_opener_tab','title':'<%=I18n.instance().get("HTML Freemaker")%>'})">
					 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/HTML5.png"/>
					</a>
					</div>
					<a class="tile-title accent"  href="javascript:loadPml({'pml':'PM_do_icon_Main_html','target':'_opener_tab','title':'<%=I18n.instance().get("HTML Freemaker")%>'})"><%=I18n.instance().get("HTML Freemaker")%></a>
				</div>
	  </div>
	</div>
	
<!-- 第二层Tools -->	
	 <div class="tile-strip seven-wide">
	        <div class="live-tile cobalt ">
	        	<div>
	        		<a  href="javascript:loadPml({'pml':'PM_do_log_dev_Main','target':'_opener_tab','title':'Log'})">
	        			 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/Personalize.png"/>
	        		</a>
	        	</div>
	        	<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_log_dev_Main','target':'_opener_tab','title':'Log'})"><%=I18n.instance().get("Logs")%></a>
	        </div>
	        <div class="live-tile cobalt ">
	        	<div>
	        		<a  href="javascript:loadPml({'pml':'trans_formmodel_setl10n','target':'_opener_tab','title':'<%=I18n.instance().get("I10n")%>'})">
	        			 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/Update.png"/>
	        		</a>
	        	</div>
	        	<a class="tile-title accent" href="javascript:loadPml({'pml':'trans_formmodel_setl10n','target':'_opener_tab','title':'<%=I18n.instance().get("I10n")%>'})"><%=I18n.instance().get("I10n")%></a>
	        </div>
	    	<div class="live-tile cobalt ">
	    		<div>
	    			<a  href="javascript:loadPml({'pml':'PM_do_pt_processtemplate_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Workflow Designer")%>'})">
	    				 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/ConnectTo.png"/>
	    			</a>
	    		</div>
	    		<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_pt_processtemplate_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Workflow Designer")%>'})"><%=I18n.instance().get("Workflow Designer")%></a>
	    	</div>
			<div class="live-tile cobalt ">
				<div>
					<a  href="javascript:loadPml({'pml':'PM_DO_BO_Export_Select','target':'_opener_tab','title':'<%=I18n.instance().get("Import Config")%>'})">
						 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/Switch.png"/>
					</a>
				</div>
				<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_DO_BO_Export_Select','target':'_opener_tab','title':'<%=I18n.instance().get("Import Config")%>'})"><%=I18n.instance().get("Import Config")%></a>
			</div>
		    <div class="live-tile cobalt ">
		    	<div>
		    		<a  href="javascript:loadPml({'pml':'PM_do_org_part_Result','target':'_opener_tab','title':'<%=I18n.instance().get("Organization Config")%>'})">
		    			 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/User_Accounts.png"/>
		    		</a>
		    	</div>
		    	<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_org_part_Result','target':'_opener_tab','title':'<%=I18n.instance().get("Organization Config")%>'})"><%=I18n.instance().get("Organization Config")%></a>
		    </div>
			<div class="live-tile cobalt ">
				<div>
					<a  href="javascript:loadPml({'pml':'PM_do_code_manager','target':'_opener_tab','title':'<%=I18n.instance().get("Sequence Config")%>'})">
						 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/RegEdit.png"/>
					</a>
				</div>
				<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_code_manager','target':'_opener_tab','title':'<%=I18n.instance().get("Sequence Config")%>'})"><%=I18n.instance().get("Sequence Config")%></a>
			</div>
			
			<div class="live-tile cobalt ">
				<div>
					<a  href="javascript:loadPml({'pml':'PM_do_jobconfig_result','target':'_opener_tab','title':'<%=I18n.instance().get("Job Config")%>'})">
						 <img class="full" src="<%=request.getContextPath()%>/web/default/workbench/images/job.png"/>
					</a>
				</div>
				<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_jobconfig_result','target':'_opener_tab','title':'<%=I18n.instance().get("Job Config")%>'})"><%=I18n.instance().get("Job Config")%></a>
			</div>
  </div>
  
	
	
</div>