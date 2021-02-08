package com.project2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.entity.Customer;
import com.project2.entity.Address;

public interface AddressDao extends JpaRepository<Address, Integer>{
	
	Address findByAddressId(int id);
	List<Address> findAll();
}
