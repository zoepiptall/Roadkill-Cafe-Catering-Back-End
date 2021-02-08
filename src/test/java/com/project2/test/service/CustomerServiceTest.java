package com.project2.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.entity.Customer;
import com.project2.repository.CustomerDao;
import com.project2.service.CustomerService;
import com.project2.util.PBEUtil;

@SpringBootTest
public class CustomerServiceTest {
	
	
	@Mock
	private CustomerDao cDao;
	
	@Mock
	private PBEUtil pbeUtil;
	
	@InjectMocks
	private CustomerService cServ;
	
	private Customer customer;
	
	@BeforeEach
	public void setUp() throws Exception {
		customer = new Customer(1, "test", "test", "Amanda", "Love", "test@test.com", "1234567", "abcd");
	
		when(cDao.findByUsername("test")).thenReturn(customer);
		when(cDao.findByEmail("test@test.com")).thenReturn(customer);
		when(cDao.findByPhoneNumber("1234567")).thenReturn(customer);
		
		when(pbeUtil.createSalt(64)).thenReturn("abcd");
		when(pbeUtil.generateSecurePassword("test", "abcd")).thenReturn("bobsyouruncle");
		when(pbeUtil.verifyUserPassword("test", customer.getPassword(), customer.getSalt())).thenReturn(true);
		when(cDao.save(customer)).thenReturn(customer);
	}
	
	
	@Test
	public void testInsertCustomer() {
		customer.setSalt(pbeUtil.createSalt(64));
		customer.setPassword(pbeUtil.generateSecurePassword(customer.getPassword(), customer.getSalt()));
		assertEquals(customer.getSalt(), "abcd");
		assertEquals(customer.getPassword(), "bobsyouruncle");	
	}
	
	@Test
	public void testFindByUsernameSuccess() {
		assertEquals(cServ.findByUsername("test"), customer);
	}
	
	@Test
	public void testFindByUsernameFailure() {
		assertEquals(cServ.findByUsername("nope"), null);
	}
	
	@Test
	public void testFindByEmailSuccess() {
		assertEquals(cServ.findByEmail("test@test.com"), customer);
	}
	
	@Test
	public void testFindByEmailFailure() {
		assertEquals(cServ.findByEmail("nope"), null);
	}
	
	@Test
	public void testFindByPhoneNumberSuccess() {
		assertEquals(cServ.findByPhoneNumber("1234567"), customer);
	}
	
	@Test
	public void testFindByPhoneNumberFailure() {
		assertEquals(cServ.findByPhoneNumber("0"), null);
	}
	
	
	@Test
	public void testVerifyPassword() {
		
		assertEquals(cServ.verifyPassword("test", "test"), true);
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer custUpdate = new Customer (1, "test", "test", "Amanda", "Hugenkiss", "test@test.com", "1234567");
		custUpdate.setSalt("abcd");
		String password = pbeUtil.generateSecurePassword(custUpdate.getPassword(), custUpdate.getSalt());
//		customer.setSalt(pbeUtil.createSalt(64));
		cServ.update(custUpdate);
		custUpdate.setPassword(password);
		assertEquals(customer.getCustomerId(), custUpdate.getCustomerId());
		assertEquals(customer.getEmail(), custUpdate.getEmail());
		assertEquals(customer.getFirstName(), custUpdate.getFirstName());
		assertEquals(customer.getLastName(), custUpdate.getLastName());
		assertEquals(customer.getPassword(), custUpdate.getPassword());
		assertEquals(customer.getPhoneNumber(), custUpdate.getPhoneNumber());
		
		
	}
	
}
