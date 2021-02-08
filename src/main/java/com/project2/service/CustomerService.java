package com.project2.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.entity.Customer;
import com.project2.repository.CustomerDao;
import com.project2.util.EmailUtilImpl;
import com.project2.util.PBEUtil;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class CustomerService {
	
	public CustomerDao customerDao;
	public EmailUtilImpl emailUtil;
	
	public PBEUtil pbeUtil;
	
	public void insert(Customer customer) {
		
		customer.setSalt(pbeUtil.createSalt(64));
		customer.setPassword(pbeUtil.generateSecurePassword(customer.getPassword(), customer.getSalt()));
		
		customerDao.save(customer);
	}
	
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	
	public Customer findById(int id){
		return customerDao.findByCustomerId(id);
	}
	
	public Customer findByUsername(String username) {
		return customerDao.findByUsername(username);
	}
	
	public Customer findByEmail(String email) {
		return customerDao.findByEmail(email);
	}
	
	public Customer findByPhoneNumber(String phoneNumber) {
		return customerDao.findByPhoneNumber(phoneNumber);
	}
	
	public boolean verifyPassword(String username, String password) {
		Customer customer = customerDao.findByUsername(username);
		return pbeUtil.verifyUserPassword(password, customer.getPassword(), customer.getSalt());
		//return customerDao.findByUsername(username).getPassword().equals(password);
	}
	
	
	public void update(Customer customer) {
		Customer oldCustomer = findByUsername(customer.getUsername());
		oldCustomer.setEmail(customer.getEmail());
		oldCustomer.setFirstName(customer.getFirstName());
		oldCustomer.setLastName(customer.getLastName());
		if(!customer.getPassword().isEmpty()) {
			oldCustomer.setPassword(pbeUtil.generateSecurePassword(customer.getPassword(), oldCustomer.getSalt()));
		}
		oldCustomer.setPhoneNumber(customer.getPhoneNumber());
		
		customerDao.save(oldCustomer);
	}
	
	public void recoverPassword(Customer customer) {
		Random rnd = new Random();
		String newPassword = String.valueOf(100000 + rnd.nextInt(900000));
		
		customer.setPassword(pbeUtil.generateSecurePassword(newPassword, customer.getSalt()));
		
		customerDao.save(customer);
		
		emailUtil.sendSimpleMessage(customer.getEmail(), "Roadkill Cafe Catering Password Reset", "Your new password is " + newPassword);
		

	}

	public Object findAll() {
		
		return customerDao.findAll();
	}
	
}
