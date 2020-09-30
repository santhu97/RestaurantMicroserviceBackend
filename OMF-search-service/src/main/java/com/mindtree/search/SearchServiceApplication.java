package com.mindtree.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import com.mindtree.search.controller.SearchController;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableCaching
public class SearchServiceApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchServiceApplication.class);


	public static void main(String[] args) {
		logger.info("Seach service Started Successfully...!");
		SpringApplication.run(SearchServiceApplication.class, args);
	}

}
