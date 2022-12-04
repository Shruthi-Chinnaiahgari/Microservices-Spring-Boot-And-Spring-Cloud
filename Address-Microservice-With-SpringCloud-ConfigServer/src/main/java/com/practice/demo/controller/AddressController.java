package com.practice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.requestmodel.CreateAddressRequest;
import com.practice.demo.responsemodel.AddressResponse;
import com.practice.demo.service.AddressService;

@RestController
@RequestMapping("/api/address")
//to refresh config server updated without restarting the application
//use spring boot actuator
@RefreshScope
public class AddressController {
	
	@Value("${address.test}")
	private String test;
	
	@Autowired
	AddressService addressService;
	
	@PostMapping("/add")
	public AddressResponse addAddress(@RequestBody CreateAddressRequest request) {
		AddressResponse response = addressService.addAddress(request);
		return response;
		
	}
	
	@GetMapping("/getAddress/{id}")
	public AddressResponse getAddressById(@PathVariable long id) {
		AddressResponse response = addressService.getAddress(id);
		return response;
		
	}
	
	
	@GetMapping("/testConfigServer")
	public String testConfigServer() {
		return test;
	}
	
}
