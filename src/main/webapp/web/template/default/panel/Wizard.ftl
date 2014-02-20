    <div id="${model.name}" width=100% height=100%>
            	<#list items as item>
            	  <h2>${item.title}</h2>
            	  <section data-mode="iframe" data-url="${item.fullCorrHref}&isApp=true">
                 </section>
                </#list>
    </div>
        
	<script  language='javascript'>
		$('#${model.name}').steps({
		        headerTag: "h2",
		        bodyTag: "section",
		        forceMoveForward:true,
		       enableFinishButton: false, 
		        transitionEffect: "fade",
		        labels:settings.labels}
		);
		resscrEvt();
	</script>