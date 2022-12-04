package com.practice.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.entity.Student;
import com.practice.demo.repository.StudentRepository;
import com.practice.demo.request.CreateStudentRequest;
import com.practice.demo.response.AddressResponse;
import com.practice.demo.response.StudentResponse;

@Service
public class StudentService {

	Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentServiceWithResilence4JandAOP addressService;
	
	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddress_id(createStudentRequest.getAddressId());
		student = studentRepository.save(student); 
		StudentResponse response = new StudentResponse(student);
		response.setAddress(addressService.getAddressById(student.getAddress_id()));
		return response;
	}
	
	public StudentResponse getById (long id) {
		logger.info("Inside studentservice getById");
		Student student = studentRepository.findById(id).get();
		AddressResponse address = addressService.getAddressById(student.getAddress_id());
		StudentResponse response = new StudentResponse(student);
		response.setAddress(address);
		return response;
	}
	
}
