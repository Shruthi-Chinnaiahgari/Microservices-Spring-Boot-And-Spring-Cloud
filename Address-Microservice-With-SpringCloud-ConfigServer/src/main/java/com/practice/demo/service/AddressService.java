package com.practice.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.entities.Address;
import com.practice.demo.repository.AddressRepository;
import com.practice.demo.requestmodel.CreateAddressRequest;
import com.practice.demo.responsemodel.AddressResponse;

@Service
public class AddressService {
	
	Logger logger = LoggerFactory.getLogger(AddressService.class);
	
	@Autowired
	AddressRepository addressRepo;

	public AddressResponse addAddress(CreateAddressRequest request) {
		Address address = new Address();
		address.setCity(request.getCity());
		address.setStreet(request.getStreet());
		addressRepo.save(address);
		AddressResponse response = new AddressResponse(address);
		return response;
	}

	public AddressResponse getAddress(long id) {
		logger.info("Id is " + id);
		Address address = addressRepo.findById(id).get();
		AddressResponse response = new AddressResponse(address);
		return response;
	}
	
	

}
