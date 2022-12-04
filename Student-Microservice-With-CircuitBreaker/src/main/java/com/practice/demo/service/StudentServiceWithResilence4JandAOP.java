package com.practice.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.feignclient.AddressFeignClient;
import com.practice.demo.response.AddressResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class StudentServiceWithResilence4JandAOP {
	
	Logger logger = LoggerFactory.getLogger(StudentServiceWithResilence4JandAOP.class);
	
	int count = 1;

	@Autowired
	AddressFeignClient addressFeignClient;
	
	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
	public AddressResponse getAddressById(long id) {
		logger.info("count is : "+ count);
		count++;
		AddressResponse address = addressFeignClient.getAddressById(id);
		return address;
	}
	
	public AddressResponse fallbackGetAddressById(long id, Throwable th) {
		logger.error("Error : " + th.getMessage());
		return new AddressResponse();
	}
}
