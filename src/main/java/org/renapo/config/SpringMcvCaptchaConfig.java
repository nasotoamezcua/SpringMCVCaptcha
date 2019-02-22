package org.renapo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.captcha.botdetect.web.servlet.SimpleCaptchaServlet;

@Configuration
public class SpringMcvCaptchaConfig {
	
	@Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }
	
	@Bean
	ServletRegistrationBean simpleCaptchaServletRegistration() {
		ServletRegistrationBean srb = new ServletRegistrationBean();
		srb.setServlet(new SimpleCaptchaServlet());
		srb.addUrlMappings("/botdetectcaptcha");
		return srb;
	}

}
