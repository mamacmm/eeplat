<#assign datatojson = "com.exedosoft.plat.template.Data2Json"?new()/> 
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<div id='${model.objUid}' class='wh100'>

</div>

<script>

(function(sid){
	var jq1 = $('#' +　sid);
	console.log(sid);
	var myChart = echarts.init(jq1.get(0));
	option = {
			    title : {
			        text: '${model.caption}',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        x : 'bottom',
			        data: []
			    },
			    toolbox: {
			        show : true,
                    feature : {
                        //mark : true,
                        dataView : {readOnly: false},
                        magicType:['line', 'bar'],
                        restore : true,
                        saveAsImage : true
                    }
			    },
			    calculable : false,
			    xAxis : [
			        {
			        	type : 'category',
			            data : ${names},
			           axisLabel:{
			            	margin:5,
			            	interval:0,
			            	formatter:function(value){if(value.length>12){ return value.substring(0,10)+"..." } else return value; }
			            }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            
			        }
			    ],
			    series : [
			        {
			            name:'Value',
			            type:'bar',
			            data: ${values}
			        }
			    ]
			};
            myChart.setOption(option);
            $(window).resize(function(){
				myChart.resize();
			});
})('${model.objUid}');
</script>