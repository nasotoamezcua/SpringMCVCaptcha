package org.renapo.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.renapo.util.SimpleCORSFilter;
import org.springframework.web.WebApplicationInitializer;

public class WebConfig implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		FilterRegistration corsFilterReg = servletContext.addFilter("simpleCORSFilter", SimpleCORSFilter.class);
		corsFilterReg.addMappingForServletNames(null, false, "/*");
	}

}
