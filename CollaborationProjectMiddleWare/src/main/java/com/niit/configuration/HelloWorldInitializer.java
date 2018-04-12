package com.niit.configuration;


import java.nio.charset.StandardCharsets;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.niit.config.DBConfig;

import javax.servlet.Filter;
public class HelloWorldInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	
	//Support for Asynchronous Service...
	@Override
	protected void customizeRegistration(javax.servlet.ServletRegistration.Dynamic registration) {
		System.out.println("customizeRegistration");
		registration.setInitParameter("dispatchOptionsRequest", "true");
		registration.setAsyncSupported(true);
	}
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{HelloWorldConfiguration.class,DBConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("I m here 2");
		return new String[]{"/"};
	}
	
	//tHIS function will do the character encoding for messaging
	 @Override
	    protected Filter[] getServletFilters() {
		 CharacterEncodingFilter filter=new CharacterEncodingFilter();
		 filter.setEncoding(StandardCharsets.UTF_8.name());
	        return new Filter[]{filter};
	    }

}
