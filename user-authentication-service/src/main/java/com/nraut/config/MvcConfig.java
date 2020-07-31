package com.nraut.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Nitin
 *
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");		
		registry.addViewController("/list").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("home");
		
	}

}
