package com.project2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.entity.Customer;
import com.project2.entity.InvoiceItem;
import com.project2.entity.SalesOrder;

public interface SalesOrderDao extends JpaRepository<SalesOrder, Integer>{
	
	SalesOrder findByOrderId(int id);
	List<SalesOrder> findAll();
	List<SalesOrder> findByCustomer(Customer customer);
	SalesOrder findByCustomerAndStatus(Customer customer, String status);
}
