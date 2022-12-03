package com.practice.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.entity.Student;
import com.practice.demo.feignclient.AddressFeignClient;
import com.practice.demo.repository.StudentRepository;
import com.practice.demo.request.CreateStudentRequest;
import com.practice.demo.response.AddressResponse;
import com.practice.demo.response.StudentResponse;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	AddressFeignClient addressFeignClient;
	
	
	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddress_id(createStudentRequest.getAddressId());
		student = studentRepository.save(student); 
		StudentResponse response = new StudentResponse(student);
		response.setAddress(addressFeignClient.getAddressById(student.getAddress_id()));
		return response;
	}
	
	public StudentResponse getById (long id) {
		Student student = studentRepository.findById(id).get();
		AddressResponse address = addressFeignClient.getAddressById(student.getAddress_id());
		StudentResponse response = new StudentResponse(student);
		response.setAddress(address);
		return response;
	}
	

}
