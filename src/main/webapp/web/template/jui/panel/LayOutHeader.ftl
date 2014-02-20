<div id="header">
			<div class="headerNav">
				<img name='logoImg' height="40px" width="150px" style="margin-top:5px;margin-left:5px"  src='web/default/images/logo_small_l.png' border='0'>
				
				
				 <ul class="nav">
					 <li><a>欢迎，a ！</a> </li>
					 <li> <a><select class="selectt" onchange="changeApp(this)"><option value="402981eb42a30c5d0142a30cd2b9000a;/eeplat/mvccontroller?paneModelUid=402981eb42a30c5d0142a30cd370000d&amp;isApp=true&quot;">appshare</option><option value="SystemManager;/eeplat/mvccontroller?paneModelUid=402880312788b836012788b842220003&amp;isApp=true&quot;">SystemManager</option></select></a></li>
					 <li><a href="http://code.google.com/p/eeplat" target="_blank">帮助</a></li>
					 <li><a href="web/default/logoff.jsp">退出</a></li>
				 </ul>

				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
</div>



<script language="javascript">

	function logOff(){
		if(confirm('<%=I18n.instance().get("Confirm Exit")%>')){
			window.location.href="web/default/logoff.jsp";
		}
	}

	function helpme(){
   		window.open('http://code.google.com/p/eeplat');
	}

	function setup(){
		window.open("console.pml?isApp=true");
	}
  
	function changeApp(obj){
	  if(obj!=null && obj.value!=null && obj.value!='f' && obj.value!='a'){
		  var datas = obj.value.split(";");
			var url = globalURL + "/servicecontroller?dataBus=setUserContext&contextKey=default_app_uid&contextValue=" +datas[0];
			$.get(url,function(data){window.location = datas[1];});
	  }else if(obj!=null && obj.value=='a'){
		  loadPml({'pml':'PM_multi_appshare_listall','target':'_opener_tab','title':'<%=I18n.instance().get("从AppShare安装")%>'});
	  }
  }
  </script>		
		