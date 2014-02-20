function createStaticDmLayer(obj,dataStr,configClearOtherUid){

    objGlobals = obj;
	// sc_page_size(每页多少条),sc_page_no(第几页)。

	/////联动菜单清楚其它的

	if(configClearOtherUid!=null){
	
	  var clears = configClearOtherUid.split(",");
	  for(i = 0; i < clears.length; i++){
	      var clearOtherUid = clears[i];
	
	      if(clearOtherUid==""){
	      	continue;
	      }
	      
		  if( $(clearOtherUid)!=null){
	  	  	$(clearOtherUid).value = "";
	  	  }
	  	  if($(clearOtherUid)!=null && $(clearOtherUid).previousSibling!=null){
		  	$(clearOtherUid).previousSibling.value = "";
		  }
	  }
	}


	   	eval("var ret =" + dataStr);
		if(ret!=null && ret.items!=null && ret.items.length>0){
		
			
		    var popHeight = 250;
		    var height = 25*(ret.items.length+1);
		    if(popHeight > height){
		    	popHeight = height;
		    }

		    $("#dmLayer").css("height",popHeight);
		    setTip(obj,popHeight);
		    
		    
  			var dmLayer = "<div>";

  			dmLayer = dmLayer + '<table id="dmLayerTable" class="dmBody" style="word-break:keep-all;font-size:10pt;cursor:pointer">';
						//输出一个空行
			dmLayer = dmLayer + '<tr height="25px"  codeID="" ><td style="padding: 1px;" ></td></tr>';
			//输出具体数据
			for(var i = 0 ;  i < ret.items.length; i++){
	 		    var content = ret.items[i];
	 		   	dmLayer = dmLayer + '<tr height="25px" codeID="'
	 		   	+ content.objuid +
	 		   	'">'
				+' <td style="padding: 1px;" title="' 
				  + unescape(content.name)   +  '">'+
				  unescape(content.name)
				+'</td> </tr> ';
			}
			dmLayer = dmLayer + '</table>';

			$("#dmLayer").empty().append(dmLayer);
			
			
			var dmWTableWidth = $("#dmLayerTable").width();
			if(dmWTableWidth < 220){
				$("#dmLayerTable").css('width',220);
				dmWTableWidth = 220;
			}
			
			$("#dmLayer").css('width',dmWTableWidth);

			
			$(".dmBody tr").bind('click',selInputValue)
 		                 .bind('mouseover',function(){$(this).find("td").addClass("dmLayerMouseOverCss")})
 		                 .bind('mouseout',function(){$(this).find("td").removeClass("dmLayerMouseOverCss")});
 		   // $(".dmBody tr:even").addClass("dmLayerEven"); 

//			mOver($("#dmBody")[0].firstChild.firstChild);
		
	   }else{
			$("#dmLayer").empty().append("&nbsp;&nbsp;&nbsp;&nbsp;没有记录!").show();
	   }
	   
	   $(".dmBody tr:even").css("background","#e6EEEE"); 
//		$('.tablesorter tbody  tr').bind('mouseover',function(){
//			$(this).addClass("mover");
//		}).bind('mouseout',function(){
//			$('.tablesorter tbody  tr').removeClass("mover");
//		});

}



function invokeStaticPopup(obj,dataStr,clearOtherUid){
	

	if($("#dmLayer").css("display")=="none"){
		
		  if(obj!=null){  
		  	obj = $(obj).parent()[0].previousSibling;
		  }
		  
		  var t = $(obj);
		  $("#dmLayer").css("top", t.offset().top -  $(document).scrollTop() + t.height()+10).css("left",t.offset().left);
 	      $("#dmLayer").empty().append("<font color='red'>正在加载.............</font>").show();
		  createStaticDmLayer(obj,dataStr,clearOtherUid);
		  $(document).bind("mouseover",popupHide);
	}else{
		$("#dmLayer").hide(); 	
	}

  
}
















