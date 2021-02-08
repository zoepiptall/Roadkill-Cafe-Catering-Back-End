package com.project2.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.service.InvoiceItemService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="/invoiceitems")
@AllArgsConstructor(onConstructor=@__(@Autowired)) 
@NoArgsConstructor
public class InvoiceItemController {
	
	private InvoiceItemService iiServ;
	
	@PostMapping("/update")
	public ResponseEntity<String> updateInvoiceItem(@RequestBody LinkedHashMap<String, String> iMap) {
		iiServ.updateItem(Integer.parseInt(iMap.get("invoiceItemId")), Integer.parseInt(iMap.get("quantity")));
		return new ResponseEntity<>("Resource updated", HttpStatus.OK);
	}

}
