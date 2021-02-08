package com.project2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.entity.Customer;
import com.project2.service.CustomerService;
import com.project2.util.PBEUtil;
import com.project2.validator.CustomerValidator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="/users")
@AllArgsConstructor(onConstructor=@__(@Autowired)) 
@NoArgsConstructor
public class CustomerController {
	
	private CustomerService customerServ;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new CustomerValidator());
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> insertCustomer(@RequestBody @Valid Customer customer, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getFieldError().getCode() + " " + result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
		} else if(customerServ.findByEmail(customer.getEmail()) != null) {
			return new ResponseEntity<>("Email already in use", HttpStatus.NOT_ACCEPTABLE);
		} else if(customerServ.findByUsername(customer.getUsername()) != null) {
			return new ResponseEntity<>("Username already in use", HttpStatus.NOT_ACCEPTABLE);
		} else if(customerServ.findByPhoneNumber(customer.getPhoneNumber()) != null) {
			return new ResponseEntity<>("Phone Number already in use", HttpStatus.NOT_ACCEPTABLE);
		}
		
		customerServ.insert(customer);
		return new ResponseEntity<>("Resource created", HttpStatus.CREATED);
	}
	
	//TODO: Session handling, saving user on either front or back end
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Customer customer) {
		System.out.println(customer);
		if(customerServ.findByUsername(customer.getUsername()) == null) {
			return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
		}else if(!customerServ.verifyPassword(customer.getUsername(), customer.getPassword())) {
			return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("Login successful", HttpStatus.OK);
	}
	
	//TODO: Error handling
	@GetMapping("/{username}")
	public ResponseEntity<Customer> getUserByUsername(@PathVariable("username") String username) {
		System.out.println(username);
		Customer customer = customerServ.findByUsername(username);
		customer.setPassword("");
		customer.setSalt("");
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@PostMapping("/update") 
	public ResponseEntity<String> updateUser(@RequestBody @Valid Customer customer, BindingResult result) {
		
		
		if(result.hasErrors()) {
			return new ResponseEntity<>(result.getFieldError().getCode() + " " + result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
		} else if(customerServ.findByEmail(customer.getEmail()) != null) {
			if (!customerServ.findByEmail(customer.getEmail()).getUsername().equals(customer.getUsername())) {
				return new ResponseEntity<>("Email address already in use", HttpStatus.NOT_ACCEPTABLE);				
			}
		} else if(customerServ.findByPhoneNumber(customer.getPhoneNumber()) != null) {
			if(!customerServ.findByPhoneNumber(customer.getPhoneNumber()).getUsername().equals(customer.getUsername()) ) {
				return new ResponseEntity<>("Phone number already in use", HttpStatus.NOT_ACCEPTABLE);				
			}
		}
		
		customerServ.update(customer);
		return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
	}
	 
	 
	//TODO: Implement java mail recovery email with auto-generated password
	@PostMapping("/recover")
	public ResponseEntity<String> recoverPassword(@RequestBody Customer customer) {
		
		customer = customerServ.findByEmail(customer.getEmail());
		
		/*
		 * if(customerServ.findByEmail(customer.getEmail()) == null) { return new
		 * ResponseEntity<>("No account found for that email address",
		 * HttpStatus.NOT_FOUND); }
		 */
		
		if(customer == null) {
			return new ResponseEntity<>("No account found for that email address", HttpStatus.NOT_FOUND);
		}
		
		customerServ.recoverPassword(customer);
		
		
		/*
		 * SimpleMailMessage message = new SimpleMailMessage(); message.setTo(to);
		 * message.setSubject(subject); message.setText(text);
		 * emailSender.send(message);
		 */
		
		//Add java mail call here
		
		return new ResponseEntity<>("Recovery email sent", HttpStatus.OK);
	}
	

}
