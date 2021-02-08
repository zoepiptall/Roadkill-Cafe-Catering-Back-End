package com.project2.test.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.entity.Customer;
import com.project2.entity.SalesOrder;
import com.project2.repository.SalesOrderDao;
import com.project2.service.SalesOrderService;

@SpringBootTest
public class SalesOrderServiceTest {

	@Mock
	private SalesOrderDao soDao;

	@InjectMocks
	private SalesOrderService soServ;
	private Customer customer;
	private SalesOrder sOrder;

	@BeforeEach
	public void setUp() throws Exception {
		customer = new Customer(1, "test", "test", "Amanda", "Love", "test@test.com", "1234567", "abcd");
		sOrder = new SalesOrder(432, "pending", customer);
		when(soDao.findByCustomerAndStatus(customer, "pending")).thenReturn(sOrder);
//		when(soDao.findById(432)).thenReturn(sOrder);
	}

	@Test
	public void testCreateSalesOrder() {
		soServ.create(sOrder);
		verify(soDao).save(sOrder);
	}

	@Test
	public void testFindByCustomerAndStatus() {
		assertEquals(soServ.findByCustomerAndStatus(customer, "pending"), sOrder);
	}
	
//	@Test
//	public void testFindByOrderId () {
//		
//	}
	

}
