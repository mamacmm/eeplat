$(document).ready(function(){

	// Validate
	// http://bassistance.de/jquery-plugins/jquery-plugin-validation/
	// http://docs.jquery.com/Plugins/Validation/
	// http://docs.jquery.com/Plugins/Validation/validate#toptions
	
		$('#contact-form').validate({
	    rules: {
	      name: {
	        minlength: 4,
	        required: true
	        
	      },
	      
	      username: {
	    	minlength: 4,
		    required: true
	      },
	      password: {
	      	minlength: 6,
	        required: true
	      },
	      password2: {
	        minlength: 6,
	        equalTo:"#password", 
	        required: true
	      }
	    },
	    messages: {
	    	   password2: {
	    	    equalTo: "The two passwords you typed do not match."
	    	   }
	    },
	    highlight: function(label) {
	    	$(label).closest('.control-group').addClass('error');
	    },
	    success: function(label) {
	    	label
	    		.text('OK!').addClass('valid')
	    		.closest('.control-group').addClass('success');
	    },
	    submitHandler:function(form){

           
           loading(EELang.loading);
     	   var paras =  $('#contact-form').serialize();
     	   paras = paras + "&callType=uf&contextServiceName=DO_ORG_ACCOUNT_Insert_md5";
    	   ////为了防止多次提交,可以加验证码
     	   $.post(globalURL + "servicecontroller",paras,
     			function (data){
     			    window.location= globalURL + "console.pml?isApp=true";
     	  });
        }    
	  });
}); // end document.ready