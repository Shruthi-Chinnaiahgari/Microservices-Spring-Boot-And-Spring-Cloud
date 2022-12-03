package com.practice.demo.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class CustomFilter implements GlobalFilter{
	
	Logger logger = LoggerFactory.getLogger(CustomFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		
		//code specific to only one API
		if(request.getURI().toString().contains("/api/studet")) {
			System.out.println("Code executed only for student API");
		}
		
		logger.info("Pre filter Authorization = " + request.getHeaders().getFirst("Authorization"));
		//route the request to controller
		//statement before then is pre filter and statement after then is post filter
		return chain.filter(exchange).then(Mono.fromRunnable(() ->{
			ServerHttpResponse response = exchange.getResponse();
			logger.info("Post filter status code is " + response.getStatusCode());
			
		}));
	}

}
