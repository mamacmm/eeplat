<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.exedosoft.plat.util.DOGlobals"%>
<%@ page import="com.exedosoft.plat.util.I18n"%>
<div style="overflow:auto;width:100%;height:100%;">
<!-- 创建工程 -->

  <div class="tile-strip seven-wide">
    <div class="live-tile cyan   two-wide">
    	 <div>
			 <%=I18n.instance().get("SomeStep1")%><b><%=I18n.instance().get("SomeStep2")%></b>
			 <%=I18n.instance().get("SomeStep3")%>
				<br/>
				<br/>
				 <div align="center">
				 	<a class="metroExtraLarge" onclick="loadPml({'pml':'PM_DO_Application_Insert','target':'_opener_tab','title':'<%=I18n.instance().get("Build a new application")%>'})" class="ctlBtn" style="font-size:15px;height:30px;">
				 	<%=I18n.instance().get("Build a new application")%></a>
				</div>	
				<span class="tile-title">
			         <%=I18n.instance().get("Help,Detail:")%><a href ="http://code.google.com/p/eeplat/wiki/FirstProjcet" target="_blank"><%=I18n.instance().get("First Step to Create Application.")%>  </a>
			   </span>
		<!-- 预留页面空间，可以增加特定业务向导，如手机、SNS 等......-->
		</div>
	</div>
	
   <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
	  <div class="live-tile green two-wide">
		    <div>
			    <%=I18n.instance().get("data manager utility")%> <%=I18n.instance().get("datamanager mysql command")%>
		    </div>
		    <a  class="tile-title" href="dbmanager/" target="_blank"><%=I18n.instance().get("DataBase Manager")%></a>&nbsp;&nbsp;
	   </div> 
  <%} else { %>
	  <div class="live-tile green">
		  	<div>
			  <%=I18n.instance().get("DataBase Manager")%></a>
		  	</div>
		  	<a  class="tile-title"  href="javascript:loadPml({'pml':'PM_do_datasource_Result','target':'_opener_tab','title':'<%=I18n.instance().get("DataBase Manager")%>'})">
	  </div>
		 <%} %>

  <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
	    <div class="live-tile green">
	    	<div>
		    	<span style="font-size:12px"><%=I18n.instance().get("FromTable2BO")%> </span>
		    </div>
		    <a class="tile-title"  href="javascript:loadPml({'pml':'TabeCreator','target':'_opener_tab','title':'<%=I18n.instance().get("Table Create Wizard")%>'})"><%=I18n.instance().get("Table Create Wizard")%></a>
	    </div>	
	<%} %>

  <% if ("true".equals(DOGlobals.getValue("multi.tenancy"))) { %>
		 <div class="live-tile green">
			 <div>
			 	<%=I18n.instance().get("inittable_note")%>
			</div>
			<a class="tile-title"  href="javascript:loadPml({'pml':'PM_DO_DataSource_getAllTables','target':'_opener_tab','title':'<%=I18n.instance().get("初始化表")%>'})"><%=I18n.instance().get("初始化表")%></a>
		 </div>	
     <%} %>
</div>


<!-- 第一层Tools -->
	<div class="tiles tile-group seven-wide">
	  <div class="tile-strip seven-wide">
				<div class="live-tile cobalt" >
				 	<div>
				 		<a  href="javascript:loadPml({'pml':'PM_do_ui_controller_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Controllers Manager")%>'})">
				 			   <img class="full" src="web/default/workbench/images/AdministrativeTools.png"/>
				 		</a>	   
				 	</div>
			  	 	<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_ui_controller_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Controllers Manager")%>'})"><%=I18n.instance().get("Controllers Manager")%></a>
	            </div>
			    
				<div class="live-tile cobalt">
					<div><a  href="javascript:loadPml({'pml':'PM_do_actionconfig_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Actions Manager")%>'})">
					 <img class="full" src="web/default/workbench/images/action.png"/>
					</a>
				   </div>
				   <a class="tile-title accent"  href="javascript:loadPml({'pml':'PM_do_actionconfig_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Actions Manager")%>'})"><%=I18n.instance().get("Actions Manager")%></a>
				</div>
				<div class="live-tile cobalt">
				 	<div><a  href="javascript:loadPml({'pml':'PM_do_resource_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Resources Manager")%>'})">
				 	 <img class="full" src="web/default/workbench/images/Configure.png"/>
				 	</a>
					</div>
					<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_resource_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Resources Manager")%>'})"><%=I18n.instance().get("Resources Manager")%></a>
				</div>
			    <div class="live-tile cobalt">
			    	<div><a  href="javascript:loadPml({'pml':'PM_do_icon_Main','target':'_opener_tab','title':'<%=I18n.instance().get("JavaScript(Browse)")%>'})">
			    	 <img class="full" src="web/default/workbench/images/application_javascript.png"/>
			    	</a>
			    	</div>
			    	<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_icon_Main','target':'_opener_tab','title':'<%=I18n.instance().get("JavaScript(Browse)")%>'})"><%=I18n.instance().get("JavaScript(Browse)")%></a>
			    </div>
			    <div class="live-tile cobalt">
			    	<div><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_rhino','target':'_opener_tab','title':'<%=I18n.instance().get("JavaScript(Server)")%>'})">
			    	 <img class="full" src="web/default/workbench/images/Java.png"/>
			    	</a>
			    	</div>
			    	<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_icon_Main_rhino','target':'_opener_tab','title':'<%=I18n.instance().get("JavaScript(Server)")%>'})"><%=I18n.instance().get("JavaScript(Server)")%></a>
			    </div>
				<div class="live-tile cobalt">
					<div><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_css','target':'_opener_tab','title':'<%=I18n.instance().get("CSS")%>'})">
					 <img class="full" src="web/default/workbench/images/css.png"/>
					</a>
					</div>
					<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_icon_Main_css','target':'_opener_tab','title':'<%=I18n.instance().get("CSS")%>'})"><%=I18n.instance().get("CSS")%></a>
				</div>
				<div class="live-tile cobalt">
					<div><a  href="javascript:loadPml({'pml':'PM_do_icon_Main_html','target':'_opener_tab','title':'<%=I18n.instance().get("HTML Freemaker")%>'})">
					 <img class="full" src="web/default/workbench/images/HTML5.png"/>
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
	        			 <img class="full" src="web/default/workbench/images/Personalize.png"/>
	        		</a>
	        	</div>
	        	<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_log_dev_Main','target':'_opener_tab','title':'Log'})"><%=I18n.instance().get("Logs")%></a>
	        </div>
	        <div class="live-tile cobalt ">
	        	<div>
	        		<a  href="javascript:loadPml({'pml':'trans_formmodel_setl10n','target':'_opener_tab','title':'<%=I18n.instance().get("I10n")%>'})">
	        			 <img class="full" src="web/default/workbench/images/Update.png"/>
	        		</a>
	        	</div>
	        	<a class="tile-title accent" href="javascript:loadPml({'pml':'trans_formmodel_setl10n','target':'_opener_tab','title':'<%=I18n.instance().get("I10n")%>'})"><%=I18n.instance().get("I10n")%></a>
	        </div>
	    	<div class="live-tile cobalt ">
	    		<div>
	    			<a  href="javascript:loadPml({'pml':'PM_do_pt_processtemplate_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Workflow Designer")%>'})">
	    				 <img class="full" src="web/default/workbench/images/ConnectTo.png"/>
	    			</a>
	    		</div>
	    		<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_pt_processtemplate_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Workflow Designer")%>'})"><%=I18n.instance().get("Workflow Designer")%></a>
	    	</div>
			<div class="live-tile cobalt ">
				<div>
					<a  href="javascript:loadPml({'pml':'PM_DO_BO_Export_Select','target':'_opener_tab','title':'<%=I18n.instance().get("Import Config")%>'})">
						 <img class="full" src="web/default/workbench/images/Switch.png"/>
					</a>
				</div>
				<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_DO_BO_Export_Select','target':'_opener_tab','title':'<%=I18n.instance().get("Import Config")%>'})"><%=I18n.instance().get("Import Config")%></a>
			</div>
		    <div class="live-tile cobalt ">
		    	<div>
		    		<a  href="javascript:loadPml({'pml':'PM_do_org_part_Result','target':'_opener_tab','title':'<%=I18n.instance().get("Organization Config")%>'})">
		    			 <img class="full" src="web/default/workbench/images/User_Accounts.png"/>
		    		</a>
		    	</div>
		    	<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_org_part_Result','target':'_opener_tab','title':'<%=I18n.instance().get("Organization Config")%>'})"><%=I18n.instance().get("Organization Config")%></a>
		    </div>
			<div class="live-tile cobalt ">
				<div>
					<a  href="javascript:loadPml({'pml':'PM_do_code_main_Manager','target':'_opener_tab','title':'<%=I18n.instance().get("Sequence Config")%>'})">
						 <img class="full" src="web/default/workbench/images/RegEdit.png"/>
					</a>
				</div>
				<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_code_main_Manager','target':'_opener_tab','title':'<%=I18n.instance().get("Sequence Config")%>'})"><%=I18n.instance().get("Sequence Config")%></a>
			</div>
			<div class="live-tile cobalt ">
				<div>
					<a  href="javascript:loadPml({'pml':'PM_do_code_item_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Sequence Item Config")%>'})">
						 <img class="full" src="web/default/workbench/images/AdministrativeTools.png"/>
					</a>
				</div>
				<a class="tile-title accent" href="javascript:loadPml({'pml':'PM_do_code_item_Main','target':'_opener_tab','title':'<%=I18n.instance().get("Sequence Item Config")%>'})"><%=I18n.instance().get("Sequence Item Config")%></a>
			</div>
  </div>
  
	
	
</div>