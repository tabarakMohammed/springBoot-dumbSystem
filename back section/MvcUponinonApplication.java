package com.example.Uponinon;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import servicesMethod.controlCenterServces;
import servicesMethod.forgotPasswordService;
import servicesMethod.userServces;
import servicesMethod.verfiyEmailService;
import servicesMethod.servicesFile.CStorgeServces;
import servicesMethod.servicesFile.StorageProperties;
import servicesMethod.servicesFile.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class MvcUponinonApplication extends WebMvcConfigurationSupport {
	
	public static void main(String[] args) {
		SpringApplication.run(MvcUponinonApplication.class, args);
	}
	
	@Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    String myExternalFilePath = "file:D:profileImages/"; // end your path with a /
	  //  String myExternalFilePath = "file:/home/profileImages/";
	        registry
	          .addResourceHandler("/**")
	          .addResourceLocations("classpath:/static/",myExternalFilePath);
	        
	        

	    }
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		internationalization intr = new internationalization();
	    registry.addInterceptor(intr.localeChangeInterceptor());
	}
	
	
	
    @Bean
    public userServces UserServces() {
    	return new userServces();
    }
	
	@Bean
	public StorageService storageService() {
	   	 return new CStorgeServces();
	}
	
    @Bean
    public forgotPasswordService forgotPassServ() {
    	 return new forgotPasswordService();
    }
	
    @Bean
    public verfiyEmailService verfiyEmailS() {
    	return new verfiyEmailService();
    }
    
    @Bean
    public controlCenterServces controlCenter() {
    	return new controlCenterServces();
    }
    
    
	

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
	  SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	  //templateResolver.setPrefix("file:/home/templates/");
	  templateResolver.setPrefix("file:D:/pathToView/jedarView/templates/");
	  templateResolver.setSuffix(".html");
	  templateResolver.setCharacterEncoding("UTF-8");
	  return templateResolver;
	}

	
	
	

    
    
    
    
    
    
}
