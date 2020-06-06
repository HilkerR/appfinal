package com.desarrolloweb.spring.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/index").setViewName("index");
	}

}