package com.project2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.entity.Customer;


public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
	List<Customer> findAll();
	Customer findByCustomerId(int id);
	Customer findByUsername(String username);
	Customer findByFirstNameAndLastName(String firstName, String lastName);
	Customer findByEmail(String email);
	Customer findByPhoneNumber(String phoneNumber);
}
