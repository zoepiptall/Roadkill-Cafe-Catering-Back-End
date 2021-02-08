package com.project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.entity.Address;
import com.project2.repository.AddressDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressService {

	public void AddressService() {
		
	}
	
	public AddressDao addressDao;
	
	public List<Address> findAll(){
		return addressDao.findAll();
	}
	
}
