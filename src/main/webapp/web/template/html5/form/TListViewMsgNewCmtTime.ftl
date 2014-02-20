<div id='divnct${model.data.uid}' class='html5-msgnewcmttime'><#if model.value?exists>最新评论时间：${model.value}<#else><span class='html5-whitespace'>&#32;</span></#if></div>

<script>
	(function(){
		var jdiv1 = $('#divnct${model.data.uid}');
		var html = jdiv1.html();
		if(html && html !=''){
			jdiv1.html(html.replace(/:\d*\.\d$/,''));
		}
	})();
</script>