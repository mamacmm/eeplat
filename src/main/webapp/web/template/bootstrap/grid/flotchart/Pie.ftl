<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
  	 
 <div id="${model.objUid}"></div>
 <script>
 
 	 var jsonData = ${jsonData};   
	
	 var placeholder = $('#${model.objUid}').css({'width':'90%' , 'min-height':'300px'});
			  $.plot(placeholder, jsonData, {
				
				series: {
			        pie: {
			            show: true,
			            label: {
			                show: true,
			                radius: 2/5,
			                formatter: labelFormatter,
			                threshold: 0.1
			            }
			        }
			    },
			    legend: {
			        show: true
			    }
			 });
 </script>
			