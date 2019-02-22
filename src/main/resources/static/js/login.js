jQuery(document).ready(function() {
	
	//obtener captcha		    
	 var captcha = $('#botdetect-captcha').captcha({
	    	captchaEndpoint: __captcha() + 'botdetectcaptcha'
	 });
    
});