package com.practice.demo.loadbalancer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

@LoadBalancerClient(value = "address-service")
public class AddressServiceLoadBalancerConfig {
	
	@LoadBalanced
	@Bean
	public Feign.Builder geignBuilder(){
		return Feign.builder();
	}

}
