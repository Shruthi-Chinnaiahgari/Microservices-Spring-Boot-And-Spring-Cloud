package com.practice.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.practice.demo.entity.Student;
import com.practice.demo.repository.StudentRepository;
import com.practice.demo.request.CreateStudentRequest;
import com.practice.demo.response.AddressResponse;
import com.practice.demo.response.StudentResponse;

import reactor.core.publisher.Mono;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	WebClient webClient;


	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddress_id(createStudentRequest.getAddressId());
		student = studentRepository.save(student); 
		StudentResponse response = new StudentResponse(student);
		response.setAddress(getAdressById(student.getAddress_id()));
		return response;
	}
	
	public StudentResponse getById (long id) {
		Student student = studentRepository.findById(id).get();
		AddressResponse address = getAdressById(student.getAddress_id());
		StudentResponse response = new StudentResponse(student);
		response.setAddress(address);
		return response;
	}
	
	public AddressResponse getAdressById(long addressId) {
		Mono<AddressResponse> addressResponse = webClient.get().uri("/getAddress/"+addressId).retrieve()
		.bodyToMono(AddressResponse.class);
		return addressResponse.block();
	}
}
