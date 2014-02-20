<div class='html5-msgphoto'><img id='img${model.data.uid}'/></div>
<script>
	(function(){
		var jimg1 = $('#img${model.data.uid}');
		var src = '${model.note?if_exists}';
		var imgkey = '${model.value?if_exists}';
		if(imgkey === ''){
			src = '/eeplat/upload/none4.png';
		}
		jimg1.attr('src',src + imgkey);
	})();
</script>