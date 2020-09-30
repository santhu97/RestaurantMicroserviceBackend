package com.mindtree.zuul.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Configuration
@Primary
@Component
public class DocumentationController implements SwaggerResourcesProvider{

	 @Override
	    public List get() {
	        List resources = new ArrayList<>();
	        resources.add(swaggerResource("zuulservice", "/v2/api-docs", "2.0"));
	        resources.add(swaggerResource("userservice", "/omf/user/v2/api-docs", "2.0"));
	        resources.add(swaggerResource("orderservice", "/omf/order/v2/api-docs", "2.0"));
	        resources.add(swaggerResource("searchservice", "/omf/search/v2/api-docs", "2.0"));
	        return resources;
	    }
	 
	    private SwaggerResource swaggerResource(String name, String location, String version) {
	        SwaggerResource swaggerResource = new SwaggerResource();
	        swaggerResource.setName(name);
	        swaggerResource.setLocation(location);
	        swaggerResource.setSwaggerVersion(version);
	        return swaggerResource;
	    }
	 
	
}
