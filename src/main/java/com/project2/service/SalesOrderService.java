package com.project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.entity.Customer;
import com.project2.entity.InvoiceItem;
import com.project2.entity.SalesOrder;
import com.project2.repository.SalesOrderDao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class SalesOrderService {

	
	public SalesOrderDao salesOrderDao;

	public void create(SalesOrder salesOrder) {
		salesOrderDao.save(salesOrder);
	}
	
	public List<SalesOrder> findByCustomer(Customer customer){ 
		 return salesOrderDao.findByCustomer(customer); 
	}
	
	public SalesOrder findByCustomerAndStatus(Customer customer, String status) {
		SalesOrder sOrder = salesOrderDao.findByCustomerAndStatus(customer, status);
		if(sOrder == null) {
			sOrder = new SalesOrder(customer);
		}
		create(sOrder);
		return sOrder;
	}
	
	public SalesOrder findByOrderId(int id) {
		return salesOrderDao.findByOrderId(id);
	}
	
	public void deleteItem(SalesOrder salesOrder, InvoiceItem item) {
		List<InvoiceItem> iList = salesOrder.getItemList();
		for(InvoiceItem i : iList) {
			if(i.getInventory() == item.getInventory()) {
				iList.remove(i);
				break;
			}
		}
		salesOrder.setItemList(iList);
		salesOrderDao.save(salesOrder);
	}

	
//	public void addItem(SalesOrder salesOrder, InvoiceItem item) {
//		List<InvoiceItem> iList = salesOrder.getItemList();
//		iList.add(item);
//		salesOrder.setItemList(iList);
//		salesOrderDao.save(salesOrder);
//	}
//	
	/*
	 * // public void deleteItem(SalesOrder salesOrder, InvoiceItem item) { //
	 * List<InvoiceItem> iList = salesOrder.getItemList(); // for(InvoiceItem i :
	 * iList) { // if(i.getInventory() == item.getInventory()) { // iList.remove(i);
	 * // break; // } // } // salesOrder.setItemList(iList); //
	 * salesOrderDao.save(salesOrder); // }
	 *///	
//	public void updateOrder(SalesOrder salesOrder, InvoiceItem item) {
//		item.getInvoiceItemId();
//		int quantity = item.getQuantity();
//		List<InvoiceItem> iList = salesOrder.getItemList();
//		for(InvoiceItem x : iList) {
//			if(x.getInventory() == item.getInventory()) {
//				x.setQuantity(quantity);
//				break;
//			}
//		}
//		salesOrder.setItemList(iList);
//		salesOrderDao.save(salesOrder);
//	}
	
}
