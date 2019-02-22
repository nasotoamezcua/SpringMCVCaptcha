jQuery(document).ready(function() {
	
	//obtener captcha		    
	 var captcha = $('#botdetect-captcha').captcha({
	    	captchaEndpoint: __captcha() + 'botdetectcaptcha'
	 });
	 
	 
	 $('#login-form').on('submit', function(e) {
	 	let error = false;
	 	$(this).find('input[name="captchaCode"]').each(function(){
	 		if( $(this).val() == "") {
	 			e.preventDefault();
	 			console.log("Ingresa un valor");
	 			error = true;
	 		}
	 	});

	 	if(error) return;

	 	var captchaCode = $('#captchaCode').val();
	 	console.log(captchaCode);
	 	var captchaId = captcha.getCaptchaId();
	 	console.log(captchaId);
		var postData = {
			captchaCode: captchaCode,
			captchaId: captchaId
		};

		console.log(__captcha() + 'captcha/resController/example/validateJQuery');

		$.ajax({
			method: 'POST',
			url: __captcha() + 'captcha/resController/example/validateJQuery',
			contentType: 'application/json',
			dataType: "json",
			data: JSON.stringify(postData),
			success: function(response) {
				if (response) {
					window.location.href = "menu.html";
				}else{
					console.log("Captcha incorrecto"); captcha.reloadImage();
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log("Error validando captcha!"); captcha.reloadImage();
			}
		});
	 });
    
});