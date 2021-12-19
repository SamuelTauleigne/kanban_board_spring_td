package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@PropertySource("classpath:swagger.properties")
public class SpringFoxConfiguration {
	
	@Bean
	public Docket apiDocket() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("fr.telecom_st_etienne.samuel_tauleigne"))
	            .paths(PathSelectors.any())
	            .build()
	            .apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
	    return new ApiInfo(
	            "Kanban Board Application",
	            "Spring Boot Project - FISE 3",
	            "VERSION 1.0",
	            "TERMS OF SERVICE URL",
	            new Contact("Samuel Tauleigne","https://github.com/SamuelTauleigne/kanban_board_spring_td","samuel.tauleigne@telecom-st-etienne.fr"),
	            "LICENSE",
	            "LICENSE URL",
	            Collections.emptyList()
	    );
	}
    
}