package org.renapo.restController;

import javax.servlet.http.HttpServletRequest;

import org.renapo.model.BasicExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.captcha.botdetect.web.servlet.SimpleCaptcha;

@RestController
@RequestMapping("/captcha/resController")
public class SpringMcvCaptchaRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@PostMapping(value ="/example/validateJQuery")
	public ResponseEntity<Boolean>  onSubmit(HttpServletRequest request, @RequestBody BasicExample basicExample) {
		log.info("Entro: onSubmit {}" , basicExample.getCaptchaCode());
		
		SimpleCaptcha captcha  = SimpleCaptcha.load(request, "exampleCaptcha");
		boolean isHuman = captcha.validate(basicExample.getCaptchaCode(),basicExample.getCaptchaId());
		
		if(isHuman) {
			basicExample.setCaptchaCorrect("Captcha Correcto");
			log.info("Mensaje del controlador: {}" , basicExample.getCaptchaCorrect());
			return new ResponseEntity<>(true,HttpStatus.OK);
		}else {
			basicExample.setCaptchaIncorrect("Captcha Invalido");
			log.info("Mensaje del controlador: {}" , basicExample.getCaptchaIncorrect());
			return new ResponseEntity<>(false,HttpStatus.OK);
		}
		
	}

}
