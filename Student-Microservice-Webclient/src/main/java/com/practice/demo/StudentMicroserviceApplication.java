package com.practice.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({"com.practice.demo.controller", "com.practice.demo.service"})
@EntityScan("com.practice.demo.entity")
@EnableJpaRepositories("com.practice.demo.repository")
@EnableDiscoveryClient
public class StudentMicroserviceApplication {

	@Value("${address.service.url}")
	private String addressServiceUrl;
	public static void main(String[] args) {
		SpringApplication.run(StudentMicroserviceApplication.class, args);
	}
	
	@Bean
	public WebClient getWebClient() {
	
		WebClient webClient = WebClient.builder().baseUrl(addressServiceUrl).build();
		return webClient;
	}

}
