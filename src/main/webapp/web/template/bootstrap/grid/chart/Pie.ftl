<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
 <div id="d${model.objUid}" style="width:100%;height:350px;"></div>
 <script>
 
    require(
        ['echarts'],
        function(ec) {
            var myChart = ec.init(document.getElementById('d${model.objUid}'));
            
            
        option = {
		    title : {
		        text: '${model.caption}',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'right',
		        data: ${names}
		    },
		    toolbox: {
		        show : false,
		        feature : {
		            mark : true,
		            dataView : {readOnly: true},
		            restore : true,
		            saveAsImage : true
		        }
		    },
		    calculable : false,
		    series : [
		        {
		            name:'${model.headTitle}',
		            type:'pie',
		            radius : [0, 100],
		            center: [,225],
		            data:${nameValues}
		        }
		    ]
		};
            
            myChart.setOption(option);
        }
    );
    </script>