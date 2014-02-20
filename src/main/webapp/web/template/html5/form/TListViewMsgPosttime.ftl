<div id='divpstm${model.data.uid}' class='html5-msgposttime'>${model.value?if_exists}</div>
<script>
	(function(){
		var jdiv1 = $('#divpstm${model.data.uid}');
		var html = jdiv1.html();
		if(html && html !=''){
			jdiv1.html(html.replace(/:\d*\.\d$/,''));
		}
	})();
</script>