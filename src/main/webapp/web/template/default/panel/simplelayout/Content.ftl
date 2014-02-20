
	<div id="zone-bar">
		${items_html}
	</div>
	<div id="page-content">
	
	</div> 
	
	
		
	<script>

		  $("#zone-bar li em").click(function() {
		   		var hidden = $(this).parents("li").children("ul").is(":hidden");
		   		
				$("#zone-bar>ul>li>ul").hide()        
			   	$("#zone-bar>ul>li>a").removeClass();
			   		
			   	if (hidden) {
			   		$(this)
				   		.parents("li").children("ul").toggle()
				   		.parents("li").children("a").addClass("zoneCur");
				   	} 
			   }
			   
			   );
			   
			 $("#zone-bar>ul>li>ul>li").click(function() {
		   	
		   		
				$("#zone-bar>ul>li>ul").hide()        
			   	$("#zone-bar>ul>li>a").removeClass();

			  });	
			  
			  
		var height = $(window).height();
		var width = $(window).width();
		var top1 = $("#page-content").offset().top;
		$("#page-content").css("height",height-top1 );
			  
			   
	</script>
