package com.yagofx.farolshoppingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FarolshoppingApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(FarolshoppingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FarolshoppingApplication.class);
	}
	
}
