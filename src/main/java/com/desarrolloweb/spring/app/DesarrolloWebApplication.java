package com.desarrolloweb.spring.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DesarrolloWebApplication extends SpringBootServletInitializer{

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DesarrolloWebApplication.class);
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(DesarrolloWebApplication.class, args);
	}	
}
