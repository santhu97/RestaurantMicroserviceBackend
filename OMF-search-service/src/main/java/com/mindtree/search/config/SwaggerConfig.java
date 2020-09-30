package com.mindtree.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()                                 
          .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))            
          .paths(PathSelectors.any())                         
          .build()
          .apiInfo(metaData());


    }
    private ApiInfo metaData() {
           
        @SuppressWarnings("deprecation")
        ApiInfo apiInfo = new ApiInfo(
                "Search Service",
                "Search Sevice", "REST API", "Spring Boot ", "Nithin Kambesh", "Version 1.0 ", "Copyright - MINDTREE ");
       
        return apiInfo;
    }


}
