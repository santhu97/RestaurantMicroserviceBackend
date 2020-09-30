package com.mindtree.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker
@RefreshScope
public class UserServiceApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);


	public static void main(String[] args) {
		logger.info("User Management service Satrted SuccessFully....!");
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
