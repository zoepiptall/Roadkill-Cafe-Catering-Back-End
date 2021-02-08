package com.project2.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.entity.Customer;
import com.project2.entity.Inventory;
import com.project2.entity.InvoiceItem;
import com.project2.entity.SalesOrder;
import com.project2.service.CustomerService;
import com.project2.service.InventoryService;
import com.project2.service.InvoiceItemService;
import com.project2.service.SalesOrderService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="/salesorders")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class SalesOrderController {
	
	private SalesOrderService sServ;
	private InventoryService iServ;
	private CustomerService cServ;
	private InvoiceItemService itemServ;
	
	@PostMapping("/add")
	public ResponseEntity<String> insertItems(@RequestBody LinkedHashMap<String, String> iMap){
		Inventory inventory = iServ.findByInventoryId(Integer.parseInt(iMap.get("itemId")));
		InvoiceItem item = new InvoiceItem(Integer.parseInt((String)iMap.get("quantity")), inventory);
		
		
		Customer customer = cServ.findById(Integer.parseInt(iMap.get("customerId")));
		
		SalesOrder sOrder = sServ.findByCustomerAndStatus(customer, "Pending");
		
		itemServ.save(item);
		
		List<InvoiceItem> iList = sOrder.getItemList();
		iList.add(item);
		sOrder.setItemList(iList);
		
		sServ.create(sOrder);
		
		return new ResponseEntity<>("Resource created!", HttpStatus.CREATED);
	}
	
	@GetMapping("/pending/{customerId}")
	public ResponseEntity<SalesOrder> getPendingOrder(@PathVariable("customerId") Integer customerId) {
		Customer customer = cServ.findById(customerId);
		SalesOrder sOrder = sServ.findByCustomerAndStatus(customer, "Pending");
		return new ResponseEntity<>(sOrder, HttpStatus.OK);
	}
	
	@PostMapping("/submit")
	public ResponseEntity<String> submitOrder(@RequestBody LinkedHashMap<String, String> iMap){
		Customer customer = cServ.findById(Integer.parseInt(iMap.get("customerId")));
		SalesOrder sOrder = sServ.findByCustomerAndStatus(customer, "Pending");
		sOrder.setStatus("Complete");
		sServ.create(sOrder);
		
		return new ResponseEntity<>("Resource updated!", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteItem(@RequestBody LinkedHashMap<String, String> iMap){
		Inventory item1 = iServ.findByInventoryId(Integer.parseInt(iMap.get("itemId")));
		Customer customer = cServ.findById(Integer.parseInt(iMap.get("customerId")));
		InvoiceItem item = new InvoiceItem(item1);
		SalesOrder sOrder = (SalesOrder) sServ.findByCustomer(customer);
		sServ.deleteItem(sOrder, item);
		return new ResponseEntity<>("Resource deleted!", HttpStatus.GONE);
	}

	
	
	
//	
//	@DeleteMapping("/delete")
//	public ResponseEntity<String> deleteItem(@RequestBody LinkedHashMap<String, String> iMap){
//		Inventory item1 = iServ.findByInventoryId(Integer.parseInt(iMap.get("itemId")));
//		Customer customer = cServ.findById(Integer.parseInt(iMap.get("customerId")));
//		InvoiceItem item = new InvoiceItem(item1);
//		SalesOrder sOrder = (SalesOrder) sServ.findByCustomer(customer);
//		sServ.deleteItem(sOrder, item);
//		return new ResponseEntity<>("Resource deleted!", HttpStatus.GONE);
//	}	
//	
//	@PostMapping("/update")
//	public ResponseEntity<String> updateItem(@RequestBody LinkedHashMap<String, String> iMap){
//		Inventory inventory = iServ.findByInventoryId(Integer.parseInt(iMap.get("itemId")));
//		InvoiceItem item = new InvoiceItem(Integer.parseInt((String)iMap.get("quantity")), inventory);
//		Customer customer = cServ.findById(Integer.parseInt(iMap.get("customerId")));
//		SalesOrder sOrder = (SalesOrder) sServ.findByCustomer(customer);
//		sServ.updateOrder(sOrder, item);
//		return new ResponseEntity<>("Resource updated!", HttpStatus.OK);
//	}

}
