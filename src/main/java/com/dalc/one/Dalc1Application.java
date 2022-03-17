package com.dalc.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


@SpringBootApplication
public class Dalc1Application {
	public static void main(String[] args) {
		SpringApplication.run(Dalc1Application.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/", "classpath:/public/",
				"classpath:/", "classpath:/resources/", "classpath:/META-INF/resources/",
				"classpath:/META-INF/resources/webjars/", "classpath:/templates/", "classpath:/static/vue/"};
		
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
				.allowedOrigins("http://3.37.127.183:8081")
				.allowedOrigins("http://172.30.1.11:8081") //오류 수정을 위한 임시 IP 
				.exposedHeaders("*")
				//.exposedHeaders("authorization")	//make client read header("jwt-token")
				.allowedMethods(
				HttpMethod.GET.name(),
				HttpMethod.HEAD.name(),
				HttpMethod.POST.name(),
				HttpMethod.PUT.name(),
				HttpMethod.DELETE.name()
			);
			}
			
			@Override
			public void addResourceHandlers(final ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
			}
		};
	}
}
