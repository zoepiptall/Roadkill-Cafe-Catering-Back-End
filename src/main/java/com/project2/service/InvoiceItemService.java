package com.project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.entity.InvoiceItem;
import com.project2.entity.SalesOrder;
import com.project2.repository.InvoiceItemDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceItemService {

	public InvoiceItemService() {
		// TODO Auto-generated constructor stub
	}
	
	public InvoiceItemDao invItemDao;
	
	public List<InvoiceItem> findAll(){
		return invItemDao.findAll();
	}
	
	public InvoiceItem findByInvoiceItemId(int id) {
		return invItemDao.findByInvoiceItemId(id);
	}
	
	public void save(InvoiceItem item) {
		invItemDao.save(item);
	}
	
	public void updateItem(int invoiceItemId, int quantity) {
		InvoiceItem item = findByInvoiceItemId(invoiceItemId);
		if(quantity == 0) {
			invItemDao.delete(item);
		} else {
			item.setQuantity(quantity);
			invItemDao.save(item);
		}
	}
}
