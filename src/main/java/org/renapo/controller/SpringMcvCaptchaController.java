package org.renapo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.renapo.model.BasicExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.botdetect.web.servlet.SimpleCaptcha;

@Controller
@RequestMapping(value = "/captcha/controller")
public class SpringMcvCaptchaController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	//JSP
	@GetMapping(value = "/example/form")
	public ModelAndView showForm() {
		log.info("Entro: showForm");
		return new ModelAndView("basicCaptcha");
	}
	
	@PostMapping(value ="/example/validate")
	public ModelAndView onSubmit(HttpServletRequest request,
			@Valid @ModelAttribute("basicExample") BasicExample basicExample) {
		
		SimpleCaptcha captcha  = SimpleCaptcha.load(request, "exampleCaptcha");
		boolean isHuman = captcha.validate(basicExample.getCaptchaCode());
		
		if(isHuman) {
			basicExample.setCaptchaCorrect("Codigo Correcto");
			basicExample.setCaptchaIncorrect("");
		}else {
			basicExample.setCaptchaCorrect("");
			basicExample.setCaptchaIncorrect("Codigo Incorrecto");
		}
		
		basicExample.setCaptchaCode("");
		
		
		return new ModelAndView("basicCaptcha", "basicExample", basicExample);
	}
	
	//JSP Y JQUEY
	@GetMapping(value = "/example/formjQuery")
	public ModelAndView showFormJQuery() {
		log.info("Entro: showForm");
		return new ModelAndView("basicCaptchaJQuery");
	}
	
	@PostMapping(value ="/example/validateQuery")
	public ModelAndView onSubmitJquery(HttpServletRequest request,
			@Valid @ModelAttribute("basicExample") BasicExample basicExample) {
		
		SimpleCaptcha captcha  = SimpleCaptcha.load(request, "exampleCaptcha");
		boolean isHuman = captcha.validate(basicExample.getCaptchaCode());
		
		if(isHuman) {
			basicExample.setCaptchaCorrect("Codigo Correcto");
			basicExample.setCaptchaIncorrect("");
		}else {
			basicExample.setCaptchaCorrect("");
			basicExample.setCaptchaIncorrect("Codigo Incorrecto");
		}
		
		basicExample.setCaptchaCode("");
		
		
		return new ModelAndView("basicCaptchaJQuery", "basicExample", basicExample);
	}

}
