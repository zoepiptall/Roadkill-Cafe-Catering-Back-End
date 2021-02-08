package com.project2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.entity.InvoiceItem;
import com.project2.entity.SalesOrder;

public interface InvoiceItemDao extends JpaRepository<InvoiceItem, Integer>{
	
	InvoiceItem findByInvoiceItemId(int id);
	//List<InvoiceItem> findBySalesOrder(SalesOrder salesOrder);
}
