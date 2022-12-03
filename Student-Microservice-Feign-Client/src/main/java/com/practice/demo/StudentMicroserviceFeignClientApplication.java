package com.practice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"com.practice.demo.controller", "com.practice.demo.service"})
@EntityScan("com.practice.demo.entity")
@EnableJpaRepositories("com.practice.demo.repository")
@EnableFeignClients("com.practice.demo.feignclient")
@EnableDiscoveryClient
public class StudentMicroserviceFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentMicroserviceFeignClientApplication.class, args);
	}

}
