package com.practice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demo.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
