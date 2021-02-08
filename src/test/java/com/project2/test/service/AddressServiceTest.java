package com.project2.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.entity.Address;
import com.project2.repository.AddressDao;
import com.project2.service.AddressService;

@SpringBootTest
public class AddressServiceTest {
	
	@Mock
	private AddressDao aDao;
	
	@InjectMocks
	private AddressService aServ;
	
	private Address address;
	
	@BeforeEach
	public void setUp() {
		address = new Address(9001, "1234 Street", "city", "state", 90210);
		List<Address> aList = new ArrayList<Address>();
		aList.add(address);
		when(aDao.findAll()).thenReturn(aList);
		
	}
	
	@Test
	public void testFindAll() {
		List<Address> aList = new ArrayList<Address>();
		aList.add(address);
		assertEquals(aServ.findAll(), aList);
	}
	

}
