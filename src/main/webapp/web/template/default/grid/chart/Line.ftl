<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
  	 
 <div id="${model.objUid}"></div>
 <script>
 
 	 var jsonData = ${jsonData};   
	
	 var placeholder = $('#${model.objUid}').css({'width':'90%' , 'min-height':'300px'});
			  $.plot(placeholder, jsonData, {
				
				series: {
			        lines: {
			            show: true,
			            label: {
			                show: true
			            },
			            steps: true
			        }
			    },
			    legend: {
			        show: true
			    },
			    xaxis: {
		            min: 0,
		            max: 100,
			    }
			 });
 </script>
			